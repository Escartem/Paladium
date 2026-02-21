package fr.paladium.palaforgeutils.lib.subcommand.base.impl;

import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.ABaseSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.ISubCallback;

public class EnumSubCommand extends ABaseSubCommand {
  public EnumSubCommand(String name, String description, Class<? extends Enum<?>> enumClass) {
    super(name, description);
    this.builder.enumeration(enumClass);
  }
  
  public static EnumSubCommand create(String name, String description, Class<? extends Enum<?>> enumClass) {
    return new EnumSubCommand(name, description, enumClass);
  }
  
  public ABaseSubCommand build(ASubCommand parent, ISubCallback callback) {
    this.builder.build(parent, (ASubCommand)this, callback);
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\subcommand\base\impl\EnumSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */