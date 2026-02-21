package fr.paladium.palamod.modules.luckyblock.entity;

import fr.paladium.palamod.modules.luckyblock.ai.EntityAIMoverHelperPala;
import fr.paladium.palamod.modules.luckyblock.ai.EntityAIWanderPala;
import fr.paladium.palamod.modules.luckyblock.pathfinding.PathNavigateFlyer;
import fr.paladium.palamod.modules.luckyblock.pathfinding.PathNavigateGround;
import fr.paladium.palamod.modules.luckyblock.pathfinding.PathNavigateSwimmer;
import java.util.List;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public abstract class EntityMobPaladium extends EntityMob {
  protected boolean divePending;
  
  protected int maxHealth;
  
  protected float moveSpeed;
  
  protected String texture;
  
  protected PathNavigate navigatorWater;
  
  protected PathNavigate navigatorFlyer;
  
  protected PathNavigate field_70699_by;
  
  protected EntityAIWanderPala wander;
  
  private EntityMoveHelper moveHelper;
  
  public EntityMobPaladium(World world) {
    super(world);
    this.texture = "blank.jpg";
    this.moveHelper = (EntityMoveHelper)new EntityAIMoverHelperPala((EntityLiving)this);
    this.navigatorWater = (PathNavigate)new PathNavigateSwimmer((EntityLiving)this, world);
    this.navigatorFlyer = (PathNavigate)new PathNavigateFlyer((EntityLiving)this, world);
    this.field_70699_by = (PathNavigate)new PathNavigateGround((EntityLiving)this, world);
    this.field_70714_bg.func_75776_a(4, (EntityAIBase)(this.wander = new EntityAIWanderPala((EntityCreature)this, 1.0D, 80)));
    initEntityAI();
  }
  
  public boolean func_70650_aV() {
    return true;
  }
  
  protected void initEntityAI() {}
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111263_d)
      .func_111128_a(getMoveSpeed());
    func_110148_a(SharedMonsterAttributes.field_111264_e)
      .func_111128_a(getAttackStrenght());
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
  }
  
  public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData) {
    return super.func_110161_a(par1EntityLivingData);
  }
  
  protected double getAttackStrenght() {
    return 2.0D;
  }
  
  protected void func_70088_a() {
    super.func_70088_a();
  }
  
  public boolean getCanSpawnHereLiving() {
    return (this.field_70170_p.func_72855_b(this.field_70121_D) && this.field_70170_p
      .func_72945_a((Entity)this, this.field_70121_D).size() == 0 && 
      !this.field_70170_p.func_72953_d(this.field_70121_D));
  }
  
  public boolean getCanSpawnHereCreature() {
    int i = MathHelper.func_76128_c(this.field_70165_t);
    int j = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
    int k = MathHelper.func_76128_c(this.field_70161_v);
    return (func_70783_a(i, j, k) >= 0.0F);
  }
  
  public boolean getCanSpawnHereMob() {
    int i = MathHelper.func_76128_c(this.field_70165_t);
    int j = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
    int k = MathHelper.func_76128_c(this.field_70161_v);
    if (this.field_70170_p.func_72972_b(EnumSkyBlock.Sky, i, j, k) > this.field_70146_Z.nextInt(32))
      return false; 
    int l = this.field_70170_p.func_72957_l(i, j, k);
    if (this.field_70170_p.func_72911_I()) {
      int i2 = this.field_70170_p.field_73008_k;
      this.field_70170_p.field_73008_k = 10;
      l = this.field_70170_p.func_72957_l(i, j, k);
      this.field_70170_p.field_73008_k = i2;
    } 
    return (l <= this.field_70146_Z.nextInt(8));
  }
  
  protected EntityLivingBase getClosestEntityLiving(Entity entity, double d) {
    double d2 = -1.0D;
    EntityLivingBase entityliving = null;
    List<?> list = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D
        .func_72314_b(d, d, d));
    for (int i = 0; i < list.size(); i++) {
      Entity entity2 = (Entity)list.get(i);
      if (!entitiesToIgnore(entity2)) {
        double d3 = entity2.func_70092_e(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v);
        if ((d < 0.0D || d3 < d * d) && (d2 == -1.0D || d3 < d2) && ((EntityLivingBase)entity2)
          .func_70685_l(entity)) {
          d2 = d3;
          entityliving = (EntityLivingBase)entity2;
        } 
      } 
    } 
    return entityliving;
  }
  
  public boolean entitiesToIgnore(Entity entity) {
    return (!(entity instanceof EntityLiving) || entity instanceof EntityMob || entity instanceof net.minecraft.entity.player.EntityPlayer);
  }
  
  public boolean checkSpawningBiome() {
    return true;
  }
  
  public void func_70636_d() {
    if (!this.field_70170_p.field_72995_K) {
      if (isHarmedByDaylight() && this.field_70170_p.func_72935_r()) {
        float var1 = func_70013_c(1.0F);
        if (var1 > 0.5F && this.field_70170_p
          .func_72937_j(MathHelper.func_76128_c(this.field_70165_t), 
            MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v)) && this.field_70146_Z
          .nextFloat() * 30.0F < (var1 - 0.4F) * 2.0F)
          func_70015_d(8); 
      } 
      if (getIsFlying() && func_70661_as().func_75500_f() && !func_70780_i() && 
        func_70638_az() == null && this.field_70146_Z.nextInt(20) == 0)
        this.wander.makeUpdate(); 
    } 
    func_70661_as().func_75501_e();
    super.func_70636_d();
  }
  
  protected int getMaxEdad() {
    return 100;
  }
  
  protected boolean isHarmedByDaylight() {
    return false;
  }
  
  public boolean func_70097_a(DamageSource damagesource, float i) {
    return super.func_70097_a(damagesource, i);
  }
  
  public boolean isFlyer() {
    return false;
  }
  
  public void func_70014_b(NBTTagCompound nbttagcompound) {
    super.func_70014_b(nbttagcompound);
  }
  
  public void func_70037_a(NBTTagCompound nbttagcompound) {
    super.func_70037_a(nbttagcompound);
  }
  
  public void func_70069_a(float f) {
    if (!isFlyer())
      super.func_70069_a(f); 
  }
  
  public boolean func_70617_f_() {
    return (!isFlyer() && super.func_70617_f_());
  }
  
  public void func_70612_e(float strafe, float forward) {
    if (!isFlyer()) {
      super.func_70612_e(strafe, forward);
      return;
    } 
    moveEntityWithHeadingFlyer(strafe, forward);
  }
  
  public void moveEntityWithHeadingFlyer(float strafe, float forward) {
    if (func_70613_aW()) {
      func_70060_a(strafe, forward, 0.1F);
      func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
      this.field_70159_w *= 0.8999999761581421D;
      this.field_70181_x *= 0.8999999761581421D;
      this.field_70179_y *= 0.8999999761581421D;
    } else {
      super.func_70612_e(strafe, forward);
    } 
  }
  
  public void performAnimation(int attackType) {}
  
  public float getMoveSpeed() {
    return 0.7F;
  }
  
  public int nameYOffset() {
    return 0;
  }
  
  public void makeEntityJump() {}
  
  public void makeEntityDive() {
    this.divePending = true;
  }
  
  protected boolean func_70692_ba() {
    return true;
  }
  
  public void func_70106_y() {
    super.func_70106_y();
  }
  
  public float getSizeFactor() {
    return 1.0F;
  }
  
  public float getAdjustedYOffset() {
    return 0.0F;
  }
  
  public void setArmorType(int i) {}
  
  public int getArmorType() {
    return 0;
  }
  
  public float pitchRotationOffset() {
    return 0.0F;
  }
  
  public float rollRotationOffset() {
    return 0.0F;
  }
  
  public float yawRotationOffset() {
    return 0.0F;
  }
  
  public float getAdjustedZOffset() {
    return 0.0F;
  }
  
  public float getAdjustedXOffset() {
    return 0.0F;
  }
  
  public boolean canAttackTarget(EntityLivingBase entity) {
    return false;
  }
  
  public boolean getIsSitting() {
    return false;
  }
  
  public boolean isNotScared() {
    return true;
  }
  
  protected boolean func_70780_i() {
    return false;
  }
  
  public boolean shouldAttackPlayers() {
    return (this.field_70170_p.field_73013_u != EnumDifficulty.PEACEFUL);
  }
  
  public double getDivingDepth() {
    return 0.0D;
  }
  
  public boolean isDiving() {
    return false;
  }
  
  public void forceEntityJump() {}
  
  public boolean func_70652_k(Entity entityIn) {
    boolean flag = entityIn.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), 
        (int)func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e());
    if (flag)
      func_174815_a((EntityLivingBase)this, entityIn); 
    return flag;
  }
  
  public int maxFlyingHeight() {
    return 5;
  }
  
  public int minFlyingHeight() {
    return 1;
  }
  
  public PathNavigate func_70661_as() {
    if (func_70090_H() && isAmphibian())
      return this.navigatorWater; 
    if (isFlyer())
      return this.navigatorFlyer; 
    return this.field_70699_by;
  }
  
  public boolean isAmphibian() {
    return false;
  }
  
  public boolean getIsFlying() {
    return isFlyer();
  }
  
  public boolean isCeased() {
    return func_70780_i();
  }
  
  public boolean isCreatureType(EnumCreatureType type, boolean forSpawnCount) {
    return (type == EnumCreatureType.monster);
  }
  
  public EntityMoveHelper func_70605_aq() {
    return this.moveHelper;
  }
  
  protected void func_174815_a(EntityLivingBase host, Entity target) {
    applyEnchantment(host, target);
  }
  
  public void applyEnchantment(EntityLivingBase host, Entity target) {
    if (target instanceof EntityLivingBase)
      EnchantmentHelper.func_151384_a((EntityLivingBase)target, (Entity)host); 
    EnchantmentHelper.func_151385_b(host, target);
  }
  
  protected void func_70619_bc() {
    this.field_70708_bq++;
    this.field_70170_p.field_72984_F.func_76320_a("checkDespawn");
    func_70623_bb();
    this.field_70170_p.field_72984_F.func_76319_b();
    this.field_70170_p.field_72984_F.func_76320_a("sensing");
    func_70635_at().func_75523_a();
    this.field_70170_p.field_72984_F.func_76319_b();
    this.field_70170_p.field_72984_F.func_76320_a("targetSelector");
    this.field_70715_bh.func_75774_a();
    this.field_70170_p.field_72984_F.func_76319_b();
    this.field_70170_p.field_72984_F.func_76320_a("goalSelector");
    this.field_70714_bg.func_75774_a();
    this.field_70170_p.field_72984_F.func_76319_b();
    this.field_70170_p.field_72984_F.func_76320_a("navigation");
    func_70661_as().func_75501_e();
    this.field_70170_p.field_72984_F.func_76319_b();
    this.field_70170_p.field_72984_F.func_76320_a("mob tick");
    func_70629_bd();
    this.field_70170_p.field_72984_F.func_76319_b();
    if (func_70115_ae() && this.field_70154_o instanceof EntityLiving && 
      func_70605_aq() instanceof EntityAIMoverHelperPala) {
      EntityLiving entityliving = (EntityLiving)this.field_70154_o;
      entityliving.func_70661_as().func_75484_a(func_70661_as().func_75505_d(), 1.5D);
      entityliving.func_70605_aq().func_75642_a(((EntityAIMoverHelperPala)
          func_70605_aq()).getX(), ((EntityAIMoverHelperPala)
          func_70605_aq()).getY(), ((EntityAIMoverHelperPala)
          func_70605_aq()).getZ(), func_70605_aq().func_75638_b());
    } 
    this.field_70170_p.field_72984_F.func_76320_a("controls");
    this.field_70170_p.field_72984_F.func_76320_a("move");
    func_70605_aq().func_75641_c();
    this.field_70170_p.field_72984_F.func_76318_c("look");
    func_70671_ap().func_75649_a();
    this.field_70170_p.field_72984_F.func_76318_c("jump");
    func_70683_ar().func_75661_b();
    this.field_70170_p.field_72984_F.func_76319_b();
    this.field_70170_p.field_72984_F.func_76319_b();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\EntityMobPaladium.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */