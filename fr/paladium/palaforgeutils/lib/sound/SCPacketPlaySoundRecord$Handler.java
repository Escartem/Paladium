package fr.paladium.palaforgeutils.lib.sound;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Handler implements IMessageHandler<SCPacketPlaySoundRecord, IMessage> {
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(SCPacketPlaySoundRecord message, MessageContext ctx) {
    if (SCPacketPlaySoundRecord.access$000(message) == null)
      return null; 
    SoundRecord.create(SCPacketPlaySoundRecord.access$000(message)).play();
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\sound\SCPacketPlaySoundRecord$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */