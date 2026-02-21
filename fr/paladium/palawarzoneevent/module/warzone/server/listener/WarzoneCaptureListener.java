package fr.paladium.palawarzoneevent.module.warzone.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.factions.api.faction.IFaction;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palawarzoneevent.module.capture.api.event.CaptureRewardEvent;
import fr.paladium.palawarzoneevent.module.warzone.server.worlddata.WarzoneLeaderboardType;
import fr.paladium.palawarzoneevent.module.warzone.server.worlddata.WarzoneLeaderboardWorldData;
import java.util.concurrent.CompletableFuture;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class WarzoneCaptureListener {
  @SubscribeEvent
  public void onCaptureReward(CaptureRewardEvent event) {
    if (event.getFaction() != null) {
      IFaction faction = event.getFaction();
      CompletableFuture<Void> future = new CompletableFuture<>();
      WarzoneLeaderboardWorldData.get().addPoints(WarzoneLeaderboardType.CAPTURE, faction.getUuid(), faction.getName(), 1L);
      future.whenComplete((result, error) -> {
            if (error != null) {
              event.getPlayers().forEach(());
              return;
            } 
            event.getPlayers().forEach(());
          });
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\warzone\server\listener\WarzoneCaptureListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */