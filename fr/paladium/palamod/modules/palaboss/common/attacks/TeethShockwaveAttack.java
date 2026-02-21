package fr.paladium.palamod.modules.palaboss.common.attacks;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import cpw.mods.fml.common.FMLCommonHandler;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.back2future.entities.projectile.EntityCustomFallingBlock;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityMorsula;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class TeethShockwaveAttack extends ShortRangeAttack {
  public static final String NAME = "teethShockwave";
  
  private transient AttackParamEntry attackDamage;
  
  private transient JsonObject jsonObject;
  
  private EntityLivingBase host;
  
  private boolean execute;
  
  public TeethShockwaveAttack(JsonObject jsonObject) {
    super("teethShockwave");
    this.jsonObject = jsonObject;
    registerParam(this.attackDamage = new AttackParamEntry("attackDamage", Integer.valueOf(55)));
    MinecraftForge.EVENT_BUS.register(this);
    FMLCommonHandler.instance().bus().register(this);
  }
  
  public JsonObject getJsonObject() {
    return this.jsonObject;
  }
  
  public void execute(World world, EntityBossBase entityHost, EntityLivingBase toAttackEntity) {
    this.host = (EntityLivingBase)entityHost;
    EntityMorsula morsula = (EntityMorsula)this.host;
    if (this.host != null) {
      this.execute = true;
      morsula.setShockwaveTeeth(true);
      morsula.field_70170_p.func_72960_a((Entity)entityHost, (byte)4);
      JsonPrimitive damagePrimitive = (JsonPrimitive)this.attackDamage.getValue();
      float damage = damagePrimitive.getAsFloat();
      AxisAlignedBB scanAbove = AxisAlignedBB.func_72330_a(entityHost.field_70165_t - 40.0D, entityHost.field_70163_u - 40.0D, entityHost.field_70161_v - 40.0D, entityHost.field_70165_t + 40.0D, entityHost.field_70163_u + 40.0D, entityHost.field_70161_v + 40.0D);
      List entities = entityHost.field_70170_p.func_72872_a(EntityLivingBase.class, scanAbove);
      for (Object entityObject : entities) {
        EntityLivingBase entity = (EntityLivingBase)entityObject;
        if (entity instanceof EntityMorsula)
          continue; 
        if (!entity.field_70122_E)
          continue; 
        entity.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)entityHost), damage);
        entity.func_70024_g((
            -MathHelper.func_76126_a(entityHost.field_70177_z * 3.1415927F / 180.0F) * 2.0F * 0.5F), 0.1D, (
            
            MathHelper.func_76134_b(entityHost.field_70177_z * 3.1415927F / 180.0F) * 2.0F * 0.5F));
        entity.field_70181_x += 0.6000000059604645D;
      } 
      for (int count = 0; count < 16; count++)
        earthquake(this.host, count); 
      morsula.setShockwaveTeeth(false);
      morsula.field_70170_p.func_72960_a((Entity)entityHost, (byte)5);
      this.execute = false;
    } else {
      System.err.println("Attack not appliquable to " + entityHost);
    } 
  }
  
  public void earthquake(EntityLivingBase player, float i, float rotationYaw, int count) {
    JsonPrimitive damagePrimitive = (JsonPrimitive)this.attackDamage.getValue();
    float damage = damagePrimitive.getAsFloat();
    double x = (count - 6) * Math.cos(Math.toRadians((i * 6.0F + rotationYaw + 90.0F))) + player.field_70165_t;
    double z = (count - 6) * Math.sin(Math.toRadians((i * 6.0F + rotationYaw + 90.0F))) + player.field_70161_v;
    BlockPos pos = new BlockPos(x, player.field_70163_u - 1.0D, z);
    EntityCustomFallingBlock falling = new EntityCustomFallingBlock(player.field_70170_p, (Entity)player, x, player.field_70163_u - 1.0D, z, 0.20000000298023224D, i * 6.0F + rotationYaw + 90.0F, pos, (int)damage);
    if (!player.field_70170_p.field_72995_K)
      player.field_70170_p.func_72838_d((Entity)falling); 
  }
  
  public void earthquake(EntityLivingBase player, int count) {
    for (float i = 0.5F; i < 3.0F; i++) {
      earthquake(player, i, 230.0F, count);
      earthquake(player, i, 270.0F, count);
      earthquake(player, i, 315.0F, count);
      earthquake(player, i, 360.0F, count);
      earthquake(player, i, 45.0F, count);
      earthquake(player, i, 90.0F, count);
      earthquake(player, i, 135.0F, count);
      earthquake(player, i, 180.0F, count);
    } 
  }
  
  public boolean isRunning() {
    return this.execute;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\attacks\TeethShockwaveAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */