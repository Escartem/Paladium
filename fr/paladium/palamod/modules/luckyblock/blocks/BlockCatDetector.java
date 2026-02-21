package fr.paladium.palamod.modules.luckyblock.blocks;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityCatDetector;
import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCatDetector extends BlockContainer implements ITooltipWiki {
  private IIcon[] icons;
  
  public BlockCatDetector() {
    super(Material.field_151576_e);
    func_149663_c("cat_detector");
    func_149713_g(255);
    func_149715_a(0.0F);
    func_149711_c(4.0F);
    func_149752_b(4.0F);
    setHarvestLevel("pickaxe", 1);
    func_149647_a(PLuckyBlock.TAB);
  }
  
  public TileEntity func_149915_a(World world, int meta) {
    return (TileEntity)new TileEntityCatDetector();
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
    return Item.func_150898_a(BlocksRegister.CAT_DETECTOR);
  }
  
  public void func_149651_a(IIconRegister icon) {
    this.icons = new IIcon[2];
    this.icons[0] = icon.func_94245_a("palamod:machines/catdetector_off");
    this.icons[1] = icon.func_94245_a("palamod:machines/catdetector_on");
  }
  
  public IIcon func_149691_a(int side, int meta) {
    if (meta < 0 || meta >= this.icons.length)
      meta = 0; 
    return this.icons[meta];
  }
  
  public boolean func_149744_f() {
    return true;
  }
  
  public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
    return true;
  }
  
  public boolean getWeakChanges(IBlockAccess world, int x, int y, int z) {
    return super.getWeakChanges(world, x, y, z);
  }
  
  public int func_149709_b(IBlockAccess p_149709_1_, int p_149709_2_, int p_149709_3_, int p_149709_4_, int p_149709_5_) {
    World world = (World)p_149709_1_;
    int meta = world.func_72805_g(p_149709_2_, p_149709_3_, p_149709_4_);
    return meta * 15;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#2.-lucky-block-en-findium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\BlockCatDetector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */