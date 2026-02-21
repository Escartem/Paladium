package fr.paladium.palamixins.mixin.common.container;

import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.tileentity.TileEntityFurnace;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({ContainerFurnace.class})
public interface MixinContainerFurnace {
  @Accessor("tileFurnace")
  TileEntityFurnace getTileEntity();
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\mixin\common\container\MixinContainerFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */