package fr.paladium.palarpg.module.dungeon.client.ui.popup;

import fr.paladium.paladiumui.kit.button.TextButtonNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palarpg.client.ui.kit.background.BackgroundNode;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.utils.TextOverflow;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.popup.UIDataPopup;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import java.util.function.Consumer;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

@UIData(zlevel = 500.0D)
@UIDataPopup(active = true)
public class UIDungeonWarningPopup extends UI {
  private final String title;
  
  private final String description;
  
  private final Boolean cancellable;
  
  private Consumer<UIDungeonWarningPopup> callback;
  
  public UIDungeonWarningPopup(String title, String description, Boolean cancellable) {
    this.title = title;
    this.description = description;
    this.cancellable = cancellable;
  }
  
  public String getTitle() {
    return this.title;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public Boolean getCancellable() {
    return this.cancellable;
  }
  
  public Consumer<UIDungeonWarningPopup> getCallback() {
    return this.callback;
  }
  
  public void init() {
    BackgroundNode.create(541.0D, 306.0D, 837.0D, 469.0D)
      .attach(this);
    ImageNode.create(923.0D, 374.0D, 74.0D, 74.0D)
      .resource(Resource.of(new ResourceLocation("palarpg", "textures/gui/popup/warning.png")).nearest())
      .attach(this);
    TextNode.create(610.0D, 446.0D)
      .text(Text.create(this.title, TextInfo.create((IFont)PaladiumFont.MINECRAFT_DUNGEONS, 30.0F, Color.WHITE)).horizontalAlign(Align.CENTER).overflow(TextOverflow.ELLIPSIS))
      .mode(TextMode.OVERFLOW)
      .width(700.0D)
      .attach(this);
    TextNode.create(705.0D, 536.0D)
      .text(Text.create(this.description, TextInfo.create((IFont)PaladiumFont.MONTSERRAT_MEDIUM, 14.0F, Color.decode("#6A6A6A"))).horizontalAlign(Align.CENTER))
      .mode(TextMode.SPLIT)
      .width(510.0D)
      .attach(this);
    FlexNode.horizontal(960.0D, 640.0D, 42.0D)
      .margin(20.0D)
      .body(flex -> {
          TextButtonNode.create(0.0D, 0.0D).text("valider").onClick(()).attach(flex);
          if (this.cancellable.booleanValue())
            TextButtonNode.create(0.0D, 0.0D).text("annuler").showBackground(false).onClick(()).attach(flex); 
        }).anchorX(Align.CENTER)
      .attach(this);
  }
  
  @NonNull
  public UIDungeonWarningPopup callback(Consumer<UIDungeonWarningPopup> callback) {
    this.callback = callback;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\popup\UIDungeonWarningPopup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */