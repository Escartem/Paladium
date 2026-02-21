package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.blocks.BlockEncryptedComputer;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class CSEncryptedComputerPacket implements IMessage {
  private int x;
  
  private int y;
  
  private int z;
  
  private boolean hacked;
  
  public CSEncryptedComputerPacket() {
    this(0, 0, 0, false);
  }
  
  public CSEncryptedComputerPacket(DoubleLocation location, boolean hacked) {
    if (location == null)
      throw new IllegalArgumentException("Location cannot be null"); 
    this.x = location.getBlockX();
    this.y = location.getBlockY();
    this.z = location.getBlockZ();
    this.hacked = hacked;
  }
  
  public CSEncryptedComputerPacket(int x, int y, int z, boolean hacked) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.hacked = hacked;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.x = buf.readInt();
    this.y = buf.readInt();
    this.z = buf.readInt();
    this.hacked = buf.readBoolean();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.x);
    buf.writeInt(this.y);
    buf.writeInt(this.z);
    buf.writeBoolean(this.hacked);
  }
  
  public static class Handler implements IMessageHandler<CSEncryptedComputerPacket, IMessage> {
    public IMessage onMessage(CSEncryptedComputerPacket message, MessageContext ctx) {
      if (ctx.side == Side.CLIENT)
        return null; 
      EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
      World world = player.field_70170_p;
      if (!world.func_72899_e(message.x, message.y, message.z))
        return null; 
      Block block = world.func_147439_a(message.x, message.y, message.z);
      if (!(block instanceof BlockEncryptedComputer))
        return null; 
      BlockEncryptedComputer computer = (BlockEncryptedComputer)block;
      DoubleLocation playerLocation = new DoubleLocation(player.field_70165_t, player.field_70163_u, player.field_70161_v);
      DoubleLocation location = new DoubleLocation(message.x, message.y, message.z);
      if (playerLocation.distance(location) > 5.0D)
        return null; 
      computer.onHacked(world, message.x, message.y, message.z, (EntityPlayer)player, message.hacked);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\packets\CSEncryptedComputerPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */