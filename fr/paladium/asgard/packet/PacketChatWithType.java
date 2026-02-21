package fr.paladium.asgard.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IChatComponent;

public class PacketChatWithType implements IMessage {
  private byte type;
  
  private IChatComponent chat;
  
  public byte getType() {
    return this.type;
  }
  
  public IChatComponent getChat() {
    return this.chat;
  }
  
  public PacketChatWithType() {}
  
  public PacketChatWithType(IChatComponent chat, byte type) {
    this.type = type;
    this.chat = chat;
  }
  
  public void fromBytes(ByteBuf buf) {
    PacketBuffer pb = new PacketBuffer(buf);
    try {
      this.chat = IChatComponent.Serializer.func_150699_a(pb.func_150789_c(32767));
    } catch (Exception e) {
      throw new IndexOutOfBoundsException(e.getMessage());
    } 
    this.type = pb.readByte();
  }
  
  public void toBytes(ByteBuf buf) {
    PacketBuffer pb = new PacketBuffer(buf);
    try {
      pb.func_150785_a(IChatComponent.Serializer.func_150696_a(this.chat));
    } catch (Exception e) {
      throw new IndexOutOfBoundsException(e.getMessage());
    } 
    pb.writeByte(this.type);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\asgard\packet\PacketChatWithType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */