package fr.paladium.pet.server.commands.open;

import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.data.CommandData;
import fr.paladium.pet.server.commands.PetSubCommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

public class OpenSubCommand extends ASubCommand {
  public static final String NAME = "open";
  
  public static final String DESCRIPTION = "Ouvrir l'interface";
  
  protected boolean performCurrentNode(ICommandSender sender, CommandData data) {
    return PetSubCommand.openHomeUI((EntityPlayerMP)sender);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\commands\open\OpenSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */