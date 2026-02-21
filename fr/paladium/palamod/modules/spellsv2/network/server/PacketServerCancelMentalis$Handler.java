package fr.paladium.palamod.modules.spellsv2.network.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.spellsv2.PSpellsV2;
import fr.paladium.palamod.modules.spellsv2.entity.EntityGhost;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientActiveSpell;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientUseMentalis;
import fr.paladium.palamod.modules.spellsv2.utils.ServerManager;
import fr.paladium.palamod.modules.spellsv2.utils.Spells;
import fr.paladium.palamod.util.FastUUID;
import java.util.List;
import net.minecraft.entity.Entity;

public class Handler implements IMessageHandler<PacketServerCancelMentalis, IMessage> {
  public IMessage onMessage(PacketServerCancelMentalis message, MessageContext ctx) {
    EntityGhost ghost = (EntityGhost)ServerManager.getMentalis().get((ctx.getServerHandler()).field_147369_b.func_110124_au());
    String uuid = FastUUID.toString((Entity)(ctx.getServerHandler()).field_147369_b);
    if (ghost.func_70089_S())
      ghost.func_70106_y(); 
    if (ServerManager.getActiveSpells().get(Integer.valueOf(Spells.MENTALIS.getSpell().getId())) != null && (
      (List)ServerManager.getActiveSpells().get(Integer.valueOf(Spells.MENTALIS.getSpell().getId())))
      .contains((ctx.getServerHandler()).field_147369_b.func_110124_au())) {
      ServerManager.removeMentalis((ctx.getServerHandler()).field_147369_b.func_110124_au());
      ServerManager.removeFreeze((ctx.getServerHandler()).field_147369_b);
      PSpellsV2.network.sendTo((IMessage)new PacketClientActiveSpell(Spells.MENTALIS
            .getSpell().getId(), uuid, false), 
          
          (ctx.getServerHandler()).field_147369_b);
      ServerManager.removeActiveSpell(Spells.MENTALIS.getSpell().getId(), uuid);
      PSpellsV2.network.sendTo((IMessage)new PacketClientUseMentalis(false, 
            FastUUID.toString((Entity)ghost)), 
          (ctx.getServerHandler()).field_147369_b);
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\network\server\PacketServerCancelMentalis$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */