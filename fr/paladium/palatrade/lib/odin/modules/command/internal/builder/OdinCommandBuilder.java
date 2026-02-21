package fr.paladium.palatrade.lib.odin.modules.command.internal.builder;

import fr.paladium.palatrade.lib.odin.modules.command.OdinCommandModule;
import fr.paladium.palatrade.lib.odin.modules.command.lib.Command;
import fr.paladium.palatrade.lib.odin.modules.command.lib.SenderType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OdinCommandBuilder {
  private String name;
  
  private String permission;
  
  private List<SenderType> senderTypes = new ArrayList<>();
  
  public OdinCommandBuilder name(String name) {
    this.name = name;
    return this;
  }
  
  public OdinCommandBuilder permission(String permission) {
    this.permission = permission;
    return this;
  }
  
  public OdinCommandBuilder only(SenderType... senderTypes) {
    this.senderTypes = Arrays.asList(senderTypes);
    return this;
  }
  
  public void build(Class<? extends Command> command) {
    if (this.name == null || this.senderTypes == null) {
      System.out.println("[Odin] Unable to register command without name");
      return;
    } 
    OdinCommandModule.getInstance().registerCommand(command, this);
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


/* Location:              E:\Paladium\!\fr\paladium\palatrade\lib\odin\modules\command\internal\builder\OdinCommandBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */