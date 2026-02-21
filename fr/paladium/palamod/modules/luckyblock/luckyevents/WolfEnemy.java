package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayerMP;

public class WolfEnemy extends ALuckyEvent {
  private final int wolfAmount = 20;
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    for (int i = 0; i < 20; i++) {
      EntityWolf entity = new EntityWolf(player.field_70170_p);
      entity.func_70916_h(true);
      entity.func_70634_a(x, y, z);
      entity.func_70784_b((Entity)player);
      player.field_70170_p.func_72838_d((Entity)entity);
    } 
  }
  
  public String getName() {
    return "L'ennemi des loups";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 300;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "l_ennemi_des_loups";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\WolfEnemy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */