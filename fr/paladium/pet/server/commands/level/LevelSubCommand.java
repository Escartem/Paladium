package fr.paladium.pet.server.commands.level;

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

public class LevelSubCommand extends ASubCommand {
  public static final String NAME = "level";
  
  public static final String PERMISSION = "paladium.pet.level";
  
  public LevelSubCommand() {
    ABaseSubCommand aBaseSubCommand1 = PlayerSubCommand.create("(player)").build(this);
    ABaseSubCommand aBaseSubCommand2 = StringSubCommand.create("set").build((ASubCommand)aBaseSubCommand1);
    ABaseSubCommand aBaseSubCommand3 = NumberSubCommand.create("(value)", "définir le level du familier", Integer.valueOf(1), Integer.valueOf(100)).build((ASubCommand)aBaseSubCommand2, set());
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
        pet.setLevel((EntityPlayer)target, value);
        PetTranslateEnum.MESSAGE_LEVEL_SET.message(sender, new Object[] { target.func_70005_c_(), Integer.valueOf(value) });
        PetTranslateEnum.MESSAGE_LEVEL_SET_TARGET.message((ICommandSender)target, new Object[] { sender.func_70005_c_(), Integer.valueOf(value) });
        return true;
      };
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\commands\level\LevelSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */