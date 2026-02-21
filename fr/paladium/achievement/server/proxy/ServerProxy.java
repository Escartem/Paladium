package fr.paladium.achievement.server.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import fr.paladium.achievement.core.proxy.CommonProxy;
import fr.paladium.achievement.core.utils.JsonUtils;
import fr.paladium.achievement.server.api.ApiConfig;
import fr.paladium.achievement.server.api.IAchievementAPI;
import fr.paladium.achievement.server.config.AchievementRewardConfig;
import fr.paladium.palaconfiguration.server.manager.ConfigurationManager;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import java.io.File;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ServerProxy extends CommonProxy {
  public File configDirectory;
  
  private IAchievementAPI api;
  
  private AchievementRewardConfig achievementRewardConfig;
  
  public File getConfigDirectory() {
    return this.configDirectory;
  }
  
  public IAchievementAPI getApi() {
    return this.api;
  }
  
  public AchievementRewardConfig getAchievementRewardConfig() {
    return this.achievementRewardConfig;
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    this.configDirectory = event.getModConfigurationDirectory();
    if (!ForgeEnv.isDev())
      initApi(); 
    super.onPreInit(event);
  }
  
  public void onInit(FMLInitializationEvent event) {
    super.onInit(event);
  }
  
  public void onPostInit(FMLPostInitializationEvent event) {
    super.onPostInit(event);
    this.achievementRewardConfig = (AchievementRewardConfig)ConfigurationManager.getInstance().register(AchievementRewardConfig.class);
  }
  
  public void onServerStarted(FMLServerStartedEvent event) {
    super.onServerStarted(event);
  }
  
  private void initApi() {
    try {
      ApiConfig config = (ApiConfig)JsonUtils.getFileObject("achievements.json", ApiConfig.class);
      Retrofit retrofit = (new Retrofit.Builder()).addConverterFactory((Converter.Factory)ScalarsConverterFactory.create()).addConverterFactory((Converter.Factory)GsonConverterFactory.create()).baseUrl(config.getBaseUrl()).build();
      this.api = (IAchievementAPI)retrofit.create(IAchievementAPI.class);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\server\proxy\ServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */