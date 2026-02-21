package fr.paladium.palamod.modules.luckyblock.events;

import net.minecraft.init.Blocks;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

class null implements Runnable {
  public void run() {
    try {
      Thread.sleep(20000L);
      e.world.func_147449_b(e.x, e.y, e.z, Blocks.field_150350_a);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\events\EventsManager$6.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */