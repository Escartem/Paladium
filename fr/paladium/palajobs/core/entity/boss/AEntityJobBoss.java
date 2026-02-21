package fr.paladium.palajobs.core.entity.boss;

import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.entity.boss.attack.BaseAttack;
import fr.paladium.palajobs.core.entity.gecko.AnimatedEntityMob;
import fr.paladium.palajobs.core.entity.gecko.animation.AnimationType;
import fr.paladium.palajobs.core.pojo.boss.BossState;
import fr.paladium.palajobs.core.utils.DurationConverter;
import fr.paladium.palajobs.core.utils.TimeUtil;
import fr.paladium.palajobs.server.managers.JobsManager;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public abstract class AEntityJobBoss extends AnimatedEntityMob implements IBossDisplayData {
  public JobType type;
  
  public double speed;
  
  public double health;
  
  public float knockback;
  
  public float explosion;
  
  public float field_70151_c;
  
  public int attackDelay;
  
  public boolean activation;
  
  public List<BaseAttack<AEntityJobBoss>> attacks;
  
  public AEntityJobBoss(World world, JobType type, double speed, double health, float knockback, float explosion, float fire, int attackDelay) {
    super(world);
    this.type = type;
    this.speed = speed;
    this.health = health;
    this.knockback = knockback;
    this.explosion = explosion;
    this.field_70151_c = fire;
    this.attackDelay = attackDelay;
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(this.health);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.1D * this.speed);
    func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(100.0D);
    func_70606_j(func_110138_aP());
    this.attacks = new ArrayList<>();
    this.field_70724_aR = this.attackDelay;
  }
  
  public void func_70088_a() {
    super.func_70088_a();
    this.field_70180_af.func_75682_a(30, Byte.valueOf((byte)0));
  }
  
  public void func_70037_a(NBTTagCompound nbt) {
    super.func_70037_a(nbt);
    if (nbt.func_74764_b("palajobs")) {
      NBTTagCompound constantNbt = nbt.func_74775_l("palajobs");
      this.type = JobType.values()[constantNbt.func_74762_e("jobType")];
      this.speed = constantNbt.func_74769_h("speed");
      this.health = constantNbt.func_74769_h("health");
      this.knockback = constantNbt.func_74760_g("knockback");
      this.explosion = constantNbt.func_74760_g("explosion");
      this.field_70151_c = constantNbt.func_74760_g("fire");
      this.attackDelay = constantNbt.func_74762_e("attackDelay");
    } 
  }
  
  public void func_70014_b(NBTTagCompound nbt) {
    super.func_70014_b(nbt);
    NBTTagCompound constantNbt = new NBTTagCompound();
    constantNbt.func_74768_a("jobType", this.type.ordinal());
    constantNbt.func_74780_a("speed", this.speed);
    constantNbt.func_74780_a("health", this.health);
    constantNbt.func_74776_a("knockback", this.knockback);
    constantNbt.func_74776_a("explosion", this.explosion);
    constantNbt.func_74776_a("fire", this.field_70151_c);
    constantNbt.func_74768_a("attackDelay", this.attackDelay);
    nbt.func_74782_a("palajobs", (NBTBase)constantNbt);
  }
  
  public void func_70071_h_() {
    super.func_70071_h_();
    if (getDefaultAnimation(AnimationType.IDLE).equals("spawn_idle") && isActivated())
      activate(); 
    if (!this.field_70170_p.field_72995_K) {
      if (!this.activation && (JobsManager.getInstance().getApi() == null || JobsManager.getInstance().getBossData() == null || (JobsManager.getInstance().getApi() != null && JobsManager.getInstance().getBossData() != null && (JobsManager.getInstance().getBossData()).state == BossState.SPAWNED))) {
        this.activation = true;
        playAnimation("palajobs:palajobs.boss.activate", "activation", 950L, true).setCallback(e -> {
              this.field_70180_af.func_75692_b(30, Byte.valueOf((byte)1));
              func_94058_c("§r" + StatCollector.func_74838_a("entity." + EntityList.func_75621_b((Entity)this) + ".name"));
            });
      } 
      if (JobsManager.getInstance().getApi() != null && JobsManager.getInstance().getBossData() != null && (JobsManager.getInstance().getBossData()).state == BossState.SPAWNING) {
        long diff = (JobsManager.getInstance().getBossData()).timestamp - TimeUtil.now();
        if (diff > 0L) {
          func_94058_c("§b" + DurationConverter.fromMillisToString(diff * 1000L));
        } else {
          int tick = this.field_70173_aa % 40;
          func_94058_c("§6Activation" + ((tick < 10) ? "" : ((tick < 20) ? "." : ((tick < 30) ? ".." : "..."))));
        } 
      } 
      if (this.activation && !isActivated())
        func_94058_c("§6§kActivation en cours"); 
      if (!isAnimated() && isMovingAnimation() && this.field_70173_aa % 20 == 0)
        playSound("palajobs.boss.step"); 
    } 
    if (this.field_70170_p.field_72995_K)
      return; 
    if (isDeathAnimation() || this.field_70724_aR > 0 || this.attacks.isEmpty() || !isActivated())
      return; 
    BaseAttack<AEntityJobBoss> attack = getRandomAttack();
    if (attack == null) {
      this.field_70724_aR = this.attackDelay;
      return;
    } 
    int attemptsFailed = 0;
    boolean attackSuccess = attack.perform((Entity)this);
    while (!attackSuccess && attemptsFailed < 5) {
      attack = getRandomAttack();
      attackSuccess = attack.perform((Entity)this);
      attemptsFailed++;
    } 
    this.field_70724_aR = this.attackDelay + ((attack != null) ? attack.getDuration() : 0);
  }
  
  public void activate() {}
  
  public BaseAttack<AEntityJobBoss> getRandomAttack() {
    int random = this.field_70170_p.field_73012_v.nextInt(100);
    int lastRandom = 0;
    for (BaseAttack<AEntityJobBoss> attack : this.attacks) {
      lastRandom += attack.getProbability();
      if (random < lastRandom)
        return attack; 
    } 
    return null;
  }
  
  public boolean func_70097_a(DamageSource source, float damage) {
    boolean success = super.func_70097_a(source, damage);
    if (success && source.func_76364_f() instanceof net.minecraft.entity.player.EntityPlayer)
      func_70624_b((EntityLivingBase)source.func_76364_f()); 
    return success;
  }
  
  public void knockback(Entity entity, float horizontal, float vertical) {
    if (!(entity instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP pl = (EntityPlayerMP)entity;
    pl.field_70160_al = true;
    float str = horizontal;
    pl.field_70159_w += (pl.field_70165_t - this.field_70165_t > 0.0D) ? str : -str;
    pl.field_70181_x += vertical;
    pl.field_70179_y += (pl.field_70161_v - this.field_70161_v > 0.0D) ? str : -str;
    pl.field_71135_a.func_147359_a((Packet)new S12PacketEntityVelocity((Entity)pl));
  }
  
  public void func_70653_a(Entity entity, float p1, double p2, double p3) {
    this.field_70160_al = true;
    float f1 = MathHelper.func_76133_a(p2 * p2 + p3 * p3);
    float f2 = 0.4F * this.knockback;
    this.field_70159_w /= 2.0D;
    this.field_70181_x /= 2.0D;
    this.field_70179_y /= 2.0D;
    this.field_70159_w -= p2 / f1 * f2;
    this.field_70181_x += f2;
    this.field_70179_y -= p3 / f1 * f2;
    if (this.field_70181_x > 0.4000000059604645D)
      this.field_70181_x = 0.4000000059604645D; 
  }
  
  public void func_70665_d(DamageSource source, float p1) {
    if (source.func_94541_c()) {
      p1 *= this.explosion;
    } else if (source.func_76347_k()) {
      p1 *= this.field_70151_c;
    } 
    super.func_70665_d(source, p1);
  }
  
  public boolean isActivated() {
    return (this.field_70180_af.func_75683_a(30) == 1);
  }
  
  public String func_70639_aQ() {
    return "palajobs:palajobs.boss.idle";
  }
  
  public String func_70621_aR() {
    return "palajobs:palajobs.boss.damage";
  }
  
  protected String func_70673_aS() {
    return "palajobs:palajobs.boss.death";
  }
  
  public void playSound(String sound) {
    func_85030_a("palajobs:" + sound, func_70599_aP(), 1.0F);
  }
  
  public float func_70599_aP() {
    return 0.4F;
  }
  
  public boolean func_70650_aV() {
    return true;
  }
  
  public boolean func_85032_ar() {
    return ((!isActivated() && !this.activation) || super.func_85032_ar());
  }
  
  public void func_70623_bb() {}
  
  public boolean func_70104_M() {
    return false;
  }
  
  public boolean func_82171_bF() {
    return false;
  }
  
  public void func_82167_n(Entity entity) {}
  
  public void func_85033_bc() {}
  
  public boolean func_70692_ba() {
    return false;
  }
  
  public boolean func_90999_ad() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\entity\boss\AEntityJobBoss.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */