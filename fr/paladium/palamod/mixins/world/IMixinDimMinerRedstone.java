package fr.paladium.palamod.mixins.world;

import fr.paladium.palamod.modules.miner.PMiner;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({World.class})
public abstract class IMixinDimMinerRedstone {
  @Inject(method = {"getBlockPowerInput"}, at = {@At("HEAD")}, cancellable = true)
  public void getBlockPowerInput(int x, int y, int z, CallbackInfoReturnable<Integer> ci) {
    if (PMiner.proxy.isMinerDimension()) {
      ci.setReturnValue(Integer.valueOf(0));
      ci.cancel();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\world\IMixinDimMinerRedstone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */