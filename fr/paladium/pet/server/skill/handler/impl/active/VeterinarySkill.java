package fr.paladium.pet.server.skill.handler.impl.active;

import fr.paladium.pet.common.constant.PetTranslateEnum;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.server.skill.handler.ASkillHandler;
import java.util.HashSet;
import java.util.UUID;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

public class VeterinarySkill extends ASkillHandler {
  public static final String ID = "veterinary";
  
  public static final HashSet<UUID> VALUES = new HashSet<>();
  
  public VeterinarySkill() {
    super("veterinary");
  }
  
  public boolean perform(EntityPlayerMP player, PetPlayer pet) {
    PetTranslateEnum.MESSAGE_VETERINARY_USED.message((ICommandSender)player);
    VALUES.add(player.func_110124_au());
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\skill\handler\impl\active\VeterinarySkill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */