package fr.paladium.pet.server.commands.assignment;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palaforgeutils.lib.chat.ChatUtils;
import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.ABaseSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.ISubCallback;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.FreeSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.PlayerSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.impl.StringSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.data.CommandData;
import fr.paladium.palaforgeutils.lib.subcommand.utils.SubCommandUtils;
import fr.paladium.pet.client.ui.utils.data.AssignmentClientData;
import fr.paladium.pet.common.PetCommonProxy;
import fr.paladium.pet.common.constant.PetTranslateEnum;
import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.network.data.additional.assignment.AssignmentData;
import fr.paladium.pet.common.network.packet.pet.SCOpenDebugUIPacket;
import fr.paladium.pet.common.utils.PetUtils;
import fr.paladium.pet.server.config.assignment.Assignment;
import fr.paladium.pet.server.config.assignment.AssignmentConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class AssignmentSubCommand extends ASubCommand {
  public static final String NAME = "assignment";
  
  public static final String PERMISSION = "palapet.command.assignment";
  
  public static final String NAME_ARG_NAME = "(name)";
  
  private static AssignmentSubCommand instance;
  
  public static AssignmentSubCommand getInstance() {
    return instance;
  }
  
  private String[] assignmentNames = new String[0];
  
  public AssignmentSubCommand() {
    instance = this;
    StringSubCommand.create("list", "Liste des missions")
      .build(this, list());
    ABaseSubCommand aBaseSubCommand1 = StringSubCommand.create("info").build(this);
    FreeSubCommand.create("(name)", "informations d'une mission", this.assignmentNames)
      .build((ASubCommand)aBaseSubCommand1, name());
    ABaseSubCommand aBaseSubCommand2 = StringSubCommand.create("reset").build(this);
    PlayerSubCommand.create("(player)", "reset les missions d'un joueur")
      .build((ASubCommand)aBaseSubCommand2, reset());
    StringSubCommand.create("debug", "Ouvre l'interface de debug")
      .build(this, debug());
  }
  
  private ISubCallback debug() {
    return (sender, data) -> {
        EntityPlayerMP player = (EntityPlayerMP)sender;
        List<AssignmentClientData> assignments = new ArrayList<>();
        AssignmentConfig config = AssignmentConfig.get();
        for (Assignment assignment : config.getAssignments())
          assignments.add(AssignmentClientData.from((EntityPlayer)player, assignment, new AssignmentData(assignment.getId()))); 
        PetCommonProxy.getInstance().getNetwork().sendTo((IMessage)new SCOpenDebugUIPacket(assignments), player);
        return true;
      };
  }
  
  private ISubCallback reset() {
    return (sender, data) -> {
        Optional<EntityPlayerMP> result = data.getTargetedPlayer();
        if (!result.isPresent())
          return false; 
        EntityPlayerMP player = result.get();
        PetPlayer pet = PetPlayer.get((EntityPlayer)player);
        if (pet == null)
          return false; 
        pet.pickupAssignments(player, System.currentTimeMillis());
        PetUtils.sendPrefixedMessage(sender, new String[] { "§aLes missions de §e" + player.func_70005_c_() + "§a ont été reset." });
        PetUtils.sendPrefixedMessage((ICommandSender)player, new String[] { "§aVos missions ont été reset." });
        return true;
      };
  }
  
  private ISubCallback name() {
    return (sender, data) -> {
        AssignmentConfig config = AssignmentConfig.get();
        String id = data.getFreeArg();
        Optional<Assignment> result = config.findAssignmentById(id);
        if (!result.isPresent()) {
          PetTranslateEnum.MESSAGE_MISSION_NOT_FOUND.message(sender, new Object[] { id });
          return true;
        } 
        Assignment assignment = result.get();
        ChatUtils.sendColoredMessage(sender, new String[] { "§8§m---------------------------------" });
        ChatUtils.sendColoredMessage(sender, new String[] { "§6Informations de la mission §d" + assignment.getId() });
        ChatUtils.sendColoredMessage(sender, new String[] { "§e- §dType: §e" + assignment.getType().name() });
        ChatUtils.sendColoredMessage(sender, new String[] { "§e- §dNiveau: §e" + assignment.getLevelRange().getMin() + " - " + assignment.getLevelRange().getMax() });
        ChatUtils.sendColoredMessage(sender, new String[] { "§e- §dDescription: §e" + assignment.getDescription(null) });
        ChatUtils.sendColoredMessage(sender, new String[] { "§e- §dWeight: §e" + assignment.getWeight() });
        ChatUtils.sendColoredMessage(sender, new String[] { "§e- §dPoints: §e" + assignment.getGivenPoints() });
        ChatUtils.sendColoredMessage(sender, new String[] { "§e- §dExperience: §e" + assignment.getGivenExp() });
        ChatUtils.sendColoredMessage(sender, new String[] { "§e- §dAmount (objectif): §e" + assignment.getAmount() });
        ChatUtils.sendColoredMessage(sender, new String[] { "§8§m---------------------------------" });
        return true;
      };
  }
  
  private ISubCallback list() {
    return (sender, data) -> {
        ChatUtils.sendColoredMessage(sender, new String[] { "§8§m---------------------------------" });
        ChatUtils.sendColoredMessage(sender, new String[] { "§6Liste des missions" });
        for (String assignmentName : this.assignmentNames) {
          ChatUtils.sendColoredMessage(sender, new String[] { "§e- §d" + assignmentName });
        } 
        ChatUtils.sendColoredMessage(sender, new String[] { "§8§m---------------------------------" });
        return true;
      };
  }
  
  public void updateAssignments(String[] assignments) {
    this.assignmentNames = assignments;
    for (ASubCommand sub : SubCommandUtils.extractSubCommands(this)) {
      if (sub.func_71517_b().equalsIgnoreCase("(name)")) {
        sub.getBuilder().freeArgument(assignments);
        break;
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\commands\assignment\AssignmentSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */