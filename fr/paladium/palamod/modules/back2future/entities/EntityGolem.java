package fr.paladium.palamod.modules.back2future.entities;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.ObjectiveType;
import fr.paladium.palajobs.core.network.data.JobsPlayer;
import fr.paladium.palamod.modules.back2future.core.utils.TargetUtils;
import fr.paladium.palamod.modules.back2future.entities.ai.BlockPos;
import fr.paladium.palamod.modules.back2future.entities.projectile.EntityBoulder;
import fr.paladium.palamod.modules.back2future.entities.projectile.EntityCustomFallingBlock;
import fr.paladium.palamod.modules.back2future.entities.projectile.EntityMobThrowable;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityGolem extends EntityMob implements IEntityAdditionalSpawnData {
  public Entity target;
  
  public int attackCounter;
  
  public int deathTicks;
  
  public int count;
  
  public BlockPos targetPos;
  
  public Boolean field_70729_aU;
  
  public float hardness;
  
  public ResourceLocation textureLoc;
  
  public static final int BUILD = 0;
  
  public static final int STAND = 1;
  
  public static final int THROW = 2;
  
  public static final int ROLL = 3;
  
  public static final int STOMP = 4;
  
  public static final int DIE = 5;
  
  private static final int ANI_ID_WATCHER = 17;
  
  public int aniID = 0;
  
  public int prevAniID = 0;
  
  public int aniFrame = 0;
  
  public int textureBlockID = -1;
  
  public static double golemMaxHealthMulti = 100.0D;
  
  public static int golemDmgMulti = 1;
  
  public static int attackCooldown = 40;
  
  public static int golemExpDrop;
  
  public static String[] golemLoot = new String[] { "100|1|mb:itemNote", "1|1|mb:itemNote" };
  
  public List<EntityPlayer> playerhit = new ArrayList<>();
  
  private final EntityAIWander entityAIWander = new EntityAIWander((EntityCreature)this, 0.25D);
  
  boolean CamShake;
  
  float CamShakeIntensity;
  
  public EntityGolem(World par1World) {
    super(par1World);
    this.CamShake = false;
    this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    func_70105_a(2.0F, 6.5F);
    this.field_70728_aV = 10;
    this.field_70178_ae = false;
  }
  
  public boolean func_70610_aX() {
    if (this.aniID == 0)
      return true; 
    return false;
  }
  
  protected boolean func_70692_ba() {
    return false;
  }
  
  public boolean func_70027_ad() {
    return false;
  }
  
  public boolean func_85032_ar() {
    if (this.aniID == 0)
      return true; 
    return false;
  }
  
  protected String func_70639_aQ() {
    return "palamod:golem_living";
  }
  
  protected boolean func_70650_aV() {
    return true;
  }
  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(5000.0D);
    func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(20.0D);
    func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.699D);
    func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(2.0D);
  }
  
  protected void func_70088_a() {
    super.func_70088_a();
    this.field_70180_af.func_75682_a(17, Integer.valueOf(0));
  }
  
  public void func_70636_d() {
    super.func_70636_d();
    getTexture();
    if (this.target == null && !this.field_70170_p.field_72995_K)
      this.target = (Entity)TargetUtils.findRandomVisablePlayer((Entity)this, 20, 4); 
    if (this.aniID == 1 && this.target != null)
      func_70661_as().func_75497_a(this.target, 0.3499999940395355D); 
    if (this.target == null) {
      this.field_70714_bg.func_75776_a(1, (EntityAIBase)this.entityAIWander);
    } else {
      this.field_70714_bg.func_85156_a((EntityAIBase)this.entityAIWander);
    } 
    if (!this.field_70170_p.field_72995_K && this.aniID == 1 && this.target != null)
      attackPicker(); 
    this.aniID = this.field_70180_af.func_75679_c(17);
    this.aniFrame = (this.aniID != this.prevAniID) ? 0 : this.aniFrame;
    this.aniFrame++;
    if (this.aniID == 0 && this.aniFrame == 1) {
      func_85030_a("palamod:golem_build", 4.0F, 1.0F);
    } else if (this.aniID == 0 && this.aniFrame > 90) {
      this.aniFrame = 0;
      this.aniID = 1;
    } else if (this.aniID == 2 && this.aniFrame == 15) {
      if (!this.field_70170_p.field_72995_K)
        throwRock(); 
    } else if (this.aniID == 2 && this.aniFrame > 29) {
      this.target = null;
      this.aniFrame = 0;
      this.aniID = 1;
    } else if (this.aniID == 3 && this.aniFrame > 0 && this.aniFrame < 9) {
      if (this.target != null) {
        func_70625_a(this.target, 360.0F, 0.0F);
        func_70661_as().func_75497_a(this.target, 0.30000001192092896D);
      } 
    } else if (this.aniID == 3 && this.aniFrame == 9) {
      func_85030_a("palamod:golem_roll", 4.0F, 1.0F);
      this.count = 0;
      Vec3 look = func_70040_Z();
      float distance = 20.0F;
      double dx = this.field_70165_t + look.field_72450_a * 20.0D;
      double dy = this.field_70163_u + look.field_72448_b * 20.0D;
      double dz = this.field_70161_v + look.field_72449_c * 20.0D;
      this.targetPos = new BlockPos(dx, dy - 1.0D, dz);
    } else if (this.aniID == 3 && this.aniFrame > 9 && this.aniFrame < 20) {
      List list = this.field_70170_p.func_72872_a(EntityPlayer.class, this.field_70121_D.func_72314_b(1.0D, 0.0D, 1.0D));
      kickEntities(list, 3.0D, 1.0D, this.hardness * golemDmgMulti);
      func_70661_as().func_75492_a(this.targetPos.getX(), this.targetPos.getY(), this.targetPos.getZ(), 1.0D);
    } else if (this.aniID == 3 && this.aniFrame == 20 && this.count < 2) {
      this.aniFrame = 10;
      this.count++;
    } else if (this.aniID == 3 && this.aniFrame > 23) {
      this.target = null;
      this.aniFrame = 0;
      this.aniID = 1;
    } else if (this.aniID == 4 && this.aniFrame > 8 && this.aniFrame < 16) {
      stompAttack();
    } else if (this.aniID == 4 && this.aniFrame > 17) {
      this.aniFrame = 0;
      this.aniID = 1;
      this.target = null;
    } else if (this.aniID == 5 && this.aniFrame == 1) {
      func_85030_a("palamod:golem_build", 4.0F, 1.0F);
      func_85030_a("palamod:golem_living", 4.0F, 1.0F);
    } else if (this.aniID == 5 && this.aniFrame > 54) {
      for (int x = 0; x < 40; x++) {
        float f = (this.field_70146_Z.nextFloat() - 0.5F) * 3.0F;
        float f1 = (this.field_70146_Z.nextFloat() - 0.5F) * 3.0F;
        float f2 = (this.field_70146_Z.nextFloat() - 0.5F) * 3.0F;
        this.field_70170_p.func_72869_a("explode", this.field_70165_t + f, this.field_70163_u + 1.0D + f1, this.field_70161_v + f2, 0.0D, 0.0D, 0.0D);
      } 
      if (!this.field_70170_p.field_72995_K) {
        TargetUtils.dropExp((Entity)this, golemExpDrop);
        TargetUtils.dropLoot((Entity)this, golemLoot);
      } 
      func_70106_y();
    } 
    this.prevAniID = this.aniID;
    if (!this.field_70170_p.field_72995_K)
      this.field_70180_af.func_75692_b(17, Integer.valueOf(this.aniID)); 
  }
  
  public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
    if (!this.field_70170_p.field_72995_K && p_70097_1_.func_76346_g() instanceof EntityPlayer && !this.playerhit.contains(p_70097_1_.func_76346_g()))
      this.playerhit.add((EntityPlayer)p_70097_1_.func_76346_g()); 
    return super.func_70097_a(p_70097_1_, p_70097_2_);
  }
  
  public void attackPicker() {
    if (this.attackCounter <= 0) {
      int pick = TargetUtils.getRanNum(0, 10);
      if (pick < 5) {
        this.aniID = 2;
      } else if (pick >= 5 && pick <= 7) {
        this.aniID = 4;
      } else {
        this.aniID = 3;
      } 
      this.attackCounter = attackCooldown;
      this.field_70180_af.func_75692_b(17, Integer.valueOf(this.aniID));
    } else {
      this.attackCounter--;
    } 
  }
  
  protected void func_70609_aI() {
    this.aniID = 5;
    this.target = null;
    if (!this.field_70170_p.field_72995_K)
      this.field_70180_af.func_75692_b(17, Integer.valueOf(this.aniID)); 
  }
  
  public void func_70645_a(DamageSource p_70645_1_) {
    if (!this.field_70170_p.field_72995_K)
      for (EntityPlayer player : this.playerhit)
        JobsPlayer.get((Entity)player).addXp(JobType.HUNTER, ObjectiveType.ENTITY_KILL, 2000.0D, player);  
    super.func_70645_a(p_70645_1_);
  }
  
  private void kickEntities(List<Entity> par1List, double force, double height, float Damage) {
    for (int i = 0; i < par1List.size(); i++) {
      Entity entity = par1List.get(i);
      if (entity instanceof EntityPlayer) {
        double d0 = (this.field_70121_D.field_72340_a + this.field_70121_D.field_72336_d) / 2.0D;
        double d1 = (this.field_70121_D.field_72339_c + this.field_70121_D.field_72334_f) / 2.0D;
        double d2 = entity.field_70165_t - d0;
        double d3 = entity.field_70161_v - d1;
        double d4 = d2 * d2 + d3 * d3;
        entity.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), Damage);
        entity.func_70024_g(d2 / d4 * force, height, d3 / d4 * force);
      } 
    } 
  }
  
  public void CamShake(Entity entity, float distance, float Intenstity) {
    if (this.field_70170_p.field_72995_K) {
      List<EntityPlayer> players = entity.field_70170_p.func_72872_a(EntityPlayer.class, entity.field_70121_D
          .func_72314_b(distance, 4.0D, distance));
      this.CamShake = !this.CamShake;
      this.CamShakeIntensity = this.CamShake ? Intenstity : -Intenstity;
      for (EntityPlayer player : players)
        player.func_70082_c(0.0F, this.CamShakeIntensity); 
    } 
  }
  
  protected Entity func_70782_k() {
    EntityPlayer entityplayer = this.field_70170_p.func_72890_a((Entity)this, 30.0D);
    return (entityplayer != null && func_70685_l((Entity)entityplayer)) ? (Entity)entityplayer : null;
  }
  
  public AxisAlignedBB func_70046_E() {
    return this.field_70121_D;
  }
  
  public void stompAttack() {
    func_85030_a("mob.ghast.fireball", 4.0F, 1.0F);
    CamShake((Entity)this, 15.0F, 40.0F);
    float i;
    for (i = 0.5F; i < 3.0F; i++) {
      double x = (this.aniFrame - 6) * Math.cos(Math.toRadians((i * 6.0F + this.field_70177_z + 90.0F))) + this.field_70165_t;
      double z = (this.aniFrame - 6) * Math.sin(Math.toRadians((i * 6.0F + this.field_70177_z + 90.0F))) + this.field_70161_v;
      BlockPos pos = new BlockPos(x, this.field_70163_u - 1.0D, z);
      EntityCustomFallingBlock falling = new EntityCustomFallingBlock(this.field_70170_p, (Entity)this, x, this.field_70163_u - 1.0D, z, 0.4000000059604645D, i * 6.0F + this.field_70177_z + 90.0F, pos, (int)(this.hardness / 2.0F * golemDmgMulti));
      if (!this.field_70170_p.field_72995_K)
        this.field_70170_p.func_72838_d((Entity)falling); 
    } 
    for (i = 0.5F; i < 3.0F; i++) {
      double x = (this.aniFrame - 6) * Math.cos(Math.toRadians((i * -6.0F + this.field_70177_z + 90.0F))) + this.field_70165_t;
      double z = (this.aniFrame - 6) * Math.sin(Math.toRadians((i * -6.0F + this.field_70177_z + 90.0F))) + this.field_70161_v;
      BlockPos pos = new BlockPos(x, this.field_70163_u - 1.0D, z);
      EntityCustomFallingBlock falling = new EntityCustomFallingBlock(this.field_70170_p, (Entity)this, x, this.field_70163_u - 1.0D, z, 0.4000000059604645D, i * -6.0F + this.field_70177_z + 90.0F, pos, (int)(this.hardness / 2.0F * golemDmgMulti));
      if (!this.field_70170_p.field_72995_K)
        this.field_70170_p.func_72838_d((Entity)falling); 
    } 
  }
  
  protected void throwRock() {
    float distance = this.target.func_70032_d((Entity)this);
    if (distance > 30.0F) {
      this.target = null;
    } else if (func_70685_l(this.target)) {
      func_70625_a(this.target, 10.0F, 10.0F);
      EntityLivingBase ent = (EntityLivingBase)this.target;
      if (this.aniID == 2 && this.aniFrame == 15) {
        float dmg = this.hardness * golemDmgMulti;
        EntityMobThrowable entityMobThrowable = (new EntityBoulder(this.field_70170_p, (EntityLivingBase)this, ent, 1.5F, 0.0F, 2.4F, -1.6F, 0.0F, 1.0F, 1.0F)).setDamage(dmg);
        if (!this.field_70170_p.field_72995_K)
          this.field_70170_p.func_72838_d((Entity)entityMobThrowable); 
      } 
    } 
  }
  
  public void getTexture() {
    if (this.textureBlockID == -1) {
      BlockPos blockPos = new BlockPos(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      while (!this.field_70170_p.func_147439_a(blockPos.getX(), blockPos.getY(), blockPos.getZ()).func_149688_o().func_76230_c())
        blockPos = blockPos.down(); 
      Block iblockstate = this.field_70170_p.func_147439_a(blockPos.getX(), blockPos.getY(), blockPos.getZ());
      this.textureBlockID = Block.func_149682_b(iblockstate);
      this.hardness = iblockstate.func_149712_f(this.field_70170_p, blockPos.getX(), blockPos.getY(), blockPos
          .getZ());
      func_110148_a(SharedMonsterAttributes.field_111267_a)
        .func_111128_a(golemMaxHealthMulti * this.hardness);
      func_70691_i((float)(golemMaxHealthMulti * this.hardness));
    } 
  }
  
  public void func_70020_e(NBTTagCompound compound) {
    super.func_70020_e(compound);
    this.textureBlockID = compound.func_74762_e("textureBlockID");
    this.hardness = compound.func_74760_g("hardness");
    this.field_70180_af.func_75692_b(17, Integer.valueOf(1));
    if (this.field_70170_p.field_72995_K) {
      Block iblockstate = Block.func_149729_e(this.textureBlockID);
      String string = iblockstate.func_149691_a(0, 0).func_94215_i() + ".png";
      String[] parts = string.split(":");
      if (parts.length == 1) {
        String[] partz = new String[2];
        partz[1] = parts[0];
        partz[0] = "minecraft";
        parts = partz;
      } 
      this.textureLoc = new ResourceLocation(parts[0] + ":textures/blocks/" + parts[1]);
    } 
  }
  
  public void func_70109_d(NBTTagCompound compound) {
    super.func_70109_d(compound);
    getTexture();
    compound.func_74768_a("textureBlockID", this.textureBlockID);
    compound.func_74776_a("hardness", this.hardness);
  }
  
  public void writeSpawnData(ByteBuf buffer) {
    getTexture();
    ByteBufUtils.writeVarInt(buffer, this.textureBlockID, 4);
  }
  
  public void readSpawnData(ByteBuf additionalData) {
    this.textureBlockID = ByteBufUtils.readVarInt(additionalData, 4);
    if (this.field_70170_p.field_72995_K) {
      Block iblockstate = Block.func_149729_e(this.textureBlockID);
      String string = iblockstate.func_149691_a(0, 0).func_94215_i() + ".png";
      String[] parts = string.split(":");
      if (parts.length == 1) {
        String[] partz = new String[2];
        partz[1] = parts[0];
        partz[0] = "minecraft";
        parts = partz;
      } 
      this.textureLoc = new ResourceLocation(parts[0] + ":textures/blocks/" + parts[1]);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\EntityGolem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */