package fr.paladium.palamod.modules.paladium.client.ui;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.slot.SlotNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palamod.modules.paladium.common.container.ModdedChestContainer;
import fr.paladium.palamod.modules.paladium.common.logics.ModdedChestLogic;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.resource.ResourceUtils;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

@UIData(background = false, container = true)
@UIDataGuiscale(active = true)
public class UIModdedChest extends UI {
  private static final Resource RING_PLACEHOLDER = Resource.of(ResourceUtils.get("palamod", "textures/gui/ring_placeholder.png"));
  
  private final ModdedChestLogic tile;
  
  public UIModdedChest(ModdedChestLogic chestLogic) {
    super((Container)new ModdedChestContainer(chestLogic, (Minecraft.func_71410_x()).field_71439_g.field_71071_by));
    this.tile = chestLogic;
  }
  
  public void init() {
    BackgroundNode.create(990.0D, 1005.0D)
      .body(body -> {
          containerBounds(body.getAbsoluteX(), body.getAbsoluteY(), body.getWidth(), body.getHeight());
          TextNode.create(31.0D, 5.0D).text(Text.create(this.tile.func_145825_b(), PaladiumText.HEADER.copy().color(this.tile.getFontColors()[0]).shadow(this.tile.getFontColors()[1]))).attach(body);
          CloseButtonNode.create(body.getWidth() - 48.8D, 31.0D).onClick(()).attach(body);
          List<Slot> slots = (getContainer()).field_75151_b;
          slots.forEach(());
        }).attach(this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\clien\\ui\UIModdedChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */