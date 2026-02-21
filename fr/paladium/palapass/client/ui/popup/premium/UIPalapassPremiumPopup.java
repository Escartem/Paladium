package fr.paladium.palapass.client.ui.popup.premium;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.TextButtonNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.constant.ResourceConstant;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palapass.client.ui.UIPalapass;
import fr.paladium.palapass.common.constants.PalapassTranslateEnum;
import fr.paladium.palapass.common.network.packet.server.CSPacketPalapassBuyPremium;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.popup.UIDataPopup;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.Signal;

@UIData(zlevel = 100.0D)
@UIDataPopup(active = true)
public class UIPalapassPremiumPopup extends UI {
  public void init() {
    BackgroundNode.create(901.0D, 351.0D)
      .body(background -> {
          CloseButtonNode.create(background.aw(-35.0D), 15.0D).onClick(()).attach(background);
          FlexNode.horizontal(background.dw(2.0D), 42.0D, PaladiumText.TITLE.getHeight()).anchorX(Align.CENTER).body(()).attach(background);
          TextNode.create(background.dw(2.0D), 110.0D).text(Text.create(PalapassTranslateEnum.UI_PROMPT_UNLOCK_REWARDS.textOrDefault("vous pourrez débloquer des récompenses exclusives").toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 15.0F, Color.WHITE), Align.CENTER)).anchorX(Align.CENTER).attach(background);
          FlexNode.horizontal(background.dw(2.0D), 199.0D, TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 28.0F, Color.WHITE).getHeight()).anchorX(Align.CENTER).body(()).attach(background);
          FlexNode.horizontal(background.dw(2.0D), 281.0D, 60.0D).margin(40.0D).anchorX(Align.CENTER).body(()).attach(background);
        }).attach(this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palapass\clien\\ui\popup\premium\UIPalapassPremiumPopup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */