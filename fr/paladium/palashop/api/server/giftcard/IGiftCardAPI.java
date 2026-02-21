package fr.paladium.palashop.api.server.giftcard;

import fr.paladium.palashop.api.server.giftcard.request.GiftCardRedeemRequest;
import fr.paladium.palashop.api.server.giftcard.response.GiftCardRedeemResponse;
import fr.paladium.palashop.server.giftcard.dto.GiftCard;
import lombok.NonNull;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IGiftCardAPI {
  @GET("{code}")
  Call<GiftCard> get(@Path("code") @NonNull String paramString);
  
  @POST("redeem/{code}")
  Call<GiftCardRedeemResponse> redeem(@Path("code") @NonNull String paramString, @Body @NonNull GiftCardRedeemRequest paramGiftCardRedeemRequest);
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\api\server\giftcard\IGiftCardAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */