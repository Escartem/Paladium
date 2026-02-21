package fr.paladium.palapass.common.quest.palamod;

import fr.paladium.palapass.common.constants.PalapassTranslateEnum;
import fr.paladium.palapass.common.network.data.PalapassPlayer;
import fr.paladium.palapass.common.pojo.quest.EnumQuestsType;
import fr.paladium.palapass.common.pojo.quest.Quest;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;

public class RingRepairQuest extends Quest {
  private static final EnumQuestsType TYPE = EnumQuestsType.RING_REPAIR;
  
  public static void trigger(EntityPlayer entityPlayer, int quantity) {
    List<Quest> quests = PalapassPlayer.get(entityPlayer).getNonCompletedQuestsOfType(TYPE);
    for (Quest quest : quests)
      quest.questProgress(entityPlayer, quantity, PalapassTranslateEnum.REPAIR_WITH_RING_PROGRESS.textOrDefault(entityPlayer, "Réparer de la durabilité avec une ring §c{VALUE}/{QUANTITY}")); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\quest\palamod\RingRepairQuest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */