package fr.paladium.palaclicker.client.ui.popup;

import fr.paladium.paladiumui.kit.button.TextButtonNode;
import fr.paladium.paladiumui.kit.popup.UIPopup;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.popup.UIDataPopup;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import lombok.NonNull;

@UIData(zlevel = 100.0D)
@UIDataPopup(active = true)
public class UIClickerPopup extends UIPopup {
  private final String description;
  
  public String getDescription() {
    return this.description;
  }
  
  public UIClickerPopup(@NonNull String title, String description) {
    super(title);
    if (title == null)
      throw new NullPointerException("title is marked non-null but is null"); 
    this.description = description;
  }
  
  public void init() {
    super.init();
    getBody().body(body -> {
          TextNode.create(body.dw(2.0D), 0.0D).text(Text.create(this.description, PaladiumText.SUB_TITLE, Align.CENTER)).mode(TextMode.SPLIT).width(body.getWidth()).attach(body);
          FlexNode.horizontal(body.dw(2.0D), 0.0D, 60.0D).margin(40.0D).align(Align.CENTER).body(()).attach(body);
        });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\clien\\ui\popup\UIClickerPopup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */