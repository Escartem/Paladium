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

public class DontDrinkThatBad extends ALuckyEvent {
  public static Potion[] types = new Potion[] { Potion.field_76440_q, Potion.field_76431_k, Potion.field_76419_f, Potion.field_76438_s, Potion.field_76421_d, Potion.field_76436_u };
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    ItemStack stack = new ItemStack((Item)Items.field_151068_bn, 1);
    stack.func_151001_c("§cNe bois pas ça !");
    stack.func_77978_p().func_74778_a("luckyeffect", "bad");
    PlayerUtils.dropItemStack((Entity)player, x, y, z, stack);
  }
  
  public String getName() {
    return "Ne bois pas ça";
  }
  
  public boolean isBad() {
    return true;
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\DontDrinkThatBad.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */