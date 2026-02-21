package fr.paladium.palamod.modules.palaboss.common.attacks;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamod.modules.back2future.entities.EntityHusk;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.back2future.entities.projectile.EntityCustomFallingBlock;
import fr.paladium.palamod.modules.hunter.entites.EntitySnake;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityAzhur;
import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingFallEvent;

public class EarthQuakeAttack extends ShortRangeAttack {
  public static final String NAME = "earthQuake";
  
  private transient AttackParamEntry attackDamage;
  
  private transient JsonObject jsonObject;
  
  private EntityLivingBase host;
  
  private boolean execute;
  
  private boolean running = false;
  
  public EarthQuakeAttack(JsonObject jsonObject) {
    super("earthQuake");
    this.jsonObject = jsonObject;
    registerParam(this.attackDamage = new AttackParamEntry("attackDamage", Integer.valueOf(10)));
    MinecraftForge.EVENT_BUS.register(this);
    FMLCommonHandler.instance().bus().register(this);
  }
  
  public JsonObject getJsonObject() {
    return this.jsonObject;
  }
  
  public void execute(final World world, final EntityBossBase entityHost, EntityLivingBase toAttackEntity) {
    if (entityHost instanceof EntityAzhur) {
      if (isRunning())
        return; 
      final EntityAzhur azhur = (EntityAzhur)entityHost;
      this.running = true;
      azhur.inEarthQuake = true;
      int i;
      for (i = 0; i < 25; i++) {
        EntityHusk zombie = new EntityHusk(world);
        zombie.func_70107_b(entityHost.field_70165_t, entityHost.field_70163_u, entityHost.field_70161_v);
        if (!world.field_72984_F.func_76322_c().toLowerCase().contains("tracker"))
          world.func_72838_d((Entity)zombie); 
      } 
      for (i = 0; i < world.field_73012_v.nextInt(3); i++) {
        EntitySnake snake = new EntitySnake(world);
        snake.func_70107_b(entityHost.field_70165_t, entityHost.field_70163_u, entityHost.field_70161_v);
        if (!world.field_72984_F.func_76322_c().toLowerCase().contains("tracker"))
          world.func_72838_d((Entity)snake); 
      } 
      (new Thread(new Runnable() {
            public void run() {
              try {
                int offset = 40;
                JsonPrimitive damagePrimitive = (JsonPrimitive)EarthQuakeAttack.this.attackDamage.getValue();
                float damage = damagePrimitive.getAsFloat();
                for (int i = 0; i < 8; i++) {
                  for (int j = 0; j < 400; j++) {
                    Thread.sleep(50L);
                    EventUtils.spawnParticle(world, "smoke", entityHost.field_70165_t + world.field_73012_v.nextInt(30) - 15.0D, entityHost.field_70163_u + world.field_73012_v
                        .nextInt(3), entityHost.field_70161_v + world.field_73012_v.nextInt(30) - 15.0D, 1000, 3.0D);
                    AxisAlignedBB scanAbove = AxisAlignedBB.func_72330_a(entityHost.field_70165_t - offset, entityHost.field_70163_u - offset, entityHost.field_70161_v - offset, entityHost.field_70165_t + offset, entityHost.field_70163_u + offset, entityHost.field_70161_v + offset);
                    List players = entityHost.field_70170_p.func_72872_a(EntityPlayer.class, scanAbove);
                    for (Object entityObject : players) {
                      EntityLivingBase entity = (EntityLivingBase)entityObject;
                      entity.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)entityHost), damage);
                      entity.func_70024_g((
                          -MathHelper.func_76126_a(EarthQuakeAttack.this.host.field_70177_z * 3.1415927F / 180.0F) * 8.0F * 0.5F), 0.1D, (
                          
                          MathHelper.func_76134_b(EarthQuakeAttack.this.host.field_70177_z * 3.1415927F / 180.0F) * 8.0F * 0.5F));
                      entity.field_70181_x += 0.8000000059604645D;
                      entity.func_70690_d(new PotionEffect(Potion.field_76440_q.field_76415_H, 60, 1, true));
                    } 
                  } 
                } 
                EarthQuakeAttack.this.running = false;
                azhur.inEarthQuake = false;
              } catch (Exception e) {
                EarthQuakeAttack.this.running = false;
                azhur.inEarthQuake = false;
              } 
            }
          })).start();
      return;
    } 
    if (entityHost instanceof fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityUlcan) {
      for (int i = 0; i < 10; i++) {
        EntitySnake zombie = new EntitySnake(world);
        zombie.func_70107_b(entityHost.field_70165_t, entityHost.field_70163_u, entityHost.field_70161_v);
        if (!world.field_72984_F.func_76322_c().toLowerCase().contains("tracker"))
          world.func_72838_d((Entity)zombie); 
      } 
    } else {
      this.host = (EntityLivingBase)entityHost;
      if (entityHost.field_70181_x < 0.1D && world.func_147439_a((int)entityHost.field_70165_t, (int)entityHost.field_70163_u - 1, (int)entityHost.field_70161_v) != Blocks.field_150350_a)
        entityHost.field_70181_x = 1.0D; 
      this.running = true;
    } 
  }
  
  @SubscribeEvent
  public void onEntityFall(LivingFallEvent event) {
    if (this.host != null) {
      if (this.host instanceof EntityAzhur)
        return; 
      if (event.entityLiving instanceof EntityBossBase) {
        event.setCanceled(true);
        if (!this.running)
          return; 
        for (int count = 0; count < 16; count++)
          earthquake(this.host, count); 
        this.running = false;
      } 
    } 
  }
  
  public void earthquake(EntityLivingBase player, float i, float rotationYaw, int count) {
    JsonPrimitive damagePrimitive = (JsonPrimitive)this.attackDamage.getValue();
    float damage = damagePrimitive.getAsFloat();
    double x = (count - 6) * Math.cos(Math.toRadians((i * 6.0F + rotationYaw + 90.0F))) + player.field_70165_t;
    double z = (count - 6) * Math.sin(Math.toRadians((i * 6.0F + rotationYaw + 90.0F))) + player.field_70161_v;
    BlockPos pos = new BlockPos(x, player.field_70163_u - 1.0D, z);
    EntityCustomFallingBlock falling = new EntityCustomFallingBlock(player.field_70170_p, (Entity)player, x, player.field_70163_u - 1.0D, z, 0.20000000298023224D, i * 6.0F + rotationYaw + 90.0F, pos, (int)damage);
    if (!player.field_70170_p.field_72995_K && 
      !player.field_70170_p.field_72984_F.func_76322_c().toLowerCase().contains("tracker"))
      player.field_70170_p.func_72838_d((Entity)falling); 
  }
  
  public void earthquake(EntityLivingBase player, int count) {
    int offset = 50;
    AxisAlignedBB scanAbove = AxisAlignedBB.func_72330_a(player.field_70165_t - offset, player.field_70163_u - offset, player.field_70161_v - offset, player.field_70165_t + offset, player.field_70163_u + offset, player.field_70161_v + offset);
    List players = player.field_70170_p.func_72872_a(EntityPlayer.class, scanAbove);
    JsonPrimitive damagePrimitive = (JsonPrimitive)this.attackDamage.getValue();
    float damage = damagePrimitive.getAsFloat();
    for (Object entityObject : players) {
      EntityLivingBase entity = (EntityLivingBase)entityObject;
      entity.func_70097_a(DamageSource.func_76358_a(this.host), damage);
      entity.func_70690_d(new PotionEffect(Potion.field_76440_q.field_76415_H, 100, 2, true));
      entity.func_70690_d(new PotionEffect(Potion.field_76437_t.field_76415_H, 100, 2, true));
    } 
    float i;
    for (i = 0.5F; i < 3.0F; i++) {
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
    return this.running;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\attacks\EarthQuakeAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */