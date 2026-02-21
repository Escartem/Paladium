package fr.paladium.palarpg.module.dungeon.client.ui.chest.node;

import fr.paladium.palarpg.module.dungeon.common.playerdata.RPGDungeonPlayerData;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.draw.DrawUtils;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.item.ItemNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import fr.paladium.zephyrui.lib.ui.node.property.watch.WatchProperty;
import fr.paladium.zephyrui.lib.utils.signal.Signal;
import java.util.List;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class DungeonChestSlotNode extends Node {
  public static final Resource DELETE_TEXTURE = Resource.of(new ResourceLocation("palarpg", "textures/gui/chest/slot/button/delete.png")).nearest();
  
  public static final Resource RESTORE_TEXTURE = Resource.of(new ResourceLocation("palarpg", "textures/gui/chest/slot/button/restore.png")).nearest();
  
  public static final Resource DELETE_HOVER_TEXTURE = Resource.of(new ResourceLocation("palarpg", "textures/gui/chest/slot/button/delete_hover.png")).nearest();
  
  public static final Resource RESTORE_HOVER_TEXTURE = Resource.of(new ResourceLocation("palarpg", "textures/gui/chest/slot/button/restore_hover.png")).nearest();
  
  private final Signal<DungeonChestSlotNodeState> stateSignal;
  
  private RPGDungeonPlayerData.RPGDungeonItem item;
  
  public Signal<DungeonChestSlotNodeState> getStateSignal() {
    return this.stateSignal;
  }
  
  public RPGDungeonPlayerData.RPGDungeonItem getItem() {
    return this.item;
  }
  
  protected DungeonChestSlotNode(double x, double y, double size) {
    super(x, y, size, size);
    this.stateSignal = Signal.of(DungeonChestSlotNodeState.DEFAULT);
    watch(this.stateSignal, new WatchProperty[] { WatchProperty.CLEAR_CHILDREN, WatchProperty.RELOAD });
  }
  
  @NonNull
  public static DungeonChestSlotNode create(double x, double y, double size) {
    return new DungeonChestSlotNode(x, y, size);
  }
  
  public void init(UI ui) {
    if (this.stateSignal.getOrDefault() == DungeonChestSlotNodeState.DELETE) {
      double rectSize = w() * 1.0925D;
      RectNode.create(dw(2.0D) - rectSize / 2.0D, dh(2.0D) - rectSize / 2.0D, rectSize, rectSize)
        .color(Color.decode("#DD0000"))
        .attach(this);
    } 
    ImageNode.create(0.0D, 0.0D, w(), h())
      .resource(((DungeonChestSlotNodeState)this.stateSignal.getOrDefault()).getTexture())
      .attach(this);
    if (this.item != null) {
      double itemSize = w() * 0.8D;
      ItemNode.create(dw(2.0D) - itemSize / 2.0D, dh(2.0D) - itemSize / 2.0D, itemSize)
        .item(this.item.getItem())
        .attach(this);
      hoverLines(() -> DrawUtils.ITEM.getTooltip(this.item.getItem()));
    } 
  }
  
  @NonNull
  public final DungeonChestSlotNode item(RPGDungeonPlayerData.RPGDungeonItem item) {
    this.item = item;
    return this;
  }
  
  @NonNull
  public final DungeonChestSlotNode state(DungeonChestSlotNodeState state) {
    this.stateSignal.set(state);
    return this;
  }
  
  public enum DungeonChestSlotNodeState {
    DEFAULT((String)Resource.of(new ResourceLocation("palarpg", "textures/gui/chest/slot/default.png")).nearest()),
    NEW((String)Resource.of(new ResourceLocation("palarpg", "textures/gui/chest/slot/new.png")).nearest()),
    DELETE((String)Resource.of(new ResourceLocation("palarpg", "textures/gui/chest/slot/delete.png")).nearest());
    
    DungeonChestSlotNodeState(Resource texture) {
      this.texture = texture;
    }
    
    private final Resource texture;
    
    public Resource getTexture() {
      return this.texture;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\chest\node\DungeonChestSlotNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */