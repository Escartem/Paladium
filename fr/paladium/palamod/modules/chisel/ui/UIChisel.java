package fr.paladium.palamod.modules.chisel.ui;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.scrollbar.ScrollbarNode;
import fr.paladium.paladiumui.kit.slot.SlotNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palamod.modules.chisel.inventory.ChiselContainer;
import fr.paladium.palamod.modules.chisel.inventory.ChiselInventory;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.scrollbar.ScrollbarNode;
import fr.paladium.zephyrui.lib.ui.node.property.overflow.OverflowProperty;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.resource.ResourceUtils;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;

@UIData(container = true)
public class UIChisel extends UI {
  private static final ResourceLocation CHISEL_LOGO = ResourceUtils.get("palamod", "textures/gui/chisel_logo.png");
  
  public UIChisel(ChiselInventory chiselInventory) {
    super((Container)new ChiselContainer((Minecraft.func_71410_x()).field_71439_g.field_71071_by, chiselInventory));
  }
  
  public void init() {
    BackgroundNode.create(760.0D, 740.0D)
      .body(body -> {
          containerBounds(body.getAbsoluteX(), body.getAbsoluteY(), body.getWidth(), body.getHeight());
          TextNode.create(31.0D, 5.0D).text(Text.create("chisel", PaladiumText.HEADER)).attach(body);
          CloseButtonNode.create(body.getWidth() - 48.8D, 31.0D).onClick(()).attach(body);
          RectNode.create(180.0D, 120.0D, 500.0D, 311.0D).color(Color.BLACK.copyAlpha(0.15F)).body(()).attach(body);
          ImageNode.create(81.0D, 195.0D, 46.0D, 46.0D).resource(CHISEL_LOGO).attach(body);
          List<Slot> slots = (getContainer()).field_75151_b;
          slots.forEach(());
        }).attach(this);
  }
  
  private List<Slot> getChiselSlot() {
    List<Slot> slots = (getContainer()).field_75151_b;
    return (List<Slot>)slots.stream().filter(slot -> slot.field_75224_c instanceof ChiselInventory).collect(Collectors.toList());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\chise\\ui\UIChisel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */