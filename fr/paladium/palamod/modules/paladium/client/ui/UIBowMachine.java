package fr.paladium.palamod.modules.paladium.client.ui;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.slot.SlotNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palamod.modules.paladium.common.container.BowMachineContainer;
import fr.paladium.palamod.modules.paladium.common.logics.BowMachineLogic;
import fr.paladium.palamod.modules.paladium.utils.BowHelper;
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
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.resource.ResourceUtils;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.FloatSignal;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

@UIData(background = false, container = true)
@UIDataGuiscale(active = true)
public class UIBowMachine extends UI {
  private static final Resource BADGE_MODIFIER = Resource.of(ResourceUtils.get("palamod", "textures/gui/badge_upgrade.png"));
  
  private static final Resource ARROW = Resource.of(ResourceUtils.get("palamod", "textures/gui/arrow.png"));
  
  public static final Resource ARROW_FILLER = Resource.of(ResourceUtils.get("palamod", "textures/gui/arrow_filler.png"));
  
  public static final Resource WRENCH_PLACEHOLDER = Resource.of(ResourceUtils.get("palamod", "textures/gui/wrench_placeholder.png"));
  
  public static final Resource BOW_PLACEHOLDER = Resource.of(ResourceUtils.get("palamod", "textures/gui/bow_placeholder.png"));
  
  private final BowMachineLogic logic;
  
  private final Signal<ItemStack> itemSignal = new Signal();
  
  private final FloatSignal progressSignal = new FloatSignal(0.0F);
  
  private final TextInfo modifierInfo = TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 25.0F, Color.WHITE);
  
  public UIBowMachine(BowMachineLogic logic) {
    super((Container)new BowMachineContainer(logic, (Minecraft.func_71410_x()).field_71439_g.field_71071_by));
    this.logic = logic;
  }
  
  public void init() {
    BackgroundNode.create(760.0D, 767.0D)
      .body(body -> {
          containerBounds(body.getAbsoluteX(), body.getAbsoluteY(), body.getWidth(), body.getHeight());
          TextNode.create(31.0D, 5.0D).text(Text.create("Bow machine", PaladiumText.HEADER)).attach(body);
          CloseButtonNode.create(body.getWidth() - 48.8D, 31.0D).onClick(()).attach(body);
          FlexNode.horizontal(36.0D, 136.0D, 40.0D).margin(20.0D).body(()).attach(body);
          RectNode.create(36.0D, 187.0D, 324.0D, 251.0D).color(Color.BLACK.copyAlpha(0.15F)).onInit(()).watch(this.itemSignal).attach(body);
          List<Slot> slots = (getContainer()).field_75151_b;
          slots.forEach(());
          ((ProgressNode)ProgressNode.create(495.0D, 269.0D, 124.0D, 57.0D).resource(ARROW, ARROW_FILLER).direction(ProgressNode.ProgressDirection.LEFT_TO_RIGHT).progress(((Float)this.progressSignal.getOrDefault()).floatValue()).onInit(())).watch((Signal)this.progressSignal).attach(body);
        }).attach(this);
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    ItemStack stack = this.logic.func_70301_a(1);
    if (!this.itemSignal.equals(stack))
      this.itemSignal.set(stack); 
    if (!this.progressSignal.equals(Float.valueOf(this.logic.getCookProgression())))
      this.progressSignal.set(Float.valueOf(this.logic.getCookProgression())); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\clien\\ui\UIBowMachine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */