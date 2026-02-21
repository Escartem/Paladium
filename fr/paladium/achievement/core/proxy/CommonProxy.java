package fr.paladium.achievement.core.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.achievement.core.AchievementRegistry;
import fr.paladium.achievement.core.data.ExtendedAchievementPlayerData;
import fr.paladium.achievement.core.helios.ModuleAchievement;
import fr.paladium.achievement.core.packets.server.SCSyncExtendedAchievementPlayerData;
import fr.paladium.achievement.core.packets.server.ui.SCAchievementHome;
import fr.paladium.achievement.core.utils.ItemStackSerializer;
import fr.paladium.helios.Helios;
import java.util.Base64;
import net.minecraft.item.ItemStack;

public class CommonProxy {
  public SimpleNetworkWrapper network;
  
  public SimpleNetworkWrapper getNetwork() {
    return this.network;
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    AchievementRegistry.getInstance().registerAchievements();
    ExtendedAchievementPlayerData.register();
    this.network = NetworkRegistry.INSTANCE.newSimpleChannel("achievement");
    this.network.registerMessage(SCAchievementHome.Handler.class, SCAchievementHome.class, 0, Side.CLIENT);
    this.network.registerMessage(SCSyncExtendedAchievementPlayerData.Handler.class, SCSyncExtendedAchievementPlayerData.class, 1, Side.CLIENT);
  }
  
  public void onInit(FMLInitializationEvent event) {
    if (event.getSide() == Side.CLIENT) {
      Helios.getClient().addModule(ModuleAchievement.class);
    } else if (event.getSide() == Side.SERVER) {
      Helios.getServer().addModule(ModuleAchievement.class);
    } 
  }
  
  public void onPostInit(FMLPostInitializationEvent event) {}
  
  public void onServerStarted(FMLServerStartedEvent event) {}
  
  public static String getBase64FromItemStack(ItemStack itemStack) {
    return Base64.getEncoder().encodeToString(ItemStackSerializer.write(itemStack).getBytes());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\core\proxy\CommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */