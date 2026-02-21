package fr.paladium.palashop.provider.cosmetic.factory.impl.spray.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palashop.provider.cosmetic.common.event.CosmeticExecuteWheelEvent;
import fr.paladium.palashop.provider.cosmetic.common.network.CSPacketExecuteWheelCosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.spray.SprayCosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.factory.impl.spray.common.entity.EntitySprayCosmetic;
import fr.paladium.palashop.provider.cosmetic.factory.impl.spray.common.network.SCPacketSprayCosmeticExecute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.ForgeDirection;

public class SprayCosmeticExecuteServerListener {
  @SubscribeEvent
  public void onSprayExecute(CosmeticExecuteWheelEvent.Post event) {
    if (event.getCosmetic().getFactory() != SprayCosmeticFactory.getInstance())
      return; 
    CSPacketExecuteWheelCosmetic.HitResult target = event.getTarget();
    Entity entity = event.getEntity();
    World world = entity.field_70170_p;
    if (target.getType() != MovingObjectPosition.MovingObjectType.BLOCK)
      return; 
    int blockX = target.getBlockX();
    int blockY = target.getBlockY();
    int blockZ = target.getBlockZ();
    int side = target.getSideHit();
    ForgeDirection direction = ForgeDirection.getOrientation(side);
    EntitySprayCosmetic spray = new EntitySprayCosmetic(world, blockX, blockY, blockZ, event.getCosmetic().getId(), direction);
    if (direction == ForgeDirection.UP || direction == ForgeDirection.DOWN)
      spray.field_70177_z = entity.field_70177_z; 
    world.func_72838_d((Entity)spray);
    if (world instanceof WorldServer) {
      EntityTracker tracker = ((WorldServer)world).func_73039_n();
      for (EntityPlayer entityPlayer : tracker.getTrackingPlayers(event.getEntity())) {
        if (!(entityPlayer instanceof EntityPlayerMP))
          continue; 
        (new SCPacketSprayCosmeticExecute(blockX, blockY, blockZ)).send((EntityPlayerMP)entityPlayer);
      } 
      if (event.getEntity() instanceof EntityPlayerMP)
        (new SCPacketSprayCosmeticExecute(blockX, blockY, blockZ)).send((EntityPlayerMP)event.getEntity()); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\spray\server\listener\SprayCosmeticExecuteServerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */