package fr.paladium.palashop.server.shop.dto;

import fr.paladium.palashop.server.blackmarket.BlackMarketManager;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
import fr.paladium.palashop.server.shop.dto.offer.ShopOffer;
import fr.paladium.palashop.server.shop.dto.user.ShopUser;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.NonNull;

public class BuyableObject<T extends IBuyable> implements IBuyable {
  private final transient ShopUser shopUser;
  
  private final T item;
  
  private final List<Float> discounts;
  
  private long price;
  
  private long defaultPrice;
  
  public String toString() {
    return "BuyableObject(shopUser=" + getShopUser() + ", item=" + getItem() + ", discounts=" + getDiscounts() + ", price=" + getPrice() + ", defaultPrice=" + getDefaultPrice() + ")";
  }
  
  private BuyableObject(ShopUser shopUser, T item, List<Float> discounts, long price, long defaultPrice) {
    this.shopUser = shopUser;
    this.item = item;
    this.discounts = discounts;
    this.price = price;
    this.defaultPrice = defaultPrice;
  }
  
  public ShopUser getShopUser() {
    return this.shopUser;
  }
  
  public T getItem() {
    return this.item;
  }
  
  public List<Float> getDiscounts() {
    return this.discounts;
  }
  
  public long getDefaultPrice() {
    return this.defaultPrice;
  }
  
  private BuyableObject(@NonNull ShopUser shopUser, @NonNull T item, long price) {
    if (shopUser == null)
      throw new NullPointerException("shopUser is marked non-null but is null"); 
    if (item == null)
      throw new NullPointerException("item is marked non-null but is null"); 
    this.shopUser = shopUser;
    this.item = item;
    this.discounts = new ArrayList<>();
    this.price = price;
    this.defaultPrice = price;
  }
  
  private BuyableObject(@NonNull BuyableObject<T> object) {
    this(object.shopUser, object.item, object.discounts, object.price, object.defaultPrice);
    if (object == null)
      throw new NullPointerException("object is marked non-null but is null"); 
  }
  
  @NonNull
  public static <T extends IShopItem> BuyableObject<T> of(@NonNull ShopUser shopUser, @NonNull T shopItem) {
    if (shopUser == null)
      throw new NullPointerException("shopUser is marked non-null but is null"); 
    if (shopItem == null)
      throw new NullPointerException("shopItem is marked non-null but is null"); 
    BuyableObject<T> object = (BuyableObject)new BuyableObject<>(shopUser, (IBuyable)shopItem, shopItem.getPrice().longValue());
    object.addDiscounts(new Float[] { Float.valueOf(BlackMarketManager.getDiscount(shopUser, shopItem.getUniqueId()) / 100.0F) });
    object.addDiscount(shopItem.getDiscount().floatValue());
    return object;
  }
  
  @NonNull
  public static BuyableObject<ShopOffer> of(@NonNull ShopUser shopUser, @NonNull ShopOffer shopOffer) {
    if (shopUser == null)
      throw new NullPointerException("shopUser is marked non-null but is null"); 
    if (shopOffer == null)
      throw new NullPointerException("shopOffer is marked non-null but is null"); 
    List<BuyableObject<IShopItem>> buyableObjects = new ArrayList<>();
    if (shopOffer.getConditionalShopItems() != null) {
      for (ConditionalBuyableObject<IShopItem> item : (Iterable<ConditionalBuyableObject<IShopItem>>)shopOffer.getConditionalShopItems()) {
        if (item.getErrorType() == ConditionalBuyableObject.ErrorType.OWNED)
          continue; 
        buyableObjects.add(item.getObject());
      } 
    } else {
      for (IShopItem item : shopOffer.getShopItems())
        buyableObjects.add(of(shopUser, item)); 
    } 
    return (new BuyableObject<>(shopUser, shopOffer, ((Long)buyableObjects.stream().map(BuyableObject::getPrice).reduce(Long::sum).orElse(Long.valueOf(0L))).longValue())).addDiscount((float)shopOffer.getDiscount().longValue());
  }
  
  @NonNull
  private BuyableObject<T> addDiscounts(@NonNull List<Float> discounts) {
    if (discounts == null)
      throw new NullPointerException("discounts is marked non-null but is null"); 
    for (Iterator<Float> iterator = discounts.iterator(); iterator.hasNext(); ) {
      float discount = ((Float)iterator.next()).floatValue();
      addDiscount(discount);
    } 
    return this;
  }
  
  @NonNull
  private BuyableObject<T> addDiscounts(@NonNull Float... discounts) {
    if (discounts == null)
      throw new NullPointerException("discounts is marked non-null but is null"); 
    Float[] arrayOfFloat;
    int i;
    byte b;
    for (arrayOfFloat = discounts, i = arrayOfFloat.length, b = 0; b < i; ) {
      float discount = arrayOfFloat[b].floatValue();
      addDiscount(discount);
      b++;
    } 
    return this;
  }
  
  @NonNull
  private BuyableObject<T> addDiscount(float discount) {
    if (discount == 0.0F)
      return this; 
    if (discount < 0.0F)
      throw new IllegalArgumentException("Discount cannot be negative"); 
    if (discount > 1.0F)
      discount /= 100.0F; 
    this.discounts.add(Float.valueOf(discount));
    float totalDiscount = 0.0F;
    for (Iterator<Float> iterator = this.discounts.iterator(); iterator.hasNext(); ) {
      float d = ((Float)iterator.next()).floatValue();
      totalDiscount += d * (1.0F - totalDiscount);
    } 
    double price = ((float)this.defaultPrice * (1.0F - totalDiscount));
    if (price < 100.0D) {
      this.price = (long)price;
      return this;
    } 
    int digits = (int)Math.min(4.0D, Math.log10(price));
    double round = 5.0D * Math.pow(10.0D, (digits - 2));
    this.price = (long)(Math.ceil(price / round) * round);
    return this;
  }
  
  public boolean hasDiscount() {
    return (this.price != this.defaultPrice);
  }
  
  public float getDiscount() {
    return ((Float)this.discounts.stream().reduce(Float::sum).orElse(Float.valueOf(0.0F))).floatValue();
  }
  
  public float getRealDiscount() {
    return (float)(this.defaultPrice - this.price) / (float)this.defaultPrice;
  }
  
  public String getUniqueId() {
    return this.item.getUniqueId();
  }
  
  public String getName() {
    return this.item.getName();
  }
  
  public String getDescription() {
    return this.item.getDescription();
  }
  
  public String getThumbnail() {
    return this.item.getThumbnail();
  }
  
  public ShopRarity getRarity() {
    return this.item.getRarity();
  }
  
  public Long getPrice() {
    return Long.valueOf(this.price);
  }
  
  public Long getSubscription() {
    return this.item.getSubscription();
  }
  
  public boolean isSubscription() {
    return this.item.isSubscription();
  }
  
  public String[] getConditions() {
    return this.item.getConditions();
  }
  
  public Boolean isUniquePurchase() {
    return this.item.isUniquePurchase();
  }
  
  public Boolean isAvailable() {
    return this.item.isAvailable();
  }
  
  @NonNull
  public BuyableObject<T> copy() {
    return new BuyableObject(this);
  }
  
  public boolean equals(Object o) {
    if (this == o)
      return true; 
    if (!(o instanceof BuyableObject))
      return false; 
    BuyableObject<?> that = (BuyableObject)o;
    return this.item.equals(that.item);
  }
  
  public int hashCode() {
    return this.item.hashCode();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\dto\BuyableObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */