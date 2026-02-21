package fr.paladium.palamod.modules.luckyblock.utils;

import fr.paladium.palamod.api.ItemsRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LuckyBlockBridge {
  public static boolean damageTotem(EntityLivingBase entity, int durability) {
    if (!(entity instanceof EntityPlayer))
      return false; 
    EntityPlayer player = (EntityPlayer)entity;
    Item totem = ItemsRegister.EASTER_TOTEM;
    for (int i = 0; i < player.field_71071_by.field_70462_a.length; ) {
      ItemStack target = player.field_71071_by.field_70462_a[i];
      if (target == null || target.func_77973_b() != totem) {
        i++;
        continue;
      } 
      target.func_77972_a(durability, (EntityLivingBase)player);
      return true;
    } 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckybloc\\utils\LuckyBlockBridge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */