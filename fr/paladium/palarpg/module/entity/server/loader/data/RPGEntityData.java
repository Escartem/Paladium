package fr.paladium.palarpg.module.entity.server.loader.data;

import com.google.gson.JsonElement;
import fr.paladium.palaboss.common.entity.properties.EntityProperties;
import fr.paladium.palarpg.module.entity.common.entity.RPGElementType;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import fr.paladium.palarpg.module.entity.server.loader.data.util.RPGAttack;
import fr.paladium.palarpg.module.entity.server.loader.data.util.RPGDefaultStat;
import fr.paladium.palarpg.module.entity.server.loader.data.util.RPGEntitySize;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.world.World;

public class RPGEntityData {
  private String id;
  
  private EntityProperties properties;
  
  private String model;
  
  private RPGEntitySize size;
  
  private String spawnAnimation;
  
  public void setId(String id) {
    this.id = id;
  }
  
  public void setProperties(EntityProperties properties) {
    this.properties = properties;
  }
  
  public void setModel(String model) {
    this.model = model;
  }
  
  public void setSize(RPGEntitySize size) {
    this.size = size;
  }
  
  public void setSpawnAnimation(String spawnAnimation) {
    this.spawnAnimation = spawnAnimation;
  }
  
  public void setSpawnAnimationDuration(long spawnAnimationDuration) {
    this.spawnAnimationDuration = spawnAnimationDuration;
  }
  
  public void setDeathAnimationDuration(long deathAnimationDuration) {
    this.deathAnimationDuration = deathAnimationDuration;
  }
  
  public void setStepHeight(float stepHeight) {
    this.stepHeight = stepHeight;
  }
  
  public void setBossBar(boolean bossBar) {
    this.bossBar = bossBar;
  }
  
  public void setType(RPGElementType type) {
    this.type = type;
  }
  
  public void setExperience(float experience) {
    this.experience = experience;
  }
  
  public void setCanTargetPlayer(boolean canTargetPlayer) {
    this.canTargetPlayer = canTargetPlayer;
  }
  
  public void setImmuneToFire(boolean immuneToFire) {
    this.immuneToFire = immuneToFire;
  }
  
  public void setRevengeAttacker(boolean revengeAttacker) {
    this.revengeAttacker = revengeAttacker;
  }
  
  public void setFallDamage(boolean fallDamage) {
    this.fallDamage = fallDamage;
  }
  
  public void setDefaultStats(List<RPGDefaultStat> defaultStats) {
    this.defaultStats = defaultStats;
  }
  
  public void setAttacks(List<RPGAttack> attacks) {
    this.attacks = attacks;
  }
  
  public void setBehaviors(Map<String, Map<String, JsonElement>> behaviors) {
    this.behaviors = behaviors;
  }
  
  public void setInstanceClass(String instanceClass) {
    this.instanceClass = instanceClass;
  }
  
  public String toString() {
    return "RPGEntityData(id=" + getId() + ", properties=" + getProperties() + ", model=" + getModel() + ", size=" + getSize() + ", spawnAnimation=" + getSpawnAnimation() + ", spawnAnimationDuration=" + getSpawnAnimationDuration() + ", deathAnimationDuration=" + getDeathAnimationDuration() + ", stepHeight=" + getStepHeight() + ", bossBar=" + isBossBar() + ", type=" + getType() + ", experience=" + getExperience() + ", canTargetPlayer=" + isCanTargetPlayer() + ", immuneToFire=" + isImmuneToFire() + ", revengeAttacker=" + isRevengeAttacker() + ", fallDamage=" + isFallDamage() + ", defaultStats=" + getDefaultStats() + ", attacks=" + getAttacks() + ", behaviors=" + getBehaviors() + ", instanceClass=" + getInstanceClass() + ")";
  }
  
  public String getId() {
    return this.id;
  }
  
  public EntityProperties getProperties() {
    return this.properties;
  }
  
  public String getModel() {
    return this.model;
  }
  
  public RPGEntitySize getSize() {
    return this.size;
  }
  
  public String getSpawnAnimation() {
    return this.spawnAnimation;
  }
  
  private long spawnAnimationDuration = 1000L;
  
  public long getSpawnAnimationDuration() {
    return this.spawnAnimationDuration;
  }
  
  private long deathAnimationDuration = 500L;
  
  public long getDeathAnimationDuration() {
    return this.deathAnimationDuration;
  }
  
  private float stepHeight = 0.5F;
  
  public float getStepHeight() {
    return this.stepHeight;
  }
  
  private boolean bossBar = false;
  
  private RPGElementType type;
  
  private float experience;
  
  public boolean isBossBar() {
    return this.bossBar;
  }
  
  public RPGElementType getType() {
    return this.type;
  }
  
  public float getExperience() {
    return this.experience;
  }
  
  private boolean canTargetPlayer = true;
  
  private boolean immuneToFire;
  
  private boolean revengeAttacker;
  
  public boolean isCanTargetPlayer() {
    return this.canTargetPlayer;
  }
  
  public boolean isImmuneToFire() {
    return this.immuneToFire;
  }
  
  public boolean isRevengeAttacker() {
    return this.revengeAttacker;
  }
  
  private boolean fallDamage = true;
  
  private List<RPGDefaultStat> defaultStats;
  
  private List<RPGAttack> attacks;
  
  private Map<String, Map<String, JsonElement>> behaviors;
  
  private String instanceClass;
  
  public boolean isFallDamage() {
    return this.fallDamage;
  }
  
  public List<RPGDefaultStat> getDefaultStats() {
    return this.defaultStats;
  }
  
  public List<RPGAttack> getAttacks() {
    return this.attacks;
  }
  
  public Map<String, Map<String, JsonElement>> getBehaviors() {
    return this.behaviors;
  }
  
  public String getInstanceClass() {
    return this.instanceClass;
  }
  
  public void onLoad() {
    if (this.properties == null)
      this.properties = EntityProperties.DEFAULT; 
    if (this.model == null)
      throw new RuntimeException("Unable to find entity model configuration"); 
    if (this.size == null)
      this.size = new RPGEntitySize(1.0F, 2.0F, 1.0F); 
    if (this.type == null)
      this.type = RPGElementType.NEUTRAL; 
    if (this.experience < 0.0F)
      this.experience = 0.0F; 
    if (this.defaultStats == null)
      this.defaultStats = new ArrayList<>(); 
    if (this.attacks == null)
      throw new RuntimeException("Unable to find entity attacks configuration"); 
    if (this.behaviors == null)
      this.behaviors = new HashMap<>(); 
  }
  
  public RPGMobEntity create(World world) {
    if (this.instanceClass == null || this.instanceClass.isEmpty())
      return new RPGMobEntity(world, this); 
    RPGMobEntity instance = null;
    try {
      Class<?> clazz = Class.forName(this.instanceClass, false, Thread.currentThread().getContextClassLoader());
      if (!RPGMobEntity.class.isAssignableFrom(clazz))
        throw new RuntimeException("The instance class \"" + this.instanceClass + "\" is not a valid rpg entity."); 
      instance = clazz.getDeclaredConstructor(new Class[] { World.class, RPGEntityData.class }).newInstance(new Object[] { world, this });
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } 
    return instance;
  }
  
  public RPGEntityData copy() {
    RPGEntityData copy = new RPGEntityData();
    copy.setProperties(this.properties.copy());
    copy.setModel(this.model);
    copy.setSpawnAnimation(this.spawnAnimation);
    copy.setSpawnAnimationDuration(this.spawnAnimationDuration);
    copy.setDeathAnimationDuration(this.deathAnimationDuration);
    copy.setSize(new RPGEntitySize(this.size.getWidth(), this.size.getHeight(), this.size.getScale()));
    copy.setType(this.type);
    copy.setExperience(this.experience);
    copy.setStepHeight(this.stepHeight);
    copy.setBossBar(this.bossBar);
    copy.setCanTargetPlayer(this.canTargetPlayer);
    copy.setImmuneToFire(this.immuneToFire);
    copy.setRevengeAttacker(this.revengeAttacker);
    copy.setFallDamage(this.fallDamage);
    copy.setDefaultStats(new ArrayList<>(this.defaultStats));
    copy.setAttacks(new ArrayList<>(this.attacks));
    copy.setBehaviors(new HashMap<>(this.behaviors));
    copy.setId(this.id);
    copy.setInstanceClass(this.instanceClass);
    return copy;
  }
  
  public boolean hasSpawnAnimation() {
    return (this.spawnAnimation != null && !this.spawnAnimation.trim().isEmpty());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\server\loader\data\RPGEntityData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */