package fr.paladium.palawither.common.network;

import com.rabbitmq.client.Delivery;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.rabbitmq.network.RabbitPacket;
import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palawither.common.wither.base.IWither;
import lombok.NonNull;

public class RBPacketWitherSpawn extends RabbitPacket {
  @PacketData
  private String name;
  
  @PacketData
  private String sound;
  
  @PacketData
  private String server;
  
  public RBPacketWitherSpawn() {}
  
  public RBPacketWitherSpawn(String name, String sound, String server) {
    this.name = name;
    this.sound = sound;
    this.server = server;
  }
  
  public RBPacketWitherSpawn(@NonNull IWither wither) {
    if (wither == null)
      throw new NullPointerException("wither is marked non-null but is null"); 
    this.name = wither.getDisplayName();
    this.sound = (wither.getInvokeSound() != null) ? wither.getInvokeSound().toString() : null;
    this.server = Server.getServerName();
  }
  
  @SideOnly(Side.SERVER)
  public void process(@NonNull Delivery delivery) {
    if (delivery == null)
      throw new NullPointerException("delivery is marked non-null but is null"); 
    (new SCPacketWitherSpawn(this.name, this.sound, IWither.WitherInvokeAlert.BROADCAST, this.server)).sendToAll();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\common\network\RBPacketWitherSpawn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */