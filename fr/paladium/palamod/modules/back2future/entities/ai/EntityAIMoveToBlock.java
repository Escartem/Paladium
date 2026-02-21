package fr.paladium.palamod.modules.back2future.entities.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.world.World;

public abstract class EntityAIMoveToBlock extends EntityAIBase {
  private final EntityCreature theEntity;
  
  private final double movementSpeed;
  
  protected int runDelay;
  
  private int timeoutCounter;
  
  private int field_179490_f;
  
  protected BlockPos destinationBlock;
  
  private boolean isAboveDestination;
  
  private int searchLength;
  
  public EntityAIMoveToBlock(EntityCreature creature, double speedIn, int length) {
    this.destinationBlock = BlockPos.ORIGIN;
    this.theEntity = creature;
    this.movementSpeed = speedIn;
    this.searchLength = length;
    func_75248_a(5);
  }
  
  public boolean func_75250_a() {
    if (this.runDelay > 0) {
      this.runDelay--;
      return false;
    } 
    this.runDelay = 200 + this.theEntity.func_70681_au().nextInt(200);
    return searchForDestination();
  }
  
  public boolean func_75253_b() {
    return (this.timeoutCounter >= -this.field_179490_f && this.timeoutCounter <= 1200 && 
      shouldMoveTo(this.theEntity.field_70170_p, this.destinationBlock));
  }
  
  public void func_75249_e() {
    this.theEntity.func_70661_as().func_75492_a(this.destinationBlock.getX() + 0.5D, (this.destinationBlock
        .getY() + 1), this.destinationBlock.getZ() + 0.5D, this.movementSpeed);
    this.timeoutCounter = 0;
    this.field_179490_f = this.theEntity.func_70681_au().nextInt(this.theEntity.func_70681_au().nextInt(1200) + 1200) + 1200;
  }
  
  public void func_75251_c() {}
  
  public void func_75246_d() {
    if (this.theEntity.func_70092_e(this.destinationBlock.up().getX(), this.destinationBlock.up().getY(), this.destinationBlock
        .up().getZ()) > 1.0D) {
      this.isAboveDestination = false;
      this.timeoutCounter++;
      if (this.timeoutCounter % 40 == 0)
        this.theEntity.func_70661_as().func_75492_a(this.destinationBlock.getX() + 0.5D, (this.destinationBlock
            .getY() + 1), this.destinationBlock.getZ() + 0.5D, this.movementSpeed); 
    } else {
      this.isAboveDestination = true;
      this.timeoutCounter--;
    } 
  }
  
  protected boolean getIsAboveDestination() {
    return this.isAboveDestination;
  }
  
  private boolean searchForDestination() {
    int i = this.searchLength;
    BlockPos blockpos = new BlockPos((Entity)this.theEntity);
    for (int j = 0; j <= 1; j = (j > 0) ? -j : (1 - j)) {
      for (int k = 0; k < i; k++) {
        int l;
        for (l = 0; l <= k; l = (l > 0) ? -l : (1 - l)) {
          int i1;
          for (i1 = (l < k && l > -k) ? k : 0; i1 <= k; i1 = (i1 > 0) ? -i1 : (1 - i1)) {
            BlockPos blockpos1 = blockpos.add(l, j - 1, i1);
            if (this.theEntity.func_110176_b(blockpos1.getX(), blockpos1.getY(), blockpos1.getZ()) && 
              shouldMoveTo(this.theEntity.field_70170_p, blockpos1)) {
              this.destinationBlock = blockpos1;
              return true;
            } 
          } 
        } 
      } 
    } 
    return false;
  }
  
  protected abstract boolean shouldMoveTo(World paramWorld, BlockPos paramBlockPos);
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\ai\EntityAIMoveToBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */