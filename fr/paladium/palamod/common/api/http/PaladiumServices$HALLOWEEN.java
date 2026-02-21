package fr.paladium.palamod.common.api.http;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HALLOWEEN {
  @GET("halloween/get")
  Call<Integer> get();
  
  @GET("halloween/increment")
  Call<Void> increment();
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\common\api\http\PaladiumServices$HALLOWEEN.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */