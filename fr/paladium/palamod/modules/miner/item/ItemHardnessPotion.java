package fr.paladium.palamod.modules.miner.item;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.miner.PMiner;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemHardnessPotion extends Item implements ITooltipWiki {
  public ItemHardnessPotion() {
    func_77625_d(1);
    func_77656_e(0);
    func_77637_a((CreativeTabs)Registry.MINER_TAB);
    func_77655_b("hardness_potion");
    func_111206_d("palamod:hardness_potion");
  }
  
  public ItemStack func_77654_b(ItemStack item, World world, EntityPlayer player) {
    if (!player.field_71075_bZ.field_75098_d)
      item.field_77994_a--; 
    if (!world.field_72995_K)
      player.func_70690_d(new PotionEffect(PMiner.HARDNESS.field_76415_H, 9600, 0)); 
    if (!player.field_71075_bZ.field_75098_d && 
      item.field_77994_a <= 0)
      return new ItemStack(Items.field_151069_bo); 
    return item;
  }
  
  public int func_77626_a(ItemStack item) {
    return 20;
  }
  
  public EnumAction func_77661_b(ItemStack p_77661_1_) {
    return EnumAction.drink;
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    player.func_71008_a(item, func_77626_a(item));
    return item;
  }
  
  public boolean func_77648_a(ItemStack item, EntityPlayer player, World world, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
    return false;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-outils#11.-autre";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\item\ItemHardnessPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */