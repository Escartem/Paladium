package fr.paladium.paladiumui.kit.popup;

import fr.paladium.paladiumui.kit.button.TextButtonNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.ui.core.data.popup.UIDataPopup;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import lombok.NonNull;
import net.minecraft.client.resources.I18n;

@UIDataPopup(active = true)
public class UIYesNoPopup extends UIPopup {
  private final Signal<String> description;
  
  private final Signal<String> yesText;
  
  private final Signal<String> noText;
  
  private Runnable yesCallback;
  
  private Runnable noCallback;
  
  private boolean yesClicked;
  
  public Signal<String> getDescription() {
    return this.description;
  }
  
  public Signal<String> getYesText() {
    return this.yesText;
  }
  
  public Signal<String> getNoText() {
    return this.noText;
  }
  
  public Runnable getYesCallback() {
    return this.yesCallback;
  }
  
  public Runnable getNoCallback() {
    return this.noCallback;
  }
  
  public boolean isYesClicked() {
    return this.yesClicked;
  }
  
  public UIYesNoPopup(@NonNull String title, String description) {
    super(title);
    if (title == null)
      throw new NullPointerException("title is marked non-null but is null"); 
    this.description = Signal.of(description);
    this.yesText = Signal.of(I18n.func_135052_a("ui.popup.yes", new Object[0]));
    this.noText = Signal.of(I18n.func_135052_a("ui.popup.no", new Object[0]));
  }
  
  public void init() {
    super.init();
    getBody().body(body -> {
          ((TextNode)TextNode.create(0.0D, 0.0D).text(Text.create(this.description.getOrDefault(), PaladiumText.SUB_TITLE, Align.CENTER)).mode(TextMode.SPLIT).onInit(())).width(body.getWidth()).watch(this.description).attach(body);
          FlexNode.horizontal(0.0D, 0.0D, 50.0D).margin(40.0D).align(Align.CENTER).body(()).attach(body);
        });
  }
  
  public boolean close() {
    if (this.yesClicked && this.yesCallback != null) {
      this.yesCallback.run();
    } else if (!this.yesClicked && this.noCallback != null) {
      this.noCallback.run();
    } 
    return super.close();
  }
  
  @NonNull
  public UIYesNoPopup description(@NonNull String description) {
    if (description == null)
      throw new NullPointerException("description is marked non-null but is null"); 
    this.description.set(description);
    return this;
  }
  
  @NonNull
  public UIYesNoPopup yesCallback(@NonNull Runnable yesCallback) {
    if (yesCallback == null)
      throw new NullPointerException("yesCallback is marked non-null but is null"); 
    this.yesCallback = yesCallback;
    return this;
  }
  
  @NonNull
  public UIYesNoPopup noCallback(@NonNull Runnable noCallback) {
    if (noCallback == null)
      throw new NullPointerException("noCallback is marked non-null but is null"); 
    this.noCallback = noCallback;
    return this;
  }
  
  @NonNull
  public UIYesNoPopup yesText(@NonNull String yesText) {
    if (yesText == null)
      throw new NullPointerException("yesText is marked non-null but is null"); 
    this.yesText.set(yesText);
    return this;
  }
  
  @NonNull
  public UIYesNoPopup noText(@NonNull String noText) {
    if (noText == null)
      throw new NullPointerException("noText is marked non-null but is null"); 
    this.noText.set(noText);
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\paladiumui\kit\popup\UIYesNoPopup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */