package fr.paladium.achievement.client.proxy;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import fr.paladium.achievement.client.ui.UIAchievement;
import fr.paladium.achievement.core.proxy.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class ClientProxy extends CommonProxy {
  public static ClientProxy instance;
  
  public void onPostInit(FMLPostInitializationEvent event) {
    super.onPostInit(event);
    instance = this;
  }
  
  public void openAchievement() {
    Minecraft.func_71410_x().func_147108_a((GuiScreen)new UIAchievement());
  }
  
  public static ClientProxy getInstance() {
    if (instance == null)
      instance = new ClientProxy(); 
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\client\proxy\ClientProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */