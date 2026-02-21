package fr.paladium.palavoicechat.client;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palaforgeutils.lib.config.JsonConfigLoader;
import fr.paladium.palavoicechat.client.config.VoIPClientConfig;
import fr.paladium.palavoicechat.client.listener.KeyBindHandlerListener;
import fr.paladium.palavoicechat.client.listener.PalaVoiceChatEntityListener;
import fr.paladium.palavoicechat.client.voip.client.IoNettyVoIPClient;
import fr.paladium.palavoicechat.common.CommonProxy;
import java.io.File;

public class ClientProxy extends CommonProxy {
  private VoIPClientConfig clientConfig;
  
  private File configPath;
  
  public VoIPClientConfig getClientConfig() {
    return this.clientConfig;
  }
  
  public void setClientConfig(VoIPClientConfig clientConfig) {
    this.clientConfig = clientConfig;
  }
  
  public File getConfigPath() {
    return this.configPath;
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    addListener(new Class[] { PalaVoiceChatEntityListener.class });
    try {
      this.configPath = new File(event.getModConfigurationDirectory(), "VoIPClientConfig.json");
      this.clientConfig = (VoIPClientConfig)JsonConfigLoader.loadConfig(this.configPath, VoIPClientConfig.class);
    } catch (Exception e) {
      e.printStackTrace();
    } 
    IoNettyVoIPClient.getInstance();
  }
  
  public void onInit(FMLInitializationEvent event) {
    KeyBindHandlerListener.init();
    FMLCommonHandler.instance().bus().register(new KeyBindHandlerListener());
    super.onInit(event);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\client\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */