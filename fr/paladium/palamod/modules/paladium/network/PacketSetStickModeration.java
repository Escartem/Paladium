package fr.paladium.palamod.modules.paladium.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

public class PacketSetStickModeration implements IMessage {
  int radius;
  
  int item;
  
  public PacketSetStickModeration() {}
  
  public PacketSetStickModeration(int radius, int item) {
    this.radius = radius;
    this.item = item;
  }
  
  public void toBytes(ByteBuf buffer) {
    buffer.writeInt(this.radius);
    buffer.writeInt(this.item);
  }
  
  public void fromBytes(ByteBuf buffer) {
    this.radius = buffer.readInt();
    this.item = buffer.readInt();
  }
  
  public static class Handler implements IMessageHandler<PacketSetStickModeration, IMessage> {
    public IMessage onMessage(PacketSetStickModeration message, MessageContext ctx) {
      EntityPlayerMP entityPlayerMP = (ctx.getServerHandler()).field_147369_b;
      NBTTagCompound compound = new NBTTagCompound();
      if (entityPlayerMP.func_70694_bm().func_77942_o())
        compound = entityPlayerMP.func_70694_bm().func_77978_p(); 
      compound.func_74768_a("radius", message.radius);
      compound.func_74768_a("item", message.item);
      entityPlayerMP.func_70694_bm().func_77982_d(compound);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\network\PacketSetStickModeration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */