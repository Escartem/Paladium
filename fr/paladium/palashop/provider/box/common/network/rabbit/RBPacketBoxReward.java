package fr.paladium.palashop.provider.box.common.network.rabbit;

import com.rabbitmq.client.Delivery;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.rabbitmq.network.RabbitPacket;
import fr.paladium.palashop.provider.box.common.network.client.SCPacketBoxReward;
import fr.paladium.palashop.server.shop.dto.ShopRarity;
import lombok.NonNull;

public class RBPacketBoxReward extends RabbitPacket {
  @PacketData
  private int entityId;
  
  @PacketData
  private String boxId;
  
  @PacketData
  private String player;
  
  @PacketData
  private String server;
  
  @PacketData
  private String reward;
  
  @PacketData
  private ShopRarity rarity;
  
  public RBPacketBoxReward() {}
  
  public RBPacketBoxReward(int entityId, String boxId, String player, String server, String reward, ShopRarity rarity) {
    this.entityId = entityId;
    this.boxId = boxId;
    this.player = player;
    this.server = server;
    this.reward = reward;
    this.rarity = rarity;
  }
  
  public void process(@NonNull Delivery delivery) {
    if (delivery == null)
      throw new NullPointerException("delivery is marked non-null but is null"); 
    (new SCPacketBoxReward(this.entityId, this.boxId, this.player, this.server, this.reward, this.rarity)).sendToAll();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\common\network\rabbit\RBPacketBoxReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */