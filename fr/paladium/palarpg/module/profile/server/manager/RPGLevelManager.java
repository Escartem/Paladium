package fr.paladium.palarpg.module.profile.server.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palaconfiguration.server.dto.file.RemoteFile;
import fr.paladium.palaconfiguration.server.dto.file.RemoteObject;
import fr.paladium.palaconfiguration.server.manager.ConfigurationManager;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.profile.ProfileModule;
import fr.paladium.palarpg.module.profile.client.ui.overlay.UIOverlayRPGLevelUp;
import fr.paladium.palarpg.module.profile.common.playerdata.RPGProfilePlayerData;
import fr.paladium.palarpg.module.profile.server.event.RPGExperienceEvent;
import fr.paladium.zephyrui.internal.mod.server.utils.ZUIServer;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;

public class RPGLevelManager {
  private static final Gson GSON = (new GsonBuilder()).create();
  
  private static final Map<Integer, LevelRewardObject> LEVEL_REWARDS = new LinkedHashMap<>();
  
  private static final long TIMEOUT = 15L;
  
  private static final TimeUnit TIMEOUT_UNIT = TimeUnit.SECONDS;
  
  public static void load() throws Exception {
    LEVEL_REWARDS.clear();
    ProfileModule.logger.info("Loading rpg level rewards from ConfigAPI", new Object[0]);
    RemoteFile levelFile = ((RemoteObject)ConfigurationManager.getInstance().getFile("rpg/level/level.json").get(15L, TIMEOUT_UNIT)).asFile();
    String jsonString = levelFile.getContentString().get();
    LevelRewardData lrData = (LevelRewardData)GSON.fromJson(jsonString, LevelRewardData.class);
    LEVEL_REWARDS.putAll(lrData.getRewards());
    ProfileModule.logger.info("RPG Levels rewards loaded !", new Object[0]);
  }
  
  public static void addExperience(EntityLivingBase entity, double experience) {
    if (!(entity instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)entity;
    RPGProfilePlayerData profileData = (RPGProfilePlayerData)RPGPlayer.getData((Entity)player, "profile");
    if (profileData == null)
      return; 
    RPGExperienceEvent.GainEvent event = new RPGExperienceEvent.GainEvent((EntityPlayer)player, experience);
    experience = MinecraftForge.EVENT_BUS.post((Event)event) ? 0.0D : event.getAmount();
    if (experience <= 0.0D)
      return; 
    profileData.addExp(experience);
    int newLevel = getLevelFromTotalExp(profileData.getTotalExp());
    if (newLevel <= profileData.getLevel())
      return; 
    for (int i = profileData.getLevel() + 1; i <= newLevel; i++) {
      if (LEVEL_REWARDS.containsKey(Integer.valueOf(i)))
        ((LevelRewardObject)LEVEL_REWARDS.get(Integer.valueOf(i))).giveTo((EntityPlayer)player, profileData); 
    } 
    MinecraftForge.EVENT_BUS.post((Event)new RPGExperienceEvent.LevelUpEvent((EntityPlayer)player, profileData.getLevel(), newLevel));
    profileData.setLevel(newLevel);
    profileData.sync();
    ZUIServer.open(UIOverlayRPGLevelUp.class, player, new Object[] { Integer.valueOf(newLevel) });
  }
  
  public static void setLevel(EntityPlayerMP entity, int targetLevel) {
    RPGProfilePlayerData profileData = (RPGProfilePlayerData)RPGPlayer.getData((Entity)entity, "profile");
    if (profileData == null)
      return; 
    if (targetLevel <= 1) {
      profileData.setTotalExp(0.0D);
      profileData.setLevel(1);
      profileData.sync();
      ZUIServer.open(UIOverlayRPGLevelUp.class, entity, new Object[] { Integer.valueOf(1) });
      return;
    } 
    profileData.setTotalExp(getTotalExpForLevel(targetLevel));
    profileData.setLevel(targetLevel);
    profileData.sync();
    ZUIServer.open(UIOverlayRPGLevelUp.class, entity, new Object[] { Integer.valueOf(targetLevel) });
  }
  
  public static int getLevelFromTotalExp(EntityLivingBase entity) {
    RPGProfilePlayerData profileData = (RPGProfilePlayerData)RPGPlayer.getData((Entity)entity, "profile");
    if (profileData == null)
      return 0; 
    return getLevelFromTotalExp(profileData.getTotalExp());
  }
  
  public static int getLevelFromTotalExp(double totalExp) {
    double copyTotalExp = totalExp;
    if (copyTotalExp < getExpForLevel(2))
      return 1; 
    int i = 2;
    double expForLevel = getExpForLevel(i);
    while (copyTotalExp >= expForLevel) {
      copyTotalExp -= expForLevel;
      if (copyTotalExp < expForLevel)
        return i; 
      expForLevel = getExpForLevel(++i);
    } 
    return i - 1;
  }
  
  public static double getTotalExpForLevel(int level) {
    if (level <= 1)
      return 0.0D; 
    double totalExp = 0.0D;
    for (int i = 2; i <= level; i++)
      totalExp += getExpForLevel(i); 
    return totalExp;
  }
  
  public double getRemainingExp(EntityPlayer entity) {
    RPGProfilePlayerData profileData = (RPGProfilePlayerData)RPGPlayer.getData((Entity)entity, "profile");
    if (profileData == null)
      return 0.0D; 
    return profileData.getRemainingExp();
  }
  
  public double getProgressionPercentage(EntityPlayer entity) {
    RPGProfilePlayerData profileData = (RPGProfilePlayerData)RPGPlayer.getData((Entity)entity, "profile");
    if (profileData == null)
      return 0.0D; 
    return profileData.getProgressionPercentage();
  }
  
  public static double getExpForLevel(int level) {
    if (level < 2)
      return 0.0D; 
    if (level > 100)
      return 1000.0D * Math.pow(1.1D, 98.0D); 
    return 1000.0D * Math.pow(1.1D, level - 2.0D);
  }
  
  public class LevelRewardData {
    private final Map<Integer, RPGLevelManager.LevelRewardObject> rewards = new HashMap<>();
    
    public Map<Integer, RPGLevelManager.LevelRewardObject> getRewards() {
      return this.rewards;
    }
  }
  
  public class LevelRewardObject {
    @SerializedName("skillpoints")
    private int skillPoints;
    
    public int getSkillPoints() {
      return this.skillPoints;
    }
    
    public void giveTo(@NonNull EntityPlayer player, @NonNull RPGProfilePlayerData pData) {
      if (player == null)
        throw new NullPointerException("player is marked non-null but is null"); 
      if (pData == null)
        throw new NullPointerException("pData is marked non-null but is null"); 
      if (this.skillPoints > 0) {
        pData.addSkillPoints(this.skillPoints);
        player.func_145747_a((new ChatComponentText("§8[§6Paladium§8] §aVous avez reçu un point de compétence !")).func_150255_a((new ChatStyle()).func_150238_a(EnumChatFormatting.GREEN)));
      } 
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\server\manager\RPGLevelManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */