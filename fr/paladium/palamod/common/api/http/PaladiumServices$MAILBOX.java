package fr.paladium.palamod.common.api.http;

import fr.paladium.palamod.modules.mailbox.client.pojo.Mail;
import fr.paladium.palamod.modules.mailbox.client.pojo.MailPlayer;
import fr.paladium.palamod.modules.mailbox.client.pojo.inputs.SendMail;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MAILBOX {
  @GET("mails/{mailId}/read/{uuid}")
  Call<String> readEmail(@Path("mailId") String paramString1, @Path("uuid") String paramString2);
  
  @DELETE("mails/{mailId}/items")
  Call<String> consumesItems(@Path("mailId") String paramString);
  
  @DELETE("mails/{mailId}/items/{uuid}")
  Call<String> consumesItem(@Path("mailId") String paramString1, @Path("uuid") String paramString2);
  
  @GET("mails/recipient/{uuid}/side/{side}")
  Call<List<Mail>> getEmailFromRecipientUUIDAndSide(@Path("uuid") String paramString1, @Path("side") String paramString2);
  
  @GET("mails/recipient/{uuid}")
  Call<List<Mail>> getEmailFromRecipientUUID(@Path("uuid") String paramString);
  
  @POST("mails")
  Call<String> sendEmail(@Body SendMail paramSendMail);
  
  @DELETE("mails/{mailId}/{uuid}")
  Call<String> deleteEmail(@Path("mailId") String paramString1, @Path("uuid") String paramString2);
  
  @GET("mails/{mailId}")
  Call<Mail> getEmail(@Path("mailId") String paramString);
  
  @GET("players")
  Call<List<MailPlayer>> getMailPlayers();
  
  @GET("players/{playerUUID}")
  Call<MailPlayer> getMailPlayerByUUID(@Path("playerUUID") String paramString);
  
  @GET("mails/admin/find-all/{uuid}")
  Call<List<Mail>> findAllPlayerMailAsAdmin(@Path("uuid") String paramString);
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\common\api\http\PaladiumServices$MAILBOX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */