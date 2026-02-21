package fr.paladium.palamod.modules.back2future.entities;

import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.ModItems;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.back2future.entities.ai.EntityAIMoveToBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityJumpHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityRabbit extends EntityAnimal {
  private int field_175540_bm = 0;
  
  private int field_175535_bn = 0;
  
  private boolean field_175536_bo = false;
  
  private boolean field_175537_bp = false;
  
  private int currentMoveTypeDuration = 0;
  
  private EnumMoveType moveType;
  
  private int carrotTicks;
  
  private EntityAIAvoidEntity aiAvoidWolves;
  
  public EntityRabbit(World world) {
    super(world);
    this.moveType = EnumMoveType.HOP;
    this.carrotTicks = 0;
    func_70105_a(0.4F, 0.5F);
    ReflectionHelper.setPrivateValue(EntityLiving.class, this, new RabbitJumpHelper(this), new String[] { "jumpHelper", "field_70767_i" });
    ReflectionHelper.setPrivateValue(EntityLiving.class, this, new RabbitMoveHelper(), new String[] { "moveHelper", "field_70765_h" });
    func_70661_as().func_75491_a(true);
    this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.field_70714_bg.func_75776_a(1, (EntityAIBase)new AIPanic(1.33D));
    this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIMate(this, 0.8D));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.0D, Items.field_151172_bF, false));
    this.field_70714_bg.func_75776_a(5, (EntityAIBase)new AIRaidFarm());
    this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIWander((EntityCreature)this, 0.6D));
    this.field_70714_bg.func_75776_a(11, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 10.0F));
    this.aiAvoidWolves = new EntityAIAvoidEntity((EntityCreature)this, EntityWolf.class, 16.0F, 1.33D, 1.33D);
    this.field_70714_bg.func_75776_a(4, (EntityAIBase)this.aiAvoidWolves);
    setMovementSpeed(0.0D);
  }
  
  public RabbitMoveHelper getMoveHelper() {
    return (RabbitMoveHelper)super.func_70605_aq();
  }
  
  public RabbitJumpHelper getJumpHelper() {
    return (RabbitJumpHelper)super.func_70683_ar();
  }
  
  public void setMoveType(EnumMoveType type) {
    this.moveType = type;
  }
  
  @SideOnly(Side.CLIENT)
  public float func_175521_o(float p_175521_1_) {
    return (this.field_175535_bn == 0) ? 0.0F : ((this.field_175540_bm + p_175521_1_) / this.field_175535_bn);
  }
  
  public void setMovementSpeed(double newSpeed) {
    func_70661_as().func_75489_a(newSpeed);
    getMoveHelper().func_75642_a(getMoveHelper().getX(), getMoveHelper().getY(), 
        getMoveHelper().getZ(), newSpeed);
  }
  
  public void setJumping(boolean jump, EnumMoveType moveTypeIn) {
    func_70637_d(jump);
    if (!jump) {
      if (this.moveType == EnumMoveType.ATTACK)
        this.moveType = EnumMoveType.HOP; 
    } else {
      setMovementSpeed(1.5D * moveTypeIn.getSpeed());
      func_85030_a(getJumpingSound(), func_70599_aP(), ((this.field_70146_Z
          .nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F) * 0.8F);
    } 
    this.field_175536_bo = jump;
  }
  
  public void doMovementAction(EnumMoveType movetype) {
    setJumping(true, movetype);
    this.field_175535_bn = movetype.func_180073_d();
    this.field_175540_bm = 0;
  }
  
  protected void func_70088_a() {
    super.func_70088_a();
    this.field_70180_af.func_75682_a(18, Byte.valueOf((byte)0));
  }
  
  public IEntityLivingData func_110161_a(IEntityLivingData livingdata) {
    setRabbitType(this.field_70146_Z.nextInt(6));
    return super.func_110161_a(livingdata);
  }
  
  protected boolean func_70650_aV() {
    return true;
  }
  
  public void func_70619_bc() {
    super.func_70619_bc();
    if (getMoveHelper().func_75638_b() > 0.8D) {
      setMoveType(EnumMoveType.SPRINT);
    } else if (this.moveType != EnumMoveType.ATTACK) {
      setMoveType(EnumMoveType.HOP);
    } 
    if (this.currentMoveTypeDuration > 0)
      this.currentMoveTypeDuration--; 
    if (this.carrotTicks > 0) {
      this.carrotTicks -= this.field_70146_Z.nextInt(3);
      if (this.carrotTicks < 0)
        this.carrotTicks = 0; 
    } 
    if (this.field_70122_E) {
      if (!this.field_175537_bp) {
        setJumping(false, EnumMoveType.NONE);
        func_175517_cu();
      } 
      if (getRabbitType() >= 98 && this.currentMoveTypeDuration == 0) {
        EntityPlayer entityPlayer = this.field_70170_p.func_72890_a((Entity)this, 20.0D);
        if (entityPlayer != null && func_70068_e((Entity)entityPlayer) < 20.0D) {
          calculateRotationYaw(((EntityLivingBase)entityPlayer).field_70165_t, ((EntityLivingBase)entityPlayer).field_70161_v);
          getMoveHelper().func_75642_a(((EntityLivingBase)entityPlayer).field_70165_t, ((EntityLivingBase)entityPlayer).field_70163_u, ((EntityLivingBase)entityPlayer).field_70161_v, 
              getMoveHelper().func_75638_b());
          doMovementAction(EnumMoveType.ATTACK);
          this.field_175537_bp = true;
        } 
      } 
      RabbitJumpHelper rabbitjumphelper = getJumpHelper();
      if (!rabbitjumphelper.getIsJumping()) {
        if (getMoveHelper().func_75640_a() && this.currentMoveTypeDuration == 0) {
          PathEntity pathentity = func_70661_as().func_75505_d();
          Vec3 vec3 = Vec3.func_72443_a(getMoveHelper().getX(), getMoveHelper().getY(), 
              getMoveHelper().getZ());
          if (pathentity != null && pathentity
            .func_75873_e() < pathentity.func_75874_d())
            vec3 = pathentity.func_75878_a((Entity)this); 
          calculateRotationYaw(vec3.field_72450_a, vec3.field_72449_c);
          doMovementAction(this.moveType);
        } 
      } else if (!rabbitjumphelper.func_180065_d()) {
        func_175518_cr();
      } 
    } 
    this.field_175537_bp = this.field_70122_E;
  }
  
  private void calculateRotationYaw(double p_175533_1_, double p_175533_3_) {
    this
      .field_70177_z = (float)(Math.atan2(p_175533_3_ - this.field_70161_v, p_175533_1_ - this.field_70165_t) * 180.0D / Math.PI) - 90.0F;
  }
  
  private void func_175518_cr() {
    getJumpHelper().func_180066_a(true);
  }
  
  private void func_175520_cs() {
    getJumpHelper().func_180066_a(false);
  }
  
  private void updateMoveTypeDuration() {
    this.currentMoveTypeDuration = getMoveTypeDuration();
  }
  
  private void func_175517_cu() {
    updateMoveTypeDuration();
    func_175520_cs();
  }
  
  public void func_70636_d() {
    super.func_70636_d();
    if (this.field_175540_bm != this.field_175535_bn) {
      if (this.field_175540_bm == 0 && !this.field_70170_p.field_72995_K)
        this.field_70170_p.func_72960_a((Entity)this, (byte)1); 
      this.field_175540_bm++;
    } else if (this.field_175535_bn != 0) {
      this.field_175540_bm = 0;
      this.field_175535_bn = 0;
    } 
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(3.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3D);
  }
  
  public void func_70014_b(NBTTagCompound nbt) {
    super.func_70014_b(nbt);
    nbt.func_74768_a("RabbitType", getRabbitType());
    nbt.func_74768_a("MoreCarrotTicks", this.carrotTicks);
  }
  
  public void func_70037_a(NBTTagCompound nbt) {
    super.func_70037_a(nbt);
    setRabbitType(nbt.func_74762_e("RabbitType"));
    this.carrotTicks = nbt.func_74762_e("MoreCarrotTicks");
  }
  
  protected String getJumpingSound() {
    return "palamod:mob.rabbit.hop";
  }
  
  protected String func_70639_aQ() {
    return "palamod:mob.rabbit.idle";
  }
  
  protected String func_70621_aR() {
    return "palamod:mob.rabbit.hurt";
  }
  
  protected String func_70673_aS() {
    return "palamod:mob.rabbit.death";
  }
  
  protected void func_70628_a(boolean hitRencently, int fortune) {
    int j = this.field_70146_Z.nextInt(2) + this.field_70146_Z.nextInt(1 + fortune);
    int i;
    for (i = 0; i < j; i++)
      func_145779_a(ModItems.rabbit_hide, 1); 
    j = this.field_70146_Z.nextInt(2);
    for (i = 0; i < j; i++) {
      if (func_70027_ad()) {
        func_145779_a(ModItems.cooked_rabbit, 1);
      } else {
        func_145779_a(ModItems.raw_rabbit, 1);
      } 
    } 
    if (this.field_70146_Z.nextInt(100) <= 10 + fortune)
      func_70099_a(new ItemStack(ModItems.rabbit_foot), 0.0F); 
  }
  
  private boolean isRabbitBreedingItem(Item item) {
    return (item == Items.field_151172_bF || item == Items.field_151150_bK || item == 
      Item.func_150898_a((Block)Blocks.field_150327_N));
  }
  
  public boolean func_70877_b(ItemStack stack) {
    return (stack != null && isRabbitBreedingItem(stack.func_77973_b()));
  }
  
  public boolean func_70652_k(Entity entityIn) {
    if (getRabbitType() >= 98) {
      func_85030_a("mob.attack", 1.0F, (this.field_70146_Z
          .nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F);
      if (getRabbitType() == 99)
        return entityIn.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), 8.0F); 
      if (getRabbitType() == 98)
        return entityIn.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), 10.0F); 
    } 
    return entityIn.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), 3.0F);
  }
  
  public int func_70658_aO() {
    return (getRabbitType() >= 98) ? 8 : super.func_70658_aO();
  }
  
  public byte getRabbitType() {
    return this.field_70180_af.func_75683_a(18);
  }
  
  public void setRabbitType(int type) {
    if (type >= 98) {
      this.field_70714_bg.func_85156_a((EntityAIBase)this.aiAvoidWolves);
      this.field_70714_bg.func_75776_a(4, (EntityAIBase)new AIEvilAttack(this));
      this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
      this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 1, true));
      this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityWolf.class, 1, true));
      if (type >= 98) {
        func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(80.0D);
        func_70606_j(func_110138_aP());
      } 
      if (!func_94056_bM()) {
        String customNameId = null;
        if (type == 99) {
          customNameId = "entity.KillerBunny.name";
        } else if (type == 98) {
          customNameId = "entity.WereRabbit.name";
        } 
        if (customNameId != null)
          func_94058_c("§b" + StatCollector.func_74838_a(customNameId)); 
      } 
    } 
    this.field_70180_af.func_75692_b(18, Byte.valueOf((byte)type));
  }
  
  static class AIEvilAttack extends EntityAIAttackOnCollide {
    public AIEvilAttack(EntityRabbit p_i45867_1_) {
      super((EntityCreature)p_i45867_1_, EntityLivingBase.class, 1.4D, true);
    }
    
    protected double func_179512_a(EntityLivingBase attackTarget) {
      return (4.0F + attackTarget.field_70130_N);
    }
  }
  
  public EntityAgeable func_90011_a(EntityAgeable mate) {
    EntityRabbit baby = new EntityRabbit(this.field_70170_p);
    if (mate instanceof EntityRabbit)
      baby.setRabbitType(
          this.field_70146_Z.nextBoolean() ? getRabbitType() : ((EntityRabbit)mate).getRabbitType()); 
    return (EntityAgeable)baby;
  }
  
  private boolean isCarrotEaten() {
    return (this.carrotTicks == 0);
  }
  
  protected int getMoveTypeDuration() {
    return this.moveType.getDuration();
  }
  
  protected void createRunningParticles() {}
  
  @SideOnly(Side.CLIENT)
  public void func_70103_a(byte id) {
    if (id == 1) {
      createRunningParticles();
      this.field_175535_bn = 10;
      this.field_175540_bm = 0;
    } else {
      super.func_70103_a(id);
    } 
  }
  
  public ItemStack getPickedResult(MovingObjectPosition target) {
    return ModEntityList.getEggFor((Class)getClass());
  }
  
  class AIPanic extends EntityAIPanic {
    private double speed;
    
    private EntityRabbit theEntity = EntityRabbit.this;
    
    public AIPanic(double speed) {
      super((EntityCreature)EntityRabbit.this, speed);
      this.speed = speed;
    }
    
    public void func_75246_d() {
      super.func_75246_d();
      this.theEntity.setMovementSpeed(this.speed);
    }
  }
  
  enum EnumMoveType {
    NONE(0.0F, 0.0F, 30, 1),
    HOP(0.8F, 0.2F, 20, 10),
    STEP(1.0F, 0.45F, 14, 14),
    SPRINT(1.75F, 0.4F, 1, 8),
    ATTACK(2.0F, 0.7F, 7, 8);
    
    private final float speed;
    
    private final float field_180077_g;
    
    private final int duration;
    
    private final int field_180085_i;
    
    EnumMoveType(float typeSpeed, float p_i45866_4_, int typeDuration, int p_i45866_6_) {
      this.speed = typeSpeed;
      this.field_180077_g = p_i45866_4_;
      this.duration = typeDuration;
      this.field_180085_i = p_i45866_6_;
    }
    
    public float getSpeed() {
      return this.speed;
    }
    
    public float func_180074_b() {
      return this.field_180077_g;
    }
    
    public int getDuration() {
      return this.duration;
    }
    
    public int func_180073_d() {
      return this.field_180085_i;
    }
  }
  
  public class RabbitJumpHelper extends EntityJumpHelper {
    private EntityRabbit theEntity;
    
    private boolean field_180068_d = false;
    
    public RabbitJumpHelper(EntityRabbit rabbit) {
      super((EntityLiving)rabbit);
      this.theEntity = rabbit;
    }
    
    public boolean getIsJumping() {
      return EntityRabbit.this.field_70703_bu;
    }
    
    public boolean func_180065_d() {
      return this.field_180068_d;
    }
    
    public void func_180066_a(boolean p_180066_1_) {
      this.field_180068_d = p_180066_1_;
    }
    
    public void func_75661_b() {
      if (EntityRabbit.this.field_70703_bu) {
        this.theEntity.doMovementAction(EntityRabbit.EnumMoveType.STEP);
        EntityRabbit.this.field_70703_bu = false;
      } 
    }
  }
  
  class RabbitMoveHelper extends EntityMoveHelper {
    private EntityRabbit theEntity = EntityRabbit.this;
    
    private double posX;
    
    private double posY;
    
    private double posZ;
    
    public RabbitMoveHelper() {
      super((EntityLiving)EntityRabbit.this);
    }
    
    public void func_75642_a(double p_75642_1_, double p_75642_3_, double p_75642_5_, double p_75642_7_) {
      super.func_75642_a(p_75642_1_, p_75642_3_, p_75642_5_, p_75642_7_);
      this.posX = p_75642_1_;
      this.posY = p_75642_3_;
      this.posZ = p_75642_5_;
    }
    
    public double getX() {
      return this.posX;
    }
    
    public double getY() {
      return this.posY;
    }
    
    public double getZ() {
      return this.posZ;
    }
    
    public void func_75641_c() {
      if (this.theEntity.field_70122_E && !EntityRabbit.this.field_175536_bo)
        this.theEntity.setMovementSpeed(0.0D); 
      super.func_75641_c();
    }
  }
  
  class AIRaidFarm extends EntityAIMoveToBlock {
    private boolean field_179498_d;
    
    private boolean field_179499_e = false;
    
    public AIRaidFarm() {
      super((EntityCreature)EntityRabbit.this, 0.699999988079071D, 16);
    }
    
    public boolean func_75250_a() {
      if (this.runDelay <= 0) {
        if (!EntityRabbit.this.field_70170_p.func_82736_K().func_82766_b("mobGriefing"))
          return false; 
        this.field_179499_e = false;
        this.field_179498_d = EntityRabbit.this.isCarrotEaten();
      } 
      return super.func_75250_a();
    }
    
    public boolean func_75253_b() {
      return (this.field_179499_e && super.func_75253_b());
    }
    
    public void func_75246_d() {
      super.func_75246_d();
      EntityRabbit.this.func_70671_ap().func_75650_a(this.destinationBlock.getX() + 0.5D, (this.destinationBlock.getY() + 1), this.destinationBlock
          .getZ() + 0.5D, 10.0F, EntityRabbit.this.func_70646_bf());
      if (getIsAboveDestination()) {
        World world = EntityRabbit.this.field_70170_p;
        BlockPos blockpos = this.destinationBlock.up();
        Block block = world.func_147439_a(blockpos.getX(), blockpos.getY(), blockpos.getZ());
        int meta = world.func_72805_g(blockpos.getX(), blockpos.getY(), blockpos.getZ());
        if (this.field_179499_e && block instanceof net.minecraft.block.BlockCarrot && meta >= 7) {
          world.func_147480_a(blockpos.getX(), blockpos.getY(), blockpos.getZ(), false);
          EntityRabbit.this.carrotTicks = 100;
        } 
        this.field_179499_e = false;
        this.runDelay = 10;
      } 
    }
    
    protected boolean shouldMoveTo(World world, BlockPos pos) {
      pos = pos.up();
      Block block = world.func_147439_a(pos.getX(), pos.getY(), pos.getZ());
      int meta = world.func_72805_g(pos.getX(), pos.getY(), pos.getZ());
      if (block instanceof net.minecraft.block.BlockCarrot && meta >= 7 && this.field_179498_d && !this.field_179499_e) {
        this.field_179499_e = true;
        return true;
      } 
      return false;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\EntityRabbit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */