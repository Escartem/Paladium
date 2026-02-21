package fr.paladium.palamod.modules.paladium.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import noppes.npcs.client.gui.player.GuiQuestLog;

public class SCOpenQuestGui implements IMessage {
  public void fromBytes(ByteBuf buf) {}
  
  public void toBytes(ByteBuf buf) {}
  
  public static class Handler implements IMessageHandler<SCOpenQuestGui, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(SCOpenQuestGui message, MessageContext ctx) {
      Minecraft.func_71410_x().func_147108_a((GuiScreen)new GuiQuestLog((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g));
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\network\SCOpenQuestGui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */