package fr.paladium.paladiumui.kit.button.impl;

import fr.paladium.paladiumui.kit.button.IconButtonNode;
import fr.paladium.paladiumui.kit.constant.ResourceConstant;
import lombok.NonNull;

public class CloseButtonNode extends IconButtonNode {
  protected CloseButtonNode(double x, double y) {
    super(x, y, 14.0D);
    icon(ResourceConstant.CLOSE);
  }
  
  @NonNull
  public static CloseButtonNode create(double x, double y) {
    return new CloseButtonNode(x, y);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\kit\button\impl\CloseButtonNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */