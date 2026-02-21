package fr.paladium.palashop.server.shop.command;

import fr.paladium.palaforgeutils.lib.command.SenderType;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.Command;
import fr.paladium.palaforgeutils.lib.command.annotated.annotation.SubCommand;
import fr.paladium.palaforgeutils.lib.command.annotated.context.CommandContext;
import fr.paladium.palaforgeutils.lib.validator.annotation.NotNull;
import fr.paladium.palaforgeutils.lib.validator.impl.primitive.string.StringValidator.NotEmpty;
import fr.paladium.palashop.api.server.shop.response.user.ShopBuyResponse;
import fr.paladium.palashop.client.ui.impl.home.UIShopHomePage;
import fr.paladium.palashop.server.shop.ShopManager;
import fr.paladium.palashop.server.shop.dto.BuyableObject;
import fr.paladium.palashop.server.shop.dto.IBuyable;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.zephyrui.internal.mod.server.utils.ZUIServer;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import net.minecraft.entity.player.EntityPlayer;

@Command(command = {"palashop", "store", "boutique"}, description = "Commande de gestion de la boutique", permission = "palashop.command.palashop")
public class PalaShopCommand {
  @SubCommand(command = "palashop", description = "Ouvrir la boutique", permission = "palashop.command.palashop.open", sender = {SenderType.PLAYER})
  public void open(CommandContext context) {
    ZUIServer.open(UIShopHomePage.class, context.getPlayer());
  }
  
  @SubCommand(command = "palashop update", description = "Mettre à jour la boutique", permission = "palashop.command.palashop.update")
  public void update(CommandContext context) {
    CompletableFuture<Void> future = ShopManager.update();
    future.thenAccept(v -> context.success("La boutique a été mise à jour"));
    future.exceptionally(e -> {
          context.error("Une erreur est survenue lors de la mise à jour de la boutique");
          return null;
        });
  }
  
  @SubCommand(command = "palashop buy <id> [<offer>]", description = "Acheter un item", permission = "palashop.command.palashop.buy", sender = {SenderType.PLAYER})
  public void buy(CommandContext context, @NotEmpty @NotNull String id, Optional<Boolean> offer) {
    boolean offerValue = ((Boolean)offer.orElse(Boolean.valueOf(false))).booleanValue();
    CompletableFuture<Map.Entry<ShopBuyResponse, BuyableObject<? extends IBuyable>>> future = ShopManager.User.buy((EntityPlayer)context.getPlayer(), id, offerValue);
    future.thenAccept(entry -> {
          ShopBuyResponse response = (ShopBuyResponse)entry.getKey();
          if (response.isSuccessful()) {
            if (response.getItems().isEmpty()) {
              context.error("Aucun item n'a été trouvé pour l'achat");
              return;
            } 
            context.success("Vous avez acheté les items ");
            for (IShopItem item : response.getItems())
              context.success("§8» §6" + item.getName()); 
          } else {
            context.error("Erreur » " + response.toString());
          } 
        });
    future.exceptionally(e -> {
          context.error("Une erreur est survenue lors de l'achat de l'item " + id);
          return null;
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\command\PalaShopCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */