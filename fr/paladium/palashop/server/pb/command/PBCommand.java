package fr.paladium.palashop.server.pb.command;

import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.Command;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.SubCommand;
import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import fr.paladium.palaforgeutils.lib.player.OfflinePlayer;
import fr.paladium.palaforgeutils.lib.tools.HastebinUtils;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palaforgeutils.lib.validator.impl.primitive.number.NumberValidator.GreaterOrEquals;
import fr.paladium.palaforgeutils.lib.validator.impl.primitive.number.NumberValidator.Positive;
import fr.paladium.palaoracle.core.pojo.PalaOracleEvent;
import fr.paladium.palashop.api.server.pb.dto.PBData;
import fr.paladium.palashop.api.server.pb.dto.PBType;
import fr.paladium.palashop.api.server.pb.response.PBHistoryResponse;
import fr.paladium.palashop.server.pb.PBManager;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Command(command = {"pb", "pbs", "pointboutique", "pointboutiques"}, description = "Commande de gestion des points boutiques", permission = "palashop.command.pb")
public class PBCommand {
  @SubCommand(command = "pb", description = "Voir vos points boutiques", permission = "palashop.command.pb.get", sender = {SenderType.PLAYER})
  public void get(CommandContext context) {
    CompletableFuture<PBData> future = PBManager.getData(UUIDUtils.toString(context.getPlayer().func_110124_au()));
    future.thenAccept(pb -> {
          context.send("§7Vous avez §e" + pb.getTotal() + " §7points boutiques");
          context.send("§8» §7Saison: " + pb.getSeason());
          context.send("§8» §7Permanent: " + pb.getPermanent());
        });
    future.exceptionally(e -> {
          e.printStackTrace();
          context.send("Une erreur est survenue lors de la récupération de vos points boutiques");
          return null;
        });
  }
  
  @SubCommand(command = "pb <player>", description = "Voir les points boutiques d'un joueur", permission = "palashop.command.pb.get.other")
  public void get(CommandContext context, OfflinePlayer player) {
    CompletableFuture<PBData> future = PBManager.getData(player.getUuidString());
    future.thenAccept(pb -> {
          context.send("§e" + player.getName() + " §7a §e" + pb.getTotal() + " §7points boutiques");
          context.send("§8» §7Saison: " + pb.getSeason());
          context.send("§8» §7Permanent: " + pb.getPermanent());
        });
    future.exceptionally(e -> {
          e.printStackTrace();
          context.error("Une erreur est survenue lors de la récupération des points boutiques de §e" + player.getName());
          return null;
        });
  }
  
  @SubCommand(command = "pb dump <player>", description = "Récupérer l'historique de transactions d'un joueur", permission = "palashop.command.pb.dump")
  public void dump(CommandContext context, OfflinePlayer player) {
    CompletableFuture<PBHistoryResponse> future = PBManager.getHistory(player.getUuidString());
    future.thenAccept(history -> {
          if ((history.getHistory()).length == 0) {
            context.error("Aucune transaction trouvée pour §e" + player.getName());
            return;
          } 
          String toPost = "---------------------\n";
          toPost = toPost + "Paladium Shop Dump\n";
          toPost = toPost + "Player: " + player.getName() + "\n";
          toPost = toPost + "UUID: " + player.getUuidString() + "\n";
          toPost = toPost + "---------------------\n";
          toPost = toPost + "\n";
          toPost = toPost + "Transactions History:\n";
          toPost = toPost + "---------------------\n";
          List<PBHistoryResponse.PBHistoryLog> transactions = new LinkedList<>(Arrays.asList(history.getHistory()));
          Collections.reverse(transactions);
          for (PBHistoryResponse.PBHistoryLog transactionHistory : transactions) {
            long beforeTotal = transactionHistory.beforeTotal;
            long beforeSeason = transactionHistory.beforeSeason;
            long beforePermanent = transactionHistory.beforePermanent;
            long afterTotal = transactionHistory.afterTotal;
            long afterSeason = transactionHistory.afterSeason;
            long afterPermanent = transactionHistory.afterPermanent;
            long diff = afterTotal - beforeTotal;
            toPost = toPost + " Value: " + beforeTotal + " -> " + afterTotal + " (" + ((diff >= 0L) ? "+" : "-") + Math.abs(diff) + ")\n";
            toPost = toPost + " Amount Season: " + transactionHistory.seasonValue + "\n";
            toPost = toPost + " Amount Permanent: " + transactionHistory.permanentValue + "\n";
            toPost = toPost + " Before Total: " + beforeTotal + "\n";
            toPost = toPost + " Before Season: " + beforeSeason + "\n";
            toPost = toPost + " Before Permanent: " + beforePermanent + "\n";
            toPost = toPost + " After Total: " + afterTotal + "\n";
            toPost = toPost + " After Season: " + afterSeason + "\n";
            toPost = toPost + " After Permanent: " + afterPermanent + "\n";
            toPost = toPost + " Date: " + transactionHistory.date + "\n";
            toPost = toPost + " Reason: " + transactionHistory.reason + "\n";
            toPost = toPost + " Type: " + transactionHistory.mutation + "\n";
            toPost = toPost + " Server: " + transactionHistory.origin + "\n";
            toPost = toPost + "---------------------\n";
          } 
          HastebinUtils.postHaste(context.getSender(), toPost);
        });
    future.exceptionally(e -> {
          e.printStackTrace();
          context.error("Une erreur est survenue lors de la récupération de l'historique de transactions de §e" + player.getName());
          return null;
        });
  }
  
  @SubCommand(command = "pb give <player> <amount> [<reason>]", description = "Donner des points boutiques", permission = "palashop.command.pb.give")
  public void give(CommandContext context, OfflinePlayer player, @Positive long amount, Optional<String> reason) {
    give(context, player, amount, PBType.PERMANENT, reason);
  }
  
  @SubCommand(command = "pb give <player> <amount> <type> [<reason>]", description = "Donner des points boutiques", permission = "palashop.command.pb.give")
  public void give(CommandContext context, OfflinePlayer player, @Positive long amount, PBType type, Optional<String> reason) {
    String finalReason = context.getSender().func_70005_c_() + " -> /pb give " + player.getName() + " (" + player.getUuidString() + ") " + amount + " " + (String)reason.orElse("no_reason");
    CompletableFuture<Long> future = PBManager.add(player.getUuidString(), finalReason, amount, type);
    future.thenAccept(pb -> {
          try {
            PalaOracleEvent oracleEvent = new PalaOracleEvent(player.getUuidString(), "[SHOP] Injected PBs");
            oracleEvent.addField("timestamp", Long.valueOf(System.currentTimeMillis()));
            oracleEvent.addField("amount", Long.valueOf(amount));
            oracleEvent.addField("reason", finalReason);
            oracleEvent.capture();
          } catch (Exception e) {
            e.printStackTrace();
          } 
          context.send("§7Vous avez donné §e" + amount + " §7points boutiques " + ((type == PBType.SEASON) ? "saisonniers" : "permanents") + " à §e" + player.getName());
        });
    future.exceptionally(e -> {
          e.printStackTrace();
          context.error("Une erreur est survenue lors de l'ajout des points boutiques " + ((type == PBType.SEASON) ? "saisonniers" : "permanents") + " à §e" + player.getName());
          return null;
        });
  }
  
  @SubCommand(command = "pb remove <player> <amount>", description = "Retirer des points boutiques", permission = "palashop.command.pb.remove")
  public void remove(CommandContext context, OfflinePlayer player, @Positive long amount) {
    CompletableFuture<Long> future = PBManager.remove(player.getUuidString(), "pb removed by " + context.getSender().func_70005_c_(), amount);
    future.thenAccept(pb -> context.send("§7Vous avez retiré §e" + amount + " §7points boutiques à §e" + player.getName()));
    future.exceptionally(e -> {
          e.printStackTrace();
          context.error("Une erreur est survenue lors de la suppression des points boutiques de §e" + player.getName());
          return null;
        });
  }
  
  @SubCommand(command = "pb set <player> <amount> <type>", description = "Définir des points boutiques", permission = "palashop.command.pb.set")
  public void set(CommandContext context, OfflinePlayer player, @GreaterOrEquals(0.0D) long amount, PBType type) {
    CompletableFuture<Long> future = PBManager.set(player.getUuidString(), "pb set by " + context.getSender().func_70005_c_(), amount, type);
    future.thenAccept(pb -> context.send("§7Vous avez défini à §e" + amount + " §7points boutiques " + ((type == PBType.SEASON) ? "saisonniers" : "permanents") + " à §e" + player.getName()));
    future.exceptionally(e -> {
          e.printStackTrace();
          context.error("Une erreur est survenue lors de la définition des points boutiques " + ((type == PBType.SEASON) ? "saisonniers" : "permanents") + " de §e" + player.getName());
          return null;
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\pb\command\PBCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */