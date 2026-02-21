package fr.paladium.palashop.client.ui.impl.giftcard.popup;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palashop.client.ui.UIShopBase;
import fr.paladium.palashop.client.ui.kit.button.TextButtonNode;
import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.palashop.client.ui.kit.constant.ResourceConstant;
import fr.paladium.palashop.client.ui.kit.constant.ShopRarityConstant;
import fr.paladium.palashop.client.ui.kit.constant.SoundConstant;
import fr.paladium.palashop.client.ui.kit.render.impl.ShopElementThumbnailRendererNode;
import fr.paladium.palashop.client.ui.kit.transition.ShopSlideDownTransition;
import fr.paladium.palashop.server.shop.dto.item.IShopItem;
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
import fr.paladium.zephyrui.lib.ui.node.effect.NodeEffect;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.RoundedNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import lombok.NonNull;

@UIDataPopup(active = true)
@UIData(backgroundColor = "#17181BF2", zlevel = 1000.0D)
public class UIShopGiftCardSuccessPopup extends UIShopBase {
  private final IShopItem shopItem;
  
  private final Runnable callback;
  
  public IShopItem getShopItem() {
    return this.shopItem;
  }
  
  public Runnable getCallback() {
    return this.callback;
  }
  
  public UIShopGiftCardSuccessPopup(@NonNull IShopItem shopItem, Runnable callback) {
    if (shopItem == null)
      throw new NullPointerException("shopItem is marked non-null but is null"); 
    this.shopItem = shopItem;
    this.callback = callback;
  }
  
  public void init() {
    SoundConstant.BUY_SUCCESS.copy().play();
    setTransition((Transition)new ShopSlideDownTransition());
    ImageNode.create(930.0D, 89.0D, 60.0D, 44.0D)
      .color(ColorConstant.GREEN)
      .resource(ResourceConstant.ICON_SUCCESS)
      .attach((UI)this);
    TextNode.create(960.0D, 144.0D)
      .text(Text.create("Code validé", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 60.0F, Color.WHITE)))
      .anchorX(Align.CENTER)
      .attach((UI)this);
    TextNode.create(960.0D, 239.0D)
      .text(Text.create("le contenu de votre code est disponible directement en jeu", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_REGULAR, 30.0F, ColorConstant.LIGHT_GRAY)))
      .anchorX(Align.CENTER)
      .attach((UI)this);
    RectNode.create(436.0D, 311.0D, 1048.0D, 2.0D)
      .color(Color.WHITE)
      .attach((UI)this);
    TextNode.create(960.0D, 388.0D)
      .text(Text.create(this.shopItem.getName(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 30.0F, Color.WHITE)))
      .anchorX(Align.CENTER)
      .attach((UI)this);
    ((RectNode)RectNode.create(811.0D, 471.0D, 301.0D, 301.0D)
      .color(ColorConstant.LIGHT_DARK)
      .effect((NodeEffect)RoundedNodeEffect.create(39.0F))
      .body(rect -> {
          ShopElementThumbnailRendererNode.create(0.0D, 0.0D, rect.getWidth(), rect.getHeight()).object(this.shopItem).attach((Node)rect);
          ImageNode.create(rect.aw(-21.0D), 21.0D).resource(ShopRarityConstant.getResource(this.shopItem.getRarity())).width(rect.getWidth() * 0.2D).anchorX(Align.END).attach((Node)rect);
        })).attach((UI)this);
    TextButtonNode.create(852.0D, 897.0D, 216.0D, 66.0D)
      .text("Valider")
      .radius(6.0F)
      .color(ColorConstant.PRIMARY)
      .onClick((node, mouseX, mouseY, clickType) -> {
          ZUI.close((UI)this);
          if (this.callback != null)
            this.callback.run(); 
        }).attach((UI)this);
  }
  
  public boolean close() {
    if (this.callback != null)
      this.callback.run(); 
    return super.close();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\impl\giftcard\popup\UIShopGiftCardSuccessPopup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */