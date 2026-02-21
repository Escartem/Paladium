package fr.paladium.palamod.mixins.world.usebug;

import fr.paladium.palamod.modules.pillage.PPillage;
import fr.paladium.palamod.util.WorldUtil;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({World.class})
public abstract class IMixinWorldSetBlock {
  @Redirect(method = {"func_147480_a"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;setBlock(IIILnet/minecraft/block/Block;II)Z"))
  private boolean newSetBlock(World world, int x, int y, int z, Block block, int meta, int f) {
    if (PPillage.debugMode)
      System.out.println("[World] setblock at " + x + ", " + y + ", " + z + " to " + block); 
    return WorldUtil.setBlock(world, x, y, z, block, meta, f);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\worl\\usebug\IMixinWorldSetBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */