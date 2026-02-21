package fr.paladium.palaspawner.client.ui;

import fr.paladium.paladiumui.kit.background.BackgroundNode;
import fr.paladium.paladiumui.kit.button.impl.CloseButtonNode;
import fr.paladium.paladiumui.kit.slot.SlotNode;
import fr.paladium.paladiumui.kit.sw.SwitchNode;
import fr.paladium.paladiumui.kit.text.PaladiumText;
import fr.paladium.palaspawner.common.container.ContainerSpawnerController;
import fr.paladium.palaspawner.common.manager.SpawnerManager;
import fr.paladium.palaspawner.common.network.packet.BBRequestController;
import fr.paladium.palaspawner.common.network.packet.CSChangeSpawnerController;
import fr.paladium.palaspawner.common.spawner.ControllerState;
import fr.paladium.palaspawner.common.spawner.blueprint.ASpawnerBluePrint;
import fr.paladium.palaspawner.common.tile.Tier;
import fr.paladium.palaspawner.common.tile.TileEntitySpawnerController;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.draw.text.builder.Text;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.core.data.UIData;
import fr.paladium.zephyrui.lib.ui.core.data.guiscale.UIDataGuiscale;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.text.TextNode;
import fr.paladium.zephyrui.lib.ui.node.impl.structure.sw.SwitchNode;
import fr.paladium.zephyrui.lib.utils.click.ClickType;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;

@UIData(background = true, container = true)
@UIDataGuiscale(active = true)
public class UISpawnerController extends UI {
  public void setSwitchNode(SwitchNode switchNode) {
    this.switchNode = switchNode;
  }
  
  public void setOpenedState(ControllerState openedState) {
    this.openedState = openedState;
  }
  
  public static final Resource LOCK = Resource.of(new ResourceLocation("palaspawner", "textures/ui/lock.png"));
  
  public static final String[] STATES = new String[] { ControllerState.MANUAL
      .name(), ControllerState.AUTOMATIC
      .name() };
  
  private final TileEntitySpawnerController tile;
  
  private final Signal<Tier> tierSignal;
  
  private final List<SlotNode> controllerSlots;
  
  private SwitchNode switchNode;
  
  private ControllerState openedState;
  
  public TileEntitySpawnerController getTile() {
    return this.tile;
  }
  
  public Signal<Tier> getTierSignal() {
    return this.tierSignal;
  }
  
  public List<SlotNode> getControllerSlots() {
    return this.controllerSlots;
  }
  
  public SwitchNode getSwitchNode() {
    return this.switchNode;
  }
  
  public ControllerState getOpenedState() {
    return this.openedState;
  }
  
  public UISpawnerController(TileEntitySpawnerController tile) {
    super((Container)new ContainerSpawnerController(tile, (EntityPlayer)(Minecraft.func_71410_x()).field_71439_g));
    this.tile = tile;
    this.controllerSlots = new ArrayList<>();
    this.openedState = tile.getControllerState();
    String id = tile.getLinkedBluePrintId();
    ASpawnerBluePrint bluePrint = SpawnerManager.getInstance().getBluePrint(id);
    Tier tier = (bluePrint == null) ? Tier.ZERO : bluePrint.getStructureTier();
    this.tierSignal = new Signal(tier);
    BBRequestController bBRequestController = new BBRequestController(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
    bBRequestController.send();
  }
  
  private String getTitle() {
    return "CONTROLLER " + ((Tier)this.tierSignal.getOrDefault()).getRomanId();
  }
  
  public void init() {
    BackgroundNode.create(787.0D, 769.0D)
      .body(back -> containerBounds(back.getAbsoluteX(), back.getAbsoluteY(), back.getWidth(), back.getHeight()))
      
      .attach(this);
    ((TextNode)TextNode.create(624.0D, 182.0D)
      .text(Text.create(getTitle(), PaladiumText.HEADER.copy()))
      .onInit(node -> node.getText().text(getTitle())))
      .watch(this.tierSignal)
      .attach(this);
    CloseButtonNode.create(1288.0D, 198.0D)
      .onClick((node, mouseX, mouseY, clickType) -> ZUI.close(this))
      .attach(this);
    this
      
      .switchNode = (SwitchNode)SwitchNode.create(827.0D, 514.0D, 256.0D, 42.0D).state(STATES).index(this.openedState.name()).onChange((node, state) -> {
        
        }).attach(this);
    List<Slot> slots = (getContainer()).field_75151_b;
    slots.forEach(slot -> {
          if (slot.field_75224_c instanceof TileEntitySpawnerController) {
            int slotId = slot.getSlotIndex();
            double startX = 871.0D;
            double startY = 314.0D;
            double offsetX = 85.05D;
            double offsetY = 85.05D;
            int slotsPerRow = 2;
            double x = 871.0D + (slotId % 2) * 85.05D;
            double y = 314.0D + (slotId / 2) * 85.05D;
            SlotNode slotNode = (SlotNode)SlotNode.create(x, y, 85.0D, slot.getSlotIndex(), slot.field_75224_c).attach(this);
            slotNode.itemSize(48.0D);
            this.controllerSlots.add(slotNode);
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
    ContainerSpawnerController container = (ContainerSpawnerController)getContainer();
    if (this.controllerSlots == null || this.controllerSlots.size() < 4)
      return; 
    for (int i = 2; i < 4; i++) {
      SlotNode slotNode = this.controllerSlots.get(i);
      if (slotNode != null)
        if (container.isSlotLocked(i)) {
          slotNode.placeholder(LOCK);
          if (slotNode.isEnabled())
            slotNode.enabled(node -> false); 
        } else {
          slotNode.placeholder((Resource)null);
          if (!slotNode.isEnabled())
            slotNode.enabled(node -> true); 
        }  
    } 
  }
  
  public boolean close() {
    ControllerState state = extractStateFromNode(this.switchNode);
    if (state != null && state != this.openedState)
      changeState(state); 
    return super.close();
  }
  
  private ControllerState extractStateFromNode(SwitchNode node) {
    String state = node.getState();
    try {
      return ControllerState.valueOf(state);
    } catch (IllegalArgumentException e) {
      return null;
    } 
  }
  
  private void changeState(ControllerState state) {
    CSChangeSpawnerController cSChangeSpawnerController = new CSChangeSpawnerController(this.tile.field_145851_c, this.tile.field_145848_d, this.tile.field_145849_e, state);
    cSChangeSpawnerController.send();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\clien\\ui\UISpawnerController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */