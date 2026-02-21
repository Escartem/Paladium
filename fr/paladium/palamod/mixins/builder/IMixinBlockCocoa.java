package fr.paladium.palamod.mixins.builder;

import fr.paladium.palamod.util.MixinBuilderHelper;
import net.minecraft.block.BlockCocoa;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({BlockCocoa.class})
public class IMixinBlockCocoa {
  @Inject(method = {"canBlockStay"}, at = {@At("HEAD")}, cancellable = true)
  public void canBlockStay(World world, int x, int y, int z, CallbackInfoReturnable<Boolean> ci) {
    if (MixinBuilderHelper.isInArea(x, z)) {
      ci.setReturnValue(Boolean.valueOf(true));
      ci.cancel();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\builder\IMixinBlockCocoa.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */