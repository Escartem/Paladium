package fr.paladium.palamod.modules.alchimiste.client.ui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.scrollbar.ScrollbarNode;
import fr.paladium.paladiumui.kit.slot.SlotNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palamod.modules.alchimiste.PAlchimiste;
import fr.paladium.palamod.modules.alchimiste.client.ui.node.EnchantNode;
import fr.paladium.palamod.modules.alchimiste.common.container.ContainerEnchantment;
import fr.paladium.palamod.modules.alchimiste.common.init.EnchantMod;
import fr.paladium.palamod.modules.alchimiste.common.network.clienttoserver.CSEnchantItem;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.scrollbar.ScrollbarNode;
import fr.paladium.zephyrui.lib.ui.node.property.overflow.OverflowProperty;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.resource.ResourceUtils;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.IntegerSignal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

@UIData(background = false, container = true)
public class UIEnchant extends UI {
  private static final ResourceLocation BOOK = ResourceUtils.get("palavanillagui", "textures/gui/enchanttable/book.png");
  
  private static final ResourceLocation BACKGROUND = ResourceUtils.get("palavanillagui", "textures/gui/enchanttable/enchant_table_background.png");
  
  private BackgroundNode background;
  
  private final IntegerSignal playerLevelSignal = new IntegerSignal(0);
  
  private final Signal<ItemStack> itemSignal = new Signal();
  
  private final TextInfo labelInfo = TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 16.0F, Color.WHITE);
  
  public UIEnchant() {
    super((Container)new ContainerEnchantment((Minecraft.func_71410_x()).field_71439_g.field_71071_by));
  }
  
  public void init() {
    ContainerNode.create(0.0D, -3.0D, 1920.0D, 1080.0D)
      .body(container -> this.background = (BackgroundNode)((BackgroundNode)BackgroundNode.create(760.0D, 689.0D).body(())).attach(container))
      
      .attach(this);
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    containerBounds(this.background.getAbsoluteX(), this.background.getAbsoluteY(), this.background.getWidth(), this.background.getHeight());
    ItemStack item = getContainer().func_75139_a(0).func_75211_c();
    int playerLevel = (Minecraft.func_71410_x()).field_71439_g.field_71068_ca;
    if (((Integer)this.playerLevelSignal.getOrDefault()).intValue() != playerLevel)
      this.playerLevelSignal.set(Integer.valueOf(playerLevel)); 
    if (this.itemSignal.getOrDefault() != item)
      this.itemSignal.set(item); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\clien\\ui\UIEnchant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */