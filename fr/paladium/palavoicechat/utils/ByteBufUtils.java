package fr.paladium.palavoicechat.utils;

import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import io.netty.buffer.ByteBuf;
import java.util.UUID;
import lombok.NonNull;

public class ByteBufUtils {
  public static UUID readUUID(ByteBuf buf) {
    return UUIDUtils.from(cpw.mods.fml.common.network.ByteBufUtils.readUTF8String(buf));
  }
  
  public static void writeUUID(ByteBuf buf, @NonNull UUID uuid) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    cpw.mods.fml.common.network.ByteBufUtils.writeUTF8String(buf, UUIDUtils.toString(uuid));
  }
  
  public static void writeBytes(ByteBuf buf, byte[] data) {
    if (buf.maxWritableBytes() > data.length + 4) {
      buf.writeInt(data.length);
      for (byte octet : data)
        buf.writeByte(octet); 
    } 
  }
  
  public static byte[] readBytes(ByteBuf buf) {
    int length = buf.readInt();
    byte[] data = new byte[length];
    for (int i = 0; i < length; i++)
      data[i] = buf.readByte(); 
    return data;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicecha\\utils\ByteBufUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */