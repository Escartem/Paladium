package fr.paladium.palamod.modules.egghunt.server;

import fr.paladium.chronos.ChronosMod;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palamod.common.api.ApiServices;
import fr.paladium.palamod.common.api.adapters.NullOnEmptyConverterFactory;
import fr.paladium.palamod.common.api.http.PaladiumServices;
import fr.paladium.palamod.modules.egghunt.PEggHunt;
import fr.paladium.palamod.modules.egghunt.common.CommonProxy;
import fr.paladium.palamod.modules.egghunt.runnable.EggHuntStatusThread;
import fr.paladium.palamod.modules.egghunt.utils.EggHuntCommonConfig;
import fr.paladium.palamod.modules.egghunt.utils.EggHuntData;
import fr.paladium.palamod.modules.egghunt.utils.EggHuntServerUUIDInput;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ServerProxy extends CommonProxy {
  public void serverStarted() {
    super.serverStarted();
    if (ForgeEnv.isDev()) {
      System.err.println("[EggHunt->ServerProxy] EggHunt is disabled in devmode.");
    } else {
      Retrofit eventRetrofit = (new Retrofit.Builder()).baseUrl(ChronosMod.getInstance().getApiURL() + "/events/").addConverterFactory((Converter.Factory)ScalarsConverterFactory.create()).addConverterFactory((Converter.Factory)GsonConverterFactory.create()).addConverterFactory((Converter.Factory)new NullOnEmptyConverterFactory()).build();
      ApiServices.Http.setEggHuntService((PaladiumServices.EGGHUNT)eventRetrofit.create(PaladiumServices.EGGHUNT.class));
      try {
        PEggHunt.serverInput = new EggHuntServerUUIDInput();
        PEggHunt.data = new EggHuntData(false, null, -1L, false);
        Response<EggHuntCommonConfig> response = ApiServices.Http.getEggHuntService().getConfig().execute();
        if (!response.isSuccessful()) {
          System.err.println("[EggHunt] Unable to load config from API (" + eventRetrofit.baseUrl() + ")");
          return;
        } 
        PEggHunt.commonConfig = (EggHuntCommonConfig)response.body();
        if (PEggHunt.commonConfig == null) {
          System.err.println("[EggHunt] Config is null from API (" + eventRetrofit.baseUrl() + ")");
          return;
        } 
      } catch (Exception e1) {
        e1.printStackTrace();
      } 
      (new EggHuntStatusThread()).start();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\egghunt\server\ServerProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */