package fr.paladium.palamod.modules.luckyblock.network.june;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.modules.luckyblock.tileentity.june.TileEntityAlarmClock;
import fr.paladium.palamod.util.FastUUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;

public class Handler implements IMessageHandler<PacketAlarmClock, IMessage> {
  public IMessage onMessage(PacketAlarmClock message, MessageContext ctx) {
    EntityPlayerMP entityPlayerMP = (ctx.getServerHandler()).field_147369_b;
    if (!((EntityPlayer)entityPlayerMP).field_70170_p.func_72899_e(PacketAlarmClock.access$000(message), PacketAlarmClock.access$100(message), PacketAlarmClock.access$200(message)))
      return null; 
    TileEntity tileEntity = ((EntityPlayer)entityPlayerMP).field_70170_p.func_147438_o(PacketAlarmClock.access$000(message), PacketAlarmClock.access$100(message), PacketAlarmClock.access$200(message));
    if (!(tileEntity instanceof TileEntityAlarmClock))
      return null; 
    TileEntityAlarmClock tileEntityAlarmClock = (TileEntityAlarmClock)tileEntity;
    Location tileLocation = new Location(tileEntityAlarmClock.func_145831_w(), tileEntityAlarmClock.field_145851_c, tileEntityAlarmClock.field_145848_d, tileEntityAlarmClock.field_145849_e);
    if (tileLocation.getDistance(new Location((Entity)entityPlayerMP)) > 10.0D)
      return null; 
    if (tileEntityAlarmClock != null && 
      tileEntityAlarmClock.getOwner().equals(FastUUID.toString((Entity)entityPlayerMP))) {
      tileEntityAlarmClock.setHourTens(PacketAlarmClock.access$300(message));
      tileEntityAlarmClock.setHourOnes(PacketAlarmClock.access$400(message));
      tileEntityAlarmClock.setMinuteTens(PacketAlarmClock.access$500(message));
      tileEntityAlarmClock.setMinuteOnes(PacketAlarmClock.access$600(message));
      tileEntityAlarmClock.func_70296_d();
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\june\PacketAlarmClock$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */