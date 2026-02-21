package fr.paladium.palamod.modules.paladium.client.ui;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.slot.SlotNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palamod.modules.paladium.common.container.ForgeContainer;
import fr.paladium.palamod.modules.paladium.common.logics.ForgeLogic;
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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

@UIData(background = false, container = true)
@UIDataGuiscale(active = true)
public class UIPalaForge extends UI {
  private static final Resource ARMOR_PLACEHOLDER = Resource.of(ResourceUtils.get("palamod", "textures/gui/armor_placeholder.png"));
  
  public static final Resource ARROW_DOWN = Resource.of(ResourceUtils.get("palamod", "textures/gui/arrow_down.png"));
  
  public static final Resource ARROW_DOWN_FILLER = Resource.of(ResourceUtils.get("palamod", "textures/gui/arrow_down_filler.png"));
  
  public static final Resource FIRE = Resource.of(ResourceUtils.get("palamod", "textures/gui/fire_loading.png"));
  
  public static final Resource FIRE_FILLER = Resource.of(ResourceUtils.get("palamod", "textures/gui/fire_loading_filler.png"));
  
  private final ForgeLogic tile;
  
  private final FloatSignal forgeProgress = new FloatSignal(0.0F);
  
  private final FloatSignal burnProgress = new FloatSignal(0.0F);
  
  public UIPalaForge(ForgeLogic tile) {
    super((Container)new ForgeContainer(tile, (EntityPlayer)(Minecraft.func_71410_x()).field_71439_g));
    this.tile = tile;
  }
  
  public void init() {
    BackgroundNode.create(760.0D, 718.0D)
      .body(body -> {
          containerBounds(body.getAbsoluteX(), body.getAbsoluteY(), body.getWidth(), body.getHeight());
          TextNode.create(31.0D, 5.0D).text(Text.create("palaforge", PaladiumText.HEADER)).attach(body);
          CloseButtonNode.create(body.getWidth() - 48.8D, 31.0D).onClick(()).attach(body);
          List<Slot> slots = (getContainer()).field_75151_b;
          slots.forEach(());
          ((ProgressNode)ProgressNode.create(body.getWidth() / 2.0D - 270.0D + 60.0D, 219.0D, 59.0D, 76.0D).resource(ARROW_DOWN, ARROW_DOWN_FILLER).direction(ProgressNode.ProgressDirection.TOP_TO_BOTTOM).onInit(())).watch((Signal)this.forgeProgress).attach(body);
          ((ProgressNode)ProgressNode.create(533.0D, 250.0D, 53.0D, 53.0D).resource(FIRE, FIRE_FILLER).direction(ProgressNode.ProgressDirection.BOTTOM_TO_TOP).onInit(())).watch((Signal)this.burnProgress).attach(body);
        }).attach(this);
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    if (((Float)this.forgeProgress.getOrDefault()).floatValue() != this.tile.getWorkProgress())
      this.forgeProgress.set(Float.valueOf(this.tile.getWorkProgress())); 
    if (((Float)this.burnProgress.getOrDefault()).floatValue() != this.tile.getBurnProgress())
      this.burnProgress.set(Float.valueOf(this.tile.getBurnProgress())); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\clien\\ui\UIPalaForge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */