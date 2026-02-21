package fr.paladium.palaforgeutils.lib.command.annotated.parser.dto;

import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.NonNull;

public final class StaticSubCommandParameter implements SubCommandEntry.SubCommandParameter {
  private final String name;
  
  public StaticSubCommandParameter(String name) {
    this.name = name;
  }
  
  public String getName() {
    return this.name;
  }
  
  public boolean test(@NonNull CommandContext context, @NonNull String argument) {
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    if (argument == null)
      throw new NullPointerException("argument is marked non-null but is null"); 
    return this.name.equalsIgnoreCase(argument);
  }
  
  public CompletableFuture<List<String>> getTabComplete(@NonNull CommandContext context) {
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    return CompletableFuture.completedFuture(Arrays.asList(new String[] { this.name }));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\command\annotated\parser\dto\SubCommandEntry$StaticSubCommandParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */