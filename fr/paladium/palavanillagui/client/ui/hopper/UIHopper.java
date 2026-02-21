package fr.paladium.palavanillagui.client.ui.hopper;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.slot.SlotNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import java.util.List;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

@UIData(container = true, background = true)
@UIDataGuiscale(active = true)
public class UIHopper extends UI {
  public UIHopper(Container container) {
    super(container);
  }
  
  public void init() {
    BackgroundNode.create(625.0D, 670.0D)
      .body(body -> {
          containerBounds(body.getAbsoluteX(), body.getAbsoluteY(), body.getWidth(), body.getHeight());
          TextNode.create(31.0D, 5.0D).text(Text.create("hopper", PaladiumText.HEADER.copy())).attach(body);
          CloseButtonNode.create(body.getWidth() - 48.8D, 31.0D).onClick(()).attach(body);
          List<Slot> slots = (getContainer()).field_75151_b;
          double margin = (body.getWidth() - 540.0D) / 2.0D;
          slots.forEach(());
        }).attach(this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\clien\\ui\hopper\UIHopper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */