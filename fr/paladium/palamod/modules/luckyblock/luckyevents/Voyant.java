package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.network.luckypass.PlayerLuckyPassProperties;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.miner.entity.EntityGodVillager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class Voyant extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EntityGodVillager villager = new EntityGodVillager(player.field_70170_p);
    villager.func_70107_b(x, y, z);
    villager.setType(2);
    PlayerLuckyPassProperties.get((EntityPlayer)player).setHasLuckyEventBypassNow(true);
    player.field_70170_p.func_72838_d((Entity)villager);
  }
  
  public String getName() {
    return "Boule de Crystal";
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
    return "crystal";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Voyant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */