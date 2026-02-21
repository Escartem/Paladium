package fr.paladium.palarpg.module.profile.server;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import fr.paladium.palarpg.module.profile.common.ProfileCommonProxy;
import fr.paladium.palarpg.module.profile.common.skilltree.manager.RPGSkillTreeManager;
import fr.paladium.palarpg.module.profile.server.listener.ProfileServerPlayerEventListener;
import fr.paladium.palarpg.module.profile.server.manager.RPGLevelManager;

public class ProfileServerProxy extends ProfileCommonProxy {
  public void onPostInit(FMLPostInitializationEvent event) {
    super.onPostInit(event);
    addListener(new Class[] { ProfileServerPlayerEventListener.class });
    try {
      RPGSkillTreeManager.load();
      RPGLevelManager.load();
    } catch (Exception e) {
      throw new RuntimeException("Unable to load the skilltree from Config-API", e);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\profile\server\ProfileServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */