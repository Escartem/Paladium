package fr.paladium.palamod.modules.miner.ui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.scrollbar.ScrollbarNode;
import fr.paladium.paladiumui.kit.slot.SlotNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palamod.modules.miner.PMiner;
import fr.paladium.palamod.modules.miner.container.ContainerAutoCrafter;
import fr.paladium.palamod.modules.miner.networks.CSSetAutoCrafter;
import fr.paladium.palamod.modules.miner.tileentity.TileEntityAutoCrafter;
import fr.paladium.palamod.modules.miner.ui.util.FakeItemSlotNode;
import fr.paladium.palamod.modules.miner.utils.AutoCrafterManager;
import fr.paladium.palamod.modules.miner.utils.AutoCrafterRecipe;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.builder.modifier.TextModifier;
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
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.scrollbar.ScrollbarNode;
import fr.paladium.zephyrui.lib.ui.node.property.overflow.OverflowProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.resource.ResourceUtils;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.FloatSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.StringSignal;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

@UIData(background = false, container = true)
@UIDataGuiscale(active = true)
public class UIAutoCrafter extends UI {
  private static final Resource BADGE_RESOURCES = Resource.of(ResourceUtils.get("palamod", "textures/gui/badge_resource.png"));
  
  private static final Resource ARROW = Resource.of(ResourceUtils.get("palamod", "textures/gui/autocrafter/arrow.png"));
  
  private static final Resource ARROW_FILLER = Resource.of(ResourceUtils.get("palamod", "textures/gui/autocrafter/arrow_filler.png"));
  
  private final TileEntityAutoCrafter tile;
  
  private final StringSignal craftSignal = new StringSignal("");
  
  private final FloatSignal progressSignal = new FloatSignal(0.0F);
  
  public UIAutoCrafter(TileEntityAutoCrafter tile) {
    super((Container)new ContainerAutoCrafter((Minecraft.func_71410_x()).field_71439_g.field_71071_by, tile));
    this.tile = tile;
  }
  
  public void init() {
    BackgroundNode.create(1190.0D, 740.0D)
      .body(body -> {
          containerBounds(body.getAbsoluteX(), body.getAbsoluteY(), body.getWidth(), body.getHeight());
          TextNode.create(31.0D, 5.0D).text(Text.create("autocrafter", PaladiumText.HEADER)).attach(body);
          CloseButtonNode.create(body.getWidth() - 48.8D, 31.0D).onClick(()).attach(body);
          RectNode.create(31.0D, 120.0D, 576.0D, 305.0D).color(Color.BLACK.copyAlpha(0.15F)).body(()).attach(body);
          FlexNode.horizontal(660.0D, 123.0D, 40.0D).margin(20.0D).body(()).attach(body);
          List<Slot> slots = (getContainer()).field_75151_b;
          slots.forEach(());
          ((ProgressNode)ProgressNode.create(862.0D, 241.0D, 76.0D, 59.0D).resource(ARROW, ARROW_FILLER).onInit(())).watch((Signal)this.progressSignal).attach(body);
        }).attach(this);
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    if (this.tile == null || this.tile.getRecipe() == null)
      return; 
    if (this.craftSignal.getOrDefault() != this.tile.getRecipe().getId())
      this.craftSignal.set(this.tile.getRecipe().getId()); 
    if (((Float)this.progressSignal.getOrDefault()).floatValue() != this.tile.getCraftProgression())
      this.progressSignal.set(Float.valueOf(this.tile.getCraftProgression())); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mine\\ui\UIAutoCrafter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */