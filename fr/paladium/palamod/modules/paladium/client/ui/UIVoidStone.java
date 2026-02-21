package fr.paladium.palamod.modules.paladium.client.ui;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.slot.SlotNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palamod.modules.paladium.common.container.VoidStoneContainer;
import fr.paladium.palamod.modules.paladium.common.inventory.VoidStoneInventory;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.resource.ResourceUtils;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;

@UIData(background = true, container = true)
@UIDataGuiscale(active = true)
public class UIVoidStone extends UI {
  private static final ResourceLocation VOID_BACKGROUND = ResourceUtils.get("palamod", "textures/gui/void_slot_background.png");
  
  public UIVoidStone() {
    super((Container)new VoidStoneContainer((Minecraft.func_71410_x()).field_71439_g.field_71071_by, new VoidStoneInventory()));
  }
  
  public void init() {
    BackgroundNode.create(750.0D, 618.0D)
      .body(body -> {
          containerBounds(body.getAbsoluteX(), body.getAbsoluteY(), body.getWidth(), body.getHeight());
          TextNode.create(31.0D, 5.0D).text(Text.create("voidstone", PaladiumText.HEADER.copy().color(new Color(23, 148, 127)).shadow(new Color(13, 89, 77)))).attach(body);
          CloseButtonNode.create(body.getWidth() - 48.8D, 31.0D).onClick(()).attach(body);
          ImageNode.create(body.getWidth() / 2.0D - 62.0D, 144.0D, 124.0D, 124.0D).resource(VOID_BACKGROUND).attach(body);
          List<Slot> slots = (getContainer()).field_75151_b;
          slots.forEach(());
        }).attach(this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\clien\\ui\UIVoidStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */