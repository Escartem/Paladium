package fr.paladium.palaforgeutils.lib.sound;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;

public class SCPacketPlaySoundRecord implements IMessage {
  private SoundRecordServer soundRecord;
  
  public SCPacketPlaySoundRecord() {}
  
  public SCPacketPlaySoundRecord(SoundRecordServer soundRecord) {
    this.soundRecord = soundRecord;
  }
  
  public void fromBytes(ByteBuf buf) {
    if (!buf.isReadable())
      return; 
    this.soundRecord = new SoundRecordServer();
    this.soundRecord.read(buf);
  }
  
  public void toBytes(ByteBuf buf) {
    if (this.soundRecord == null)
      return; 
    this.soundRecord.write(buf);
  }
  
  public static class Handler implements IMessageHandler<SCPacketPlaySoundRecord, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(SCPacketPlaySoundRecord message, MessageContext ctx) {
      if (message.soundRecord == null)
        return null; 
      SoundRecord.create(message.soundRecord).play();
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\sound\SCPacketPlaySoundRecord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */