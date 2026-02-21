package fr.paladium.palamod.modules.spellsv2.network.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.spellsv2.utils.ClientManager;
import java.util.Map;
import net.minecraft.client.Minecraft;

public class Handler implements IMessageHandler<PacketClientResetCooldown, IMessage> {
  public IMessage onMessage(PacketClientResetCooldown message, MessageContext ctx) {
    if (ClientManager.getSpellDelay().containsKey((Minecraft.func_71410_x()).field_71439_g.func_110124_au()))
      ((Map)ClientManager.getSpellDelay().get((Minecraft.func_71410_x()).field_71439_g.func_110124_au())).clear(); 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\network\client\PacketClientResetCooldown$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */