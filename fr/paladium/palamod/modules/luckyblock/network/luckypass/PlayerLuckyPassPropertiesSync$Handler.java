package fr.paladium.palamod.modules.luckyblock.network.luckypass;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.PalaMod;
import net.minecraft.entity.player.EntityPlayer;

public class Handler implements IMessageHandler<PlayerLuckyPassPropertiesSync, IMessage> {
  public IMessage onMessage(PlayerLuckyPassPropertiesSync message, MessageContext ctx) {
    EntityPlayer player = PalaMod.proxy.getPlayerEntity(ctx).func_130014_f_().func_72924_a(PlayerLuckyPassPropertiesSync.access$000(message));
    if (player == null)
      return null; 
    PlayerLuckyPassProperties.get(player).setHasLuckyPass(PlayerLuckyPassPropertiesSync.access$100(message));
    PlayerLuckyPassProperties.get(player).setWins(PlayerLuckyPassPropertiesSync.access$200(message));
    PlayerLuckyPassProperties.get(player).setDate(PlayerLuckyPassPropertiesSync.access$300(message));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\network\luckypass\PlayerLuckyPassPropertiesSync$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */