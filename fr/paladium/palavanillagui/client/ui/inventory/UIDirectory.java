package fr.paladium.palavanillagui.client.ui.inventory;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.popup.UIYesNoPopup;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palavanillagui.client.ClientProxy;
import fr.paladium.palavanillagui.client.ui.util.container.ShortcutContainer;
import fr.paladium.palavanillagui.client.ui.util.container.ShortcutListContainer;
import fr.paladium.palavanillagui.client.ui.util.node.ShortcutNode;
import fr.paladium.palavanillagui.common.packet.CSRequestShortcuts;
import fr.paladium.palavanillagui.common.utils.InventoryShortcut;
import fr.paladium.translate.common.texttotranslate.TTT;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.modifier.TextModifier;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.core.data.popup.UIDataPopup;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.property.draggable.DraggableProperty;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.ListSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.BooleanSignal;
import lombok.NonNull;

@UIDataPopup(active = true)
@UIDataGuiscale(active = true)
public class UIDirectory extends UI {
  private final ListSignal<InventoryShortcut> listSignal = new ListSignal();
  
  public ListSignal<InventoryShortcut> getListSignal() {
    return this.listSignal;
  }
  
  private final BooleanSignal editMode = new BooleanSignal(false);
  
  private ShortcutNode draggedNode;
  
  public BooleanSignal getEditMode() {
    return this.editMode;
  }
  
  public ShortcutNode getDraggedNode() {
    return this.draggedNode;
  }
  
  public UIDirectory() {
    (new CSRequestShortcuts()).send();
  }
  
  public void init() {
    this.draggedNode = (ShortcutNode)ShortcutNode.create(0.0D, 0.0D, 98.0D).resource(ShortcutNode.SHORTCUT_BACKGROUND, null).visible(node -> false).draggable(DraggableProperty.screen());
    this.draggedNode.onSnap((node, snapNode) -> {
          if (!(snapNode instanceof ShortcutNode))
            return; 
          ClientProxy.configInventoryShortcut.swapShortcut(this.draggedNode.getShortcut(), ((ShortcutNode)snapNode).getShortcut());
        });
    BackgroundNode.create(1333.57D, 826.0D)
      .body(background -> ContainerNode.create(0.0D, 0.0D, background.getWidth(), background.getHeight()).body(()).attach(background))
      
      .attach(this);
    this.draggedNode.zindex(100).attach(this);
  }
  
  public void mouseReleased(double mouseX, double mouseY, @NonNull ClickType clickType, @NonNull InternalContext context) {
    if (clickType == null)
      throw new NullPointerException("clickType is marked non-null but is null"); 
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    if (isDragging())
      setUndragged(); 
    super.mouseReleased(mouseX, mouseY, clickType, context);
  }
  
  public boolean close() {
    if (((Boolean)this.editMode.getOrDefault()).booleanValue() && ((Boolean)ShortcutListContainer.modifySignal.getOrDefault()).booleanValue()) {
      ZUI.open((UI)(new UIYesNoPopup(
            TTT.format("directory.popup.title", new Object[0]), TTT.format("directory.popup.description", new Object[0])))
          .yesText(TTT.format("directory.popup.confirm", new Object[0]))
          .noText(TTT.format("directory.popup.cancel", new Object[0]))
          .yesCallback(() -> {
              ShortcutListContainer.saveChanges(this);
              ZUI.close(this);
            }));
      return false;
    } 
    return super.close();
  }
  
  public boolean isDragging() {
    return (((Boolean)this.editMode.getOrDefault()).booleanValue() && this.draggedNode.getShortcut() != null);
  }
  
  public void setUndragged() {
    if (((Boolean)this.editMode.getOrDefault()).booleanValue())
      this.draggedNode.shortcut(null).dragging(false, 0.0D, 0.0D).visible(node -> false); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\clien\\ui\inventory\UIDirectory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */