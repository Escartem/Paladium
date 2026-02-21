package fr.paladium.palamod.modules.smeltery.items.tools;

import fr.paladium.palamod.common.Registry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSmithHammer extends ItemHammer {
  public ItemSmithHammer() {
    super(Item.ToolMaterial.WOOD);
    func_77656_e(10);
    func_77655_b("hammer.smith");
    func_111206_d("palamod:hammer/smith");
    func_77637_a((CreativeTabs)Registry.SMELTERY_TAB);
  }
  
  public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    if (!(world.func_147439_a(x, y, z) instanceof net.minecraft.block.BlockAnvil))
      return false; 
    int meta = world.func_72805_g(x, y, z);
    if (meta == 0)
      return false; 
    world.func_147468_f(x, y, z);
    world.func_147465_d(x, y, z, Blocks.field_150467_bQ, meta - 4, 0);
    if (!player.field_71075_bZ.field_75098_d)
      stack.func_77972_a(1, (EntityLivingBase)player); 
    return true;
  }
  
  public boolean func_82789_a(ItemStack input, ItemStack repair) {
    if (repair.func_77973_b() == Items.field_151042_j)
      return true; 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smeltery\items\tools\ItemSmithHammer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */