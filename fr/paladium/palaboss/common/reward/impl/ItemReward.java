package fr.paladium.palaboss.common.reward.impl;

import fr.paladium.palaboss.common.entity.EntityBossMob;
import fr.paladium.palaboss.common.reward.IReward;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palaforgeutils.lib.player.PlayerUtils;
import java.util.UUID;
import lombok.NonNull;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemReward implements IReward<EntityBossMob> {
  private ItemStack itemStack;
  
  private int quantity;
  
  public void setItemStack(ItemStack itemStack) {
    this.itemStack = itemStack;
  }
  
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
  
  public ItemReward(ItemStack itemStack, int quantity) {
    this.itemStack = itemStack;
    this.quantity = quantity;
  }
  
  public ItemStack getItemStack() {
    return this.itemStack;
  }
  
  public int getQuantity() {
    return this.quantity;
  }
  
  public boolean canBeGiven(@NonNull UUID playerUUID, @NonNull EntityBossMob entity, float damage) {
    if (playerUUID == null)
      throw new NullPointerException("playerUUID is marked non-null but is null"); 
    if (entity == null)
      throw new NullPointerException("entity is marked non-null but is null"); 
    return (PlayerUtils.getPlayer(playerUUID) != null);
  }
  
  public void give(UUID playerUUID, EntityBossMob entity) {
    this.itemStack.field_77994_a = 0;
    InventoryUtils.addItem((EntityPlayer)PlayerUtils.getPlayer(playerUUID), this.itemStack, this.quantity);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaboss\common\reward\impl\ItemReward.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */