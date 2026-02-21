package fr.paladium.palamod.modules.luckyblock.profile.gui;

import fr.paladium.paladiumui.kit.scrollbar.ScrollbarNode;
import fr.paladium.palamod.modules.luckyblock.profile.dto.LuckyBlock;
import fr.paladium.palamod.modules.luckyblock.profile.dto.LuckyBlockData;
import fr.paladium.palamod.modules.luckyblock.profile.gui.node.ProfileLuckyBlockNode;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyEvents;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyType;
import fr.paladium.profile.client.gui.UIProfile;
import fr.paladium.profile.common.module.AModule;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.grid.GridNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.scrollbar.ScrollbarNode;
import fr.paladium.zephyrui.lib.ui.node.property.overflow.OverflowProperty;
import java.util.Arrays;
import java.util.List;

public class UIProfileLuckyBlock extends UIProfile<LuckyBlockData> {
  public UIProfileLuckyBlock(AModule<?> module) {
    super(module);
  }
  
  public void onInit() {
    RectNode.create(271.0D, 293.0D, 1378.0D, 642.0D)
      .color(Color.BLACK.copyAlpha(0.2F))
      .attach((UI)this);
  }
  
  public void onPostInit() {
    LuckyBlockData data = (LuckyBlockData)getProfileData().getOrDefault();
    ContainerNode.create(271.0D, 293.0D, 1378.0D, 642.0D)
      .overflow(OverflowProperty.SCROLL)
      .scrollbar((ScrollbarNode)ScrollbarNode.create(1323.0D, 34.0D, 578.0D))
      .body(container -> GridNode.create(85.0D, 34.0D, container.aw(-170.0D), 0.0D).horizontalMargin(130.0D).verticalMargin(43.0D).body(()).attach(container))
      
      .attach((UI)this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\profile\gui\UIProfileLuckyBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */