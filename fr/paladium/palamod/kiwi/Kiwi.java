package fr.paladium.palamod.kiwi;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.asgard.AsgardMod;
import fr.paladium.palamod.Constants;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.client.utils.Utils;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.common.creativetab.PCreativeTab;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.config.PConfigs;
import fr.paladium.palamod.modules.addons.PAddons;
import fr.paladium.palamod.modules.ajobs.PJobs;
import fr.paladium.palamod.modules.alchimiste.PAlchimiste;
import fr.paladium.palamod.modules.apet.PPet;
import fr.paladium.palamod.modules.aspawner.PPalaSpawner;
import fr.paladium.palamod.modules.back2future.Back2Future;
import fr.paladium.palamod.modules.chisel.PChisel;
import fr.paladium.palamod.modules.communityevents.PCommunityEvents;
import fr.paladium.palamod.modules.design.PDesign;
import fr.paladium.palamod.modules.discord.PDiscord;
import fr.paladium.palamod.modules.egghunt.PEggHunt;
import fr.paladium.palamod.modules.end.PEnd;
import fr.paladium.palamod.modules.enderchest.PEnderChest;
import fr.paladium.palamod.modules.factions.PFactions;
import fr.paladium.palamod.modules.homefinder.PHomeFinder;
import fr.paladium.palamod.modules.hunter.PHunter;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.mailbox.PMailbox;
import fr.paladium.palamod.modules.miner.PMiner;
import fr.paladium.palamod.modules.nemesis.NemesisPulse;
import fr.paladium.palamod.modules.packetreducer.PPacketReducer;
import fr.paladium.palamod.modules.palaboss.PPalaBoss;
import fr.paladium.palamod.modules.paladium.PPaladium;
import fr.paladium.palamod.modules.paladynamique.PPalaDynamique;
import fr.paladium.palamod.modules.palawither.PPalaWither;
import fr.paladium.palamod.modules.pillage.PPillage;
import fr.paladium.palamod.modules.pvp.PPvp;
import fr.paladium.palamod.modules.shop.PShop;
import fr.paladium.palamod.modules.smeltery.PSmeltery;
import fr.paladium.palamod.modules.spellsv2.PSpellsV2;
import fr.paladium.palamod.modules.stats.PStats;
import fr.paladium.palamod.modules.trixium.PTrixium;
import fr.paladium.palamod.modules.troll.PTroll;
import fr.paladium.palamod.modules.world.PWorld;
import fr.paladium.palamod.pulsar.PulseManager;

public class Kiwi {
  public Kiwi(PalaMod palamod, PulseManager pulsar) {}
  
  public void preInit(FMLPreInitializationEvent event) {
    if (event.getSide().isServer())
      Constants.MOD_ENV = Constants.Environment.SERVER; 
    if (Utils.isDev() || Constants.MOD_ENV == Constants.Environment.SERVER)
      PConfigs.initConfigs(event.getModConfigurationDirectory()); 
    PalaMod.network = NetworkRegistry.INSTANCE.newSimpleChannel("palamod");
    PalaMod.netHandler = NetworkRegistry.INSTANCE.newSimpleChannel("palamod_netHandler");
    PalaMod.proxy.initialize();
    if (event.getSide().isClient())
      try {
        registerClientOnly();
      } catch (Exception exception) {} 
    PalaMod.pulsar.registerPulse(new PAddons());
    PalaMod.pulsar.registerPulse(new PPaladium());
    PalaMod.pulsar.registerPulse(new PWorld());
    PalaMod.pulsar.registerPulse(new PSmeltery());
    PalaMod.pulsar.registerPulse(new Back2Future());
    PalaMod.pulsar.registerPulse(new PChisel());
    PalaMod.pulsar.registerPulse(new PMiner());
    PalaMod.pulsar.registerPulse(new PAlchimiste());
    PalaMod.pulsar.registerPulse(new PPalaBoss());
    PalaMod.pulsar.registerPulse(new PPillage());
    PalaMod.pulsar.registerPulse(new PLuckyBlock());
    PalaMod.pulsar.registerPulse(new PSpellsV2());
    PalaMod.pulsar.registerPulse(new PHunter());
    PalaMod.pulsar.registerPulse(new PEggHunt());
    PalaMod.pulsar.registerPulse(new PTrixium());
    PalaMod.pulsar.registerPulse(new PPvp());
    PalaMod.pulsar.registerPulse(new PStats());
    PalaMod.pulsar.registerPulse(new PShop());
    PalaMod.pulsar.registerPulse(new PTroll());
    PalaMod.pulsar.registerPulse(new PMailbox());
    PalaMod.pulsar.registerPulse(new PEnderChest());
    PalaMod.pulsar.registerPulse(new PFactions());
    PalaMod.pulsar.registerPulse(new PPacketReducer());
    PalaMod.pulsar.registerPulse(new PEnd());
    PalaMod.pulsar.registerPulse(new PPalaDynamique());
    PalaMod.pulsar.registerPulse(new PHomeFinder());
    PalaMod.pulsar.registerPulse(new PJobs());
    PalaMod.pulsar.registerPulse(new PCommunityEvents());
    PalaMod.pulsar.registerPulse(new PPet());
    PalaMod.pulsar.registerPulse(new PPalaSpawner());
    PalaMod.pulsar.registerPulse(new PPalaWither());
    PalaMod.pulsar.registerPulse(new NemesisPulse());
    PGuiRegistry.init();
    Registry.PALADIUM_TAB = new PCreativeTab("palamod");
    Registry.PLANTS_TAB = new PCreativeTab("palamod_plants");
    Registry.CHISEL_TAB = new PCreativeTab("palamod_chisel");
    Registry.FOOD_TAB = new PCreativeTab("palamod_food");
    Registry.DECORATIVE_TAB = new PCreativeTab("palamod_decorative");
    Registry.SMELTERY_TAB = new PCreativeTab("palamod_smeltery");
    Registry.POTION_TAB = new PCreativeTab("palamod_potion");
    Registry.PILLAGE_TAB = new PCreativeTab("palamod_pillage");
    Registry.HUNTER_TAB = new PCreativeTab("palamod_hunter");
    Registry.MINER_TAB = new PCreativeTab("palamod_miner");
    Registry.EGGHUNT_TAB = new PCreativeTab("palamod_egghunt");
    PalaMod.pulsar.preInit(event);
  }
  
  @SideOnly(Side.CLIENT)
  public void registerClientOnly() {
    PalaMod.pulsar.registerPulse(new PDiscord());
    PalaMod.pulsar.registerPulse(new PDesign());
    PalaMod.pulsar.registerPulse(new AsgardMod());
  }
  
  public void init(FMLInitializationEvent event) {
    PalaMod.pulsar.init(event);
    PalaMod.proxy.init();
  }
  
  public void postInit(FMLPostInitializationEvent event) {
    PalaMod.proxy.postInit();
    PalaMod.pulsar.postInit(event);
  }
  
  public void serverStarting(FMLServerStartingEvent event) {
    PalaMod.proxy.serverStarting(event);
    PalaMod.pulsar.serverStarting(event);
  }
  
  public void serverStarted(FMLServerStartedEvent event) {
    PalaMod.pulsar.serverStarted(event);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\kiwi\Kiwi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */