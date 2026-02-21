package fr.paladium.palamod.modules.trixium.network.ranking;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.factions.api.faction.IFaction;
import fr.paladium.factions.core.faction.FactionController;
import fr.paladium.factions.core.player.Player;
import fr.paladium.factions.core.player.PlayerController;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palamod.common.api.ApiServices;
import fr.paladium.palamod.modules.trixium.PTrixium;
import fr.paladium.palamod.modules.trixium.api.PlayerTrixiumDeposit;
import fr.paladium.palamod.modules.trixium.api.PlayerTrixiumRanking;
import fr.paladium.palamod.modules.trixium.api.TrixiumAPI;
import fr.paladium.palamod.modules.trixium.api.TrixiumFactionProfile;
import fr.paladium.palamod.modules.trixium.api.TrixiumProfile;
import fr.paladium.palamod.util.FastUUID;
import java.util.List;
import net.minecraft.entity.Entity;
import retrofit2.Response;

public class Handler implements IMessageHandler<CSPacketTrixiumRanking, IMessage> {
  public IMessage onMessage(CSPacketTrixiumRanking message, MessageContext ctx) {
    TrixiumAPI.getExecutor().submit(() -> {
          try {
            PlayerTrixiumRanking ranking = new PlayerTrixiumRanking(20, 0);
            Response<List<TrixiumProfile>> playerRankingResponse = ApiServices.Http.getTrixiumService().getPlayerRanking(ranking).execute();
            if (!playerRankingResponse.isSuccessful() || playerRankingResponse.body() == null) {
              (new Notification(Notification.NotificationType.ERROR, "Impossible de charger le classement joueur", "trixium")).send((ctx.getServerHandler()).field_147369_b);
              return;
            } 
            Response<List<TrixiumFactionProfile>> factionRankingResponse = ApiServices.Http.getTrixiumService().getFactionRanking(ranking).execute();
            if (!factionRankingResponse.isSuccessful() || factionRankingResponse.body() == null) {
              (new Notification(Notification.NotificationType.ERROR, "Impossible de charger le classement faction", "trixium")).send((ctx.getServerHandler()).field_147369_b);
              return;
            } 
            List<TrixiumFactionProfile> factionProfiles = (List<TrixiumFactionProfile>)factionRankingResponse.body();
            for (TrixiumFactionProfile profile : factionProfiles) {
              IFaction faction = FactionController.getInstance().getExistingFactionSync(FastUUID.parseUUID(profile.factionUUID));
              profile.exists = (faction != null);
              if (faction == null) {
                profile.factionName = "§cFaction supprimée";
                continue;
              } 
              profile.factionName = faction.getName();
              profile.setEmblem(faction.getEmblemEntity());
            } 
            Response<TrixiumProfile> playerProfileResponse = ApiServices.Http.getTrixiumService().getPlayerTrixium(FastUUID.toString((Entity)(ctx.getServerHandler()).field_147369_b), new PlayerTrixiumDeposit((ctx.getServerHandler()).field_147369_b.func_70005_c_())).execute();
            if (!playerProfileResponse.isSuccessful() || playerProfileResponse.body() == null) {
              (new Notification(Notification.NotificationType.ERROR, "Impossible de charger votre position", "trixium")).send((ctx.getServerHandler()).field_147369_b);
              return;
            } 
            TrixiumFactionProfile factionProfile = null;
            Player iPlayer = PlayerController.getInstance().getLoadedPlayer((ctx.getServerHandler()).field_147369_b);
            if (iPlayer != null && iPlayer.hasFaction() && !iPlayer.getFaction().isDefaultFaction()) {
              Response<TrixiumFactionProfile> factionProfileResponse = ApiServices.Http.getTrixiumService().getFactionTrixium(FastUUID.toString(iPlayer.getFactionUuid())).execute();
              if (!factionProfileResponse.isSuccessful() || factionProfileResponse.body() == null) {
                (new Notification(Notification.NotificationType.ERROR, "Impossible de charger la position de votre faction", "trixium")).send((ctx.getServerHandler()).field_147369_b);
                return;
              } 
              factionProfile = (TrixiumFactionProfile)factionProfileResponse.body();
              factionProfile.factionName = iPlayer.getFaction().getName();
              factionProfile.setEmblem(iPlayer.getFaction().getEmblemEntity());
              factionProfile.exists = true;
            } 
            PTrixium.getNetwork().sendTo(new SCPacketTrixiumRanking((List<TrixiumProfile>)playerRankingResponse.body(), factionProfiles, (TrixiumProfile)playerProfileResponse.body(), factionProfile), (ctx.getServerHandler()).field_147369_b);
          } catch (Exception e) {
            e.printStackTrace();
            (new Notification(Notification.NotificationType.ERROR, "Impossible de charger le classement", "trixium")).send((ctx.getServerHandler()).field_147369_b);
          } 
        });
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\network\ranking\CSPacketTrixiumRanking$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */