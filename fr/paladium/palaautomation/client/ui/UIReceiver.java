package fr.paladium.palaautomation.client.ui;

import fr.paladium.palaautomation.client.ui.node.PipeSlotNode;
import fr.paladium.palaautomation.common.container.ContainerReceiver;
import fr.paladium.palaautomation.common.packet.CSPipeMachinePickupItemsPacket;
import fr.paladium.palaautomation.common.tile.IPipeMachine;
import fr.paladium.palaautomation.common.tile.impl.TileEntityReceiver;
import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.slot.SlotNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.paladiumui.kit.textfield.IntegerFieldNode;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.draw.text.utils.TextMode;
import fr.paladium.zephyrui.lib.font.dto.font.IFont;
import fr.paladium.zephyrui.lib.font.dto.text.TextInfo;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.utils.align.Align;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.IntegerSignal;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

@UIDataGuiscale(active = true)
@UIData(background = true, container = true)
public class UIReceiver extends UI implements IAutomationUI {
  private final TileEntityReceiver tile;
  
  private final IntegerSignal quantitySignal;
  
  public TileEntityReceiver getTile() {
    return this.tile;
  }
  
  public UIReceiver(TileEntityReceiver tile) {
    super((Container)new ContainerReceiver(tile, (EntityPlayer)(Minecraft.func_71410_x()).field_71439_g));
    this.tile = tile;
    this.quantitySignal = new IntegerSignal(1);
  }
  
  private String getTitle() {
    return "RECEIVER";
  }
  
  public void init() {
    BackgroundNode.create(787.0D, 769.0D)
      .body(back -> containerBounds(back.getAbsoluteX(), back.getAbsoluteY(), back.getWidth(), back.getHeight()))
      
      .attach(this);
    ((TextNode)TextNode.create(624.0D, 182.0D)
      .text(Text.create(getTitle(), PaladiumText.HEADER.copy()))
      .onInit(node -> node.getText().text(getTitle())))
      .attach(this);
    CloseButtonNode.create(1288.0D, 198.0D)
      .onClick((node, mouseX, mouseY, clickType) -> ZUI.close(this))
      .attach(this);
    ((IntegerFieldNode)((IntegerFieldNode)IntegerFieldNode.create(899.0D, 471.0D, 126.0D)
      .backgroundColor(Color.TRANSPARENT)
      .borderColor(Color.WHITE)
      .focusColor(Color.WHITE)
      .value(((Integer)this.quantitySignal.getOrDefault()).intValue())
      .min(1)
      .marginLeft(23.0D)
      .onChange((node, before, after) -> setQuantity(node.getValue())))
      
      .height(56.0D)
      .body(ifn -> {
          TextNode.create(ifn.getWidth() - 60.0D, ifn.dh(2.0D)).mode(TextMode.BOX).text(Text.create("+", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 35.0F, Color.WHITE), Align.CENTER, Align.CENTER)).anchor(Align.CENTER, Align.CENTER).zindex(1).size(40.0D, 56.0D).onClick(()).attach(ifn);
          TextNode.create(ifn.getWidth() - 20.0D, ifn.dh(2.0D)).mode(TextMode.BOX).text(Text.create("-", TextInfo.create((IFont)PaladiumFont.PIXEL_NES, 30.0F, Color.WHITE), Align.CENTER, Align.CENTER)).anchor(Align.CENTER, Align.CENTER).size(40.0D, 56.0D).zindex(1).onClick(()).attach(ifn);
        }).onInit(node -> {
          IntegerSignal quantitySignal = getQuantitySignal();
          if (quantitySignal == null)
            return; 
          int currentValue = ((Integer)quantitySignal.getOrDefault()).intValue();
          if (currentValue == node.getValue())
            return; 
          node.value(currentValue);
        })).watch((Signal)this.quantitySignal)
      .attach(this);
    List<Slot> slots = (getContainer()).field_75151_b;
    slots.forEach(slot -> {
          if (slot.field_75224_c instanceof TileEntityReceiver) {
            int slotId = slot.getSlotIndex();
            PipeSlotNode slotNode = (PipeSlotNode)PipeSlotNode.create(931.0D, 394.0D, 60.0D, slot.getSlotIndex(), slot.field_75224_c, (IPipeMachine)this.tile).attach(this);
            slotNode.itemSize(40.0D);
            RectNode.create(931.0D, 394.0D, 60.0D, 60.0D).color(Color.TRANSPARENT).onClick(()).zindex(1).attach(this);
          } else if (slot.field_75224_c instanceof net.minecraft.entity.player.InventoryPlayer) {
            double x = 694.0D;
            double y = 620.0D;
            int i = (slot.getSlotIndex() - 9) % 9;
            int yLevel = (int)Math.floor(((slot.getSlotIndex() - 9) / 9));
            if (slot.getSlotIndex() > 9 && slot.getSlotIndex() < 36) {
              x += i * 60.0D;
              y += yLevel * 60.0D;
            } 
            if (slot.getSlotIndex() >= 0 && slot.getSlotIndex() <= 8) {
              x += slot.getSlotIndex() * 60.0D;
              y += 218.0D;
            } 
            SlotNode.create(x, y, 60.0D, slot.getSlotIndex(), slot.field_75224_c).attach(this);
          } 
        });
  }
  
  public void postDraw(double mouseX, double mouseY, float ticks) {
    super.postDraw(mouseX, mouseY, ticks);
  }
  
  public boolean close() {
    return super.close();
  }
  
  public IntegerSignal getQuantitySignal() {
    return this.quantitySignal;
  }
  
  public IPipeMachine getPipeMachine() {
    return (IPipeMachine)this.tile;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\clien\\ui\UIReceiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */