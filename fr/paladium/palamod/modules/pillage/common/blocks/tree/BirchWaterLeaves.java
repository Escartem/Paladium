package fr.paladium.palamod.modules.pillage.common.blocks.tree;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.pillage.registerer.PPRegisterer;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BirchWaterLeaves extends BlockLeaves implements ITooltipWiki {
  private IIcon icon;
  
  public BirchWaterLeaves(String name) {
    func_149663_c(name);
    func_149658_d("palamod:pillage/" + name);
    func_149647_a((CreativeTabs)Registry.PILLAGE_TAB);
    GameRegistry.registerBlock((Block)this, func_149739_a());
  }
  
  public Item func_149650_a(int metadata, Random random, int par3) {
    return Item.func_150898_a(PPRegisterer.PPBlocks.WATER_SAPLING);
  }
  
  public void func_149690_a(World world, int x, int y, int z, int metadata, float par6, int par7) {
    if (!world.field_72995_K && 
      world.field_73012_v.nextInt(20) == 0) {
      Item splingid = func_149650_a(metadata, world.field_73012_v, par7);
      func_149642_a(world, x, y, z, new ItemStack(splingid, 1, 
            func_149692_a(metadata)));
    } 
  }
  
  public IIcon func_149691_a(int side, int meta) {
    return this.icon;
  }
  
  public void func_149651_a(IIconRegister iIconRegister) {
    this.icon = iIconRegister.func_94245_a(func_149641_N());
  }
  
  public String[] func_150125_e() {
    return new String[0];
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  public int func_149635_D() {
    return -1;
  }
  
  public int func_149741_i(int par1) {
    return -1;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149720_d(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_) {
    return -1;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-ressources-naturelles#2.-les-arbres";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\blocks\tree\BirchWaterLeaves.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */