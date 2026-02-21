package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.tasks.majordome;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palajobs.utils.forge.location.Location;
import fr.paladium.palamod.modules.luckyblock.entity.may.EntityNPC;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents.SandCastleLifeEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.objects.MajordomeData;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.SchematicUtils;
import fr.paladium.palaschematic.utils.Schematic;
import java.util.TimerTask;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MajordomeRunnable extends TimerTask {
  private UUID oldEntityUniqueId;
  
  private ItemStack reward;
  
  private Location location;
  
  public MajordomeRunnable(UUID oldEntityUniqueId, ItemStack reward, Location location) {
    this.oldEntityUniqueId = oldEntityUniqueId;
    this.reward = reward;
    this.location = location;
  }
  
  public void run() {
    MajordomeData data = SandCastleLifeEvent.INSTANCE.get(this.oldEntityUniqueId);
    if (data == null)
      return; 
    World world = this.location.getWorld();
    EntityNPC majordome = SandCastleLifeEvent.INSTANCE.getMajordome("Majordome_FAKE", this.location.getWorld(), this.location);
    world.func_72838_d((Entity)majordome);
    data.setEntityUniqueId(majordome.func_110124_au());
    ItemUtils.spawnItemAtEntity((Entity)majordome, this.reward);
    try {
      Thread.sleep(5000L);
      data = SandCastleLifeEvent.INSTANCE.get(majordome.func_110124_au());
      if (data == null)
        return; 
      Schematic schematic = data.getSchematic();
      EntityLivingBase entity = MonthlyUtils.getLivingEntityByUniqueId(world, majordome.func_110124_au());
      SchematicUtils.clean(schematic, world, this.location.getBlockX(), this.location.getBlockY(), this.location.getBlockZ());
      SandCastleLifeEvent.INSTANCE.remove(data);
      if (entity == null || entity.field_70128_L)
        return; 
      entity.func_70106_y();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\tasks\majordome\MajordomeRunnable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */