package fr.paladium.palarpg.module.entity.common.entity.attack.impl;

import fr.paladium.palaboss.common.entity.EntityBossMob;
import fr.paladium.palaboss.common.entity.ai.attack.selector.AttackEntitySelector;
import fr.paladium.palarpg.module.entity.common.entity.attack.ARPGBaseAttack;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import fr.paladium.palarpg.module.entity.server.loader.data.util.RPGAttack;
import java.util.List;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;

public class RPGLazerForwardAttack extends ARPGBaseAttack<RPGMobEntity> {
  public static final String ID = "LAZER_FORWARD";
  
  public String toString() {
    getClass();
    return "RPGLazerForwardAttack(boxSize=" + 0.25D + ", selector=" + this.selector + ")";
  }
  
  private final double boxSize = 0.25D;
  
  private IEntitySelector selector;
  
  public RPGLazerForwardAttack(RPGAttack attack, RPGMobEntity entity) {
    super(attack);
    setEntity((EntityBossMob)entity);
    this.selector = (IEntitySelector)new AttackEntitySelector(e -> {
          if (!(e instanceof EntityPlayer))
            return Boolean.valueOf(false); 
          EntityPlayer player = (EntityPlayer)e;
          return (!player.func_70089_S() || player.field_71075_bZ.field_75102_a) ? Boolean.valueOf(false) : Boolean.valueOf(true);
        });
  }
  
  public boolean canPerform() {
    if (!super.canPerform() || ((RPGMobEntity)getEntity()).func_70638_az() == null || ((RPGMobEntity)getEntity()).func_70032_d((Entity)((RPGMobEntity)getEntity()).func_70638_az()) > getAttack().getRange())
      return false; 
    return true;
  }
  
  public void perform() {
    super.perform();
    EntityLivingBase target = ((RPGMobEntity)getEntity()).func_70638_az();
    if (target == null)
      return; 
    double d0 = target.field_70165_t - ((RPGMobEntity)getEntity()).field_70165_t;
    double d2 = target.field_70161_v - ((RPGMobEntity)getEntity()).field_70161_v;
    float f2 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
    ((RPGMobEntity)getEntity()).field_70126_B = ((RPGMobEntity)getEntity()).field_70758_at = ((RPGMobEntity)getEntity()).field_70759_as = ((RPGMobEntity)getEntity()).field_70177_z = f2 % 360.0F;
    if (getExecutingTick() > 55 && getExecutingTick() < 130 && getExecutingTick() % 5 == 0) {
      Vec3 start = Vec3.func_72443_a(((RPGMobEntity)getEntity()).field_70165_t, ((RPGMobEntity)getEntity()).field_70163_u + ((RPGMobEntity)getEntity()).field_70131_O / 2.0D, ((RPGMobEntity)getEntity()).field_70161_v);
      Vec3 look = ((RPGMobEntity)getEntity()).func_70040_Z().func_72432_b();
      List<EntityPlayer> players = getEntitiesAround(EntityPlayer.class, getAttack().getRange(), this.selector);
      double i;
      for (i = 1.5D; i < getAttack().getRange(); i += 0.5D) {
        double x = start.field_72450_a + look.field_72450_a * i;
        double y = start.field_72448_b + look.field_72448_b * i;
        double z = start.field_72449_c + look.field_72449_c * i;
        getClass();
        getClass();
        getClass();
        getClass();
        getClass();
        getClass();
        AxisAlignedBB aabb = AxisAlignedBB.func_72330_a(x - 0.25D, y - 0.25D, z - 0.25D, x + 0.25D, y + 0.25D, z + 0.25D);
        for (EntityPlayer player : players) {
          if (player.field_70737_aN <= 0 && player.field_70121_D.func_72326_a(aabb))
            damage((Entity)player); 
        } 
      } 
    } 
  }
  
  public String getID() {
    return "LAZER_FORWARD";
  }
  
  public ARPGBaseAttack<RPGMobEntity> copy(RPGAttack attack, RPGMobEntity entity) {
    return new RPGLazerForwardAttack(attack, entity);
  }
  
  public RPGLazerForwardAttack() {}
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\attack\impl\RPGLazerForwardAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */