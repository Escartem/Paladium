package fr.paladium.palamod.modules.luckyblock.network.june;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.modules.luckyblock.tileentity.june.TileEntityAlarmClock;
import fr.paladium.palamod.util.FastUUID;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;

public class PacketAlarmClock implements IMessage {
  private static final int MAX_CLOCK_DISTANCE = 10;
  
  private int x;
  
  private int y;
  
  private int z;
  
  private int h1;
  
  private int h0;
  
  private int m1;
  
  private int m0;
  
  public PacketAlarmClock(int x, int y, int z, int h1, int h0, int m1, int m0) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.h1 = h1;
    this.h0 = h0;
    this.m1 = m1;
    this.m0 = m0;
  }
  
  public PacketAlarmClock() {}
  
  public void fromBytes(ByteBuf buf) {
    this.x = buf.readInt();
    this.y = buf.readInt();
    this.z = buf.readInt();
    this.h1 = buf.readInt();
    this.h0 = buf.readInt();
    this.m1 = buf.readInt();
    this.m0 = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.x);
    buf.writeInt(this.y);
    buf.writeInt(this.z);
    buf.writeInt(this.h1);
    buf.writeInt(this.h0);
    buf.writeInt(this.m1);
    buf.writeInt(this.m0);
  }
  
  public static class Handler implements IMessageHandler<PacketAlarmClock, IMessage> {
    public IMessage onMessage(PacketAlarmClock message, MessageContext ctx) {
      EntityPlayerMP entityPlayerMP = (ctx.getServerHandler()).field_147369_b;
      if (!((EntityPlayer)entityPlayerMP).field_70170_p.func_72899_e(message.x, message.y, message.z))
        return null; 
      TileEntity tileEntity = ((EntityPlayer)entityPlayerMP).field_70170_p.func_147438_o(message.x, message.y, message.z);
      if (!(tileEntity instanceof TileEntityAlarmClock))
        return null; 
      TileEntityAlarmClock tileEntityAlarmClock = (TileEntityAlarmClock)tileEntity;
      Location tileLocation = new Location(tileEntityAlarmClock.func_145831_w(), tileEntityAlarmClock.field_145851_c, tileEntityAlarmClock.field_145848_d, tileEntityAlarmClock.field_145849_e);
      if (tileLocation.getDistance(new Location((Entity)entityPlayerMP)) > 10.0D)
        return null; 
      if (tileEntityAlarmClock != null && 
        tileEntityAlarmClock.getOwner().equals(FastUUID.toString((Entity)entityPlayerMP))) {
        tileEntityAlarmClock.setHourTens(message.h1);
        tileEntityAlarmClock.setHourOnes(message.h0);
        tileEntityAlarmClock.setMinuteTens(message.m1);
        tileEntityAlarmClock.setMinuteOnes(message.m0);
        tileEntityAlarmClock.func_70296_d();
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\june\PacketAlarmClock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */