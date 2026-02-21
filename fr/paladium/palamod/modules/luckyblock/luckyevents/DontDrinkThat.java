package fr.paladium.palamod.modules.luckyblock.luckyevents;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import fr.paladium.palamod.modules.luckyblock.utils.PlayerUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;

public class DontDrinkThat extends ALuckyEvent {
  public static Potion[] types = new Potion[] { Potion.field_76420_g, Potion.field_76422_e, Potion.field_76426_n, Potion.field_76424_c, Potion.field_76434_w, Potion.field_76444_x };
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    ItemStack stack = new ItemStack((Item)Items.field_151068_bn, 1);
    stack.func_151001_c("§cNe bois pas ça !");
    stack.func_77978_p().func_74778_a("luckyeffect", "good");
    PlayerUtils.dropItemStack((Entity)player, x, y, z, stack);
  }
  
  public String getName() {
    return "Ne bois pas ça";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 200;
  }
  
  public double getWeight() {
    return PLuckyBlock.LuckyConst / getRarity();
  }
  
  public String getTexture() {
    return "bois_pas_ca";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\DontDrinkThat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */