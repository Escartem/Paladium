package fr.paladium.palamod.modules.luckyblock.network.may;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;

public class PacketOpenGuiBook implements IMessage {
  private ItemStack book;
  
  public PacketOpenGuiBook() {}
  
  public PacketOpenGuiBook(ItemStack book) {
    this.book = book;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.book = ByteBufUtils.readItemStack(buf);
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeItemStack(buf, this.book);
  }
  
  public static class Handler implements IMessageHandler<PacketOpenGuiBook, IMessage> {
    public IMessage onMessage(PacketOpenGuiBook message, MessageContext ctx) {
      ClientProxy.openBook(message.book);
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\may\PacketOpenGuiBook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */