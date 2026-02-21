package fr.paladium.palamod.modules.paladium.common.items.armors.ancient.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.paladium.client.events.ClientItemAncientArmorRenderListener;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;
import net.minecraft.client.Minecraft;

@PacketHandler
public class Handler implements IMessageHandler<SCPacketItemAncientArmorTeleportationEffect, IMessage> {
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(SCPacketItemAncientArmorTeleportationEffect message, MessageContext ctx) {
    if ((Minecraft.func_71410_x()).field_71439_g.field_70170_p.func_73045_a(SCPacketItemAncientArmorTeleportationEffect.access$000(message)) != null)
      ClientItemAncientArmorRenderListener.getTeleportationEffectPlayers().put(Integer.valueOf(SCPacketItemAncientArmorTeleportationEffect.access$000(message)), Long.valueOf(System.currentTimeMillis() + 100L)); 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\armors\ancient\network\SCPacketItemAncientArmorTeleportationEffect$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */