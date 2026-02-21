package fr.paladium.palaautomation.client.ui;

import fr.paladium.palaautomation.client.ui.node.PipeFakeSlotNode;
import fr.paladium.palaautomation.client.ui.node.PipeSlotNode;
import fr.paladium.palaautomation.common.container.ContainerCrafter;
import fr.paladium.palaautomation.common.packet.BBCrafterChangeStatePacket;
import fr.paladium.palaautomation.common.packet.CSPipeMachinePickupItemsPacket;
import fr.paladium.palaautomation.common.packet.CSPipeMachineUpdatePacket;
import fr.paladium.palaautomation.common.tile.IPipeMachine;
import fr.paladium.palaautomation.common.tile.impl.TileEntityCrafter;
import fr.paladium.palaautomation.common.tile.impl.TileEntityReceiver;
import fr.paladium.palaautomation.common.tile.util.AutoCrafterRecipe;
import fr.paladium.palaautomation.common.tile.util.PipeItemData;
import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.font.PaladiumFont;
import fr.paladium.paladiumui.kit.slot.SlotNode;
import fr.paladium.paladiumui.kit.sw.SwitchNode;
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
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

@UIDataGuiscale(active = true)
@UIData(background = true, container = true)
public class UICrafter extends UI implements IAutomationUI {
  private final TileEntityCrafter tile;
  
  private final IntegerSignal quantitySignal;
  
  private SwitchNode switchNode;
  
  public void setSwitchNode(SwitchNode switchNode) {
    this.switchNode = switchNode;
  }
  
  public TileEntityCrafter getTile() {
    return this.tile;
  }
  
  public SwitchNode getSwitchNode() {
    return this.switchNode;
  }
  
  public UICrafter(TileEntityCrafter tile) {
    super((Container)new ContainerCrafter(tile, (EntityPlayer)(Minecraft.func_71410_x()).field_71439_g));
    this.tile = tile;
    this.quantitySignal = new IntegerSignal(1);
    CSPipeMachineUpdatePacket.send((IPipeMachine)tile);
  }
  
  private String getTitle() {
    return "CRAFTER";
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
    List<Slot> slots = (getContainer()).field_75151_b;
    slots.forEach(slot -> {
          if (slot.field_75224_c instanceof TileEntityCrafter) {
            PipeSlotNode mainSlot = (PipeSlotNode)PipeSlotNode.create(1051.0D, 406.0D, 60.0D, slot.getSlotIndex(), (IInventory)this.tile, (IPipeMachine)this.tile).attach(this);
            RectNode.create(1051.0D, 406.0D, 60.0D, 60.0D).color(Color.TRANSPARENT).onClick(()).zindex(1).attach(this);
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
    AutoCrafterRecipe recipe = this.tile.findRecipe();
    double startX = 758.0D;
    double startY = 355.0D;
    for (Map.Entry<Integer, TileEntityReceiver> entry : (Iterable<Map.Entry<Integer, TileEntityReceiver>>)recipe.getInputReceivers().entrySet()) {
      int slotId = ((Integer)entry.getKey()).intValue();
      double x = 818.0D + 60.0D * (slotId % 3);
      double y = 355.0D + 60.0D * Math.floor(slotId / 3.0D);
      TileEntityReceiver receiver = entry.getValue();
      PipeFakeSlotNode pipeFakeSlotNode = (PipeFakeSlotNode)PipeFakeSlotNode.create(x, y, 60.0D, (IPipeMachine)receiver).onClick((node, mouseX, mouseY, clickType) -> {
            if ((getMc()).field_71439_g.field_71071_by.func_70445_o() != null)
              return; 
            CSPipeMachinePickupItemsPacket.send((IPipeMachine)receiver, ((Integer)this.quantitySignal.getOrDefault()).intValue());
          }).attach(this);
    } 
    ((IntegerFieldNode)((IntegerFieldNode)IntegerFieldNode.create(1019.0D, 483.0D, 126.0D)
      .backgroundColor(Color.TRANSPARENT)
      .borderColor(Color.WHITE)
      .focusColor(Color.WHITE)
      .value(((Integer)this.quantitySignal.getOrDefault()).intValue())
      .min(1)
      .marginLeft(23.0D)
      .onChange((node, before, after) -> {
          int max = getMaxQuantity();
          int quantity = node.getValue();
          if (quantity > max) {
            this.quantitySignal.set(Integer.valueOf(max));
            return;
          } 
          this.quantitySignal.set(Integer.valueOf(quantity));
        })).height(56.0D)
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
    String[] states = { "ON", "OFF" };
    this
      
      .switchNode = (SwitchNode)((SwitchNode)SwitchNode.create(820.0D, 555.0D, 175.0D, 42.0D).state(states).index(this.tile.isEnabled() ? 0 : 1).onChange((node, index) -> (new BBCrafterChangeStatePacket(this.tile.field_145851_c, this.tile.field_145848_d, this.tile.field_145849_e, (((Integer)this.switchNode.getStateIndex().getOrDefault()).intValue() == 0))).send())).onClick((node, mouseX, mouseY, clickType) -> (new BBCrafterChangeStatePacket(this.tile.field_145851_c, this.tile.field_145848_d, this.tile.field_145849_e, (((Integer)this.switchNode.getStateIndex().getOrDefault()).intValue() == 0))).send()).attach(this);
  }
  
  private int getMaxQuantity() {
    int max = 0;
    PipeItemData data = getItemData();
    if (data != null)
      max = data.getCount(); 
    AutoCrafterRecipe recipe = this.tile.findRecipe();
    for (Map.Entry<Integer, TileEntityReceiver> entry : (Iterable<Map.Entry<Integer, TileEntityReceiver>>)recipe.getInputReceivers().entrySet()) {
      TileEntityReceiver receiver = entry.getValue();
      int count = (receiver.getItemData() == null) ? 0 : receiver.getItemData().getCount();
      if (count > max)
        max = count; 
    } 
    return Math.min(max, 576);
  }
  
  public void postDraw(double mouseX, double mouseY, float ticks) {}
  
  public boolean close() {
    (new BBCrafterChangeStatePacket(this.tile.field_145851_c, this.tile.field_145848_d, this.tile.field_145849_e, (((Integer)this.switchNode.getStateIndex().getOrDefault()).intValue() == 0))).send();
    return super.close();
  }
  
  public IPipeMachine getPipeMachine() {
    return (IPipeMachine)this.tile;
  }
  
  public IntegerSignal getQuantitySignal() {
    return this.quantitySignal;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\clien\\ui\UICrafter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */