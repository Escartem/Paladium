package fr.paladium.palarpg.client.ui.kit.textfield;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.textfield.TextFieldNode;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import lombok.NonNull;

public class TextFieldNode extends TextFieldNode {
  protected TextFieldNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    backgroundColor(new Color(40, 40, 40));
    borderColor(new Color(40, 40, 40));
    focusColor((new Color(40, 40, 40)).darker(0.2F));
    marginLeft(18.0D);
    info(TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 14.0F, Color.WHITE));
  }
  
  @NonNull
  public static TextFieldNode create(double x, double y, double width, double height) {
    return new TextFieldNode(x, y, width, height);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\clien\\ui\kit\textfield\TextFieldNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */