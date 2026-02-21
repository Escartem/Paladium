package fr.paladium.pet.server.skill.handler.impl.active;

import fr.paladium.pet.common.constant.PetTranslateEnum;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.server.skill.handler.ASkillHandler;
import java.util.HashMap;
import java.util.UUID;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

public class RepairSkill extends ASkillHandler {
  public static final HashMap<UUID, Integer> VALUES = new HashMap<>();
  
  public static final String ID = "repair";
  
  public RepairSkill() {
    super("repair");
  }
  
  public boolean perform(EntityPlayerMP player, PetPlayer pet) {
    double value = getSkill().getPersonalValue(pet);
    if (value <= 0.0D)
      return false; 
    int intValue = (int)Math.ceil(value);
    VALUES.put(player.func_110124_au(), Integer.valueOf(intValue));
    PetTranslateEnum.MESSAGE_PET_REPAIR.message((ICommandSender)player, new Object[] { Integer.valueOf(intValue) });
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\skill\handler\impl\active\RepairSkill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */