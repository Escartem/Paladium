package fr.paladium.palatrade.lib.odin.modules.packet.lib;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palatrade.lib.odin.modules.packet.OdinPacketModule;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public abstract class ForgePacket implements IMessage {
  public abstract void processServer(EntityPlayerMP paramEntityPlayerMP);
  
  public abstract void processClient();
  
  public abstract void write(ByteBuf paramByteBuf);
  
  public abstract void read(ByteBuf paramByteBuf);
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(OdinPacketModule.getInstance().getPacketId(getClass()));
    write(buf);
  }
  
  public void fromBytes(ByteBuf buf) {
    read(buf);
  }
  
  public void writeBoolean(ByteBuf buf, boolean value) {
    buf.writeBoolean(value);
  }
  
  public void writeByte(ByteBuf buf, byte value) {
    buf.writeByte(value);
  }
  
  public void writeChar(ByteBuf buf, char value) {
    buf.writeChar(value);
  }
  
  public void writeDouble(ByteBuf buf, double value) {
    buf.writeDouble(value);
  }
  
  public void writeFloat(ByteBuf buf, float value) {
    buf.writeFloat(value);
  }
  
  public void writeLong(ByteBuf buf, long value) {
    buf.writeLong(value);
  }
  
  public void writeInt(ByteBuf buf, int value) {
    buf.writeInt(value);
  }
  
  public void writeString(ByteBuf buf, String value) {
    ByteBufUtils.writeUTF8String(buf, value);
  }
  
  public void writeNbt(ByteBuf buf, NBTTagCompound nbt) {
    ByteBufUtils.writeTag(buf, nbt);
  }
  
  public void writeItemStack(ByteBuf buf, ItemStack item) {
    ByteBufUtils.writeItemStack(buf, item);
  }
  
  public boolean readBoolean(ByteBuf buf) {
    return buf.readBoolean();
  }
  
  public byte readByte(ByteBuf buf) {
    return buf.readByte();
  }
  
  public char readChar(ByteBuf buf) {
    return buf.readChar();
  }
  
  public double readDouble(ByteBuf buf) {
    return buf.readDouble();
  }
  
  public float readFloat(ByteBuf buf) {
    return buf.readFloat();
  }
  
  public long readLong(ByteBuf buf) {
    return buf.readLong();
  }
  
  public int readInt(ByteBuf buf) {
    return buf.readInt();
  }
  
  public String readString(ByteBuf buf) {
    return ByteBufUtils.readUTF8String(buf);
  }
  
  public NBTTagCompound readNbt(ByteBuf buf) {
    return ByteBufUtils.readTag(buf);
  }
  
  public ItemStack readItemStack(ByteBuf buf) {
    return ByteBufUtils.readItemStack(buf);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\lib\odin\modules\packet\lib\ForgePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */