package fr.paladium.palamod.modules.luckyblock.luckyevents.christmas;

import fr.paladium.palamod.modules.luckyblock.entity.christmas.EntityChristmasSleigh;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;

public class Sleigh extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    for (int ox = -1; ox < 2; ox++) {
      for (int oz = -1; oz < 2; oz++) {
        if (EventUtils.canInteract((EntityPlayer)player, x + ox, y, z + oz) && (ox != 0 || oz != 0))
          player.field_70170_p.func_147449_b(x + ox, y, z + oz, Blocks.field_150448_aq); 
      } 
    } 
    EntityChristmasSleigh entityChristmasSleigh = new EntityChristmasSleigh(player.field_70170_p, (x + 1), (y + 1), z);
    player.field_70170_p.func_72838_d((Entity)entityChristmasSleigh);
  }
  
  public String getName() {
    return "Très-no";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 70;
  }
  
  public String getTexture() {
    return "sleigh";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\christmas\Sleigh.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */