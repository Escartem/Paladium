package fr.paladium.pet.server.skill.handler.impl.active;

import fr.paladium.pet.common.constant.PetTranslateEnum;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.server.skill.handler.ASkillHandler;
import java.util.HashMap;
import java.util.UUID;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

public class EnchantedSkill extends ASkillHandler {
  public static final String ID = "enchanted";
  
  public static final HashMap<UUID, Integer> VALUES = new HashMap<>();
  
  public EnchantedSkill() {
    super("enchanted");
  }
  
  public boolean perform(EntityPlayerMP player, PetPlayer pet) {
    double value = getSkill().getPersonalValue(pet);
    if (value <= 0.0D)
      return false; 
    int intValue = (int)Math.ceil(value);
    VALUES.put(player.func_110124_au(), Integer.valueOf(intValue));
    PetTranslateEnum.MESSAGE_ENCHANTED_ENABLED.message((ICommandSender)player, new Object[] { Integer.valueOf(intValue) });
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\skill\handler\impl\active\EnchantedSkill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */