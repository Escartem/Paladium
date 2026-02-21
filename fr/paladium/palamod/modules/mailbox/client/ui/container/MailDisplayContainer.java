package fr.paladium.palamod.modules.mailbox.client.ui.container;

import fr.paladium.palamod.modules.mailbox.client.ui.UIAction;
import fr.paladium.palamod.modules.mailbox.client.ui.ZUIMailBox;
import fr.paladium.palamod.modules.mailbox.client.ui.nodes.MailDisplayNode;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import lombok.NonNull;

public class MailDisplayContainer extends RectNode {
  protected MailDisplayContainer(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    ZUIMailBox mailUi = (ZUIMailBox)getUi();
    Signal<UIAction> typeSignal = mailUi.getTypeSignal();
    clearChildren();
    if (UIAction.NEWMESSAGE.equals(typeSignal.getOrDefault())) {
      NewMessageContainer.create(0.0D, 0.0D, getWidth(), getHeight())
        .attach((Node)this);
      return;
    } 
    MailDisplayNode.create(0.0D, 0.0D, getWidth(), getHeight())
      .attach((Node)this);
  }
  
  public static MailDisplayContainer create(double x, double y, double width, double height) {
    return new MailDisplayContainer(x, y, width, height);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\clien\\ui\container\MailDisplayContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */