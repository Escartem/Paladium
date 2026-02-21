package fr.paladium.palaforgeutils.lib.packet.serial.dto;

import fr.paladium.palaforgeutils.lib.packet.serial.IBufSerializer;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;

public class NBTTagCompoundBufSerializer implements IBufSerializer<NBTTagCompound> {
  public void write(ByteBuf buf, Object value) {
    PacketSerialUtils.writeNbt(buf, (NBTTagCompound)value);
  }
  
  public NBTTagCompound read(ByteBuf buf, Class<?>... clazz) {
    return PacketSerialUtils.readNbt(buf);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\packet\serial\dto\NBTTagCompoundBufSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */