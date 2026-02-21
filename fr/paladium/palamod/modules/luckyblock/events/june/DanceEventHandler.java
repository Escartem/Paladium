package fr.paladium.palamod.modules.luckyblock.events.june;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.palamod.modules.luckyblock.entity.june.dancer.EntityDancer;
import fr.paladium.palamod.modules.luckyblock.events.june.objetcs.DancerUser;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;

public class DanceEventHandler {
  public static final Map<UUID, DancerUser> DANCER_MAP = new HashMap<>();
  
  public static void putDancer(EntityPlayer player, EntityDancer dancer) {
    Optional<DancerUser> result = getDancer(player);
    if (result.isPresent()) {
      DancerUser oldDancer = result.get();
      EntityDancer entityDancer = oldDancer.getEntity();
      if (entityDancer != null && !entityDancer.field_70128_L) {
        entityDancer.stopTask();
        entityDancer.func_70106_y();
      } 
    } 
    DANCER_MAP.put(player.func_110124_au(), new DancerUser(dancer, player));
  }
  
  public static void removeDancer(UUID uniqueId) {
    if (uniqueId == null)
      return; 
    DANCER_MAP.remove(uniqueId);
  }
  
  public static void removeDancer(EntityPlayer player) {
    removeDancer(player.func_110124_au());
  }
  
  public static Optional<DancerUser> getDancer(EntityPlayer player) {
    if (DANCER_MAP.containsKey(player.func_110124_au()))
      return Optional.of(DANCER_MAP.get(player.func_110124_au())); 
    return Optional.empty();
  }
  
  @SubscribeEvent
  public void onPlayerMoveEvent(TickEvent.PlayerTickEvent event) {
    EntityPlayer player = event.player;
    if (player.field_70170_p.field_72995_K)
      return; 
    if (!DANCER_MAP.containsKey(player.func_110124_au()))
      return; 
    Optional<DancerUser> result = getDancer(player);
    if (!result.isPresent())
      return; 
    DancerUser user = result.get();
    EntityDancer entity = user.getEntity();
    if (entity == null || entity.field_70128_L) {
      DANCER_MAP.remove(player.func_110124_au());
      return;
    } 
    if (!user.hasMoved((int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v))
      return; 
    user.dance(player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\events\june\DanceEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */