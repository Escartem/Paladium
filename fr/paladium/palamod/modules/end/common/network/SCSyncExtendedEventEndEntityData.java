package fr.paladium.palamod.modules.end.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;

public class SCSyncExtendedEventEndEntityData implements IMessage {
  private NBTTagCompound nbt;
  
  public SCSyncExtendedEventEndEntityData(NBTTagCompound nbt) {
    this.nbt = nbt;
  }
  
  public SCSyncExtendedEventEndEntityData() {}
  
  public void toBytes(ByteBuf buf) {
    PacketBuffer buffer = new PacketBuffer(buf);
    try {
      buffer.func_150786_a(this.nbt);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public void fromBytes(ByteBuf buf) {
    if (!buf.isReadable())
      return; 
    PacketBuffer buffer = new PacketBuffer(buf);
    try {
      this.nbt = buffer.func_150793_b();
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static class Handler implements IMessageHandler<SCSyncExtendedEventEndEntityData, IMessage> {
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(SCSyncExtendedEventEndEntityData message, MessageContext ctx) {
      if (message.nbt == null || (Minecraft.func_71410_x()).field_71439_g == null)
        return null; 
      ExtendedEventEndEntity eep = ExtendedEventEndEntity.get((Entity)(Minecraft.func_71410_x()).field_71439_g);
      if (eep == null)
        return null; 
      eep.loadNBTData(message.nbt);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\end\common\network\SCSyncExtendedEventEndEntityData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */