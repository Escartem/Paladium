package fr.paladium.palamod.modules.palaboss.common.attacks;

import com.google.gson.JsonObject;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase;
import fr.paladium.palamod.modules.palaboss.common.entity.projectiles.EntityGear;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class SpawnSbireAttack extends DistancedAttack {
  public static final String NAME = "spawnSbires";
  
  private transient AttackParamEntry sbireType;
  
  private transient AttackParamEntry minSbiresCount;
  
  private transient AttackParamEntry maxSbiresCount;
  
  private transient JsonObject jsonObject;
  
  public SpawnSbireAttack(JsonObject jsonObject) {
    super("spawnSbires");
    this.jsonObject = jsonObject;
    registerParam(this
        .sbireType = new AttackParamEntry("sbireType", EntityGear.class.getName()));
    registerParam(this.minSbiresCount = new AttackParamEntry("minSbiresCount", Integer.valueOf(1)));
    registerParam(this.maxSbiresCount = new AttackParamEntry("maxSbiresCount", Integer.valueOf(5)));
  }
  
  public boolean isRunning() {
    return false;
  }
  
  public JsonObject getJsonObject() {
    return this.jsonObject;
  }
  
  public void execute(World world, EntityBossBase entityHost, EntityLivingBase toAttackEntity) {
    int minSbireCount = this.minSbiresCount.getValueAsPrimitive().getAsInt();
    int maxSbireCount = this.maxSbiresCount.getValueAsPrimitive().getAsInt();
    int sbireCount = world.field_73012_v.nextInt(maxSbireCount - minSbireCount) + minSbireCount;
    for (int i = 0; i <= sbireCount; i++) {
      Entity entity = EntityList.func_75620_a(this.sbireType.getValueAsPrimitive().getAsString(), world);
      Vec3 playerLookVector = entityHost.func_70040_Z();
      double x = entityHost.field_70165_t;
      double z = entityHost.field_70161_v;
      double y = entityHost.field_70163_u;
      entity.func_70107_b(x, y, z);
      world.func_72838_d(entity);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\attacks\SpawnSbireAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */