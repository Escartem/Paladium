package fr.paladium.palamod.modules.mailbox.client.ui.container;

import fr.paladium.paladiumui.kit.button.TextButtonNode;
import fr.paladium.paladiumui.kit.constant.ResourceConstant;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.textfield.TextFieldNode;
import fr.paladium.palamod.modules.mailbox.client.ui.ZUIMailBox;
import fr.paladium.palamod.modules.mailbox.client.ui.ZUIMailBoxSendAdminMail;
import fr.paladium.palamod.modules.mailbox.client.ui.nodes.MultilineTextFieldNode;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.resource.ResourceUtils;
import lombok.NonNull;

public class AdminNewMessageContainer extends RectNode {
  public static final Resource ENVELOPE_ICON = Resource.of(ResourceUtils.get("palamod", "textures/gui/mailbox/envelope_icon.png"));
  
  private final TextInfo newMessageInputInfo = TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 19.0F).color(new Color(68, 68, 68));
  
  private TextFieldNode recipientNode;
  
  public TextFieldNode getRecipientNode() {
    return this.recipientNode;
  }
  
  protected AdminNewMessageContainer(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    ZUIMailBoxSendAdminMail adminUi = (ZUIMailBoxSendAdminMail)getUi();
    FlexNode.horizontal(35.0D, 28.0D, 114.0D)
      .margin(35.0D)
      .body(row -> {
          ImageNode.create(0.0D, 0.0D, 114.0D, 114.0D).resource(ZUIMailBox.MESSAGE_BADGE.nearest()).attach(row);
          TextNode.create(0.0D, row.dh(2.0D)).text(Text.create("Nouveau message", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 64.0F, Color.WHITE))).anchorY(Align.CENTER).attach(row);
        }).attach((Node)this);
    ((TextFieldNode)TextFieldNode.create(35.0D, 175.0D, 582.0D)
      .backgroundColor(Color.WHITE)
      .borderColor(Color.WHITE)
      .focusColor(Color.LIGHTGRAY)
      .margin(70.0D)
      .placeholder("OBJET")
      .info(this.newMessageInputInfo)
      .onChange((field, oldText, newText) -> adminUi.getObjectSignal().set(field.getText())))
      
      .body(input -> ImageNode.create(28.0D, 11.0D).resource(ENVELOPE_ICON).size(32.0D, 24.0D).attach(input))
      
      .attach((Node)this);
    this
      
      .recipientNode = (TextFieldNode)((TextFieldNode)TextFieldNode.create(35.0D, 258.0D, 582.0D).backgroundColor(Color.WHITE).borderColor(Color.WHITE).focusColor(Color.LIGHTGRAY).margin(70.0D).placeholder("DESTINATAIRE").info(this.newMessageInputInfo).onChange((field, oldText, newText) -> adminUi.getRecipientSignal().set(field.getText()))).body(input -> ImageNode.create(30.0D, 8.0D).resource(NewMessageContainer.NAMETAG_ICON).size(30.0D, 30.0D).attach(input)).attach((Node)this);
    ((MultilineTextFieldNode)MultilineTextFieldNode.create(35.0D, 345.0D, 1029.0D, 357.0D)
      .backgroundColor(Color.WHITE)
      .borderColor(Color.WHITE)
      .focusColor(Color.LIGHTGRAY)
      .margin(25.0D)
      .placeholder("CONTENU...")
      .info(this.newMessageInputInfo)
      .onChange((field, oldText, newText) -> adminUi.getContentSignal().set(field.getText())))
      
      .attach((Node)this);
    TextButtonNode.create(862.0D, 736.0D)
      .icon(ResourceConstant.CLOSE)
      .iconSize(24.0D)
      .iconPosition(TextButtonNode.IconPosition.LEFT)
      .text("Envoyer")
      .onClick((btn, mouseX, mouseY, clickType) -> adminUi.sendMessage())
      
      .attach((Node)this);
  }
  
  public static AdminNewMessageContainer create(double x, double y, double width, double height) {
    return new AdminNewMessageContainer(x, y, width, height);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\clien\\ui\container\AdminNewMessageContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */