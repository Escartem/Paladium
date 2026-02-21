package fr.paladium.palaforgeutils.lib.command;

import fr.paladium.palaforgeutils.PalaForgeUtilsMod;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandBuilder {
  private String name;
  
  private String permission;
  
  private List<SenderType> senderTypes = new ArrayList<>();
  
  public static CommandBuilder create() {
    return new CommandBuilder();
  }
  
  public CommandBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public CommandBuilder permission(String permission) {
    this.permission = permission;
    return this;
  }
  
  public CommandBuilder only(SenderType... senderTypes) {
    this.senderTypes = Arrays.asList(senderTypes);
    return this;
  }
  
  public void build(Class<? extends Command> command) {
    if (this.name == null || this.senderTypes == null) {
      System.out.println("[Helios] Unable to register command without name");
      return;
    } 
    PalaForgeUtilsMod.getServer().registerCommand(command, this);
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getPermission() {
    return this.permission;
  }
  
  public List<SenderType> getSenderTypes() {
    return this.senderTypes;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\command\CommandBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */