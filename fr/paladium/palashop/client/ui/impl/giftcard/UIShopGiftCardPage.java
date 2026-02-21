package fr.paladium.palashop.client.ui.impl.giftcard;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palashop.api.server.giftcard.response.GiftCardRedeemResponse;
import fr.paladium.palashop.client.ui.UIShopPage;
import fr.paladium.palashop.client.ui.impl.giftcard.node.HiddenTextFieldNode;
import fr.paladium.palashop.client.ui.impl.giftcard.popup.UIShopGiftCardSuccessPopup;
import fr.paladium.palashop.client.ui.kit.button.TextButtonNode;
import fr.paladium.palashop.client.ui.kit.constant.ColorConstant;
import fr.paladium.palashop.client.ui.kit.constant.ResourceConstant;
import fr.paladium.palashop.server.giftcard.GiftCardManager;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.effect.NodeEffect;
import fr.paladium.zephyrui.lib.ui.node.effect.impl.RoundedNodeEffect;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.textfield.TextFieldNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.StringSignal;
import lombok.NonNull;

public class UIShopGiftCardPage extends UIShopPage implements UIShopPage.IBackableUI {
  private final UIShopPage back;
  
  private final StringSignal errorSignal;
  
  private final StringSignal codeSignal;
  
  private HiddenTextFieldNode codeField;
  
  public UIShopPage getBack() {
    return this.back;
  }
  
  public StringSignal getErrorSignal() {
    return this.errorSignal;
  }
  
  public StringSignal getCodeSignal() {
    return this.codeSignal;
  }
  
  public HiddenTextFieldNode getCodeField() {
    return this.codeField;
  }
  
  public UIShopGiftCardPage(@NonNull UIShopPage back) {
    super(back.getElement());
    if (back == null)
      throw new NullPointerException("back is marked non-null but is null"); 
    this.back = back;
    this.errorSignal = new StringSignal();
    this.codeSignal = new StringSignal();
  }
  
  public void init() {
    super.init();
    TextNode.create(42.0D, 138.0D)
      .text(Text.create(getElement().getName().toUpperCase() + " > ", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 15.0F, ColorConstant.LIGHT_GRAY)).add(Text.create("CARTE CADEAU", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 15.0F, ColorConstant.LIGHT_GRAY))))
      .onClick((node, mouseX, mouseY, clickType) -> ZUI.close((UI)this))
      .attach((UI)this);
    TextNode.create(42.0D, 159.0D)
      .text(Text.create("CARTE CADEAU", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 40.0F, Color.WHITE)))
      .attach((UI)this);
    RectNode.create(42.0D, 220.0D, 1836.0D, 2.0D)
      .color(ColorConstant.LIGHT_DARK)
      .attach((UI)this);
    ImageNode.create(960.0D, 388.0D)
      .resource(ResourceConstant.ICON_GIFT)
      .color(ColorConstant.PRIMARY)
      .width(94.0D)
      .anchorX(Align.CENTER)
      .attach((UI)this);
    ((TextNode)TextNode.create(960.0D, 512.0D)
      .onInit(node -> {
          if (this.errorSignal.getOrDefault() == null)
            return; 
          node.text(Text.create(((String)this.errorSignal.getOrDefault()).toUpperCase(), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 18.0F, ColorConstant.PRIMARY)));
        })).visible(node -> (this.errorSignal.isPresent() && !((String)this.errorSignal.getOrDefault()).isEmpty()))
      .watch((Signal)this.errorSignal)
      .anchorX(Align.CENTER)
      .attach((UI)this);
    TextNode.create(960.0D, 540.0D).text(Text.create("Saisissez les 16 lettres ou chiffres de votre code ou carte cadeau", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 18.0F, Color.WHITE)))
      .anchorX(Align.CENTER)
      .attach((UI)this);
    FlexNode.horizontal(960.0D, 589.0D, 54.0D)
      .margin(9.0D)
      .align(Align.CENTER)
      .body(flex -> {
          for (int i = 0; i < 4; i++) {
            int iFinal = i;
            ((RectNode)RectNode.create(0.0D, 0.0D, 258.0D, flex.getHeight()).color(ColorConstant.LIGHT_DARK).effect((NodeEffect)RoundedNodeEffect.create(6.0F)).body(()).onDraw(())).watch((Signal)this.errorSignal).attach(flex);
            if (i < 3)
              RectNode.create(0.0D, 0.0D, 14.0D, 2.0D).color(Color.WHITE).attach(flex); 
          } 
        }).anchorX(Align.CENTER)
      .attach((UI)this);
    this
      
      .codeField = (HiddenTextFieldNode)HiddenTextFieldNode.create(0.0D, 0.0D, 0.0D, 0.0D).focused(true).text((this.codeSignal.getOrDefault() == null) ? "" : (String)this.codeSignal.getOrDefault()).filter((oldText, newText) -> newText.replaceAll("[^a-zA-Z0-9]", "")).maxTextLength(16).onChange((node, oldText, newText) -> {
          this.errorSignal.set(null);
          this.codeSignal.set(newText);
        }).attach((UI)this);
    ((TextButtonNode)((TextButtonNode)TextButtonNode.create(853.0D, 726.0D, 249.0D, 66.0D)
      .text("Récupérer")
      .enabled(node -> (!node.isLoading() && node.isMounted() && this.codeSignal.isPresent() && ((String)this.codeSignal.getOrDefault()).length() >= 16)))
      .onClick((node, mouseX, mouseY, clickType) -> {
          if (!this.codeSignal.isPresent() || ((String)this.codeSignal.getOrDefault()).length() < 16)
            return; 
          node.loading(true);
          String rawCode = ((String)this.codeSignal.getOrDefault()).toLowerCase();
          String code = rawCode.substring(0, 4) + "-" + rawCode.substring(4, 8) + "-" + rawCode.substring(8, 12) + "-" + rawCode.substring(12, 16);
          GiftCardManager.redeem(code).thenAccept(()).exceptionally(());
        })).attach((UI)this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\impl\giftcard\UIShopGiftCardPage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */