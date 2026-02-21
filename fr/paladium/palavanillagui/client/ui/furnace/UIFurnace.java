package fr.paladium.palavanillagui.client.ui.furnace;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.slot.SlotNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palamixins.mixin.common.container.MixinContainerFurnace;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.progress.ProgressNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.resource.ResourceUtils;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.FloatSignal;
import java.util.List;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntityFurnace;

@UIData(container = true)
@UIDataGuiscale(active = true)
public class UIFurnace extends UI {
  private static final Resource FIRE_BACKGROUND = Resource.of(ResourceUtils.get("palavanillagui", "textures/gui/furnace/fire_loading.png"));
  
  private static final Resource FIRE_FILLER = Resource.of(ResourceUtils.get("palavanillagui", "textures/gui/furnace/fire_loading_filler.png"));
  
  private static final Resource ARROW_BACKGROUND = Resource.of(ResourceUtils.get("palavanillagui", "textures/gui/furnace/arrow_furnace.png"));
  
  private static final Resource ARROW_FILLER = Resource.of(ResourceUtils.get("palavanillagui", "textures/gui/furnace/arrow_furnace_filler.png"));
  
  private final TileEntityFurnace tile;
  
  private final FloatSignal burnRemainSignal = new FloatSignal(0.0F);
  
  private final FloatSignal cookSignal = new FloatSignal(0.0F);
  
  public UIFurnace(Container container) {
    super(container);
    this.tile = ((MixinContainerFurnace)container).getTileEntity();
  }
  
  public void init() {
    BackgroundNode.create(625.0D, 670.0D)
      .body(body -> {
          containerBounds(body.getAbsoluteX(), body.getAbsoluteY(), body.getWidth(), body.getHeight());
          TextNode.create(31.0D, 5.0D).text(Text.create("Furnace", PaladiumText.HEADER.copy())).attach(body);
          CloseButtonNode.create(body.getWidth() - 48.8D, 31.0D).onClick(()).attach(body);
          List<Slot> slots = (getContainer()).field_75151_b;
          double margin = (body.getWidth() - 540.0D) / 2.0D;
          slots.forEach(());
          ((ProgressNode)ProgressNode.create(margin + 150.0D + 2.0D, 192.0D, 56.0D, 56.0D).progress(((Float)this.burnRemainSignal.getOrDefault()).floatValue()).resource(FIRE_BACKGROUND, FIRE_FILLER).direction(ProgressNode.ProgressDirection.BOTTOM_TO_TOP).onInit(())).watch((Signal)this.burnRemainSignal).attach(body);
          ((ProgressNode)ProgressNode.create(margin + 240.0D, 190.0D, 88.0D, 60.0D).progress(((Float)this.cookSignal.getOrDefault()).floatValue()).resource(ARROW_BACKGROUND, ARROW_FILLER).onInit(())).watch((Signal)this.cookSignal).attach(body);
        }).attach(this);
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    float progressBurnRemain = this.tile.func_145955_e(100) / 100.0F;
    float progressCook = this.tile.func_145953_d(100) / 100.0F;
    if (((Float)this.burnRemainSignal.getOrDefault()).floatValue() != progressBurnRemain)
      this.burnRemainSignal.set(Float.valueOf(progressBurnRemain)); 
    if (((Float)this.cookSignal.getOrDefault()).floatValue() != progressCook)
      this.cookSignal.set(Float.valueOf(progressCook)); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\clien\\ui\furnace\UIFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */