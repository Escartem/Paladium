package fr.paladium.palaforgeutils.lib.subcommand.base;

import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.builder.SubCommandBuilder;

public abstract class ABaseSubCommand extends ASubCommand {
  public ABaseSubCommand(String name, String description) {
    this.builder = SubCommandBuilder.create(name, description);
  }
  
  public ABaseSubCommand build(ASubCommand parent) {
    return build(parent, null);
  }
  
  public abstract ABaseSubCommand build(ASubCommand paramASubCommand, ISubCallback paramISubCallback);
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\subcommand\base\ABaseSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */