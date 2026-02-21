package fr.paladium.palamod.modules.paladium.client.ui;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.slot.SlotNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palamod.modules.paladium.common.container.PaladiumFurnaceContainer;
import fr.paladium.palamod.modules.paladium.common.logics.PaladiumFurnaceLogic;
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

@UIData(background = false, container = true)
@UIDataGuiscale(active = true)
public class UIPalaFurnace extends UI {
  private static final Resource UPGRADE_PLACEHOLDER = Resource.of(ResourceUtils.get("palamod", "textures/gui/upgrade_placeholder.png"));
  
  private static final Resource ARROW = Resource.of(ResourceUtils.get("palamod", "textures/gui/arrow_furnace.png"));
  
  private static final Resource ARROW_FILLER = Resource.of(ResourceUtils.get("palamod", "textures/gui/arrow_furnace_filler.png"));
  
  private static final Resource FIRE = Resource.of(ResourceUtils.get("palamod", "textures/gui/fire_loading.png"));
  
  private static final Resource FIRE_FILLER = Resource.of(ResourceUtils.get("palamod", "textures/gui/fire_loading_filler.png"));
  
  private final PaladiumFurnaceLogic tile;
  
  private final FloatSignal cookProgress = new FloatSignal(0.0F);
  
  private final FloatSignal burnProgress = new FloatSignal(0.0F);
  
  public UIPalaFurnace(PaladiumFurnaceLogic tile) {
    super((Container)new PaladiumFurnaceContainer((Minecraft.func_71410_x()).field_71439_g.field_71071_by, tile));
    this.tile = tile;
  }
  
  public void init() {
    BackgroundNode.create(760.0D, 689.0D)
      .body(body -> {
          containerBounds(body.getAbsoluteX(), body.getAbsoluteY(), body.getWidth(), body.getHeight());
          TextNode.create(31.0D, 5.0D).text(Text.create("palafurnace", PaladiumText.HEADER)).attach(body);
          CloseButtonNode.create(body.getWidth() - 48.8D, 31.0D).onClick(()).attach(body);
          List<Slot> slots = (getContainer()).field_75151_b;
          slots.forEach(());
          ((ProgressNode)ProgressNode.create(410.0D, 219.0D, 95.0D, 65.0D).resource(ARROW, ARROW_FILLER).onInit(())).watch((Signal)this.cookProgress).attach(body);
          ((ProgressNode)ProgressNode.create(294.0D, 244.0D, 52.0D, 52.5D).resource(FIRE, FIRE_FILLER).direction(ProgressNode.ProgressDirection.BOTTOM_TO_TOP).onInit(())).watch((Signal)this.burnProgress).attach(body);
        }).attach(this);
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    if (((Float)this.cookProgress.getOrDefault()).floatValue() != this.tile.getCookProgress())
      this.cookProgress.set(Float.valueOf(this.tile.getCookProgress())); 
    if (((Float)this.burnProgress.getOrDefault()).floatValue() != this.tile.getBurnTimeRemaining())
      this.burnProgress.set(Float.valueOf(this.tile.getBurnTimeRemaining())); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\clien\\ui\UIPalaFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */