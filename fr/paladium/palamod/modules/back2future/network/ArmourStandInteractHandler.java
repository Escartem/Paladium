package fr.paladium.palamod.modules.back2future.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.back2future.entities.EntityArmourStand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

public class ArmourStandInteractHandler implements IMessageHandler<ArmourStandInteractMessage, IMessage> {
  public IMessage onMessage(ArmourStandInteractMessage message, MessageContext ctx) {
    WorldServer worldServer = DimensionManager.getWorld(message.dimID);
    Entity entity = worldServer.func_73045_a(message.standID);
    if (!(entity instanceof EntityArmourStand))
      return null; 
    EntityArmourStand stand = (EntityArmourStand)entity;
    EntityPlayerMP entityPlayerMP = (ctx.getServerHandler()).field_147369_b;
    stand.interact((EntityPlayer)entityPlayerMP, message.hitPos);
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\network\ArmourStandInteractHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */