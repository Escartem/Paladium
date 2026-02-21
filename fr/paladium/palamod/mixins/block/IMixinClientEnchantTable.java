package fr.paladium.palamod.mixins.block;

import fr.paladium.palamod.api.BlocksRegister;
import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockEnchantmentTable;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin({BlockEnchantmentTable.class})
public abstract class IMixinClientEnchantTable extends BlockContainer {
  protected IMixinClientEnchantTable(Material p_i45386_1_) {
    super(p_i45386_1_);
  }
  
  @Inject(method = {"randomDisplayTick"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/world/World;getBlock(III)Lnet/minecraft/block/Block;", ordinal = 0)}, locals = LocalCapture.CAPTURE_FAILEXCEPTION)
  private void loadCurrentPosition(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_, CallbackInfo ci, int l, int i1, int j1) {
    if (p_149734_1_.func_147439_a(l, j1, i1) == BlocksRegister.MYSTICAL_BOOKSHELF) {
      if (!p_149734_1_.func_147437_c((l - p_149734_2_) / 2 + p_149734_2_, j1, (i1 - p_149734_4_) / 2 + p_149734_4_))
        return; 
      p_149734_1_.func_72869_a("enchantmenttable", p_149734_2_ + 0.5D, p_149734_3_ + 2.0D, p_149734_4_ + 0.5D, ((l - p_149734_2_) + p_149734_5_
          .nextFloat()) - 0.5D, ((j1 - p_149734_3_) - p_149734_5_
          .nextFloat() - 1.0F), ((i1 - p_149734_4_) + p_149734_5_
          .nextFloat()) - 0.5D);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\block\IMixinClientEnchantTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */