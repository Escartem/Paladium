package fr.paladium.palamod.modules.stats.profile.gui;

import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palamod.modules.stats.dto.PlayerStats;
import fr.paladium.palamod.modules.stats.profile.gui.node.ProfileStatsNode;
import fr.paladium.profile.client.gui.UIProfile;
import fr.paladium.profile.common.module.AModule;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import net.minecraft.util.ResourceLocation;

public class UIProfileStats extends UIProfile<PlayerStats> {
  public UIProfileStats(AModule<?> module) {
    super(module);
  }
  
  public void onInit() {
    RectNode.create(266.0D, 278.0D, 1389.0D, 324.0D)
      .color(Color.BLACK.copyAlpha(0.2F))
      .body(rect -> TextNode.create(36.0D, 23.0D).text(Text.create("distance", PaladiumText.TITLE)).attach(rect))
      
      .attach((UI)this);
    RectNode.create(266.0D, 622.0D, 1389.0D, 324.0D)
      .color(Color.BLACK.copyAlpha(0.2F))
      .body(rect -> TextNode.create(36.0D, 23.0D).text(Text.create("pillage", PaladiumText.TITLE)).attach(rect))
      
      .attach((UI)this);
  }
  
  public void onPostInit() {
    PlayerStats data = (PlayerStats)getProfileData().getOrDefault();
    ContainerNode.create(266.0D, 278.0D, 1389.0D, 324.0D)
      .body(container -> FlexNode.horizontal(90.0D, 80.0D, container.ah(-90.0D)).margin(103.0D).body(()).attach(container))
      
      .attach((UI)this);
    ContainerNode.create(266.0D, 622.0D, 1389.0D, 324.0D)
      .body(container -> FlexNode.horizontal(90.0D, 80.0D, container.ah(-90.0D)).margin(103.0D).body(()).attach(container))
      
      .attach((UI)this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\stats\profile\gui\UIProfileStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */