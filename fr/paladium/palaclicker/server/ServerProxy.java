package fr.paladium.palaclicker.server;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.paladium.palaclicker.common.CommonProxy;
import fr.paladium.palaclicker.server.command.ClickerCommand;
import fr.paladium.palaclicker.server.config.building.BuildingGlobalConfig;
import fr.paladium.palaclicker.server.config.shop.ShopGlobalConfig;
import fr.paladium.palaclicker.server.config.upgrade.UpgradeGlobalConfig;
import fr.paladium.palaclicker.server.task.ClickerTask;
import fr.paladium.palaconfiguration.server.manager.ConfigurationManager;
import fr.paladium.palaforgeutils.lib.subcommand.builder.SubCommandBuilder;

public class ServerProxy extends CommonProxy {
  private BuildingGlobalConfig buildingConfig;
  
  private UpgradeGlobalConfig upgradeConfig;
  
  private ShopGlobalConfig shopConfig;
  
  public BuildingGlobalConfig getBuildingConfig() {
    return this.buildingConfig;
  }
  
  public UpgradeGlobalConfig getUpgradeConfig() {
    return this.upgradeConfig;
  }
  
  public ShopGlobalConfig getShopConfig() {
    return this.shopConfig;
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    initConfig();
    initCommand();
  }
  
  public void onPostInit(FMLPostInitializationEvent event) {
    initConfig();
  }
  
  public void onServerStarting(FMLServerStartingEvent event) {
    super.onServerStarting(event);
    (new ClickerTask()).start();
  }
  
  private void initConfig() {
    ConfigurationManager manager = ConfigurationManager.getInstance();
    this.buildingConfig = (BuildingGlobalConfig)manager.register(BuildingGlobalConfig.class);
    this.upgradeConfig = (UpgradeGlobalConfig)manager.register(UpgradeGlobalConfig.class);
    this.shopConfig = (ShopGlobalConfig)manager.register(ShopGlobalConfig.class);
  }
  
  private void initCommand() {
    SubCommandBuilder.create("clicker", "manage clicker")
      .permission("paladium.cmd.clicker")
      .string()
      .register(ClickerCommand.class);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\server\ServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */