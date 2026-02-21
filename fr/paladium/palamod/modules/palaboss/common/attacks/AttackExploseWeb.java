package fr.paladium.palamod.modules.palaboss.common.attacks;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.palaboss.PPalaBoss;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase;
import fr.paladium.palamod.modules.palaboss.common.entity.projectiles.EntityArachnaWeb;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class AttackExploseWeb extends DistancedAttack {
  public static final String NAME = "exploseWeb";
  
  private transient AttackParamEntry damage;
  
  private transient JsonObject jsonObject;
  
  public AttackExploseWeb(JsonObject jsonObject) {
    super("exploseWeb");
    this.jsonObject = jsonObject;
    registerParam(this.damage = new AttackParamEntry("attackDamage", Double.valueOf(10.0D)));
  }
  
  public boolean isRunning() {
    return false;
  }
  
  public JsonObject getJsonObject() {
    return this.jsonObject;
  }
  
  public void execute(final World world, final EntityBossBase entityHost, EntityLivingBase toAttackEntity) {
    JsonPrimitive damagePrimitive = (JsonPrimitive)this.damage.getValue();
    final float damage = damagePrimitive.getAsFloat();
    for (int i = 0; i < 5 + world.field_73012_v.nextInt(15); i++) {
      int rotationYaw = world.field_73012_v.nextInt(180) + -90;
      rotationYaw = -rotationYaw;
      EntityArachnaWeb entityArachnaWeb = new EntityArachnaWeb(world, (EntityLivingBase)entityHost, toAttackEntity, 0.3F, (14 - world.field_73013_u.func_151525_a() * 4), rotationYaw, -(60 + world.field_73012_v.nextInt(30)));
      entityArachnaWeb.setDamage(damage);
      world.func_72838_d((Entity)entityArachnaWeb);
    } 
    (new Thread(new Runnable() {
          public void run() {
            int offset = 25;
            AxisAlignedBB scanAbove = AxisAlignedBB.func_72330_a(entityHost.field_70165_t - offset, entityHost.field_70163_u - offset, entityHost.field_70161_v - offset, entityHost.field_70165_t + offset, entityHost.field_70163_u + offset, entityHost.field_70161_v + offset);
            List entities = entityHost.field_70170_p.func_72872_a(EntityLivingBase.class, scanAbove);
            for (Object entityObject : entities) {
              EntityLivingBase entity = (EntityLivingBase)entityObject;
              if (entity instanceof fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityArachna)
                continue; 
              entity.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)entityHost), damage);
              entity.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 100, 5, true));
              entity.func_70690_d(new PotionEffect(Potion.field_76440_q.field_76415_H, 100, 5, true));
              entity.func_70024_g((
                  -MathHelper.func_76126_a(entityHost.field_70177_z * 3.1415927F / 180.0F) * 2.0F * 0.5F), 0.1D, (
                  
                  MathHelper.func_76134_b(entityHost.field_70177_z * 3.1415927F / 180.0F) * 2.0F * 0.5F));
              entity.field_70181_x += 0.5000000059604645D;
            } 
            for (int i = 0; i < world.field_73012_v.nextInt(25); i++) {
              int x = (int)(entityHost.field_70165_t + ((world.field_73012_v.nextInt(2) == 0) ? (-world.field_73012_v.nextInt(20) - 7) : (world.field_73012_v.nextInt(20) + 7)));
              int z = (int)(entityHost.field_70161_v + ((world.field_73012_v.nextInt(2) == 0) ? (-world.field_73012_v.nextInt(20) - 7) : (world.field_73012_v.nextInt(20) + 7)));
              int y = world.func_72976_f(x, z);
              if (EventUtils.canInteract(entityHost.field_70170_p.func_72890_a((Entity)entityHost, 50.0D), x, y, z))
                world.func_147449_b(x, y, z, (Block)PPalaBoss.poisonousWeb); 
            } 
          }
        })).start();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\attacks\AttackExploseWeb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */