package fr.paladium.palamod.modules.hunter.ui;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.slot.SlotNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palamod.modules.hunter.gui.containers.ContainerBackPack;
import fr.paladium.palamod.modules.hunter.gui.containers.InventoryBackPack;
import fr.paladium.translate.common.texttotranslate.TTT;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

@UIData(container = true)
@UIDataGuiscale(active = true)
public class UIBackPack extends UI {
  private final InventoryBackPack backpackInv;
  
  private final TextInfo titleTextInfo = PaladiumText.HEADER.copy().fontSize(40.0F);
  
  private final int numRows;
  
  private final double rowHeight;
  
  public UIBackPack(InventoryPlayer playerInv, InventoryBackPack backpackInv) {
    super((Container)new ContainerBackPack(playerInv, backpackInv));
    this.backpackInv = backpackInv;
    this.numRows = (int)Math.ceil((this.backpackInv.size / 9));
    this.rowHeight = 60.0D * this.numRows;
    if (this.backpackInv.func_145825_b().contains("backpack")) {
      String[] splittedName = this.backpackInv.func_145825_b().split("\\.");
      String backpackType = splittedName[splittedName.length - 1];
      switch (backpackType) {
        case "amethyste":
          this.titleTextInfo.color(new Color(183, 30, 255)).shadow(new Color(125, 0, 184));
          break;
        case "titane":
          this.titleTextInfo.color(new Color(165, 165, 165)).shadow(new Color(88, 88, 88));
          break;
        case "endium":
          this.titleTextInfo.color(new Color(37, 85, 255)).shadow(new Color(17, 0, 214));
          break;
      } 
    } 
  }
  
  public void init() {
    ContainerNode.create(0.0D, 0.0D, 1920.0D, 1080.0D)
      .body(container -> BackgroundNode.create(748.8D, 455.81D + this.rowHeight).body(()).attach(container))
      
      .attach(this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunte\\ui\UIBackPack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */