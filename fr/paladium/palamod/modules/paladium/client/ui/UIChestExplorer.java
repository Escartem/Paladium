package fr.paladium.palamod.modules.paladium.client.ui;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.slot.SlotNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palamod.modules.paladium.common.container.ChestExplorerContainer;
import fr.paladium.palamod.modules.paladium.common.inventory.ChestExplorerInventory;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;

@UIData(background = false, container = true)
@UIDataGuiscale(active = true)
public class UIChestExplorer extends UI {
  public UIChestExplorer(TileEntity tile, ChestExplorerInventory inventory) {
    super((Container)new ChestExplorerContainer(tile, inventory, (Minecraft.func_71410_x()).field_71439_g.field_71071_by));
  }
  
  public void init() {
    BackgroundNode background = (BackgroundNode)BackgroundNode.create(935.0D, 1005.0D).body(body -> {
          TextNode.create(31.0D, 5.0D).text(Text.create("Chest explorer", PaladiumText.HEADER)).attach(body);
          CloseButtonNode.create(body.getWidth() - 48.8D, 31.0D).onClick(()).attach(body);
          List<Slot> slots = (getContainer()).field_75151_b;
          slots.forEach(());
        }).attach(this);
    containerBounds(background.getAbsoluteX(), background.getAbsoluteY(), background.getWidth(), background.getHeight());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\clien\\ui\UIChestExplorer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */