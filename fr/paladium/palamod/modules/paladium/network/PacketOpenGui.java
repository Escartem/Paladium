package fr.paladium.palamod.modules.paladium.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.paladium.common.logics.AlchemyCreatorLogic;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketOpenGui implements IMessage {
  byte id;
  
  int x;
  
  int y;
  
  int z;
  
  public PacketOpenGui setInformations(byte id) {
    this.id = id;
    return this;
  }
  
  public PacketOpenGui setInformations(byte id, int x, int y, int z) {
    this.id = id;
    this.x = x;
    this.y = y;
    this.z = z;
    return this;
  }
  
  public void toBytes(ByteBuf buffer) {
    buffer.writeByte(this.id);
    buffer.writeInt(this.x);
    buffer.writeInt(this.y);
    buffer.writeInt(this.z);
  }
  
  public void fromBytes(ByteBuf buffer) {
    this.id = buffer.readByte();
    this.x = buffer.readInt();
    this.y = buffer.readInt();
    this.z = buffer.readInt();
  }
  
  public static class Handler implements IMessageHandler<PacketOpenGui, IMessage> {
    public IMessage onMessage(PacketOpenGui message, MessageContext ctx) {
      EntityPlayerMP entityPlayerMP = (ctx.getServerHandler()).field_147369_b;
      if (message.id == PGuiRegistry.GUI_ALCHEMY_CREATOR_ARROW || message.id == PGuiRegistry.GUI_ALCHEMY_CREATOR_POTION) {
        AlchemyCreatorLogic tile = (AlchemyCreatorLogic)AlchemyCreatorLogic.oppenedGui.get(UUIDUtils.toString((Entity)entityPlayerMP));
        if (tile != null)
          entityPlayerMP.openGui(PalaMod.instance, message.id, ((EntityPlayer)entityPlayerMP).field_70170_p, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e); 
        return null;
      } 
      entityPlayerMP.openGui(PalaMod.instance, message.id, ((EntityPlayer)entityPlayerMP).field_70170_p, message.x, message.y, message.z);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\network\PacketOpenGui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */