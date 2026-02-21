package fr.paladium.palawarzoneevent.module.warzone.common.network;

import com.rabbitmq.client.Delivery;
import fr.paladium.palaforgeutils.lib.rabbitmq.network.RabbitPacket;
import lombok.NonNull;
import net.minecraft.server.MinecraftServer;

public class RBPacketRequestPlayerCount extends RabbitPacket {
  public void process(@NonNull Delivery delivery) {
    if (delivery == null)
      throw new NullPointerException("delivery is marked non-null but is null"); 
    reply(Integer.valueOf((MinecraftServer.func_71276_C().func_130014_f_()).field_73010_i.size()));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawarzoneevent\module\warzone\common\network\RBPacketRequestPlayerCount.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */