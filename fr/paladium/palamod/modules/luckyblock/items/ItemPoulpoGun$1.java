package fr.paladium.palamod.modules.luckyblock.items;

import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.projectile.EntitySnowball;

class null implements Runnable {
  public void run() {
    if (snow.field_70128_L) {
      PaladiumScheduler.INSTANCE.cancelTask(task.id);
      return;
    } 
    squid.func_70107_b(snow.field_70165_t, snow.field_70163_u - 5.0D, snow.field_70161_v);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemPoulpoGun$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */