package fr.paladium.palavoicechat.server.api;

import fr.paladium.palavoicechat.server.api.pojo.PlayerPunishment;
import fr.paladium.palavoicechat.server.api.pojo.PunishmentType;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IModAPI {
  @GET("/punishments/player/{uuid}/{type}/active")
  Call<PlayerPunishment> getActivePunishment(@Path("uuid") String paramString, @Path("type") PunishmentType paramPunishmentType);
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\server\api\IModAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */