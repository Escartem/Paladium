package fr.paladium.palamod.modules.paladium.client.ui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.lib.apollon.container.abstracts.PaladiumContainer;
import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.HexaButtonNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.common.guihandler.PGuiRegistry;
import fr.paladium.palamod.modules.paladium.common.logics.AlchemyCreatorLogic;
import fr.paladium.palamod.modules.paladium.network.PacketOpenGui;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

@UIData(background = true, container = true)
@UIDataGuiscale(active = true)
public class UIAlchemyCreatorBase extends UI {
  private static final Resource HEXA_POTION = Resource.of(new ResourceLocation("palamod", "textures/gui/hexa_potion.png"));
  
  private static final Resource HEXA_ARROW = Resource.of(new ResourceLocation("palamod", "textures/gui/hexa_arrow.png"));
  
  private static final Resource BUBBLE_BACKGROUND = Resource.of(new ResourceLocation("palamod", "textures/gui/bubble_background.png"));
  
  private static final Resource BUBBLE_FOREGROUND = Resource.of(new ResourceLocation("palamod", "textures/gui/bubble_foreground.png"));
  
  protected AlchemyCreatorLogic logic;
  
  protected BackgroundNode background;
  
  public UIAlchemyCreatorBase(PaladiumContainer alchemyContainer) {
    super((Container)alchemyContainer);
  }
  
  public void init() {
    this
      
      .background = (BackgroundNode)BackgroundNode.create(760.0D, 800.0D).body(body -> {
          TextNode.create(31.0D, 5.0D).text(Text.create("alchemy creator", PaladiumText.HEADER)).attach(body);
          CloseButtonNode.create(body.getWidth() - 48.8D, 31.0D).onClick(()).attach(body);
          HexaButtonNode.create(body.getWidth() - 47.0D + 6.0D, 198.0D).icon(HEXA_POTION).border(false).width(94.0D).height(107.0D).onClick(()).attach(body);
          HexaButtonNode.create(body.getWidth() - 47.0D + 6.0D, 315.0D).icon(HEXA_ARROW).border(false).width(94.0D).height(107.0D).onClick(()).attach(body);
        }).attach(this);
    containerBounds(this.background.getAbsoluteX(), this.background.getAbsoluteY(), this.background.getWidth(), this.background.getHeight());
  }
  
  public static Resource getBubbleBackground() {
    return BUBBLE_BACKGROUND;
  }
  
  public static Resource getBubbleForeground() {
    return BUBBLE_FOREGROUND;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\clien\\ui\UIAlchemyCreatorBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */