package fr.paladium.palamod.modules.paladium.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.paladium.palamod.modules.luckyblock.utils.TimeUtil;
import net.minecraft.client.Minecraft;

public class Handler implements IMessageHandler<PacketStickBaseCooldown, IMessage> {
  public IMessage onMessage(PacketStickBaseCooldown message, MessageContext ctx) {
    (Minecraft.func_71410_x()).field_71439_g.getEntityData().func_74772_a("timer-" + message.type, 
        TimeUtil.nowZoned().plusSeconds(message.cooldown).toEpochSecond());
    for (int i = 0; i < 200; i++)
      (Minecraft.func_71410_x()).field_71439_g.field_70170_p.func_72869_a("reddust", 
          (Minecraft.func_71410_x()).field_71439_g.field_70165_t + 
          (Minecraft.func_71410_x()).field_71439_g.field_70170_p.field_73012_v.nextDouble() * 2.0D - 1.0D, 
          (Minecraft.func_71410_x()).field_71439_g.field_70163_u + 
          (Minecraft.func_71410_x()).field_71439_g.field_70170_p.field_73012_v.nextDouble() * 2.0D - 2.0D, 
          (Minecraft.func_71410_x()).field_71439_g.field_70161_v + 
          (Minecraft.func_71410_x()).field_71439_g.field_70170_p.field_73012_v.nextDouble() * 2.0D - 1.0D, message.color[0], message.color[1], message.color[2]); 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\network\PacketStickBaseCooldown$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */