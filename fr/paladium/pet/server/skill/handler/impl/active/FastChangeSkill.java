package fr.paladium.pet.server.skill.handler.impl.active;

import fr.paladium.pet.common.constant.PetTranslateEnum;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.network.data.additional.skill.SkillData;
import fr.paladium.pet.common.utils.PetUtils;
import fr.paladium.pet.server.skill.handler.ASkillHandler;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

public class FastChangeSkill extends ASkillHandler {
  public static final String ID = "fast_change";
  
  public FastChangeSkill() {
    super("fast_change");
  }
  
  public void reduceSkillCooldowns(EntityPlayerMP player, PetPlayer pet, double value) {
    double percent = PetUtils.getValueAsPercent(value);
    long now = System.currentTimeMillis();
    pet.getSkillData().getSkills().forEach((slot, data) -> {
          if (data.getSkillId() == null)
            return; 
          long cooldown = data.getChangeCooldown(player, now);
          if (cooldown <= 0L)
            return; 
          long decrease = (long)(cooldown * percent);
          if (decrease <= 0L) {
            data.setLastChangeMillis(0L);
            return;
          } 
          data.setLastChangeMillis(data.getLastChangeMillis() - decrease);
        });
  }
  
  public boolean perform(EntityPlayerMP player, PetPlayer pet) {
    double value = getSkill().getPersonalValue(pet);
    if (value <= 0.0D)
      return false; 
    reduceSkillCooldowns(player, pet, value);
    PetTranslateEnum.MESSAGE_FAST_CHANGE_SUCCESS.message((ICommandSender)player);
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\skill\handler\impl\active\FastChangeSkill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */