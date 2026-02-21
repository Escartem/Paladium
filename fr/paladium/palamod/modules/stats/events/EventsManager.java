package fr.paladium.palamod.modules.stats.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.factions.core.utils.Vec3d;
import fr.paladium.palamod.modules.stats.eep.StatsEep;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class EventsManager {
  private final Map<UUID, Vec3d> lastPositions = new HashMap<>();
  
  @SideOnly(Side.SERVER)
  @SubscribeEvent
  public void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
    if (event.player.field_70170_p.field_72995_K)
      return; 
    EntityPlayerMP player = (EntityPlayerMP)event.player;
    this.lastPositions.remove(player.func_110124_au());
  }
  
  @SideOnly(Side.SERVER)
  @SubscribeEvent
  public void onPlayerTick(TickEvent.PlayerTickEvent e) {
    if (e.side == Side.CLIENT || e.phase != TickEvent.Phase.END)
      return; 
    if (e.player.field_70170_p.field_72995_K || !(e.player instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)e.player;
    Vec3d lastPosition = this.lastPositions.get(player.func_110124_au());
    if (lastPosition != null) {
      double distance = e.player.func_70011_f(lastPosition.getX(), e.player.field_70163_u, lastPosition.getZ());
      if (distance >= 1.0D) {
        if (distance < 3.0D) {
          StatsEep statsEep = StatsEep.get((Entity)player);
          if (player.func_70115_ae() && player.field_70154_o instanceof net.minecraft.entity.item.EntityBoat) {
            statsEep.increaseBoatDistance((int)distance);
          } else if (player.func_70115_ae() && player.field_70154_o instanceof fr.paladium.mount.core.entities.EntityMount) {
            statsEep.increaseMountDistance((int)distance);
          } else {
            statsEep.increaseWalkDistance((int)distance);
          } 
        } 
        lastPosition.set(e.player.field_70165_t, e.player.field_70163_u, e.player.field_70161_v);
      } 
    } else {
      lastPosition = new Vec3d(e.player.field_70165_t, e.player.field_70163_u, e.player.field_70161_v);
      this.lastPositions.put(player.func_110124_au(), lastPosition);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\stats\events\EventsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */