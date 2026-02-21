package fr.paladium.palaforgeutils.lib.packet.serial.dto;

import fr.paladium.palaforgeutils.lib.packet.serial.IBufSerializer;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketSerialUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;

public class ItemStackBufSerializer implements IBufSerializer<ItemStack> {
  public void write(ByteBuf buf, Object value) {
    PacketSerialUtils.writeItemStack(buf, (ItemStack)value);
  }
  
  public ItemStack read(ByteBuf buf, Class<?>... clazz) {
    return PacketSerialUtils.readItemStack(buf);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\packet\serial\dto\ItemStackBufSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */