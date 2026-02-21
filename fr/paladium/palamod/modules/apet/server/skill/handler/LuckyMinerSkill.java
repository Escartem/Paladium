package fr.paladium.palamod.modules.apet.server.skill.handler;

import fr.paladium.pet.common.constant.PetTranslateEnum;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.server.skill.handler.ASkillHandler;
import java.util.HashMap;
import java.util.UUID;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

public class LuckyMinerSkill extends ASkillHandler {
  public static final String ID = "lucky_miner";
  
  public static final HashMap<UUID, Double> LUCKY_MINERS = new HashMap<>();
  
  public LuckyMinerSkill() {
    super("lucky_miner");
  }
  
  public boolean perform(EntityPlayerMP player, PetPlayer pet) {
    double value = getSkill().getPersonalValue(pet);
    if (value <= 1.0D)
      return false; 
    LUCKY_MINERS.put(player.func_110124_au(), Double.valueOf(value));
    PetTranslateEnum.MESSAGE_LUCKY_MINER_ACTIVATED.message((ICommandSender)player);
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\apet\server\skill\handler\LuckyMinerSkill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */