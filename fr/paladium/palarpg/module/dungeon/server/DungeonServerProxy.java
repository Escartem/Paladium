package fr.paladium.palarpg.module.dungeon.server;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import fr.paladium.palaforgeutils.lib.rabbitmq.network.RabbitNetwork;
import fr.paladium.palarpg.PalaRPGMod;
import fr.paladium.palarpg.module.dungeon.DungeonModule;
import fr.paladium.palarpg.module.dungeon.common.DungeonCommonProxy;
import fr.paladium.palarpg.module.dungeon.server.bukkit.DungeonBukkitImpl;
import fr.paladium.palarpg.module.dungeon.server.listener.DungeonServerBackpackListener;
import fr.paladium.palarpg.module.dungeon.server.listener.DungeonServerDeathListener;
import fr.paladium.palarpg.module.dungeon.server.listener.DungeonServerKeyRingListener;
import fr.paladium.palarpg.module.dungeon.server.listener.DungeonServerPlayerListener;
import fr.paladium.palarpg.module.dungeon.server.listener.DungeonServerRankingListener;
import fr.paladium.palarpg.module.dungeon.server.listener.DungeonServerTipListener;
import fr.paladium.palarpg.module.dungeon.server.manager.DungeonManager;
import fr.paladium.palarpg.module.dungeon.server.rabbitmq.RBPacketDungeonInvite;
import fr.paladium.palarpg.module.dungeon.server.rabbitmq.RBPacketDungeonJoin;
import fr.paladium.palarpg.module.dungeon.server.rabbitmq.RBPacketDungeonReward;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.PropertyManager;
import net.minecraft.world.WorldServer;
import org.apache.commons.io.FileUtils;

public class DungeonServerProxy extends DungeonCommonProxy {
  private File templateWorldFile;
  
  public File getTemplateWorldFile() {
    return this.templateWorldFile;
  }
  
  public void onPreInit(FMLPreInitializationEvent event) {
    if (!PalaRPGMod.proxy.isDungeonWorld()) {
      super.onPreInit(event);
      return;
    } 
    this.templateWorldFile = new File("dungeon_world/");
    PropertyManager propertyManager = new PropertyManager(new File("server.properties"));
    String levelName = propertyManager.func_73671_a("level-name", "world");
    File worldFile = new File(levelName);
    if (worldFile.exists() && worldFile.isDirectory())
      if (this.templateWorldFile.exists() && this.templateWorldFile.isDirectory()) {
        try {
          FileUtils.forceDelete(worldFile);
        } catch (IOException e) {
          e.printStackTrace();
          DungeonModule.logger.error("Unable to delete the dungeon world at " + worldFile.getAbsolutePath(), new Object[0]);
        } 
        try {
          FileUtils.copyDirectory(this.templateWorldFile, worldFile);
        } catch (IOException e) {
          e.printStackTrace();
          DungeonModule.logger.error("Unable to copy the dungeon template world to " + worldFile.getAbsolutePath(), new Object[0]);
        } 
      } else {
        this.templateWorldFile = worldFile;
      }  
    super.onPreInit(event);
    addListener(new Class[] { DungeonServerTipListener.class, DungeonServerDeathListener.class, DungeonServerPlayerListener.class, DungeonServerKeyRingListener.class, DungeonServerBackpackListener.class, DungeonServerRankingListener.class });
  }
  
  public void onInit(FMLInitializationEvent event) {
    super.onInit(event);
    RabbitNetwork rabbitNetwork = RabbitNetwork.createNetwork("palarpg_dungeon");
    rabbitNetwork.registerPacket(RBPacketDungeonJoin.class);
    rabbitNetwork.registerPacket(RBPacketDungeonInvite.class);
    rabbitNetwork.registerPacket(RBPacketDungeonReward.class);
  }
  
  public void onPostInit(FMLPostInitializationEvent event) {
    super.onPostInit(event);
    if (!PalaRPGMod.proxy.isDungeonWorld())
      return; 
    try {
      DungeonManager.load();
    } catch (Exception e) {
      throw new RuntimeException("Unable to load the dungeons from Config-API", e);
    } 
  }
  
  public void onServerStarted(FMLServerStartedEvent event) {
    super.onServerStarted(event);
    try {
      DungeonBukkitImpl.load();
    } catch (Exception e) {
      DungeonModule.logger.error("Unable to load the Bukkit dungeon implementation", new Object[] { e });
    } 
    if (!PalaRPGMod.proxy.isDungeonWorld())
      return; 
    List<WorldServer> worlds = new LinkedList<>();
    int i;
    for (i = 1; i < (MinecraftServer.func_71276_C()).field_71305_c.length; i++) {
      WorldServer world = (MinecraftServer.func_71276_C()).field_71305_c[i];
      File worldFile = (world.field_73011_w.getSaveFolder() == null) ? world.func_72860_G().func_75765_b() : new File(world.func_72860_G().func_75765_b(), world.field_73011_w.getSaveFolder());
      if (worldFile.exists())
        try {
          FileUtils.forceDelete(worldFile);
          (MinecraftServer.func_71276_C()).field_71305_c[i] = null;
        } catch (IOException e) {
          throw new RuntimeException("Unable to delete the dungeon world at " + worldFile.getAbsolutePath(), e);
        }  
    } 
    for (i = 0; i < (MinecraftServer.func_71276_C()).field_71305_c.length; i++) {
      WorldServer world = (MinecraftServer.func_71276_C()).field_71305_c[i];
      if (world != null)
        worlds.add(world); 
    } 
    (MinecraftServer.func_71276_C()).field_71305_c = worlds.<WorldServer>toArray(new WorldServer[0]);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\DungeonServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */