package fr.paladium.palamod.modules.spellsv2.network.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.utils.EventUtils;
import fr.paladium.palamod.modules.spellsv2.PSpellsV2;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientActiveSpell;
import fr.paladium.palamod.modules.spellsv2.utils.ServerManager;
import fr.paladium.palamod.modules.spellsv2.utils.Spells;
import fr.paladium.palamod.util.FastUUID;
import java.util.List;
import net.minecraft.entity.Entity;

public class Handler implements IMessageHandler<PacketServerCancelInertium, IMessage> {
  public IMessage onMessage(PacketServerCancelInertium message, MessageContext ctx) {
    String uuid = FastUUID.toString((Entity)(ctx.getServerHandler()).field_147369_b);
    (ctx.getServerHandler()).field_147369_b.func_130014_f_().func_147468_f((
        (Integer)((List<Integer>)ServerManager.getFreeze().get((ctx.getServerHandler()).field_147369_b)).get(0)).intValue(), (
        (Integer)((List<Integer>)ServerManager.getFreeze().get((ctx.getServerHandler()).field_147369_b)).get(1)).intValue(), (
        (Integer)((List<Integer>)ServerManager.getFreeze().get((ctx.getServerHandler()).field_147369_b)).get(2)).intValue());
    ServerManager.removeActiveSpell(Spells.INERTIUM.getSpell().getId(), uuid);
    ServerManager.removeFreeze((ctx.getServerHandler()).field_147369_b);
    EventUtils.spawnParticle((ctx.getServerHandler()).field_147369_b.field_70170_p, "smoke", 
        (int)(ctx.getServerHandler()).field_147369_b.field_70165_t, 
        (int)(ctx.getServerHandler()).field_147369_b.field_70163_u, 
        (int)(ctx.getServerHandler()).field_147369_b.field_70161_v, 100, 0.10000000149011612D);
    PSpellsV2.network.sendToAll((IMessage)new PacketClientActiveSpell(Spells.INERTIUM.getSpell().getId(), uuid, false));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\network\server\PacketServerCancelInertium$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */