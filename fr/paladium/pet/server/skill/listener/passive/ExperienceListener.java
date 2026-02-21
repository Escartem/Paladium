package fr.paladium.pet.server.skill.listener.passive;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.server.skill.handler.PassiveResponse;
import fr.paladium.pet.server.skill.handler.PassiveSkillEnum;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;

public class ExperienceListener {
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void onEarn(PlayerPickupXpEvent event) {
    EntityPlayerMP player = (EntityPlayerMP)event.entityPlayer;
    PetPlayer pet = PetPlayer.get((EntityPlayer)player);
    PassiveResponse response = PassiveSkillEnum.EXPERIENCE_ABSORBER.getResponse(pet);
    double value = response.getPersonalValue(pet);
    if (!response.has(value))
      return; 
    double random = Math.random() * 100.0D;
    if (random > value)
      return; 
    event.orb.field_70530_e *= 2;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\skill\listener\passive\ExperienceListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */