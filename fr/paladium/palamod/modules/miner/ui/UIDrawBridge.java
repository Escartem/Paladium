package fr.paladium.palamod.modules.miner.ui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.slot.SlotNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palamod.modules.miner.PMiner;
import fr.paladium.palamod.modules.miner.container.ContainerDrawBridge;
import fr.paladium.palamod.modules.miner.networks.CSDrawBridgeSetDirection;
import fr.paladium.palamod.modules.miner.tileentity.TileEntityDrawBridge;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.resource.ResourceUtils;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.IntegerSignal;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;

@UIData(background = false, container = true)
@UIDataGuiscale(active = true)
public class UIDrawBridge extends UI {
  private static final ResourceLocation MIDDLE = ResourceUtils.get("palamod", "textures/gui/drawbridge/middle.png");
  
  private static final ResourceLocation ARROW_UP = ResourceUtils.get("palamod", "textures/gui/drawbridge/arrow_up.png");
  
  private static final ResourceLocation ARROW_UP_HOVER = ResourceUtils.get("palamod", "textures/gui/drawbridge/arrow_up_hover.png");
  
  private static final ResourceLocation ARROW_DOWN = ResourceUtils.get("palamod", "textures/gui/drawbridge/arrow_down.png");
  
  private static final ResourceLocation ARROW_DOWN_HOVER = ResourceUtils.get("palamod", "textures/gui/drawbridge/arrow_down_hover.png");
  
  private static final ResourceLocation ARROW_LEFT = ResourceUtils.get("palamod", "textures/gui/drawbridge/arrow_left.png");
  
  private static final ResourceLocation ARROW_LEFT_HOVER = ResourceUtils.get("palamod", "textures/gui/drawbridge/arrow_left_hover.png");
  
  private static final ResourceLocation ARROW_RIGHT = ResourceUtils.get("palamod", "textures/gui/drawbridge/arrow_right.png");
  
  private static final ResourceLocation ARROW_RIGHT_HOVER = ResourceUtils.get("palamod", "textures/gui/drawbridge/arrow_right_hover.png");
  
  private final IntegerSignal selected;
  
  private final TileEntityDrawBridge tile;
  
  public UIDrawBridge(TileEntityDrawBridge tile) {
    super((Container)new ContainerDrawBridge((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g, tile));
    this.tile = tile;
    this.selected = new IntegerSignal(this.tile.getDirection());
  }
  
  public void init() {
    BackgroundNode.create(850.0D, 663.0D)
      .body(body -> {
          containerBounds(body.getAbsoluteX(), body.getAbsoluteY(), body.getWidth(), body.getHeight());
          TextNode.create(31.0D, 5.0D).text(Text.create("Drawbridge", PaladiumText.HEADER)).attach(body);
          CloseButtonNode.create(body.getWidth() - 48.8D, 31.0D).onClick(()).attach(body);
          int i;
          for (i = 0; i < 7; i++)
            RectNode.create(95.0D + i * 68.0D, 180.0D, 8.0D, 10.0D).color(Color.DARKGRAY).attach(body); 
          RectNode.create(539.0D, 215.0D, 10.0D, 20.0D).color(Color.DARKGRAY).attach(body);
          RectNode.create(60.0D, 225.0D, 486.0D, 10.0D).color(Color.DARKGRAY).attach(body);
          RectNode.create(60.0D, 225.0D, 10.0D, 22.0D).color(Color.DARKGRAY).attach(body);
          for (i = 0; i < 7; i++)
            RectNode.create(95.0D + i * 68.0D, 270.0D, 8.0D, 10.0D).color(Color.DARKGRAY).attach(body); 
          List<Slot> slots = (getContainer()).field_75151_b;
          slots.forEach(());
          ImageNode.create(694.0D, 210.0D, 43.0D, 43.0D).resource(MIDDLE).attach(body);
          ((ImageNode)ImageNode.create(694.0D, 153.0D, 43.0D, 38.0D).resource(ARROW_UP).hoverResource(ARROW_UP_HOVER).onInit(())).onClick(()).watch((Signal)this.selected).attach(body);
          ((ImageNode)ImageNode.create(694.0D, 272.0D, 43.0D, 38.0D).resource(ARROW_DOWN).hoverResource(ARROW_DOWN_HOVER).onInit(())).onClick(()).watch((Signal)this.selected).attach(body);
          ((ImageNode)ImageNode.create(635.0D, 210.0D, 38.0D, 43.0D).resource(ARROW_LEFT).hoverResource(ARROW_LEFT_HOVER).onInit(())).onClick(()).watch((Signal)this.selected).attach(body);
          ((ImageNode)ImageNode.create(756.0D, 210.0D, 38.0D, 43.0D).resource(ARROW_RIGHT).hoverResource(ARROW_RIGHT_HOVER).onInit(())).onClick(()).watch((Signal)this.selected).attach(body);
        }).attach(this);
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    if (((Integer)this.selected.getOrDefault()).intValue() != this.tile.getDirection())
      this.selected.set(Integer.valueOf(this.tile.getDirection())); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mine\\ui\UIDrawBridge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */