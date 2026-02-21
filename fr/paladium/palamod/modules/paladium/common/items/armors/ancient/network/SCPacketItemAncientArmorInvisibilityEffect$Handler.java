package fr.paladium.palamod.modules.paladium.common.items.armors.ancient.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.paladium.common.items.LegendaryStone;
import fr.paladium.palamod.modules.paladium.utils.LengendaryStoneFx;
import fr.paladium.palamod.modules.paladium.utils.PacketHandler;

@PacketHandler
public class Handler implements IMessageHandler<SCPacketItemAncientArmorInvisibilityEffect, IMessage> {
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(SCPacketItemAncientArmorInvisibilityEffect message, MessageContext ctx) {
    LengendaryStoneFx.execute(SCPacketItemAncientArmorInvisibilityEffect.access$000(message), SCPacketItemAncientArmorInvisibilityEffect.access$100(message), SCPacketItemAncientArmorInvisibilityEffect.access$200(message), LegendaryStone.Effect.INVISIBILITY.getRed(), LegendaryStone.Effect.INVISIBILITY.getGreen(), LegendaryStone.Effect.INVISIBILITY.getBlue());
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\armors\ancient\network\SCPacketItemAncientArmorInvisibilityEffect$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */