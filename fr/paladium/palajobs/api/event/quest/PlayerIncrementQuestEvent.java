package fr.paladium.palajobs.api.event.quest;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palajobs.api.quest.IQuestPlayerData;
import fr.paladium.palajobs.core.quest.AbstractQuest;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerIncrementQuestEvent extends Event {
  private final EntityPlayer player;
  
  private final int amount;
  
  private final AbstractQuest quest;
  
  private final IQuestPlayerData questData;
  
  public EntityPlayer getPlayer() {
    return this.player;
  }
  
  public int getAmount() {
    return this.amount;
  }
  
  public AbstractQuest getQuest() {
    return this.quest;
  }
  
  public IQuestPlayerData getQuestData() {
    return this.questData;
  }
  
  public PlayerIncrementQuestEvent(EntityPlayer player, int amount, AbstractQuest quest, IQuestPlayerData questData) {
    this.player = player;
    this.amount = amount;
    this.quest = quest;
    this.questData = questData;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\api\event\quest\PlayerIncrementQuestEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */