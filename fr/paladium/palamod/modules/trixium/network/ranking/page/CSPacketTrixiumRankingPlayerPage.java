package fr.paladium.palamod.modules.trixium.network.ranking.page;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palamod.common.api.ApiServices;
import fr.paladium.palamod.modules.trixium.PTrixium;
import fr.paladium.palamod.modules.trixium.api.PlayerTrixiumRanking;
import fr.paladium.palamod.modules.trixium.api.TrixiumAPI;
import fr.paladium.palamod.modules.trixium.api.TrixiumProfile;
import io.netty.buffer.ByteBuf;
import java.util.List;
import retrofit2.Response;

public class CSPacketTrixiumRankingPlayerPage implements IMessage {
  private int page;
  
  public CSPacketTrixiumRankingPlayerPage() {}
  
  public CSPacketTrixiumRankingPlayerPage(int page) {
    this.page = page;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.page = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.page);
  }
  
  public static class Handler implements IMessageHandler<CSPacketTrixiumRankingPlayerPage, IMessage> {
    public IMessage onMessage(CSPacketTrixiumRankingPlayerPage message, MessageContext ctx) {
      TrixiumAPI.getExecutor().submit(() -> {
            try {
              PlayerTrixiumRanking ranking = new PlayerTrixiumRanking(20, message.page);
              Response<List<TrixiumProfile>> response = ApiServices.Http.getTrixiumService().getPlayerRanking(ranking).execute();
              if (!response.isSuccessful() || response.body() == null) {
                (new Notification(Notification.NotificationType.ERROR, "Impossible de charger le classement joueur", "trixium")).send((ctx.getServerHandler()).field_147369_b);
                return;
              } 
              PTrixium.getNetwork().sendTo(new SCPacketTrixiumRankingPlayerPage(message.page, (List<TrixiumProfile>)response.body()), (ctx.getServerHandler()).field_147369_b);
            } catch (Exception e) {
              e.printStackTrace();
              (new Notification(Notification.NotificationType.ERROR, "Impossible de charger le classement joueur", "trixium")).send((ctx.getServerHandler()).field_147369_b);
            } 
          });
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\network\ranking\page\CSPacketTrixiumRankingPlayerPage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */