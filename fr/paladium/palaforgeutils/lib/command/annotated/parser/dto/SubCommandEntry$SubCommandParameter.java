package fr.paladium.palaforgeutils.lib.command.annotated.parser.dto;

import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.NonNull;

public interface SubCommandParameter {
  String getName();
  
  boolean test(@NonNull CommandContext paramCommandContext, @NonNull String paramString);
  
  CompletableFuture<List<String>> getTabComplete(@NonNull CommandContext paramCommandContext);
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\command\annotated\parser\dto\SubCommandEntry$SubCommandParameter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */