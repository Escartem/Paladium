package fr.paladium.pet.server.commands.bypass;

import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.ABaseSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.PlayerSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.data.CommandData;
import fr.paladium.pet.common.constant.PetTranslateEnum;
import fr.paladium.pet.server.skill.SkillManager;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

public class BypassSubCommand extends ASubCommand {
  public static final String NAME = "bypass";
  
  public static final String PERMISSION = "paladium.pet.bypass";
  
  public static final String DESCRIPTION = "Bypass un joueur";
  
  public static BypassSubCommand INSTANCE;
  
  public BypassSubCommand() {
    INSTANCE = this;
    ABaseSubCommand aBaseSubCommand = PlayerSubCommand.create("(player)", "Bypass un joueur").build(this, (sender, data) -> {
          Optional<EntityPlayerMP> result = data.getTargetedPlayer();
          if (!result.isPresent())
            return false; 
          HashSet<UUID> bypassSet = SkillManager.getInstance().getBypassSet();
          EntityPlayerMP target = result.get();
          UUID uniqueId = target.func_110124_au();
          if (bypassSet.contains(uniqueId)) {
            bypassSet.remove(uniqueId);
            PetTranslateEnum.MESSAGE_BYPASS_REMOVE.message(sender, new Object[] { target.func_70005_c_() });
          } else {
            bypassSet.add(uniqueId);
            PetTranslateEnum.MESSAGE_BYPASS_ADD.message(sender, new Object[] { target.func_70005_c_() });
          } 
          return true;
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\commands\bypass\BypassSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */