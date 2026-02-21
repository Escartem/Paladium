package fr.paladium.palamod.modules.spellsv2.network.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.spellsv2.entity.EntityGhost;
import fr.paladium.palamod.modules.spellsv2.utils.ServerManager;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;

public class Handler implements IMessageHandler<PacketServerMoveEntityGhost, IMessage> {
  public IMessage onMessage(PacketServerMoveEntityGhost message, MessageContext ctx) {
    EntityGhost ghost = (EntityGhost)ServerManager.getMentalis().get((ctx.getServerHandler()).field_147369_b.func_110124_au());
    if (ghost != null) {
      float f = (float)((ctx.getServerHandler()).field_147369_b.field_70165_t - ghost.field_70165_t);
      float f2 = (float)((ctx.getServerHandler()).field_147369_b.field_70161_v - ghost.field_70161_v);
      float dist = MathHelper.func_76129_c(f * f + f2 * f2);
      if (dist < 15.0F) {
        ghost.func_70612_e((float)(message.z * 50.0D), (float)(message.x * 50.0D));
        ghost.func_70091_d(0.0D, message.y * 2.0D, 0.0D);
      } else {
        double offsetX = ghost.field_70165_t - (ctx.getServerHandler()).field_147369_b.field_70165_t;
        double offsetZ = ghost.field_70161_v - (ctx.getServerHandler()).field_147369_b.field_70161_v;
        ghost.func_70634_a(ghost.field_70165_t + ((offsetX > 0.0D) ? -2 : true), ghost.field_70163_u, ghost.field_70161_v + ((offsetZ > 0.0D) ? -2 : true));
        (ctx.getServerHandler()).field_147369_b
          .func_145747_a((IChatComponent)new ChatComponentText("§cNe vous éloignez pas trop."));
      } 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\network\server\PacketServerMoveEntityGhost$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */