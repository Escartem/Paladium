package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import fr.welsymc.guardiangolem.common.init.ItemInit;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GolemUpgrade extends ALuckyEvent {
  private Item[] items = new Item[] { ItemInit.COSMETIC_HAT_FARMER, ItemInit.COSMETIC_HAT_LEPRECHAUN, ItemInit.COSMETIC_PARTICLES_FLAME, ItemInit.COSMETIC_PARTICLES_ENDER, ItemInit.COSMETIC_SKIN_STEAMPUNK, ItemInit.COSMETIC_SKIN_FUTURIST, ItemInit.COSMETIC_SKIN_DIRTY };
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    Random rand = player.field_70170_p.field_73012_v;
    Item item = this.items[rand.nextInt(this.items.length)];
    PlayerUtils.dropItemStack((Entity)player, x, y, z, new ItemStack(item, 1));
  }
  
  public String getName() {
    return "Golem Classieux";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 1000;
  }
  
  public String getTexture() {
    return "golem_classieux";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\GolemUpgrade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */