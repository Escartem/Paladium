package fr.paladium.palamod.mixins.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({BlockBush.class})
public abstract class IMixinBlockBush extends Block {
  protected IMixinBlockBush(Material material) {
    super(material);
  }
  
  @Overwrite
  public void func_149855_e(World world, int x, int y, int z) {
    if (!func_149718_j(world, x, y, z))
      world.func_147465_d(x, y, z, Block.func_149729_e(0), 0, 2); 
  }
  
  @Shadow
  public abstract boolean func_149718_j(World paramWorld, int paramInt1, int paramInt2, int paramInt3);
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\block\IMixinBlockBush.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */