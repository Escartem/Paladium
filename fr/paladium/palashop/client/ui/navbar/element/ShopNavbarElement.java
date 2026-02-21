package fr.paladium.palashop.client.ui.navbar.element;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.utils.list.IndexedElement;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import lombok.NonNull;

public abstract class ShopNavbarElement implements IndexedElement {
  private final String id;
  
  private final String name;
  
  private final Class<? extends UI> uiClass;
  
  private int index;
  
  private Supplier<CompletableFuture<Boolean>> condition;
  
  public ShopNavbarElement(String id, String name, Class<? extends UI> uiClass) {
    this.condition = (() -> CompletableFuture.completedFuture(Boolean.valueOf(true)));
    this.id = id;
    this.name = name;
    this.uiClass = uiClass;
  }
  
  public String getId() {
    return this.id;
  }
  
  public String getName() {
    return this.name;
  }
  
  public Class<? extends UI> getUiClass() {
    return this.uiClass;
  }
  
  public int getIndex() {
    return this.index;
  }
  
  public Supplier<CompletableFuture<Boolean>> getCondition() {
    return this.condition;
  }
  
  @NonNull
  public <T extends ShopNavbarElement> T index(int index) {
    this.index = index;
    return (T)this;
  }
  
  @NonNull
  public <T extends ShopNavbarElement> T condition(@NonNull Supplier<CompletableFuture<Boolean>> condition) {
    if (condition == null)
      throw new NullPointerException("condition is marked non-null but is null"); 
    this.condition = condition;
    return (T)this;
  }
  
  @NonNull
  public CompletableFuture<Boolean> isAvailable() {
    return this.condition.get();
  }
  
  @SideOnly(Side.CLIENT)
  public UI createUI() {
    try {
      return this.uiClass.newInstance();
    } catch (InstantiationException|IllegalAccessException e) {
      e.printStackTrace();
      return null;
    } 
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.id });
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (!(obj instanceof ShopNavbarElement))
      return false; 
    ShopNavbarElement other = (ShopNavbarElement)obj;
    return Objects.equals(this.id, other.id);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\navbar\element\ShopNavbarElement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */