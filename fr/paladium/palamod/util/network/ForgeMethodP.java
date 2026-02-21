package fr.paladium.palamod.util.network;

import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import fr.paladium.palamod.util.network.PNetwork.IMethodP;
import fr.paladium.palamod.util.network.PNetwork.PMController;

public class ForgeMethodP implements IMethodP {
  public void load(PMController pmController) {
    FMLEventChannel fmlEventChannel = NetworkRegistry.INSTANCE.newEventDrivenChannel(pmController.getMessageChannel());
    fmlEventChannel.register(new ForgeNetworkSnifferP(pmController));
  }
  
  public void sendPacket(PMController pmController, byte[] data, Object player) {}
}


/* Location:              E:\Paladium\!\fr\paladium\palamo\\util\network\ForgeMethodP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */