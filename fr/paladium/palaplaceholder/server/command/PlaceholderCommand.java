package fr.paladium.palaplaceholder.server.command;

import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.Command;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.SubCommand;
import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import fr.paladium.palaplaceholder.common.manager.PlaceholderManager;
import net.minecraft.entity.player.EntityPlayer;

@Command(command = {"placeholder"}, permission = "paladium.placeholder.debug", sender = {SenderType.PLAYER})
public class PlaceholderCommand {
  @SubCommand(command = "placeholder <testString>", description = "Permet de tester une chaine de caractère avec le systeme de placeholder")
  public void debugString(CommandContext context, String testString) {
    context.send(PlaceholderManager.inst().setPlaceholders((EntityPlayer)context.getPlayer(), testString));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaplaceholder\server\command\PlaceholderCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */