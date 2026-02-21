package fr.paladium.palapass.common.quest.misc;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.server.Server;
import fr.paladium.palapass.common.constants.PalapassTranslateEnum;
import fr.paladium.palapass.common.network.data.PalapassPlayer;
import fr.paladium.palapass.common.pojo.quest.EnumQuestsType;
import fr.paladium.palapass.common.pojo.quest.Quest;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;

public class VisitServerQuest extends Quest {
  private static final EnumQuestsType TYPE = EnumQuestsType.SERVER_VISIT;
  
  public static void trigger(EntityPlayer entityPlayer, String serverName) {
    List<Quest> quests = PalapassPlayer.get(entityPlayer).getNonCompletedQuestsOfType(TYPE);
    String upperCaseServerName = serverName.toUpperCase();
    for (Quest quest : quests) {
      if (upperCaseServerName.startsWith(quest.getRegexServer().toUpperCase())) {
        String visitServerProgressMessage = PalapassTranslateEnum.VISIT_SERVER_PROGRESS.textOrDefault(entityPlayer, "Aller dans un serveur " + quest
            
            .getServerName() + " §c{VALUE}/{QUANTITY}", new Object[] { quest
              .getServerName() });
        quest.questProgress(entityPlayer, 1, visitServerProgressMessage);
      } 
    } 
  }
  
  @SideOnly(Side.SERVER)
  @SubscribeEvent
  public void onJoin(PlayerEvent.PlayerLoggedInEvent e) {
    trigger(e.player, Server.getRawServerType());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\quest\misc\VisitServerQuest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */