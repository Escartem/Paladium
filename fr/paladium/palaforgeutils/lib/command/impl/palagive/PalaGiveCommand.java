package fr.paladium.palaforgeutils.lib.command.impl.palagive;

import com.mongodb.client.result.DeleteResult;
import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.Command;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.CommandParameter;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.SubCommand;
import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import fr.paladium.palaforgeutils.lib.command.impl.palagive.manager.PalaGiveManager;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palaforgeutils.lib.player.OfflinePlayer;
import fr.paladium.palaforgeutils.lib.player.PlayerSelector;
import fr.paladium.palaforgeutils.lib.time.TimeUtils;
import fr.paladium.palaforgeutils.lib.tools.HastebinUtils;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palaforgeutils.lib.validator.impl.primitive.string.StringValidator.NotEmpty;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import org.bson.Document;

@Command(command = {"palagive"}, description = "Gives items to a player", permission = "paladium.command.palagive")
public class PalaGiveCommand {
  @SubCommand(command = "palagive dump <player>", description = "Dumps the inventory of a player to the console")
  public void dumpGive(CommandContext context, OfflinePlayer player) {
    PalaGiveManager.inst().dump(player.getUuidString()).thenAccept(logs -> {
          if (logs == null || logs.isEmpty()) {
            context.error("Aucune données trouvées pour le joueur " + player.getName() + " §8(" + player.getUuidString() + ")");
            return;
          } 
          logs.sort(());
          StringBuilder stringBuilder = new StringBuilder();
          for (Document log : logs) {
            ItemStack stack = ItemUtils.parse(log.getString("item"));
            String item = stack.toString() + (stack.func_77942_o() ? stack.field_77990_d.toString() : "");
            String server = log.getString("server");
            String inventoryBefore = log.getString("inventoryBefore");
            String inventoryAfter = log.getString("inventoryAfter");
            int given = log.getInteger("given").intValue();
            long timestamp = log.getLong("created").longValue();
            String date = TimeUtils.fromLong(timestamp / 1000L).format(DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm:ss O"));
            stringBuilder.append(String.format("{%n Item: %s,%n Server: %s,%n InventoryBefore: %s,%n InventoryAfter: %s,%n Given: %d,%n Date: %s%n}%n", new Object[] { item, server, inventoryBefore, inventoryAfter, Integer.valueOf(given), date }));
          } 
          context.success("Données pour le joueur " + player.getName() + ":");
          HastebinUtils.postHaste(context.getSender(), stringBuilder.toString());
        }).exceptionally(throwable -> {
          throwable.printStackTrace();
          context.error("Une erreur est survenue lors de la récupération des données.");
          return null;
        });
  }
  
  @SubCommand(command = "palagive clearqueue <player>", description = "Clear the item queue of a player")
  public void clearQueue(CommandContext context, OfflinePlayer player) {
    PalaGiveManager.inst().clear(player.getUuidString()).thenAccept(result -> {
          if (result == null) {
            context.error("Une erreur est survenue lors de la suppression de la file d'attente.");
            return;
          } 
          context.success(result.getDeletedCount() + " entrées ont été supprimé pour le joueur " + player.getName() + " (" + player.getUuidString() + ")");
        });
  }
  
  @SubCommand(command = "palagive <player> hand", description = "Gives an item to a player", sender = {SenderType.PLAYER})
  public void giveHand(CommandContext context, OfflinePlayer player) {
    if (context.getPlayer().func_70694_bm() == null) {
      context.error("Vous devez tenir un item en main.");
      return;
    } 
    ItemStack itemStack = context.getPlayer().func_70694_bm().func_77946_l();
    PalaGiveManager.inst().give(player, itemStack);
    context.success("§6x" + itemStack.field_77994_a + " " + itemStack.func_82833_r() + "§a ont bien été ajoutés à la file d'attente de " + player.getName() + ".");
  }
  
  @SubCommand(command = "palagive <player> inventory", description = "Gives your inventory to a player", sender = {SenderType.PLAYER})
  public void giveInventory(CommandContext context, OfflinePlayer player) {
    List<ItemStack> inventory = new ArrayList<>();
    for (int i = 0; i < (context.getPlayer()).field_71071_by.field_70462_a.length; i++) {
      ItemStack itemStack = (context.getPlayer()).field_71071_by.field_70462_a[i];
      if (itemStack != null)
        inventory.add(itemStack.func_77946_l()); 
    } 
    if (inventory.isEmpty()) {
      context.error("Votre inventaire est vide.");
      return;
    } 
    PalaGiveManager.inst().give(player, inventory);
    context.success("§aVotre inventaire a bien été ajouté à la file d'attente de " + player.getName() + ".");
  }
  
  @SubCommand(command = "palagive <player> <item>", description = "Gives an item to a player")
  public void giveItem(CommandContext context, OfflinePlayer player, @NotEmpty @CommandParameter(description = "Respect the pattern modid:unlocalized meta size or b64 item string") String item) {
    ItemStack itemStack = ItemUtils.parse(item);
    if (itemStack == null) {
      context.error("L'item spécifié est invalide: " + item);
      return;
    } 
    PalaGiveManager.inst().give(player.getUuidString(), itemStack);
    context.success("§6x" + itemStack.field_77994_a + " " + itemStack.func_82833_r() + "§a ont bien été ajoutés à la file d'attente de " + player.getName() + ".");
  }
  
  @SubCommand(command = "palagive all hand", description = "Gives an item to all players", sender = {SenderType.PLAYER})
  public void giveAllHand(CommandContext context) {
    if (context.getPlayer().func_70694_bm() == null) {
      context.error("Vous devez tenir un item en main.");
      return;
    } 
    ItemStack itemStack = context.getPlayer().func_70694_bm().func_77946_l();
    PlayerSelector.ALL().apply(playerMP -> PalaGiveManager.inst().give(UUIDUtils.toString((Entity)playerMP), itemStack));
    context.success("§6x" + itemStack.field_77994_a + " " + itemStack.func_82833_r() + "§a ont bien été ajoutés à la file d'attente de tous les joueurs en ligne sur ce serveur.");
  }
  
  @SubCommand(command = "palagive all inventory", description = "Gives your inventory to all players", sender = {SenderType.PLAYER})
  public void giveAllInventory(CommandContext context) {
    List<ItemStack> inventory = new ArrayList<>();
    for (int i = 0; i < (context.getPlayer()).field_71071_by.field_70462_a.length; i++) {
      ItemStack itemStack = (context.getPlayer()).field_71071_by.field_70462_a[i];
      if (itemStack != null)
        inventory.add(itemStack.func_77946_l()); 
    } 
    if (inventory.isEmpty()) {
      context.error("Votre inventaire est vide.");
      return;
    } 
    PlayerSelector.ALL().apply(playerMP -> PalaGiveManager.inst().give(UUIDUtils.toString((Entity)playerMP), inventory));
    context.success("§aVotre inventaire a bien été ajouté à la file d'attente de tous les joueurs en ligne sur ce serveur.");
  }
  
  @SubCommand(command = "palagive all <item>", description = "Gives an item to all players")
  public void giveAllItems(CommandContext context, @NotEmpty @CommandParameter(description = "Respect the pattern modid:unlocalized meta size or b64 item string") String item) {
    ItemStack itemStack = ItemUtils.parse(item);
    if (itemStack == null) {
      context.error("L'item spécifié est invalide: " + item);
      return;
    } 
    PlayerSelector.ALL().apply(playerMP -> PalaGiveManager.inst().give(UUIDUtils.toString((Entity)playerMP), itemStack));
    context.success("§7x" + itemStack.field_77994_a + " " + itemStack.func_82833_r() + "§a ont bien été ajoutés à la file d'attente de tous les joueurs en ligne sur ce serveur.");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\command\impl\palagive\PalaGiveCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */