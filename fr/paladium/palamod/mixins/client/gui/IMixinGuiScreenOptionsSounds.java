package fr.paladium.palamod.mixins.client.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palamod.modules.packetreducer.PPacketReducer;
import fr.paladium.palamod.modules.packetreducer.common.packets.PReducerSettingsPacket;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenOptionsSounds;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({GuiScreenOptionsSounds.class})
public abstract class IMixinGuiScreenOptionsSounds extends GuiScreen {
  public void func_146281_b() {
    super.func_146281_b();
    if (!ForgeEnv.isDev())
      PPacketReducer.networkWrapper.sendToServer((IMessage)new PReducerSettingsPacket()); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\client\gui\IMixinGuiScreenOptionsSounds.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */