package fr.paladium.palashop.common.provider.dto.render;

import fr.paladium.zephyrui.lib.ui.node.Node;
import lombok.NonNull;

public interface ShopElementRenderer<T> {
  void render(@NonNull T paramT, @NonNull Node paramNode);
  
  default boolean isValid(@NonNull T element) {
    if (element == null)
      throw new NullPointerException("element is marked non-null but is null"); 
    return true;
  }
  
  @NonNull
  Class<T> getElementType();
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\provider\dto\render\ShopElementRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */