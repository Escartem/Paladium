package fr.paladium.palamod.modules.hunter.networks;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import fr.paladium.palamod.modules.hunter.PHunter;

public class HunterCompassNetwork {
  public HunterCompassNetwork() {
    System.out.println("##Init Hunter Compass Network");
  }
  
  @SubscribeEvent
  public void getMessage(FMLNetworkEvent.ClientCustomPacketEvent event) {
    if (event.packet.channel().equals("huntercompass")) {
      ByteArrayDataInput in = ByteStreams.newDataInput(event.packet.payload().array());
      String[] str = in.readUTF().split("_");
      int x = Integer.valueOf(str[1]).intValue();
      int y = Integer.valueOf(str[2]).intValue();
      int z = Integer.valueOf(str[3]).intValue();
      int type = Integer.valueOf(str[0]).intValue();
      PHunter.network.sendToServer(new PacketUpdateHunterCompass(x, y, z, type));
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\networks\HunterCompassNetwork.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */