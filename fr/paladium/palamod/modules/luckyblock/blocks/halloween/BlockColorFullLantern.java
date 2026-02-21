package fr.paladium.palamod.modules.luckyblock.blocks.halloween;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockBase;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockColorFullLantern extends BlockBase {
  IIcon[] faces = new IIcon[5];
  
  IIcon top;
  
  public BlockColorFullLantern() {
    super(Material.field_151576_e);
    func_149715_a(1.0F);
    func_149663_c("colored_lentern");
    func_149658_d("palamod:pumpkin/colored_lentern");
    func_149711_c(0.3F);
    func_149672_a(field_149778_k);
  }
  
  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister p_149651_1_) {
    super.func_149651_a(p_149651_1_);
    for (int i = 0; i < 5; i++)
      this.faces[i] = p_149651_1_.func_94245_a(func_149641_N() + (i + 1)); 
    this.top = p_149651_1_.func_94245_a("pumpkin_top");
    this.field_149761_L = p_149651_1_.func_94245_a("pumpkin_side");
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    return (side == 1) ? this.top : ((side == 0) ? this.top : ((side == 2) ? this.faces[meta] : this.field_149761_L));
  }
  
  public void func_149726_b(World world, int x, int y, int z) {
    world.func_147464_a(x, y, z, (Block)this, 2);
  }
  
  public void func_149674_a(World world, int x, int y, int z, Random random) {
    if (world.field_72995_K)
      return; 
    int metadata = world.func_72805_g(x, y, z);
    metadata++;
    if (metadata > 4)
      metadata = 0; 
    world.func_72921_c(x, y, z, metadata, 1);
    world.func_147471_g(x, y, z);
    world.func_147464_a(x, y, z, (Block)this, 2);
    super.func_149674_a(world, x, y, z, random);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\halloween\BlockColorFullLantern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */