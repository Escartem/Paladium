package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;

public class PacketCrash implements IMessage {
  public String title;
  
  public String message;
  
  public PacketCrash() {}
  
  public PacketCrash(String message) {
    this("LuckyBlock", message);
  }
  
  public PacketCrash(String title, String message) {
    this.title = title;
    this.message = message;
  }
  
  public void fromBytes(ByteBuf buf) {
    this.title = ByteBufUtils.readUTF8String(buf);
    this.message = ByteBufUtils.readUTF8String(buf);
  }
  
  public void toBytes(ByteBuf buf) {
    ByteBufUtils.writeUTF8String(buf, this.title);
    ByteBufUtils.writeUTF8String(buf, this.message);
  }
  
  public static class Handler implements IMessageHandler<PacketCrash, IMessage> {
    public IMessage onMessage(PacketCrash message, MessageContext ctx) {
      Minecraft.func_71410_x()
        .func_71404_a(new CrashReport(message.title, new Throwable(message.message)));
      return null;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketCrash.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */