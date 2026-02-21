package fr.paladium.palavanillagui.client.listener;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamixins.event.client.RenderRecordOverlayEvent;
import fr.paladium.palamixins.event.client.RenderToolHightlightEvent;
import fr.paladium.palavanillagui.client.ui.UIManager;
import fr.paladium.palavanillagui.client.ui.inventory.UIInventory;
import fr.paladium.palavanillagui.client.ui.inventory.guibutton.GuiInventoryButton;
import fr.paladium.palavanillagui.client.ui.overlay.UIHotbarOverlay;
import fr.paladium.palavanillagui.client.ui.util.IBypassAble;
import fr.paladium.zephyrui.internal.mod.bridge.gui.client.GuiScreenBridge;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.MapSignal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class ClientListener {
  @SideOnly(Side.CLIENT)
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onGuiOpened(GuiOpenEvent event) {
    if (event.gui != null && UIManager.containKey(event.gui.getClass())) {
      UI ui = (GuiScreenBridge.get() == null || GuiScreenBridge.get().getUiList().isEmpty()) ? null : (UI)GuiScreenBridge.get().getUiList().getFirst();
      if ((ui instanceof IBypassAble && ((IBypassAble)ui).isBypass()) || (event.gui instanceof net.minecraft.client.gui.inventory.GuiInventory && ZUI.isOpen(UIInventory.class)))
        return; 
      if (event.gui instanceof net.minecraft.client.gui.inventory.GuiInventory && (Minecraft.func_71410_x()).field_71442_b.func_78758_h())
        return; 
      Container container = (event.gui instanceof GuiContainer) ? ((GuiContainer)event.gui).field_147002_h : null;
      UIManager.openUI(event.gui.getClass(), container);
      event.setCanceled(true);
    } 
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public void onGuiInit(GuiScreenEvent.InitGuiEvent.Post event) {
    if (event.gui instanceof net.minecraft.client.gui.inventory.GuiContainerCreative) {
      ScaledResolution sc = new ScaledResolution(Minecraft.func_71410_x(), (Minecraft.func_71410_x()).field_71443_c, (Minecraft.func_71410_x()).field_71440_d);
      int x = sc.func_78326_a() / 2 + 48 + getPotionOffset();
      event.buttonList.add(new GuiInventoryButton(x, sc.func_78328_b() / 2 + 70, "inv."));
    } 
  }
  
  public int getPotionOffset() {
    if ((Minecraft.func_71410_x()).field_71439_g.func_70651_bq().size() > 0)
      return 60; 
    return 0;
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
  public void onRenderGameOverlay(RenderGameOverlayEvent.Pre event) {
    if (ZUI.isOpen(UIHotbarOverlay.class)) {
      MapSignal<RenderGameOverlayEvent.ElementType, Boolean> signal = ((UIHotbarOverlay)ZUI.getUI(UIHotbarOverlay.class)).getEventSignal();
      if (((Boolean)signal.get(event.type)).booleanValue() != event.isCanceled())
        signal.put(event.type, Boolean.valueOf(event.isCanceled())); 
    } 
    if (RenderGameOverlayEvent.ElementType.HOTBAR.equals(event.type) || RenderGameOverlayEvent.ElementType.EXPERIENCE.equals(event.type) || RenderGameOverlayEvent.ElementType.HEALTH.equals(event.type) || RenderGameOverlayEvent.ElementType.ARMOR.equals(event.type) || RenderGameOverlayEvent.ElementType.FOOD.equals(event.type) || RenderGameOverlayEvent.ElementType.AIR.equals(event.type) || RenderGameOverlayEvent.ElementType.HEALTHMOUNT.equals(event.type) || RenderGameOverlayEvent.ElementType.JUMPBAR.equals(event.type)) {
      if (!event.isCanceled())
        MinecraftForge.EVENT_BUS.post((Event)new RenderGameOverlayEvent.Post((RenderGameOverlayEvent)event, event.type)); 
      event.setCanceled(true);
    } 
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
  public void onRenderToolHightlight(RenderToolHightlightEvent.Pre event) {
    event.setCanceled(true);
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
  public void onRenderRecordOverlay(RenderRecordOverlayEvent.Pre event) {
    UIHotbarOverlay ui = (UIHotbarOverlay)ZUI.getUI(UIHotbarOverlay.class);
    if (ui != null)
      ui.setTextDisplayed(event.getTextDisplayed()); 
    event.setCanceled(true);
  }
  
  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void onJoin(EntityJoinWorldEvent event) {
    if (event.entity instanceof net.minecraft.client.entity.EntityClientPlayerMP && 
      !ZUI.isOpen(UIHotbarOverlay.class))
      ZUI.open((UI)new UIHotbarOverlay()); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\client\listener\ClientListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */