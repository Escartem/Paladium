package fr.paladium.palaclicker.common.profile.fetcher;

import fr.paladium.palaclicker.common.profile.api.ClickerApi;
import fr.paladium.palaclicker.common.profile.dto.ProfilePlayerClicker;
import fr.paladium.profile.server.fetcher.DataFetcher;
import java.io.IOException;
import java.time.Duration;
import retrofit2.Call;
import retrofit2.Response;

public class ClickerFetcher extends DataFetcher<ClickerApi, ProfilePlayerClicker> {
  public ClickerFetcher(Class<ClickerApi> apiClazz) {
    super(apiClazz, 1000, Duration.ofMinutes(5L), Duration.ofMinutes(5L));
  }
  
  public ProfilePlayerClicker fetch(String uuid) {
    Call<ProfilePlayerClicker> call = ((ClickerApi)getApi()).getClicker(uuid);
    try {
      Response<ProfilePlayerClicker> response = call.execute();
      if (response.isSuccessful())
        return (ProfilePlayerClicker)response.body(); 
    } catch (IOException e) {
      e.printStackTrace();
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\common\profile\fetcher\ClickerFetcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */