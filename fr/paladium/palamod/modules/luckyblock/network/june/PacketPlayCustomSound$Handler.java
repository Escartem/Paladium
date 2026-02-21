package fr.paladium.palamod.modules.luckyblock.network.june;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;

public class Handler implements IMessageHandler<PacketPlayCustomSound, IMessage> {
  public IMessage onMessage(PacketPlayCustomSound message, MessageContext ctx) {
    String name = message.soundName;
    boolean replace = message.replace;
    if (PacketPlayCustomSound.isEmpty(name)) {
      ClientProxy.stopSounds();
      return null;
    } 
    if (replace)
      ClientProxy.stopSounds(); 
    ClientProxy.playSound(name);
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\june\PacketPlayCustomSound$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */