package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.config.ClientHalloweenTradeConfig;
import fr.paladium.palamod.modules.luckyblock.gui.halloween.UIHalloweenTrade;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;
import net.minecraft.client.Minecraft;

@PacketHandler
public class Handler implements IMessageHandler<SCPacketUpdateHalloweenTrade, IMessage> {
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(SCPacketUpdateHalloweenTrade message, MessageContext ctx) {
    ClientProxy.configHalloween = new ClientHalloweenTradeConfig(message.trade, message.tradeAmount, SCPacketUpdateHalloweenTrade.access$000(message), SCPacketUpdateHalloweenTrade.access$100(message), SCPacketUpdateHalloweenTrade.access$200(message), SCPacketUpdateHalloweenTrade.access$300(message));
    if ((Minecraft.func_71410_x()).field_71462_r instanceof UIHalloweenTrade)
      ((UIHalloweenTrade)(Minecraft.func_71410_x()).field_71462_r).setConfig(ClientProxy.configHalloween); 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\SCPacketUpdateHalloweenTrade$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */