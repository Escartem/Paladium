package fr.paladium.palarpg.module.dungeon.server.rabbitmq;

import com.rabbitmq.client.Delivery;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.rabbitmq.network.RabbitPacket;
import fr.paladium.palarpg.module.dungeon.common.network.chest.SCPacketRPGDungeonChestRewardBroadcast;
import lombok.NonNull;
import net.minecraft.item.ItemStack;

public class RBPacketDungeonReward extends RabbitPacket {
  @PacketData
  private String playerName;
  
  @PacketData
  private String dungeonName;
  
  @PacketData
  private ItemStack item;
  
  public RBPacketDungeonReward() {}
  
  public RBPacketDungeonReward(String playerName, String dungeonName, ItemStack item) {
    this.playerName = playerName;
    this.dungeonName = dungeonName;
    this.item = item;
  }
  
  @SideOnly(Side.SERVER)
  public void process(@NonNull Delivery delivery) {
    if (delivery == null)
      throw new NullPointerException("delivery is marked non-null but is null"); 
    (new SCPacketRPGDungeonChestRewardBroadcast(this.playerName, this.dungeonName, this.item)).sendToAll();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\rabbitmq\RBPacketDungeonReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */