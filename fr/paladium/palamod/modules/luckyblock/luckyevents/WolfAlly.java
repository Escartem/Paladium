package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayerMP;

public class WolfAlly extends ALuckyEvent {
  private final int wolfAmount = 20;
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    for (int i = 0; i < 20; i++) {
      EntityWolf entity = new EntityWolf(player.field_70170_p);
      entity.func_70606_j(entity.func_110138_aP());
      entity.func_70634_a(x, y, z);
      player.field_70170_p.func_72838_d((Entity)entity);
    } 
  }
  
  public String getName() {
    return "L'ami des loups";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 300;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "l_ami_des_loups";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\WolfAlly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */