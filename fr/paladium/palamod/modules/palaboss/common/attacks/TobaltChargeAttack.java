package fr.paladium.palamod.modules.palaboss.common.attacks;

import com.google.gson.JsonObject;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityTobalt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class TobaltChargeAttack extends DistancedAttack {
  public static final String NAME = "tobaltCharge";
  
  private transient AttackParamEntry attackDamage;
  
  private transient AttackParamEntry knockback;
  
  private transient JsonObject jsonObject;
  
  private Entity entityHost;
  
  public TobaltChargeAttack(JsonObject jsonObject) {
    super("tobaltCharge");
    this.jsonObject = jsonObject;
    registerParam(this.attackDamage = new AttackParamEntry("attackDamage", Integer.valueOf(10)));
    registerParam(this.knockback = new AttackParamEntry("knockback", Integer.valueOf(1)));
  }
  
  public JsonObject getJsonObject() {
    return this.jsonObject;
  }
  
  public void execute(World world, EntityBossBase entityHost, EntityLivingBase toAttackEntity) {
    this.entityHost = (Entity)entityHost;
    EntityTobalt tobalt = (EntityTobalt)entityHost;
    tobalt.setCharge(true);
    tobalt.updateDatas();
  }
  
  public boolean isRunning() {
    EntityTobalt tobalt = (EntityTobalt)this.entityHost;
    if (tobalt.isInCharge() || tobalt.isStunt())
      return true; 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\attacks\TobaltChargeAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */