package fr.paladium.paladiumui.kit.text;

import fr.paladium.paladiumui.kit.constant.ColorConstant;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;

public class PaladiumText {
  public static final TextInfo HEADER = TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 54.0F).color(ColorConstant.PRIMARY).shadow(new Color(185, 28, 12)).shadow(0.0F, 4.0F);
  
  public static final TextInfo TITLE = TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 25.0F).color(Color.WHITE);
  
  public static final TextInfo SUB_TITLE = TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 20.0F).color(Color.WHITE);
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\kit\text\PaladiumText.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */