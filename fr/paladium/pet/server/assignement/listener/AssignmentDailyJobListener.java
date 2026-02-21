package fr.paladium.pet.server.assignement.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palajobs.api.event.quest.PlayerFinishQuestEvent;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.server.assignement.AssignmentManager;
import fr.paladium.pet.server.config.assignment.fields.AssignmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class AssignmentDailyJobListener {
  private final AssignmentManager manager = AssignmentManager.getInstance();
  
  @SubscribeEvent
  public void onCompleteDailyJob(PlayerFinishQuestEvent event) {
    EntityPlayerMP player = (EntityPlayerMP)event.getPlayer();
    this.manager.performAssignments(player, PetPlayer.get((EntityPlayer)player), null, new AssignmentType[] { AssignmentType.DAILY_JOB });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\assignement\listener\AssignmentDailyJobListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */