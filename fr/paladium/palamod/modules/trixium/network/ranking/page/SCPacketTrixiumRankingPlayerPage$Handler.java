package fr.paladium.palamod.modules.trixium.network.ranking.page;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.lib.apollon.nodes.buttons.utils.DynamicScrollableArea;
import fr.paladium.lib.apollon.nodes.buttons.utils.ScrollableArea;
import fr.paladium.palamod.modules.trixium.api.TrixiumProfile;
import fr.paladium.palamod.modules.trixium.gui.UITrixiumRanking;
import fr.paladium.palamod.modules.trixium.gui.node.ranking.TrixiumPlayerRankingNode;
import net.minecraft.client.Minecraft;

public class Handler implements IMessageHandler<SCPacketTrixiumRankingPlayerPage, IMessage> {
  @SideOnly(Side.CLIENT)
  public IMessage onMessage(SCPacketTrixiumRankingPlayerPage message, MessageContext ctx) {
    if (!((Minecraft.func_71410_x()).field_71462_r instanceof UITrixiumRanking))
      return null; 
    UITrixiumRanking ui = (UITrixiumRanking)(Minecraft.func_71410_x()).field_71462_r;
    DynamicScrollableArea area = ui.getPlayersArea();
    int i = SCPacketTrixiumRankingPlayerPage.access$000(message) * 20;
    for (TrixiumProfile profile : SCPacketTrixiumRankingPlayerPage.access$100(message)) {
      ui.addNode((new TrixiumPlayerRankingNode(ui.width(2.86F), ui.height(27.59F + 5.0F * i), ui.width(33.9F), ui.height(5.0F), i, profile.playerName, (int)profile.amountTrixium)).setArea((ScrollableArea)area));
      i++;
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\network\ranking\page\SCPacketTrixiumRankingPlayerPage$Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */