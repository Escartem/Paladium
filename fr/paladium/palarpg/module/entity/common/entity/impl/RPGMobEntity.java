package fr.paladium.palarpg.module.entity.common.entity.impl;

import com.eliotlash.molang.MolangParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaboss.common.entity.EntityBossMob;
import fr.paladium.palaboss.common.entity.ai.attack.ABaseAttack;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.entity.client.loader.RPGEntityModel;
import fr.paladium.palarpg.module.entity.client.loader.cache.RPGEntityModelCache;
import fr.paladium.palarpg.module.entity.common.condition.IMolangParserHolder;
import fr.paladium.palarpg.module.entity.common.entity.IRPGEntity;
import fr.paladium.palarpg.module.entity.common.entity.RPGElementType;
import fr.paladium.palarpg.module.entity.common.entity.attack.AttackManager;
import fr.paladium.palarpg.module.entity.common.entity.attack.ISummonEntityAttack;
import fr.paladium.palarpg.module.entity.common.entity.behavior.ABehavior;
import fr.paladium.palarpg.module.entity.common.entity.behavior.BehaviorManager;
import fr.paladium.palarpg.module.entity.common.entity.behavior.data.BoostMutator;
import fr.paladium.palarpg.module.entity.server.loader.data.RPGEntityData;
import fr.paladium.palarpg.module.entity.server.loader.data.util.RPGAttack;
import fr.paladium.palarpg.module.entity.server.loader.data.util.RPGDefaultStat;
import fr.paladium.palarpg.module.stat.common.playerdata.RPGStatPlayerData;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.AStatCapability;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.EnumStats;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.StatCapabilityMutator;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.StatMutationType;
import fr.paladium.palarpg.module.stat.common.playerdata.capability.TimedStatCapabilityMutator;
import fr.paladium.zephyrui.lib.animation.animator.TweenAnimator;
import fr.paladium.zephyrui.lib.resource.Resource;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import software.bernie.geckolib3.entity.animation.Animation;
import software.bernie.geckolib3.entity.animation.AnimationType;
import software.bernie.geckolib3.model.impl.model.LindwormModel;

public class RPGMobEntity extends EntityBossMob implements IRPGEntity, IEntityAdditionalSpawnData, IMolangParserHolder {
  private static final Gson GSON = (new GsonBuilder()).create();
  
  private static final int DATA_BOOST = 20;
  
  private final MolangParser molangParser = new MolangParser();
  
  private RPGEntityData rpgData;
  
  public RPGEntityData getRpgData() {
    return this.rpgData;
  }
  
  private final List<ABehavior> behaviors = new ArrayList<>();
  
  private boolean hasSpawn = false;
  
  @SideOnly(Side.CLIENT)
  private TweenAnimator scaleAnimator;
  
  @SideOnly(Side.CLIENT)
  private LindwormModel<RPGMobEntity> lindwormModel;
  
  public TweenAnimator getScaleAnimator() {
    return this.scaleAnimator;
  }
  
  public LindwormModel<RPGMobEntity> getLindwormModel() {
    return this.lindwormModel;
  }
  
  public RPGMobEntity(World world) {
    super(world);
    this.field_70715_bh.field_75782_a.clear();
    registerMolangVariables(new String[] { "entity.tick_existed", "entity.health", "entity.max_health", "entity.health_percentage", "entity.is_boosted" });
  }
  
  public RPGMobEntity(World world, RPGEntityData entityRpgData) {
    super(world, entityRpgData.getProperties());
    this.rpgData = entityRpgData;
    setSize();
    try {
      loadAnimation();
    } catch (Exception e) {
      throw new RuntimeException("An error occurred while loading animations for the entity with RPG ID " + this.rpgData.getId(), e);
    } 
    try {
      loadAttacks();
    } catch (Exception e) {
      throw new RuntimeException("An error occurred while loading attacks for the entity with RPG ID " + this.rpgData.getId(), e);
    } 
    try {
      loadEEP();
    } catch (Exception e) {
      throw new RuntimeException("An error occurred while loading EEP stats for the entity with RPG ID " + this.rpgData.getId(), e);
    } 
    try {
      loadBehaviors();
    } catch (Exception e) {
      throw new RuntimeException("An error occurred while loading behaviors for the entity with RPG ID " + this.rpgData.getId(), e);
    } 
    this.field_70138_W = this.rpgData.getStepHeight();
    this.field_70715_bh.field_75782_a.clear();
    this.field_70714_bg.field_75782_a.clear();
    this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0D));
    registerMolangVariables(new String[] { "entity.tick_existed", "entity.health", "entity.max_health", "entity.health_percentage", "entity.is_boosted" });
    if (world.field_72995_K)
      loadModel(this.rpgData.getModel()); 
  }
  
  protected void func_70088_a() {
    super.func_70088_a();
    func_70096_w().func_75682_a(20, Byte.valueOf((byte)0));
  }
  
  public void func_70071_h_() {
    if (this.rpgData == null) {
      func_70106_y();
      return;
    } 
    if (func_70638_az() != null && (
      !func_70638_az().func_70089_S() || (func_70638_az() instanceof EntityPlayer && ((EntityPlayer)func_70638_az()).field_71075_bZ.field_75102_a)))
      func_70624_b((EntityLivingBase)null); 
    if (!this.field_70170_p.field_72995_K && getRpgData().hasSpawnAnimation() && !this.hasSpawn) {
      this.hasSpawn = true;
      playAnimation(getRpgData().getSpawnAnimation(), getRpgData().getSpawnAnimationDuration(), true);
    } 
    setMolangVariable("entity.tick_existed", this.field_70173_aa);
    setMolangVariable("entity.health", func_110143_aJ());
    setMolangVariable("entity.max_health", func_110138_aP());
    setMolangVariable("entity.health_percentage", (func_110143_aJ() / func_110138_aP()));
    setMolangVariable("entity.is_boosted", isBoosted() ? 1.0D : 0.0D);
    this.behaviors.forEach(behavior -> {
          if (behavior.canExecute())
            behavior.execute(); 
        });
    super.func_70071_h_();
  }
  
  public void func_70037_a(NBTTagCompound nbt) {
    super.func_70037_a(nbt);
    if (nbt.func_74764_b("rpgdata")) {
      this.rpgData = (RPGEntityData)GSON.fromJson(nbt.func_74779_i("rpgdata"), RPGEntityData.class);
      if (this.rpgData == null)
        return; 
    } 
    if (nbt.func_74764_b("hasSpawn"))
      this.hasSpawn = nbt.func_74767_n("hasSpawn"); 
    loadAttacks();
    loadBehaviors();
    loadAnimation();
    setSize();
    this.field_70138_W = this.rpgData.getStepHeight();
  }
  
  public void func_70014_b(NBTTagCompound nbt) {
    super.func_70014_b(nbt);
    if (this.rpgData != null)
      nbt.func_74778_a("rpgdata", GSON.toJson(this.rpgData)); 
    nbt.func_74757_a("hasSpawn", this.hasSpawn);
  }
  
  protected void func_70626_be() {}
  
  public void func_70778_a(PathEntity p_70778_1_) {}
  
  public void setSize() {
    func_70105_a(this.rpgData.getSize().getWidth() * this.rpgData.getSize().getScale(), this.rpgData.getSize().getHeight() * this.rpgData.getSize().getScale());
    if (this.field_70170_p.field_72995_K)
      this.scaleAnimator = TweenAnimator.create(this.rpgData.getSize().getScale()); 
  }
  
  private void loadEEP() {
    RPGStatPlayerData rpgStats = (RPGStatPlayerData)RPGPlayer.getData((Entity)this, "stats");
    if (rpgStats == null)
      return; 
    this.rpgData.getDefaultStats().forEach(defaultStat -> {
          if (defaultStat == null)
            throw new RuntimeException("A default stat is null for the entity with RPG ID " + this.rpgData.getId()); 
          if (defaultStat.getStat() == null)
            throw new RuntimeException("A default stat has a null stat for the entity with RPG ID " + this.rpgData.getId()); 
          AStatCapability<Number> capability = rpgStats.getCapabilityByName(defaultStat.getStat());
          if (capability == null)
            return; 
          capability.setDefaultValue(Float.valueOf(defaultStat.getValue()));
        });
  }
  
  private void loadAttacks() {
    getAttacks().clear();
    this.rpgData.getAttacks().forEach(rpgAttack -> addAttack((ABaseAttack)AttackManager.getAttack(rpgAttack.getAttackId(), rpgAttack, this)));
  }
  
  private void loadBehaviors() {
    this.behaviors.clear();
    getRpgData().getBehaviors().forEach((behaviorID, datas) -> this.behaviors.add(BehaviorManager.getBehavior(behaviorID, datas, this)));
  }
  
  @SideOnly(Side.CLIENT)
  public void loadModel(String modelId) {
    RPGEntityModel model = RPGEntityModelCache.get(modelId);
    if (model == null)
      throw new RuntimeException("No model found for the model id " + modelId); 
    if (!model.hasAnimation())
      throw new RuntimeException("No animation found for the model id " + modelId + ", an animation file is required for mobs"); 
    this.lindwormModel = new LindwormModel(model.getGeoModel(), model.getAnimationFile(), Resource.of((ResourceLocation)model.getTextureResource()).nearest(), (m, e) -> (RPGMobEntity)e);
  }
  
  public RPGElementType getRPGType() {
    return this.rpgData.getType();
  }
  
  public float getRPGExperienceOnKill() {
    return this.rpgData.getExperience();
  }
  
  public void writeSpawnData(ByteBuf buffer) {
    if (this.rpgData == null)
      return; 
    ByteBufUtils.writeUTF8String(buffer, GSON.toJson(this.rpgData));
  }
  
  public void readSpawnData(ByteBuf additionalData) {
    if (!additionalData.isReadable())
      return; 
    String rpgDataJson = ByteBufUtils.readUTF8String(additionalData);
    if (rpgDataJson == null || rpgDataJson.isEmpty())
      return; 
    this.rpgData = (RPGEntityData)GSON.fromJson(rpgDataJson, RPGEntityData.class);
    if (this.rpgData == null)
      return; 
    setSize();
    this.field_70138_W = this.rpgData.getStepHeight();
    loadAnimation();
    loadBehaviors();
    loadModel(getRpgData().getModel());
  }
  
  public void func_70624_b(EntityLivingBase entity) {
    if (entity instanceof EntityPlayer && !getRpgData().isCanTargetPlayer())
      return; 
    super.func_70624_b(entity);
  }
  
  public boolean func_70097_a(DamageSource source, float damage) {
    if (source.func_76347_k() && getRpgData().isImmuneToFire())
      return false; 
    return super.func_70097_a(source, damage);
  }
  
  public void func_70106_y() {
    super.func_70106_y();
    getAttacks().forEach(attack -> {
          if (attack instanceof ISummonEntityAttack) {
            ISummonEntityAttack summonAttack = (ISummonEntityAttack)attack;
            if (summonAttack.killEntitiesOnDeath() && summonAttack.hasEntitiesSpawned())
              summonAttack.getEntitiesSpawned().forEach(Entity::func_70106_y); 
          } 
        });
  }
  
  public float func_70047_e() {
    return this.field_70131_O * 0.8F;
  }
  
  private void loadAnimation() {
    setDefaultAnimation(AnimationType.IDLE, new String[] { "idle" });
    if (getProperties() != null && getProperties().getSpeed() > 0.0D)
      setDefaultAnimation(AnimationType.WALK, new String[] { "walk" }); 
    setDefaultAnimation(AnimationType.DEATH, new Animation[] { new Animation("death", getRpgData().getDeathAnimationDuration()) });
    setDefaultAnimation(AnimationType.HURT, new String[] { "hurt" });
  }
  
  public boolean isBoosted() {
    return (func_70096_w().func_75683_a(20) == 1);
  }
  
  public void boost(long duration, Map<String, BoostMutator> boostEffects) {
    if (isBoosted() || this.field_70170_p.field_72995_K || boostEffects.isEmpty())
      return; 
    func_70096_w().func_75692_b(20, Byte.valueOf((byte)1));
    boostEffects.forEach((statName, boost) -> {
          if ("ATTACK_SPEED".equalsIgnoreCase(statName)) {
            if (boost.getValue() instanceof Double) {
              double value = ((Double)boost.getValue()).doubleValue();
              int defaultAttackDelay = getProperties().getAttackDelay();
              int newAttackDelay = (boost.getType() == StatMutationType.ADDITIVE) ? (int)(defaultAttackDelay + value) : (int)(defaultAttackDelay * value);
              getProperties().setAttackDelay(newAttackDelay);
              (new Thread((), "RPGMobEntity/AttackDelayBoostThread")).start();
            } 
          } else if ("SPEED".equalsIgnoreCase(statName)) {
            if (boost.getValue() instanceof Double) {
              double value = ((Double)boost.getValue()).doubleValue();
              double defaultSpeed = getProperties().getSpeed();
              double newSpeed = (boost.getType() == StatMutationType.ADDITIVE) ? (defaultSpeed + value) : (defaultSpeed * (1.0D + value));
              getProperties().setSpeed(newSpeed);
              func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(newSpeed / 100.0D);
              (new Thread((), "RPGMobEntity/SpeedBoostThread")).start();
            } 
          } else {
            EnumStats stat = EnumStats.fromString(statName);
            if (stat == null)
              throw new RuntimeException("The stat '" + statName + "' doesn't exist"); 
            RPGStatPlayerData statData = (RPGStatPlayerData)RPGPlayer.getData((Entity)this, "stats");
            if (statData == null)
              throw new RuntimeException("Something went wrong when fetching entity stats data"); 
            AStatCapability<Object> statCapability = statData.getCapabilityByName(stat);
            if (statCapability == null)
              throw new RuntimeException("Something went wrong when fetching entity stats capability data"); 
            int boostDuration = (int)Math.floor((duration / 1000L * 20L));
            TimedStatCapabilityMutator<Object> timedCapability = (TimedStatCapabilityMutator<Object>)TimedStatCapabilityMutator.create().setTick(boostDuration).setCallback(()).setId("BOOST_" + stat.getStatName() + "_" + boost.getType().name()).setValue(boost.getValue()).setMutationType(boost.getType());
            statCapability.addMutator((StatCapabilityMutator)timedCapability);
          } 
        });
  }
  
  public void addMaxHealth(float maxHealth) {
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a((func_110138_aP() + maxHealth));
    func_70606_j(func_110143_aJ() + maxHealth);
  }
  
  public boolean hasBehavior(String id) {
    return this.rpgData.getBehaviors().containsKey(id);
  }
  
  public Set<String> getBehaviorsName() {
    return this.rpgData.getBehaviors().keySet();
  }
  
  public <T extends ABehavior> Optional<T> getBehavior(String id) {
    if (!hasBehavior(id))
      return Optional.empty(); 
    return this.behaviors.stream().filter(b -> b.getID().equalsIgnoreCase(id)).findFirst();
  }
  
  public void setSizeScale(float newScale) {
    getRpgData().getSize().setScale(newScale);
    func_70105_a(getRpgData().getSize().getWidth() * newScale, getRpgData().getSize().getHeight() * newScale);
    if (this.field_70170_p.field_72995_K)
      this.scaleAnimator.sequence(2000.0F, newScale).start(); 
  }
  
  protected void func_70069_a(float damage) {
    if (!this.rpgData.isFallDamage())
      return; 
    super.func_70069_a(damage);
  }
  
  @NonNull
  public MolangParser getParser() {
    return this.molangParser;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\entity\impl\RPGMobEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */