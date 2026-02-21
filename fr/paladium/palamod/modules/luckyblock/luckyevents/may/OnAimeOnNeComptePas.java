package fr.paladium.palamod.modules.luckyblock.luckyevents.may;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class OnAimeOnNeComptePas extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "Tu aimes ton familier et ça se voit ! Et si on arrêtait de compter grâce à ces supers récompenses ?" });
    ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(ItemsRegister.PET_XP_MEDIUM));
    ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(ItemsRegister.PET_SNACK));
  }
  
  public String getName() {
    return "Quand on aime on ne compte pas (les levels)";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 150;
  }
  
  public String getTexture() {
    return "may/aime_compte_pas";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\may\OnAimeOnNeComptePas.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */