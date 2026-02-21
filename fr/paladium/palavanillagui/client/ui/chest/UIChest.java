package fr.paladium.palavanillagui.client.ui.chest;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.slot.SlotNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palavanillagui.client.ui.chest.container.EnderchestFakeSlotContainer;
import fr.paladium.translate.common.texttotranslate.TTT;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;

@UIData(container = true, background = true)
@UIDataGuiscale(active = true)
public class UIChest extends UI {
  private static final Resource ENDERCHEST_BACKGROUND = Resource.of(new ResourceLocation("palavanillagui", "textures/gui/chest/enderchest_background.png"));
  
  private static final Resource ENDERCHEST_SLOT = Resource.of(new ResourceLocation("palavanillagui", "textures/gui/chest/enderchest_slot.png"));
  
  private static final Pattern COLOR_PATTERN = Pattern.compile("(?i)§[0-9A-FK-OR]");
  
  private final IInventory lowerChestInventory;
  
  private final int numRows;
  
  private final boolean isEnderchest;
  
  private final double lowerInventoryHeight;
  
  private String ownerName;
  
  private String chestTitle;
  
  public UIChest(Container container) {
    super(container);
    this.lowerChestInventory = ((ContainerChest)container).func_85151_d();
    this.numRows = (int)Math.ceil((this.lowerChestInventory.func_70302_i_() / 9));
    String inventoryName = this.lowerChestInventory.func_145825_b();
    if (inventoryName.startsWith("§"))
      inventoryName = inventoryName.substring(2); 
    this.isEnderchest = inventoryName.matches("\\bEnderChest\\b.*\\(.+\\)");
    this.lowerInventoryHeight = this.isEnderchest ? 340.0D : (60.0D * this.numRows);
    if (this.isEnderchest) {
      Pattern pattern = Pattern.compile("\\(([^\\)]+)\\)");
      Matcher matcher = pattern.matcher(this.lowerChestInventory.func_145825_b());
      if (matcher.find()) {
        this.ownerName = matcher.group(1);
        if (this.ownerName.startsWith("§"))
          this.ownerName = this.ownerName.substring(2); 
      } 
    } 
    if (this.lowerChestInventory.func_145818_k_()) {
      this.chestTitle = this.lowerChestInventory.func_145825_b();
    } else {
      this.chestTitle = TTT.format(this.isEnderchest ? "vanilla.gui.chest.container.enderchest" : I18n.func_135052_a(COLOR_PATTERN.matcher(this.lowerChestInventory.func_145825_b()).replaceAll(""), new Object[0]), new Object[0]);
    } 
  }
  
  public void init() {
    BackgroundNode.create(748.8D, 470.0D + this.lowerInventoryHeight)
      .body(background -> {
          containerBounds(background.getAbsoluteX(), background.getAbsoluteY(), background.getWidth(), background.getHeight());
          double x = background.getWidth() / 2.0D - 270.0D;
          TextInfo info = PaladiumText.HEADER.copy();
          if (this.isEnderchest) {
            info.color(new Color(23, 148, 127)).shadow(new Color(13, 89, 77));
            ImageNode.create(-35.0D, -35.0D).resource(ENDERCHEST_BACKGROUND).size(background.getWidth() + 70.0D, background.getHeight() + 70.0D).attach(background);
            TextNode.create(36.0D, 110.0D).text(Text.create(TTT.format("vanilla.gui.enderchest.belongingto", new Object[] { this.ownerName }), TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 22.0F).color(Color.WHITE))).attach(background);
            EnderchestFakeSlotContainer.create(x, 160.0D + this.numRows * 60.0D, 5 - this.numRows).attach(background);
          } 
          TextNode.create(31.0D, 5.0D).text(Text.create(this.chestTitle, info)).attach(background);
          SlotNode slotNode = null;
          int i;
          for (i = 0; i < this.numRows; i++) {
            for (int k = 0; k < 9; k++) {
              Slot slot = (getContainer()).field_75151_b.get(i * 9 + k);
              slotNode = (SlotNode)SlotNode.create(k * 60.0D + x, i * 60.0D + 120.0D + (this.isEnderchest ? 40.0D : 0.0D), 60.0D, slot.getSlotIndex(), slot.field_75224_c).attach(background);
              if (this.isEnderchest)
                slotNode.texture(ENDERCHEST_SLOT, ENDERCHEST_SLOT); 
            } 
          } 
          for (i = 0; i < 4; i++) {
            for (int k = 0; k < 9; k++) {
              Slot slot = (getContainer()).field_75151_b.get(this.lowerChestInventory.func_70302_i_() + i * 9 + k);
              double y = i * 60.0D + this.lowerInventoryHeight + 40.0D + 120.0D;
              if (slot.getSlotIndex() < 9)
                y += 40.0D; 
              slotNode = (SlotNode)SlotNode.create(k * 60.0D + x, y, 60.0D, slot.getSlotIndex(), slot.field_75224_c).attach(background);
              if (this.isEnderchest)
                slotNode.texture(ENDERCHEST_SLOT, ENDERCHEST_SLOT); 
            } 
          } 
          CloseButtonNode.create(background.getWidth() - 48.8D, 25.0D).onClick(()).attach(background);
        }).attach(this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\clien\\ui\chest\UIChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */