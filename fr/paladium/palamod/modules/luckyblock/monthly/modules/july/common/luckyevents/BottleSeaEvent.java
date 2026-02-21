package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents;

import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class BottleSeaEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Une bouteille à la mer";
  
  private static final String TEXTURE_PATH = "july/bottle_sea";
  
  private static final int RARITY = 250;
  
  private static final boolean IS_BAD = false;
  
  private static final String DESCRIPTION = "&eVous venez de trouver une bouteille à la mer ! regardez donc ce qu'elle contient !";
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&eVous venez de trouver une bouteille à la mer ! regardez donc ce qu'elle contient !" });
    InventoryUtils.giveOrDropitems((EntityPlayer)player, new ItemStack(ItemsRegister.SEA_BOTTLE));
  }
  
  public String getName() {
    return "Une bouteille à la mer";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 250;
  }
  
  public String getTexture() {
    return "july/bottle_sea";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\luckyevents\BottleSeaEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */