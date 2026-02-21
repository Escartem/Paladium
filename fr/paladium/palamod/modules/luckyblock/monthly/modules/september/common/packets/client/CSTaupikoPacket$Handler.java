package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.packets.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.entities.EntityTaupiko;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;

public class Handler implements IMessageHandler<CSTaupikoPacket, IMessage> {
  public IMessage onMessage(CSTaupikoPacket message, MessageContext ctx) {
    if (ctx.side == Side.CLIENT)
      return null; 
    EntityPlayerMP player = (ctx.getServerHandler()).field_147369_b;
    List<EntityTaupiko> entities = player.field_70170_p.func_72872_a(EntityTaupiko.class, player.field_70121_D
        .func_72314_b(5.0D, 5.0D, 5.0D));
    Iterator<EntityTaupiko> iterator = entities.iterator();
    if (iterator.hasNext()) {
      EntityTaupiko entity = iterator.next();
      entity.handlePacket(player, CSTaupikoPacket.access$000(message));
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\packets\client\CSTaupikoPacket$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */