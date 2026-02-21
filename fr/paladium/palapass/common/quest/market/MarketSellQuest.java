package fr.paladium.palapass.common.quest.market;

import fr.paladium.palapass.common.constants.PalapassTranslateEnum;
import fr.paladium.palapass.common.network.data.PalapassPlayer;
import fr.paladium.palapass.common.pojo.quest.EnumQuestsType;
import fr.paladium.palapass.common.pojo.quest.Quest;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;

public class MarketSellQuest extends Quest {
  private static final EnumQuestsType TYPE = EnumQuestsType.MARKET_SELL;
  
  public static void trigger(EntityPlayer entityPlayer, int quantity) {
    List<Quest> quests = PalapassPlayer.get(entityPlayer).getNonCompletedQuestsOfType(TYPE);
    for (Quest quest : quests) {
      String sellMarketProgressMessage = PalapassTranslateEnum.SELL_ITEMS_MARKET_PROGRESS.textOrDefault(entityPlayer, "Vendre des items au market §c{VALUE}/{QUANTITY}");
      quest.questProgress(entityPlayer, quantity, sellMarketProgressMessage);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\quest\market\MarketSellQuest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */