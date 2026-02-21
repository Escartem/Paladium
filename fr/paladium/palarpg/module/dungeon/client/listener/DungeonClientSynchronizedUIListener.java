package fr.paladium.palarpg.module.dungeon.client.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palarpg.module.dungeon.client.ui.utils.IDungeonSynchronizedUI;
import fr.paladium.palarpg.module.dungeon.common.network.ui.sync.BBPacketRPGDungeonSynchronizedUIClose;
import fr.paladium.palarpg.module.dungeon.common.network.ui.sync.BBPacketRPGDungeonSynchronizedUIKeyPress;
import fr.paladium.palarpg.module.dungeon.common.network.ui.sync.BBPacketRPGDungeonSynchronizedUIMouseDrag;
import fr.paladium.palarpg.module.dungeon.common.network.ui.sync.BBPacketRPGDungeonSynchronizedUIMousePress;
import fr.paladium.palarpg.module.dungeon.common.network.ui.sync.BBPacketRPGDungeonSynchronizedUIMouseRelease;
import fr.paladium.palarpg.module.dungeon.common.network.ui.sync.BBPacketRPGDungeonSynchronizedUIMouseScroll;
import fr.paladium.zephyrui.lib.event.impl.interact.keyboard.UIKeyPressedEvent;
import fr.paladium.zephyrui.lib.event.impl.interact.mouse.UIMouseDraggedEvent;
import fr.paladium.zephyrui.lib.event.impl.interact.mouse.UIMousePressedEvent;
import fr.paladium.zephyrui.lib.event.impl.interact.mouse.UIMouseReleasedEvent;
import fr.paladium.zephyrui.lib.event.impl.interact.mouse.UIMouseScrolledEvent;
import fr.paladium.zephyrui.lib.event.impl.open.UICloseEvent;
import fr.paladium.zephyrui.lib.ui.core.UI;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class DungeonClientSynchronizedUIListener {
  @SubscribeEvent
  public void onClose(UICloseEvent event) {
    UI ui = event.getUi();
    if (ui instanceof IDungeonSynchronizedUI) {
      IDungeonSynchronizedUI dungeonUI = (IDungeonSynchronizedUI)ui;
      if (!dungeonUI.isMain()) {
        if (event.isCancelable() && !dungeonUI.canRemoteClose())
          event.setCanceled(true); 
        return;
      } 
      String uuid = UUIDUtils.toString((Entity)(Minecraft.func_71410_x()).field_71439_g);
      (new BBPacketRPGDungeonSynchronizedUIClose(uuid, ui.getClass().getName(), !event.isCancelable())).send();
    } 
  }
  
  @SubscribeEvent
  public void onMousePressed(UIMousePressedEvent.Pre event) {
    UI ui = event.getUi();
    if (ui instanceof IDungeonSynchronizedUI) {
      IDungeonSynchronizedUI dungeonUI = (IDungeonSynchronizedUI)ui;
      if (!dungeonUI.isMain()) {
        if (!dungeonUI.canRemoteMousePress(event.getMouseX(), event.getMouseY(), event.getClickType()))
          event.setCanceled(true); 
        return;
      } 
      String uuid = UUIDUtils.toString((Entity)(Minecraft.func_71410_x()).field_71439_g);
      (new BBPacketRPGDungeonSynchronizedUIMousePress(uuid, ui.getClass().getName(), event.getMouseX(), event.getMouseY(), event.getClickType())).send();
    } 
  }
  
  @SubscribeEvent
  public void onMouseDragged(UIMouseDraggedEvent.Pre event) {
    UI ui = event.getUi();
    if (ui instanceof IDungeonSynchronizedUI) {
      IDungeonSynchronizedUI dungeonUI = (IDungeonSynchronizedUI)ui;
      if (!dungeonUI.isMain()) {
        if (!dungeonUI.canRemoteMouseDrag(event.getMouseX(), event.getMouseY(), event.getClickType(), event.getDeltaTime()))
          event.setCanceled(true); 
        return;
      } 
      String uuid = UUIDUtils.toString((Entity)(Minecraft.func_71410_x()).field_71439_g);
      (new BBPacketRPGDungeonSynchronizedUIMouseDrag(uuid, ui.getClass().getName(), event.getMouseX(), event.getMouseY(), event.getClickType(), event.getDeltaTime())).send();
    } 
  }
  
  @SubscribeEvent
  public void onMouseReleased(UIMouseReleasedEvent.Pre event) {
    UI ui = event.getUi();
    if (ui instanceof IDungeonSynchronizedUI) {
      IDungeonSynchronizedUI dungeonUI = (IDungeonSynchronizedUI)ui;
      if (!dungeonUI.isMain()) {
        if (!dungeonUI.canRemoteMouseRelease(event.getMouseX(), event.getMouseY(), event.getClickType()))
          event.setCanceled(true); 
        return;
      } 
      String uuid = UUIDUtils.toString((Entity)(Minecraft.func_71410_x()).field_71439_g);
      (new BBPacketRPGDungeonSynchronizedUIMouseRelease(uuid, ui.getClass().getName(), event.getMouseX(), event.getMouseY(), event.getClickType())).send();
    } 
  }
  
  @SubscribeEvent
  public void onMouseScrolled(UIMouseScrolledEvent.Pre event) {
    UI ui = event.getUi();
    if (ui instanceof IDungeonSynchronizedUI) {
      IDungeonSynchronizedUI dungeonUI = (IDungeonSynchronizedUI)ui;
      if (!dungeonUI.isMain()) {
        if (!dungeonUI.canRemoteMouseScroll(event.getMouseX(), event.getMouseY(), event.getValue()))
          event.setCanceled(true); 
        return;
      } 
      String uuid = UUIDUtils.toString((Entity)(Minecraft.func_71410_x()).field_71439_g);
      (new BBPacketRPGDungeonSynchronizedUIMouseScroll(uuid, ui.getClass().getName(), event.getMouseX(), event.getMouseY(), event.getValue())).send();
    } 
  }
  
  @SubscribeEvent
  public void onMouseScrolled(UIKeyPressedEvent.Pre event) {
    UI ui = event.getUi();
    if (ui instanceof IDungeonSynchronizedUI) {
      IDungeonSynchronizedUI dungeonUI = (IDungeonSynchronizedUI)ui;
      if (!dungeonUI.isMain()) {
        if (!dungeonUI.canRemoteKeyPress(event.getKeyChar(), event.getKeyCode()))
          event.setCanceled(true); 
        return;
      } 
      String uuid = UUIDUtils.toString((Entity)(Minecraft.func_71410_x()).field_71439_g);
      (new BBPacketRPGDungeonSynchronizedUIKeyPress(uuid, ui.getClass().getName(), event.getKeyChar(), event.getKeyCode())).send();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\client\listener\DungeonClientSynchronizedUIListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */