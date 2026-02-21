package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class GoodButNotTooMuch extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    ItemStack helmet = new ItemStack(ItemsRegister.PALADIUM_HELMET);
    ItemStack chestplate = new ItemStack(ItemsRegister.PALADIUM_CHESTPLATE);
    ItemStack leggings = new ItemStack(ItemsRegister.PALADIUM_LEGGINGS);
    ItemStack boots = new ItemStack(ItemsRegister.PALADIUM_BOOTS);
    addEnchant(helmet);
    addEnchant(chestplate);
    addEnchant(leggings);
    addEnchant(boots);
    PlayerUtils.dropItemStack((Entity)player, x, y, z, helmet);
    PlayerUtils.dropItemStack((Entity)player, x, y, z, chestplate);
    PlayerUtils.dropItemStack((Entity)player, x, y, z, leggings);
    PlayerUtils.dropItemStack((Entity)player, x, y, z, boots);
  }
  
  private void addEnchant(ItemStack item) {
    item.func_77966_a(Enchantment.field_77332_c, 4);
    item.func_77966_a(Enchantment.field_77347_r, 3);
    item.func_77964_b(item.func_77958_k() - 1);
  }
  
  public String getName() {
    return "Bien mais pas trop";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 300;
  }
  
  public String getTexture() {
    return "goodbutnottoomuch";
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\GoodButNotTooMuch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */