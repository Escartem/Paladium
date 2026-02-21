package fr.paladium.palaautomation.common.util;

import fr.paladium.palaforgeutils.lib.chat.ChatUtils;
import net.minecraft.command.ICommandSender;

public enum PipeError {
  NO_ERROR("§aAucune erreur."),
  REACHED_MAX_NETWORK_SIZE("§cCe réseau a atteint la taille maximale de 30 tuyaux."),
  NO_RECEIVER("§cAucun récepteur trouvé."),
  RECEIVER_FULL("§cLe récepteur est plein.");
  
  private final String message;
  
  public String getMessage() {
    return this.message;
  }
  
  PipeError(String message) {
    this.message = message;
  }
  
  public void sendMessage(ICommandSender sender) {
    ChatUtils.sendColoredMessage(sender, new String[] { this.message });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\commo\\util\PipeError.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */