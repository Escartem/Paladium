package fr.paladium.palaforgeutils.lib.command.impl.dispatch.network;

import com.rabbitmq.client.Delivery;
import fr.paladium.palaforgeutils.lib.console.ConsoleUtils;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.rabbitmq.network.RabbitPacket;
import fr.paladium.palaforgeutils.lib.server.Server;

public class RBPacketDispatchCommand extends RabbitPacket {
  @PacketData
  private String serverType;
  
  @PacketData
  private String command;
  
  public RBPacketDispatchCommand() {}
  
  public RBPacketDispatchCommand(String serverType, String command) {
    this.serverType = serverType;
    this.command = command;
  }
  
  public void process(Delivery delivery) {
    if (this.serverType == null || this.command == null || this.serverType.isEmpty() || this.command.isEmpty())
      return; 
    if ("all".equalsIgnoreCase(this.serverType) || Server.is(new String[] { this.serverType }))
      ConsoleUtils.executeCommand(this.command); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\command\impl\dispatch\network\RBPacketDispatchCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */