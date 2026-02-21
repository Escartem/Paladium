package fr.paladium.palajobs.core.packets.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palajobs.client.ui.home.UIJobsHome;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class Handler implements IMessageHandler<SCOpenUiJobsHome, IMessage> {
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(SCOpenUiJobsHome message, MessageContext ctx) {
    Minecraft.func_71410_x().func_147108_a((GuiScreen)new UIJobsHome());
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\packets\server\SCOpenUiJobsHome$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */