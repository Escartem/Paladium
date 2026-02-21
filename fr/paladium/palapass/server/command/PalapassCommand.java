package fr.paladium.palapass.server.command;

import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.builder.SubCommandBuilder;
import fr.paladium.palaforgeutils.lib.subcommand.data.CommandData;
import fr.paladium.palapass.common.manager.PalapassManager;
import fr.paladium.palapass.server.command.admin.PalapassDebugSubCommand;
import fr.paladium.palapass.server.command.admin.PalapassGenerateSubCommand;
import fr.paladium.palapass.server.command.admin.PalapassSetPointsSubCommand;
import fr.paladium.palapass.server.command.admin.PalapassSetQuestSubCommand;
import fr.paladium.palapass.server.command.admin.PalapassUnlockPremiumSubCommand;
import fr.paladium.palapass.server.command.player.PalapassGuiSubCommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class PalapassCommand extends ASubCommand {
  public static final String NAME = "palapass";
  
  public static final String DESCRIPTION = "Gérer son palapass";
  
  public PalapassCommand() {
    SubCommandBuilder.create("generate")
      .permission("palapass.admin")
      .string()
      .build(this, (ASubCommand)new PalapassGenerateSubCommand());
    SubCommandBuilder.create("debug")
      .permission("palapass.admin")
      .string()
      .build(this, (ASubCommand)new PalapassDebugSubCommand());
    SubCommandBuilder.create("points")
      .permission("palapass.admin")
      .string()
      .build(this, (ASubCommand)new PalapassSetPointsSubCommand());
    SubCommandBuilder.create("quest")
      .permission("palapass.admin")
      .string()
      .build(this, (ASubCommand)new PalapassSetQuestSubCommand());
    SubCommandBuilder.create("unlock")
      .permission("palapass.admin")
      .string()
      .build(this, (ASubCommand)new PalapassUnlockPremiumSubCommand());
    SubCommandBuilder.create("gui")
      .permission("palapass.player")
      .executable()
      .string()
      .build(this, (ASubCommand)new PalapassGuiSubCommand());
  }
  
  public boolean performCurrentNode(ICommandSender sender, CommandData data) {
    if (!(sender instanceof EntityPlayerMP)) {
      sender.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cVous devez être connecté pour effectuer cette commande."));
      return false;
    } 
    PalapassManager.openPalapass((EntityPlayerMP)sender);
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\server\command\PalapassCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */