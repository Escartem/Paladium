package fr.paladium.palamod.modules.back2future.blocks;

import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.back2future.entities.EntityFallingConcrete;
import fr.paladium.palamod.modules.back2future.lib.EnumDyeColor;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BlockConcretePowder extends BlockFalling {
  private EnumDyeColor color;
  
  public BlockConcretePowder(EnumDyeColor color) {
    super(Material.field_151595_p);
    this.color = color;
    func_149663_c("concrete_powder_" + this.color);
    func_149672_a(field_149776_m);
    func_149647_a(Back2Future.creativeTab);
    func_149711_c(0.5F);
    func_149752_b(2.5F);
    func_149658_d("concrete_powder_" + this.color.getName());
  }
  
  public void func_149726_b(World worldIn, int x, int y, int z) {
    if (worldIn.func_147439_a(x + 1, y, z).func_149688_o() == Material.field_151586_h || worldIn
      .func_147439_a(x - 1, y, z).func_149688_o() == Material.field_151586_h || worldIn
      .func_147439_a(x, y, z + 1).func_149688_o() == Material.field_151586_h || worldIn
      .func_147439_a(x, y, z - 1).func_149688_o() == Material.field_151586_h || worldIn
      .func_147439_a(x, y + 1, z).func_149688_o() == Material.field_151586_h || worldIn
      .func_147439_a(x, y - 1, z).func_149688_o() == Material.field_151586_h) {
      worldIn.func_147449_b(x, y, z, ConcreteRegistry.getSolidFromDye(this.color));
    } else {
      worldIn.func_147464_a(x, y, z, (Block)this, func_149738_a(worldIn));
    } 
  }
  
  public void func_149695_a(World blkAcc, int x, int y, int z, Block p_149695_5_) {
    if (blkAcc instanceof World) {
      World worldIn = blkAcc;
      if (worldIn.func_147439_a(x + 1, y, z).func_149688_o() == Material.field_151586_h || worldIn
        .func_147439_a(x - 1, y, z).func_149688_o() == Material.field_151586_h || worldIn
        .func_147439_a(x, y, z + 1).func_149688_o() == Material.field_151586_h || worldIn
        .func_147439_a(x, y, z - 1).func_149688_o() == Material.field_151586_h || worldIn
        .func_147439_a(x, y + 1, z).func_149688_o() == Material.field_151586_h || worldIn
        .func_147439_a(x, y - 1, z).func_149688_o() == Material.field_151586_h) {
        worldIn.func_147449_b(x, y, z, ConcreteRegistry.getSolidFromDye(this.color));
      } else {
        worldIn.func_147464_a(x, y, z, (Block)this, func_149738_a(worldIn));
      } 
    } 
  }
  
  public void func_149674_a(World worldIn, int x, int y, int z, Random rand) {
    if (!worldIn.field_72995_K)
      func_149830_m(worldIn, x, y, z); 
  }
  
  private void func_149830_m(World p_149830_1_, int p_149830_2_, int p_149830_3_, int p_149830_4_) {
    if (canFallThrough(p_149830_1_, p_149830_2_, p_149830_3_ - 1, p_149830_4_) && p_149830_3_ >= 0) {
      byte b0 = 32;
      if (!field_149832_M && p_149830_1_.func_72904_c(p_149830_2_ - b0, p_149830_3_ - b0, p_149830_4_ - b0, p_149830_2_ + b0, p_149830_3_ + b0, p_149830_4_ + b0)) {
        if (!p_149830_1_.field_72995_K) {
          EntityFallingConcrete entityfallingblock = new EntityFallingConcrete(p_149830_1_, (p_149830_2_ + 0.5F), (p_149830_3_ + 0.5F), (p_149830_4_ + 0.5F), (Block)this, p_149830_1_.func_72805_g(p_149830_2_, p_149830_3_, p_149830_4_));
          func_149829_a((EntityFallingBlock)entityfallingblock);
          p_149830_1_.func_72838_d((Entity)entityfallingblock);
        } 
      } else {
        p_149830_1_.func_147468_f(p_149830_2_, p_149830_3_, p_149830_4_);
        while (canFallThrough(p_149830_1_, p_149830_2_, p_149830_3_ - 1, p_149830_4_) && p_149830_3_ > 0)
          p_149830_3_--; 
        if (p_149830_3_ > 0)
          p_149830_1_.func_147449_b(p_149830_2_, p_149830_3_, p_149830_4_, (Block)this); 
      } 
    } 
  }
  
  protected void onStartFalling(EntityFallingBlock fallingEntity) {}
  
  public static boolean canFallThrough(World worldIn, int x, int y, int z) {
    Block block = worldIn.func_147439_a(x, y, z);
    Material material = block.func_149688_o();
    return (block == Blocks.field_150480_ab || material == Material.field_151579_a || material == Material.field_151586_h || material == Material.field_151587_i);
  }
  
  public void onEndFalling(World worldIn, int x, int y, int z) {}
  
  public EnumDyeColor getColor() {
    return this.color;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\blocks\BlockConcretePowder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */