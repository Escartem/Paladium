package fr.paladium.palamod.common.api.http;

import fr.paladium.palamod.common.api.dto.UUID;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProfileServices {
  @GET("users/profiles/minecraft/{username}")
  Call<UUID> getUUIDFromUsername(@Path("username") String paramString);
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\common\api\http\ProfileServices.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */