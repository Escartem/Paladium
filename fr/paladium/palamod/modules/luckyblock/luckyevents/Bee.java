package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.entity.EntityBee;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class Bee extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    for (int i = 0; i <= 15; i++) {
      EntityBee entity = new EntityBee(player.field_70170_p);
      entity.setTarget((Entity)player);
      entity.func_70634_a(x, y, z);
      player.field_70170_p.func_72838_d((Entity)entity);
    } 
  }
  
  public String getName() {
    return "A-Bee-mé";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 100;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "abeeme";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Bee.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */