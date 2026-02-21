package fr.paladium.palamod.modules.luckyblock.network.june;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import io.netty.buffer.ByteBuf;

public class PacketPlayCustomSound implements IMessage {
  public String soundName;
  
  public boolean replace;
  
  public PacketPlayCustomSound() {}
  
  public PacketPlayCustomSound(String soundName, boolean replace) {
    if (soundName == null) {
      this.soundName = "";
    } else {
      this.soundName = soundName;
    } 
    this.replace = replace;
  }
  
  public PacketPlayCustomSound(String soundName) {
    this(soundName, true);
  }
  
  public static boolean isEmpty(String str) {
    return (str == null || str.length() == 0);
  }
  
  public void fromBytes(ByteBuf buf) {
    this.soundName = ByteBufUtils.readUTF8String(buf);
    this.replace = buf.readBoolean();
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, this.soundName);
    buf.writeBoolean(this.replace);
  }
  
  public static class Handler implements IMessageHandler<PacketPlayCustomSound, IMessage> {
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
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\june\PacketPlayCustomSound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */