package fr.paladium.palavanillagui.client.ui.enchanttable;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.slot.SlotNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palavanillagui.client.ui.util.EnchantmentOption;
import fr.paladium.palavanillagui.client.ui.util.node.EnchantmentOptionNode;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.resource.ResourceUtils;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.ListSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.IntegerSignal;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerEnchantment;
import net.minecraft.inventory.Slot;

@UIData(container = true, background = true)
@UIDataGuiscale(active = true)
public class UIEnchantingTable extends UI {
  private static final Resource BOOK = Resource.of(ResourceUtils.get("palavanillagui", "textures/gui/enchanttable/book.png"));
  
  private static final Resource BACKGROUND = Resource.of(ResourceUtils.get("palavanillagui", "textures/gui/enchanttable/enchant_table_background.png"));
  
  private final ContainerEnchantment containerEnchant;
  
  private final IntegerSignal playerLevelSignal = new IntegerSignal(0);
  
  private final ListSignal<EnchantmentOption> enchantOptionSignal = new ListSignal(Arrays.asList(new EnchantmentOption[] { new EnchantmentOption(0), new EnchantmentOption(0), new EnchantmentOption(0) }));
  
  public UIEnchantingTable(Container container) {
    super(container);
    this.containerEnchant = (ContainerEnchantment)container;
  }
  
  public void init() {
    ((BackgroundNode)BackgroundNode.create(760.0D, 689.0D)
      .body(body -> {
          containerBounds(body.getAbsoluteX(), body.getAbsoluteY(), body.getWidth(), body.getHeight());
          ImageNode.create(-35.0D, -35.0D).resource(BACKGROUND).size(body.getWidth() + 70.0D, body.getHeight() + 70.0D).attach((Node)body);
          TextNode.create(31.0D, 5.0D).text(Text.create("enchant", PaladiumText.HEADER.copy())).attach((Node)body);
          ImageNode.create(68.0D, 124.0D, 126.0D, 126.0D).resource(BOOK).attach((Node)body);
          List<Slot> slots = (getContainer()).field_75151_b;
          slots.forEach(());
          CloseButtonNode.create(body.getWidth() - 48.8D, 31.0D).onClick(()).attach((Node)body);
          RectNode.create(257.0D, 124.0D, 420.0D, 248.0D).color(Color.BLACK.copyAlpha(0.15F)).onInit(()).watch((Signal)this.playerLevelSignal).watch((Signal)this.enchantOptionSignal).attach((Node)body);
        })).attach(this);
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    int playerLevel = (Minecraft.func_71410_x()).field_71439_g.field_71068_ca;
    if (((Integer)this.playerLevelSignal.getOrDefault()).intValue() != playerLevel)
      this.playerLevelSignal.set(Integer.valueOf(playerLevel)); 
    for (EnchantmentOption eOption : this.enchantOptionSignal.getOrDefault()) {
      int index = this.enchantOptionSignal.indexOf(eOption);
      if (eOption.getLevel() != this.containerEnchant.field_75167_g[index])
        this.enchantOptionSignal.set(index, new EnchantmentOption(this.containerEnchant.field_75167_g[index])); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\clien\\ui\enchanttable\UIEnchantingTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */