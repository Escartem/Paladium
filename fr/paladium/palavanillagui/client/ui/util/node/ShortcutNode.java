package fr.paladium.palavanillagui.client.ui.util.node;

import fr.paladium.palavanillagui.client.ui.inventory.UIDirectory;
import fr.paladium.palavanillagui.common.utils.InventoryShortcut;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import fr.paladium.zephyrui.lib.utils.list.IndexedElement;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class ShortcutNode extends Node {
  public static final Resource SHORTCUT_BACKGROUND = Resource.of(new ResourceLocation("palavanillagui", "textures/gui/inventory/shortcut_background.png"));
  
  public static final Resource SHORTCUT_BACKGROUND_HOVER = Resource.of(new ResourceLocation("palavanillagui", "textures/gui/inventory/shortcut_background_hover.png"));
  
  private InventoryShortcut shortcut = null;
  
  public InventoryShortcut getShortcut() {
    return this.shortcut;
  }
  
  private Resource background = SHORTCUT_BACKGROUND;
  
  public Resource getBackground() {
    return this.background;
  }
  
  private Resource backgroundHover = SHORTCUT_BACKGROUND_HOVER;
  
  private ImageNode icon;
  
  public Resource getBackgroundHover() {
    return this.backgroundHover;
  }
  
  public ImageNode getIcon() {
    return this.icon;
  }
  
  private boolean shortcutListed = false;
  
  public boolean isShortcutListed() {
    return this.shortcutListed;
  }
  
  protected ShortcutNode(double x, double y, double size) {
    super(x, y, size, size);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    ImageNode.create(0.0D, 0.0D)
      .resource(SHORTCUT_BACKGROUND, this.backgroundHover)
      .linear(false).size(getWidth(), getHeight())
      .attach(this);
    onClick((node, mouseX, mouseY, clickType) -> {
          if (ui instanceof UIDirectory && ((Boolean)((UIDirectory)getUi()).getEditMode().getOrDefault()).booleanValue())
            return; 
          if (this.shortcut != null)
            this.shortcut.execute(); 
        });
  }
  
  public void draw(double mouseX, double mouseY, float ticks) {
    if (this.shortcut == null || this.icon == null)
      return; 
    double iconSize = dw(2.0D);
    double x = dw(2.0D) - iconSize / 2.0D;
    double y = dh(2.0D) - iconSize / 2.0D;
    this.icon.bounds(x, y, iconSize, iconSize);
  }
  
  public static ShortcutNode create(double x, double y, double size) {
    return new ShortcutNode(x, y, size);
  }
  
  public ShortcutNode shortcut(InventoryShortcut shortcut) {
    this.shortcut = shortcut;
    if (this.shortcut == null)
      return this; 
    if (this.icon != null)
      getChildren().remove((IndexedElement)this.icon); 
    double iconSize = dw(2.0D);
    double x = dw(2.0D) - iconSize / 2.0D;
    double y = dh(2.0D) - iconSize / 2.0D;
    this.icon = (ImageNode)ImageNode.create(x, y).resource(this.shortcut.getImageLink()).size(iconSize, iconSize).zindex(1).attach(this);
    return this;
  }
  
  public void mousePressed(double mouseX, double mouseY, @NonNull ClickType clickType, @NonNull InternalContext context) {
    if (clickType == null)
      throw new NullPointerException("clickType is marked non-null but is null"); 
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    if (isHovered(mouseX, mouseY) && !isShortcutListed() && getUi() instanceof UIDirectory) {
      if (!((Boolean)((UIDirectory)getUi()).getEditMode().getOrDefault()).booleanValue()) {
        context.cancel();
        return;
      } 
      ((UIDirectory)getUi())
        .getDraggedNode()
        .shortcut(this.shortcut)
        .x(getAbsoluteX())
        .y(getAbsoluteY())
        .dragging(true, mouseX, mouseY)
        .visible(node -> true);
    } 
    super.mousePressed(mouseX, mouseY, clickType, context);
  }
  
  public ShortcutNode resource(Resource background) {
    this.background = background;
    return this;
  }
  
  public ShortcutNode resource(Resource background, Resource hover) {
    this.background = background;
    this.backgroundHover = hover;
    return this;
  }
  
  public ShortcutNode shortcutListed(boolean listed) {
    this.shortcutListed = listed;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\clien\\u\\util\node\ShortcutNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */