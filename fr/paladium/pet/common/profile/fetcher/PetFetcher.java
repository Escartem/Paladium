package fr.paladium.pet.common.profile.fetcher;

import fr.paladium.pet.common.profile.api.PetApi;
import fr.paladium.pet.common.profile.dto.Pet;
import fr.paladium.profile.server.fetcher.DataFetcher;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Response;

public class PetFetcher extends DataFetcher<PetApi, Pet> {
  public PetFetcher(Class<PetApi> apiClazz) {
    super(apiClazz, 1000, Duration.ofMinutes(5L), Duration.ofMinutes(5L));
  }
  
  public Pet fetch(String uuid) {
    Call<Pet> call = ((PetApi)getApi()).getPet(uuid);
    try {
      Response<Pet> response = call.execute();
      if (response.isSuccessful())
        return ((Pet)response.body()).build(); 
      return (new Pet(null, 0, 0.0D, new ArrayList(), 0)).build();
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\profile\fetcher\PetFetcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */