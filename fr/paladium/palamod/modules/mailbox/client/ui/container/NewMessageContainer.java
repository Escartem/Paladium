package fr.paladium.palamod.modules.mailbox.client.ui.container;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.paladiumui.kit.button.IconButtonNode;
import fr.paladium.paladiumui.kit.button.TextButtonNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.textfield.TextFieldNode;
import fr.paladium.palamod.modules.mailbox.PMailbox;
import fr.paladium.palamod.modules.mailbox.client.ui.UIAction;
import fr.paladium.palamod.modules.mailbox.client.ui.ZUIMailBox;
import fr.paladium.palamod.modules.mailbox.client.ui.nodes.MultilineTextFieldNode;
import fr.paladium.palamod.modules.mailbox.common.packets.server.MailboxServerMessagePacket;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.resource.ResourceUtils;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.StringSignal;
import lombok.NonNull;

public class NewMessageContainer extends ContainerNode {
  public static final Resource NAMETAG_ICON = Resource.of(ResourceUtils.get("palamod", "textures/gui/mailbox/nametag_icon.png"));
  
  private final TextInfo newMessageInputInfo = TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 19.0F).color(new Color(68, 68, 68));
  
  private final StringSignal objectSignal = new StringSignal("");
  
  private final StringSignal recipientSignal = new StringSignal("");
  
  private final StringSignal contentSignal = new StringSignal("");
  
  protected NewMessageContainer(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    FlexNode.horizontal(35.0D, 28.0D, 114.0D)
      .margin(35.0D)
      .body(row -> {
          ImageNode.create(0.0D, 0.0D, 114.0D, 114.0D).resource(ZUIMailBox.MESSAGE_BADGE).attach(row);
          TextNode.create(0.0D, 0.0D).text(Text.create("Nouveau message", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 64.0F, Color.WHITE))).attach(row);
        }).attach((Node)this);
    ((TextFieldNode)TextFieldNode.create(35.0D, 175.0D, 1029.0D)
      .backgroundColor(Color.WHITE)
      .borderColor(Color.WHITE)
      .focusColor(Color.LIGHTGRAY)
      .margin(70.0D)
      .placeholder("OBJET")
      .info(this.newMessageInputInfo)
      .onChange((field, oldText, newText) -> this.objectSignal.set(field.getText())))
      
      .body(input -> ImageNode.create(28.0D, 11.0D).resource(AdminNewMessageContainer.ENVELOPE_ICON).size(32.0D, 24.0D).attach(input))
      
      .attach((Node)this);
    ((TextFieldNode)TextFieldNode.create(35.0D, 258.0D, 1029.0D)
      .backgroundColor(Color.WHITE)
      .borderColor(Color.WHITE)
      .focusColor(Color.LIGHTGRAY)
      .margin(70.0D)
      .placeholder("DESTINATAIRE")
      .info(this.newMessageInputInfo)
      .onChange((field, oldText, newText) -> this.recipientSignal.set(field.getText())))
      
      .body(input -> ImageNode.create(30.0D, 8.0D).resource(NAMETAG_ICON).size(30.0D, 30.0D).attach(input))
      
      .attach((Node)this);
    ((MultilineTextFieldNode)MultilineTextFieldNode.create(35.0D, 345.0D, 1029.0D, 357.0D)
      .backgroundColor(Color.WHITE)
      .borderColor(Color.WHITE)
      .focusColor(Color.LIGHTGRAY)
      .margin(25.0D)
      .placeholder("CONTENU...")
      .info(this.newMessageInputInfo)
      .onChange((field, oldText, newText) -> this.contentSignal.set(field.getText())))
      
      .attach((Node)this);
    IconButtonNode.create(35.0D, 736.0D, 22.0D)
      .icon(ZUIMailBox.TRASH_ICON)
      .padding(10.0D)
      .onClick((btn, mouseX, mouseY, clickType) -> {
          ZUIMailBox zui = (ZUIMailBox)getUi();
          zui.getTypeSignal().set(UIAction.NONE);
        }).attach((Node)this);
    TextButtonNode.create(862.0D, 736.0D)
      .icon(ZUIMailBox.WHITE_ENVELOPE_ICON)
      .iconSize(24.0D)
      .iconPosition(TextButtonNode.IconPosition.LEFT)
      .text("Envoyer")
      .onClick((btn, mouseX, mouseY, clickType) -> {
          if (!((String)this.objectSignal.getOrDefault()).isEmpty() && !((String)this.recipientSignal.getOrDefault()).isEmpty() && !((String)this.contentSignal.getOrDefault()).isEmpty()) {
            PMailbox.networkWrapper.sendToServer((IMessage)new MailboxServerMessagePacket(((String)this.objectSignal.getOrDefault()).trim(), ((String)this.contentSignal.getOrDefault()).trim(), ((String)this.recipientSignal.getOrDefault()).trim()));
            ZUIMailBox zui = (ZUIMailBox)getUi();
            zui.getTypeSignal().set(UIAction.NONE);
          } 
        }).attach((Node)this);
  }
  
  public static NewMessageContainer create(double x, double y, double width, double height) {
    return new NewMessageContainer(x, y, width, height);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\clien\\ui\container\NewMessageContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */