package fr.paladium.palawarzoneevent.module.warzone.common.network;

import com.rabbitmq.client.Delivery;
import fr.paladium.palaforgeutils.lib.rabbitmq.network.RabbitPacket;
import fr.paladium.palawarzoneevent.module.warzone.server.phase.WarzonePhaseManager;
import lombok.NonNull;

public class RBPacketRequestCurrentWarzonePhase extends RabbitPacket {
  public void process(@NonNull Delivery delivery) {
    if (delivery == null)
      throw new NullPointerException("delivery is marked non-null but is null"); 
    reply((WarzonePhaseManager.inst().getCurrentWZPhase() != null) ? WarzonePhaseManager.inst().getCurrentWZPhase().getDisplayName() : "");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\warzone\common\network\RBPacketRequestCurrentWarzonePhase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */