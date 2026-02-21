package fr.paladium.palarpg.module.dungeon.client.ui.hub.select.node;

import fr.paladium.palarpg.module.dungeon.server.config.level.DungeonLevelLootsConfig;
import fr.paladium.palarpg.module.equipment.client.constant.RPGItemRarityConstant;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.resource.Resource;
import fr.paladium.zephyrui.lib.ui.core.UI;
import fr.paladium.zephyrui.lib.ui.node.Node;
import fr.paladium.zephyrui.lib.ui.node.impl.design.image.ImageNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.item.ItemNode;
import fr.paladium.zephyrui.lib.ui.node.impl.design.shape.RectNode;
import lombok.NonNull;
import net.minecraft.util.ResourceLocation;

public class DungeonSelectItemSlotNode extends Node {
  private DungeonLevelLootsConfig.DungeonLevelLootsElementConfig item;
  
  protected DungeonSelectItemSlotNode(double x, double y, double width, double height) {
    super(x, y, width, height);
  }
  
  @NonNull
  public static DungeonSelectItemSlotNode create(double x, double y, double width, double height) {
    return new DungeonSelectItemSlotNode(x, y, width, height);
  }
  
  public void init(@NonNull UI ui) {
    if (ui == null)
      throw new NullPointerException("ui is marked non-null but is null"); 
    if (this.item == null)
      throw new IllegalStateException("ItemStack cannot be null"); 
    RectNode.create(0.0D, 0.0D, getWidth(), getHeight())
      .color(new Color(32, 32, 32))
      .body(itemNode -> {
          ImageNode.create(-itemNode.getWidth() * 0.25D, -itemNode.getHeight() * 0.25D, itemNode.getWidth() * 1.5D, itemNode.getHeight() * 1.5D).resource(Resource.of(new ResourceLocation("palarpg", "textures/gui/select/slot.png")).linear()).color(RPGItemRarityConstant.getColor(this.item.getRarity())).attach(itemNode);
          ItemNode.create(itemNode.getWidth() * 0.10000000149011612D, itemNode.getWidth() * 0.10000000149011612D, itemNode.getWidth() * 0.800000011920929D).item(this.item.create()).stackSize(false).itemHover(true).attach(itemNode);
        }).attach(this);
  }
  
  @NonNull
  public DungeonSelectItemSlotNode item(@NonNull DungeonLevelLootsConfig.DungeonLevelLootsElementConfig item) {
    if (item == null)
      throw new NullPointerException("item is marked non-null but is null"); 
    this.item = item;
    return this;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\hub\select\node\DungeonSelectItemSlotNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */