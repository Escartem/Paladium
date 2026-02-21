package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayerMP;

public class End extends ALuckyEvent {
  private final int endermanAmount = 20;
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    for (int i = 0; i < 20; i++) {
      EntityEnderman entity = new EntityEnderman(player.field_70170_p);
      entity.func_70634_a(x, y, z);
      player.field_70170_p.func_72838_d((Entity)entity);
    } 
  }
  
  public String getName() {
    return "End";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 200;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "end";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\End.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */