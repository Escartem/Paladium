package fr.paladium.palapass.common.event;

import cpw.mods.fml.common.eventhandler.Event;
import fr.paladium.palapass.common.pojo.quest.Quest;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerFinishPassQuestEvent extends Event {
  private final EntityPlayer player;
  
  private final Quest quest;
  
  public PlayerFinishPassQuestEvent(EntityPlayer player, Quest quest) {
    this.player = player;
    this.quest = quest;
  }
  
  public EntityPlayer getPlayer() {
    return this.player;
  }
  
  public Quest getQuest() {
    return this.quest;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\common\event\PlayerFinishPassQuestEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */