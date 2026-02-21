package fr.paladium.palashop.provider.box.api;

import fr.paladium.palashop.provider.box.common.dto.box.Box;
import java.util.Set;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IBoxAPI {
  @GET("list")
  Call<Set<Box>> list();
  
  @POST("update")
  Call<Void> update();
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\api\IBoxAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */