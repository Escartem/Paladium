package fr.paladium.palamod.modules.palaboss.common.attacks;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.bukkit.util.Vector;

public class SandAttack extends ShortRangeAttack {
  public static final String NAME = "sand";
  
  private transient AttackParamEntry attackDamage;
  
  private transient AttackParamEntry knockback;
  
  private transient JsonObject jsonObject;
  
  public SandAttack(JsonObject jsonObject) {
    super("sand");
    this.jsonObject = jsonObject;
    registerParam(this.attackDamage = new AttackParamEntry("attackDamage", Integer.valueOf(90)));
    registerParam(this.knockback = new AttackParamEntry("knockback", Integer.valueOf(2)));
  }
  
  public JsonObject getJsonObject() {
    return this.jsonObject;
  }
  
  public void execute(World world, EntityBossBase entityHost, EntityLivingBase toAttackEntity) {
    JsonPrimitive damagePrimitive = (JsonPrimitive)this.attackDamage.getValue();
    float damage = damagePrimitive.getAsFloat();
    JsonPrimitive knockbackPrimitive = (JsonPrimitive)this.knockback.getValue();
    float knockback = knockbackPrimitive.getAsFloat();
    spawnParticule(world, (int)entityHost.field_70165_t, (int)entityHost.field_70163_u + 2, (int)entityHost.field_70161_v, toAttackEntity);
    toAttackEntity.func_70024_g((
        -MathHelper.func_76126_a(entityHost.field_70177_z * 3.1415927F / 180.0F) * knockback * 0.5F), 0.1D, (
        
        MathHelper.func_76134_b(entityHost.field_70177_z * 3.1415927F / 180.0F) * knockback * 0.5F));
    toAttackEntity.field_70181_x += 0.4000000059604645D;
    toAttackEntity.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)entityHost), damage);
  }
  
  public void spawnParticule(World world, int x, int y, int z, EntityLivingBase target) {
    Vector p1 = new Vector(x, y, z);
    Vector p2 = new Vector(target.field_70165_t, target.field_70163_u, target.field_70161_v);
    Vector vector = p2.clone().subtract(p1).normalize().multiply(1);
    double length = 0.0D;
    while (true) {
      if (length < Math.sqrt((new BlockPos(x, y, z))
          .distanceSq(target.field_70165_t, target.field_70163_u, target.field_70161_v))) {
        EventUtils.spawnParticle(world, "reddust", p1.getX(), p1.getY(), p1.getZ(), 100, 0.0D);
        length++;
        p1.add(vector);
        continue;
      } 
      break;
    } 
  }
  
  public boolean isRunning() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\attacks\SandAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */