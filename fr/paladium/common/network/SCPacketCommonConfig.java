package fr.paladium.common.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.ServerType;
import fr.paladium.common.CommonModule;
import io.netty.buffer.ByteBuf;

public class SCPacketCommonConfig implements IMessage {
  private ServerType serverType = CommonModule.getInstance().getConfig().getServerType(Side.SERVER);
  
  private String serverName = CommonModule.getInstance().getConfig().getServerName(Side.SERVER);
  
  public void fromBytes(ByteBuf buf) {
    this.serverType = ServerType.values()[buf.readInt()];
    this.serverName = ByteBufUtils.readUTF8String(buf);
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.serverType.ordinal());
    ByteBufUtils.writeUTF8String(buf, this.serverName);
  }
  
  public static class Handler implements IMessageHandler<SCPacketCommonConfig, IMessage> {
    public IMessage onMessage(SCPacketCommonConfig message, MessageContext ctx) {
      CommonModule.getInstance().getConfig().setServerType(message.serverType);
      CommonModule.getInstance().getConfig().setServerName(message.serverName);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\common\network\SCPacketCommonConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */