package fr.paladium.palamod.modules.luckyblock.ui.utils.container;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palamod.modules.luckyblock.ui.UISafeChestLocked;
import fr.paladium.palamod.modules.luckyblock.ui.utils.signal.CodeSignal;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.signal.Signal;

public class CodeDisplayContainer extends ContainerNode {
  private final TextInfo numberTextInfo = TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 100.0F).color(Color.WHITE);
  
  protected CodeDisplayContainer(double x, double y, double width, double height) {
    super(x, y, width, height);
    onInit(container -> {
          CodeSignal codeSignal = ((UISafeChestLocked)getUi()).getCodeSignal();
          FlexNode.horizontal(0.0D, 0.0D, getHeight()).margin(40.0D).width(getWidth()).onInit(()).watch((Signal)codeSignal).attach((Node)this);
        });
  }
  
  public static CodeDisplayContainer create(double x, double y, double width, double height) {
    return new CodeDisplayContainer(x, y, width, height);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckybloc\\u\\utils\container\CodeDisplayContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */