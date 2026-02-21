package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityBruteforcer;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;

public class PacketBruteforcerStart implements IMessage {
  private int x;
  
  private int y;
  
  private int z;
  
  public PacketBruteforcerStart() {}
  
  public PacketBruteforcerStart(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.x = buf.readInt();
    this.y = buf.readInt();
    this.z = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.x);
    buf.writeInt(this.y);
    buf.writeInt(this.z);
  }
  
  public static class Handler implements IMessageHandler<PacketBruteforcerStart, IMessage> {
    public IMessage onMessage(PacketBruteforcerStart message, MessageContext ctx) {
      EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
      if (!player.field_70170_p.func_72899_e(message.x, message.y, message.z))
        return null; 
      TileEntity tile = player.field_70170_p.func_147438_o(message.x, message.y, message.z);
      if (tile == null || !(tile instanceof TileEntityBruteforcer))
        return null; 
      TileEntityBruteforcer bruteforcer = (TileEntityBruteforcer)tile;
      bruteforcer.start();
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketBruteforcerStart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */