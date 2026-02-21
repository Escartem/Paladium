package fr.paladium.palavanillagui.client.ui.anvil;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.constant.ColorConstant;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.slot.SlotNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.paladiumui.kit.textfield.TextFieldNode;
import fr.paladium.palavanillagui.client.ui.util.node.ArrowCraftingNode;
import fr.paladium.palavanillagui.client.ui.util.node.PlusNode;
import fr.paladium.palavanillagui.common.container.ContainerAnvil;
import fr.paladium.palavanillagui.common.packet.CSPacketRenameItemAnvil;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.textfield.TextFieldNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.resource.ResourceUtils;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.IntegerSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.StringSignal;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

@UIData(container = true, background = true)
@UIDataGuiscale(active = true)
public class UIAnvil extends UI {
  private static final ResourceLocation XP_BADGE = ResourceUtils.get("palavanillagui", "textures/gui/xp_icon.png");
  
  private final StringSignal nameSignal = new StringSignal("");
  
  private final ContainerAnvil containerAnvil;
  
  private ItemStack firstSlot;
  
  private final IntegerSignal maxCostSignal = new IntegerSignal(0);
  
  private final TextInfo costInfo = TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 20.0F, ColorConstant.GREEN);
  
  public UIAnvil(ContainerAnvil container) {
    super((Container)container);
    this.containerAnvil = container;
  }
  
  public void init() {
    BackgroundNode.create(625.0D, 670.0D)
      .body(body -> {
          containerBounds(body.getAbsoluteX(), body.getAbsoluteY(), body.getWidth(), body.getHeight());
          TextNode.create(31.0D, 5.0D).text(Text.create("Anvil", PaladiumText.HEADER.copy())).attach(body);
          CloseButtonNode.create(body.getWidth() - 48.8D, 31.0D).onClick(()).attach(body);
          List<Slot> slots = (getContainer()).field_75151_b;
          double margin = (body.getWidth() - 540.0D) / 2.0D;
          ((TextFieldNode)TextFieldNode.create(margin + 60.0D, 135.0D, 420.0D).placeholder("RENOMMER...").maxTextLength(30).marginLeft(10.0D).onInit(())).onChange(()).watch((Signal)this.nameSignal).attach(body);
          slots.forEach(());
          PlusNode.create(margin + 150.0D, 230.0D, 60.0D, 60.0D).attach(body);
          ArrowCraftingNode.create(margin + 240.0D + 30.0D + 50.0D, 230.0D).width(83.0D).attach(body);
          ImageNode.create(490.0D, 145.0D).resource(XP_BADGE).size(25.0D, 25.0D).onInit(()).watch((Signal)this.maxCostSignal).attach(body);
          ((TextNode)TextNode.create(485.0D, 146.0D).anchorX(Align.END).onInit(())).watch((Signal)this.maxCostSignal).attach(body);
        }).attach(this);
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    if (this.firstSlot != this.containerAnvil.func_75139_a(0).func_75211_c()) {
      this.firstSlot = this.containerAnvil.func_75139_a(0).func_75211_c();
      this.containerAnvil.updateItemName((this.firstSlot == null) ? "" : this.firstSlot.func_82833_r());
      (new CSPacketRenameItemAnvil((this.firstSlot == null) ? "" : this.firstSlot.func_82833_r())).send();
    } 
    if (!this.containerAnvil.getRepairedItemName().equalsIgnoreCase((String)this.nameSignal.getOrDefault()))
      this.nameSignal.set(this.containerAnvil.getRepairedItemName()); 
    if (this.containerAnvil.getMaximumCost() != ((Integer)this.maxCostSignal.getOrDefault()).intValue())
      this.maxCostSignal.set(Integer.valueOf(this.containerAnvil.getMaximumCost())); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\clien\\ui\anvil\UIAnvil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */