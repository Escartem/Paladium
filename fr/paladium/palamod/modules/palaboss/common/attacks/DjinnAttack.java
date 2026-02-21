package fr.paladium.palamod.modules.palaboss.common.attacks;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import cpw.mods.fml.common.FMLCommonHandler;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.back2future.entities.projectile.EntityCustomFallingBlock;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class DjinnAttack extends ShortRangeAttack {
  public static final String NAME = "djinn";
  
  private transient AttackParamEntry attackDamage;
  
  private transient AttackParamEntry attackRange;
  
  private transient JsonObject jsonObject;
  
  private EntityLivingBase host;
  
  private boolean execute;
  
  private boolean running = false;
  
  public DjinnAttack(JsonObject jsonObject) {
    super("djinn");
    this.jsonObject = jsonObject;
    registerParam(this.attackDamage = new AttackParamEntry("attackDamage", Integer.valueOf(40)));
    registerParam(this.attackRange = new AttackParamEntry("attackRange", Integer.valueOf(40)));
    MinecraftForge.EVENT_BUS.register(this);
    FMLCommonHandler.instance().bus().register(this);
  }
  
  public JsonObject getJsonObject() {
    return this.jsonObject;
  }
  
  public void execute(World world, final EntityBossBase entityHost, EntityLivingBase toAttackEntity) {
    this.host = (EntityLivingBase)entityHost;
    JsonPrimitive rangePrimitive = (JsonPrimitive)this.attackRange.getValue();
    final float range = rangePrimitive.getAsFloat();
    if (isRunning())
      return; 
    entityHost.field_70145_X = true;
    this.running = true;
    (new Thread(new Runnable() {
          public void run() {
            try {
              Thread.sleep(1000L);
              while (entityHost.field_70163_u < entityHost.field_70170_p.func_72976_f((int)entityHost.field_70165_t, (int)entityHost.field_70161_v))
                entityHost.field_70181_x = 0.5D; 
              entityHost.field_70181_x = 0.5D;
              entityHost.field_70145_X = false;
              DjinnAttack.this.earthquake((EntityLivingBase)entityHost, (int)range);
              DjinnAttack.this.running = false;
            } catch (Exception e) {
              e.printStackTrace();
            } 
          }
        })).start();
  }
  
  public void earthquake(EntityLivingBase player, float i, float rotationYaw, int count) {
    JsonPrimitive damagePrimitive = (JsonPrimitive)this.attackDamage.getValue();
    float damage = damagePrimitive.getAsFloat();
    double x = (count - count / 2) * Math.cos(Math.toRadians((i * (count / 2) + rotationYaw + 90.0F))) + player.field_70165_t;
    double z = (count - count / 2) * Math.sin(Math.toRadians((i * (count / 2) + rotationYaw + 90.0F))) + player.field_70161_v;
    BlockPos pos = new BlockPos(x, player.field_70163_u - 1.0D, z);
    EntityCustomFallingBlock falling = new EntityCustomFallingBlock(player.field_70170_p, (Entity)player, x, player.field_70163_u - 1.0D, z, 0.20000000298023224D, i * (count / 2) + rotationYaw + 90.0F, pos, (int)damage);
    if (!player.field_70170_p.field_72995_K && 
      !player.field_70170_p.field_72984_F.func_76322_c().toLowerCase().contains("tracker"))
      player.field_70170_p.func_72838_d((Entity)falling); 
  }
  
  public void earthquake(EntityLivingBase player, int count) throws InterruptedException {
    JsonPrimitive rangePrimitive = (JsonPrimitive)this.attackRange.getValue();
    float range = rangePrimitive.getAsFloat();
    AxisAlignedBB scanAbove = AxisAlignedBB.func_72330_a(player.field_70165_t - range, player.field_70163_u - range, player.field_70161_v - range, player.field_70165_t + range, player.field_70163_u + range, player.field_70161_v + range);
    List players = player.field_70170_p.func_72872_a(EntityPlayer.class, scanAbove);
    JsonPrimitive damagePrimitive = (JsonPrimitive)this.attackDamage.getValue();
    float damage = damagePrimitive.getAsFloat();
    for (Object entityObject : players) {
      EntityLivingBase entity = (EntityLivingBase)entityObject;
      entity.func_70097_a(DamageSource.func_76358_a(this.host), damage);
      entity.func_70024_g((
          -MathHelper.func_76126_a(this.host.field_70177_z * 3.1415927F / 180.0F) * 2.0F * 0.5F), 0.1D, (
          
          MathHelper.func_76134_b(this.host.field_70177_z * 3.1415927F / 180.0F) * 2.0F * 0.5F));
      entity.field_70181_x += 0.4000000059604645D;
    } 
    for (int j = 0; j < count; j += 5) {
      Thread.sleep(200L);
      float i;
      for (i = 0.5F; i < 3.0F; i++) {
        earthquake(player, i, 230.0F, j);
        earthquake(player, i, 270.0F, j);
        earthquake(player, i, 315.0F, j);
        earthquake(player, i, 360.0F, j);
        earthquake(player, i, 45.0F, j);
        earthquake(player, i, 90.0F, j);
        earthquake(player, i, 135.0F, j);
        earthquake(player, i, 180.0F, j);
      } 
    } 
  }
  
  public boolean isRunning() {
    return this.running;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\attacks\DjinnAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */