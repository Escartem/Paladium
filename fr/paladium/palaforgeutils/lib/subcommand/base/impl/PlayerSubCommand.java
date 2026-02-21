package fr.paladium.palaforgeutils.lib.subcommand.base.impl;

import fr.paladium.palaforgeutils.lib.subcommand.ASubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.ABaseSubCommand;
import fr.paladium.palaforgeutils.lib.subcommand.base.ISubCallback;

public class PlayerSubCommand extends ABaseSubCommand {
  public PlayerSubCommand(String name, String description) {
    super(name, description);
    this.builder.player();
  }
  
  public static PlayerSubCommand create(String name, String description) {
    return new PlayerSubCommand(name, description);
  }
  
  public static PlayerSubCommand create(String name) {
    return create(name, null);
  }
  
  public ABaseSubCommand build(ASubCommand parent, ISubCallback callback) {
    this.builder.build(parent, (ASubCommand)this, callback);
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\subcommand\base\impl\PlayerSubCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */