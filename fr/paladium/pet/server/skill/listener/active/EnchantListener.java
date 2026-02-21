package fr.paladium.pet.server.skill.listener.active;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.pet.common.constant.PetTranslateEnum;
import fr.paladium.pet.common.event.PlayerEnchantItemEvent;
import fr.paladium.pet.server.skill.handler.impl.active.EnchantedSkill;
import java.util.UUID;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

public class EnchantListener {
  @SubscribeEvent
  public void onEnchant(PlayerEnchantItemEvent event) {
    int cost = event.getCost();
    EntityPlayerMP player = (EntityPlayerMP)event.getPlayer();
    UUID uniqueId = player.func_110124_au();
    if (!EnchantedSkill.VALUES.containsKey(uniqueId))
      return; 
    int value = ((Integer)EnchantedSkill.VALUES.get(uniqueId)).intValue();
    if (cost < value)
      return; 
    EnchantedSkill.VALUES.remove(uniqueId);
    PetTranslateEnum.MESSAGE_ENCHANTED_SUCCESS.message((ICommandSender)player, new Object[] { Integer.valueOf(value) });
    player.func_82242_a(value);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\skill\listener\active\EnchantListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */