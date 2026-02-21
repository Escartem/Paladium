package fr.paladium.palamod.mixins.builder;

import fr.paladium.palamod.modules.ajobs.common.bridge.JobsBridge;
import fr.paladium.palamod.util.MixinBuilderHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({BlockBush.class})
public class IMixinBlockBush {
  @Inject(method = {"checkAndDropBlock"}, at = {@At("HEAD")}, cancellable = true)
  public void checkAndDropBlock(World world, int x, int y, int z, CallbackInfo ci) {
    if (MixinBuilderHelper.isInArea(x, z))
      ci.cancel(); 
  }
  
  @Overwrite
  protected boolean func_149854_a(Block p_149854_1_) {
    return (p_149854_1_ == Blocks.field_150349_c || p_149854_1_ == Blocks.field_150346_d || p_149854_1_ == Blocks.field_150458_ak || p_149854_1_ == JobsBridge.getColoredGrass());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\builder\IMixinBlockBush.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */