package fr.paladium.palarpg.module.entity.common.entity.attack.impl;

import fr.paladium.palaboss.common.entity.EntityBossMob;
import fr.paladium.palarpg.module.entity.common.entity.attack.ARPGBaseAttack;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import fr.paladium.palarpg.module.entity.server.loader.data.util.RPGAttack;
import java.util.List;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;

public class RPGNimbriaChargeAttack extends ARPGBaseAttack<RPGMobEntity> {
  private static final String ID = "NIMBRIA_CHARGE_ATTACK";
  
  private final PlayerEntitySelector playerSelector = new PlayerEntitySelector();
  
  private double minDistance;
  
  public RPGNimbriaChargeAttack(RPGAttack attack, RPGMobEntity entity) {
    super(attack);
    setEntity((EntityBossMob)entity);
    this.minDistance = ((Double)getData("minDistance", Double.valueOf(8.0D))).doubleValue();
  }
  
  public boolean canPerform() {
    return (((RPGMobEntity)getEntity()).func_70638_az() instanceof net.minecraft.entity.player.EntityPlayerMP && ((RPGMobEntity)getEntity()).func_70032_d((Entity)((RPGMobEntity)getEntity()).func_70638_az()) >= this.minDistance && super.canPerform());
  }
  
  public void perform() {
    super.perform();
    ((RPGMobEntity)getEntity()).field_70177_z = ((RPGMobEntity)getEntity()).field_70126_B;
    if (getExecutingTick() > 40 && getExecutingTick() < 105) {
      double speed = ((RPGMobEntity)getEntity()).func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e() * 2.0D;
      float yawRad = (float)Math.toRadians(((RPGMobEntity)getEntity()).field_70177_z);
      double dirX = -Math.sin(yawRad);
      double dirZ = Math.cos(yawRad);
      ((RPGMobEntity)getEntity()).func_70091_d(dirX * speed, 0.0D, dirZ * speed);
      List<EntityPlayer> entities = ((RPGMobEntity)getEntity()).field_70170_p.func_82733_a(EntityPlayer.class, ((RPGMobEntity)getEntity()).field_70121_D, this.playerSelector);
      entities.forEach(player -> damage((Entity)player));
    } 
  }
  
  public String getID() {
    return "NIMBRIA_CHARGE_ATTACK";
  }
  
  public ARPGBaseAttack<RPGMobEntity> copy(RPGAttack attack, RPGMobEntity entity) {
    return new RPGNimbriaChargeAttack(attack, entity);
  }
  
  public RPGNimbriaChargeAttack() {}
  
  private class PlayerEntitySelector implements IEntitySelector {
    public boolean func_82704_a(Entity entity) {
      if (!(entity instanceof EntityPlayer))
        return false; 
      EntityPlayer player = (EntityPlayer)entity;
      return (player.func_70089_S() && !player.field_71075_bZ.field_75102_a);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\attack\impl\RPGNimbriaChargeAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */