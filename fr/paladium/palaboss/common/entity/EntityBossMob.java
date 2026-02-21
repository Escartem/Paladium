package fr.paladium.palaboss.common.entity;

import fr.paladium.palaboss.common.entity.ai.attack.ABaseAttack;
import fr.paladium.palaboss.common.entity.ai.task.EntityAIMoveTarget;
import fr.paladium.palaboss.common.entity.ai.task.EntityAINearestPlayerTarget;
import fr.paladium.palaboss.common.entity.properties.EntityProperties;
import fr.paladium.palaboss.common.reward.IReward;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.CustomInstructionKeyframeEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.entity.impl.AnimatedEntityMob;

public class EntityBossMob extends AnimatedEntityMob implements AnimationController.ICustomInstructionListener<EntityBossMob> {
  private HashMap<UUID, Float> damager;
  
  private final LinkedList<IReward<EntityBossMob>> rewards;
  
  private EntityProperties properties;
  
  private ABaseAttack<? extends EntityBossMob> currentAttack;
  
  public HashMap<UUID, Float> getDamager() {
    return this.damager;
  }
  
  public LinkedList<IReward<EntityBossMob>> getRewards() {
    return this.rewards;
  }
  
  public EntityProperties getProperties() {
    return this.properties;
  }
  
  public ABaseAttack<? extends EntityBossMob> getCurrentAttack() {
    return this.currentAttack;
  }
  
  private final long attackStartTime = 0L;
  
  private final List<ABaseAttack<? extends EntityBossMob>> attacks;
  
  public long getAttackStartTime() {
    getClass();
    return 0L;
  }
  
  public List<ABaseAttack<? extends EntityBossMob>> getAttacks() {
    return this.attacks;
  }
  
  private final LinkedList<ABaseAttack<? extends EntityBossMob>> attackQueue = new LinkedList<>();
  
  public LinkedList<ABaseAttack<? extends EntityBossMob>> getAttackQueue() {
    return this.attackQueue;
  }
  
  public EntityBossMob(World world) {
    this(world, EntityProperties.DEFAULT);
  }
  
  public EntityBossMob(World world, EntityProperties entityProperties) {
    super(world);
    this.damager = new HashMap<>();
    this.rewards = new LinkedList<>();
    this.properties = entityProperties;
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(this.properties.getHealth());
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.1D * this.properties.getSpeed());
    func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(this.properties.getFollowRange());
    func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(this.properties.getKnockback());
    func_70606_j(func_110138_aP());
    this.attacks = new ArrayList<>();
    this.field_70724_aR = this.properties.getAttackDelay();
    this.field_70715_bh.func_75776_a(0, (EntityAIBase)new EntityAINearestPlayerTarget((EntityCreature)this));
    this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAIMoveTarget((EntityCreature)this));
    this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0D));
    registerRewards();
  }
  
  public EntityBossMob(World world, double x, double y, double z) {
    this(world, x, y, z, EntityProperties.DEFAULT);
  }
  
  public EntityBossMob(World world, double x, double y, double z, EntityProperties entityProperties) {
    this(world, entityProperties);
    func_70107_b(x, y, z);
  }
  
  public void func_70037_a(NBTTagCompound nbt) {
    super.func_70037_a(nbt);
    if (nbt.func_74764_b("bossProperties"))
      this.properties = EntityProperties.fromNBT(nbt.func_74775_l("bossProperties")); 
    NBTTagCompound nbtDamagerMap = nbt.func_74775_l("damager");
    HashMap<UUID, Float> damagerMap = new HashMap<>();
    int length = nbtDamagerMap.func_74762_e("length");
    for (int i = 0; i < length; i++) {
      String key = nbtDamagerMap.func_74779_i(i + "-key");
      Float value = Float.valueOf(nbtDamagerMap.func_74760_g(i + "-value"));
      damagerMap.put(UUIDUtils.from(key), value);
    } 
    this.damager = damagerMap;
  }
  
  public void func_70014_b(NBTTagCompound nbt) {
    super.func_70014_b(nbt);
    NBTTagCompound bossTag = new NBTTagCompound();
    this.properties.writeNBT(bossTag);
    nbt.func_74782_a("bossProperties", (NBTBase)bossTag);
    NBTTagCompound damagerHashMap = new NBTTagCompound();
    damagerHashMap.func_74768_a("length", this.damager.size());
    int i = 0;
    for (Map.Entry<UUID, Float> entry : this.damager.entrySet()) {
      damagerHashMap.func_74778_a(i + "-key", UUIDUtils.toString(entry.getKey()));
      damagerHashMap.func_74776_a(i + "-value", ((Float)entry.getValue()).floatValue());
      i++;
    } 
    nbt.func_74782_a("damager", (NBTBase)damagerHashMap);
  }
  
  public void func_70071_h_() {
    super.func_70071_h_();
    if (this.field_70170_p.field_72995_K || getAnimated().isDeathAnimation() || this.attacks.isEmpty())
      return; 
    if (!this.attackQueue.isEmpty() && this.currentAttack == null) {
      this.currentAttack = this.attackQueue.poll();
      return;
    } 
    if (this.currentAttack != null) {
      if (this.field_70724_aR <= 0) {
        this.currentAttack.onStart();
        this.field_70724_aR += this.currentAttack.getDuration() + getProperties().getAttackDelay();
      } 
      this.currentAttack.perform();
      if (this.field_70724_aR == getProperties().getAttackDelay()) {
        this.currentAttack.onEnd();
        this.currentAttack = null;
      } 
      return;
    } 
    if (this.field_70724_aR > 0)
      return; 
    if (this.attackQueue.isEmpty())
      pushRandomAttack(); 
  }
  
  public void pushRandomAttack() {
    List<ABaseAttack<? extends EntityBossMob>> possibleAttacks = new ArrayList<>();
    int totalWeight = 0;
    for (ABaseAttack<? extends EntityBossMob> attack : this.attacks) {
      if (attack.getProbability() > 0 && attack.canPerform()) {
        possibleAttacks.add(attack);
        totalWeight += attack.getProbability();
      } 
    } 
    if (possibleAttacks.isEmpty() || totalWeight == 0)
      return; 
    if (possibleAttacks.size() == 1) {
      this.attackQueue.add(possibleAttacks.get(0));
      return;
    } 
    int random = this.field_70170_p.field_73012_v.nextInt(totalWeight) + 1;
    int sumWeight = 0;
    for (ABaseAttack<? extends EntityBossMob> attack : possibleAttacks) {
      sumWeight += attack.getProbability();
      if (random <= sumWeight) {
        this.attackQueue.add(attack);
        break;
      } 
    } 
  }
  
  public <T extends EntityBossMob> void addAttack(ABaseAttack<T> attack) {
    this.attacks.add(attack);
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
  
  public void func_70665_d(DamageSource source, float p1) {
    if (source.func_94541_c()) {
      p1 *= this.properties.getExplosion();
    } else if (source.func_76347_k()) {
      p1 *= this.properties.getFire();
    } else if (source.func_76364_f() instanceof net.minecraft.entity.player.EntityPlayer) {
      UUID playerUUID = source.func_76364_f().func_110124_au();
      if (this.damager.containsKey(playerUUID)) {
        this.damager.put(playerUUID, Float.valueOf(((Float)this.damager.get(playerUUID)).floatValue() + p1));
      } else {
        this.damager.put(playerUUID, Float.valueOf(p1));
      } 
    } 
    super.func_70665_d(source, p1);
  }
  
  public void func_70645_a(DamageSource p_70645_1_) {
    super.func_70645_a(p_70645_1_);
    giveRewards();
  }
  
  private void giveRewards() {
    if (this.field_70170_p.field_72995_K)
      return; 
    this.rewards.forEach(reward -> this.damager.forEach(()));
  }
  
  public void registerRewards() {}
  
  public void registerReward(IReward<? extends EntityBossMob> reward) {
    this.rewards.add(reward);
  }
  
  public void playSound(String modid, String sound) {
    func_85030_a(modid + ":" + sound, func_70599_aP(), 1.0F);
  }
  
  public float func_70599_aP() {
    return 0.4F;
  }
  
  public void registerControllers(AnimationData data) {
    super.registerControllers(data);
    AnimationController controller = (AnimationController)data.getAnimationControllers().get("animationController");
    if (controller != null)
      controller.registerCustomInstructionListener(this); 
  }
  
  public void executeInstruction(CustomInstructionKeyframeEvent<EntityBossMob> event) {
    if (this.currentAttack != null)
      this.currentAttack.onAnimationKeyframe(event); 
  }
  
  public void clearSpeed() {
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0D);
  }
  
  public void resetSpeed() {
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.1D * getProperties().getSpeed());
  }
  
  public boolean func_70650_aV() {
    return true;
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


/* Location:              E:\Paladium\!\fr\paladium\palaboss\common\entity\EntityBossMob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */