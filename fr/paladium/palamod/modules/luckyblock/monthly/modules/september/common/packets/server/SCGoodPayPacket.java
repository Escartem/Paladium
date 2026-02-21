package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.packets.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.uis.GoodPayUI;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class SCGoodPayPacket implements IMessage {
  private int id;
  
  public SCGoodPayPacket() {
    this.id = 0;
  }
  
  public SCGoodPayPacket(int id) {
    this.id = id;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.id = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.id);
  }
  
  public static class Handler implements IMessageHandler<SCGoodPayPacket, IMessage> {
    @SideOnly(Side.CLIENT)
    public void openUI(int id) {
      if ((Minecraft.func_71410_x()).field_71462_r != null)
        return; 
      Minecraft.func_71410_x().func_147108_a((GuiScreen)new GoodPayUI(id));
    }
    
    public IMessage onMessage(SCGoodPayPacket message, MessageContext ctx) {
      if (ctx.side != Side.CLIENT)
        return null; 
      openUI(message.id);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\packets\server\SCGoodPayPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */