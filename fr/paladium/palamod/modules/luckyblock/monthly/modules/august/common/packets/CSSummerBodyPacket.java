package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.blocks.BlockRunningTreadmill;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class CSSummerBodyPacket implements IMessage {
  private int x;
  
  private int y;
  
  private int z;
  
  private boolean success;
  
  public CSSummerBodyPacket() {
    this(0, 0, 0, false);
  }
  
  public CSSummerBodyPacket(DoubleLocation location, boolean success) {
    if (location == null)
      throw new IllegalArgumentException("Location cannot be null"); 
    this.x = location.getBlockX();
    this.y = location.getBlockY();
    this.z = location.getBlockZ();
    this.success = success;
  }
  
  public CSSummerBodyPacket(int x, int y, int z, boolean success) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.success = success;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.x = buf.readInt();
    this.y = buf.readInt();
    this.z = buf.readInt();
    this.success = buf.readBoolean();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.x);
    buf.writeInt(this.y);
    buf.writeInt(this.z);
    buf.writeBoolean(this.success);
  }
  
  public static class Handler implements IMessageHandler<CSSummerBodyPacket, IMessage> {
    public IMessage onMessage(CSSummerBodyPacket message, MessageContext ctx) {
      if (ctx.side == Side.CLIENT)
        return null; 
      EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
      World world = player.field_70170_p;
      if (!world.func_72899_e(message.x, message.y, message.z))
        return null; 
      Block block = world.func_147439_a(message.x, message.y, message.z);
      if (!(block instanceof BlockRunningTreadmill))
        return null; 
      BlockRunningTreadmill treadmill = (BlockRunningTreadmill)block;
      DoubleLocation playerLocation = new DoubleLocation(player.field_70165_t, player.field_70163_u, player.field_70161_v);
      DoubleLocation location = new DoubleLocation(message.x, message.y, message.z);
      if (playerLocation.distance(location) > 5.0D)
        return null; 
      treadmill.handlePacket(player, location.getBlockX(), location.getBlockY(), location.getBlockZ(), message.success);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\packets\CSSummerBodyPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */