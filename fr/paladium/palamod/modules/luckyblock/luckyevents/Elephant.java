package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.hunter.entites.EntityElephant;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class Elephant extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EntityElephant entity = new EntityElephant(player.field_70170_p);
    entity.setTamed(true);
    entity.func_70634_a(x, y, z);
    player.field_70170_p.func_72838_d((Entity)entity);
  }
  
  public String getName() {
    return "Et ! Les phans !";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 200;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "et_les_phan";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Elephant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */