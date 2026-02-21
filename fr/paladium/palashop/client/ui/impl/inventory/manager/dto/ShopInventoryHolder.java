package fr.paladium.palashop.client.ui.impl.inventory.manager.dto;

import fr.paladium.zephyrui.lib.utils.list.IndexedElement;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import lombok.NonNull;

public class ShopInventoryHolder implements IndexedElement {
  private final String id;
  
  private final String name;
  
  private final Supplier<CompletableFuture<List<IShopInventoryElement>>> supplier;
  
  private int index;
  
  public ShopInventoryHolder(String id, String name, Supplier<CompletableFuture<List<IShopInventoryElement>>> supplier) {
    this.id = id;
    this.name = name;
    this.supplier = supplier;
  }
  
  public String getId() {
    return this.id;
  }
  
  public String getName() {
    return this.name;
  }
  
  public Supplier<CompletableFuture<List<IShopInventoryElement>>> getSupplier() {
    return this.supplier;
  }
  
  public int getIndex() {
    return this.index;
  }
  
  public CompletableFuture<List<IShopInventoryElement>> get() {
    if (this.supplier == null || this.supplier.get() == null)
      return null; 
    return this.supplier.get();
  }
  
  @NonNull
  public <T extends ShopInventoryHolder> T index(int index) {
    this.index = index;
    return (T)this;
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.id });
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (!(obj instanceof ShopInventoryHolder))
      return false; 
    ShopInventoryHolder other = (ShopInventoryHolder)obj;
    return Objects.equals(this.id, other.id);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\impl\inventory\manager\dto\ShopInventoryHolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */