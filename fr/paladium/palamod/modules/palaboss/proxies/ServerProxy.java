package fr.paladium.palamod.modules.palaboss.proxies;

import cpw.mods.fml.common.event.FMLServerStartedEvent;
import fr.paladium.chronos.ChronosMod;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palamod.common.api.ApiServices;
import fr.paladium.palamod.common.api.adapters.NullOnEmptyConverterFactory;
import fr.paladium.palamod.common.api.http.PaladiumServices;
import fr.paladium.palamod.modules.palaboss.PPalaBoss;
import fr.paladium.palamod.modules.palaboss.common.utils.BossCommonConfig;
import fr.paladium.palamod.modules.palaboss.common.utils.ServerBossData;
import java.net.InetAddress;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ServerProxy extends CommonProxy {
  public void serverStarted(FMLServerStartedEvent e) {
    super.serverStarted(e);
    if (ForgeEnv.isDev()) {
      System.err.println("[PalaBoss->ServerProxy] Chronos PalaBoss is disabled in devmode.");
      return;
    } 
    if (PPalaBoss.config == null) {
      System.err.println("[PalaBoss->ServerProxy] Chronos PalaBoss config is null.");
      return;
    } 
    Retrofit eventRetrofit = (new Retrofit.Builder()).baseUrl(ChronosMod.getInstance().getApiURL() + "/events/").addConverterFactory((Converter.Factory)ScalarsConverterFactory.create()).addConverterFactory((Converter.Factory)GsonConverterFactory.create()).addConverterFactory((Converter.Factory)new NullOnEmptyConverterFactory()).build();
    ApiServices.Http.setBossService((PaladiumServices.BOSS)eventRetrofit.create(PaladiumServices.BOSS.class));
    try {
      PPalaBoss.serverBossData = new ServerBossData(InetAddress.getLocalHost().getHostName(), "");
      Response<BossCommonConfig> response = ApiServices.Http.getBossService().getConfig().execute();
      if (!response.isSuccessful()) {
        System.err.println("[PalaBoss] Unable to load config from API (" + eventRetrofit.baseUrl() + ")");
        return;
      } 
      PPalaBoss.commonConfig = (BossCommonConfig)response.body();
      if (PPalaBoss.commonConfig == null)
        System.err.println("[PalaBoss] Config is null from API (" + eventRetrofit.baseUrl() + ")"); 
    } catch (Exception e1) {
      if (!ForgeEnv.isDev())
        e1.printStackTrace(); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\proxies\ServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */