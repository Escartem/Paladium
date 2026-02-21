package fr.paladium.palavanillagui.client.ui.chest;

import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.ironchest.ContainerIronChest;
import cpw.mods.ironchest.IronChestType;
import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.slot.SlotNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;

@UIData(container = true, background = true)
@UIDataGuiscale(active = true)
public class UICpwChest extends UI {
  private static final Resource DIRTCHEST_BACKGROUND = Resource.of(new ResourceLocation("palavanillagui", "textures/gui/chest/dirtchest_background.png"));
  
  private static final Resource DIRTCHEST_SLOT = Resource.of(new ResourceLocation("palavanillagui", "textures/gui/chest/dirtchest_slot.png"));
  
  private IronChestType type;
  
  private final int numRows;
  
  private double rowHeight;
  
  private boolean isDirtChest;
  
  private final TextInfo textInfo = PaladiumText.HEADER.copy();
  
  public UICpwChest(Container container) {
    super(container);
    try {
      this.type = (IronChestType)ReflectionHelper.getPrivateValue(ContainerIronChest.class, container, new String[] { "type" });
    } catch (Exception e) {
      e.printStackTrace();
    } 
    switch (this.type) {
      case IRON:
        this.textInfo.color(new Color(255, 220, 205)).shadow(new Color(161, 104, 79));
        break;
      case GOLD:
        this.textInfo.color(new Color(253, 245, 95)).shadow(new Color(178, 100, 17));
        break;
      case DIAMOND:
        this.textInfo.color(new Color(74, 237, 217)).shadow(new Color(26, 170, 167));
        break;
      case CRYSTAL:
        this.textInfo.color(new Color(136, 225, 244)).shadow(new Color(81, 142, 176));
        break;
      case OBSIDIAN:
        this.textInfo.color(new Color(133, 37, 255)).shadow(new Color(64, 1, 126));
        break;
      case DIRTCHEST9000:
        this.textInfo.color(new Color(157, 93, 33)).shadow(new Color(108, 59, 14));
        this.isDirtChest = true;
        break;
    } 
    this.numRows = this.type.getRowCount();
    this.rowHeight = 60.0D * this.numRows + (this.isDirtChest ? 120.0D : 0.0D);
    if (this.isDirtChest)
      this.rowHeight += 120.0D; 
  }
  
  public void init() {
    BackgroundNode.create(748.8D + ((this.type.getRowLength() == 12) ? 100.0D : 0.0D), 455.81D + this.rowHeight)
      .body(background -> {
          containerBounds(background.getAbsoluteX(), background.getAbsoluteY(), background.getWidth(), background.getHeight());
          if (this.isDirtChest)
            ImageNode.create(-35.0D, -35.0D).resource(DIRTCHEST_BACKGROUND).size(background.getWidth() + 70.0D, background.getHeight() + 70.0D).attach(background); 
          TextNode.create(31.0D, 5.0D).text(Text.create(I18n.func_135052_a(this.type.name(), new Object[0]), this.textInfo)).attach(background);
          SlotNode slotNode = null;
          double x = (background.getWidth() - this.type.getRowLength() * 60.0D) / 2.0D;
          for (int i = 0; i < this.numRows; i++) {
            for (int k = 0; k < this.type.getRowLength(); k++) {
              int slotIndex = i * this.type.getRowLength() + k;
              Slot slot = (getContainer()).field_75151_b.get(slotIndex);
              slotNode = (SlotNode)SlotNode.create(k * 60.0D + x, i * 60.0D + 120.0D + (this.isDirtChest ? (this.rowHeight / 2.0D - 30.0D) : 0.0D), 60.0D, slot.getSlotIndex(), slot.field_75224_c).attach(background);
              if (this.isDirtChest)
                slotNode.texture(DIRTCHEST_SLOT, DIRTCHEST_SLOT); 
            } 
          } 
          double xInv = (background.getWidth() - 540.0D) / 2.0D;
          for (int j = 0; j < 4; j++) {
            for (int k = 0; k < 9; k++) {
              int slotIndex = this.numRows * this.type.getRowLength() + j * 9 + k;
              Slot slot = (getContainer()).field_75151_b.get(slotIndex);
              double y = j * 60.0D + this.rowHeight + 40.0D + 120.0D;
              if (slot.getSlotIndex() < 9)
                y += 40.0D; 
              slotNode = (SlotNode)SlotNode.create(k * 60.0D + xInv, y, 60.0D, slot.getSlotIndex(), slot.field_75224_c).attach(background);
              if (this.isDirtChest)
                slotNode.texture(DIRTCHEST_SLOT, DIRTCHEST_SLOT); 
            } 
          } 
          CloseButtonNode.create(background.getWidth() - 48.8D, 25.0D).onClick(()).attach(background);
        }).attach(this);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\clien\\ui\chest\UICpwChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */