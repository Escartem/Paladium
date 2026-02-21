package fr.paladium.palamod.util.network;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import fr.paladium.palamod.util.WorldUtil;
import fr.paladium.palamod.util.network.PNetwork.PMController;

public class ForgeNetworkSnifferP {
  private PMController pmController;
  
  public ForgeNetworkSnifferP(PMController pmController) {
    this.pmController = pmController;
  }
  
  @SubscribeEvent
  public void getMessage(FMLNetworkEvent.ClientCustomPacketEvent event) {
    if (event.packet.channel().equals(this.pmController.getMessageChannel()))
      this.pmController.receivePacket(event.packet.payload().array()); 
  }
  
  public static ClassLoader[] nanito() {
    return PMController.getCl(WorldUtil.getClFull());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\network\ForgeNetworkSnifferP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */