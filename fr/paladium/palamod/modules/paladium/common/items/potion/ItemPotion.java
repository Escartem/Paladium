package fr.paladium.palamod.modules.paladium.common.items.potion;

import fr.paladium.palamod.common.Registry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemPotion extends Item {
  PotionEffect effect;
  
  public ItemPotion(String name, String texturename, PotionEffect effect) {
    func_77625_d(1);
    func_77656_e(16);
    func_77655_b(name);
    func_77637_a((CreativeTabs)Registry.POTION_TAB);
    func_111206_d("palamod:potion/" + texturename);
    this.effect = effect;
  }
  
  public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
    player.func_71008_a(stack, func_77626_a(stack));
    return stack;
  }
  
  public ItemStack func_77654_b(ItemStack stack, World world, EntityPlayer player) {
    if (!player.field_71075_bZ.field_75098_d)
      stack.field_77994_a--; 
    if (!world.field_72995_K)
      player.func_70690_d(this.effect); 
    if (!player.field_71075_bZ.field_75098_d) {
      if (stack.field_77994_a <= 0)
        return new ItemStack(Items.field_151069_bo); 
      player.field_71071_by.func_70441_a(new ItemStack(Items.field_151069_bo));
    } 
    return stack;
  }
  
  public int func_77626_a(ItemStack stack) {
    return 32;
  }
  
  public EnumAction func_77661_b(ItemStack stack) {
    return EnumAction.drink;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\potion\ItemPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */