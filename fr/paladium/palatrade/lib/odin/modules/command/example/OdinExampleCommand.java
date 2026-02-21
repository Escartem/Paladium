package fr.paladium.palatrade.lib.odin.modules.command.example;

import fr.paladium.palatrade.lib.odin.modules.command.lib.Command;
import net.minecraft.command.ICommandSender;

public class OdinExampleCommand extends Command {
  public boolean perform(ICommandSender sender, String[] args) {
    return false;
  }
  
  public String getCommandUsage(ICommandSender sender, String[] args) {
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palatrade\lib\odin\modules\command\example\OdinExampleCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */