package fr.paladium.pet.common.profile.api;

import fr.paladium.pet.common.profile.dto.Pet;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PetApi {
  @GET("player/profile/{uuid}/pet")
  Call<Pet> getPet(@Path("uuid") String paramString);
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\profile\api\PetApi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */