package fr.paladium.palamod.modules.hunter.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDolphinNoiseBox extends Item {
  public ItemDolphinNoiseBox() {
    func_77656_e(16);
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    player.func_85030_a("palamod:dolphin_noise_box", 4.0F, 1.0F);
    if (!player.field_71075_bZ.field_75098_d)
      item.func_77972_a(1, (EntityLivingBase)player); 
    return super.func_77659_a(item, world, player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\items\ItemDolphinNoiseBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */