package fr.paladium.palamod.modules.paladium.client.ui;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.slot.SlotNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palamod.modules.paladium.common.container.PaladiumMachineContainer;
import fr.paladium.palamod.modules.paladium.common.logics.PaladiumMachineLogic;
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
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

@UIData(container = true)
@UIDataGuiscale(active = true)
public class UIPalaMachine extends UI {
  public static final Resource BACKGROUND_IMAGE = Resource.of(ResourceUtils.get("palamod", "textures/gui/palamachine_background.png"));
  
  private static final Resource BACKGROUND_IMAGE_FILLER = Resource.of(ResourceUtils.get("palamod", "textures/gui/palamachine_background_filler.png"));
  
  public static final Resource MAIN_SLOT = Resource.of(ResourceUtils.get("palamod", "textures/gui/palamachine_result_slot.png"));
  
  private final PaladiumMachineLogic tile;
  
  private final FloatSignal progress = new FloatSignal(0.0F);
  
  public UIPalaMachine(PaladiumMachineLogic tile) {
    super((Container)new PaladiumMachineContainer(tile, (Minecraft.func_71410_x()).field_71439_g.field_71071_by));
    this.tile = tile;
  }
  
  public void init() {
    BackgroundNode.create(760.0D, 689.0D)
      .body(body -> {
          containerBounds(body.getAbsoluteX(), body.getAbsoluteY(), body.getWidth(), body.getHeight());
          TextNode.create(31.0D, 5.0D).text(Text.create("palamachine", PaladiumText.HEADER)).attach(body);
          CloseButtonNode.create(body.getWidth() - 48.8D, 31.0D).onClick(()).attach(body);
          ((ProgressNode)ProgressNode.create(262.0D, 138.0D, 236.0D, 236.0D).resource(BACKGROUND_IMAGE, BACKGROUND_IMAGE_FILLER).direction(ProgressNode.ProgressDirection.TOP_TO_BOTTOM).onInit(())).watch((Signal)this.progress).attach(body);
          List<Slot> slots = (getContainer()).field_75151_b;
          slots.forEach(());
        }).attach(this);
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    if (((Float)this.progress.getOrDefault()).floatValue() != this.tile.getCookFloatProgress())
      this.progress.set(Float.valueOf(this.tile.getCookFloatProgress())); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\clien\\ui\UIPalaMachine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */