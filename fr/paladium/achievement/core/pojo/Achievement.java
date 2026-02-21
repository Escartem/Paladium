package fr.paladium.achievement.core.pojo;

import com.google.common.collect.Lists;
import fr.paladium.achievement.core.AchievementRegistry;
import fr.paladium.achievement.core.data.ExtendedAchievementPlayerData;
import fr.paladium.achievement.server.managers.AchievementManager;
import java.util.List;
import java.util.Objects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class Achievement {
  private final String id;
  
  private AchievementCategory category;
  
  private String name;
  
  private String description;
  
  public Achievement(String id, AchievementCategory category, String name, String description, int nbToValidate, String icon, List<Achievement> subAchievements, Achievement parent) {
    this.id = id;
    this.category = category;
    this.name = name;
    this.description = description;
    this.nbToValidate = nbToValidate;
    this.icon = icon;
    this.subAchievements = subAchievements;
    this.parent = parent;
  }
  
  public String getId() {
    return this.id;
  }
  
  public AchievementCategory getCategory() {
    return this.category;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  private int nbToValidate = -1;
  
  private String icon;
  
  public int getNbToValidate() {
    return this.nbToValidate;
  }
  
  public String getIcon() {
    return this.icon;
  }
  
  private transient List<Achievement> subAchievements = Lists.newArrayList();
  
  private transient Achievement parent;
  
  public List<Achievement> getSubAchievements() {
    return this.subAchievements;
  }
  
  public Achievement getParent() {
    return this.parent;
  }
  
  public Achievement(String achievementId) {
    this.id = achievementId;
  }
  
  public Achievement(String id, AchievementCategory category, String name, String description, String icon) {
    this.id = id;
    this.category = category;
    this.name = name;
    this.description = description;
    this.icon = icon;
  }
  
  public Achievement(String id, AchievementCategory category, String name, String description, int nbToValidate, String icon) {
    this.id = id;
    this.category = category;
    this.name = name;
    this.description = description;
    this.nbToValidate = nbToValidate;
    this.icon = icon;
  }
  
  public Achievement icon(ItemStack stack) {
    this.icon = AchievementManager.getStringFromItemStack(stack);
    return this;
  }
  
  public void register() {
    AchievementRegistry.register(this);
  }
  
  public Achievement addSubAchievement(Achievement subAchievement) {
    subAchievement.parent = this;
    AchievementRegistry.register(subAchievement);
    this.subAchievements.add(subAchievement);
    return this;
  }
  
  public static void incrementStats(Achievement achvmnt, EntityPlayer p, int nb) {
    if (!(p instanceof net.minecraft.entity.player.EntityPlayerMP))
      return; 
    ExtendedAchievementPlayerData eep = ExtendedAchievementPlayerData.get((Entity)p);
    if (eep != null)
      eep.incrementStats(achvmnt, nb, p); 
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null || getClass() != obj.getClass())
      return false; 
    Achievement other = (Achievement)obj;
    if (!Objects.equals(this.id, other.id))
      return false; 
    return true;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.id });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\core\pojo\Achievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */