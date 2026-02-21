package fr.paladium.palamod.modules.paladium.common.items.boost.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.paladium.common.items.boost.hud.ModuleBoost;

public class Handler implements IMessageHandler<PlayerBoostEPropertiesSync, IMessage> {
  public IMessage onMessage(PlayerBoostEPropertiesSync message, MessageContext ctx) {
    ModuleBoost.getInstance().setBoostDoubleXp(PlayerBoostEPropertiesSync.access$000(message));
    ModuleBoost.getInstance().setBoostMinerFou(PlayerBoostEPropertiesSync.access$100(message));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\boost\packet\PlayerBoostEPropertiesSync$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */