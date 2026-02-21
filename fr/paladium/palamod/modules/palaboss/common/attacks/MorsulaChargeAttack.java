package fr.paladium.palamod.modules.palaboss.common.attacks;

import com.google.gson.JsonObject;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityMorsula;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class MorsulaChargeAttack extends DistancedAttack {
  public static final String NAME = "morsulaCharge";
  
  private transient AttackParamEntry attackDamage;
  
  private transient AttackParamEntry knockback;
  
  private transient JsonObject jsonObject;
  
  private Entity entityHost;
  
  public MorsulaChargeAttack(JsonObject jsonObject) {
    super("morsulaCharge");
    this.jsonObject = jsonObject;
    registerParam(this.attackDamage = new AttackParamEntry("attackDamage", Integer.valueOf(75)));
    registerParam(this.knockback = new AttackParamEntry("knockback", Integer.valueOf(4)));
  }
  
  public JsonObject getJsonObject() {
    return this.jsonObject;
  }
  
  public void execute(World world, EntityBossBase entityHost, EntityLivingBase toAttackEntity) {
    this.entityHost = (Entity)entityHost;
    EntityMorsula morsula = (EntityMorsula)entityHost;
    morsula.setCharge(true);
  }
  
  public boolean isRunning() {
    EntityMorsula morsula = (EntityMorsula)this.entityHost;
    if (morsula.isInCharge())
      return true; 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\attacks\MorsulaChargeAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */