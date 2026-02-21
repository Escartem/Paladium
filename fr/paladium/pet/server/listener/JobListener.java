package fr.paladium.pet.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palajobs.api.event.OnPlayerLevelUp;
import fr.paladium.pet.common.event.experience.PetExperienceSource;
import fr.paladium.pet.common.network.data.PetPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class JobListener {
  @SubscribeEvent
  public void onLevelUp(OnPlayerLevelUp event) {
    EntityPlayerMP player = (EntityPlayerMP)event.player;
    PetPlayer pet = PetPlayer.get((EntityPlayer)player);
    if (pet == null || !pet.has())
      return; 
    PetExperienceSource source = PetExperienceSource.getByJobName(event.jobName);
    if (source == null)
      return; 
    pet.earnExperience((EntityPlayer)player, source, pet.getExperienceByJobLevelUp(event.levelAfter));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\listener\JobListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */