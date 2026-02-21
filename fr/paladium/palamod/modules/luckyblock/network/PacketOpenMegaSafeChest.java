package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityMegaSafeChest;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;

public class PacketOpenMegaSafeChest implements IMessage {
  private int x;
  
  private int y;
  
  private int z;
  
  private String code;
  
  public PacketOpenMegaSafeChest() {}
  
  public PacketOpenMegaSafeChest(int x, int y, int z, String code) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.code = code;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.x = buf.readInt();
    this.y = buf.readInt();
    this.z = buf.readInt();
    this.code = ByteBufUtils.readUTF8String(buf);
  }
  
  public void toBytes(ByteBuf buf) {
    buf.writeInt(this.x);
    buf.writeInt(this.y);
    buf.writeInt(this.z);
    ByteBufUtils.writeUTF8String(buf, this.code);
  }
  
  public static class Handler implements IMessageHandler<PacketOpenMegaSafeChest, IMessage> {
    public IMessage onMessage(PacketOpenMegaSafeChest message, MessageContext ctx) {
      EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
      if (!player.field_70170_p.func_72899_e(message.x, message.y, message.z))
        return null; 
      TileEntity tile = player.field_70170_p.func_147438_o(message.x, message.y, message.z);
      if (tile == null || !(tile instanceof TileEntityMegaSafeChest))
        return null; 
      TileEntityMegaSafeChest chest = (TileEntityMegaSafeChest)tile;
      if (!chest.func_70300_a((EntityPlayer)player))
        return null; 
      if (chest.getCode() == null || chest.getCode().isEmpty()) {
        chest.code = message.code;
      } else if (!chest.getCode().equals(message.code)) {
        return null;
      } 
      player.field_71135_a.func_147359_a(tile.func_145844_m());
      player.openGui(PalaMod.instance, PGuiRegistry.GUI_SAFE_CHEST, tile.func_145831_w(), tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketOpenMegaSafeChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */