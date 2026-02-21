package fr.paladium.palaforgeutils.lib.subcommand.base.impl;

import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.ABaseSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.ISubCallback;

public class StringSubCommand extends ABaseSubCommand {
  public StringSubCommand(String name, String description) {
    super(name, description);
    this.builder.string();
  }
  
  public static StringSubCommand create(String name, String description) {
    return new StringSubCommand(name, description);
  }
  
  public static StringSubCommand create(String name) {
    return new StringSubCommand(name, null);
  }
  
  public ABaseSubCommand build(ASubCommand parent, ISubCallback callback) {
    this.builder.build(parent, (ASubCommand)this, callback);
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\subcommand\base\impl\StringSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */