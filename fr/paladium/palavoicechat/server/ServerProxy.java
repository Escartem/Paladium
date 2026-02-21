package fr.paladium.palavoicechat.server;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import fr.paladium.palaforgeutils.lib.config.JsonConfigLoader;
import fr.paladium.palavoicechat.common.CommonProxy;
import fr.paladium.palavoicechat.server.api.IModAPI;
import fr.paladium.palavoicechat.server.config.VoIPServerConfig;
import fr.paladium.palavoicechat.server.listener.ServerListener;
import fr.paladium.palavoicechat.server.voip.IoNettyVoIPServer;
import java.io.File;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerProxy extends CommonProxy {
  public static VoIPServerConfig serverConfig;
  
  public IModAPI modApi;
  
  public IModAPI getModApi() {
    return this.modApi;
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    try {
      serverConfig = (VoIPServerConfig)JsonConfigLoader.loadConfig(new File(event.getModConfigurationDirectory(), "VoIPServerConfig.json"), VoIPServerConfig.class);
    } catch (Exception e) {
      e.printStackTrace();
    } 
    OkHttpClient okHttpClient = (new OkHttpClient.Builder()).connectTimeout(30L, TimeUnit.MINUTES).readTimeout(30L, TimeUnit.SECONDS).writeTimeout(30L, TimeUnit.SECONDS).build();
    String url = serverConfig.getModApiUrl();
    if (url == null || url.trim().isEmpty()) {
      System.out.println("[PalaVoiceChat] Mod API URL is not set, skipping Mod API initialization.");
      return;
    } 
    Retrofit retrofit3 = (new Retrofit.Builder()).baseUrl(url).client(okHttpClient).addConverterFactory((Converter.Factory)GsonConverterFactory.create()).build();
    this.modApi = (IModAPI)retrofit3.create(IModAPI.class);
  }
  
  public void onInit(FMLInitializationEvent event) {
    FMLCommonHandler.instance().bus().register(new ServerListener());
  }
  
  public void onServerStarted(FMLServerStartedEvent event) {
    System.out.println("[PalaVoiceChat] Starting VoIP client...");
    IoNettyVoIPServer.getInstance();
    System.out.println("[PalaVoiceChat] VoIP client started to reach " + serverConfig.getVoipServerIp() + ":" + serverConfig.getVoipServerPort());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\server\ServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */