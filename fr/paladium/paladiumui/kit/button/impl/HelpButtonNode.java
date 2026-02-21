package fr.paladium.paladiumui.kit.button.impl;

import fr.paladium.paladiumui.kit.constant.ResourceConstant;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import lombok.NonNull;

public class HelpButtonNode extends ImageNode {
  protected HelpButtonNode(double x, double y) {
    super(x, y);
    size(28.0D, 28.0D);
    resource(ResourceConstant.HELP);
    linear(false);
  }
  
  @NonNull
  public static HelpButtonNode create(double x, double y) {
    return new HelpButtonNode(x, y);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\kit\button\impl\HelpButtonNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */