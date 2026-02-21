package fr.paladium.palamod.modules.paladium.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.utils.TimeUtil;
import fr.paladium.palamod.modules.paladium.common.items.LegendaryStone;
import fr.paladium.palamod.modules.paladium.utils.LengendaryStoneFx;
import net.minecraft.client.Minecraft;

public class Handler implements IMessageHandler<PacketLegendaryEffect, IMessage> {
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(PacketLegendaryEffect message, MessageContext ctx) {
    if (Side.CLIENT.equals(ctx.side)) {
      LengendaryStoneFx.execute(PacketLegendaryEffect.access$000(message), PacketLegendaryEffect.access$100(message), PacketLegendaryEffect.access$200(message));
      if (PacketLegendaryEffect.access$300(message) != null)
        if (PacketLegendaryEffect.access$300(message) == LegendaryStone.Effect.POWER) {
          (Minecraft.func_71410_x()).field_71439_g.getEntityData().func_74772_a("legendary_power", TimeUtil.now());
        } else if (PacketLegendaryEffect.access$300(message) == LegendaryStone.Effect.CHAOS) {
          (Minecraft.func_71410_x()).field_71439_g.getEntityData().func_74772_a("legendary_chaos", System.currentTimeMillis());
        }  
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\network\PacketLegendaryEffect$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */