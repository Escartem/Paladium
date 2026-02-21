package fr.paladium.palamod.mixins.builder;

import fr.paladium.palamod.util.MixinBuilderHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({BlockTorch.class})
public class IMixinBlockTorch {
  @Inject(method = {"func_150108_b"}, at = {@At("HEAD")}, cancellable = true)
  public void canBlockStay(World world, int x, int y, int z, Block block, CallbackInfoReturnable<Boolean> ci) {
    if (MixinBuilderHelper.isInArea(x, z)) {
      ci.setReturnValue(Boolean.valueOf(true));
      ci.cancel();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\builder\IMixinBlockTorch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */