package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.miner.entity.EntityGodVillager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class VillaNul extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EntityGodVillager villager = new EntityGodVillager(player.field_70170_p);
    villager.func_70107_b(x, y, z);
    villager.setType(3);
    player.field_70170_p.func_72838_d((Entity)villager);
  }
  
  public String getName() {
    return "Villa-nul";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 100;
  }
  
  public String getTexture() {
    return "villanul";
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\VillaNul.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */