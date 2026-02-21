package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SmokedSalmon extends ALuckyEvent {
  private Item[] smokes = new Item[] { 
      ItemsRegister.SMOKEBOMB_BLACK, ItemsRegister.SMOKEBOMB_BLUE, ItemsRegister.SMOKEBOMB_BROWN, ItemsRegister.SMOKEBOMB_CYAN, ItemsRegister.SMOKEBOMB_GRAY, ItemsRegister.SMOKEBOMB_GREEN, ItemsRegister.SMOKEBOMB_LIGHT_BLUE, ItemsRegister.SMOKEBOMB_LIGHT_GRAY, ItemsRegister.SMOKEBOMB_LIME, ItemsRegister.SMOKEBOMB_MAGENTA, 
      ItemsRegister.SMOKEBOMB_ORANGE, ItemsRegister.SMOKEBOMB_PINK, ItemsRegister.SMOKEBOMB_PURPLE, ItemsRegister.SMOKEBOMB_RED, ItemsRegister.SMOKEBOMB_WHITE, ItemsRegister.SMOKEBOMB_YELLOW };
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    Random rand = player.field_70170_p.field_73012_v;
    for (int i = 0; i < 3; i++) {
      ItemStack stack = new ItemStack(this.smokes[rand.nextInt(this.smokes.length)], 1);
      PlayerUtils.dropItemStack((Entity)player, x, y, z, stack);
    } 
  }
  
  public String getName() {
    return "Saumon Fumé";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 500;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "saumon_fume";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\SmokedSalmon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */