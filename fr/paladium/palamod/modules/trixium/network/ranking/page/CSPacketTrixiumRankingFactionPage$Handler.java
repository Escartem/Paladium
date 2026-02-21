package fr.paladium.palamod.modules.trixium.network.ranking.page;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.factions.api.faction.IFaction;
import fr.paladium.factions.core.faction.FactionController;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palamod.common.api.ApiServices;
import fr.paladium.palamod.modules.trixium.PTrixium;
import fr.paladium.palamod.modules.trixium.api.PlayerTrixiumRanking;
import fr.paladium.palamod.modules.trixium.api.TrixiumAPI;
import fr.paladium.palamod.modules.trixium.api.TrixiumFactionProfile;
import fr.paladium.palamod.util.FastUUID;
import java.util.List;
import retrofit2.Response;

public class Handler implements IMessageHandler<CSPacketTrixiumRankingFactionPage, IMessage> {
  public IMessage onMessage(CSPacketTrixiumRankingFactionPage message, MessageContext ctx) {
    TrixiumAPI.getExecutor().submit(() -> {
          try {
            PlayerTrixiumRanking ranking = new PlayerTrixiumRanking(20, CSPacketTrixiumRankingFactionPage.access$000(message));
            Response<List<TrixiumFactionProfile>> reponse = ApiServices.Http.getTrixiumService().getFactionRanking(ranking).execute();
            if (!reponse.isSuccessful() || reponse.body() == null) {
              (new Notification(Notification.NotificationType.ERROR, "Impossible de charger le classement faction", "trixium")).send((ctx.getServerHandler()).field_147369_b);
              return;
            } 
            List<TrixiumFactionProfile> profiles = (List<TrixiumFactionProfile>)reponse.body();
            for (TrixiumFactionProfile profile : profiles) {
              IFaction faction = FactionController.getInstance().getExistingFactionSync(FastUUID.parseUUID(profile.factionUUID));
              profile.exists = (faction != null);
              if (faction == null) {
                profile.factionName = "§cFaction supprimée";
                continue;
              } 
              profile.factionName = faction.getName();
              profile.setEmblem(faction.getEmblemEntity());
            } 
            PTrixium.getNetwork().sendTo(new SCPacketTrixiumRankingFactionPage(CSPacketTrixiumRankingFactionPage.access$000(message), profiles), (ctx.getServerHandler()).field_147369_b);
          } catch (Exception e) {
            e.printStackTrace();
            (new Notification(Notification.NotificationType.ERROR, "Impossible de charger le classement faction", "trixium")).send((ctx.getServerHandler()).field_147369_b);
          } 
        });
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\network\ranking\page\CSPacketTrixiumRankingFactionPage$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */