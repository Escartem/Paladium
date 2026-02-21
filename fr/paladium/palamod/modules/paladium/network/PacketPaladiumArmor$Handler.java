package fr.paladium.palamod.modules.paladium.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.paladium.network.data.PaladiumPlayer;
import net.minecraft.entity.Entity;

public class Handler implements IMessageHandler<PacketPaladiumArmor, IMessage> {
  public IMessage onMessage(PacketPaladiumArmor message, MessageContext ctx) {
    int type = message.paladiumArmorType;
    if (type > (PaladiumPlayer.get((Entity)(ctx.getServerHandler()).field_147369_b).isTrixiumRank() ? 11 : 10))
      type = 0; 
    if (type < 0)
      type = PaladiumPlayer.get((Entity)(ctx.getServerHandler()).field_147369_b).isTrixiumRank() ? 11 : 10; 
    PaladiumPlayer.get((Entity)(ctx.getServerHandler()).field_147369_b).setPaladiumArmorType(type);
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\network\PacketPaladiumArmor$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */