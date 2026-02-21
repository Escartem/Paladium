package fr.paladium.palapass.common.quest.misc;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palapass.common.constants.PalapassTranslateEnum;
import fr.paladium.palapass.common.network.data.PalapassPlayer;
import fr.paladium.palapass.common.pojo.quest.EnumQuestsType;
import fr.paladium.palapass.common.pojo.quest.Quest;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class EnterWorldQuest extends Quest {
  private static final EnumQuestsType TYPE = EnumQuestsType.ENTER_WORLD;
  
  public static void trigger(EntityPlayer entityPlayer, String world) {
    List<Quest> quests = PalapassPlayer.get(entityPlayer).getNonCompletedQuestsOfType(TYPE);
    for (Quest quest : quests) {
      if (quest.getWorld().contains(world)) {
        String visitWorldProgressMessage = PalapassTranslateEnum.VISIT_WORLD_PROGRESS.textOrDefault(entityPlayer, "Aller dans le monde " + quest
            
            .getWorldName() + " §c{VALUE}/{QUANTITY}", new Object[] { quest
              .getWorldName() });
        quest.questProgress(entityPlayer, 1, visitWorldProgressMessage);
      } 
    } 
  }
  
  @SideOnly(Side.SERVER)
  @SubscribeEvent
  public void onJoin(EntityJoinWorldEvent e) {
    if (!(e.entity instanceof EntityPlayer))
      return; 
    EntityPlayer player = (EntityPlayer)e.entity;
    trigger(player, e.world.field_73011_w.func_80007_l());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\quest\misc\EnterWorldQuest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */