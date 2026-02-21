package fr.paladium.palamod.modules.palaboss.client.profile.gui;

import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.scrollbar.ScrollbarNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palajobs.api.type.JobType;
import fr.paladium.palajobs.core.pojo.boss.BossPlayerStat;
import fr.paladium.palamod.modules.palaboss.client.profile.gui.node.ProfileBossNode;
import fr.paladium.palamod.modules.palaboss.common.boss.BossAttributes;
import fr.paladium.palamod.modules.palaboss.common.boss.BossRegistry;
import fr.paladium.palamod.modules.palaboss.common.eep.BossPlayerStat;
import fr.paladium.palamod.modules.palaboss.profile.dto.PlayerBossData;
import fr.paladium.profile.client.gui.UIProfile;
import fr.paladium.profile.common.module.AModule;
import fr.paladium.translate.common.texttotranslate.TTT;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.grid.GridNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.scrollbar.ScrollbarNode;
import fr.paladium.zephyrui.lib.ui.node.property.overflow.OverflowProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.format.FormatUtils;
import java.util.Arrays;
import java.util.List;
import net.minecraft.util.ResourceLocation;

public class UIProfileBoss extends UIProfile<PlayerBossData> {
  public UIProfileBoss(AModule<?> module) {
    super(module);
  }
  
  public void onInit() {
    RectNode.create(243.0D, 168.0D, 1434.0D, 378.0D)
      .color(Color.BLACK.copyAlpha(0.2F))
      .attach((UI)this);
    RectNode.create(243.0D, 569.0D, 1434.0D, 378.0D)
      .color(Color.BLACK.copyAlpha(0.2F))
      .body(rect -> TextNode.create(23.0D, -5.0D).text(Text.create("boss métier", PaladiumText.HEADER)).attach(rect))
      
      .attach((UI)this);
  }
  
  public void onPostInit() {
    PlayerBossData data = (PlayerBossData)getProfileData().getOrDefault();
    ContainerNode.create(271.0D, 298.0D, 1350.0D, 248.0D)
      .overflow(OverflowProperty.SCROLL)
      .scrollbar((ScrollbarNode)ScrollbarNode.create(1360.0D, 24.0D, 200.0D))
      .body(container -> GridNode.create(0.0D, 0.0D, container.getWidth(), 0.0D).horizontalMargin(88.0D).verticalMargin(30.0D).body(()).attach(container))
      
      .attach((UI)this);
    ContainerNode.create(271.0D, 699.0D, 1350.0D, 248.0D)
      .body(container -> FlexNode.horizontal(0.0D, 0.0D, 180.0D).margin(50.0D).body(()).attach(container))
      
      .attach((UI)this);
  }
  
  private BossPlayerStat getPlayerStat(PlayerBossData data, String key) {
    return (BossPlayerStat)data.getBossStats().getStats().getOrDefault(key, new BossPlayerStat(key));
  }
  
  private BossPlayerStat getJobStat(PlayerBossData data, String key) {
    return (BossPlayerStat)data.getJobBossStats().getStats().getOrDefault(key, new BossPlayerStat(key));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\client\profile\gui\UIProfileBoss.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */