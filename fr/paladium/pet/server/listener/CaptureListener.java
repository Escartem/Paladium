package fr.paladium.pet.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palajobs.api.PalaJobsAPI;
import fr.paladium.palajobs.api.player.IJobsPlayer;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.api.type.ObjectiveType;
import fr.paladium.pet.common.event.capture.PetCaptureEvent;
import fr.paladium.pet.server.config.global.GlobalConfig;
import java.util.Optional;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class CaptureListener {
  @SubscribeEvent
  public void onPetCapture(PetCaptureEvent event) {
    double value = GlobalConfig.get().getExperiencePerPetCapture();
    EntityPlayerMP player = (EntityPlayerMP)event.getPlayer();
    Optional<IJobsPlayer> optJobsPlayer = PalaJobsAPI.inst().getJobsPlayer((EntityPlayer)player);
    if (!optJobsPlayer.isPresent())
      return; 
    ((IJobsPlayer)optJobsPlayer.get()).addXp(JobType.HUNTER, ObjectiveType.FISH, value, (EntityPlayer)player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\listener\CaptureListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */