package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tasks;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.structures.PoolStructure;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PoolRunnable extends TimerTask {
  private PoolStructure structure;
  
  public PoolRunnable(PoolStructure structure) {
    this.structure = structure;
  }
  
  public void run() {
    EntityPlayer player = this.structure.getPlayer();
    if (player == null || this.structure.isExpired()) {
      stop();
      return;
    } 
    List<UUID> deadEntities = getDeadEntities(player);
    this.structure.getItemsList().removeIf(deadEntities::contains);
    if (this.structure.getItemsList().isEmpty()) {
      stop();
      return;
    } 
  }
  
  private List<UUID> getDeadEntities(EntityPlayer player) {
    List<?> loadedEntities = player.field_70170_p.field_72996_f;
    List<UUID> deadEntities = new ArrayList<>();
    for (UUID uuid : this.structure.getItemsList()) {
      if (uuid == null)
        continue; 
      Entity entity = MonthlyUtils.getEntityByUniqueId(loadedEntities, uuid);
      if (entity == null || entity.field_70128_L) {
        deadEntities.add(uuid);
        continue;
      } 
      if (!(entity instanceof net.minecraft.entity.item.EntityItem))
        deadEntities.add(uuid); 
    } 
    return deadEntities;
  }
  
  private void stop() {
    if (this.structure != null)
      this.structure.onExpire(); 
    cancel();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\tasks\PoolRunnable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */