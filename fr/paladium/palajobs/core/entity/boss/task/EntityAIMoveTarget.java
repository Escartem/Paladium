package fr.paladium.palajobs.core.entity.boss.task;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathEntity;

public class EntityAIMoveTarget extends EntityAIBase {
  private EntityCreature attacker;
  
  private PathEntity entityPathEntity;
  
  private double lastPosX;
  
  private double lastPosY;
  
  private double lastPosZ;
  
  private int updateTick;
  
  public EntityAIMoveTarget(EntityCreature entity) {
    this.attacker = entity;
    func_75248_a(3);
  }
  
  public boolean func_75250_a() {
    EntityLivingBase entitylivingbase = this.attacker.func_70638_az();
    if (entitylivingbase == null)
      return false; 
    if (!entitylivingbase.func_70089_S())
      return false; 
    if (--this.updateTick <= 0) {
      double size = (this.attacker.field_70121_D.field_72336_d - this.attacker.field_70121_D.field_72340_a) * 2.0D;
      if (this.attacker.func_70032_d((Entity)entitylivingbase) < size) {
        this.updateTick = 4 + this.attacker.func_70681_au().nextInt(7);
        this.attacker.func_70661_as().func_75499_g();
        return false;
      } 
      this.entityPathEntity = this.attacker.func_70661_as().func_75494_a((Entity)entitylivingbase);
      this.updateTick = 4 + this.attacker.func_70681_au().nextInt(7);
      return (this.entityPathEntity != null);
    } 
    return true;
  }
  
  public boolean func_75253_b() {
    EntityLivingBase entitylivingbase = this.attacker.func_70638_az();
    return (entitylivingbase == null) ? false : (!entitylivingbase.func_70089_S() ? false : (!this.attacker.func_70661_as().func_75500_f()));
  }
  
  public void func_75249_e() {
    this.attacker.func_70661_as().func_75484_a(this.entityPathEntity, 1.0D);
    this.updateTick = 0;
  }
  
  public void func_75251_c() {
    this.attacker.func_70661_as().func_75499_g();
  }
  
  public void func_75246_d() {
    EntityLivingBase entitylivingbase = this.attacker.func_70638_az();
    this.attacker.func_70671_ap().func_75651_a((Entity)entitylivingbase, 30.0F, 30.0F);
    double distance = this.attacker.func_70092_e(entitylivingbase.field_70165_t, entitylivingbase.field_70121_D.field_72338_b, entitylivingbase.field_70161_v);
    this.updateTick--;
    if (this.attacker.func_70635_at().func_75522_a((Entity)entitylivingbase) && this.updateTick <= 0 && ((this.lastPosX == 0.0D && this.lastPosY == 0.0D && this.lastPosZ == 0.0D) || entitylivingbase.func_70092_e(this.lastPosX, this.lastPosY, this.lastPosZ) >= 1.0D || this.attacker.func_70681_au().nextFloat() < 0.05F)) {
      this.lastPosX = entitylivingbase.field_70165_t;
      this.lastPosY = entitylivingbase.field_70121_D.field_72338_b;
      this.lastPosZ = entitylivingbase.field_70161_v;
      this.updateTick = 4 + this.attacker.func_70681_au().nextInt(7);
      if (distance > 1024.0D) {
        this.updateTick += 10;
      } else if (distance > 256.0D) {
        this.updateTick += 5;
      } 
      if (!this.attacker.func_70661_as().func_75497_a((Entity)entitylivingbase, 1.0D))
        this.updateTick += 15; 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\entity\boss\task\EntityAIMoveTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */