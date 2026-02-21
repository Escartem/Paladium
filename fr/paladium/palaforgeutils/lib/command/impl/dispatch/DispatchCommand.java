package fr.paladium.palaforgeutils.lib.command.impl.dispatch;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.Command;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.CommandParameter;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.CommandParameterTabComplete;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.SubCommand;
import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import fr.paladium.palaforgeutils.lib.command.impl.dispatch.network.RBPacketDispatchCommand;
import fr.paladium.palaforgeutils.lib.server.ServerType;
import java.util.Collection;

@Command(command = {"dispatch"}, description = "Dispatch command on all servers", permission = "paladium.command.dispatch")
public class DispatchCommand {
  @SubCommand(command = "dispatch <server_type> <command>", description = "Dispatch a command on a specific server")
  public void dispatch(CommandContext context, @CommandParameter(tabComplete = @CommandParameterTabComplete(method = "completeServerTypes")) String serverType, String command) {
    (new RBPacketDispatchCommand(serverType, command)).broadcast();
    context.success("Commande envoyée sur les serveurs §e" + serverType.toUpperCase());
  }
  
  @SideOnly(Side.SERVER)
  private String[] completeServerTypes(CommandContext context) {
    Collection<String> serverTypes = ServerType.getServerTypes().keySet();
    serverTypes.add("ALL");
    return serverTypes.<String>toArray(new String[0]);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\command\impl\dispatch\DispatchCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */