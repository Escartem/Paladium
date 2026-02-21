package fr.paladium.palawarzoneevent.module.warzone.common.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rabbitmq.client.Delivery;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.rabbitmq.network.RabbitPacket;
import fr.paladium.palawarzoneevent.module.warzone.common.data.CaptureLeaderboardData;
import fr.paladium.palawarzoneevent.module.warzone.server.worlddata.WarzoneLeaderboardType;
import fr.paladium.palawarzoneevent.module.warzone.server.worlddata.WarzoneLeaderboardWorldData;
import lombok.NonNull;

public class RBPacketRequestCaptureClassementPage extends RabbitPacket {
  public RBPacketRequestCaptureClassementPage() {}
  
  public RBPacketRequestCaptureClassementPage(int index) {
    this.index = index;
  }
  
  private static final Gson GSON = (new GsonBuilder()).create();
  
  @PacketData
  private int index;
  
  public void process(@NonNull Delivery delivery) {
    if (delivery == null)
      throw new NullPointerException("delivery is marked non-null but is null"); 
    reply(GSON.toJson(new CaptureLeaderboardData(WarzoneLeaderboardWorldData.get().getLeaderboardPage(WarzoneLeaderboardType.CAPTURE, this.index))));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\warzone\common\network\RBPacketRequestCaptureClassementPage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */