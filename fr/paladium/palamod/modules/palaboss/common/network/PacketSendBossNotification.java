package fr.paladium.palamod.modules.palaboss.common.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.palaboss.common.utils.BossLocation;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class PacketSendBossNotification implements IMessage {
  private String serverName;
  
  private BossLocation location;
  
  public PacketSendBossNotification() {}
  
  public PacketSendBossNotification(String serverName, BossLocation location) {
    this.serverName = serverName;
    this.location = location;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.serverName = ByteBufUtils.readUTF8String(buf);
    this.location = new BossLocation(buf.readInt(), buf.readInt(), buf.readInt());
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, this.serverName);
    buf.writeInt(this.location.getX());
    buf.writeInt(this.location.getY());
    buf.writeInt(this.location.getZ());
  }
  
  public static class Handler implements IMessageHandler<PacketSendBossNotification, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(PacketSendBossNotification message, MessageContext ctx) {
      (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(" "));
      (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §bUn boss va apparaitre dans 5 minutes sur §e" + message.serverName + " §ben §e" + message.location.getX() + ", " + message.location.getY() + ", " + message.location.getZ()));
      (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(" "));
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\network\PacketSendBossNotification.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */