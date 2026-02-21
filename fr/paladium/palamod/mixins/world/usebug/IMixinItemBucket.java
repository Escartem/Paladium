package fr.paladium.palamod.mixins.world.usebug;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({ItemBucket.class})
public abstract class IMixinItemBucket extends Item {
  @Shadow
  private Block field_77876_a;
  
  @Redirect(method = {"tryPlaceContainedLiquid"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;setBlock(IIILnet/minecraft/block/Block;II)Z"))
  private boolean newSetBlock(World world, int x, int y, int z, Block block, int meta, int f) {
    if (y >= 255)
      return false; 
    return world.func_147465_d(x, y, z, this.field_77876_a, 0, 3);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\worl\\usebug\IMixinItemBucket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */