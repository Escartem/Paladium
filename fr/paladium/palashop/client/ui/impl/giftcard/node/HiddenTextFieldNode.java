package fr.paladium.palashop.client.ui.impl.giftcard.node;

import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.font.impl.minecraft.MinecraftFont;
import fr.paladium.zephyrui.lib.ui.node.impl.design.textfield.TextFieldNode;
import lombok.NonNull;

public class HiddenTextFieldNode extends TextFieldNode {
  protected HiddenTextFieldNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    info(TextInfo.create(MinecraftFont.MINECRAFT, 0.0F));
  }
  
  @NonNull
  public static HiddenTextFieldNode create(double x, double y, double width, double height) {
    return new HiddenTextFieldNode(x, y, width, height);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\impl\giftcard\node\HiddenTextFieldNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */