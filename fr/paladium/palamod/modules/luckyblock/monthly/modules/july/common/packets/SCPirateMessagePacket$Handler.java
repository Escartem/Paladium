package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.uis.ComputerUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class Handler implements IMessageHandler<SCPirateMessagePacket, IMessage> {
  public IMessage onMessage(SCPirateMessagePacket message, MessageContext ctx) {
    DoubleLocation location = new DoubleLocation(0.0D, 0.0D, 0.0D);
    Minecraft.func_71410_x().func_147108_a((GuiScreen)new ComputerUI(location));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\packets\SCPirateMessagePacket$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */