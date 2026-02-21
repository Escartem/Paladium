package fr.paladium.palawarzoneevent.module.warzone.common.network;

import com.rabbitmq.client.Delivery;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.factions.api.FactionAPI;
import fr.paladium.factions.api.faction.IFaction;
import fr.paladium.palaforgeutils.lib.rabbitmq.network.RabbitPacket;
import fr.paladium.palawarzoneevent.module.capture.CaptureModule;
import fr.paladium.palawarzoneevent.module.capture.common.util.CapturePoint;
import lombok.NonNull;

public class RBPacketGetCaptureHolder extends RabbitPacket {
  @SideOnly(Side.SERVER)
  public void process(@NonNull Delivery delivery) {
    if (delivery == null)
      throw new NullPointerException("delivery is marked non-null but is null"); 
    if (CaptureModule.getServer().getConfig().getCapturePoints().isEmpty()) {
      reply("");
      return;
    } 
    CapturePoint cPoint = CaptureModule.getServer().getConfig().getCapturePoints().get(0);
    if (cPoint.getCapturingProgress() == 100.0D && cPoint.getCapturingFactionUUID() != null) {
      IFaction faction = FactionAPI.getInstance().getFaction(((CapturePoint)CaptureModule.getServer().getConfig().getCapturePoints().get(0)).getCapturingFactionUUID());
      reply(faction.getName());
    } 
    reply("");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\warzone\common\network\RBPacketGetCaptureHolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */