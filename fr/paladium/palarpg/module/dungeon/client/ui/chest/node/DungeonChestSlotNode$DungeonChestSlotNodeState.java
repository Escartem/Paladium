package fr.paladium.palarpg.module.dungeon.client.ui.chest.node;

import fr.paladium.zephyrui.lib.resource.Resource;
import net.minecraft.util.ResourceLocation;

public enum DungeonChestSlotNodeState {
  DEFAULT(Resource.of(new ResourceLocation("palarpg", "textures/gui/chest/slot/default.png")).nearest()),
  NEW(Resource.of(new ResourceLocation("palarpg", "textures/gui/chest/slot/new.png")).nearest()),
  DELETE(Resource.of(new ResourceLocation("palarpg", "textures/gui/chest/slot/delete.png")).nearest());
  
  DungeonChestSlotNodeState(Resource texture) {
    this.texture = texture;
  }
  
  private final Resource texture;
  
  public Resource getTexture() {
    return this.texture;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\clien\\ui\chest\node\DungeonChestSlotNode$DungeonChestSlotNodeState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */