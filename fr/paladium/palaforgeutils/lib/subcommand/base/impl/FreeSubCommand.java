package fr.paladium.palaforgeutils.lib.subcommand.base.impl;

import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.ABaseSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.ISubCallback;

public class FreeSubCommand extends ABaseSubCommand {
  public FreeSubCommand(String name, String description, String... freeArgs) {
    super(name, description);
    this.builder.freeArgument(freeArgs);
  }
  
  public FreeSubCommand(String name, String description) {
    super(name, description);
    this.builder.freeArgument();
  }
  
  public static FreeSubCommand create(String name) {
    return new FreeSubCommand(name, null);
  }
  
  public static FreeSubCommand create(String name, String description) {
    return new FreeSubCommand(name, description);
  }
  
  public static FreeSubCommand create(String name, String description, String... freeArgs) {
    return new FreeSubCommand(name, description, freeArgs);
  }
  
  public static FreeSubCommand create(String name, String... freeArgs) {
    return new FreeSubCommand(name, null, freeArgs);
  }
  
  public ABaseSubCommand build(ASubCommand parent, ISubCallback callback) {
    this.builder.build(parent, (ASubCommand)this, callback);
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\subcommand\base\impl\FreeSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */