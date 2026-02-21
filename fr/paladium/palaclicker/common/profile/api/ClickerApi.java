package fr.paladium.palaclicker.common.profile.api;

import fr.paladium.palaclicker.common.profile.dto.ProfilePlayerClicker;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ClickerApi {
  @GET("player/profile/{uuid}/clicker")
  Call<ProfilePlayerClicker> getClicker(@Path("uuid") String paramString);
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\common\profile\api\ClickerApi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */