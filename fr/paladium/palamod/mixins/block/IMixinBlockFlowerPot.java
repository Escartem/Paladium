package fr.paladium.palamod.mixins.block;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.ajobs.common.bridge.JobsBridge;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFlowerPot;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({BlockFlowerPot.class})
public abstract class IMixinBlockFlowerPot extends BlockContainer {
  protected IMixinBlockFlowerPot(Material p_i45386_1_) {
    super(p_i45386_1_);
  }
  
  @Inject(method = {"onBlockActivated"}, at = {@At("HEAD")}, cancellable = true)
  public void onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_, CallbackInfoReturnable<Boolean> ci) {
    if (p_149727_5_.field_71071_by.func_70448_g() != null && !JobsBridge.canUseCraft(p_149727_5_, p_149727_5_.field_71071_by.func_70448_g(), false))
      ci.setReturnValue(Boolean.valueOf(false)); 
  }
  
  public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
    Object object = null;
    byte b0 = 0;
    switch (p_149915_2_) {
      case 1:
        object = Blocks.field_150328_O;
        b0 = 0;
        break;
      case 2:
        object = Blocks.field_150327_N;
        break;
      case 3:
        object = Blocks.field_150345_g;
        b0 = 0;
        break;
      case 4:
        object = Blocks.field_150345_g;
        b0 = 1;
        break;
      case 5:
        object = Blocks.field_150345_g;
        b0 = 2;
        break;
      case 6:
        object = Blocks.field_150345_g;
        b0 = 3;
        break;
      case 7:
        object = Blocks.field_150337_Q;
        break;
      case 8:
        object = Blocks.field_150338_P;
        break;
      case 9:
        object = Blocks.field_150434_aF;
        break;
      case 10:
        object = Blocks.field_150330_I;
        break;
      case 11:
        object = Blocks.field_150329_H;
        b0 = 2;
        break;
      case 12:
        object = Blocks.field_150345_g;
        b0 = 4;
        break;
      case 13:
        object = Blocks.field_150345_g;
        b0 = 5;
      case 15:
        object = BlocksRegister.LILY_OF_THE_VALLEY;
        b0 = 5;
        break;
    } 
    return (TileEntity)new TileEntityFlowerPot(Item.func_150898_a((Block)object), b0);
  }
  
  @Overwrite
  private boolean func_149928_a(Block p_149928_1_, int p_149928_2_) {
    return (p_149928_1_ != BlocksRegister.LILY_OF_THE_VALLEY && p_149928_1_ != Blocks.field_150327_N && p_149928_1_ != Blocks.field_150328_O && p_149928_1_ != Blocks.field_150434_aF && p_149928_1_ != Blocks.field_150338_P && p_149928_1_ != Blocks.field_150337_Q && p_149928_1_ != Blocks.field_150345_g && p_149928_1_ != Blocks.field_150330_I) ? ((p_149928_1_ == Blocks.field_150329_H && p_149928_2_ == 2)) : true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\block\IMixinBlockFlowerPot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */