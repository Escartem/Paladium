package fr.paladium.palamod.mixins.world.usebug;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({EntityFallingBlock.class})
public abstract class IMixinEntityFallingBlock extends Entity {
  public IMixinEntityFallingBlock(World p_i1582_1_) {
    super(p_i1582_1_);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\worl\\usebug\IMixinEntityFallingBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */