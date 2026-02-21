package fr.paladium.palamod.modules.luckyblock.rabbitmq.listener;

import fr.paladium.palamod.modules.luckyblock.rabbitmq.packet.SpeakerMessagePacket;
import fr.paladium.palamod.util.rabbitmq.RabbitListener;
import fr.paladium.palamod.util.rabbitmq.RabbitPacketType;
import fr.paladium.palamod.util.rabbitmq.RabbitQueue;
import fr.paladium.palamod.util.rabbitmq.RabbitService;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class SpeakerMessageListener extends RabbitListener<SpeakerMessagePacket> {
  public SpeakerMessageListener(RabbitService service) {
    super(service, RabbitPacketType.PUBLISH, RabbitQueue.LB_SPEAKER_MESSAGE.getQueueName(), SpeakerMessagePacket.class);
  }
  
  public void onReceive(SpeakerMessagePacket packet) {
    if (packet == null)
      return; 
    List<EntityPlayerMP> players = (MinecraftServer.func_71276_C().func_71203_ab()).field_72404_b;
    for (EntityPlayerMP p : players) {
      if (!p.func_110124_au().toString().equals(packet.getSenderUUID()))
        p.func_146105_b((IChatComponent)new ChatComponentText("§7[§bHaut-Parleur§7] §b" + packet.getSender() + "§7: " + packet.getMessage())); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\rabbitmq\listener\SpeakerMessageListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */