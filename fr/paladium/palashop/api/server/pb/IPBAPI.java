package fr.paladium.palashop.api.server.pb;

import fr.paladium.palashop.api.server.pb.request.PBMutationRequest;
import fr.paladium.palashop.api.server.pb.response.PBHistoryResponse;
import lombok.NonNull;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IPBAPI {
  @GET("{uuid}")
  Call<Long> get(@Path("uuid") @NonNull String paramString);
  
  @GET("{uuid}/season")
  Call<Long> getSeason(@Path("uuid") @NonNull String paramString);
  
  @GET("{uuid}/permanent")
  Call<Long> getPermanent(@Path("uuid") @NonNull String paramString);
  
  @GET("{uuid}/history")
  Call<PBHistoryResponse> getHistory(@Path("uuid") @NonNull String paramString);
  
  @POST("{uuid}")
  Call<Long> mutate(@Path("uuid") @NonNull String paramString, @Body @NonNull PBMutationRequest paramPBMutationRequest);
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\api\server\pb\IPBAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */