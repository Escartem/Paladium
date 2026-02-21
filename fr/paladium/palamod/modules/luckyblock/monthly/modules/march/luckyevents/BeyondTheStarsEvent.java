package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class BeyondTheStarsEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Par delà les étoiles";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 220;
  
  private static final String TEXTURE_PATH = "march/beyond_the_stars";
  
  public static final String FAILURE_MESSAGE = "§cTu n'as pas réussi le test";
  
  public static final String WIN_UNLOCK_MESSAGE = "§aTu as réussi le test et tu obtiens 1 amulette de feu";
  
  public static final String WIN_MESSAGE = "§aTu as réussi le test !";
  
  private static final String ACTIVATE_MESSAGE = "§aUn télescope est apparu à tes pieds !";
  
  private static BeyondTheStarsEvent instance;
  
  public static BeyondTheStarsEvent getInstance() {
    return instance;
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    if (EventUtils.canInteract((EntityPlayer)player, x, y, z)) {
      player.field_70170_p.func_147449_b(x, y, z, BlocksRegister.TELESCOPE);
    } else {
      ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(BlocksRegister.TELESCOPE));
    } 
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "§aUn télescope est apparu à tes pieds !" });
  }
  
  public String getName() {
    return "Par delà les étoiles";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 220;
  }
  
  public String getTexture() {
    return "march/beyond_the_stars";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\luckyevents\BeyondTheStarsEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */