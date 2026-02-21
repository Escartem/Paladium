package fr.paladium.palamixins.mixin.common.container;

import net.minecraft.inventory.ContainerDispenser;
import net.minecraft.tileentity.TileEntityDispenser;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({ContainerDispenser.class})
public interface MixinContainerDispenser {
  @Accessor("tileDispenser")
  TileEntityDispenser getTileEntity();
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\mixin\common\container\MixinContainerDispenser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */