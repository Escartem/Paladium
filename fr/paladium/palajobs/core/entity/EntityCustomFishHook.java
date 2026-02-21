package fr.paladium.palajobs.core.entity;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palajobs.core.fishing.FishingCategory;
import fr.paladium.palajobs.core.registry.FishingRegistry;
import fr.paladium.palajobs.server.managers.FishingManager;
import fr.paladium.pet.client.PetClientProxy;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.utils.PetUtils;
import fr.paladium.pet.server.skill.handler.PassiveResponse;
import fr.paladium.pet.server.skill.handler.PassiveSkillEnum;
import io.netty.buffer.ByteBuf;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityCustomFishHook extends EntityFishHook implements IEntityAdditionalSpawnData {
  private EntityPlayer thrower;
  
  private int timer;
  
  private Block tile;
  
  private int xTile;
  
  private int yTile;
  
  private int zTile;
  
  private double xPosition;
  
  private double yPosition;
  
  private double zPosition;
  
  private double yawPosition;
  
  private double pitchPosition;
  
  private int flyingLifespan;
  
  private int groundLifespan;
  
  private boolean inGround;
  
  private int airTime;
  
  private Entity hitEntity;
  
  @SideOnly(Side.CLIENT)
  private double clientMotionX;
  
  @SideOnly(Side.CLIENT)
  private double clientMotionY;
  
  @SideOnly(Side.CLIENT)
  private double clientMotionZ;
  
  public EntityPlayer getThrower() {
    return this.thrower;
  }
  
  public int getTimer() {
    return this.timer;
  }
  
  public Block getTile() {
    return this.tile;
  }
  
  public int getXTile() {
    return this.xTile;
  }
  
  public int getYTile() {
    return this.yTile;
  }
  
  public int getZTile() {
    return this.zTile;
  }
  
  public double getXPosition() {
    return this.xPosition;
  }
  
  public double getYPosition() {
    return this.yPosition;
  }
  
  public double getZPosition() {
    return this.zPosition;
  }
  
  public double getYawPosition() {
    return this.yawPosition;
  }
  
  public double getPitchPosition() {
    return this.pitchPosition;
  }
  
  public int getFlyingLifespan() {
    return this.flyingLifespan;
  }
  
  public int getGroundLifespan() {
    return this.groundLifespan;
  }
  
  public boolean isInGround() {
    return this.inGround;
  }
  
  public int getAirTime() {
    return this.airTime;
  }
  
  public Entity getHitEntity() {
    return this.hitEntity;
  }
  
  public double getClientMotionX() {
    return this.clientMotionX;
  }
  
  public double getClientMotionY() {
    return this.clientMotionY;
  }
  
  public double getClientMotionZ() {
    return this.clientMotionZ;
  }
  
  public EntityCustomFishHook(World world) {
    super(world);
    func_70105_a(0.25F, 0.25F);
    this.field_70158_ak = true;
    this.xTile = -1;
    this.yTile = -1;
    this.zTile = -1;
  }
  
  @SideOnly(Side.CLIENT)
  public EntityCustomFishHook(World world, double x, double y, double z, EntityPlayer player) {
    this(world);
    func_70107_b(x, y, z);
    this.field_70158_ak = true;
    this.thrower = player;
    this.thrower.field_71104_cf = this;
  }
  
  public EntityCustomFishHook(World world, EntityPlayer player) {
    super(world);
    func_70105_a(0.25F, 0.25F);
    this.field_70158_ak = true;
    this.thrower = player;
    this.thrower.field_71104_cf = this;
    int minTimer = 5;
    int maxTimer = 20;
    this.timer = (world.field_73012_v.nextInt(15) + 5) * 20;
    applyPetSkill(world, player);
    this.xTile = -1;
    this.yTile = -1;
    this.zTile = -1;
    func_70012_b(player.field_70165_t, player.field_70163_u + 1.62D - player.field_70129_M, player.field_70161_v, player.field_70177_z, player.field_70125_A);
    this.field_70165_t -= (MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F);
    this.field_70163_u -= 0.10000000149011612D;
    this.field_70161_v -= (MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F);
    func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
    this.field_70129_M = 0.0F;
    float f = 0.4F;
    this.field_70159_w = (-MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F) * f);
    this.field_70179_y = (MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F) * f);
    this.field_70181_x = (-MathHelper.func_76126_a(this.field_70125_A / 180.0F * 3.1415927F) * f);
    randomizeMotion(this.field_70159_w, this.field_70181_x, this.field_70179_y, 1.5F, 1.0F);
  }
  
  public void applyPetSkill(World world, EntityPlayer player) {
    if (!world.field_72995_K) {
      PetPlayer pet = PetPlayer.get(player);
      PassiveResponse response = PassiveSkillEnum.FISHERMAN.getResponse(pet);
      double value = response.getPersonalValue(pet);
      if (response.has(value)) {
        double percentage = PetUtils.getValueAsPercent(value);
        int decrease = (int)(this.timer * percentage);
        this.timer -= decrease;
      } 
    } else {
      double value = PetClientProxy.getInstance().getSkillValue(PassiveSkillEnum.FISHERMAN);
      if (value <= 0.0D)
        return; 
      double percentage = PetUtils.getValueAsPercent(value);
      int decrease = (int)(this.timer * percentage);
      this.timer -= decrease;
    } 
  }
  
  public void randomizeMotion(double motX, double motY, double motZ, float mult1, float mult2) {
    float f2 = MathHelper.func_76133_a(motX * motX + motY * motY + motZ * motZ);
    motX /= f2;
    motY /= f2;
    motZ /= f2;
    motX += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * mult2;
    motY += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * mult2;
    motZ += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * mult2;
    motX *= mult1;
    motY *= mult1;
    motZ *= mult1;
    this.field_70159_w = motX;
    this.field_70181_x = motY;
    this.field_70179_y = motZ;
    float f3 = MathHelper.func_76133_a(motX * motX + motZ * motZ);
    this.field_70126_B = this.field_70177_z = (float)(Math.atan2(motX, motZ) * 180.0D / Math.PI);
    this.field_70127_C = this.field_70125_A = (float)(Math.atan2(motY, f3) * 180.0D / Math.PI);
    this.groundLifespan = 0;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_70056_a(double x, double y, double z, float yaw, float pitch, int lifespan) {
    this.xPosition = x;
    this.yPosition = y;
    this.zPosition = z;
    this.yawPosition = yaw;
    this.pitchPosition = pitch;
    this.flyingLifespan = lifespan;
    this.field_70159_w = this.clientMotionX;
    this.field_70181_x = this.clientMotionY;
    this.field_70179_y = this.clientMotionZ;
  }
  
  @SideOnly(Side.CLIENT)
  public void func_70016_h(double motX, double motY, double motZ) {
    this.clientMotionX = this.field_70159_w = motX;
    this.clientMotionY = this.field_70181_x = motY;
    this.clientMotionZ = this.field_70179_y = motZ;
  }
  
  public void func_70071_h_() {
    func_70030_z();
    if (this.thrower == null) {
      func_70106_y();
      return;
    } 
    ItemStack itemstack = this.thrower.func_71045_bC();
    if (!this.field_70170_p.field_72995_K && 
      itemstack == null) {
      func_70106_y();
      return;
    } 
    if (this.flyingLifespan > 0) {
      double newX = this.field_70165_t + (this.xPosition - this.field_70165_t) / this.flyingLifespan;
      double newY = this.field_70163_u + (this.yPosition - this.field_70163_u) / this.flyingLifespan;
      double newZ = this.field_70161_v + (this.zPosition - this.field_70161_v) / this.flyingLifespan;
      double wrappedYaw = MathHelper.func_76138_g(this.yawPosition - this.field_70177_z);
      this.field_70177_z = (float)(this.field_70177_z + wrappedYaw / this.flyingLifespan);
      this.field_70125_A = (float)(this.field_70125_A + (this.pitchPosition - this.field_70125_A) / this.flyingLifespan);
      func_70107_b(newX, newY, newZ);
      func_70101_b(this.field_70177_z, this.field_70125_A);
      this.flyingLifespan--;
    } else {
      if (!this.field_70170_p.field_72995_K) {
        if (this.thrower.field_70128_L || !this.thrower.func_70089_S() || itemstack == null || !(itemstack.func_77973_b() instanceof net.minecraft.item.ItemFishingRod) || func_70068_e((Entity)this.thrower) > 1024.0D) {
          func_70106_y();
          return;
        } 
        if (this.hitEntity != null) {
          if (!this.hitEntity.field_70128_L) {
            this.field_70165_t = this.hitEntity.field_70165_t;
            this.field_70163_u = this.hitEntity.field_70121_D.field_72338_b + this.hitEntity.field_70131_O * 0.8D;
            this.field_70161_v = this.hitEntity.field_70161_v;
            return;
          } 
          this.hitEntity = null;
        } 
      } 
      if (this.inGround) {
        if (this.field_70170_p.func_147439_a(this.xTile, this.yTile, this.zTile) == this.tile) {
          this.groundLifespan++;
          if (this.groundLifespan == 1200)
            func_70106_y(); 
          return;
        } 
        this.inGround = false;
        this.field_70159_w *= (this.field_70146_Z.nextFloat() * 0.2F);
        this.field_70181_x *= (this.field_70146_Z.nextFloat() * 0.2F);
        this.field_70179_y *= (this.field_70146_Z.nextFloat() * 0.2F);
        this.groundLifespan = 0;
        this.airTime = 0;
      } else {
        this.airTime++;
      } 
      Vec3 posVec = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      Vec3 nextPosVec = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
      MovingObjectPosition rayTrace = this.field_70170_p.func_72933_a(posVec, nextPosVec);
      posVec = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      nextPosVec = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
      if (rayTrace != null)
        nextPosVec = Vec3.func_72443_a(rayTrace.field_72307_f.field_72450_a, rayTrace.field_72307_f.field_72448_b, rayTrace.field_72307_f.field_72449_c); 
      Entity nearestEntity = null;
      List<?> list = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0D, 1.0D, 1.0D));
      double lastDistance = 0.0D;
      for (int i = 0; i < list.size(); i++) {
        Entity entity = (Entity)list.get(i);
        if (entity.func_70067_L() && (entity != this.thrower || this.airTime >= 5)) {
          AxisAlignedBB axisalignedbb = entity.field_70121_D.func_72314_b(0.30000001192092896D, 0.30000001192092896D, 0.30000001192092896D);
          MovingObjectPosition interecption = axisalignedbb.func_72327_a(posVec, nextPosVec);
          if (interecption != null) {
            double dist = posVec.func_72438_d(interecption.field_72307_f);
            if (dist < lastDistance || lastDistance == 0.0D) {
              nearestEntity = entity;
              lastDistance = dist;
            } 
          } 
        } 
      } 
      if (nearestEntity != null)
        rayTrace = new MovingObjectPosition(nearestEntity); 
      if (rayTrace != null)
        if (rayTrace.field_72308_g != null) {
          if (rayTrace.field_72308_g.func_70097_a(DamageSource.func_76356_a((Entity)this, (Entity)this.thrower), 0.0F))
            this.hitEntity = rayTrace.field_72308_g; 
        } else {
          this.inGround = true;
        }  
      if (!this.inGround) {
        func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
        float motionSqrt = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
        this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0D / Math.PI);
        for (this.field_70125_A = (float)(Math.atan2(this.field_70181_x, motionSqrt) * 180.0D / Math.PI); this.field_70125_A - this.field_70127_C < -180.0F; this.field_70127_C -= 360.0F);
        while (this.field_70125_A - this.field_70127_C >= 180.0F)
          this.field_70127_C += 360.0F; 
        while (this.field_70177_z - this.field_70126_B < -180.0F)
          this.field_70126_B -= 360.0F; 
        while (this.field_70177_z - this.field_70126_B >= 180.0F)
          this.field_70126_B += 360.0F; 
        this.field_70125_A = this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2F;
        this.field_70177_z = this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2F;
        float motionFactor = 0.92F;
        if (this.field_70122_E || this.field_70123_F)
          motionFactor = 0.5F; 
        byte b0 = 5;
        double d10 = 0.0D;
        for (int j = 0; j < b0; j++) {
          double d3 = this.field_70121_D.field_72338_b + (this.field_70121_D.field_72337_e - this.field_70121_D.field_72338_b) * (j + 0) / b0 - 0.125D + 0.125D;
          double d4 = this.field_70121_D.field_72338_b + (this.field_70121_D.field_72337_e - this.field_70121_D.field_72338_b) * (j + 1) / b0 - 0.125D + 0.125D;
          AxisAlignedBB axisalignedbb1 = AxisAlignedBB.func_72330_a(this.field_70121_D.field_72340_a, d3, this.field_70121_D.field_72339_c, this.field_70121_D.field_72336_d, d4, this.field_70121_D.field_72334_f);
          if (this.field_70170_p.func_72830_b(axisalignedbb1, Material.field_151586_h))
            d10 += 1.0D / b0; 
        } 
        this.field_70181_x += 0.03999999910593033D * (d10 * 2.0D - 1.0D);
        if (d10 > 0.0D) {
          motionFactor = (float)(motionFactor * 0.9D);
          this.field_70181_x *= 0.8D;
          if (!this.field_70170_p.field_72995_K) {
            if (this.timer <= 0) {
              FishingCategory category = FishingRegistry.getRandomCategory(itemstack.func_77973_b(), this.field_70170_p.field_73012_v);
              if (category != null && this.thrower instanceof EntityPlayerMP)
                FishingManager.open((EntityPlayerMP)this.thrower, category); 
              func_70106_y();
            } 
            this.timer--;
          } 
        } 
        this.field_70159_w *= motionFactor;
        this.field_70181_x *= motionFactor;
        this.field_70179_y *= motionFactor;
        func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      } 
    } 
  }
  
  public void func_70014_b(NBTTagCompound compound) {
    compound.func_74768_a("timer", this.timer);
    compound.func_74777_a("xTile", (short)this.xTile);
    compound.func_74777_a("yTile", (short)this.yTile);
    compound.func_74777_a("zTile", (short)this.zTile);
    compound.func_74774_a("inTile", (byte)Block.func_149682_b(this.tile));
    compound.func_74774_a("inGround", (byte)(this.inGround ? 1 : 0));
  }
  
  public void func_70037_a(NBTTagCompound compound) {
    this.timer = compound.func_74762_e("timer");
    this.xTile = compound.func_74765_d("xTile");
    this.yTile = compound.func_74765_d("yTile");
    this.zTile = compound.func_74765_d("zTile");
    this.tile = Block.func_149729_e(compound.func_74771_c("inTile") & 0xFF);
    this.inGround = (compound.func_74771_c("inGround") == 1);
  }
  
  @SideOnly(Side.CLIENT)
  public float func_70053_R() {
    return 0.0F;
  }
  
  public void func_70106_y() {
    super.func_70106_y();
    if (this.thrower != null)
      this.thrower.field_71104_cf = null; 
  }
  
  @SideOnly(Side.SERVER)
  public void writeSpawnData(ByteBuf buffer) {
    if (this.thrower != null)
      ByteBufUtils.writeUTF8String(buffer, UUIDUtils.toString((Entity)this.thrower)); 
  }
  
  @SideOnly(Side.CLIENT)
  public void readSpawnData(ByteBuf additionalData) {
    if (!additionalData.isReadable())
      return; 
    UUID uuid = UUID.fromString(ByteBufUtils.readUTF8String(additionalData));
    this.thrower = (Minecraft.func_71410_x()).field_71439_g.field_70170_p.func_152378_a(uuid);
    if (this.thrower != null)
      this.thrower.field_71104_cf = this; 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\entity\EntityCustomFishHook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */