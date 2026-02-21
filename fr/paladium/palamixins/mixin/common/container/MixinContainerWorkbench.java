package fr.paladium.palamixins.mixin.common.container;

import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({ContainerWorkbench.class})
public interface MixinContainerWorkbench {
  @Accessor("worldObj")
  World getWorld();
  
  @Accessor("posX")
  int getPosX();
  
  @Accessor("posY")
  int getPosY();
  
  @Accessor("posZ")
  int getPosZ();
}


/* Location:              E:\Paladium\!\fr\paladium\palamixins\mixin\common\container\MixinContainerWorkbench.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */