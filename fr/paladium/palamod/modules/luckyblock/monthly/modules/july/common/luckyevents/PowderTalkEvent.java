package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PowderTalkEvent extends ALuckyEvent {
  public static final String STRUCTURE_NAME = "barrel";
  
  public static final String WARNING_MESSAGE = "&e&l[!] &eVous avez fait parler la poudre ! &cAttention à l'explosion !";
  
  private static final String EVENT_NAME = "Faire parler la poudre";
  
  private static final String TEXTURE_PATH = "july/powder_talk";
  
  private static final int RARITY = 200;
  
  private static final boolean IS_BAD = false;
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    World world = player.field_70170_p;
    MonthlyUtils.performExplosion(world, x, y, z);
    giveItems(world, x, y, z);
  }
  
  private void giveItems(World world, int x, int y, int z) {
    ItemUtils.spawnItemInWorld(world, x, y, z, new ItemStack(BlocksRegister.BARREL, 1));
  }
  
  public String getName() {
    return "Faire parler la poudre";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 200;
  }
  
  public String getTexture() {
    return "july/powder_talk";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\luckyevents\PowderTalkEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */