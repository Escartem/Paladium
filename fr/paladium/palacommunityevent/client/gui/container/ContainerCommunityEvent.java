package fr.paladium.palacommunityevent.client.gui.container;

import fr.paladium.lib.apollon.container.interfaces.ApollonContainer;
import fr.paladium.palacommunityevent.client.gui.container.slot.CommunityEventSlot;
import fr.paladium.palacommunityevent.common.pojo.CommunityEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerCommunityEvent extends Container implements ApollonContainer {
  private EntityPlayer player;
  
  public CommunityEvent communityEventData;
  
  public EntityPlayer getPlayer() {
    return this.player;
  }
  
  public void setPlayer(EntityPlayer player) {
    this.player = player;
  }
  
  public CommunityEvent getCommunityEventData() {
    return this.communityEventData;
  }
  
  public void setCommunityEventData(CommunityEvent communityEventData) {
    this.communityEventData = communityEventData;
  }
  
  public ContainerCommunityEvent(EntityPlayer player, CommunityEvent communityEvent) {
    setPlayer(player);
    createContainerSlots(new CommunityEventInventory(), player);
    createPlayerSlots(player.field_71071_by);
    setCommunityEventData(communityEvent);
  }
  
  public ContainerCommunityEvent(EntityPlayer player) {
    setPlayer(player);
    createContainerSlots(new CommunityEventInventory(), player);
    createPlayerSlots(player.field_71071_by);
  }
  
  private void createContainerSlots(CommunityEventInventory inventory, EntityPlayer player) {
    func_75146_a((Slot)new CommunityEventSlot(inventory, 0, 0, 0, player, this));
  }
  
  private void createPlayerSlots(InventoryPlayer player) {
    int i;
    for (i = 0; i < 3; i++) {
      for (int j = 0; j < 9; j++)
        func_75146_a(new Slot((IInventory)player, j + i * 9 + 9, 0, 0)); 
    } 
    for (i = 0; i < 9; i++)
      func_75146_a(new Slot((IInventory)player, i, 0, 0)); 
  }
  
  public boolean func_75145_c(EntityPlayer player) {
    return true;
  }
  
  public ItemStack func_82846_b(EntityPlayer player, int id) {
    ItemStack stack = null;
    Slot slot = this.field_75151_b.get(id);
    if (slot == null || !slot.func_75216_d())
      return null; 
    stack = slot.func_75211_c();
    if (stack == null)
      return null; 
    boolean isValid = false;
    for (ItemStack is : this.communityEventData.targetedItems) {
      if (is.func_77969_a(stack))
        isValid = true; 
    } 
    if (!isValid)
      return null; 
    func_75141_a(id, null);
    func_75141_a(0, stack);
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\client\gui\container\ContainerCommunityEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */