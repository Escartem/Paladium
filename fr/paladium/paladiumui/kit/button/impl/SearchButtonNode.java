package fr.paladium.paladiumui.kit.button.impl;

import fr.paladium.paladiumui.kit.button.IconButtonNode;
import fr.paladium.paladiumui.kit.constant.ResourceConstant;
import lombok.NonNull;

public class SearchButtonNode extends IconButtonNode {
  protected SearchButtonNode(double x, double y) {
    super(x, y, 14.0D);
    icon(ResourceConstant.SEARCH);
  }
  
  @NonNull
  public static SearchButtonNode create(double x, double y) {
    return new SearchButtonNode(x, y);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\kit\button\impl\SearchButtonNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */