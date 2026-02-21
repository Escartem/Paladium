package fr.paladium.palamod.modules.palaboss.common.attacks;

import com.google.gson.JsonObject;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.bukkit.util.Vector;

public class WebLaunchAttack extends ShortRangeAttack {
  public static final String NAME = "webLaunch";
  
  private transient JsonObject jsonObject;
  
  public WebLaunchAttack(JsonObject jsonObject) {
    super("webLaunch");
    this.jsonObject = jsonObject;
  }
  
  public JsonObject getJsonObject() {
    return this.jsonObject;
  }
  
  public void execute(World world, EntityBossBase entityHost, EntityLivingBase toAttackEntity) {
    if (!(toAttackEntity instanceof EntityPlayer))
      return; 
    spawnParticule(world, (int)entityHost.field_70165_t, (int)entityHost.field_70163_u, (int)entityHost.field_70161_v, toAttackEntity);
    int i;
    for (i = -3; i < 4; i++) {
      for (int j = -3; j < 4; j++) {
        for (int k = -3; k < 4; k++) {
          if (EventUtils.canInteract((EntityPlayer)toAttackEntity, (int)toAttackEntity.field_70165_t + i, (int)toAttackEntity.field_70163_u + j, (int)toAttackEntity.field_70161_v + k) && 
            
            EventUtils.isAir(world, (int)toAttackEntity.field_70165_t + i, (int)toAttackEntity.field_70163_u + j, (int)toAttackEntity.field_70161_v + k))
            world.func_147449_b((int)toAttackEntity.field_70165_t + i, (int)toAttackEntity.field_70163_u + j, (int)toAttackEntity.field_70161_v + k, (Block)BlocksRegister.WEB_CUSTOM); 
        } 
      } 
    } 
    for (i = 0; i < 7; i++) {
      EntityCaveSpider spider = new EntityCaveSpider(world);
      spider.func_70107_b(toAttackEntity.field_70165_t, toAttackEntity.field_70163_u, toAttackEntity.field_70161_v);
      world.func_72838_d((Entity)spider);
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\attacks\WebLaunchAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */