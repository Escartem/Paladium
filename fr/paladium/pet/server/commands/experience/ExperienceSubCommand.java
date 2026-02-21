package fr.paladium.pet.server.commands.experience;

import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.ABaseSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.ISubCallback;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.NumberSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.PlayerSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.StringSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.data.CommandData;
import fr.paladium.pet.common.constant.PetTranslateEnum;
import fr.paladium.pet.common.network.data.PetPlayer;
import java.util.Optional;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class ExperienceSubCommand extends ASubCommand {
  public static final String NAME = "experience";
  
  public static final String PERMISSION = "paladium.pet.experience";
  
  public ExperienceSubCommand() {
    ABaseSubCommand aBaseSubCommand1 = PlayerSubCommand.create("(player)").build(this);
    ABaseSubCommand aBaseSubCommand2 = StringSubCommand.create("set").build((ASubCommand)aBaseSubCommand1);
    ABaseSubCommand aBaseSubCommand3 = NumberSubCommand.create("(value)", "définir l'EXP du familier").build((ASubCommand)aBaseSubCommand2, set());
    ABaseSubCommand aBaseSubCommand4 = StringSubCommand.create("add").build((ASubCommand)aBaseSubCommand1);
    ABaseSubCommand aBaseSubCommand5 = NumberSubCommand.create("(value)", "ajouter de l'EXP au familier").build((ASubCommand)aBaseSubCommand4, add());
  }
  
  private ISubCallback add() {
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
        pet.addExperience((EntityPlayer)target, value);
        PetTranslateEnum.MESSAGE_EXPERIENCE_ADD.message(sender, new Object[] { target.func_70005_c_(), Integer.valueOf(value) });
        PetTranslateEnum.MESSAGE_EXPERIENCE_ADD_TARGET.message((ICommandSender)target, new Object[] { sender.func_70005_c_(), Integer.valueOf(value) });
        return true;
      };
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
        pet.setExperience((EntityPlayer)target, value);
        PetTranslateEnum.MESSAGE_EXPERIENCE_SET.message(sender, new Object[] { target.func_70005_c_(), Integer.valueOf(value) });
        PetTranslateEnum.MESSAGE_EXPERIENCE_SET_TARGET.message((ICommandSender)target, new Object[] { sender.func_70005_c_(), Integer.valueOf(value) });
        return true;
      };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\commands\experience\ExperienceSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */