package fr.paladium.palamod.modules.luckyblock.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.modules.luckyblock.network.luckypass.PlayerLuckyPassProperties;
import net.minecraft.entity.player.EntityPlayer;

public class Handler implements IMessageHandler<PacketLuckyEventBypass, IMessage> {
  public IMessage onMessage(PacketLuckyEventBypass message, MessageContext ctx) {
    PlayerLuckyPassProperties properties = PlayerLuckyPassProperties.get((EntityPlayer)(ctx.getServerHandler()).field_147369_b);
    if (properties.isHasLuckyEventBypassNow()) {
      properties.setHasLuckyEventBypassNow(false);
      PacketLuckyEventBypass.access$100(PacketLuckyEventBypass.access$000(message), (ctx.getServerHandler()).field_147369_b);
    } else if (ctx.side == Side.CLIENT) {
      properties.setHasLuckyEventBypassNow(false);
    } 
    (ctx.getServerHandler()).field_147369_b.func_71053_j();
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\PacketLuckyEventBypass$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */