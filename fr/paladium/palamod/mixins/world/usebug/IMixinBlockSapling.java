package fr.paladium.palamod.mixins.world.usebug;

import fr.paladium.palamod.util.WorldUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({BlockSapling.class})
public abstract class IMixinBlockSapling {
  @Redirect(method = {"func_149878_d"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;setBlock(IIILnet/minecraft/block/Block;II)Z"))
  private boolean newSetBlock(World world, int x, int y, int z, Block block, int meta, int f) {
    return WorldUtil.setBlock(world, x, y, z, block, meta, f);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\worl\\usebug\IMixinBlockSapling.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */