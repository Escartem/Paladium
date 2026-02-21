package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;

class null implements Runnable {
  long times = 0L;
  
  public void run() {
    this.times++;
    if (this.times > 10L) {
      PaladiumScheduler.INSTANCE.cancelTask(task.id);
      return;
    } 
    int px = (int)player.field_70165_t;
    int py = (int)player.field_70163_u;
    int pz = (int)player.field_70161_v;
    for (int cx = px - 5; cx < px + 5; cx++) {
      for (int cz = pz - 5; cz < pz + 5; cz++) {
        if (player.field_70170_p.field_73012_v.nextDouble() > 0.7D && 
          EventUtils.canInteract((EntityPlayer)player, cx, py + 10, cz))
          player.field_70170_p.func_147449_b(cx, py + 10, cz, Blocks.field_150467_bQ); 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\AnvilHead$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */