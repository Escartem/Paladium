package fr.paladium.paladiumui.kit.popup;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.utils.TextOverflow;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.popup.UIDataPopup;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import lombok.NonNull;

@UIDataPopup(active = true)
public class UIPopup extends UI {
  private final Signal<String> title;
  
  private Node background;
  
  private Node body;
  
  public Signal<String> getTitle() {
    return this.title;
  }
  
  public Node getBackground() {
    return this.background;
  }
  
  public Node getBody() {
    return this.body;
  }
  
  public UIPopup(@NonNull String title) {
    if (title == null)
      throw new NullPointerException("title is marked non-null but is null"); 
    this.title = Signal.of(title);
  }
  
  public void init() {
    this
      
      .background = BackgroundNode.create(931.0D, 180.0D).body(background -> {
          ((TextNode)TextNode.create(13.5D, 55.0D, background.aw(-27.0D), 0.0D).text(Text.create(this.title.getOrDefault(), PaladiumText.TITLE, Align.CENTER).overflow(TextOverflow.ELLIPSIS)).mode(TextMode.OVERFLOW).onInit(())).watch(this.title).attach(background);
          CloseButtonNode.create(background.aw(-35.0D), 15.0D).onClick(()).attach(background);
          this.body = FlexNode.vertical(27.0D, 150.0D, background.aw(-54.0D)).margin(50.0D).align(Align.CENTER).attach(background);
        }).attach(this);
  }
  
  public void update() {
    double bodyHeight = this.body.getHeight();
    this.background.height((bodyHeight <= 0.0D) ? 130.0D : (180.0D + bodyHeight));
  }
  
  @NonNull
  public UIPopup title(@NonNull String title) {
    if (title == null)
      throw new NullPointerException("title is marked non-null but is null"); 
    this.title.set(title);
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\kit\popup\UIPopup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */