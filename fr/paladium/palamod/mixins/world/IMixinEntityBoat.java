package fr.paladium.palamod.mixins.world;

import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.init.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({EntityBoat.class})
public abstract class IMixinEntityBoat extends Entity {
  public IMixinEntityBoat(World p_i1582_1_) {
    super(p_i1582_1_);
  }
  
  public void func_70030_z() {
    BlockPos pos = new BlockPos(this);
    if (!this.field_70170_p.field_72995_K && 
      !this.field_70170_p.func_147437_c(pos.getX(), pos.getY() + 1, pos.getZ())) {
      if (this.field_70153_n != null)
        this.field_70153_n.func_70078_a(null); 
      func_145778_a(Items.field_151124_az, 1, 0.0F);
      func_70106_y();
    } 
    super.func_70030_z();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\mixins\world\IMixinEntityBoat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */