package fr.paladium.palashop.server.blackmarket.command;

import fr.paladium.palaforgeutils.lib.command.annotated.annotation.Command;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.SubCommand;
import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import fr.paladium.palashop.server.blackmarket.BlackMarketManager;
import fr.paladium.palashop.server.blackmarket.dto.BlackMarketConfig;
import java.util.concurrent.CompletableFuture;

@Command(command = {"blackmarket", "bm"}, description = "Commande de gestion du marché noir", permission = "palashop.command.blackmarket")
public class BlackMarketCommand {
  @SubCommand(command = "blackmarket update", description = "Mettre à jour le marché noir", permission = "palashop.command.blackmarket.update")
  public void update(CommandContext context) {
    CompletableFuture<BlackMarketConfig> future = BlackMarketManager.update();
    future.thenAccept(v -> context.success("Le marché noir a été mis à jour avec succès"));
    future.exceptionally(e -> {
          context.error("Une erreur est survenue lors de la mise à jour du marché noir");
          return null;
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\blackmarket\command\BlackMarketCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */