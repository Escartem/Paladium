package fr.paladium.palashop.api.server.provider;

import java.util.List;
import org.bson.Document;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IProviderAPI {
  @GET("/")
  Call<List<String>> list();
  
  @GET("{provider}")
  Call<Boolean> exists(@Path("provider") String paramString);
  
  @GET("{provider}/{uuid}")
  Call<Document> get(@Path("provider") String paramString1, @Path("uuid") String paramString2);
  
  @GET("{provider}/has/{uuid}")
  Call<Boolean> has(@Path("provider") String paramString1, @Path("uuid") String paramString2);
  
  @POST("{provider}/{uuid}")
  Call<Void> set(@Path("provider") String paramString1, @Path("uuid") String paramString2, @Body Document paramDocument);
  
  @POST("{provider}/{uuid}/save")
  Call<Void> save(@Path("provider") String paramString1, @Path("uuid") String paramString2);
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\api\server\provider\IProviderAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */