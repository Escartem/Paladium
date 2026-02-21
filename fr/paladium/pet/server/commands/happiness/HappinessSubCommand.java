package fr.paladium.pet.server.commands.happiness;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.ABaseSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.ISubCallback;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.NumberSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.PlayerSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.StringSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.data.CommandData;
import fr.paladium.pet.common.PetCommonProxy;
import fr.paladium.pet.common.constant.PetTranslateEnum;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.network.packet.pet.BBUpdateClientSkillValuesPacket;
import java.util.Optional;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class HappinessSubCommand extends ASubCommand {
  public static final String NAME = "happiness";
  
  public static final String PERMISSION = "paladium.pet.happiness";
  
  public HappinessSubCommand() {
    ABaseSubCommand aBaseSubCommand1 = PlayerSubCommand.create("(player)").build(this);
    ABaseSubCommand aBaseSubCommand2 = StringSubCommand.create("set").build((ASubCommand)aBaseSubCommand1);
    ABaseSubCommand aBaseSubCommand3 = NumberSubCommand.create("(value)", "définir le bonheur du familier").build((ASubCommand)aBaseSubCommand2, set());
  }
  
  private ISubCallback set() {
    return (sender, data) -> {
        Optional<EntityPlayerMP> result = data.getTargetedPlayer();
        if (!result.isPresent())
          return false; 
        EntityPlayerMP target = result.get();
        PetPlayer pet = PetPlayer.get((EntityPlayer)target);
        int value = data.getInteger();
        if (pet == null || !pet.has()) {
          PetTranslateEnum.MESSAGE_TARGET_NO_PET.message(sender, new Object[] { target.func_70005_c_() });
          return true;
        } 
        int happiness = pet.setHappiness((EntityPlayer)target, value);
        PetTranslateEnum.MESSAGE_HAPPINESS_SET.message(sender, new Object[] { target.func_70005_c_(), Integer.valueOf(happiness) });
        PetTranslateEnum.MESSAGE_HAPPINESS_SET_TARGET.message((ICommandSender)target, new Object[] { sender.func_70005_c_(), Integer.valueOf(happiness) });
        BBUpdateClientSkillValuesPacket packet = new BBUpdateClientSkillValuesPacket(pet);
        PetCommonProxy.getInstance().getNetwork().sendTo((IMessage)packet, target);
        return true;
      };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\commands\happiness\HappinessSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */