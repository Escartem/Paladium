package fr.paladium.asgard;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IChatComponent;
import org.apache.commons.codec.EncoderException;

public class AsgardNetUtils extends PacketBuffer {
  public AsgardNetUtils(PacketBuffer buf) {
    super((ByteBuf)buf);
  }
  
  public static IChatComponent readChatComponent(PacketBuffer buf) throws IOException {
    return IChatComponent.Serializer.func_150699_a(buf.func_150789_c(32767));
  }
  
  public static void writeChatComponent(PacketBuffer buf, IChatComponent component) {
    try {
      writeString(buf, IChatComponent.Serializer.func_150696_a(component));
    } catch (EncoderException e) {
      e.printStackTrace();
    } 
  }
  
  public static <T extends Enum<T>> T readEnumValue(PacketBuffer buf, Class<T> enumClass) {
    return (T)((Enum[])enumClass.getEnumConstants())[buf.func_150792_a()];
  }
  
  public static void writeEnumValue(PacketBuffer buf, Enum<?> value) {
    buf.func_150787_b(value.ordinal());
  }
  
  public static AsgardNetUtils writeString(PacketBuffer buf, String string) throws EncoderException {
    byte[] abyte = string.getBytes(Charsets.UTF_8);
    if (abyte.length > 32767)
      throw new EncoderException("String too big (was " + string
          .length() + " bytes encoded, max " + '翿' + ")"); 
    buf.func_150787_b(abyte.length);
    buf.writeBytes(abyte);
    return new AsgardNetUtils(buf);
  }
  
  public IChatComponent readChatComponent() throws IOException {
    return IChatComponent.Serializer.func_150699_a(func_150789_c(32767));
  }
  
  public void writeChatComponent(IChatComponent component) throws EncoderException {
    writeString(IChatComponent.Serializer.func_150696_a(component));
  }
  
  public <T extends Enum<T>> T readEnumValue(Class<T> enumClass) {
    return (T)((Enum[])enumClass.getEnumConstants())[func_150792_a()];
  }
  
  public void writeEnumValue(Enum<?> value) {
    func_150787_b(value.ordinal());
  }
  
  public AsgardNetUtils writeString(String string) throws EncoderException {
    byte[] abyte = string.getBytes(Charsets.UTF_8);
    if (abyte.length > 32767)
      throw new EncoderException("String too big (was " + string
          .length() + " bytes encoded, max " + '翿' + ")"); 
    func_150787_b(abyte.length);
    writeBytes(abyte);
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\asgard\AsgardNetUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */