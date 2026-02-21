package fr.paladium.palarpg.module.dungeon.client.ui.utils;

import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.event.impl.interact.mouse.UIMouseDraggedEvent;
import fr.paladium.zephyrui.lib.event.impl.interact.mouse.UIMousePressedEvent;
import fr.paladium.zephyrui.lib.event.impl.interact.mouse.UIMouseReleasedEvent;
import fr.paladium.zephyrui.lib.event.impl.interact.mouse.UIMouseScrolledEvent;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import lombok.NonNull;

public class DungeonSynchronizedUI extends UI implements IDungeonSynchronizedUI {
  private String main;
  
  private DungeonSynchronizedCursorNode cursorNode;
  
  private boolean canRemoteClose;
  
  private boolean canRemoteMousePress;
  
  private boolean canRemoteMouseDrag;
  
  private boolean canRemoteMouseRelease;
  
  private boolean canRemoteMouseScroll;
  
  private boolean canRemoteKeyPress;
  
  public void setMain(String main) {
    this.main = main;
  }
  
  public void setCursorNode(DungeonSynchronizedCursorNode cursorNode) {
    this.cursorNode = cursorNode;
  }
  
  public void setCanRemoteClose(boolean canRemoteClose) {
    this.canRemoteClose = canRemoteClose;
  }
  
  public void setCanRemoteMousePress(boolean canRemoteMousePress) {
    this.canRemoteMousePress = canRemoteMousePress;
  }
  
  public void setCanRemoteMouseDrag(boolean canRemoteMouseDrag) {
    this.canRemoteMouseDrag = canRemoteMouseDrag;
  }
  
  public void setCanRemoteMouseRelease(boolean canRemoteMouseRelease) {
    this.canRemoteMouseRelease = canRemoteMouseRelease;
  }
  
  public void setCanRemoteMouseScroll(boolean canRemoteMouseScroll) {
    this.canRemoteMouseScroll = canRemoteMouseScroll;
  }
  
  public void setCanRemoteKeyPress(boolean canRemoteKeyPress) {
    this.canRemoteKeyPress = canRemoteKeyPress;
  }
  
  public String getMain() {
    return this.main;
  }
  
  public DungeonSynchronizedCursorNode getCursorNode() {
    return this.cursorNode;
  }
  
  public boolean isCanRemoteClose() {
    return this.canRemoteClose;
  }
  
  public boolean isCanRemoteMousePress() {
    return this.canRemoteMousePress;
  }
  
  public boolean isCanRemoteMouseDrag() {
    return this.canRemoteMouseDrag;
  }
  
  public boolean isCanRemoteMouseRelease() {
    return this.canRemoteMouseRelease;
  }
  
  public boolean isCanRemoteMouseScroll() {
    return this.canRemoteMouseScroll;
  }
  
  public boolean isCanRemoteKeyPress() {
    return this.canRemoteKeyPress;
  }
  
  public void init() {
    this.cursorNode = (DungeonSynchronizedCursorNode)DungeonSynchronizedCursorNode.create().zindex(100).zlevel(500.0D).attach(this);
  }
  
  public void onRemoteClosed() {
    if (isMain())
      return; 
    this.canRemoteClose = true;
    ZUI.close(this);
  }
  
  public void onRemoteMousePressed(double mouseX, double mouseY, @NonNull ClickType clickType) {
    if (clickType == null)
      throw new NullPointerException("clickType is marked non-null but is null"); 
    if (isMain() || !isInitialized())
      return; 
    this.canRemoteMousePress = true;
    if (getBridge() != null && (new UIMousePressedEvent.Pre(getBridge(), this, clickType, mouseX, mouseY)).post())
      return; 
    InternalContext context = InternalContext.create();
    getNodeList().reversed().stream().filter(node -> (node.getZindex() > 0)).forEach(node -> node.onMousePressed(mouseX, mouseY, clickType, context));
    if (!context.isCancelled() && getData().container())
      clickContainer(mouseX, mouseY, clickType.getButton(), context); 
    getNodeList().reversed().stream().filter(node -> (node.getZindex() <= 0)).forEach(node -> node.onMousePressed(mouseX, mouseY, clickType, context));
    if (!context.isCancelled() && getBridge() != null)
      (new UIMousePressedEvent.Post(getBridge(), this, clickType, mouseX, mouseY)).post(); 
    mousePressed(mouseX, mouseY, clickType, context);
  }
  
  public void onRemoteMouseDragged(double mouseX, double mouseY, @NonNull ClickType clickType, long deltaTime) {
    if (clickType == null)
      throw new NullPointerException("clickType is marked non-null but is null"); 
    if (isMain() || !isInitialized())
      return; 
    this.canRemoteMouseDrag = true;
    if (getBridge() != null && (new UIMouseDraggedEvent.Pre(getBridge(), this, clickType, deltaTime, mouseX, mouseY)).post())
      return; 
    InternalContext context = InternalContext.create();
    getNodeList().reversed().forEach(node -> node.onMouseDragged(mouseX, mouseY, clickType, deltaTime, context));
    if (!context.isCancelled() && getBridge() != null)
      (new UIMouseDraggedEvent.Post(getBridge(), this, clickType, deltaTime, mouseX, mouseY)).post(); 
    mouseDragged(mouseX, mouseY, clickType, deltaTime, context);
  }
  
  public void onRemoteMouseReleased(double mouseX, double mouseY, @NonNull ClickType clickType) {
    if (clickType == null)
      throw new NullPointerException("clickType is marked non-null but is null"); 
    if (isMain() || !isInitialized())
      return; 
    this.canRemoteMouseRelease = true;
    if (getBridge() != null && (new UIMouseReleasedEvent.Pre(getBridge(), this, clickType, mouseX, mouseY)).post())
      return; 
    InternalContext context = InternalContext.create();
    if (getData().container())
      releasedContainer(mouseX, mouseY, clickType.getButton()); 
    getNodeList().reversed().forEach(node -> node.onMouseReleased(mouseX, mouseY, clickType, context));
    if (!context.isCancelled() && getBridge() != null)
      (new UIMouseReleasedEvent.Post(getBridge(), this, clickType, mouseX, mouseY)).post(); 
    mouseReleased(mouseX, mouseY, clickType, context);
  }
  
  public void onRemoteMouseScrolled(double mouseX, double mouseY, int value) {
    if (isMain() || !isInitialized())
      return; 
    this.canRemoteMouseScroll = true;
    if (getBridge() != null && (new UIMouseScrolledEvent.Pre(getBridge(), this, value, mouseX, mouseY)).post())
      return; 
    InternalContext context = InternalContext.create();
    getNodeList().reversed().forEach(node -> node.onMouseScroll(mouseX, mouseY, value, context));
    if (!context.isCancelled() && getBridge() != null)
      (new UIMouseScrolledEvent.Post(getBridge(), this, value, mouseX, mouseY)).post(); 
    mouseScroll(mouseX, mouseY, value, context);
  }
  
  public void onRemoteKeyPressed(char c, int keyCode) {
    if (isMain())
      return; 
    this.canRemoteMousePress = true;
    onKeyPressed(c, keyCode);
  }
  
  public boolean canRemoteClose() {
    if (isMain())
      return true; 
    boolean value = this.canRemoteClose;
    this.canRemoteClose = false;
    return value;
  }
  
  public boolean canRemoteMousePress(double mouseX, double mouseY, @NonNull ClickType clickType) {
    if (clickType == null)
      throw new NullPointerException("clickType is marked non-null but is null"); 
    if (isMain())
      return true; 
    boolean value = this.canRemoteMousePress;
    this.canRemoteMousePress = false;
    return value;
  }
  
  public boolean canRemoteMouseDrag(double mouseX, double mouseY, @NonNull ClickType clickType, long deltaTime) {
    if (clickType == null)
      throw new NullPointerException("clickType is marked non-null but is null"); 
    if (isMain())
      return true; 
    boolean value = this.canRemoteMouseDrag;
    this.canRemoteMouseDrag = false;
    return value;
  }
  
  public boolean canRemoteMouseRelease(double mouseX, double mouseY, @NonNull ClickType clickType) {
    if (clickType == null)
      throw new NullPointerException("clickType is marked non-null but is null"); 
    if (isMain())
      return true; 
    boolean value = this.canRemoteMouseRelease;
    this.canRemoteMouseRelease = false;
    return value;
  }
  
  public boolean canRemoteMouseScroll(double mouseX, double mouseY, int value) {
    if (isMain())
      return true; 
    boolean can = this.canRemoteMouseScroll;
    this.canRemoteMouseScroll = false;
    return can;
  }
  
  public boolean canRemoteKeyPress(char c, int keyCode) {
    if (isMain())
      return true; 
    boolean value = this.canRemoteKeyPress;
    this.canRemoteKeyPress = false;
    return value;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\u\\utils\DungeonSynchronizedUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */