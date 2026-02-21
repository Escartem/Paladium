package fr.paladium.palamod.modules.luckyblock.ui;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.scrollbar.ScrollbarNode;
import fr.paladium.paladiumui.kit.selector.SelectorNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.paladiumui.kit.textfield.SearchTextFieldNode;
import fr.paladium.palamod.modules.luckyblock.ui.util.FilterOption;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.item.ItemNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.textfield.TextFieldNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.container.ContainerNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.flex.FlexNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.scrollbar.ScrollbarNode;
import fr.paladium.zephyrui.lib.ui.node.property.overflow.OverflowProperty;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.resource.ResourceUtils;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.MapSignal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.StringSignal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class UIChunkAnalyser extends UI {
  private static final ResourceLocation BACKGROUND = ResourceUtils.get("palamod", "textures/gui/chunk_analyser_background.png");
  
  private final MapSignal<Block, Integer> blockMapSignal = new MapSignal(new HashMap<>());
  
  private final StringSignal searchSignal = new StringSignal("");
  
  private final TextInfo blockNumberInfo = TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 25.0F).color(Color.GREEN);
  
  private final TextInfo blockNameInfo = TextInfo.create((IFont)PaladiumFont.MONTSERRAT_BOLD, 25.0F).color(Color.WHITE);
  
  private final Signal<FilterOption> filterOptionSignal = new Signal(FilterOption.NONE);
  
  public void init() {
    BackgroundNode.create(760.0D, 740.0D)
      .body(body -> {
          ImageNode.create(-35.0D, -35.0D).resource(BACKGROUND).size(body.getWidth() + 70.0D, body.getHeight() + 70.0D).attach(body);
          TextNode.create(31.0D, 5.0D).text(Text.create("chunk analyser", PaladiumText.HEADER.copy().color(new Color(38, 239, 46)).shadow(new Color(7, 133, 12)))).attach(body);
          CloseButtonNode.create(body.getWidth() - 48.8D, 31.0D).onClick(()).attach(body);
          SelectorNode.create(40.0D, 137.0D, 287.0D).zindex(1).zlevel(100.0D).body(()).size(287.0D, 54.0D).attach(body);
          ((SearchTextFieldNode)SearchTextFieldNode.create(455.0D, 137.0D, 255.0D, 43.0D).placeholder("RECHERCHER...").onChange(())).onEnter(()).attach(body);
          RectNode.create(39.5D, 205.0D, 681.0D, 517.0D).color(Color.BLACK.copyAlpha(0.15F)).body(()).attach(body);
        }).attach(this);
  }
  
  public void preDraw(double mouseX, double mouseY, float ticks) {
    if (this.blockMapSignal.isEmpty())
      searchBlock(); 
  }
  
  private void searchBlock() {
    Minecraft mc = Minecraft.func_71410_x();
    HashMap<Block, Integer> blocks = new HashMap<>();
    for (int x = 0; x < 16; x++) {
      for (int y = 255; y > 0; y--) {
        for (int z = 0; z < 16; z++) {
          int ox = mc.field_71439_g.field_70176_ah * 16;
          int oz = mc.field_71439_g.field_70164_aj * 16;
          boolean onlyTile = false;
          Block b = mc.field_71439_g.field_70170_p.func_147439_a(x + ox, y, z + oz);
          if ((((String)this.searchSignal.getOrDefault()).isEmpty() || b.func_149732_F().toLowerCase().contains(((String)this.searchSignal.getOrDefault()).toLowerCase())) && !b.equals(Blocks.field_150350_a))
            if (blocks.containsKey(b)) {
              int c = ((Integer)blocks.get(b)).intValue();
              c++;
              blocks.put(b, Integer.valueOf(c));
            } else {
              blocks.put(b, Integer.valueOf(1));
            }  
        } 
      } 
    } 
    this.blockMapSignal.set(blocks);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckybloc\\ui\UIChunkAnalyser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */