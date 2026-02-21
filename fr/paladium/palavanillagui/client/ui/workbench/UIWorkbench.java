package fr.paladium.palavanillagui.client.ui.workbench;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.slot.SlotNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palamixins.mixin.common.container.MixinContainerWorkbench;
import fr.paladium.palavanillagui.client.ui.util.IBypassAble;
import fr.paladium.palavanillagui.client.ui.util.node.ArrowCraftingNode;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.context.InternalContext;
import java.util.List;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import org.lwjgl.input.Keyboard;

@UIData(container = true, background = true)
@UIDataGuiscale(active = true)
public class UIWorkbench extends UI implements IBypassAble {
  private boolean bypass;
  
  public UIWorkbench(Container container) {
    super(container);
  }
  
  public void init() {
    BackgroundNode.create(625.0D, 670.0D)
      .body(body -> {
          containerBounds(body.getAbsoluteX(), body.getAbsoluteY(), body.getWidth(), body.getHeight());
          TextNode.create(31.0D, 5.0D).text(Text.create("Crafting", PaladiumText.HEADER.copy())).attach(body);
          CloseButtonNode.create(body.getWidth() - 48.8D, 31.0D).onClick(()).attach(body);
          List<Slot> slots = (getContainer()).field_75151_b;
          double margin = (body.getWidth() - 540.0D) / 2.0D;
          slots.forEach(());
          ArrowCraftingNode.create(margin + 240.0D + 30.0D, 196.0D).attach(body);
        }).attach(this);
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
              MixinContainerWorkbench cwMixin = (MixinContainerWorkbench)getContainer();
              GuiCrafting guiCrafting = new GuiCrafting((Minecraft.func_71410_x()).field_71439_g.field_71071_by, cwMixin.getWorld(), cwMixin.getPosX(), cwMixin.getPosY(), cwMixin.getPosZ());
              guiCrafting.field_147002_h.field_75152_c = (getContainer()).field_75152_c;
              Minecraft.func_71410_x().func_147108_a((GuiScreen)guiCrafting);
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


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\clien\\ui\workbench\UIWorkbench.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */