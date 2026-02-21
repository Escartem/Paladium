package fr.paladium.palamod.mixins.block;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.miner.PMiner;
import java.awt.Color;
import net.minecraft.block.BlockGrass;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({BlockGrass.class})
public abstract class IMixinBlockGrass {
  private final int dimMinerColor = (new Color(100, 100, 100)).getRGB();
  
  @Inject(method = {"colorMultiplier"}, at = {@At("HEAD")}, cancellable = true)
  public void colorMultiplier(IBlockAccess world, int x, int y, int z, CallbackInfoReturnable<Integer> cir) {
    if (PMiner.proxy.isMinerDimension()) {
      cir.setReturnValue(Integer.valueOf(this.dimMinerColor));
      cir.cancel();
    } 
  }
  
  @Inject(method = {"getIcon(II)Lnet/minecraft/util/IIcon;"}, at = {@At("HEAD")}, cancellable = true)
  public void getIcon(int side, int meta, CallbackInfoReturnable<IIcon> cir) {
    if (!PMiner.proxy.isMinerDimension())
      return; 
    cir.setReturnValue(BlocksRegister.STRANGE_GRASS.func_149691_a(side, meta));
    cir.cancel();
  }
  
  @Inject(method = {"getIcon(Lnet/minecraft/world/IBlockAccess;IIII)Lnet/minecraft/util/IIcon;"}, at = {@At("HEAD")}, cancellable = true)
  public void getIcon(IBlockAccess world, int x, int y, int z, int side, CallbackInfoReturnable<IIcon> cir) {
    if (!PMiner.proxy.isMinerDimension())
      return; 
    cir.setReturnValue(BlocksRegister.STRANGE_GRASS.func_149673_e(world, x, y, z, side));
    cir.cancel();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\block\IMixinBlockGrass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */