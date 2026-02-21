package fr.paladium.palashop.client.ui.navbar.element.impl;

import fr.paladium.palashop.client.ui.navbar.element.ShopNavbarElement;
import fr.paladium.zephyrui.lib.ui.core.UI;
import lombok.NonNull;

public class ShopNavbarPage extends ShopNavbarElement {
  public ShopNavbarPage(@NonNull String id, @NonNull String name, @NonNull Class<? extends UI> uiClass) {
    super(id, name, uiClass);
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    if (uiClass == null)
      throw new NullPointerException("uiClass is marked non-null but is null"); 
  }
  
  @NonNull
  public ShopNavbarPage before(@NonNull ShopNavbarPage page) {
    if (page == null)
      throw new NullPointerException("page is marked non-null but is null"); 
    index(page.getIndex() - 1);
    return this;
  }
  
  @NonNull
  public ShopNavbarPage after(@NonNull ShopNavbarPage page) {
    if (page == null)
      throw new NullPointerException("page is marked non-null but is null"); 
    index(page.getIndex() + 1);
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\navbar\element\impl\ShopNavbarPage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */