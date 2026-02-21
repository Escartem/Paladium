package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityClipboard;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

public class PacketClipboard implements IMessage {
  private int x;
  
  private int y;
  
  private int z;
  
  private int selection;
  
  public PacketClipboard() {}
  
  public PacketClipboard(int x, int y, int z, int selection) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.selection = selection;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.x = buf.readInt();
    this.y = buf.readInt();
    this.z = buf.readInt();
    this.selection = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.x);
    buf.writeInt(this.y);
    buf.writeInt(this.z);
    buf.writeInt(this.selection);
  }
  
  public static class Handler implements IMessageHandler<PacketClipboard, IMessage> {
    public IMessage onMessage(PacketClipboard message, MessageContext ctx) {
      if (!(ctx.getServerHandler()).field_147369_b.field_70170_p.func_72899_e(message.x, message.y, message.z))
        return null; 
      TileEntity tile = (ctx.getServerHandler()).field_147369_b.field_70170_p.func_147438_o(message.x, message
          .y, message.z);
      if (tile != null && tile instanceof TileEntityClipboard) {
        TileEntityClipboard clipboard = (TileEntityClipboard)tile;
        clipboard.updateClipboardFromPlayerSelection(message.selection);
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketClipboard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */