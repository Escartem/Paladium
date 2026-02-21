package fr.paladium.paladiumui.kit.button.impl;

import fr.paladium.paladiumui.kit.constant.ResourceConstant;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import lombok.NonNull;

public class BackButtonNode extends ImageNode {
  protected BackButtonNode(double x, double y) {
    super(x, y);
    size(25.0D, 25.0D);
    resource(ResourceConstant.BACK);
    linear(false);
  }
  
  @NonNull
  public static BackButtonNode create(double x, double y) {
    return new BackButtonNode(x, y);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\kit\button\impl\BackButtonNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */