package fr.paladium.pet.server.skill.handler.impl.active;

import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.server.skill.handler.ASkillHandler;
import net.minecraft.entity.player.EntityPlayerMP;

public class PocketHappinessSkill extends ASkillHandler {
  public static final String ID = "pocket_happiness";
  
  public PocketHappinessSkill() {
    super("pocket_happiness");
  }
  
  public boolean perform(EntityPlayerMP player, PetPlayer pet) {
    double value = getSkill().getPersonalValue(pet);
    if (value <= 0.0D)
      return false; 
    pet.earnHappiness(player, (int)value);
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\skill\handler\impl\active\PocketHappinessSkill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */