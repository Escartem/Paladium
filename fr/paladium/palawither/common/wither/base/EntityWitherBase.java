package fr.paladium.palawither.common.wither.base;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palawither.api.ItemsRegister;
import fr.paladium.palawither.client.entity.EntityWitherSmokeFX;
import fr.paladium.palawither.common.item.ItemWitherUpgrade;
import fr.paladium.palawither.common.network.SCPacketWitherData;
import fr.paladium.palawither.common.tileentity.TileEntityWitherReceptacle;
import fr.paladium.palawither.common.utils.UniversalTimeUtils;
import fr.paladium.palawither.common.wither.base.property.WitherProperty;
import fr.paladium.palawither.common.wither.base.property.impl.WitherBreakBlockProperty;
import fr.paladium.palawither.common.wither.base.property.impl.WitherExplosionProperty;
import fr.paladium.palawither.common.wither.base.property.impl.WitherProjectileProperty;
import fr.paladium.worldguardbridge.common.dto.flag.impl.StateFlag;
import fr.paladium.worldguardbridge.common.dto.flag.utils.FlagConstants;
import fr.paladium.worldguardbridge.common.manager.WGManager;
import fr.paladium.worldguardbridge.common.manager.utils.WGRegionList;
import fr.paladium.zephyrui.lib.color.Color;
import io.netty.buffer.ByteBuf;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import lombok.NonNull;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class EntityWitherBase extends EntityMob implements IWither, IRangedAttackMob, IEntityAdditionalSpawnData {
  private final Map<String, Object> properties;
  
  private final Color particleColor;
  
  private final int[] nextHeadUpdate = new int[2];
  
  private final int[] idleHeadUpdates = new int[2];
  
  private int blockBreakCounter;
  
  private int spawnX;
  
  private int spawnY;
  
  private int spawnZ;
  
  public EntityWitherBase(@NonNull World world) {
    super(world);
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    func_70661_as().func_75495_e(true);
    func_70606_j(func_110138_aP());
    func_70105_a(0.9F, 3.45F);
    if (hasVanillaBehavior()) {
      this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
      this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIArrowAttack(this, 1.0D, 40, 20.0F));
      this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0D));
      this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
      this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
      this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
      this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityLiving.class, 0, false, false, IWither.DEFAULT_ENTITY_SELECTOR));
    } 
    this.field_70178_ae = true;
    this.field_70728_aV = getExperienceValue();
    this.properties = new HashMap<>();
    this.particleColor = Color.decode(getColor());
  }
  
  public void writeSpawnData(ByteBuf buffer) {
    buffer.writeInt(this.spawnX);
    buffer.writeInt(this.spawnY);
    buffer.writeInt(this.spawnZ);
  }
  
  public void readSpawnData(ByteBuf additionalData) {
    this.spawnX = additionalData.readInt();
    this.spawnY = additionalData.readInt();
    this.spawnZ = additionalData.readInt();
  }
  
  public void func_70014_b(NBTTagCompound nbt) {
    super.func_70014_b(nbt);
    nbt.func_74768_a("SpawnX", this.spawnX);
    nbt.func_74768_a("SpawnY", this.spawnY);
    nbt.func_74768_a("SpawnZ", this.spawnZ);
  }
  
  public void func_70037_a(NBTTagCompound nbt) {
    super.func_70037_a(nbt);
    this.spawnX = nbt.func_74762_e("SpawnX");
    this.spawnY = nbt.func_74762_e("SpawnY");
    this.spawnZ = nbt.func_74762_e("SpawnZ");
  }
  
  @NonNull
  public <T extends IWither> T onSpawn(@NonNull TileEntityWitherReceptacle tile) {
    if (tile == null)
      throw new NullPointerException("tile is marked non-null but is null"); 
    setSpawn(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
    WitherExplosionProperty explosionProperty = getProperty("explosion");
    if (explosionProperty != null)
      explosionProperty.explode(this); 
    return (T)this;
  }
  
  public void breakBlocks() {
    int floorX = MathHelper.func_76128_c(this.field_70165_t);
    int floorY = MathHelper.func_76128_c(this.field_70163_u);
    int floorZ = MathHelper.func_76128_c(this.field_70161_v);
    int width = Math.round(this.field_70130_N);
    int height = Math.round(this.field_70131_O);
    boolean broken = false;
    for (int ox = -width; ox <= width; ox++) {
      for (int oz = -width; oz <= width; oz++) {
        for (int oy = 0; oy <= height; oy++) {
          int blockX = floorX + ox;
          int blockY = floorY + oy;
          int blockZ = floorZ + oz;
          broken = (breakBlock(blockX, blockY, blockZ) || broken);
        } 
      } 
    } 
    if (broken)
      this.field_70170_p.func_72889_a(null, 1012, floorX, floorY, floorZ, 0); 
  }
  
  public boolean breakBlock(int x, int y, int z) {
    Block block = this.field_70170_p.func_147439_a(x, y, z);
    if (block == Blocks.field_150357_h || block == Blocks.field_150384_bq || block == Blocks.field_150378_br || block == Blocks.field_150483_bI)
      return false; 
    if (block.isAir((IBlockAccess)this.field_70170_p, x, y, z) || !block.canEntityDestroy((IBlockAccess)this.field_70170_p, x, y, z, (Entity)this))
      return false; 
    WGRegionList regionList = WGManager.inst().getRegionListAt(this.field_70170_p, x, y, z);
    Optional<StateFlag> canBreakBlock = regionList.getFlag(FlagConstants.BLOCK_BREAK);
    Optional<StateFlag> canDragonBreakBlock = regionList.getFlag(FlagConstants.ENDERDRAGON_BLOCK_DAMAGE);
    if ((canBreakBlock.isPresent() && ((StateFlag)canBreakBlock.get()).isDenied()) || (canDragonBreakBlock.isPresent() && ((StateFlag)canDragonBreakBlock.get()).isDenied()))
      return false; 
    return this.field_70170_p.func_147480_a(x, y, z, true);
  }
  
  public void launchWitherSkullToEntity(int headIndex, EntityLivingBase target) {
    launchWitherSkullToCoords(headIndex, target.field_70165_t, target.field_70163_u + target.func_70047_e() * 0.5D, target.field_70161_v);
  }
  
  public void launchWitherSkullToCoords(int headIndex, double targetX, double targetY, double targetZ) {
    if (this.field_70170_p.field_72995_K)
      return; 
    WitherProjectileProperty projectileProperty = getProperty("projectile");
    if (projectileProperty == null)
      return; 
    double headX = getHeadX(headIndex);
    double headY = getHeadY(headIndex);
    double headZ = getHeadZ(headIndex);
    double motionX = targetX - headX;
    double motionY = targetY - headY;
    double motionZ = targetZ - headZ;
    try {
      projectileProperty.invoke(this, headX, headY, headZ, motionX, motionY, motionZ);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  public Entity getWatchedTarget(int headIndex) {
    int entityId = getWatchedTargetId(headIndex);
    if (entityId <= 0)
      return null; 
    return this.field_70170_p.func_73045_a(entityId);
  }
  
  public int getWatchedTargetId(int headIndex) {
    return this.field_70180_af.func_75679_c(17 + headIndex);
  }
  
  public void setWatchedTargetId(int headIndex, int entityId) {
    this.field_70180_af.func_75692_b(17 + headIndex, Integer.valueOf(entityId));
  }
  
  public void setSpawn(int x, int y, int z) {
    this.spawnX = x;
    this.spawnY = y;
    this.spawnZ = z;
  }
  
  public boolean isArmored() {
    return (hasProperty("armored") && func_110143_aJ() <= func_110138_aP() / 2.0F);
  }
  
  private ItemWitherUpgrade getUpgrade() {
    try {
      ItemStack stack = this.field_70180_af.func_82710_f(21);
      if (stack == null || stack.func_77973_b() == null || !(stack.func_77973_b() instanceof ItemWitherUpgrade))
        return null; 
      long upgradeTime = func_70096_w().func_75679_c(22) * 1000L;
      if (upgradeTime + TimeUnit.HOURS.toMillis(1L) < UniversalTimeUtils.now()) {
        setUpgrade((ItemWitherUpgrade)null);
        return null;
      } 
      return (ItemWitherUpgrade)stack.func_77973_b();
    } catch (Exception e) {
      return null;
    } 
  }
  
  private void setUpgrade(ItemWitherUpgrade upgrade) {
    if (upgrade == null) {
      this.field_70180_af.func_75692_b(21, null);
      this.field_70180_af.func_75692_b(22, Integer.valueOf(0));
    } else {
      this.field_70180_af.func_75692_b(21, new ItemStack((Item)upgrade));
      this.field_70180_af.func_75692_b(22, Integer.valueOf((int)(UniversalTimeUtils.now() / 1000L)));
    } 
  }
  
  @SideOnly(Side.CLIENT)
  private void spawnParticles(boolean armored, double x, double y, double z) {
    (Minecraft.func_71410_x()).field_71452_i.func_78873_a((EntityFX)new EntityWitherSmokeFX(this.field_70170_p, x + this.field_70146_Z.nextGaussian() * 0.30000001192092896D, y + this.field_70146_Z.nextGaussian() * 0.30000001192092896D, z + this.field_70146_Z.nextGaussian() * 0.30000001192092896D, this.particleColor.r, this.particleColor.g, this.particleColor.b));
    if (armored && this.field_70170_p.field_73012_v.nextInt(4) == 0)
      this.field_70170_p.func_72869_a("mobSpell", x + this.field_70146_Z.nextGaussian() * 0.30000001192092896D, y + this.field_70146_Z.nextGaussian() * 0.30000001192092896D, z + this.field_70146_Z.nextGaussian() * 0.30000001192092896D, 0.699999988079071D, 0.699999988079071D, 0.5D); 
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(300.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.6D);
    func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(40.0D);
    func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(8.0D);
  }
  
  protected void func_70088_a() {
    super.func_70088_a();
    this.field_70180_af.func_75682_a(17, Integer.valueOf(0));
    this.field_70180_af.func_75682_a(18, Integer.valueOf(0));
    this.field_70180_af.func_75682_a(19, Integer.valueOf(0));
    this.field_70180_af.func_82709_a(21, 5);
    this.field_70180_af.func_82709_a(22, 2);
    this.field_70180_af.func_75692_b(21, null);
    this.field_70180_af.func_75692_b(22, Integer.valueOf(0));
  }
  
  public void func_70636_d() {
    if (!this.field_70170_p.field_72995_K)
      SCPacketWitherData.update(this); 
    if (hasVanillaBehavior()) {
      double speed = func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e();
      this.field_70181_x *= speed;
      if (!this.field_70170_p.field_72995_K) {
        Entity target = getWatchedTarget(0);
        if (target != null) {
          if (this.field_70163_u < target.field_70163_u || (!isArmored() && this.field_70163_u < target.field_70163_u + 5.0D)) {
            if (this.field_70181_x < 0.0D)
              this.field_70181_x = 0.0D; 
            this.field_70181_x += (0.5D - this.field_70181_x) * speed;
          } 
          double distanceX = target.field_70165_t - this.field_70165_t;
          double distanceZ = target.field_70161_v - this.field_70161_v;
          double distance = distanceX * distanceX + distanceZ * distanceZ;
          if (distance > 9.0D) {
            double sqrtDistance = MathHelper.func_76133_a(distance);
            this.field_70159_w += (distanceX / sqrtDistance * 0.5D - this.field_70159_w) * speed;
            this.field_70179_y += (distanceZ / sqrtDistance * 0.5D - this.field_70179_y) * speed;
          } 
        } 
      } 
      if (this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y > 0.05000000074505806D)
        this.field_70177_z = (float)Math.atan2(this.field_70179_y, this.field_70159_w) * 57.295776F - 90.0F; 
    } 
    double maxDistance = getMaxDistance();
    if (!this.field_70170_p.field_72995_K && maxDistance > 0.0D) {
      double distanceX = this.field_70165_t - this.spawnX;
      double distanceY = this.field_70163_u - this.spawnY;
      double distanceZ = this.field_70161_v - this.spawnZ;
      double distance = MathHelper.func_76133_a(distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ);
      if (distance > maxDistance) {
        this.field_70159_w = (this.spawnX - this.field_70165_t) / distance * 0.1D;
        this.field_70181_x = (this.spawnY - this.field_70163_u) / distance * 0.1D;
        this.field_70179_y = (this.spawnZ - this.field_70161_v) / distance * 0.1D;
      } 
    } 
    super.func_70636_d();
    if (this.field_70170_p.field_72995_K) {
      boolean armored = isArmored();
      for (int headIndex = 0; headIndex < 3; headIndex++)
        spawnParticles(armored, getHeadX(headIndex), getHeadY(headIndex), getHeadZ(headIndex)); 
    } 
  }
  
  protected void func_70619_bc() {
    super.func_70619_bc();
    WitherProjectileProperty projectileProperty = getProperty("projectile");
    if (projectileProperty != null)
      for (int headIndex = 1; headIndex < 3; headIndex++) {
        if (this.field_70173_aa >= this.nextHeadUpdate[headIndex - 1]) {
          this.nextHeadUpdate[headIndex - 1] = this.field_70173_aa + 10 + this.field_70146_Z.nextInt(10);
          if (this.field_70170_p.field_73013_u == EnumDifficulty.NORMAL || this.field_70170_p.field_73013_u == EnumDifficulty.HARD) {
            int idleHeadUpdate = this.idleHeadUpdates[headIndex - 1];
            this.idleHeadUpdates[headIndex - 1] = this.idleHeadUpdates[headIndex - 1] + 1;
            if (idleHeadUpdate > 15) {
              float f = 10.0F;
              float f1 = 5.0F;
              double d0 = MathHelper.func_82716_a(this.field_70146_Z, this.field_70165_t - 10.0D, this.field_70165_t + 10.0D);
              double d1 = MathHelper.func_82716_a(this.field_70146_Z, this.field_70163_u - 5.0D, this.field_70163_u + 5.0D);
              double d2 = MathHelper.func_82716_a(this.field_70146_Z, this.field_70161_v - 10.0D, this.field_70161_v + 10.0D);
              launchWitherSkullToCoords(headIndex + 1, d0, d1, d2);
              this.idleHeadUpdates[headIndex - 1] = 0;
            } 
          } 
          int targetId = getWatchedTargetId(headIndex);
          if (targetId > 0) {
            Entity entity = getWatchedTarget(headIndex);
            if (entity != null && entity.func_70089_S() && func_70068_e(entity) <= 900.0D && func_70685_l(entity)) {
              launchWitherSkullToEntity(headIndex + 1, (EntityLivingBase)entity);
              this.nextHeadUpdate[headIndex - 1] = this.field_70173_aa + 40 + this.field_70146_Z.nextInt(20);
              this.idleHeadUpdates[headIndex - 1] = 0;
            } else {
              setWatchedTargetId(headIndex, 0);
            } 
          } else {
            List<EntityLivingBase> list = this.field_70170_p.func_82733_a(EntityLivingBase.class, this.field_70121_D.func_72314_b(20.0D, 8.0D, 20.0D), projectileProperty.getEntitySelector());
            for (int k1 = 0; k1 < 10 && !list.isEmpty(); k1++) {
              EntityLivingBase entitylivingbase = list.get(this.field_70146_Z.nextInt(list.size()));
              if (entitylivingbase != this && entitylivingbase.func_70089_S() && func_70685_l((Entity)entitylivingbase)) {
                if (entitylivingbase instanceof EntityPlayer) {
                  if (!((EntityPlayer)entitylivingbase).field_71075_bZ.field_75102_a)
                    setWatchedTargetId(headIndex, entitylivingbase.func_145782_y()); 
                  break;
                } 
                setWatchedTargetId(headIndex, entitylivingbase.func_145782_y());
                break;
              } 
              list.remove(entitylivingbase);
            } 
          } 
        } 
      }  
    if (func_70638_az() != null) {
      setWatchedTargetId(0, func_70638_az().func_145782_y());
    } else {
      setWatchedTargetId(0, 0);
    } 
    if (this.blockBreakCounter > 0) {
      this.blockBreakCounter--;
      if (this.blockBreakCounter == 0 && hasProperty("break_block") && this.field_70170_p.func_82736_K().func_82766_b("mobGriefing"))
        breakBlocks(); 
    } 
    if (this.field_70173_aa % 20 == 0)
      func_70691_i(1.0F); 
  }
  
  public void func_82196_d(EntityLivingBase target, float distance) {
    launchWitherSkullToEntity(0, target);
  }
  
  public boolean func_70097_a(DamageSource source, float damage) {
    if (func_85032_ar() || source == DamageSource.field_76369_e || (isArmored() && source.func_76364_f() instanceof net.minecraft.entity.projectile.EntityArrow))
      return false; 
    Entity entity = source.func_76346_g();
    if (entity != null && !(entity instanceof EntityPlayer) && entity instanceof EntityLivingBase && ((EntityLivingBase)entity).func_70668_bt() == func_70668_bt())
      return false; 
    WitherBreakBlockProperty breakBlockProperty = getProperty("break_block");
    if (this.blockBreakCounter <= 0 && breakBlockProperty != null)
      this.blockBreakCounter = ((Integer)breakBlockProperty.getDelayFunction().apply(source)).intValue(); 
    return super.func_70097_a(source, (!(source.func_76364_f() instanceof EntityPlayer) && hasUpgrade(ItemsRegister.RESISTANCE_WITHER_UPGRADE)) ? (damage / 2.0F) : damage);
  }
  
  protected void func_70628_a(boolean recentlyHit, int looting) {
    func_145779_a(Items.field_151156_bN, 1);
  }
  
  public int func_70658_aO() {
    return 4;
  }
  
  protected void func_70623_bb() {
    this.field_70708_bq = 0;
  }
  
  protected boolean func_70650_aV() {
    return true;
  }
  
  public EnumCreatureAttribute func_70668_bt() {
    return EnumCreatureAttribute.UNDEAD;
  }
  
  public void func_70078_a(Entity entity) {
    this.field_70154_o = null;
  }
  
  public void func_70110_aj() {}
  
  protected void func_70069_a(float distance) {}
  
  public void func_70690_d(PotionEffect effect) {}
  
  @SideOnly(Side.CLIENT)
  public float func_70053_R() {
    return this.field_70131_O / 8.0F;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_70070_b(float ticks) {
    return 15728880;
  }
  
  public boolean func_70692_ba() {
    return false;
  }
  
  public boolean func_90999_ad() {
    return false;
  }
  
  public void func_70015_d(int fire) {}
  
  protected void func_70081_e(int fire) {}
  
  protected void func_70044_A() {}
  
  public String getDisplayName() {
    return "entity." + EntityList.func_75621_b((Entity)this) + ".name";
  }
  
  public boolean hasUpgrade(@NonNull ItemWitherUpgrade upgrade) {
    if (upgrade == null)
      throw new NullPointerException("upgrade is marked non-null but is null"); 
    ItemWitherUpgrade existingUpgrade = getUpgrade();
    if (existingUpgrade == null)
      return false; 
    return existingUpgrade.equals(upgrade);
  }
  
  public boolean addUpgrade(@NonNull EntityPlayer player, @NonNull ItemWitherUpgrade upgrade) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (upgrade == null)
      throw new NullPointerException("upgrade is marked non-null but is null"); 
    if (getUpgrade() != null) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cCe §cWither §cpossède §cdéjà §cune §camélioration §c!"));
      return false;
    } 
    setUpgrade(upgrade);
    return true;
  }
  
  @NonNull
  public <T extends EntityWitherBase> T addProperty(@NonNull Object... properties) {
    if (properties == null)
      throw new NullPointerException("properties is marked non-null but is null"); 
    for (Object property : properties) {
      WitherProperty witherProperty = property.getClass().<WitherProperty>getAnnotation(WitherProperty.class);
      if (witherProperty == null)
        throw new IllegalArgumentException("The property must be annotated with @WitherProperty"); 
      this.properties.put(witherProperty.value(), property);
      if (property instanceof WitherProjectileProperty) {
        WitherProjectileProperty projectileProperty = (WitherProjectileProperty)property;
        this.field_70715_bh.field_75782_a.clear();
        this.field_70714_bg.field_75782_a.removeIf(obj -> ((EntityAITasks.EntityAITaskEntry)obj).field_75733_a instanceof EntityAIArrowAttack);
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIArrowAttack(this, 1.0D, 40, 20.0F));
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
        this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityLiving.class, 0, false, false, projectileProperty.getEntitySelector()));
      } 
    } 
    return (T)this;
  }
  
  @NonNull
  public <T extends EntityWitherBase> T removeProperty(@NonNull String propertyName) {
    if (propertyName == null)
      throw new NullPointerException("propertyName is marked non-null but is null"); 
    this.properties.remove(propertyName);
    if ("projectile".equals(propertyName)) {
      this.field_70715_bh.field_75782_a.clear();
      this.field_70714_bg.field_75782_a.removeIf(obj -> ((EntityAITasks.EntityAITaskEntry)obj).field_75733_a instanceof EntityAIArrowAttack);
      if (hasVanillaBehavior()) {
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIArrowAttack(this, 1.0D, 40, 20.0F));
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
        this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityLiving.class, 0, false, false, IWither.DEFAULT_ENTITY_SELECTOR));
      } 
    } 
    return (T)this;
  }
  
  public boolean hasProperty(@NonNull String propertyName) {
    if (propertyName == null)
      throw new NullPointerException("propertyName is marked non-null but is null"); 
    return this.properties.containsKey(propertyName);
  }
  
  public <T> T getProperty(@NonNull String propertyName) {
    if (propertyName == null)
      throw new NullPointerException("propertyName is marked non-null but is null"); 
    if (!hasProperty(propertyName))
      return null; 
    return (T)this.properties.get(propertyName);
  }
  
  protected String func_70639_aQ() {
    return "mob.wither.idle";
  }
  
  protected String func_70621_aR() {
    return "mob.wither.hurt";
  }
  
  protected String func_70673_aS() {
    return "mob.wither.death";
  }
  
  protected boolean hasVanillaBehavior() {
    return true;
  }
  
  protected int getExperienceValue() {
    return 50;
  }
  
  protected double getHeadX(int headIndex) {
    if (headIndex <= 0)
      return this.field_70165_t; 
    float f = (this.field_70761_aq + (180 * (headIndex - 1))) / 180.0F * 3.1415927F;
    float f1 = MathHelper.func_76134_b(f);
    return this.field_70165_t + (f1 * this.field_70130_N * 1.44F);
  }
  
  protected double getHeadY(int headIndex) {
    return (headIndex <= 0) ? (this.field_70163_u + (this.field_70131_O * 0.87F)) : (this.field_70163_u + (this.field_70131_O * 0.64F));
  }
  
  protected double getHeadZ(int headIndex) {
    if (headIndex <= 0)
      return this.field_70161_v; 
    float f = (this.field_70761_aq + (180 * (headIndex - 1))) / 180.0F * 3.1415927F;
    float f1 = MathHelper.func_76126_a(f);
    return this.field_70161_v + (f1 * this.field_70130_N * 1.44F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\wither\base\EntityWitherBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */