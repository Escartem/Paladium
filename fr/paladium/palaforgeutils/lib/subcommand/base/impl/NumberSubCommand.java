package fr.paladium.palaforgeutils.lib.subcommand.base.impl;

import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.ABaseSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.ISubCallback;

public class NumberSubCommand extends ABaseSubCommand {
  public NumberSubCommand(String name, String description, Number min, Number max) {
    super(name, description);
    this.builder.number(min, max);
  }
  
  public static NumberSubCommand create(String name, String description, Number min, Number max) {
    return new NumberSubCommand(name, description, min, max);
  }
  
  public static NumberSubCommand create(String name, Number min, Number max) {
    return new NumberSubCommand(name, null, min, max);
  }
  
  public static NumberSubCommand create(String name, String description) {
    return new NumberSubCommand(name, description, Integer.valueOf(0), Integer.valueOf(0));
  }
  
  public static NumberSubCommand create(String name) {
    return new NumberSubCommand(name, null, Integer.valueOf(0), Integer.valueOf(0));
  }
  
  public ABaseSubCommand build(ASubCommand parent, ISubCallback callback) {
    this.builder.build(parent, (ASubCommand)this, callback);
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\subcommand\base\impl\NumberSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */