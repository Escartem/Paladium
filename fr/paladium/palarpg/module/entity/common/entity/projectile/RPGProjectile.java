package fr.paladium.palarpg.module.entity.common.entity.projectile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palarpg.module.entity.client.loader.RPGEntityModel;
import fr.paladium.palarpg.module.entity.client.loader.cache.RPGEntityModelCache;
import fr.paladium.palarpg.module.entity.common.entity.ProjectileDamageManager;
import fr.paladium.palarpg.module.entity.common.entity.source.CustomEntityDamageSource;
import fr.paladium.palarpg.module.entity.server.loader.data.util.RPGAttack;
import fr.paladium.zephyrui.lib.resource.Resource;
import io.netty.buffer.ByteBuf;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import software.bernie.geckolib3.entity.impl.AnimatedEntity;
import software.bernie.geckolib3.model.impl.model.LindwormModel;

public class RPGProjectile extends AnimatedEntity implements IEntityAdditionalSpawnData {
  public void setLindwormModel(LindwormModel<RPGProjectile> lindwormModel) {
    this.lindwormModel = lindwormModel;
  }
  
  public void setThrower(EntityLivingBase thrower) {
    this.thrower = thrower;
  }
  
  public void setAttack(RPGAttack attack) {
    this.attack = attack;
  }
  
  public void setOnGround(boolean onGround) {
    this.onGround = onGround;
  }
  
  public void setBounces(int bounces) {
    this.bounces = bounces;
  }
  
  private static final Gson GSON = (new GsonBuilder()).create();
  
  private final PlayerEntitySelector playerSelector = new PlayerEntitySelector();
  
  @SideOnly(Side.CLIENT)
  private LindwormModel<RPGProjectile> lindwormModel;
  
  private EntityLivingBase thrower;
  
  private RPGAttack attack;
  
  public PlayerEntitySelector getPlayerSelector() {
    return this.playerSelector;
  }
  
  public EntityLivingBase getThrower() {
    return this.thrower;
  }
  
  public RPGAttack getAttack() {
    return this.attack;
  }
  
  private boolean onGround = false;
  
  public boolean isOnGround() {
    return this.onGround;
  }
  
  private int bounces = 0;
  
  public int getBounces() {
    return this.bounces;
  }
  
  public RPGProjectile(World world) {
    super(world);
    func_70105_a(0.5F, 0.5F);
  }
  
  public RPGProjectile(World world, EntityLivingBase thrower) {
    this(world);
    this.thrower = thrower;
    func_70012_b(thrower.field_70165_t, thrower.field_70163_u + thrower.func_70047_e(), thrower.field_70161_v, thrower.field_70177_z, thrower.field_70125_A);
  }
  
  public RPGProjectile(World world, EntityLivingBase thrower, RPGAttack rpgAttack) {
    this(world, thrower);
    this.attack = rpgAttack;
  }
  
  public void onImpact(MovingObjectPosition mop) {
    if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) {
      if (mop.field_72308_g != getThrower() && !(mop.field_72308_g instanceof RPGProjectile) && !(mop.field_72308_g instanceof fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity)) {
        if (this.attack.doDamage()) {
          ProjectileDamageManager.registerProjectileDamage((Entity)this, (Entity)getThrower(), this.attack.getDamage());
          mop.field_72308_g.func_70097_a(CustomEntityDamageSource.causeCustomMobDamage((Entity)this).func_76349_b(), this.attack.getDamage());
        } 
        if (this.attack.getProjectile().hasPotionEffect() && mop.field_72308_g instanceof EntityLivingBase) {
          EntityLivingBase living = (EntityLivingBase)mop.field_72308_g;
          living.func_70690_d(this.attack.getProjectile().getPotionEffect());
        } 
        if (this.attack.getProjectile().isDeathOnCollideEntity()) {
          func_70106_y();
          if (this.attack.getProjectile().isExplodeOnDeath())
            this.field_70170_p.func_72876_a((Entity)this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 5.0F, false); 
        } 
      } 
      return;
    } 
    if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
      Block block = this.field_70170_p.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
      if (!block.func_149688_o().func_76230_c() || block.func_149668_a(this.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d) == null)
        return; 
      setMotion(this.field_70159_w * this.attack.getProjectile().getGroundFriction(), this.field_70181_x * this.attack.getProjectile().getGroundFriction(), this.field_70179_y * this.attack.getProjectile().getGroundFriction());
      if (this.attack.getProjectile().isDeathOnCollideBlock()) {
        func_70106_y();
        if (this.attack.getProjectile().isExplodeOnDeath())
          this.field_70170_p.func_72876_a((Entity)this, this.field_70165_t, this.field_70163_u, this.field_70161_v, getAttack().getProjectile().getExplosionSize(), false); 
        return;
      } 
      if (this.attack.getProjectile().canBounce() && this.bounces < this.attack.getProjectile().getMaxBounces()) {
        this.bounces++;
        switch (mop.field_72310_e) {
          case 0:
          case 1:
            setMotion(this.field_70159_w * this.attack.getProjectile().getGroundFriction(), -this.field_70181_x * this.attack.getProjectile().getBounceRestitution(), this.field_70179_y * this.attack.getProjectile().getGroundFriction());
            break;
          case 4:
          case 5:
            setMotion(-this.field_70159_w * this.attack.getProjectile().getBounceRestitution(), this.field_70181_x * this.attack.getProjectile().getAirFriction(), this.field_70179_y * this.attack.getProjectile().getAirFriction());
            break;
          case 2:
          case 3:
            setMotion(this.field_70159_w * this.attack.getProjectile().getAirFriction(), this.field_70181_x * this.attack.getProjectile().getAirFriction(), -this.field_70179_y * this.attack.getProjectile().getBounceRestitution());
            break;
        } 
        return;
      } 
      if (this.attack.getProjectile().isStickToBlock()) {
        this.onGround = true;
        setMotion(0.0D, 0.0D, 0.0D);
      } 
    } 
  }
  
  public void applyMovement() {
    if (this.onGround)
      return; 
    this.field_70181_x -= this.attack.getProjectile().getGravity();
    setMotion(this.field_70159_w * this.attack.getProjectile().getAirFriction(), this.field_70181_x * this.attack.getProjectile().getAirFriction(), this.field_70179_y * this.attack.getProjectile().getAirFriction());
    func_70107_b(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
  }
  
  protected void func_70088_a() {}
  
  public void func_70071_h_() {
    if ((this.thrower != null && this.thrower.field_70128_L) || this.attack == null) {
      func_70106_y();
      return;
    } 
    super.func_70071_h_();
    if (this.attack.getProjectile().getLifeSpan() > 0L && this.field_70173_aa >= this.attack.getProjectile().getLifeSpan()) {
      func_70106_y();
      return;
    } 
    Vec3 vecPos = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
    MovingObjectPosition movingobjectposition = null;
    if (this.onGround) {
      if (getAttack().getProjectile().hasPotionEffect() || getAttack().doDamage()) {
        List<?> list = this.field_70170_p.func_82733_a(EntityPlayer.class, this.field_70121_D.func_72314_b(1.0D, 1.0D, 1.0D), this.playerSelector);
        list.forEach(entity -> {
              if (entity instanceof EntityLivingBase && !(entity instanceof fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity))
                onImpact(new MovingObjectPosition((Entity)entity)); 
            });
      } 
    } else {
      Vec3 vecNextPos = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
      movingobjectposition = this.field_70170_p.func_72933_a(vecPos, vecNextPos);
      vecPos = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      vecNextPos = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
      if (movingobjectposition != null)
        vecNextPos = Vec3.func_72443_a(movingobjectposition.field_72307_f.field_72450_a, movingobjectposition.field_72307_f.field_72448_b, movingobjectposition.field_72307_f.field_72449_c); 
      Entity entity = null;
      List<?> list = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0D, 1.0D, 1.0D));
      double maxDistance = 0.0D;
      for (int i = 0; i < list.size(); i++) {
        Entity entity1 = (Entity)list.get(i);
        if (entity1.func_70067_L() && entity1 != getThrower()) {
          float f = 0.3F;
          AxisAlignedBB axisalignedbb = entity1.field_70121_D.func_72314_b(0.30000001192092896D, 0.30000001192092896D, 0.30000001192092896D);
          MovingObjectPosition movingobjectposition1 = axisalignedbb.func_72327_a(vecPos, vecNextPos);
          if (movingobjectposition1 != null) {
            double distance = vecPos.func_72438_d(movingobjectposition1.field_72307_f);
            if (distance < maxDistance || maxDistance == 0.0D) {
              entity = entity1;
              maxDistance = distance;
            } 
          } 
        } 
      } 
      if (entity != null)
        movingobjectposition = new MovingObjectPosition(entity); 
    } 
    if (movingobjectposition != null)
      onImpact(movingobjectposition); 
    applyMovement();
  }
  
  protected void func_70037_a(NBTTagCompound compound) {
    if (!compound.func_74764_b("rpgAttack"))
      return; 
    this.attack = (RPGAttack)GSON.fromJson(compound.func_74779_i("rpgAttack"), RPGAttack.class);
  }
  
  protected void func_70014_b(NBTTagCompound compound) {
    if (this.attack == null)
      return; 
    String json = GSON.toJson(this.attack);
    compound.func_74778_a("rpgAttack", json);
  }
  
  public void setMotion(double motionX, double motionY, double motionZ) {
    this.field_70159_w = motionX;
    this.field_70181_x = motionY;
    this.field_70179_y = motionZ;
  }
  
  @SideOnly(Side.CLIENT)
  public LindwormModel<RPGProjectile> getLindwormModel() {
    return this.lindwormModel;
  }
  
  @SideOnly(Side.CLIENT)
  public void loadModel(String modelId) {
    RPGEntityModel model = RPGEntityModelCache.get(modelId);
    if (model == null)
      throw new RuntimeException("No model found the model id " + modelId); 
    this.lindwormModel = new LindwormModel(model.getGeoModel(), Resource.of((ResourceLocation)model.getTextureResource()).nearest(), (m, e) -> (RPGProjectile)e);
    if (model.hasAnimation())
      this.lindwormModel.setAnimationFile(model.getAnimationFile()); 
  }
  
  public void writeSpawnData(ByteBuf buffer) {
    if (this.attack == null)
      return; 
    ByteBufUtils.writeUTF8String(buffer, GSON.toJson(this.attack));
  }
  
  public void readSpawnData(ByteBuf additionalData) {
    if (!additionalData.isReadable())
      return; 
    String attackJson = ByteBufUtils.readUTF8String(additionalData);
    if (attackJson == null || attackJson.isEmpty())
      return; 
    this.attack = (RPGAttack)GSON.fromJson(attackJson, RPGAttack.class);
    if (this.attack != null)
      loadModel(this.attack.getProjectile().getModel()); 
  }
  
  private class PlayerEntitySelector implements IEntitySelector {
    public boolean func_82704_a(Entity entity) {
      if (!(entity instanceof EntityPlayer))
        return false; 
      EntityPlayer player = (EntityPlayer)entity;
      return (player.func_70089_S() && !player.field_71075_bZ.field_75102_a);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\projectile\RPGProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */