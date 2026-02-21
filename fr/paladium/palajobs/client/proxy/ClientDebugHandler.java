package fr.paladium.palajobs.client.proxy;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.ServerType;
import fr.paladium.common.CommonModule;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palajobs.client.register.KeysRegister;
import fr.paladium.palajobs.client.ui.home.UIJobsHome;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;

public class ClientDebugHandler {
  @SubscribeEvent
  @SideOnly(Side.CLIENT)
  public void onKeyInput(TickEvent.ClientTickEvent e) {
    Minecraft mc = Minecraft.func_71410_x();
    if (e.phase == TickEvent.Phase.END && mc != null) {
      if (KeysRegister.OPEN_JOBS.func_151468_f() && mc.field_71439_g != null && CommonModule.getInstance().getConfig().getServerType() != ServerType.LOBBY && CommonModule.getInstance().getConfig().getServerType() != ServerType.GAME)
        Minecraft.func_71410_x().func_147108_a((GuiScreen)new UIJobsHome()); 
      if (ForgeEnv.isIDE() && Keyboard.isKeyDown(36) && (Minecraft.func_71410_x()).field_71462_r == null && mc.field_71439_g != null)
        Minecraft.func_71410_x().func_147108_a((GuiScreen)new UIJobsHome()); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\client\proxy\ClientDebugHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */