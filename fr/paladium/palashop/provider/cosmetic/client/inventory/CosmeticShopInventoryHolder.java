package fr.paladium.palashop.provider.cosmetic.client.inventory;

import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palashop.client.ui.impl.inventory.manager.dto.IShopInventoryElement;
import fr.paladium.palashop.client.ui.impl.inventory.manager.dto.ShopInventoryHolder;
import fr.paladium.palashop.provider.cosmetic.CosmeticProvider;
import fr.paladium.palashop.provider.cosmetic.common.dto.cosmetic.ICosmetic;
import fr.paladium.palashop.provider.cosmetic.common.network.BBPacketGetCosmetics;
import fr.paladium.palashop.provider.cosmetic.factory.CosmeticFactory;
import fr.paladium.palashop.provider.cosmetic.server.dto.cosmetic.ShopCosmeticData;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import lombok.NonNull;

public class CosmeticShopInventoryHolder extends ShopInventoryHolder {
  public CosmeticShopInventoryHolder() {
    super("cosmetics", "Cosmétiques", CosmeticShopInventoryHolder::getElements);
  }
  
  @NonNull
  private static CompletableFuture<List<IShopInventoryElement>> getElements() {
    CompletableFuture<List<IShopInventoryElement>> future = new CompletableFuture<>();
    (new BBPacketGetCosmetics()).subscribe(data -> {
          if (data.getData() == null) {
            (new Notification(Notification.NotificationType.ERROR, "Impossible de charger vos cosmétiques", "palashop")).send();
            future.complete(new ArrayList());
            return;
          } 
          List<IShopInventoryElement> cosmetics = new ArrayList<>();
          for (ShopCosmeticData cosmeticData : data.getData()) {
            Optional<CosmeticFactory> optionalFactory = CosmeticProvider.getInstance().getFactory(cosmeticData.getFactory());
            if (!optionalFactory.isPresent())
              continue; 
            Optional<ICosmetic> optionalCosmetic = ((CosmeticFactory)optionalFactory.get()).getCosmetic(cosmeticData.getCosmetic());
            if (!optionalCosmetic.isPresent() || !(optionalCosmetic.get() instanceof fr.paladium.palashop.provider.cosmetic.client.dto.cosmetic.ICosmeticClient))
              continue; 
            cosmetics.add(optionalCosmetic.get());
          } 
          future.complete(cosmetics);
        }).send();
    return future;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\client\inventory\CosmeticShopInventoryHolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */