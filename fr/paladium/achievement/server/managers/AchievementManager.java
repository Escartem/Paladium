package fr.paladium.achievement.server.managers;

import fr.paladium.achievement.core.AchievementRegistry;
import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.core.pojo.WalkDistanceQuestHelper;
import fr.paladium.achievement.core.utils.ItemStackSerializer;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import org.bukkit.Bukkit;

public class AchievementManager {
  public static AchievementManager instance;
  
  private final ConcurrentHashMap<UUID, WalkDistanceQuestHelper> walkDistanceQuestData = new ConcurrentHashMap<>();
  
  public ConcurrentHashMap<UUID, WalkDistanceQuestHelper> getWalkDistanceQuestData() {
    return this.walkDistanceQuestData;
  }
  
  private final Map<Class<?>, List<Achievement>> achievementsByType = new ConcurrentHashMap<>();
  
  private final Map<AchievementCategory, List<Achievement>> achievementsByCategory = new ConcurrentHashMap<>();
  
  public AchievementManager() {
    instance = this;
  }
  
  public List<Achievement> getAchievements(Class<?> type) {
    List<Achievement> achievements = this.achievementsByType.get(type);
    if (achievements == null) {
      achievements = (List<Achievement>)AchievementRegistry.getInstance().getAchievements().stream().filter(a -> (a != null && type.equals(a.getClass()))).collect(Collectors.toList());
      this.achievementsByType.put(type, achievements);
    } 
    return achievements;
  }
  
  public List<Achievement> getAchievementsByCategory(AchievementCategory item) {
    List<Achievement> achievements = this.achievementsByCategory.get(item);
    if (achievements == null) {
      achievements = (List<Achievement>)AchievementRegistry.getInstance().getAchievements().stream().filter(a -> (a != null && item.equals(a.getCategory()) && a.getParent() == null)).collect(Collectors.toList());
      this.achievementsByCategory.put(item, achievements);
    } 
    return achievements;
  }
  
  public static AchievementManager getInstance() {
    if (instance == null)
      new AchievementManager(); 
    return instance;
  }
  
  public static ItemStack getItemStackFromString(String itemStr) {
    try {
      return ItemStackSerializer.read(new String(Base64.getDecoder().decode(itemStr)));
    } catch (Exception e) {
      return new ItemStack(Blocks.field_150357_h);
    } 
  }
  
  public static String getStringFromItemStack(ItemStack stack) {
    return ItemStackSerializer.write(stack);
  }
  
  public static boolean hasBukkitPermission(UUID uuid, String permission) {
    try {
      return Bukkit.getPlayer(uuid).hasPermission(permission);
    } catch (NullPointerException e) {
      return true;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\server\managers\AchievementManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */