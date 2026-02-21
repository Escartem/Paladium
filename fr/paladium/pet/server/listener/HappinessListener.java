package fr.paladium.pet.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.pet.common.constant.PetTranslateEnum;
import fr.paladium.pet.common.event.happiness.PetEarnHappinessEvent;
import net.minecraft.command.ICommandSender;

public class HappinessListener {
  @SubscribeEvent
  public void onEarnHappiness(PetEarnHappinessEvent event) {
    PetTranslateEnum.MESSAGE_EARN_HAPPINESS.message((ICommandSender)event.getPlayer(), new Object[] { Integer.valueOf(event.getAmount()) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\listener\HappinessListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */