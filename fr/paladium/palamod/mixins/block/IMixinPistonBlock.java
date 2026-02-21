package fr.paladium.palamod.mixins.block;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palamod.events.BlockPistonExtendEvent;
import fr.paladium.palamod.events.BlockPistonRetractEvent;
import fr.paladium.palamod.modules.miner.PMiner;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.Facing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({BlockPistonBase.class})
public abstract class IMixinPistonBlock {
  @Inject(method = {"canPushBlock"}, at = {@At("HEAD")}, cancellable = true)
  private static void checkWirelessRedstone(Block b, World world, int x, int y, int z, boolean p_150080_5_, CallbackInfoReturnable<Boolean> callback) {
    if (world.func_147439_a(x, y, z) instanceof fr.paladium.palamod.modules.hunter.blocks.BlockBamboo) {
      world.func_147439_a(x, y, z).func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
      world.func_147468_f(x, y, z);
      callback.setReturnValue(Boolean.valueOf(true));
      callback.cancel();
    } 
    if (PMiner.proxy.isMinerDimension()) {
      callback.setReturnValue(Boolean.valueOf(false));
      callback.cancel();
    } 
  }
  
  @Inject(method = {"updatePistonState"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/world/World;addBlockEvent(IIILnet/minecraft/block/Block;II)V", ordinal = 0)})
  private void onPistonExtend(World world, int x, int y, int z, CallbackInfo ci) {
    int meta = world.func_72805_g(x, y, z);
    int orientation = BlockPistonBase.func_150076_b(meta);
    int length = getLength(world, x, y, z, orientation);
    MinecraftForge.EVENT_BUS.post((Event)new BlockPistonExtendEvent(world, x, y, z, ForgeDirection.values()[orientation], length));
  }
  
  @Inject(method = {"updatePistonState"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/world/World;addBlockEvent(IIILnet/minecraft/block/Block;II)V", ordinal = 1)})
  private void onPistonRetract(World world, int x, int y, int z, CallbackInfo ci) {
    int meta = world.func_72805_g(x, y, z);
    int orientation = BlockPistonBase.func_150076_b(meta);
    MinecraftForge.EVENT_BUS.post((Event)new BlockPistonRetractEvent(world, x, y, z, ForgeDirection.values()[orientation]));
  }
  
  private static int getLength(World p_150077_0_, int p_150077_1_, int p_150077_2_, int p_150077_3_, int p_150077_4_) {
    int i1 = p_150077_1_ + Facing.field_71586_b[p_150077_4_];
    int j1 = p_150077_2_ + Facing.field_71587_c[p_150077_4_];
    int k1 = p_150077_3_ + Facing.field_71585_d[p_150077_4_];
    int l1 = 0;
    while (l1 < 13) {
      if (j1 <= 0 || j1 >= p_150077_0_.func_72800_K())
        return -1; 
      Block block = p_150077_0_.func_147439_a(i1, j1, k1);
      if (!block.isAir((IBlockAccess)p_150077_0_, i1, j1, k1)) {
        if (!canPushBlock(block, p_150077_0_, i1, j1, k1, true))
          return -1; 
        if (block.func_149656_h() != 1) {
          if (l1 == 12)
            return -1; 
          i1 += Facing.field_71586_b[p_150077_4_];
          j1 += Facing.field_71587_c[p_150077_4_];
          k1 += Facing.field_71585_d[p_150077_4_];
          l1++;
        } 
      } 
    } 
    return l1;
  }
  
  private static boolean canPushBlock(Block p_150080_0_, World p_150080_1_, int p_150080_2_, int p_150080_3_, int p_150080_4_, boolean p_150080_5_) {
    if (p_150080_0_ == Blocks.field_150343_Z)
      return false; 
    if (p_150080_0_ != Blocks.field_150331_J && p_150080_0_ != Blocks.field_150320_F) {
      if (p_150080_0_.func_149712_f(p_150080_1_, p_150080_2_, p_150080_3_, p_150080_4_) == -1.0F || p_150080_0_.func_149656_h() == 2)
        return false; 
      if (p_150080_0_.func_149656_h() == 1) {
        if (!p_150080_5_)
          return false; 
        return true;
      } 
    } else if (BlockPistonBase.func_150075_c(p_150080_1_.func_72805_g(p_150080_2_, p_150080_3_, p_150080_4_))) {
      return false;
    } 
    return !p_150080_1_.func_147439_a(p_150080_2_, p_150080_3_, p_150080_4_).hasTileEntity(p_150080_1_.func_72805_g(p_150080_2_, p_150080_3_, p_150080_4_));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\block\IMixinPistonBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */