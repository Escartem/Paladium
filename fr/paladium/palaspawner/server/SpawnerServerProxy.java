package fr.paladium.palaspawner.server;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.paladium.blueprint.server.subcommand.blueprint.BluePrintSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.builder.SubCommandBuilder;
import fr.paladium.palaspawner.common.SpawnerCommonProxy;
import fr.paladium.palaspawner.common.manager.SpawnerManager;
import fr.paladium.palaspawner.common.spawner.data.ASpawnerEntityData;
import fr.paladium.palaspawner.server.subcommand.palaspawner.PalaSpawnerSubCommand;

public class SpawnerServerProxy extends SpawnerCommonProxy {
  private static SpawnerServerProxy instance;
  
  public static SpawnerServerProxy getInstance() {
    return instance;
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    instance = this;
    super.onPreInit(event);
  }
  
  public void onInit(FMLInitializationEvent event) {
    super.onInit(event);
  }
  
  public void onPostInit(FMLPostInitializationEvent event) {
    super.onPostInit(event);
    SubCommandBuilder.of(BluePrintSubCommand.class).register();
    SubCommandBuilder.of(PalaSpawnerSubCommand.class).register();
  }
  
  public void onServerStarting(FMLServerStartingEvent event) {
    super.onServerStarting(event);
  }
  
  public void onServerStarted(FMLServerStartedEvent event) {
    super.onServerStarted(event);
    SpawnerManager.getInstance().getSpawnerEntities().values().forEach(data -> data.setExperiencePoints());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\server\SpawnerServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */