package fr.paladium.palamod.modules.mailbox.client.ui.container;

import fr.paladium.paladiumui.kit.checkbox.CheckboxNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.textfield.IntegerFieldNode;
import fr.paladium.palamod.modules.mailbox.client.pojo.EnumMailType;
import fr.paladium.palamod.modules.mailbox.client.ui.ZUIMailBoxSendAdminMail;
import fr.paladium.palamod.modules.mailbox.client.ui.nodes.MailTypeNode;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.modifier.TextModifier;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.grid.GridNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.resource.ResourceUtils;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import lombok.NonNull;

public class AdminSettingsContainer extends RectNode {
  private static final Resource SETTINGS_BADGE = Resource.of(ResourceUtils.get("palamod", "textures/gui/mailbox/parameter_badge.png"));
  
  private static final Resource CHEST_BADGE = Resource.of(ResourceUtils.get("palamod", "textures/gui/mailbox/chest_badge.png"));
  
  private static final Resource CLOCK_ICON = Resource.of(ResourceUtils.get("palamod", "textures/gui/mailbox/clock_icon.png"));
  
  private final Color checkColor = new Color(21, 70, 171);
  
  protected AdminSettingsContainer(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    ZUIMailBoxSendAdminMail aUi = (ZUIMailBoxSendAdminMail)getUi();
    FlexNode.horizontal(48.0D, 31.0D, 38.0D)
      .margin(10.0D)
      .body(row -> {
          ImageNode.create(0.0D, 0.0D, 39.4D, 38.0D).resource(SETTINGS_BADGE).attach(row);
          TextNode.create(0.0D, row.dh(2.0D)).text(Text.create("Paramètres", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 35.0F, Color.WHITE))).anchorY(Align.CENTER).attach(row);
        }).attach((Node)this);
    ((IntegerFieldNode)IntegerFieldNode.create(48.0D, 112.0D, 529.0D)
      .backgroundColor(Color.WHITE)
      .borderColor(Color.WHITE)
      .focusColor(Color.LIGHTGRAY)
      .min(-1)
      .max(365)
      .margin(75.0D)
      .placeholder("temps avant expiration...")
      .info(TextInfo.create((IFont)PaladiumFont.MONTSERRAT_SEMI_BOLD, 19.0F))
      .onChange((field, oldText, newText) -> aUi.getExpirationDateSignal().set(Integer.valueOf(field.getValue()))))
      
      .body(field -> ImageNode.create(32.0D, field.dh(2.0D) - 15.0D).resource(CLOCK_ICON).size(30.0D, 30.0D).attach(field))
      
      .attach((Node)this);
    ((CheckboxNode)CheckboxNode.create(48.0D, 196.0D, 30.0D)
      .checkedBackgroundColor(this.checkColor)
      .uncheckedBackgroundColor(this.checkColor)
      .onChange((check, checked) -> {
          aUi.getEveryoneSignal().set(Boolean.valueOf(check.isChecked()));
          aUi.getRecipientField().text(check.isChecked() ? "@everyone" : "").enabled(());
          aUi.getRecipientSignal().set(check.isChecked() ? "@everyone" : "");
        })).body(check -> TextNode.create(50.0D, -1.0D).text(Text.create("Envoyer à tous", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 25.0F).color(Color.WHITE))).attach(check))
      
      .attach((Node)this);
    ((CheckboxNode)CheckboxNode.create(48.0D, 255.0D, 30.0D)
      .checkedBackgroundColor(this.checkColor)
      .uncheckedBackgroundColor(this.checkColor)
      .onChange((check, checked) -> aUi.getPaladiumSignal().set(Boolean.valueOf(check.isChecked()))))
      
      .body(check -> TextNode.create(50.0D, -1.0D).text(Text.create("email paladium", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 25.0F).color(Color.WHITE)).modifier(TextModifier.UPPER_CASE)).attach(check))
      
      .attach((Node)this);
    RectNode.create(41.0D, 313.0D, 538.0D, 132.0D)
      .color(Color.WHITE.copyAlpha(0.15F))
      .body(rect -> GridNode.create(10.0D, 12.0D, 516.0D, 0.0D).verticalMargin(10.0D).horizontalMargin(7.0D).body(()).attach(rect))
      
      .attach((Node)this);
    FlexNode.horizontal(48.0D, 462.0D, 40.0D)
      .margin(20.0D)
      .body(row -> {
          ImageNode.create(0.0D, 0.0D, 40.0D, 40.0D).resource(CHEST_BADGE).attach(row);
          TextNode.create(0.0D, 0.0D).text(Text.create("Inventaire", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 35.0F, Color.WHITE))).attach(row);
        }).attach((Node)this);
  }
  
  public static AdminSettingsContainer create(double x, double y, double width, double height) {
    return new AdminSettingsContainer(x, y, width, height);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\clien\\ui\container\AdminSettingsContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */