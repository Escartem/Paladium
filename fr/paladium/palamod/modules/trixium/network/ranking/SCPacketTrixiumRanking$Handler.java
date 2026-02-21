package fr.paladium.palamod.modules.trixium.network.ranking;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.trixium.gui.UITrixiumRanking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class Handler implements IMessageHandler<SCPacketTrixiumRanking, IMessage> {
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(SCPacketTrixiumRanking message, MessageContext ctx) {
    Minecraft.func_71410_x().func_147108_a((GuiScreen)new UITrixiumRanking(SCPacketTrixiumRanking.access$000(message), SCPacketTrixiumRanking.access$100(message), SCPacketTrixiumRanking.access$200(message), SCPacketTrixiumRanking.access$300(message)));
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\network\ranking\SCPacketTrixiumRanking$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */