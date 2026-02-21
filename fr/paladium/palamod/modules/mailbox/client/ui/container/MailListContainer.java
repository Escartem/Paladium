package fr.paladium.palamod.modules.mailbox.client.ui.container;

import fr.paladium.paladiumui.kit.button.TextButtonNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.scrollbar.ScrollbarNode;
import fr.paladium.paladiumui.kit.textfield.SearchTextFieldNode;
import fr.paladium.palamod.modules.mailbox.client.pojo.Mail;
import fr.paladium.palamod.modules.mailbox.client.ui.UIAction;
import fr.paladium.palamod.modules.mailbox.client.ui.ZUIMailBox;
import fr.paladium.palamod.modules.mailbox.client.ui.nodes.ZMailNode;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.scrollbar.ScrollbarNode;
import fr.paladium.zephyrui.lib.ui.node.property.overflow.OverflowProperty;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.ISignal;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.ListSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.StringSignal;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;

public class MailListContainer extends RectNode {
  private final StringSignal filterSignal = new StringSignal("");
  
  protected MailListContainer(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    ZUIMailBox mailUi = (ZUIMailBox)getUi();
    Signal<UIAction> typeSignal = mailUi.getTypeSignal();
    Signal<Mail> selectedMailSignal = mailUi.getSelectedMail();
    ListSignal<Mail> mailSignal = mailUi.getMailSignal();
    TextButtonNode.create(27.0D, 29.0D)
      .icon(ZUIMailBox.PLUS_ICON)
      .iconPosition(TextButtonNode.IconPosition.LEFT)
      .iconMargin(17.0D)
      .text("nouveau")
      .height(42.0D)
      .onClick((btn, mouseX, mouseY, clickType) -> {
          typeSignal.silent();
          typeSignal.set(UIAction.NEWMESSAGE);
          selectedMailSignal.set(null);
        }).attach((Node)this);
    ((SearchTextFieldNode)SearchTextFieldNode.create(338.0D, 30.0D, 255.0D)
      .backgroundColor(Color.WHITE)
      .borderColor(Color.WHITE)
      .focusColor(Color.LIGHTGRAY)
      .info(TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 19.0F).color(Color.BLACK.copyAlpha(0.5F)))
      .marginLeft(33.0D)
      .placeholder("RECHERCHER...")
      .onEnter((field, text) -> this.filterSignal.set(field.getText())))
      
      .attach((Node)this);
    ContainerNode.create(27.0D, 105.0D, 566.0D, 699.0D)
      .overflow(OverflowProperty.SCROLL)
      .scrollbar((ScrollbarNode)ScrollbarNode.create(534.0D, 0.0D, 699.0D))
      .onInit(maillistContainer -> {
          maillistContainer.clearChildren();
          if (!mailSignal.isPresent()) {
            FlexNode.vertical(0.0D, 0.0D, 510.0D).margin(15.0D).body(()).attach(maillistContainer);
            return;
          } 
          List<Mail> mails = (List<Mail>)mailSignal.getOrDefault();
          if (!((String)this.filterSignal.getOrDefault()).isEmpty())
            mails = (List<Mail>)mails.stream().filter(()).collect(Collectors.toList()); 
          Iterator<Mail> mailIterator = mails.iterator();
          FlexNode.vertical(0.0D, 0.0D, 510.0D).margin(15.0D).body(()).attach(maillistContainer);
        }).watch((Signal)mailSignal)
      .watch(selectedMailSignal)
      .watch((Signal)this.filterSignal)
      .wait((ISignal)mailSignal)
      .attach((Node)this);
  }
  
  public static MailListContainer create(double x, double y, double width, double height) {
    return new MailListContainer(x, y, width, height);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\clien\\ui\container\MailListContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */