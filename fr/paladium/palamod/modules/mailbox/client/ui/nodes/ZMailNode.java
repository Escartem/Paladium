package fr.paladium.palamod.modules.mailbox.client.ui.nodes;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palamod.modules.mailbox.client.pojo.Mail;
import fr.paladium.palamod.modules.mailbox.client.ui.MailboxResource;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.head.HeadNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import lombok.NonNull;

public class ZMailNode extends RectNode {
  private String playerName = "";
  
  private boolean selected = false;
  
  private final TextInfo playerNameInfo = TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 34.0F).color(Color.WHITE);
  
  private final TextInfo objectInfo = TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 20.0F).color(Color.WHITE);
  
  private boolean paladium;
  
  private Mail mail;
  
  protected ZMailNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    if (this.playerName.isEmpty() || this.mail == null)
      return; 
    if (!this.paladium) {
      HeadNode.create(14.0D, 12.0D, 68.0D)
        .player(this.playerName)
        .attach((Node)this);
    } else {
      ImageNode.create(14.0D, 12.0D, 68.0D, 68.0D)
        .resource(MailboxResource.PALA_ICON.getResource())
        .attach((Node)this);
    } 
    TextNode.create(96.0D, 12.0D)
      .text(Text.create(this.paladium ? "paladium" : this.playerName, this.playerNameInfo))
      .attach((Node)this);
    TextNode.create(96.0D, 52.0D)
      .text(Text.create(this.mail.getObject(), this.objectInfo))
      .attach((Node)this);
    ImageNode.create(getWidth() - 12.0D - 41.0D, 12.0D, 41.0D, 41.0D)
      .resource(this.mail.getMailType().getResource().getResource())
      .attach((Node)this);
  }
  
  public static ZMailNode create(double x, double y, double width, double height) {
    return new ZMailNode(x, y, width, height);
  }
  
  public <T extends ZMailNode> T senderName(String playerName) {
    this.playerName = playerName;
    return (T)this;
  }
  
  public <T extends ZMailNode> T paladium(boolean paladium) {
    this.paladium = paladium;
    return (T)this;
  }
  
  public <T extends ZMailNode> T selected(boolean selected) {
    this.selected = selected;
    if (selected) {
      this.playerNameInfo.color(new Color(43, 103, 255));
      this.objectInfo.color(new Color(43, 103, 255));
    } 
    return (T)this;
  }
  
  public <T extends ZMailNode> T mail(Mail mail) {
    this.mail = mail;
    return (T)this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\clien\\ui\nodes\ZMailNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */