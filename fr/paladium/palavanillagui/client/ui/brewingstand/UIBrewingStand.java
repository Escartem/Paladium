package fr.paladium.palavanillagui.client.ui.brewingstand;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.slot.SlotNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palamixins.mixin.common.container.MixinContainerBrewingStand;
import fr.paladium.palavanillagui.client.ui.util.IBypassAble;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.progress.ProgressNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.FloatSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.IntegerSignal;
import java.util.List;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiBrewingStand;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

@UIData(container = true, background = true)
@UIDataGuiscale(active = true)
public class UIBrewingStand extends UI implements IBypassAble {
  private static final Resource POTION_PLACEHOLDER = Resource.of(new ResourceLocation("palavanillagui", "textures/gui/potion_placeholder.png"));
  
  private static final Resource BUBBLE_BACKGROUND = Resource.of(new ResourceLocation("palavanillagui", "textures/gui/bubble_background.png"));
  
  private static final Resource BUBBLE_FOREGROUND = Resource.of(new ResourceLocation("palavanillagui", "textures/gui/bubble_foreground.png"));
  
  private static final Resource ARROW_BACKGROUND = Resource.of(new ResourceLocation("palavanillagui", "textures/gui/arrow_background.png"));
  
  private static final Resource ARROW_FOREGROUND = Resource.of(new ResourceLocation("palavanillagui", "textures/gui/arrow_foreground.png"));
  
  private static final Resource BREWING_LINE = Resource.of(new ResourceLocation("palavanillagui", "textures/gui/brewing_line.png"));
  
  private final TileEntityBrewingStand tileBrewing;
  
  private final IntegerSignal brewTimeSignal = new IntegerSignal(0);
  
  private final FloatSignal bubbleProgSignal = new FloatSignal(0.0F);
  
  private boolean bypass = false;
  
  public UIBrewingStand(Container container) {
    super(container);
    this.tileBrewing = ((MixinContainerBrewingStand)container).getTileEntity();
  }
  
  public void init() {
    BackgroundNode.create(625.0D, 670.0D)
      .body(body -> {
          containerBounds(body.getAbsoluteX(), body.getAbsoluteY(), body.getWidth(), body.getHeight());
          TextNode.create(31.0D, 5.0D).text(Text.create("Brewing", PaladiumText.HEADER.copy())).attach(body);
          CloseButtonNode.create(body.getWidth() - 48.8D, 31.0D).onClick(()).attach(body);
          ((ProgressNode)ProgressNode.create(74.0D, 144.0D, 70.0D, 174.0D).progress(0.5F).direction(ProgressNode.ProgressDirection.BOTTOM_TO_TOP).resource(BUBBLE_BACKGROUND, BUBBLE_FOREGROUND).onInit(())).watch((Signal)this.bubbleProgSignal).attach(body);
          ImageNode.create(197.0D, 188.0D, 226.0D, 70.0D).resource(BREWING_LINE).linear(false).attach(body);
          ((ProgressNode)ProgressNode.create(476.0D, 131.0D, 66.0D, 187.0D).progress(((Integer)this.brewTimeSignal.getOrDefault()).intValue() / 400.0F).direction(ProgressNode.ProgressDirection.TOP_TO_BOTTOM).resource(ARROW_BACKGROUND, ARROW_FOREGROUND).onInit(())).watch((Signal)this.brewTimeSignal).attach(body);
          List<Slot> slots = (getContainer()).field_75151_b;
          double margin = (body.getWidth() - 540.0D) / 2.0D;
          slots.forEach(());
        }).attach(this);
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    int brewTime = (this.tileBrewing.func_145935_i() == 0) ? 0 : Math.max(400 - this.tileBrewing.func_145935_i(), 0);
    float bubbleTime = (brewTime % 14);
    if (((Integer)this.brewTimeSignal.getOrDefault()).intValue() != brewTime)
      this.brewTimeSignal.set(Integer.valueOf(brewTime)); 
    if (((Float)this.bubbleProgSignal.getOrDefault()).floatValue() != bubbleTime)
      this.bubbleProgSignal.set(Float.valueOf(bubbleTime)); 
  }
  
  public void keyPressed(char c, int keyCode, @NonNull InternalContext context) {
    if (context == null)
      throw new NullPointerException("context is marked non-null but is null"); 
    if (context.isCancelled())
      return; 
    if (keyCode == (Minecraft.func_71410_x()).field_71474_y.field_151445_Q.func_151463_i() && getUi().isOnTop()) {
      context.cancel(() -> {
            if (Keyboard.isKeyDown(56)) {
              this.bypass = true;
              GuiBrewingStand guiBrewing = new GuiBrewingStand((Minecraft.func_71410_x()).field_71439_g.field_71071_by, this.tileBrewing);
              guiBrewing.field_147002_h.field_75152_c = (getContainer()).field_75152_c;
              Minecraft.func_71410_x().func_147108_a((GuiScreen)guiBrewing);
              return;
            } 
            ZUI.close(this);
          });
      return;
    } 
    super.keyPressed(c, keyCode, context);
  }
  
  public boolean isBypass() {
    return this.bypass;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\clien\\ui\brewingstand\UIBrewingStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */