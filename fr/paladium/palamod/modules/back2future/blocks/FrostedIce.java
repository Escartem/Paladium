package fr.paladium.palamod.modules.back2future.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.core.utils.Utils;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockIce;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class FrostedIce extends BlockIce {
  @SideOnly(Side.CLIENT)
  private IIcon[] icons;
  
  public FrostedIce() {
    func_149711_c(0.5F);
    func_149713_g(3);
    func_149647_a(null);
    func_149672_a(field_149778_k);
    func_149658_d("frosted_ice");
    func_149663_c(Utils.getUnlocalisedName("frosted_ice"));
  }
  
  public void func_149726_b(World world, int x, int y, int z) {
    if (world.field_72995_K)
      return; 
    world.func_147464_a(x, y, z, (Block)this, 40 + world.field_73012_v.nextInt(40));
  }
  
  public void func_149674_a(World world, int x, int y, int z, Random rand) {
    if (world.field_72995_K)
      return; 
    int surroundingBlockCount = 0;
    for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
      Block block = world.func_147439_a(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
      if ((block == this || block == Blocks.field_150432_aD || block == Blocks.field_150403_cj) && 
        ++surroundingBlockCount >= 4)
        break; 
    } 
    if (surroundingBlockCount < 4 || rand.nextInt(100) <= 33) {
      int meta = world.func_72805_g(x, y, z);
      if (meta < 3) {
        world.func_72921_c(x, y, z, meta + 1, 2);
      } else {
        world.func_147449_b(x, y, z, Blocks.field_150355_j);
      } 
    } 
    world.func_147464_a(x, y, z, (Block)this, 40 + rand.nextInt(40));
  }
  
  protected boolean func_149700_E() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    if (meta < 0 || meta >= this.icons.length)
      meta = 0; 
    return this.icons[meta];
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister reg) {
    this.icons = new IIcon[4];
    for (int i = 0; i < this.icons.length; i++)
      this.icons[i] = reg.func_94245_a(func_149641_N() + "_" + i); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\FrostedIce.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */