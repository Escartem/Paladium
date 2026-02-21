package fr.paladium.pet.server.skill.handler.impl.active;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palaforgeutils.lib.task.DurationConverter;
import fr.paladium.pet.common.PetCommonProxy;
import fr.paladium.pet.common.constant.PetTranslateEnum;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.network.packet.skill.breakspeed.SCObsidianBreakSpeedPacket;
import fr.paladium.pet.server.skill.handler.ASkillHandler;
import fr.paladium.pet.server.skill.listener.active.BreakSpeedListener;
import java.util.concurrent.TimeUnit;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

public class UnbreakablePickaxeSkill extends ASkillHandler {
  public static final String ID = "unbreakable_pickaxe";
  
  public static final long DURATION = TimeUnit.MINUTES.toMillis(1L);
  
  public UnbreakablePickaxeSkill() {
    super("unbreakable_pickaxe");
  }
  
  public boolean perform(EntityPlayerMP player, PetPlayer pet) {
    double value = getSkill().getPersonalValue(pet);
    if (value <= 1.0D)
      return false; 
    BreakSpeedListener.INSTANCE.put(player, value);
    PetTranslateEnum.MESSAGE_UNBREAKABLE_PICKAXE.message((ICommandSender)player, new Object[] { String.format("%.2f", new Object[] { Double.valueOf(value) }), DurationConverter.fromMillisToString(DURATION) });
    PetCommonProxy.getInstance().getNetwork().sendTo((IMessage)new SCObsidianBreakSpeedPacket(value, DURATION), player);
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\skill\handler\impl\active\UnbreakablePickaxeSkill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */