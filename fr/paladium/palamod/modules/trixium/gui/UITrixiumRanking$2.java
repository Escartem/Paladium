package fr.paladium.palamod.modules.trixium.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.nodes.buttons.utils.DynamicScrollableArea;
import fr.paladium.lib.apollon.nodes.buttons.utils.ScrollArea;
import fr.paladium.palamod.modules.trixium.PTrixium;
import fr.paladium.palamod.modules.trixium.network.ranking.page.CSPacketTrixiumRankingFactionPage;

class null extends DynamicScrollableArea {
  null(double x0, double x1, double x2, double x3, ScrollArea x4, int x5, int x6) {
    super(x0, x1, x2, x3, x4, x5, x6);
  }
  
  public void update(int index) {
    PTrixium.getNetwork().sendToServer((IMessage)new CSPacketTrixiumRankingFactionPage((int)Math.ceil((index / 20.0F))));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\trixium\gui\UITrixiumRanking$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */