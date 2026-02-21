package fr.paladium.announcement.server.command;

import fr.paladium.announcement.server.command.subcommand.OpenSubCommand;
import fr.paladium.announcement.server.command.subcommand.RefreshSubCommand;
import fr.paladium.announcement.server.command.subcommand.ResetSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.SubCommandInfo;
import fr.paladium.palaforgeutils.lib.subcommand.builder.SubCommandType;

@SubCommandInfo(name = "announcement", type = SubCommandType.STRING, executable = false)
public class AnnouncementCommand extends ASubCommand {
  protected void init() {
    addNodes(new Class[] { RefreshSubCommand.class, ResetSubCommand.class, OpenSubCommand.class });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\announcement\server\command\AnnouncementCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */