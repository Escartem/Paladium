package fr.paladium.palamod.modules.paladium.network;

import com.google.common.io.ByteStreams;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import fr.paladium.palamod.modules.addons.network.MessageP;
import net.minecraft.client.Minecraft;

public class ClearChatSniffer {
  @SubscribeEvent
  public void getMessage(FMLNetworkEvent.ClientCustomPacketEvent event) {
    if (event.packet.channel().equalsIgnoreCase("clearchat")) {
      MessageP messageP = MessageP.fromString(ByteStreams.newDataInput(event.packet.payload().array()).readUTF());
      switch (messageP.getId()) {
        case 1:
          (Minecraft.func_71410_x()).field_71456_v.func_146158_b().func_146231_a();
          break;
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\network\ClearChatSniffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */