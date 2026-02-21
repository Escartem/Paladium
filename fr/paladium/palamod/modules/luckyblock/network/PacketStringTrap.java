package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityStringTrapOff;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketStringTrap implements IMessage {
  String player;
  
  TileEntityStringTrapOff te;
  
  int x;
  
  int y;
  
  int z;
  
  public void addInformations(String player, TileEntityStringTrapOff te) {
    this.player = player;
    this.te = te;
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, this.player);
    buf.writeInt(this.te.field_145851_c);
    buf.writeInt(this.te.field_145848_d);
    buf.writeInt(this.te.field_145849_e);
  }
  
  public void fromBytes(ByteBuf buf) {
    this.player = ByteBufUtils.readUTF8String(buf);
    this.x = buf.readInt();
    this.y = buf.readInt();
    this.z = buf.readInt();
  }
  
  public static class Handler implements IMessageHandler<PacketStringTrap, IMessage> {
    public IMessage onMessage(PacketStringTrap message, MessageContext ctx) {
      EntityPlayerMP entityPlayerMP = (ctx.getServerHandler()).field_147369_b;
      if (!((EntityPlayer)entityPlayerMP).field_70170_p.func_72899_e(message.x, message.y, message.z))
        return null; 
      TileEntityStringTrapOff detector = (TileEntityStringTrapOff)((EntityPlayer)entityPlayerMP).field_70170_p.func_147438_o(message.x, message.y, message.z);
      if (detector != null)
        detector.setWhitelist(message.player); 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketStringTrap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */