package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.entities.EntityDutchBoat;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class Handler implements IMessageHandler<CSJumpPacket, IMessage> {
  private EntityDutchBoat getBoat(EntityPlayerMP player) {
    if (player == null)
      return null; 
    if (!player.func_70115_ae())
      return null; 
    Entity riding = player.field_70154_o;
    if (!(riding instanceof EntityDutchBoat))
      return null; 
    return (EntityDutchBoat)riding;
  }
  
  public IMessage onMessage(CSJumpPacket message, MessageContext ctx) {
    EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
    EntityDutchBoat boat = getBoat(player);
    if (boat == null)
      return null; 
    boat.onPacketJumpReceived((EntityPlayer)player);
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\packets\CSJumpPacket$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */