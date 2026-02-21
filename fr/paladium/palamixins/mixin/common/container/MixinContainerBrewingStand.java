package fr.paladium.palamixins.mixin.common.container;

import net.minecraft.inventory.ContainerBrewingStand;
import net.minecraft.tileentity.TileEntityBrewingStand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({ContainerBrewingStand.class})
public interface MixinContainerBrewingStand {
  @Accessor("tileBrewingStand")
  TileEntityBrewingStand getTileEntity();
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\mixin\common\container\MixinContainerBrewingStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */