package fr.paladium.palamod.modules.paladium.client.profile.gui;

import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palamod.modules.paladium.common.items.LegendaryStone;
import fr.paladium.palamod.modules.paladium.common.legendarystone.dto.LegendaryStoneData;
import fr.paladium.profile.client.gui.UIProfile;
import fr.paladium.profile.common.module.AModule;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.item.ItemNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.grid.GridNode;
import fr.paladium.zephyrui.lib.utils.align.Align;

public class UIProfileLegendaryStone extends UIProfile<LegendaryStoneData> {
  public UIProfileLegendaryStone(AModule<?> module) {
    super(module);
  }
  
  public void onInit() {
    RectNode.create(264.0D, 290.0D, 1389.0D, 243.0D)
      .color(Color.BLACK.copyAlpha(0.2F))
      .attach((UI)this);
  }
  
  public void onPostInit() {
    LegendaryStoneData data = (LegendaryStoneData)getProfileData().getOrDefault();
    ContainerNode.create(264.0D, 290.0D, 1389.0D, 243.0D)
      .body(container -> GridNode.create(97.0D, 36.0D, container.aw(-194.0D), 0.0D).margin(49.0D).body(()).attach(container))
      
      .attach((UI)this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\profile\gui\UIProfileLegendaryStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */