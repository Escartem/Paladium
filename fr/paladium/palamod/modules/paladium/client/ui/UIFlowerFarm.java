package fr.paladium.palamod.modules.paladium.client.ui;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.slot.SlotNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palamod.common.guihandler.SimpleGHandler;
import fr.paladium.palamod.modules.paladium.common.container.FlowerFarmContainer;
import fr.paladium.palamod.modules.paladium.common.logics.TileFlowerFarm;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.progress.ProgressNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.resource.ResourceUtils;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.FloatSignal;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

@UIData(background = false, container = true)
@UIDataGuiscale(active = true)
public class UIFlowerFarm extends UI {
  public static final Resource ARROW = Resource.of(ResourceUtils.get("palamod", "textures/gui/flower_machine/arrow.png"));
  
  private final TileFlowerFarm tile;
  
  public TileFlowerFarm getTile() {
    return this.tile;
  }
  
  private final FloatSignal progressFuel = new FloatSignal(0.0F);
  
  public UIFlowerFarm(SimpleGHandler.GuiHandlerData data) {
    this((TileFlowerFarm)data.getTileEntity(TileFlowerFarm.class));
  }
  
  public UIFlowerFarm(TileFlowerFarm tile) {
    super((Container)new FlowerFarmContainer(tile, (EntityPlayer)(Minecraft.func_71410_x()).field_71439_g));
    this.tile = tile;
  }
  
  public void init() {
    BackgroundNode.create(826.0D, 821.0D)
      .body(body -> {
          containerBounds(body.getAbsoluteX(), body.getAbsoluteY(), body.getWidth(), body.getHeight());
          TextNode.create(31.0D, 5.0D).text(Text.create("Flower Machine", PaladiumText.HEADER)).attach(body);
          CloseButtonNode.create(body.getWidth() - 48.8D, 31.0D).onClick(()).attach(body);
          ((ProgressNode)ProgressNode.create(146.0D, 397.0D, 533.0D, 62.0D).progress(((Float)this.progressFuel.getOrDefault()).floatValue()).direction(ProgressNode.ProgressDirection.RIGHT_TO_LEFT).color(new Color(90, 212, 1), new Color(50, 50, 50)).onInit(())).watch((Signal)this.progressFuel).attach(body);
          ((ProgressNode)ProgressNode.create(146.0D, 459.0D, 533.0D, 6.0D).progress(((Float)this.progressFuel.getOrDefault()).floatValue()).direction(ProgressNode.ProgressDirection.RIGHT_TO_LEFT).color(new Color(36, 83, 32), new Color(26, 26, 26)).onInit(())).watch((Signal)this.progressFuel).attach(body);
          ImageNode.create(370.0D, 273.0D).resource(ARROW).attach(body);
          ((TextNode)TextNode.create(body.dw(2.0D), 413.0D).text(Text.create("", TextInfo.create((IFont)PaladiumFont.MONTSERRAT_EXTRA_BOLD, 27.0F, Color.WHITE))).anchorX(Align.CENTER).onInit(())).watch((Signal)this.progressFuel).attach(body);
          TextNode.create(143.0D, 172.0D).text(Text.create("Carburant", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 27.0F, Color.WHITE))).attach(body);
          List<Slot> slots = (getContainer()).field_75151_b;
          slots.forEach(());
        }).attach(this);
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    if (((Float)this.progressFuel.getOrDefault()).floatValue() != this.tile.getScaledWork())
      this.progressFuel.set(Float.valueOf(this.tile.getScaledWork())); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\clien\\ui\UIFlowerFarm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */