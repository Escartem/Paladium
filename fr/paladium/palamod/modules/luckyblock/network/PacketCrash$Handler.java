package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;

public class Handler implements IMessageHandler<PacketCrash, IMessage> {
  public IMessage onMessage(PacketCrash message, MessageContext ctx) {
    Minecraft.func_71410_x()
      .func_71404_a(new CrashReport(message.title, new Throwable(message.message)));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketCrash$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */