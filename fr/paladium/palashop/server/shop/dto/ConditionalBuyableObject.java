package fr.paladium.palashop.server.shop.dto;

import fr.paladium.palashop.server.shop.condition.ShopConditions;
import fr.paladium.palashop.server.shop.condition.exception.ShopConditionException;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.palashop.server.shop.dto.offer.ShopOffer;
import fr.paladium.palashop.server.shop.dto.user.ShopUser;
import java.util.Objects;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayer;

public class ConditionalBuyableObject<T extends IBuyable> implements IBuyable {
  private final BuyableObject<T> object;
  
  private final String error;
  
  private final ErrorType errorType;
  
  public String toString() {
    return "ConditionalBuyableObject(object=" + getObject() + ", error=" + getError() + ", errorType=" + getErrorType() + ")";
  }
  
  public ConditionalBuyableObject(BuyableObject<T> object, String error, ErrorType errorType) {
    this.object = object;
    this.error = error;
    this.errorType = errorType;
  }
  
  public BuyableObject<T> getObject() {
    return this.object;
  }
  
  public String getError() {
    return this.error;
  }
  
  public ErrorType getErrorType() {
    return this.errorType;
  }
  
  @NonNull
  public static ConditionalBuyableObject<IShopItem> from(@NonNull ShopUser user, @NonNull EntityPlayer player, @NonNull IShopItem shopItem) {
    if (user == null)
      throw new NullPointerException("user is marked non-null but is null"); 
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (shopItem == null)
      throw new NullPointerException("shopItem is marked non-null but is null"); 
    return from(user, player, BuyableObject.of(user, shopItem));
  }
  
  @NonNull
  public static ConditionalBuyableObject<ShopOffer> from(@NonNull ShopUser user, @NonNull EntityPlayer player, @NonNull ShopOffer shopOffer) {
    if (user == null)
      throw new NullPointerException("user is marked non-null but is null"); 
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (shopOffer == null)
      throw new NullPointerException("shopOffer is marked non-null but is null"); 
    return from(user, player, BuyableObject.of(user, shopOffer));
  }
  
  @NonNull
  public static <T extends IBuyable> ConditionalBuyableObject<T> from(@NonNull ShopUser user, @NonNull EntityPlayer player, @NonNull BuyableObject<T> buyable) {
    if (user == null)
      throw new NullPointerException("user is marked non-null but is null"); 
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (buyable == null)
      throw new NullPointerException("buyable is marked non-null but is null"); 
    boolean isProvided = true;
    ShopConditionException exception = ShopConditions.validate(user, player, (IBuyable)buyable.getItem(), false);
    if (exception == null && buyable.getItem() instanceof ShopOffer) {
      ShopOffer shopOffer = (ShopOffer)buyable.getItem();
      if (shopOffer.getConditionalShopItems() != null) {
        for (ConditionalBuyableObject<IShopItem> shopItem : (Iterable<ConditionalBuyableObject<IShopItem>>)shopOffer.getConditionalShopItems()) {
          if (shopItem.isBuyable() || !((IShopItem)shopItem.getObject().getItem()).isAvailable().booleanValue() || shopItem.getErrorType() == ErrorType.OWNED || shopItem.getErrorType() == ErrorType.BUYABLE)
            continue; 
          return new ConditionalBuyableObject<>(buyable, shopItem.getError(), shopItem.getErrorType());
        } 
      } else {
        for (IShopItem shopItem : shopOffer.getShopItems()) {
          if (!shopItem.isAvailable().booleanValue())
            continue; 
          ConditionalBuyableObject<IShopItem> conditionalBuyableObject = from(user, player, BuyableObject.of(user, shopItem));
          if (conditionalBuyableObject.isBuyable() || conditionalBuyableObject.getErrorType() == ErrorType.OWNED || conditionalBuyableObject.getErrorType() == ErrorType.BUYABLE)
            continue; 
          return new ConditionalBuyableObject<>(buyable, conditionalBuyableObject.getError(), conditionalBuyableObject.getErrorType());
        } 
      } 
    } 
    boolean owned = false;
    if ((buyable.getItem().isUniquePurchase().booleanValue() || buyable.getItem().isSubscription()) && 
      user.getOwnedItems().contains(buyable.getItem().getUniqueId()))
      owned = true; 
    if (!owned && buyable.getItem() instanceof ShopOffer) {
      owned = true;
      ShopOffer shopOffer = (ShopOffer)buyable.getItem();
      for (IShopItem shopItem : shopOffer.getShopItems()) {
        if (!shopItem.isUniquePurchase().booleanValue() && !shopItem.isSubscription()) {
          owned = false;
          break;
        } 
        if (!user.getOwnedItems().contains(shopItem.getUniqueId()))
          owned = false; 
      } 
    } 
    boolean isBuyable = true;
    if (buyable.getItem() instanceof IShopItem) {
      if (!((IShopItem)buyable.getItem()).isBuyable().booleanValue())
        isBuyable = false; 
      if (((IShopItem)buyable.getItem()).getProviderInstance() == null)
        isProvided = false; 
    } 
    String error = owned ? "Vous possédez déjà ce produit." : ((exception != null) ? exception.getMessage() : (!isBuyable ? "Ce produit n'est pas achetable individuellement." : (!isProvided ? "Ce produit n'est pas disponible sur ce serveur." : null)));
    return new ConditionalBuyableObject<>(buyable, error, owned ? ErrorType.OWNED : ((exception != null || !isProvided) ? ErrorType.CONDITIONS : (!isBuyable ? ErrorType.BUYABLE : null)));
  }
  
  public boolean isBuyable() {
    return (this.error == null && this.errorType == null);
  }
  
  public String getUniqueId() {
    return this.object.getUniqueId();
  }
  
  public String getName() {
    return this.object.getName();
  }
  
  public String getDescription() {
    return this.object.getDescription();
  }
  
  public String getThumbnail() {
    return this.object.getThumbnail();
  }
  
  public ShopRarity getRarity() {
    return this.object.getRarity();
  }
  
  public Long getPrice() {
    return this.object.getPrice();
  }
  
  public Long getSubscription() {
    return this.object.getSubscription();
  }
  
  public boolean isSubscription() {
    return this.object.isSubscription();
  }
  
  public String[] getConditions() {
    return this.object.getConditions();
  }
  
  public Boolean isUniquePurchase() {
    return this.object.isUniquePurchase();
  }
  
  public Boolean isAvailable() {
    return this.object.isAvailable();
  }
  
  public enum ErrorType {
    OWNED, CONDITIONS, BUYABLE;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.error, this.errorType, this.object });
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null || getClass() != obj.getClass())
      return false; 
    ConditionalBuyableObject other = (ConditionalBuyableObject)obj;
    return (Objects.equals(this.error, other.error) && this.errorType == other.errorType && Objects.equals(this.object, other.object));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\dto\ConditionalBuyableObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */