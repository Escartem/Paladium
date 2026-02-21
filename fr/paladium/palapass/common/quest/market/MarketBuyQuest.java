package fr.paladium.palapass.common.quest.market;

import fr.paladium.palapass.common.constants.PalapassTranslateEnum;
import fr.paladium.palapass.common.network.data.PalapassPlayer;
import fr.paladium.palapass.common.pojo.quest.EnumQuestsType;
import fr.paladium.palapass.common.pojo.quest.Quest;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;

public class MarketBuyQuest extends Quest {
  private static final EnumQuestsType TYPE = EnumQuestsType.MARKET_BUY;
  
  public static void trigger(EntityPlayer entityPlayer, int quantity) {
    List<Quest> quests = PalapassPlayer.get(entityPlayer).getNonCompletedQuestsOfType(TYPE);
    for (Quest quest : quests) {
      String buyMarketProgressMessage = PalapassTranslateEnum.BUY_ITEMS_MARKET_PROGRESS.textOrDefault(entityPlayer, "Acheter des items au market §c{VALUE}/{QUANTITY}");
      quest.questProgress(entityPlayer, quantity, buyMarketProgressMessage);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\quest\market\MarketBuyQuest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */