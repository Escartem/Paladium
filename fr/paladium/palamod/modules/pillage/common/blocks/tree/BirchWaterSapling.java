package fr.paladium.palamod.modules.pillage.common.blocks.tree;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.common.Registry;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BirchWaterSapling extends BlockSapling implements ITooltipWiki {
  public BirchWaterSapling(String name) {
    func_149672_a(Block.field_149779_h);
    func_149663_c(name);
    func_149658_d("palamod:pillage/" + name);
    func_149647_a((CreativeTabs)Registry.PILLAGE_TAB);
    GameRegistry.registerBlock((Block)this, func_149739_a());
  }
  
  public void func_149878_d(World worldIn, int posX, int posY, int posZ, Random rand) {
    int metadata = worldIn.func_72805_g(posX, posY, posZ);
    WorldGenBirchWater worldGenBirchWater = new WorldGenBirchWater(true, false);
    worldIn.func_147468_f(posX, posY, posZ);
    if (!worldGenBirchWater.func_76484_a(worldIn, rand, posX, posY, posZ))
      worldIn.func_147465_d(posX, posY, posZ, (Block)this, metadata, 4); 
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int metadata) {
    return this.field_149761_L;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149666_a(Item item, CreativeTabs creativetabs, List<ItemStack> list) {
    list.add(new ItemStack(item, 1, 0));
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister iiconRegister) {
    this.field_149761_L = iiconRegister.func_94245_a(func_149641_N());
  }
  
  public boolean func_149718_j(World world, int x, int y, int z) {
    return world.func_147439_a(x, y - 1, z) instanceof net.minecraft.block.BlockClay;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/les-ressources-naturelles#1.-les-plantes";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\blocks\tree\BirchWaterSapling.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */