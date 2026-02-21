package fr.paladium.palamod.modules.palaboss.common.attacks;

import com.google.gson.JsonObject;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityArachna;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class ArachnaRushAttack extends DistancedAttack {
  public static final String NAME = "arachnaRush";
  
  private transient JsonObject jsonObject;
  
  private Entity entityHost;
  
  public ArachnaRushAttack(JsonObject jsonObject) {
    super("arachnaRush");
    this.jsonObject = jsonObject;
  }
  
  public JsonObject getJsonObject() {
    return this.jsonObject;
  }
  
  public void execute(World world, EntityBossBase entityHost, EntityLivingBase toAttackEntity) {
    this.entityHost = (Entity)entityHost;
    EntityArachna arachna = (EntityArachna)entityHost;
    arachna.setCharge(true);
  }
  
  public boolean isRunning() {
    EntityArachna arachna = (EntityArachna)this.entityHost;
    if (arachna.isInCharge())
      return true; 
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\attacks\ArachnaRushAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */