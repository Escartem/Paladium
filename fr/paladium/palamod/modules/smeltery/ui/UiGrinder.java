package fr.paladium.palamod.modules.smeltery.ui;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.slot.SlotNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palamod.modules.smeltery.inventory.GrinderContainer;
import fr.paladium.palamod.modules.smeltery.logics.GrinderLogic;
import fr.paladium.palamod.modules.smeltery.ui.util.LoadingArrowNode;
import fr.paladium.palamod.modules.smeltery.ui.util.LoadingFireNode;
import fr.paladium.palamod.modules.smeltery.ui.util.TankNode;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.modifier.TextModifier;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.DoubleSignal;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

@UIData(container = true)
@UIDataGuiscale(active = true)
public class UiGrinder extends UI {
  public static final ResourceLocation BADGE_CRAFT = new ResourceLocation("palamod", "textures/gui/badge_craft.png");
  
  public static final ResourceLocation BADGE_UPGRADE = new ResourceLocation("palamod", "textures/gui/badge_upgrade.png");
  
  public static final ResourceLocation BADGE_MELT = new ResourceLocation("palamod", "textures/gui/badge_melt.png");
  
  public static final ResourceLocation WHITE_LINE = new ResourceLocation("palamod", "textures/gui/white_line.png");
  
  private final GrinderLogic logic;
  
  private final DoubleSignal toolLoadingSignal = new DoubleSignal(0.0D);
  
  private final DoubleSignal upgradeLoadingSignal = new DoubleSignal(0.0D);
  
  private final DoubleSignal progressLoadingSignal = new DoubleSignal(0.0D);
  
  private final DoubleSignal tankLoadingSignal = new DoubleSignal(0.0D);
  
  public UiGrinder(GrinderLogic grinderLogic) {
    super((Container)new GrinderContainer((Minecraft.func_71410_x()).field_71439_g.field_71071_by, grinderLogic));
    this.logic = grinderLogic;
  }
  
  public void init() {
    BackgroundNode.create(760.0D, 831.0D)
      .body(body -> {
          containerBounds(body.getAbsoluteX(), body.getAbsoluteY(), body.getWidth(), body.getHeight());
          TextNode.create(31.0D, 5.0D).text(Text.create(StatCollector.func_74838_a("crafters.Grinder"), PaladiumText.HEADER)).attach(body);
          CloseButtonNode.create(body.getWidth() - 48.8D, 31.0D).onClick(()).attach(body);
          FlexNode.horizontal(36.0D, 136.0D, 40.0D).margin(18.0D).body(()).attach(body);
          FlexNode.horizontal(430.0D, 136.0D, 40.0D).margin(18.0D).body(()).attach(body);
          FlexNode.horizontal(36.0D, 386.0D, 40.0D).margin(18.0D).body(()).attach(body);
          RectNode.create(430.0D, 185.0D, 300.0D, 300.0D).color(new Color(0.0F, 0.0F, 0.0F, 0.15F)).attach(body);
          List<Slot> slots = (getContainer()).field_75151_b;
          slots.forEach(());
          ((LoadingArrowNode)LoadingArrowNode.create(160.0D, 220.0D, 89.0D, 60.0D).thickness(12.0D).onInit(())).watch((Signal)this.toolLoadingSignal).attach(body);
          ((LoadingArrowNode)LoadingArrowNode.create(160.0D, 434.0D, 89.0D, 60.0D).thickness(12.0D).onInit(())).watch((Signal)this.upgradeLoadingSignal).attach(body);
          ((LoadingFireNode)LoadingFireNode.create(458.0D, 285.0D, 53.0D, 53.0D).onInit(())).watch((Signal)this.progressLoadingSignal).attach(body);
          ((TankNode)TankNode.create(540.0D, 210.0D, 164.0D, 226.0D).onInit(())).watch((Signal)this.tankLoadingSignal).attach(body);
          ((TextNode)TextNode.create(625.0D, 445.0D).text(Text.create(this.logic.getPaladium() + "/" + this.logic.getMaxPaladium(), TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 20.0F, new Color(139, 139, 141)))).anchorX(Align.CENTER).onInit(())).watch((Signal)this.tankLoadingSignal).attach(body);
        }).attach(this);
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    setSignalIfDifferent(this.toolLoadingSignal, this.logic.getNonScaledTool());
    setSignalIfDifferent(this.upgradeLoadingSignal, this.logic.getNonScaledUpgrade());
    setSignalIfDifferent(this.progressLoadingSignal, this.logic.getNonScaledProgress());
    setSignalIfDifferent(this.tankLoadingSignal, this.logic.getPaladium() / this.logic.getMaxPaladium());
  }
  
  private void setSignalIfDifferent(DoubleSignal signal, double value) {
    if (((Double)signal.getOrDefault()).doubleValue() != value)
      signal.set(Double.valueOf(value)); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smelter\\ui\UiGrinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */