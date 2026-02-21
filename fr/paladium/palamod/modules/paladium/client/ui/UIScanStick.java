package fr.paladium.palamod.modules.paladium.client.ui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.TextButtonNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.scrollbar.ScrollbarNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.paladiumui.kit.textfield.IntegerFieldNode;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.paladium.client.ui.util.PlayerListItemNode;
import fr.paladium.palamod.modules.paladium.network.CSPacketRequestScanStickPlayers;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.scrollbar.ScrollbarNode;
import fr.paladium.zephyrui.lib.ui.node.property.overflow.OverflowProperty;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.ListSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.IntegerSignal;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

@UIDataGuiscale(active = true)
public class UIScanStick extends UI {
  private static final Resource SETTINGS_ICON = Resource.of(new ResourceLocation("palamod", "textures/gui/settings_badge.png"));
  
  private static final Resource RESULT_ICON = Resource.of(new ResourceLocation("palamod", "textures/gui/result_badge.png"));
  
  private static final Resource RECT_BAR = Resource.of(new ResourceLocation("palamod", "textures/gui/container/safechest/white_line.png"));
  
  private final ListSignal<String> playersSignal = new ListSignal(new ArrayList());
  
  private final IntegerSignal itemSignal = new IntegerSignal(0);
  
  private final IntegerSignal radiusSignal = new IntegerSignal(0);
  
  public void init() {
    BackgroundNode.create(936.0D, 598.0D)
      .body(background -> {
          TextNode.create(31.0D, 5.0D).text(Text.create("scan", PaladiumText.HEADER)).attach(background);
          CloseButtonNode.create(background.getWidth() - 48.8D, 25.0D).onClick(()).attach(background);
          FlexNode.horizontal(36.0D, 136.0D, 38.0D).margin(18.0D).body(()).attach(background);
          FlexNode.horizontal(405.0D, 136.0D, 38.0D).margin(18.0D).body(()).attach(background);
          ContainerNode.create(40.0D, 206.0D, 337.0D, 257.0D).body(()).attach(background);
          RectNode.create(406.0D, 200.0D, 497.0D, 355.0D).color(new Color(0.0F, 0.0F, 0.0F, 0.15F)).body(()).attach(background);
        }).attach(this);
  }
  
  public void setPlayerList(List<String> playerList) {
    this.playersSignal.set(playerList);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\clien\\ui\UIScanStick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */