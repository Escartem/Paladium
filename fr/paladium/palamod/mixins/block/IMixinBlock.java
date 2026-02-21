package fr.paladium.palamod.mixins.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.miner.PMiner;
import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({Block.class})
public abstract class IMixinBlock {
  private final int dimMinerColor = (new Color(100, 100, 100)).getRGB();
  
  @Overwrite(remap = false)
  public boolean isBed(IBlockAccess world, int x, int y, int z, EntityLivingBase player) {
    return ((Block)this == Blocks.field_150324_C || (Block)this == BlocksRegister.SLEEPING_BAG);
  }
  
  @SideOnly(Side.CLIENT)
  @Inject(method = {"colorMultiplier"}, at = {@At("HEAD")}, cancellable = true)
  public void colorMultiplier(IBlockAccess world, int x, int y, int z, CallbackInfoReturnable<Integer> cir) {
    if (PMiner.proxy.isMinerDimension()) {
      cir.setReturnValue(Integer.valueOf(this.dimMinerColor));
      cir.cancel();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\block\IMixinBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */