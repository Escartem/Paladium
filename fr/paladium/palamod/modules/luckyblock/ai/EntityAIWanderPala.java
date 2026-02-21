package fr.paladium.palamod.modules.luckyblock.ai;

import fr.paladium.palamod.modules.luckyblock.entity.EntityMobPaladium;
import fr.paladium.palamod.modules.luckyblock.utils.AITool;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class EntityAIWanderPala extends EntityAIBase {
  private EntityCreature entity;
  
  private double xPosition;
  
  private double yPosition;
  
  private double zPosition;
  
  private double speed;
  
  private int executionChance;
  
  private boolean mustUpdate;
  
  public EntityAIWanderPala(EntityCreature creatureIn, double speedIn) {
    this(creatureIn, speedIn, 120);
  }
  
  public EntityAIWanderPala(EntityCreature creatureIn, double speedIn, int chance) {
    this.entity = creatureIn;
    this.speed = speedIn;
    this.executionChance = chance;
    func_75248_a(1);
  }
  
  public boolean func_75250_a() {
    if (this.entity instanceof EntityMobPaladium && ((EntityMobPaladium)this.entity).isCeased())
      return false; 
    if (this.entity.field_70153_n != null && !(this.entity instanceof EntityMobPaladium))
      return false; 
    if (!this.mustUpdate) {
      if (this.entity.func_70654_ax() >= 100)
        return false; 
      if (this.entity.func_70681_au().nextInt(this.executionChance) != 0)
        return false; 
    } 
    Vec3 vec3 = RandomPositionGenerator.func_75463_a(this.entity, 10, 12);
    if (vec3 != null && this.entity instanceof EntityMobPaladium && this.entity
      .func_70661_as() instanceof fr.paladium.palamod.modules.luckyblock.pathfinding.PathNavigateFlyer) {
      int distToFloor = AITool.distanceToFloor((Entity)this.entity);
      int finalYHeight = distToFloor + MathHelper.func_76128_c(vec3.field_72448_b - this.entity.field_70163_u);
      if (finalYHeight < ((EntityMobPaladium)this.entity).minFlyingHeight())
        return false; 
      if (finalYHeight > ((EntityMobPaladium)this.entity).maxFlyingHeight())
        return false; 
    } 
    if (vec3 == null)
      return false; 
    this.xPosition = vec3.field_72450_a;
    this.yPosition = vec3.field_72448_b;
    this.zPosition = vec3.field_72449_c;
    this.mustUpdate = false;
    return true;
  }
  
  public boolean func_75253_b() {
    return (!this.entity.func_70661_as().func_75500_f() && this.entity.field_70153_n == null);
  }
  
  public void func_75249_e() {
    this.entity.func_70661_as().func_75492_a(this.xPosition, this.yPosition, this.zPosition, this.speed);
  }
  
  public void makeUpdate() {
    this.mustUpdate = true;
  }
  
  public void setExecutionChance(int newchance) {
    this.executionChance = newchance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\ai\EntityAIWanderPala.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */