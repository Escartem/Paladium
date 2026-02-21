package fr.paladium.palamod.modules.luckyblock.ai;

import fr.paladium.palamod.modules.luckyblock.entity.EntityFakePoweredCreeper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIFakePoweredCreeperSwell extends EntityAIBase {
  EntityFakePoweredCreeper swellingCreeper;
  
  EntityLivingBase creeperAttackTarget;
  
  private static final String __OBFID = "CL_00001614";
  
  public EntityAIFakePoweredCreeperSwell(EntityFakePoweredCreeper p_i1655_1_) {
    this.swellingCreeper = p_i1655_1_;
    func_75248_a(1);
  }
  
  public boolean func_75250_a() {
    EntityLivingBase entitylivingbase = this.swellingCreeper.func_70638_az();
    return (this.swellingCreeper.getCreeperState() > 0 || (entitylivingbase != null && this.swellingCreeper
      .func_70068_e((Entity)entitylivingbase) < 9.0D));
  }
  
  public void func_75249_e() {
    this.swellingCreeper.func_70661_as().func_75499_g();
    this.creeperAttackTarget = this.swellingCreeper.func_70638_az();
  }
  
  public void func_75251_c() {
    this.creeperAttackTarget = null;
  }
  
  public void func_75246_d() {
    if (this.creeperAttackTarget == null) {
      this.swellingCreeper.setCreeperState(-1);
    } else if (this.swellingCreeper.func_70068_e((Entity)this.creeperAttackTarget) > 49.0D) {
      this.swellingCreeper.setCreeperState(-1);
    } else if (!this.swellingCreeper.func_70635_at().func_75522_a((Entity)this.creeperAttackTarget)) {
      this.swellingCreeper.setCreeperState(-1);
    } else {
      this.swellingCreeper.setCreeperState(1);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\ai\EntityAIFakePoweredCreeperSwell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */