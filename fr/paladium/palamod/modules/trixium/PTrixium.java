package fr.paladium.palamod.modules.trixium;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.common.api.ApiServices;
import fr.paladium.palamod.common.api.adapters.NullOnEmptyConverterFactory;
import fr.paladium.palamod.common.api.http.PaladiumServices;
import fr.paladium.palamod.modules.factions.utils.JsonUtils;
import fr.paladium.palamod.modules.trixium.command.TrixiumCommand;
import fr.paladium.palamod.modules.trixium.config.TrixiumConfig;
import fr.paladium.palamod.modules.trixium.event.ClientTrixiumHandler;
import fr.paladium.palamod.modules.trixium.init.Blocks;
import fr.paladium.palamod.modules.trixium.init.Items;
import fr.paladium.palamod.modules.trixium.network.CSPacketTrixiumClaim;
import fr.paladium.palamod.modules.trixium.network.SCPacketTrixiumData;
import fr.paladium.palamod.modules.trixium.network.ranking.CSPacketTrixiumRanking;
import fr.paladium.palamod.modules.trixium.network.ranking.SCPacketTrixiumRanking;
import fr.paladium.palamod.modules.trixium.network.ranking.page.CSPacketTrixiumRankingFactionPage;
import fr.paladium.palamod.modules.trixium.network.ranking.page.CSPacketTrixiumRankingPlayerPage;
import fr.paladium.palamod.modules.trixium.network.ranking.page.SCPacketTrixiumRankingFactionPage;
import fr.paladium.palamod.modules.trixium.network.ranking.page.SCPacketTrixiumRankingPlayerPage;
import fr.paladium.palamod.pulsar.pulse.Handler;
import fr.paladium.palamod.pulsar.pulse.Pulse;
import net.minecraft.command.ICommand;
import net.minecraftforge.common.MinecraftForge;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@ObjectHolder("palamod")
@Pulse(id = "palamod-trixium", description = "Trixium", forced = true)
public class PTrixium {
  @Instance("palamod-trixium")
  public static PTrixium instance;
  
  public static SimpleNetworkWrapper network;
  
  private TrixiumConfig config;
  
  public static PTrixium getInstance() {
    return instance;
  }
  
  public static SimpleNetworkWrapper getNetwork() {
    return network;
  }
  
  public TrixiumConfig getConfig() {
    return this.config;
  }
  
  @Handler
  public void preInit(FMLPreInitializationEvent event) throws Exception {
    instance = this;
    network = NetworkRegistry.INSTANCE.newSimpleChannel("trixium");
    network.registerMessage(SCPacketTrixiumData.Handler.class, SCPacketTrixiumData.class, 0, Side.CLIENT);
    network.registerMessage(CSPacketTrixiumClaim.Handler.class, CSPacketTrixiumClaim.class, 1, Side.SERVER);
    network.registerMessage(CSPacketTrixiumRanking.Handler.class, CSPacketTrixiumRanking.class, 2, Side.SERVER);
    network.registerMessage(SCPacketTrixiumRanking.Handler.class, SCPacketTrixiumRanking.class, 3, Side.CLIENT);
    network.registerMessage(CSPacketTrixiumRankingPlayerPage.Handler.class, CSPacketTrixiumRankingPlayerPage.class, 4, Side.SERVER);
    network.registerMessage(SCPacketTrixiumRankingPlayerPage.Handler.class, SCPacketTrixiumRankingPlayerPage.class, 5, Side.CLIENT);
    network.registerMessage(CSPacketTrixiumRankingFactionPage.Handler.class, CSPacketTrixiumRankingFactionPage.class, 6, Side.SERVER);
    network.registerMessage(SCPacketTrixiumRankingFactionPage.Handler.class, SCPacketTrixiumRankingFactionPage.class, 7, Side.CLIENT);
    Blocks.register();
    Items.register();
    if (event.getSide() == Side.SERVER) {
      this.config = (TrixiumConfig)JsonUtils.getFileObject(event.getModConfigurationDirectory().getAbsolutePath(), "trixium.json", TrixiumConfig.class);
      if (this.config != null) {
        System.out.println("[Trixium] Loaded " + this.config.rewards.size() + " rewards.");
      } else {
        System.out.println("[Trixium] Unable to load config.");
      } 
    } 
  }
  
  @Handler
  @SideOnly(Side.CLIENT)
  public void postInit(FMLPostInitializationEvent event) {
    if (event.getSide().isClient()) {
      MinecraftForge.EVENT_BUS.register(new ClientTrixiumHandler());
      FMLCommonHandler.instance().bus().register(new ClientTrixiumHandler());
    } 
  }
  
  @Handler
  public void serverStarting(FMLServerStartingEvent e) {
    e.registerServerCommand((ICommand)new TrixiumCommand());
    Retrofit eventRetrofit = (new Retrofit.Builder()).baseUrl(this.config.api).addConverterFactory((Converter.Factory)ScalarsConverterFactory.create()).addConverterFactory((Converter.Factory)GsonConverterFactory.create()).addConverterFactory((Converter.Factory)new NullOnEmptyConverterFactory()).build();
    ApiServices.Http.setTrixiumService((PaladiumServices.TRIXIUM)eventRetrofit.create(PaladiumServices.TRIXIUM.class));
    System.out.println("[Trixium] Conneted to api (" + this.config.api + ")");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\PTrixium.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */