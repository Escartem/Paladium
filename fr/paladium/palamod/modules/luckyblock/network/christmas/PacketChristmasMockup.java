package fr.paladium.palamod.modules.luckyblock.network.christmas;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.tileentity.christmas.TileEntityChristmasMockup;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PacketChristmasMockup implements IMessage {
  private TileEntityChristmasMockup tileEntity;
  
  private int orientation;
  
  private int x;
  
  private int y;
  
  private int z;
  
  public PacketChristmasMockup() {}
  
  public PacketChristmasMockup(TileEntityChristmasMockup tileEntity, int orientation) {
    this.tileEntity = tileEntity;
    this.orientation = orientation;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.x = buf.readInt();
    this.y = buf.readInt();
    this.z = buf.readInt();
    this.orientation = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.tileEntity.field_145851_c);
    buf.writeInt(this.tileEntity.field_145848_d);
    buf.writeInt(this.tileEntity.field_145849_e);
    buf.writeInt(this.orientation);
  }
  
  public static class Handler implements IMessageHandler<PacketChristmasMockup, PacketChristmasMockup> {
    public PacketChristmasMockup onMessage(PacketChristmasMockup packet, MessageContext ctx) {
      if (ctx.side == Side.CLIENT)
        return handleClient(packet, (PalaMod.proxy.getPlayerEntity(ctx)).field_70170_p); 
      if (ctx.side == Side.SERVER)
        return handleServer(packet, (ctx.getServerHandler()).field_147369_b.field_70170_p); 
      return null;
    }
    
    public PacketChristmasMockup handleClient(PacketChristmasMockup packet, World world) {
      if (world == null)
        return null; 
      TileEntity buffer = world.func_147438_o(packet.x, packet.y, packet.z);
      if (buffer instanceof TileEntityChristmasMockup) {
        TileEntityChristmasMockup tileEntity = (TileEntityChristmasMockup)world.func_147438_o(packet.x, packet.y, packet.z);
        tileEntity.setOrientation(packet.orientation);
      } 
      return null;
    }
    
    public PacketChristmasMockup handleServer(PacketChristmasMockup packet, World world) {
      if (world == null)
        return null; 
      if (!world.func_72899_e(packet.x, packet.y, packet.z))
        return null; 
      TileEntity buffer = world.func_147438_o(packet.x, packet.y, packet.z);
      if (buffer instanceof TileEntityChristmasMockup) {
        TileEntityChristmasMockup tileEntity = (TileEntityChristmasMockup)world.func_147438_o(packet.x, packet.y, packet.z);
        return new PacketChristmasMockup(tileEntity, tileEntity.getOrientation());
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\christmas\PacketChristmasMockup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */