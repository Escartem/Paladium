package fr.paladium.palamod.mixins.builder;

import fr.paladium.palamod.util.MixinBuilderHelper;
import net.minecraft.block.BlockFalling;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({BlockFalling.class})
public class IMixinBlockFalling {
  @Inject(method = {"func_149830_m"}, at = {@At("HEAD")}, cancellable = true)
  public void func_149830_m(World world, int x, int y, int z, CallbackInfo ci) {
    if (MixinBuilderHelper.isInArea(x, z))
      ci.cancel(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\builder\IMixinBlockFalling.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */