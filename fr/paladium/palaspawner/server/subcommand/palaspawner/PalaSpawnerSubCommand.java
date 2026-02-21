package fr.paladium.palaspawner.server.subcommand.palaspawner;

import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.SubCommandInfo;
import fr.paladium.palaforgeutils.lib.subcommand.builder.SubCommandType;
import fr.paladium.palaspawner.server.subcommand.palaspawner.sub.DebugSubCommand;
import fr.paladium.palaspawner.server.subcommand.palaspawner.sub.GiveAllSubCommand;
import fr.paladium.palaspawner.server.subcommand.palaspawner.sub.GiveSubCommand;

@SubCommandInfo(name = "palaspawner", permission = "palaspawner.command.spawner", type = SubCommandType.STRING, executable = true)
public class PalaSpawnerSubCommand extends ASubCommand {
  protected void init() {
    addNodes(new Class[] { GiveSubCommand.class, GiveAllSubCommand.class, DebugSubCommand.class });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\server\subcommand\palaspawner\PalaSpawnerSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */