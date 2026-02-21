package fr.paladium.palaforgeutils.lib.command.annotated.exception;

import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import lombok.NonNull;

public class CommandConditionException extends RuntimeException {
  public CommandConditionException(@NonNull CommandContext context, @NonNull String message) {
    super(message);
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    if (message == null)
      throw new NullPointerException("message is marked non-null but is null"); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\command\annotated\exception\CommandConditionException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */