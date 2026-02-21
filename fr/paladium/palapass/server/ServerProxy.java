package fr.paladium.palapass.server;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.paladium.palaconfiguration.server.manager.ConfigurationManager;
import fr.paladium.palaforgeutils.lib.subcommand.builder.SubCommandBuilder;
import fr.paladium.palapass.common.CommonProxy;
import fr.paladium.palapass.common.manager.PalapassManager;
import fr.paladium.palapass.common.pojo.quest.EnumQuestsType;
import fr.paladium.palapass.server.command.PalapassCommand;
import fr.paladium.palapass.server.config.PalapassGlobalConfig;
import fr.paladium.palapass.server.config.quest.daily.DailyQuestsConfig;
import fr.paladium.palapass.server.config.quest.season.AprilSeasonQuestsConfig;
import fr.paladium.palapass.server.config.quest.season.AugustSeasonQuestsConfig;
import fr.paladium.palapass.server.config.quest.season.DecemberSeasonQuestsConfig;
import fr.paladium.palapass.server.config.quest.season.FebruarySeasonQuestsConfig;
import fr.paladium.palapass.server.config.quest.season.JanuarySeasonQuestsConfig;
import fr.paladium.palapass.server.config.quest.season.JulySeasonQuestsConfig;
import fr.paladium.palapass.server.config.quest.season.JuneSeasonQuestsConfig;
import fr.paladium.palapass.server.config.quest.season.MarchSeasonQuestsConfig;
import fr.paladium.palapass.server.config.quest.season.MaySeasonQuestsConfig;
import fr.paladium.palapass.server.config.quest.season.NovemberSeasonQuestsConfig;
import fr.paladium.palapass.server.config.quest.season.OctoberSeasonQuestsConfig;
import fr.paladium.palapass.server.config.quest.season.SeptemberSeasonQuestsConfig;
import fr.paladium.palapass.server.config.reward.AprilFreeRewards;
import fr.paladium.palapass.server.config.reward.AugustFreeRewards;
import fr.paladium.palapass.server.config.reward.DecemberFreeRewards;
import fr.paladium.palapass.server.config.reward.FebruaryFreeRewards;
import fr.paladium.palapass.server.config.reward.JanuaryFreeRewards;
import fr.paladium.palapass.server.config.reward.JulyFreeRewards;
import fr.paladium.palapass.server.config.reward.JuneFreeRewards;
import fr.paladium.palapass.server.config.reward.MarchFreeRewards;
import fr.paladium.palapass.server.config.reward.MayFreeRewards;
import fr.paladium.palapass.server.config.reward.NovemberFreeRewards;
import fr.paladium.palapass.server.config.reward.OctoberFreeRewards;
import fr.paladium.palapass.server.config.reward.SeptemberFreeRewards;
import fr.paladium.palapass.server.listener.PalapassPlayerJoinListener;

public class ServerProxy extends CommonProxy {
  public void onPreInit(FMLPreInitializationEvent event) {
    super.onPreInit(event);
    SubCommandBuilder.create("palapass", "Gérer son palapass")
      .string()
      .executable()
      .register(PalapassCommand.class);
    new PalapassManager();
    addListener(new Class[] { PalapassPlayerJoinListener.class });
    for (EnumQuestsType questTypes : EnumQuestsType.values()) {
      addListener(new Class[] { questTypes.linkedClass });
    } 
  }
  
  public void onInit(FMLInitializationEvent event) {
    super.onInit(event);
    registerConfig();
  }
  
  private void registerConfig() {
    ConfigurationManager manager = ConfigurationManager.getInstance();
    manager.register(PalapassGlobalConfig.class);
    manager.register(DailyQuestsConfig.class);
    manager.register(JanuarySeasonQuestsConfig.class);
    manager.register(FebruarySeasonQuestsConfig.class);
    manager.register(MarchSeasonQuestsConfig.class);
    manager.register(AprilSeasonQuestsConfig.class);
    manager.register(MaySeasonQuestsConfig.class);
    manager.register(JuneSeasonQuestsConfig.class);
    manager.register(JulySeasonQuestsConfig.class);
    manager.register(AugustSeasonQuestsConfig.class);
    manager.register(SeptemberSeasonQuestsConfig.class);
    manager.register(OctoberSeasonQuestsConfig.class);
    manager.register(NovemberSeasonQuestsConfig.class);
    manager.register(DecemberSeasonQuestsConfig.class);
    manager.register(JanuaryFreeRewards.class);
    manager.register(FebruaryFreeRewards.class);
    manager.register(MarchFreeRewards.class);
    manager.register(AprilFreeRewards.class);
    manager.register(MayFreeRewards.class);
    manager.register(JuneFreeRewards.class);
    manager.register(JulyFreeRewards.class);
    manager.register(AugustFreeRewards.class);
    manager.register(SeptemberFreeRewards.class);
    manager.register(OctoberFreeRewards.class);
    manager.register(NovemberFreeRewards.class);
    manager.register(DecemberFreeRewards.class);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\server\ServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */