package fr.paladium.palamod.modules.mailbox.client.ui.nodes;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palamod.modules.mailbox.client.pojo.EnumMailType;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.modifier.TextModifier;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import lombok.NonNull;

public class MailTypeNode extends RectNode {
  private EnumMailType mailType;
  
  private String mailTypeText;
  
  public EnumMailType getMailType() {
    return this.mailType;
  }
  
  protected MailTypeNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    if (this.mailType == null)
      return; 
    FlexNode.horizontal(19.0D, dh(2.0D) - 19.0D, 38.0D)
      .align(Align.CENTER)
      .margin(18.0D)
      .body(row -> {
          ImageNode.create(0.0D, 0.0D, 38.0D, 38.0D).resource(this.mailType.getResource().getResource()).attach(row);
          TextNode.create(0.0D, row.dh(2.0D)).text(Text.create(this.mailTypeText, TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 20.0F, Color.WHITE), Align.START, Align.CENTER).modifier(TextModifier.UPPER_CASE)).attach(row);
        }).attach((Node)this);
  }
  
  public <T extends MailTypeNode> T mailType(EnumMailType mailType) {
    this.mailType = mailType;
    return (T)this;
  }
  
  public <T extends MailTypeNode> T typeText(String typeText) {
    this.mailTypeText = typeText;
    return (T)this;
  }
  
  public static MailTypeNode create(double x, double y, double width, double height) {
    return new MailTypeNode(x, y, width, height);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\clien\\ui\nodes\MailTypeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */