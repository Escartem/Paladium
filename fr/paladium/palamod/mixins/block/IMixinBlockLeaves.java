package fr.paladium.palamod.mixins.block;

import fr.paladium.palamod.modules.miner.PMiner;
import java.awt.Color;
import net.minecraft.block.BlockLeaves;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({BlockLeaves.class})
public abstract class IMixinBlockLeaves {
  private final int dimMinerColor = (new Color(100, 100, 100)).getRGB();
  
  @Inject(method = {"colorMultiplier"}, at = {@At("HEAD")}, cancellable = true)
  public void colorMultiplier(IBlockAccess world, int x, int y, int z, CallbackInfoReturnable<Integer> cir) {
    if (PMiner.proxy.isMinerDimension()) {
      cir.setReturnValue(Integer.valueOf(this.dimMinerColor));
      cir.cancel();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\block\IMixinBlockLeaves.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */