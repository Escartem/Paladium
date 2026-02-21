package fr.paladium.palamod.common.api.http;

import fr.paladium.palamod.modules.egghunt.utils.EggHuntCommonConfig;
import fr.paladium.palamod.modules.egghunt.utils.EggHuntPlayerEggInput;
import fr.paladium.palamod.modules.egghunt.utils.EggHuntPlayerNameInput;
import fr.paladium.palamod.modules.egghunt.utils.EggHuntStatus;
import fr.paladium.palamod.modules.egghunt.utils.PlayerEggHuntRanked;
import fr.paladium.palamod.modules.mailbox.client.pojo.Mail;
import fr.paladium.palamod.modules.mailbox.client.pojo.MailPlayer;
import fr.paladium.palamod.modules.mailbox.client.pojo.inputs.SendMail;
import fr.paladium.palamod.modules.palaboss.common.utils.BossCommonConfig;
import fr.paladium.palamod.modules.trixium.api.PlayerTrixiumClaimReward;
import fr.paladium.palamod.modules.trixium.api.PlayerTrixiumDeposit;
import fr.paladium.palamod.modules.trixium.api.PlayerTrixiumRanking;
import fr.paladium.palamod.modules.trixium.api.TrixiumFactionProfile;
import fr.paladium.palamod.modules.trixium.api.TrixiumProfile;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PaladiumServices {
  public static interface MAILBOX {
    @GET("mails/{mailId}/read/{uuid}")
    Call<String> readEmail(@Path("mailId") String param1String1, @Path("uuid") String param1String2);
    
    @DELETE("mails/{mailId}/items")
    Call<String> consumesItems(@Path("mailId") String param1String);
    
    @DELETE("mails/{mailId}/items/{uuid}")
    Call<String> consumesItem(@Path("mailId") String param1String1, @Path("uuid") String param1String2);
    
    @GET("mails/recipient/{uuid}/side/{side}")
    Call<List<Mail>> getEmailFromRecipientUUIDAndSide(@Path("uuid") String param1String1, @Path("side") String param1String2);
    
    @GET("mails/recipient/{uuid}")
    Call<List<Mail>> getEmailFromRecipientUUID(@Path("uuid") String param1String);
    
    @POST("mails")
    Call<String> sendEmail(@Body SendMail param1SendMail);
    
    @DELETE("mails/{mailId}/{uuid}")
    Call<String> deleteEmail(@Path("mailId") String param1String1, @Path("uuid") String param1String2);
    
    @GET("mails/{mailId}")
    Call<Mail> getEmail(@Path("mailId") String param1String);
    
    @GET("players")
    Call<List<MailPlayer>> getMailPlayers();
    
    @GET("players/{playerUUID}")
    Call<MailPlayer> getMailPlayerByUUID(@Path("playerUUID") String param1String);
    
    @GET("mails/admin/find-all/{uuid}")
    Call<List<Mail>> findAllPlayerMailAsAdmin(@Path("uuid") String param1String);
  }
  
  public static interface TRIXIUM {
    @POST("trixium/players/{uuid}/get")
    Call<TrixiumProfile> getPlayerTrixium(@Path("uuid") String param1String, @Body PlayerTrixiumDeposit param1PlayerTrixiumDeposit);
    
    @POST("trixium/players/{uuid}/deposit")
    Call<Void> deposit(@Path("uuid") String param1String, @Body PlayerTrixiumDeposit param1PlayerTrixiumDeposit);
    
    @POST("trixium/players/{uuid}/claim")
    Call<Void> claim(@Path("uuid") String param1String, @Body PlayerTrixiumClaimReward param1PlayerTrixiumClaimReward);
    
    @POST("trixium/players/ranking")
    Call<List<TrixiumProfile>> getPlayerRanking(@Body PlayerTrixiumRanking param1PlayerTrixiumRanking);
    
    @GET("trixium/factions/{uuid}")
    Call<TrixiumFactionProfile> getFactionTrixium(@Path("uuid") String param1String);
    
    @POST("trixium/factions/ranking")
    Call<List<TrixiumFactionProfile>> getFactionRanking(@Body PlayerTrixiumRanking param1PlayerTrixiumRanking);
  }
  
  public static interface HALLOWEEN {
    @GET("halloween/get")
    Call<Integer> get();
    
    @GET("halloween/increment")
    Call<Void> increment();
  }
  
  public static interface EGGHUNT {
    @GET("egghunt/status")
    Call<EggHuntStatus> getStatus();
    
    @GET("egghunt/config")
    Call<EggHuntCommonConfig> getConfig();
    
    @POST("egghunt/owner")
    Call<Void> setOwner(@Body EggHuntPlayerEggInput param1EggHuntPlayerEggInput);
    
    @POST("egghunt/killer")
    Call<Void> setKiller(@Body EggHuntPlayerNameInput param1EggHuntPlayerNameInput);
    
    @GET("egghunt/players/{uuid}")
    Call<PlayerEggHuntRanked> getPlayerRank(@Path("uuid") String param1String);
    
    @GET("egghunt/players/top")
    Call<List<PlayerEggHuntRanked>> getRanking();
  }
  
  public static interface BOSS {
    @GET("boss/config")
    Call<BossCommonConfig> getConfig();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\common\api\http\PaladiumServices.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */