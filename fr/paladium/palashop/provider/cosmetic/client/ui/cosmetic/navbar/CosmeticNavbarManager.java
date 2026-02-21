package fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.navbar;

import fr.paladium.palashop.provider.cosmetic.client.ui.cosmetic.navbar.element.CosmeticNavbarElement;
import fr.paladium.zephyrui.lib.utils.list.IndexedElement;
import fr.paladium.zephyrui.lib.utils.list.IndexedLinkedList;
import lombok.NonNull;

public class CosmeticNavbarManager {
  private static final IndexedLinkedList<CosmeticNavbarElement> ELEMENT_LIST = new IndexedLinkedList();
  
  public static void register(@NonNull CosmeticNavbarElement element) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    ELEMENT_LIST.add((IndexedElement)element);
  }
  
  public static CosmeticNavbarElement getElement(@NonNull String id) {
    if (id == null)
      throw new NullPointerException("id is marked non-null but is null"); 
    return ELEMENT_LIST.ordered().stream().filter(page -> page.getId().equals(id)).findFirst().orElse(null);
  }
  
  @NonNull
  public static IndexedLinkedList<CosmeticNavbarElement> getElementList() {
    return ELEMENT_LIST;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\clien\\ui\cosmetic\navbar\CosmeticNavbarManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */