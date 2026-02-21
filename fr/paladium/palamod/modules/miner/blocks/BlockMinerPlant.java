package fr.paladium.palamod.modules.miner.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.common.Registry;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMinerPlant extends BlockBush {
  private final String name;
  
  private final boolean growable;
  
  private final int growableMaxStated;
  
  private final float[] heights;
  
  private final IIcon[] icons;
  
  public BlockMinerPlant(String name) {
    this(name, false, 0, new float[0]);
  }
  
  public BlockMinerPlant(String name, boolean growable, int growableMaxStated, float... heights) {
    this.name = name;
    func_149663_c(name);
    func_149658_d("palamod:miner/" + name);
    func_149647_a((CreativeTabs)Registry.MINER_TAB);
    func_149711_c(0.0F);
    func_149672_a(Block.field_149779_h);
    this.growable = growable;
    this.growableMaxStated = growableMaxStated;
    this.icons = new IIcon[growableMaxStated];
    this.heights = heights;
    func_149675_a(growable);
  }
  
  public void func_149651_a(IIconRegister register) {
    for (int i = 0; i < this.growableMaxStated; i++) {
      if (i > 0) {
        this.icons[i] = register.func_94245_a("palamod:miner/" + this.name + "_" + i);
        ((TextureAtlasSprite)this.icons[i]).func_110969_c(32);
      } else {
        this.icons[i] = register.func_94245_a("palamod:miner/" + this.name);
      } 
    } 
    super.func_149651_a(register);
  }
  
  public void func_149674_a(World world, int x, int y, int z, Random r) {
    if (this.growable) {
      int meta = world.func_72805_g(x, y, z);
      if (meta < this.growableMaxStated - 1) {
        meta++;
        func_149676_a((float)this.field_149759_B, (float)this.field_149760_C, (float)this.field_149754_D, (float)this.field_149755_E, this.heights[meta], (float)this.field_149757_G);
        world.func_72921_c(x, y, z, meta, 2);
      } 
    } 
    super.func_149674_a(world, x, y, z, r);
  }
  
  protected boolean func_149700_E() {
    return true;
  }
  
  public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
    return null;
  }
  
  public int func_149745_a(Random p_149745_1_) {
    return 1;
  }
  
  public IIcon func_149673_e(IBlockAccess world, int x, int y, int z, int dir) {
    if (this.growable)
      return this.icons[world.func_72805_g(x, y, z)]; 
    return super.func_149673_e(world, x, y, z, dir);
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int dir, int meta) {
    if (this.growable)
      return this.icons[meta]; 
    return this.field_149761_L;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\miner\blocks\BlockMinerPlant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */