package fr.paladium.palamod.modules.palaboss.common.attacks;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase;
import fr.paladium.palamod.modules.palaboss.common.entity.projectiles.EntityBossProjectiles;
import fr.paladium.palamod.modules.palaboss.common.entity.projectiles.EntityIceProjectile;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class IceProjectileAttack extends DistancedAttack {
  public static final String NAME = "iceProjectile";
  
  private transient AttackParamEntry damage;
  
  private transient JsonObject jsonObject;
  
  public IceProjectileAttack(JsonObject jsonObject) {
    super("iceProjectile");
    this.jsonObject = jsonObject;
    registerParam(this.damage = new AttackParamEntry("attackDamage", Double.valueOf(10.0D)));
  }
  
  public boolean isRunning() {
    return false;
  }
  
  public JsonObject getJsonObject() {
    return this.jsonObject;
  }
  
  public void execute(World world, EntityBossBase entityHost, EntityLivingBase toAttackEntity) {
    JsonPrimitive damagePrimitive = (JsonPrimitive)this.damage.getValue();
    float damage = damagePrimitive.getAsFloat();
    int offset = 20;
    AxisAlignedBB scanAbove = AxisAlignedBB.func_72330_a(entityHost.field_70165_t - offset, entityHost.field_70163_u - offset, entityHost.field_70161_v - offset, entityHost.field_70165_t + offset, entityHost.field_70163_u + offset, entityHost.field_70161_v + offset);
    List entities = entityHost.field_70170_p.func_72872_a(EntityPlayer.class, scanAbove);
    for (Object entityObject : entities) {
      EntityLivingBase entity = (EntityLivingBase)entityObject;
      if (entity instanceof fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityTobalt)
        continue; 
      Entity projectile = null;
      EntityIceProjectile entityIceProjectile = new EntityIceProjectile(world, (EntityLivingBase)entityHost, entity, Float.valueOf(1.6F), Float.valueOf((14 - world.field_73013_u.func_151525_a() * 4)));
      if (entityIceProjectile instanceof EntityBossProjectiles)
        ((EntityBossProjectiles)entityIceProjectile).setDamage(damage); 
      world.func_72838_d((Entity)entityIceProjectile);
      entity.func_70097_a(DamageSource.field_76376_m, damage);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\attacks\IceProjectileAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */