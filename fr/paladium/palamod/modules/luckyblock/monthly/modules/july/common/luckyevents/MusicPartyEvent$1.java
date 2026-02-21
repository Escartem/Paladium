package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents;

import fr.paladium.palamod.modules.luckyblock.entity.may.EntityNPC;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;
import net.minecraft.item.ItemStack;

class null extends TimerTask {
  public void run() {
    Random random = new Random();
    EntityNPC npc = entities.get(random.nextInt(entities.size()));
    if (npc == null || npc.field_70128_L)
      return; 
    ItemStack stack = npc.func_70694_bm();
    if (stack == null)
      return; 
    npc.func_70099_a(stack.func_77946_l(), 0.0F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\luckyevents\MusicPartyEvent$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */