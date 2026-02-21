package fr.paladium.palamod.modules.back2future.entities;

import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.back2future.entities.ai.EntityAIMoveToBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.world.World;

class AIRaidFarm extends EntityAIMoveToBlock {
  private boolean field_179498_d;
  
  private boolean field_179499_e = false;
  
  public AIRaidFarm() {
    super((EntityCreature)EntityRabbit.this, 0.699999988079071D, 16);
  }
  
  public boolean func_75250_a() {
    if (this.runDelay <= 0) {
      if (!EntityRabbit.this.field_70170_p.func_82736_K().func_82766_b("mobGriefing"))
        return false; 
      this.field_179499_e = false;
      this.field_179498_d = EntityRabbit.access$400(EntityRabbit.this);
    } 
    return super.func_75250_a();
  }
  
  public boolean func_75253_b() {
    return (this.field_179499_e && super.func_75253_b());
  }
  
  public void func_75246_d() {
    super.func_75246_d();
    EntityRabbit.this.func_70671_ap().func_75650_a(this.destinationBlock.getX() + 0.5D, (this.destinationBlock.getY() + 1), this.destinationBlock
        .getZ() + 0.5D, 10.0F, EntityRabbit.this.func_70646_bf());
    if (getIsAboveDestination()) {
      World world = EntityRabbit.this.field_70170_p;
      BlockPos blockpos = this.destinationBlock.up();
      Block block = world.func_147439_a(blockpos.getX(), blockpos.getY(), blockpos.getZ());
      int meta = world.func_72805_g(blockpos.getX(), blockpos.getY(), blockpos.getZ());
      if (this.field_179499_e && block instanceof net.minecraft.block.BlockCarrot && meta >= 7) {
        world.func_147480_a(blockpos.getX(), blockpos.getY(), blockpos.getZ(), false);
        EntityRabbit.access$502(EntityRabbit.this, 100);
      } 
      this.field_179499_e = false;
      this.runDelay = 10;
    } 
  }
  
  protected boolean shouldMoveTo(World world, BlockPos pos) {
    pos = pos.up();
    Block block = world.func_147439_a(pos.getX(), pos.getY(), pos.getZ());
    int meta = world.func_72805_g(pos.getX(), pos.getY(), pos.getZ());
    if (block instanceof net.minecraft.block.BlockCarrot && meta >= 7 && this.field_179498_d && !this.field_179499_e) {
      this.field_179499_e = true;
      return true;
    } 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\EntityRabbit$AIRaidFarm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */