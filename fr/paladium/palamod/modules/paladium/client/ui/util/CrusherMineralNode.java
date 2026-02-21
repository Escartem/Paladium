package fr.paladium.palamod.modules.paladium.client.ui.util;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.paladium.client.ui.UICrusher;
import fr.paladium.palamod.modules.paladium.common.logics.TileCrusher;
import fr.paladium.palamod.modules.paladium.network.PacketCrusherButton;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.item.ItemNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.progress.ProgressNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.resource.ResourceUtils;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.ListSignal;
import java.util.HashMap;
import lombok.NonNull;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CrusherMineralNode extends RectNode {
  private static final Resource ENDIUM_CORNER = Resource.of(ResourceUtils.get("palamod", "textures/gui/crusher/endium_corner.png"));
  
  private static final Resource PALADIUM_CORNER = Resource.of(ResourceUtils.get("palamod", "textures/gui/crusher/paladium_corner.png"));
  
  private static final Resource TITANE_CORNER = Resource.of(ResourceUtils.get("palamod", "textures/gui/crusher/titane_corner.png"));
  
  private static final Resource AMETHYST_CORNER = Resource.of(ResourceUtils.get("palamod", "textures/gui/crusher/amethyst_corner.png"));
  
  private static final HashMap<Item, Resource> CORNER_MAP = new HashMap<Item, Resource>() {
    
    };
  
  private Color loadingBackgroundColor = new Color(50, 50, 50);
  
  private Color loadingColor = Color.WHITE;
  
  private Item item = ItemsRegister.AMETHYST_INGOT;
  
  private Item itemNeeded = ItemsRegister.FRUIT_EGGPLANT;
  
  private final TextInfo titleInfo = TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 24.0F).color(Color.WHITE);
  
  private final TextInfo subTitleInfo = TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 16.0F).color(Color.GRAY);
  
  protected CrusherMineralNode(double x, double y, double width, double height) {
    super(x, y, width, height);
    hoveredColor(Color.BLACK.copyAlpha(0.5F));
    onClick((node, mouseX, mouseY, clickType) -> {
          TileCrusher tile = ((UICrusher)ZUI.getUI(UICrusher.class)).getTile();
          if (!TileCrusher.EnumCrusherResult.NONE.equals(tile.getExtractItem()))
            return; 
          TileCrusher.EnumCrusherResult craft = TileCrusher.EnumCrusherResult.findByItem(this.item);
          if (craft != null)
            PalaMod.getNetwork().sendToServer((IMessage)new PacketCrusherButton(craft, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e)); 
        });
    ((RectNode)RectNode.create(0.0D, 0.0D, getWidth(), getHeight())
      .color(Color.BLACK.copyAlpha(0.5F))
      .visible(node -> false)
      .zlevel(1.0D)
      .zindex(1)
      .onInit(dark -> {
          Signal<TileCrusher.EnumCrusherResult> craftSignal = ((UICrusher)ZUI.getUI(UICrusher.class)).getCraftSignal();
          TileCrusher.EnumCrusherResult typeResult = TileCrusher.EnumCrusherResult.findByItem(this.item);
          boolean visible = (craftSignal.getOrDefault() == TileCrusher.EnumCrusherResult.NONE) ? false : ((typeResult != craftSignal.getOrDefault()));
          dark.visible(());
        })).watch(((UICrusher)ZUI.getUI(UICrusher.class)).getCraftSignal())
      .attach((Node)this);
    ((ImageNode)ImageNode.create(getWidth() - 25.0D, -1.0D, 25.0D, 25.0D)
      .resource(CORNER_MAP.get(this.item))
      .visible(node -> false)
      .onInit(dark -> {
          Signal<TileCrusher.EnumCrusherResult> craftSignal = ((UICrusher)ZUI.getUI(UICrusher.class)).getCraftSignal();
          TileCrusher.EnumCrusherResult typeResult = TileCrusher.EnumCrusherResult.findByItem(this.item);
          dark.resource(CORNER_MAP.get(this.item)).visible(());
        })).watch(((UICrusher)ZUI.getUI(UICrusher.class)).getCraftSignal())
      .attach((Node)this);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    UICrusher uiCrush = (UICrusher)getUi();
    ListSignal<Integer> plantTankSignal = uiCrush.getPlantTank();
    TextNode.create(7.0D, 5.0D)
      .text(Text.create((new ItemStack(this.itemNeeded)).func_82833_r(), this.titleInfo))
      .attach((Node)this);
    TileCrusher.EnumCrusherRecipes type = TileCrusher.EnumCrusherRecipes.findCurrentRecipe(this.itemNeeded);
    ((TextNode)TextNode.create(7.0D, 35.0D)
      .onInit(textNode -> {
          int content = ((Integer)plantTankSignal.get(TileCrusher.EnumCrusherRecipes.findCurrentRecipe(this.itemNeeded).ordinal())).intValue();
          textNode.text(Text.create(content + "/" + type.getBarSize(), this.subTitleInfo));
        })).watch((Signal)plantTankSignal)
      .attach((Node)this);
    ((ProgressNode)ProgressNode.create(0.0D, getHeight() - 8.0D, getWidth(), 8.0D)
      .color(this.loadingBackgroundColor, this.loadingColor)
      .progress(0.0F)
      .onInit(prog -> {
          int fillContent = ((Integer)plantTankSignal.get(TileCrusher.EnumCrusherRecipes.findCurrentRecipe(this.itemNeeded).ordinal())).intValue();
          float newFillProgression = fillContent / type.getBarSize();
          prog.progress(newFillProgression);
        })).watch((Signal)plantTankSignal)
      .attach((Node)this);
    RectNode.create(getWidth() - 77.0D, 4.0D, 55.0D, 55.0D)
      .color(Color.BLACK.copyAlpha(0.15F))
      .body(rect -> ItemNode.create(4.0D, 4.0D, 47.0D).item(this.item).attach(rect))
      
      .attach((Node)this);
  }
  
  public static CrusherMineralNode create(double x, double y, double width, double height) {
    return new CrusherMineralNode(x, y, width, height);
  }
  
  public <T extends CrusherMineralNode> T loadingColor(Color color) {
    this.loadingColor = color;
    return (T)this;
  }
  
  public <T extends CrusherMineralNode> T loadingBackgroundColor(Color color) {
    this.loadingBackgroundColor = color;
    return (T)this;
  }
  
  public <T extends CrusherMineralNode> T item(Item item) {
    this.item = item;
    return (T)this;
  }
  
  public <T extends CrusherMineralNode> T itemNeeded(Item itemNeeded) {
    this.itemNeeded = itemNeeded;
    return (T)this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\clien\\u\\util\CrusherMineralNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */