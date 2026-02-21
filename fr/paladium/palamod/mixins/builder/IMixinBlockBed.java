package fr.paladium.palamod.mixins.builder;

import fr.paladium.palamod.util.MixinBuilderHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({BlockBed.class})
public class IMixinBlockBed {
  @Inject(method = {"onNeighborBlockChange"}, at = {@At("HEAD")}, cancellable = true)
  public void onNeighborBlockChange(World world, int x, int y, int z, Block block, CallbackInfo ci) {
    if (MixinBuilderHelper.isInArea(x, z))
      ci.cancel(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\builder\IMixinBlockBed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */