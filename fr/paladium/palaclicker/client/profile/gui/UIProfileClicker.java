package fr.paladium.palaclicker.client.profile.gui;

import fr.paladium.palaclicker.client.profile.gui.node.ProfileClickerUpgradeNode;
import fr.paladium.palaclicker.client.ui.UIClicker;
import fr.paladium.palaclicker.client.ui.node.right.ClickerBuildingNode;
import fr.paladium.palaclicker.common.network.packet.building.BBPacketClickerBuildingData;
import fr.paladium.palaclicker.common.network.packet.upgrade.BBPacketClickerUpgradeData;
import fr.paladium.palaclicker.common.profile.dto.ProfileClickerBuilding;
import fr.paladium.palaclicker.common.profile.dto.ProfilePlayerClicker;
import fr.paladium.palaclicker.server.config.building.dto.ClickerBuilding;
import fr.paladium.palaclicker.server.config.upgrade.dto.ClickerUpgrade;
import fr.paladium.palaclicker.server.config.upgrade.dto.ClickerUpgradeType;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.scrollbar.ScrollbarNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.profile.client.gui.UIProfile;
import fr.paladium.profile.common.module.AModule;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.grid.GridNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.scrollbar.ScrollbarNode;
import fr.paladium.zephyrui.lib.ui.node.property.overflow.OverflowProperty;
import net.minecraft.util.ResourceLocation;

public class UIProfileClicker extends UIProfile<ProfilePlayerClicker> {
  public UIProfileClicker(AModule<?> module) {
    super(module);
  }
  
  public void onInit() {
    TextNode.create(271.0D, 275.0D)
      .text(Text.create("statistiques", PaladiumText.TITLE))
      .attach((UI)this);
    ImageNode.create(215.0D, 348.0D, 1490.0D, 194.0D)
      .resource(new ResourceLocation("palaclicker", "textures/gui/profile/banner.png"))
      .linear(false)
      .attach((UI)this);
    TextNode.create(377.0D, 376.0D)
      .text(Text.create("cliccoins produits", PaladiumText.TITLE))
      .attach((UI)this);
    TextNode.create(377.0D, 451.0D)
      .text(Text.create("production par seconde", PaladiumText.TITLE))
      .attach((UI)this);
    TextNode.create(878.0D, 376.0D)
      .text(Text.create("dernier batiment acheté", PaladiumText.TITLE))
      .attach((UI)this);
    RectNode.create(215.0D, 542.0D, 1490.0D, 428.0D)
      .color(Color.BLACK.copyAlpha(0.2F))
      .attach((UI)this);
  }
  
  public void onPostInit() {
    ProfilePlayerClicker data = (ProfilePlayerClicker)getProfileData().getOrDefault();
    TextNode.create(377.0D, 407.0D)
      .text(Text.create(UIClicker.formatBigNumber(Long.valueOf(data.getBuildings().stream().mapToLong(b -> (long)b.getProduction()).sum())), PaladiumText.TITLE.copy().color(new Color(249, 194, 43))))
      .attach((UI)this);
    TextNode.create(377.0D, 482.0D)
      .text(Text.create(UIClicker.formatBigNumber(Double.valueOf(data.getRps())) + "/sec", PaladiumText.TITLE.copy().color(new Color(249, 194, 43))))
      .attach((UI)this);
    if (data.getLastBuildingBought() != null && !data.getBuildings().isEmpty())
      (new BBPacketClickerBuildingData()).subscribe(result -> {
            ClickerBuilding building = result.getBuildingList().stream().filter(()).findFirst().orElse(null);
            ProfileClickerBuilding profileBuilding = data.getBuildings().stream().filter(()).findFirst().orElse(null);
            if (building != null && profileBuilding != null) {
              building.make();
              ClickerBuildingNode.create(878.0D, 422.0D, 391.0D, 89.0D).data(building, profileBuilding.getQuantity(), true).available(true).enabled(()).attach((UI)this);
            } 
          }).send(); 
    (new BBPacketClickerUpgradeData()).subscribe(result -> ContainerNode.create(215.0D, 542.0D, 1490.0D, 428.0D).overflow(OverflowProperty.SCROLL).scrollbar((ScrollbarNode)ScrollbarNode.create(1440.0D, 17.0D, 394.0D)).body(()).attach((UI)this))
      
      .send();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\client\profile\gui\UIProfileClicker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */