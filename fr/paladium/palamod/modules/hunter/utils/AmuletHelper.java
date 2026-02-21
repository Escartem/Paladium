package fr.paladium.palamod.modules.hunter.utils;

import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.hunter.items.ItemAmulet;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;

public class AmuletHelper {
  public static List<ItemAmulet.Type> getAmulets(EntityPlayer player) {
    List<ItemAmulet.Type> amulets = new ArrayList<>();
    ItemStack chestplate = player.func_82169_q(2);
    if (chestplate != null && (
      chestplate.func_77973_b() == ItemsRegister.PALADIUM_CHESTPLATE || chestplate
      .func_77973_b() == ItemsRegister.ENDIUM_CHESTPLATE || chestplate
      .func_77973_b() == ItemsRegister.MIXED_ENDIUM_CHESTPLATE || chestplate
      .func_77973_b() == ItemsRegister.PALADIUM_GREEN_CHESTPLATE) && 
      chestplate.func_77942_o() && 
      chestplate.func_77978_p().func_74764_b("amulets")) {
      NBTTagList amuletsNBT = chestplate.func_77978_p().func_150295_c("amulets", 8);
      for (int i = 0; i < amuletsNBT.func_74745_c(); i++) {
        ItemAmulet.Type type = ItemAmulet.Type.valueOf(amuletsNBT.func_150307_f(i));
        amulets.add(type);
      } 
    } 
    return amulets;
  }
  
  public static boolean hasAmulet(EntityPlayer player, ItemAmulet.Type type) {
    return getAmulets(player).contains(type);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunte\\utils\AmuletHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */