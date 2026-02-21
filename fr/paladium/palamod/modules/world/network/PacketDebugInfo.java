package fr.paladium.palamod.modules.world.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.world.PWorld;
import io.netty.buffer.ByteBuf;

public class PacketDebugInfo implements IMessage {
  boolean paladiumgreen_enabled;
  
  boolean endium_enabled;
  
  boolean doublepaladium_enabled;
  
  public PacketDebugInfo() {}
  
  public PacketDebugInfo(boolean paladiumgreen_enabled, boolean endium_enabled, boolean doublepaladium_enabled) {
    this.paladiumgreen_enabled = paladiumgreen_enabled;
    this.endium_enabled = endium_enabled;
    this.doublepaladium_enabled = doublepaladium_enabled;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.paladiumgreen_enabled = buf.readBoolean();
    this.endium_enabled = buf.readBoolean();
    this.doublepaladium_enabled = buf.readBoolean();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeBoolean(this.paladiumgreen_enabled);
    buf.writeBoolean(this.endium_enabled);
    buf.writeBoolean(this.doublepaladium_enabled);
  }
  
  public static class Handler implements IMessageHandler<PacketDebugInfo, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(PacketDebugInfo message, MessageContext ctx) {
      PWorld.initBlocksLayers(message.paladiumgreen_enabled, message.endium_enabled, message.doublepaladium_enabled);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\world\network\PacketDebugInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */