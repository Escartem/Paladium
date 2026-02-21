package fr.paladium.pet.server.commands.debug;

import fr.paladium.palaforgeutils.lib.chat.ChatUtils;
import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.PlayerSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.data.CommandData;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.network.data.additional.assignment.AssignmentData;
import fr.paladium.pet.common.network.data.additional.skill.SkillData;
import fr.paladium.pet.common.store.provider.PetSkinShopProvider;
import java.util.Optional;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class DebugSubCommand extends ASubCommand {
  public static final String NAME = "debug";
  
  public static final String DESCRIPTION = "Debug un joueur";
  
  public static final String PERMISSION = "palapet.command.debug";
  
  public DebugSubCommand() {
    PlayerSubCommand.create("(player)", "Debug un joueur")
      .build(this, (sender, data) -> {
          Optional<EntityPlayerMP> result = data.getTargetedPlayer();
          if (!result.isPresent())
            return false; 
          EntityPlayerMP player = result.get();
          PetPlayer pet = PetPlayer.get((EntityPlayer)player);
          ChatUtils.sendColoredMessage(sender, new String[] { "§8§m---------------------------------" });
          ChatUtils.sendColoredMessage(sender, new String[] { "§eDebug de §6" + player.getDisplayName() + "§e:" });
          ChatUtils.sendColoredMessage(sender, new String[] { "§e- Pet: §6" + pet.has() });
          ChatUtils.sendColoredMessage(sender, new String[] { "§e- Level: §6" + pet.getLevel() });
          ChatUtils.sendColoredMessage(sender, new String[] { "§e- Experience: §6" + pet.getExperience() });
          ChatUtils.sendColoredMessage(sender, new String[] { "§e- Happiness: §6" + pet.getHappiness() + "§e/§6" + pet.getMaxHappiness() });
          ChatUtils.sendColoredMessage(sender, new String[] { "§e- Slots: §6" + pet.getSlots(pet.getLevel()) });
          ChatUtils.sendColoredMessage(sender, new String[] { "§e- Assignments: §6" });
          pet.getAssignmentData().getAssignments().forEach(());
          ChatUtils.sendColoredMessage(sender, new String[] { "§e- Skills: §6" });
          pet.getSkillData().getSkills().forEach(());
          ChatUtils.sendColoredMessage(sender, new String[] { "§e- Skins: §6" });
          PetSkinShopProvider.getInstance().getSkins((EntityPlayer)player, pet).forEach(());
          ChatUtils.sendColoredMessage(sender, new String[] { "§8§m---------------------------------" });
          return true;
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\commands\debug\DebugSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */