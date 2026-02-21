package fr.paladium.palamod.modules.enderchest.inventory;

import java.util.UUID;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.InventoryEnderChest;

public class OfflineInventoryEnderChest extends InventoryEnderChest {
  private final EntityPlayerMP editor;
  
  private final UUID owner;
  
  private final String inventoryName;
  
  public EntityPlayerMP getEditor() {
    return this.editor;
  }
  
  public UUID getOwner() {
    return this.owner;
  }
  
  public OfflineInventoryEnderChest(EntityPlayerMP editor, UUID owner, String ownerName) {
    this.editor = editor;
    this.owner = owner;
    String name = "§8EnderChest (§c" + ownerName + "§8)";
    this
      .inventoryName = name.substring(0, Math.min(25, name.length())) + ((name.length() > 25) ? "...§8)" : "");
  }
  
  public String func_145825_b() {
    return this.inventoryName;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\enderchest\inventory\OfflineInventoryEnderChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */