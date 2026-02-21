package fr.paladium.palamod.modules.palaboss.common.attacks;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import org.bukkit.util.Vector;

public class ElectrocutionAttack extends ShortRangeAttack {
  public static final String NAME = "electrocution";
  
  private transient AttackParamEntry attackDamage;
  
  private transient AttackParamEntry attackRange;
  
  private transient JsonObject jsonObject;
  
  public ElectrocutionAttack(JsonObject jsonObject) {
    super("electrocution");
    this.jsonObject = jsonObject;
    registerParam(this.attackDamage = new AttackParamEntry("attackDamage", Integer.valueOf(90)));
    registerParam(this.attackRange = new AttackParamEntry("attackRange", Integer.valueOf(15)));
  }
  
  public JsonObject getJsonObject() {
    return this.jsonObject;
  }
  
  public void execute(World world, EntityBossBase entityHost, EntityLivingBase toAttackEntity) {
    JsonPrimitive damagePrimitive = (JsonPrimitive)this.attackDamage.getValue();
    float damage = damagePrimitive.getAsFloat();
    JsonPrimitive rangePrimitive = (JsonPrimitive)this.attackRange.getValue();
    float range = rangePrimitive.getAsFloat();
    AxisAlignedBB scanAbove = AxisAlignedBB.func_72330_a(entityHost.field_70165_t - range, entityHost.field_70163_u - range, entityHost.field_70161_v - range, entityHost.field_70165_t + range, entityHost.field_70163_u + range, entityHost.field_70161_v + range);
    List entities = entityHost.field_70170_p.func_72872_a(EntityPlayer.class, scanAbove);
    for (Object entityObject : entities) {
      EntityLivingBase entity = (EntityLivingBase)entityObject;
      if (entity instanceof fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityUlcan)
        continue; 
      spawnParticule(world, (int)entityHost.field_70165_t, (int)entityHost.field_70163_u + 2, (int)entityHost.field_70161_v, entity);
      entity.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)entityHost), damage);
    } 
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\attacks\ElectrocutionAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */