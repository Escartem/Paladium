package fr.paladium.palatrade.lib.odin.modules.extended.internal.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palatrade.lib.odin.modules.extended.lib.ExtendedEntityProperties;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;

public class SCSyncExtendedEntityProperties implements IMessage {
  private String propName;
  
  private NBTTagCompound nbt;
  
  public SCSyncExtendedEntityProperties() {}
  
  public SCSyncExtendedEntityProperties(String propName, NBTTagCompound nbt) {
    this.propName = propName;
    this.nbt = nbt;
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, this.propName);
    PacketBuffer buffer = new PacketBuffer(buf);
    try {
      buffer.func_150786_a(this.nbt);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public void fromBytes(ByteBuf buf) {
    this.propName = ByteBufUtils.readUTF8String(buf);
    PacketBuffer buffer = new PacketBuffer(buf);
    try {
      this.nbt = buffer.func_150793_b();
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static class Handler implements IMessageHandler<SCSyncExtendedEntityProperties, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(SCSyncExtendedEntityProperties message, MessageContext ctx) {
      if (message.propName == null)
        return null; 
      ExtendedEntityProperties eep = (ExtendedEntityProperties)(Minecraft.func_71410_x()).field_71439_g.getExtendedProperties(message.propName);
      eep.loadNBTData(message.nbt);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\lib\odin\modules\extended\internal\network\SCSyncExtendedEntityProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */