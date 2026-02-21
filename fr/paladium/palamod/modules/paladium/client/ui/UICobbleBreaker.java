package fr.paladium.palamod.modules.paladium.client.ui;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.slot.SlotNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.paladium.client.ui.util.ArrowWorkNode;
import fr.paladium.palamod.modules.paladium.client.ui.util.RectItemNode;
import fr.paladium.palamod.modules.paladium.common.container.ContainerCobbleBreaker;
import fr.paladium.palamod.modules.paladium.common.logics.TileCobbleBreaker;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

@UIData(container = true, background = false)
@UIDataGuiscale(active = true)
public class UICobbleBreaker extends UI {
  public static final Resource COBBLEBREAK_TOOL = Resource.of(new ResourceLocation("palamod", "textures/gui/cobblebreaker_tool.png"));
  
  private final TileCobbleBreaker teCobbleBreaker;
  
  public UICobbleBreaker(TileCobbleBreaker tile, EntityPlayer player) {
    super((Container)new ContainerCobbleBreaker(tile, player));
    this.teCobbleBreaker = tile;
  }
  
  public void init() {
    ContainerNode.create(0.0D, 0.0D, 1920.0D, 1080.0D)
      .body(container -> BackgroundNode.create(760.0D, 746.0D).body(()).attach(container))
      
      .attach(this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\clien\\ui\UICobbleBreaker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */