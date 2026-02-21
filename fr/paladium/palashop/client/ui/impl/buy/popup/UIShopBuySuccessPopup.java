package fr.paladium.palashop.client.ui.impl.buy.popup;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palashop.client.ui.UIShopBase;
import fr.paladium.palashop.client.ui.UIShopPage;
import fr.paladium.palashop.client.ui.kit.button.TextButtonNode;
import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.palashop.client.ui.kit.constant.ResourceConstant;
import fr.paladium.palashop.client.ui.kit.constant.SoundConstant;
import fr.paladium.palashop.client.ui.kit.transition.ShopSlideDownTransition;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.popup.UIDataPopup;
import fr.paladium.zephyrui.lib.ui.core.transition.Transition;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import lombok.NonNull;

@UIDataPopup(active = true)
@UIData(backgroundColor = "#1C5616F2", zlevel = 1000.0D)
public class UIShopBuySuccessPopup extends UIShopBase {
  private final UIShopPage origin;
  
  public UIShopPage getOrigin() {
    return this.origin;
  }
  
  public UIShopBuySuccessPopup(@NonNull UIShopPage origin) {
    if (origin == null)
      throw new NullPointerException("origin is marked non-null but is null"); 
    this.origin = origin;
  }
  
  public void init() {
    SoundConstant.BUY_SUCCESS.copy().play();
    setTransition((Transition)new ShopSlideDownTransition());
    ImageNode.create(887.0D, 249.0D, 147.0D, 157.0D)
      .resource(ResourceConstant.ICON_BASKET_SUCCESS)
      .color(ColorConstant.GREEN)
      .attach((UI)this);
    TextNode.create(960.0D, 449.0D)
      .text(Text.create("Merci pour votre achat !", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 60.0F, Color.WHITE)))
      .anchorX(Align.CENTER)
      .attach((UI)this);
    TextNode.create(960.0D, 544.0D)
      .text(Text.create("le contenu de votre achat est disponible directement en jeu", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_REGULAR, 30.0F, Color.WHITE)))
      .anchorX(Align.CENTER)
      .attach((UI)this);
    TextButtonNode.create(852.0D, 696.0D, 216.0D, 66.0D)
      .text("Fermer")
      .radius(6.0F)
      .color(ColorConstant.PRIMARY)
      .onClick((node, mouseX, mouseY, clickType) -> {
          ZUI.open((UI)this.origin);
          this.origin.reload();
        }).attach((UI)this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\impl\buy\popup\UIShopBuySuccessPopup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */