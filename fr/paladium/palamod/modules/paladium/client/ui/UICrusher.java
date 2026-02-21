package fr.paladium.palamod.modules.paladium.client.ui;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.slot.SlotNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.paladium.client.ui.util.CrusherFuelTankContainer;
import fr.paladium.palamod.modules.paladium.client.ui.util.CrusherMineralNode;
import fr.paladium.palamod.modules.paladium.common.container.CrusherContainer;
import fr.paladium.palamod.modules.paladium.common.logics.TileCrusher;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.progress.ProgressNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.resource.ResourceUtils;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.ListSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.FloatSignal;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

@UIData(background = false, container = true)
@UIDataGuiscale(active = true)
public class UICrusher extends UI {
  private static final Resource FIRE_BACKGROUND = Resource.of(ResourceUtils.get("palamod", "textures/gui/fire_loading.png"));
  
  private static final Resource FIRE_FILLER = Resource.of(ResourceUtils.get("palamod", "textures/gui/fire_loading_filler.png"));
  
  private static final Resource ARROW = Resource.of(ResourceUtils.get("palamod", "textures/gui/arrow.png"));
  
  private static final Resource ARROW_FILLER = Resource.of(ResourceUtils.get("palamod", "textures/gui/arrow_filler.png"));
  
  private static final Resource PLANT_PLACEHOLDER = Resource.of(ResourceUtils.get("palamod", "textures/gui/crusher/plant_placeholder.png"));
  
  private static final Resource FUEL_PLACEHOLDER = Resource.of(ResourceUtils.get("palamod", "textures/gui/crusher/fuel_placeholder.png"));
  
  private final TileCrusher tile;
  
  public TileCrusher getTile() {
    return this.tile;
  }
  
  private final FloatSignal progressFuel = new FloatSignal(0.0F);
  
  private final FloatSignal progressArrow = new FloatSignal(0.0F);
  
  private final ListSignal<Integer> fuelTank = new ListSignal(
      Arrays.asList(new Integer[] { Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0) }));
  
  public ListSignal<Integer> getFuelTank() {
    return this.fuelTank;
  }
  
  private final ListSignal<Integer> plantTank = new ListSignal(
      Arrays.asList(new Integer[] { Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0) }));
  
  public ListSignal<Integer> getPlantTank() {
    return this.plantTank;
  }
  
  private final Signal<TileCrusher.EnumCrusherResult> craftSignal = new Signal(TileCrusher.EnumCrusherResult.NONE);
  
  public Signal<TileCrusher.EnumCrusherResult> getCraftSignal() {
    return this.craftSignal;
  }
  
  private final Color pipeColor = new Color(63, 64, 77);
  
  private CrusherMineralNode amethysteNode;
  
  private CrusherMineralNode titaneNode;
  
  private CrusherMineralNode paladiumNode;
  
  private CrusherMineralNode endiumNode;
  
  public UICrusher(TileCrusher tile) {
    super((Container)new CrusherContainer(tile, (EntityPlayer)(Minecraft.func_71410_x()).field_71439_g));
    this.tile = tile;
  }
  
  public void init() {
    BackgroundNode.create(1094.0D, 831.0D)
      .body(body -> {
          containerBounds(body.getAbsoluteX(), body.getAbsoluteY(), body.getWidth(), body.getHeight());
          TextNode.create(31.0D, 5.0D).text(Text.create("Crusher", PaladiumText.HEADER)).attach(body);
          CloseButtonNode.create(body.getWidth() - 48.8D, 31.0D).onClick(()).attach(body);
          List<Slot> slots = (getContainer()).field_75151_b;
          slots.forEach(());
          ((ProgressNode)ProgressNode.create(332.0D, 337.0D, 52.0D, 52.1D).progress(((Float)this.progressFuel.getOrDefault()).floatValue()).direction(ProgressNode.ProgressDirection.BOTTOM_TO_TOP).resource(FIRE_BACKGROUND, FIRE_FILLER).onInit(())).watch((Signal)this.progressFuel).attach(body);
          CrusherFuelTankContainer.create(81.0D, 285.0D, 197.0D, 175.0D).color(Color.BLACK.copyAlpha(0.15F)).attach(body);
          RectNode.create(278.0D, 424.0D, 50.0D, 10.0D).color(this.pipeColor).attach(body);
          RectNode.create(416.0D, 176.0D, 10.0D, 247.0D).color(this.pipeColor).attach(body);
          RectNode.create(388.0D, 166.0D, 85.0D, 10.0D).color(this.pipeColor).attach(body);
          RectNode.create(426.0D, 248.0D, 47.0D, 10.0D).color(this.pipeColor).attach(body);
          RectNode.create(426.0D, 330.0D, 47.0D, 10.0D).color(this.pipeColor).attach(body);
          RectNode.create(426.0D, 413.0D, 47.0D, 10.0D).color(this.pipeColor).attach(body);
          this.amethysteNode = (CrusherMineralNode)CrusherMineralNode.create(472.0D, 134.5D, 383.0D, 73.0D).loadingColor(new Color(183, 30, 255)).color(Color.BLACK.copyAlpha(0.25F)).attach(body);
          this.titaneNode = (CrusherMineralNode)CrusherMineralNode.create(472.0D, 216.5D, 383.0D, 73.0D).loadingColor(new Color(165, 165, 165)).item(ItemsRegister.TITANE_INGOT).itemNeeded(ItemsRegister.FRUIT_CHERVIL).color(Color.BLACK.copyAlpha(0.25F)).attach(body);
          this.paladiumNode = (CrusherMineralNode)CrusherMineralNode.create(472.0D, 298.5D, 383.0D, 73.0D).loadingColor(new Color(239, 57, 38)).item(ItemsRegister.PALADIUM_INGOT).itemNeeded(ItemsRegister.FRUIT_KIWANO).color(Color.BLACK.copyAlpha(0.25F)).attach(body);
          this.endiumNode = (CrusherMineralNode)CrusherMineralNode.create(472.0D, 380.5D, 383.0D, 73.0D).loadingColor(new Color(37, 85, 255)).item(ItemsRegister.ENDIUM_NUGGET).itemNeeded(ItemsRegister.FRUIT_ORANGEBLUE).color(Color.BLACK.copyAlpha(0.25F)).attach(body);
          RectNode.create(902.0D, 166.0D, 10.0D, 257.0D).color(this.pipeColor).attach(body);
          RectNode.create(855.0D, 166.0D, 47.0D, 10.0D).color(this.pipeColor).attach(body);
          RectNode.create(855.0D, 248.0D, 47.0D, 10.0D).color(this.pipeColor).attach(body);
          RectNode.create(855.0D, 330.0D, 47.0D, 10.0D).color(this.pipeColor).attach(body);
          RectNode.create(855.0D, 413.0D, 47.0D, 10.0D).color(this.pipeColor).attach(body);
          RectNode.create(912.0D, 289.5D, 47.0D, 10.0D).color(this.pipeColor).attach(body);
          ((ProgressNode)ProgressNode.create(0.0D, 0.0D, 70.0D, 32.0D).resource(ARROW, ARROW_FILLER).onInit(())).watch((Signal)this.progressArrow).visible(()).attach(body);
        }).attach(this);
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    if (((Float)this.progressFuel.getOrDefault()).floatValue() != this.tile.getFuelProgression())
      this.progressFuel.set(Float.valueOf(this.tile.getFuelProgression())); 
    if (((Float)this.progressArrow.getOrDefault()).floatValue() != this.tile.getArrowProgression())
      this.progressArrow.set(Float.valueOf(this.tile.getArrowProgression())); 
    if (this.craftSignal.getOrDefault() != this.tile.getExtractItem())
      this.craftSignal.set(this.tile.getExtractItem()); 
    int i;
    for (i = 0; i < this.fuelTank.size(); i++) {
      if (((Integer)this.fuelTank.get(i)).intValue() != this.tile.fuelTanks[i])
        this.fuelTank.set(i, Integer.valueOf(this.tile.fuelTanks[i])); 
    } 
    for (i = 0; i < this.plantTank.size(); i++) {
      if (((Integer)this.plantTank.get(i)).intValue() != this.tile.tanks[i])
        this.plantTank.set(i, Integer.valueOf(this.tile.tanks[i])); 
    } 
  }
  
  private CrusherMineralNode getCraftingNode() {
    switch (this.tile.getExtractItem()) {
      case AMETHYST:
        return this.amethysteNode;
      case TITANE:
        return this.titaneNode;
      case PALADIUM:
        return this.paladiumNode;
      case ENDIUM:
        return this.endiumNode;
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\clien\\ui\UICrusher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */