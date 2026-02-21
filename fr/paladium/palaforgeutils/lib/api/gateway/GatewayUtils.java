package fr.paladium.palaforgeutils.lib.api.gateway;

import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class GatewayUtils {
  private static Retrofit retrofit;
  
  public static Object registerAPI(Class<?> c) {
    return retrofit.create(c);
  }
  
  public static void init(String url) {
    retrofit = (new Retrofit.Builder()).addConverterFactory((Converter.Factory)ScalarsConverterFactory.create()).addConverterFactory((Converter.Factory)GsonConverterFactory.create()).baseUrl(url).build();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\api\gateway\GatewayUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */