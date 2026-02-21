package fr.paladium.palavanillagui.client.ui.util.container;

import fr.paladium.paladiumui.kit.button.TextButtonNode;
import fr.paladium.paladiumui.kit.constant.ResourceConstant;
import fr.paladium.palavanillagui.client.ClientProxy;
import fr.paladium.palavanillagui.client.ui.inventory.UIDirectory;
import fr.paladium.palavanillagui.client.ui.inventory.UIInventory;
import fr.paladium.palavanillagui.client.ui.util.node.ShortcutNode;
import fr.paladium.palavanillagui.common.utils.InventoryShortcut;
import fr.paladium.translate.common.texttotranslate.TTT;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.property.draggable.DraggableProperty;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.ListSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.BooleanSignal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class ShortcutListContainer extends ContainerNode {
  private static final Resource SHORTCUT_LIST_BACKGROUND = Resource.of(new ResourceLocation("palavanillagui", "textures/gui/inventory/shortcut_list_background.png"));
  
  public static ListSignal<InventoryShortcut> listSignal = null;
  
  public static BooleanSignal modifySignal = null;
  
  protected ShortcutListContainer(double x, double y, double width, double height) {
    super(x, y, width, height);
    listSignal = new ListSignal(new LinkedList(ClientProxy.configInventoryShortcut.getShortcuts()));
    modifySignal = new BooleanSignal(false);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    body(() -> {
          FlexNode.vertical(0.0D, 0.0D, 88.38D).margin(12.0D).onInit(()).watch((Signal)listSignal).attach((Node)this);
          TextButtonNode.create(0.0D, 527.0D).icon(ResourceConstant.FLAT_EDIT).iconMargin(10.0D).iconSize(17.0D).horizontalPadding(15.0D).onInit(()).onClick(()).watch((Signal)((UIDirectory)getUi()).getEditMode()).watch((Signal)modifySignal).attach((Node)this);
        });
  }
  
  public static ShortcutListContainer create(double x, double y, double width, double height) {
    return new ShortcutListContainer(x, y, width, height);
  }
  
  public static void saveChanges(UIDirectory ui) {
    ui.getEditMode().set(Boolean.valueOf(false));
    ClientProxy.configInventoryShortcut.setShortcuts((LinkedList)listSignal.getOrDefault());
    listSignal.set(new LinkedList(ClientProxy.configInventoryShortcut.getShortcuts()));
    modifySignal.set(Boolean.valueOf(false));
    UIInventory inventory = (UIInventory)ZUI.getUI(UIInventory.class);
    if (inventory != null)
      inventory.reloadList(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\clien\\u\\util\container\ShortcutListContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */