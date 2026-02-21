package fr.paladium.palamod.modules.paladium.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.paladium.common.logics.TileCrusher;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PacketCrusherButton implements IMessage {
  private int type;
  
  private int x;
  
  private int y;
  
  private int z;
  
  public PacketCrusherButton() {}
  
  public PacketCrusherButton(TileCrusher.EnumCrusherResult type, int x, int y, int z) {
    this.type = type.ordinal();
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public void toBytes(ByteBuf buffer) {
    buffer.writeInt(this.type);
    buffer.writeInt(this.x);
    buffer.writeInt(this.y);
    buffer.writeInt(this.z);
  }
  
  public void fromBytes(ByteBuf buffer) {
    this.type = buffer.readInt();
    this.x = buffer.readInt();
    this.y = buffer.readInt();
    this.z = buffer.readInt();
  }
  
  public static class Handler implements IMessageHandler<PacketCrusherButton, IMessage> {
    public IMessage onMessage(PacketCrusherButton message, MessageContext ctx) {
      EntityPlayerMP entityPlayerMP = (ctx.getServerHandler()).field_147369_b;
      if (message.type < 0 || message.type > 4)
        return null; 
      if (entityPlayerMP.func_130014_f_() != null) {
        World world = entityPlayerMP.func_130014_f_();
        TileEntity tile = world.func_147438_o(message.x, message.y, message.z);
        if (tile != null && tile instanceof TileCrusher) {
          TileCrusher tileCrusher = (TileCrusher)tile;
          if (tileCrusher.func_70300_a((EntityPlayer)entityPlayerMP) && tileCrusher.getExtractItem().equals(TileCrusher.EnumCrusherResult.NONE))
            tileCrusher.setCraft(TileCrusher.EnumCrusherResult.values()[message.type]); 
        } 
      } 
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\network\PacketCrusherButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */