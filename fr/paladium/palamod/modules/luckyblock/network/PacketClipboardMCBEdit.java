package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;

public class PacketClipboardMCBEdit implements IMessage {
  private int x;
  
  private int y;
  
  private int z;
  
  private int currentPage;
  
  private ItemStack clipStack;
  
  public PacketClipboardMCBEdit(ItemStack clipStack, int tilex, int tiley, int tilez, int currentPage) {
    this.clipStack = clipStack;
    this.x = tilex;
    this.y = tiley;
    this.z = tilez;
    this.currentPage = currentPage;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.clipStack = ByteBufUtils.readItemStack(buf);
    this.x = buf.readInt();
    this.y = buf.readInt();
    this.z = buf.readInt();
    this.currentPage = buf.readInt();
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeItemStack(buf, this.clipStack);
    buf.writeInt(this.x);
    buf.writeInt(this.y);
    buf.writeInt(this.z);
    buf.writeInt(this.currentPage);
  }
  
  public static class Handler implements IMessageHandler<PacketClipboardMCBEdit, IMessage> {
    public IMessage onMessage(PacketClipboardMCBEdit message, MessageContext ctx) {
      if (message.clipStack != null);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketClipboardMCBEdit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */