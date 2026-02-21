package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.events;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.palamod.api.ItemsRegister;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.MouseEvent;
import org.lwjgl.input.Mouse;

public class TelescopeEventHandler {
  private static final double DEFAULT_CAMERA_ZOOM = 1.0D;
  
  private static final double TELESCOPE_CAMERA_ZOOM = 2.0D;
  
  private Item lastItemInHand = null;
  
  private int scrollValue = 0;
  
  private boolean usingTelescope = false;
  
  @SubscribeEvent
  public void onTick(TickEvent.ClientTickEvent event) {
    Minecraft minecraft = Minecraft.func_71410_x();
    if (event.phase != TickEvent.Phase.END || minecraft.field_71439_g == null)
      return; 
    boolean rightClick = Mouse.isButtonDown(1);
    this.usingTelescope = false;
    Item currentItem = (minecraft.field_71439_g.func_70694_bm() != null) ? minecraft.field_71439_g.func_70694_bm().func_77973_b() : null;
    if (currentItem != null && currentItem.equals(ItemsRegister.TELESCOPE) && rightClick) {
      setCameraZoom(minecraft, (2.0D + this.scrollValue) * 2.0D);
      this.usingTelescope = true;
    } 
    if (this.lastItemInHand != null && this.lastItemInHand.equals(ItemsRegister.TELESCOPE) && (
      currentItem == null || !currentItem.equals(ItemsRegister.TELESCOPE) || !this.usingTelescope)) {
      setCameraZoom(minecraft, 1.0D);
      this.scrollValue = 0;
      this.usingTelescope = false;
    } 
    this.lastItemInHand = currentItem;
  }
  
  @SubscribeEvent
  public void onMouseScroll(MouseEvent event) {
    Minecraft minecraft = Minecraft.func_71410_x();
    Item currentItem = (minecraft.field_71439_g.func_70694_bm() != null) ? minecraft.field_71439_g.func_70694_bm().func_77973_b() : null;
    if (currentItem != null && currentItem.equals(ItemsRegister.TELESCOPE)) {
      if (event.dwheel == 0 || !this.usingTelescope)
        return; 
      event.setCanceled(true);
      if (event.dwheel > 0) {
        this.scrollValue++;
      } else {
        this.scrollValue--;
      } 
      if (this.scrollValue > 2) {
        this.scrollValue = 2;
      } else if (this.scrollValue < 0) {
        this.scrollValue = 0;
      } 
    } 
  }
  
  public double getCameraZoom(Minecraft mc) {
    try {
      return ((Double)ObfuscationReflectionHelper.getPrivateValue(EntityRenderer.class, mc.field_71460_t, new String[] { "cameraZoom", "field_78503_V" })).doubleValue();
    } catch (Exception e) {
      e.printStackTrace();
      return 1.0D;
    } 
  }
  
  public void setCameraZoom(Minecraft mc, double zoom) {
    try {
      ObfuscationReflectionHelper.setPrivateValue(EntityRenderer.class, mc.field_71460_t, 
          Double.valueOf(zoom), new String[] { "cameraZoom", "field_78503_V" });
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\client\events\TelescopeEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */