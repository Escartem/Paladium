package fr.paladium.palamod.modules.mailbox.client.ui.nodes;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.paladiumui.kit.button.ButtonNode;
import fr.paladium.paladiumui.kit.button.IconButtonNode;
import fr.paladium.paladiumui.kit.button.TextButtonNode;
import fr.paladium.paladiumui.kit.constant.ResourceConstant;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.scrollbar.ScrollbarNode;
import fr.paladium.palamod.modules.mailbox.PMailbox;
import fr.paladium.palamod.modules.mailbox.client.pojo.EnumMailType;
import fr.paladium.palamod.modules.mailbox.client.pojo.Mail;
import fr.paladium.palamod.modules.mailbox.client.ui.MailboxResource;
import fr.paladium.palamod.modules.mailbox.client.ui.ZUIMailBox;
import fr.paladium.palamod.modules.mailbox.common.packets.server.EnumMailActions;
import fr.paladium.palamod.modules.mailbox.common.packets.server.MailboxServerMessagePacket;
import fr.paladium.palamod.modules.mailbox.common.packets.server.MailboxServerProcessMailPacket;
import fr.paladium.palamod.util.ItemStackSerializer;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.head.HeadNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.item.ItemNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.textfield.MultilineTextFieldNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.scrollbar.ScrollbarNode;
import fr.paladium.zephyrui.lib.ui.node.property.overflow.OverflowProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.StringSignal;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.Iterator;
import lombok.NonNull;
import net.minecraft.item.ItemStack;

public class MailDisplayNode extends ContainerNode {
  private final StringSignal answerContentSignal = new StringSignal("");
  
  private MultilineTextFieldNode multlinenode;
  
  protected MailDisplayNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    ZUIMailBox mailUi = (ZUIMailBox)getUi();
    Signal<Mail> selectedMailSignal = mailUi.getSelectedMail();
    if (selectedMailSignal.getOrDefault() == null)
      return; 
    Mail selectedMail = (Mail)selectedMailSignal.getOrDefault();
    if (selectedMail.isPaladium()) {
      ImageNode.create(36.0D, 36.0D, 135.0D, 135.0D)
        .resource(MailboxResource.PALA_ICON.getResource())
        .attach((Node)this);
    } else {
      HeadNode.create(36.0D, 36.0D, 135.0D)
        .player(selectedMail.getSenderName())
        .attach((Node)this);
    } 
    TextNode.create(227.0D, 36.0D)
      .text(Text.create(selectedMail.isPaladium() ? "paladium" : selectedMail.getSenderName(), TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 64.0F, Color.WHITE)))
      .attach((Node)this);
    TextNode.create(227.0D, 114.0D)
      .text(Text.create(selectedMail.getObject(), TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 32.0F, Color.WHITE)))
      .attach((Node)this);
    ImageNode.create(134.0D, 135.0D, 73.0D, 73.0D)
      .resource(ZUIMailBox.MESSAGE_BADGE)
      .attach((Node)this);
    TextNode.create(1065.0D, 52.0D)
      .text(Text.create(ZUIMailBox.DATE_FORMATTER.format(Date.from(Instant.parse(selectedMail.getDate()))).replace(":", "h"), TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 24.0F, Color.WHITE)))
      .anchorX(Align.END)
      .attach((Node)this);
    Duration res = Duration.between(Instant.now(), Instant.parse(selectedMail.getExpireDate()));
    int days = (int)(res.getSeconds() / 3600L / 24L);
    long HH = res.getSeconds() / 3600L - (days * 24);
    long MM = res.getSeconds() % 3600L / 60L;
    TextNode.create(1065.0D, 127.0D)
      .text(Text.create("Expire dans " + days + "J, " + HH + "h " + MM + "min", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_REGULAR, 20.0F, Color.WHITE)))
      .anchorX(Align.END)
      .attach((Node)this);
    RectNode.create(36.0D, 219.0D, 1029.0D, 293.0D)
      .color(new Color(61, 108, 231, 77))
      .body(messageContainer -> ContainerNode.create(33.0D, 18.0D, 975.0D, 252.0D).overflow(OverflowProperty.SCROLL).scrollbar((ScrollbarNode)ScrollbarNode.create(943.0D, 0.0D, 252.0D)).onInit(()).attach(messageContainer))
      
      .attach((Node)this);
    if (selectedMail.hasItems()) {
      ContainerNode.create(35.0D, 538.0D, 1029.0D, 164.0D)
        .body(itemContainer -> {
            Iterator<String> iterator = selectedMail.getEncodedItems().iterator();
            FlexNode.vertical(184.5D, 22.0D, 660.0D).body(()).attach(itemContainer);
          }).attach((Node)this);
      TextButtonNode.create(842.0D, 736.0D)
        .icon(ZUIMailBox.WHITE_ENVELOPE_ICON)
        .iconSize(24.0D)
        .iconPosition(TextButtonNode.IconPosition.LEFT)
        .text("Récuperer")
        .onClick((node, mouseX, mouseY, clickType) -> {
            PMailbox.networkWrapper.sendToServer((IMessage)new MailboxServerProcessMailPacket(selectedMail.getId().toString(), EnumMailActions.RECUP));
            mailUi.getSelectedMail().set(null);
            mailUi.getMailSignal().remove(mailUi.getMailSignal().indexOf(selectedMail));
          }).attach((Node)this);
    } else if (EnumMailType.INVITATION.equals(selectedMail.getMailType())) {
      TextButtonNode.create(640.0D, 736.0D)
        .text("Accepter")
        .iconPosition(TextButtonNode.IconPosition.LEFT)
        .color(ButtonNode.GREEN_COLOR)
        .onClick((node, mouseX, mouseY, clickType) -> {
            PMailbox.networkWrapper.sendToServer((IMessage)new MailboxServerProcessMailPacket(selectedMail.getId().toString(), EnumMailActions.ACCEPT));
            mailUi.getSelectedMail().set(null);
            mailUi.getMailSignal().remove(mailUi.getMailSignal().indexOf(selectedMail));
          }).attach((Node)this);
      TextButtonNode.create(862.0D, 736.0D)
        .text("Refuser")
        .iconPosition(TextButtonNode.IconPosition.LEFT)
        .onClick((node, mouseX, mouseY, clickType) -> {
            PMailbox.networkWrapper.sendToServer((IMessage)new MailboxServerProcessMailPacket(selectedMail.getId().toString(), EnumMailActions.DENY));
            mailUi.getSelectedMail().set(null);
            mailUi.getMailSignal().remove(mailUi.getMailSignal().indexOf(selectedMail));
          }).attach((Node)this);
    } else {
      this
        
        .multlinenode = (MultilineTextFieldNode)MultilineTextFieldNode.create(35.0D, 538.0D, 1029.0D, 164.0D).<MultilineTextFieldNode>backgroundColor(Color.WHITE).<MultilineTextFieldNode>borderColor(Color.WHITE).<MultilineTextFieldNode>focusColor(Color.LIGHTGRAY).info(TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 19.0F).color(Color.BLACK)).marginLeft(33.0D).marginTop(10.0D).onChange((node, oldText, newText) -> this.answerContentSignal.set(newText)).attach((Node)this);
      TextButtonNode.create(862.0D, 736.0D)
        .icon(ZUIMailBox.WHITE_ENVELOPE_ICON)
        .iconSize(24.0D)
        .iconPosition(TextButtonNode.IconPosition.LEFT)
        .text("Envoyer")
        .onClick((node, mouseX, mouseY, clickType) -> {
            Mail mail = (Mail)selectedMailSignal.getOrDefault();
            if (!((String)this.answerContentSignal.getOrDefault()).isEmpty()) {
              PMailbox.networkWrapper.sendToServer((IMessage)new MailboxServerMessagePacket(mail.getObject(), ((String)this.answerContentSignal.getOrDefault()).trim(), mail.getSenderName()));
              this.multlinenode.text("");
            } 
          }).attach((Node)this);
    } 
    IconButtonNode.create(35.0D, 736.0D, 22.0D)
      .icon(ZUIMailBox.TRASH_ICON)
      .padding(10.0D)
      .onClick((node, mouseX, mouseY, clickType) -> {
          PMailbox.networkWrapper.sendToServer((IMessage)new MailboxServerProcessMailPacket(selectedMail.getId().toString(), EnumMailActions.DELETE));
          mailUi.getSelectedMail().set(null);
          mailUi.getMailSignal().remove(mailUi.getMailSignal().indexOf(selectedMail));
        }).enabled(n -> !(selectedMail.isPaladium() && !selectedMail.getEncodedItems().isEmpty()))
      .attach((Node)this);
  }
  
  public static MailDisplayNode create(double x, double y, double width, double height) {
    return new MailDisplayNode(x, y, width, height);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\clien\\ui\nodes\MailDisplayNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */