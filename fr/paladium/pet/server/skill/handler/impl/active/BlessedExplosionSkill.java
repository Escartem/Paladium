package fr.paladium.pet.server.skill.handler.impl.active;

import fr.paladium.pet.common.constant.PetTranslateEnum;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.server.skill.handler.ASkillHandler;
import java.util.HashMap;
import java.util.UUID;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

public class BlessedExplosionSkill extends ASkillHandler {
  public static final String ID = "blessed_explosion";
  
  public static final int RADIUS = 5;
  
  public static final HashMap<UUID, Double> VALUES = new HashMap<>();
  
  public BlessedExplosionSkill() {
    super("blessed_explosion");
  }
  
  public boolean perform(EntityPlayerMP player, PetPlayer pet) {
    double value = getSkill().getPersonalValue(pet);
    if (value <= 0.0D)
      return false; 
    VALUES.put(player.func_110124_au(), Double.valueOf(value));
    PetTranslateEnum.MESSAGE_BLESSED_EXPLOSION.message((ICommandSender)player, new Object[] { Integer.valueOf((int)value) });
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\skill\handler\impl\active\BlessedExplosionSkill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */