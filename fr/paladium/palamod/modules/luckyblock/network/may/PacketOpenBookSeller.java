package fr.paladium.palamod.modules.luckyblock.network.may;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.gui.may.UIBookseller;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class PacketOpenBookSeller implements IMessage {
  public void toBytes(ByteBuf buf) {}
  
  public void fromBytes(ByteBuf buf) {}
  
  public static class Handler implements IMessageHandler<PacketOpenBookSeller, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(PacketOpenBookSeller message, MessageContext ctx) {
      Minecraft.func_71410_x().func_147108_a((GuiScreen)new UIBookseller());
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\may\PacketOpenBookSeller.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */