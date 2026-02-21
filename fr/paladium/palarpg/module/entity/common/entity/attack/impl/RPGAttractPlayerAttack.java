package fr.paladium.palarpg.module.entity.common.entity.attack.impl;

import fr.paladium.palaboss.common.entity.EntityBossMob;
import fr.paladium.palarpg.module.entity.common.entity.attack.ARPGBaseAttack;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import fr.paladium.palarpg.module.entity.server.loader.data.util.RPGAttack;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;

public class RPGAttractPlayerAttack extends ARPGBaseAttack<RPGMobEntity> {
  public static final String ID = "ATTRACT_PLAYER";
  
  public RPGAttractPlayerAttack() {}
  
  private static final Map<Integer, Long> ACTIVE_MAP = new HashMap<>();
  
  private double strength;
  
  public RPGAttractPlayerAttack(RPGAttack attack, RPGMobEntity entity) {
    super(attack);
    setEntity((EntityBossMob)entity);
    this.strength = ((Double)getData("strength", Double.valueOf(0.01D))).doubleValue();
  }
  
  public boolean canPerform() {
    if (((RPGMobEntity)getEntity()).field_70170_p.field_72995_K || ((RPGMobEntity)getEntity()).getAnimated().isDeathAnimation() || !(((RPGMobEntity)getEntity()).func_70638_az() instanceof EntityPlayerMP) || !super.canPerform())
      return false; 
    if (((RPGMobEntity)getEntity()).func_70638_az() != null && !((RPGMobEntity)getEntity()).func_70635_at().func_75522_a((Entity)((RPGMobEntity)getEntity()).func_70638_az()))
      return false; 
    if (ACTIVE_MAP.containsKey(Integer.valueOf(((RPGMobEntity)getEntity()).func_145782_y()))) {
      long lastUsed = ((Long)ACTIVE_MAP.get(Integer.valueOf(((RPGMobEntity)getEntity()).func_145782_y()))).longValue();
      if (System.currentTimeMillis() - lastUsed < 500L)
        return false; 
    } 
    return true;
  }
  
  public void perform() {
    EntityPlayerMP target = (EntityPlayerMP)((RPGMobEntity)getEntity()).func_70638_az();
    if (target == null)
      return; 
    double dx = ((RPGMobEntity)getEntity()).field_70165_t - target.field_70165_t;
    double dy = ((RPGMobEntity)getEntity()).field_70163_u + ((RPGMobEntity)getEntity()).func_70047_e() - target.field_70163_u + target.func_70047_e();
    double dz = ((RPGMobEntity)getEntity()).field_70161_v - target.field_70161_v;
    double len = Math.sqrt(dx * dx + dy * dy + dz * dz);
    if (len > 1.0D) {
      double nx = dx / len;
      double nz = dz / len;
      target.field_70160_al = true;
      target.field_70159_w += nx * this.strength;
      target.field_70179_y += nz * this.strength;
      target.field_71135_a.func_147359_a((Packet)new S12PacketEntityVelocity((Entity)target));
      ACTIVE_MAP.put(Integer.valueOf(((RPGMobEntity)getEntity()).func_145782_y()), Long.valueOf(System.currentTimeMillis()));
    } 
  }
  
  public String getID() {
    return "ATTRACT_PLAYER";
  }
  
  public ARPGBaseAttack<RPGMobEntity> copy(RPGAttack attack, RPGMobEntity entity) {
    return new RPGAttractPlayerAttack(attack, entity);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\attack\impl\RPGAttractPlayerAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */