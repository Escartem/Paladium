package fr.paladium.palashop.client.ui.navbar.element.impl;

import fr.paladium.palashop.client.ui.navbar.element.ShopNavbarElement;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import lombok.NonNull;

public class ShopNavbarTab extends ShopNavbarElement {
  private final Resource resource;
  
  public Resource getResource() {
    return this.resource;
  }
  
  public ShopNavbarTab(@NonNull String id, @NonNull String name, @NonNull Resource resource, @NonNull Class<? extends UI> uiClass) {
    super(id, name, uiClass);
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    if (resource == null)
      throw new NullPointerException("resource is marked non-null but is null"); 
    if (uiClass == null)
      throw new NullPointerException("uiClass is marked non-null but is null"); 
    this.resource = resource;
  }
  
  @NonNull
  public ShopNavbarTab before(@NonNull ShopNavbarTab page) {
    if (page == null)
      throw new NullPointerException("page is marked non-null but is null"); 
    index(page.getIndex() - 1);
    return this;
  }
  
  @NonNull
  public ShopNavbarTab after(@NonNull ShopNavbarTab page) {
    if (page == null)
      throw new NullPointerException("page is marked non-null but is null"); 
    index(page.getIndex() + 1);
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\navbar\element\impl\ShopNavbarTab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */