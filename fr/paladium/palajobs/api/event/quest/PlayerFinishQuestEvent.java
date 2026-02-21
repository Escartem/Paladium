package fr.paladium.palajobs.api.event.quest;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palajobs.api.quest.IQuestPlayerData;
import fr.paladium.palajobs.core.quest.AbstractQuest;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerFinishQuestEvent extends Event {
  private final EntityPlayer player;
  
  private final AbstractQuest quest;
  
  private final IQuestPlayerData questData;
  
  public EntityPlayer getPlayer() {
    return this.player;
  }
  
  public AbstractQuest getQuest() {
    return this.quest;
  }
  
  public IQuestPlayerData getQuestData() {
    return this.questData;
  }
  
  public PlayerFinishQuestEvent(EntityPlayer player, AbstractQuest quest, IQuestPlayerData questData) {
    this.player = player;
    this.quest = quest;
    this.questData = questData;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\api\event\quest\PlayerFinishQuestEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */