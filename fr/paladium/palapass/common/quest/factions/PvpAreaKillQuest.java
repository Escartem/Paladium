package fr.paladium.palapass.common.quest.factions;

import fr.paladium.palapass.common.constants.PalapassTranslateEnum;
import fr.paladium.palapass.common.network.data.PalapassPlayer;
import fr.paladium.palapass.common.pojo.quest.EnumQuestsType;
import fr.paladium.palapass.common.pojo.quest.Quest;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;

public class PvpAreaKillQuest extends Quest {
  private static final EnumQuestsType TYPE = EnumQuestsType.PVP_AREA_KILL;
  
  public static void trigger(EntityPlayer entityPlayer) {
    List<Quest> quests = PalapassPlayer.get(entityPlayer).getNonCompletedQuestsOfType(TYPE);
    for (Quest quest : quests) {
      String progressMessage = PalapassTranslateEnum.PVP_AREA_KILL_PROGRESS.textOrDefault(entityPlayer, "Tuer des joueurs dans la pvp area §c{VALUE}/{QUANTITY}");
      quest.questProgress(entityPlayer, 1, progressMessage);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\quest\factions\PvpAreaKillQuest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */