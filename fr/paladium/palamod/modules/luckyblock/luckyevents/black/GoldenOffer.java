package fr.paladium.palamod.modules.luckyblock.luckyevents.black;

import fr.paladium.palamod.modules.luckyblock.entity.black.EntityLBVillager;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class GoldenOffer extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    EntityLBVillager villager = new EntityLBVillager(player.field_70170_p, (EntityPlayer)player);
    villager.func_70107_b(x, y, z);
    player.field_70170_p.func_72838_d((Entity)villager);
  }
  
  public String getName() {
    return "Une offre en or";
  }
  
  public int getRarity() {
    return 750;
  }
  
  public String getTexture() {
    return "une_offre_en_or";
  }
  
  public boolean isBad() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\black\GoldenOffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */