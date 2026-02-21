package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents;

import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class HackerEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Pirate informatique";
  
  private static final String TEXTURE_PATH = "july/hacker";
  
  private static final int RARITY = 300;
  
  private static final boolean IS_BAD = false;
  
  private static final String ENCRYPTED_COMPUTER_GIVE_MESSAGE = "Vous avez reçu un ordinateur crypté !";
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "Vous avez reçu un ordinateur crypté !" });
    InventoryUtils.giveOrDropitems((EntityPlayer)player, new ItemStack(BlocksRegister.ENCRYPTED_COMPUTER, 1));
  }
  
  public String getName() {
    return "Pirate informatique";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 300;
  }
  
  public String getTexture() {
    return "july/hacker";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\luckyevents\HackerEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */