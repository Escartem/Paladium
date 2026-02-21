package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.entity.EntityInvisible;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class OrangeOrBlue extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EntityInvisible entity = new EntityInvisible(player.field_70170_p);
    entity.func_70634_a(x, y, z);
    player.field_70170_p.func_72838_d((Entity)entity);
  }
  
  public String getName() {
    return "Orange ou bleu ?";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 700;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "orange_ou_bleu";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\OrangeOrBlue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */