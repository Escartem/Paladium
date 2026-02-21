package fr.paladium.palashop.provider.cosmetic.client.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;
import fr.paladium.palashop.provider.cosmetic.CosmeticProvider;
import fr.paladium.palashop.provider.cosmetic.client.ui.selector.UICosmeticSelectorOverlay;
import fr.paladium.zephyrui.internal.mod.bridge.overlay.client.OverlayBridge;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;
import java.lang.reflect.Method;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Mouse;

public class CosmeticKeybindListener {
  private static Method unpressKeyMethod;
  
  private long openTime;
  
  private boolean keyPressed;
  
  private UICosmeticSelectorOverlay overlay;
  
  @SubscribeEvent
  public void onSelectKeyPressed(TickEvent.ClientTickEvent event) {
    if ((Minecraft.func_71410_x()).field_71462_r != null || (Minecraft.func_71410_x()).field_71439_g == null || (this.overlay != null && !ZUI.isOpen(UICosmeticSelectorOverlay.class))) {
      close();
      return;
    } 
    if (CosmeticProvider.getClient().getSelectKey().func_151470_d()) {
      open();
      this.keyPressed = true;
    } else {
      close();
    } 
    if (this.overlay != null)
      while (Mouse.next()) {
        if (OverlayBridge.get().handleMouseInput() || (Minecraft.func_71410_x()).field_71462_r == null)
          continue; 
        (Minecraft.func_71410_x()).field_71462_r.func_146274_d();
      }  
  }
  
  private void close() {
    if (this.keyPressed) {
      if (this.overlay != null) {
        if (unpressKeyMethod == null)
          try {
            unpressKeyMethod = ReflectionHelper.findMethod(KeyBinding.class, CosmeticProvider.getClient().getSelectKey(), new String[] { "unpressKey", "func_74505_d" }, new Class[0]);
            unpressKeyMethod.setAccessible(true);
          } catch (Exception exception) {} 
        try {
          unpressKeyMethod.invoke(CosmeticProvider.getClient().getSelectKey(), new Object[0]);
        } catch (Exception exception) {}
      } else {
        UICosmeticSelectorOverlay.applyLast();
      } 
      this.openTime = 0L;
      this.keyPressed = false;
    } 
    if (this.overlay == null)
      return; 
    if ((Minecraft.func_71410_x()).field_71462_r == null) {
      (Minecraft.func_71410_x()).field_71415_G = true;
      (Minecraft.func_71410_x()).field_71417_B.func_74372_a();
    } 
    if (!ZUI.isOpen(UICosmeticSelectorOverlay.class)) {
      this.overlay = null;
      return;
    } 
    ZUI.close((UI)this.overlay);
    this.overlay = null;
  }
  
  private void open() {
    if (this.overlay != null)
      return; 
    if (this.openTime == 0L)
      this.openTime = System.currentTimeMillis(); 
    if (System.currentTimeMillis() - this.openTime < 200L)
      return; 
    (Minecraft.func_71410_x()).field_71415_G = false;
    (Minecraft.func_71410_x()).field_71417_B.func_74373_b();
    this.overlay = new UICosmeticSelectorOverlay();
    ZUI.open((UI)this.overlay);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\client\listener\CosmeticKeybindListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */