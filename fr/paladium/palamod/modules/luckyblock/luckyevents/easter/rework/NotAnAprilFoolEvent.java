package fr.paladium.palamod.modules.luckyblock.luckyevents.easter.rework;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palajobs.core.registry.ItemsRegistry;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class NotAnAprilFoolEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Ce n'est pas un poisson d'avril";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 700;
  
  private static final String TEXTURE_PATH = "easter/not_an_april_fool";
  
  private static final String ACTIVATE_MESSAGE = "Vous avez reçu une canne à pêche en endium !";
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(ItemsRegistry.ENDIUM_FISHING_ROD));
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "Vous avez reçu une canne à pêche en endium !" });
  }
  
  public String getName() {
    return "Ce n'est pas un poisson d'avril";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 700;
  }
  
  public String getTexture() {
    return "easter/not_an_april_fool";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\easter\rework\NotAnAprilFoolEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */