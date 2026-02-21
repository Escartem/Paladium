package fr.paladium.palacommunityevent.client.gui.container.slot;

import fr.paladium.palacommunityevent.client.gui.container.ContainerCommunityEvent;
import fr.paladium.palacommunityevent.server.managers.PalaCommunityEventManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class CommunityEventSlot extends Slot {
  private EntityPlayer player;
  
  private ContainerCommunityEvent container;
  
  public CommunityEventSlot(IInventory inv, int slot, int x, int y, EntityPlayer player, ContainerCommunityEvent containerCommunityEvent) {
    super(inv, slot, x, y);
    this.player = player;
    this.container = containerCommunityEvent;
  }
  
  public boolean func_75214_a(ItemStack itemStack) {
    for (ItemStack is : this.container.communityEventData.targetedItems) {
      if (itemStack.func_77969_a(is))
        return true; 
    } 
    return false;
  }
  
  public ItemStack func_75209_a(int count) {
    return super.func_75209_a(count);
  }
  
  public int func_75219_a() {
    return 64;
  }
  
  public void func_75218_e() {
    ItemStack itemStack = func_75211_c();
    if (itemStack == null || this.player == null)
      return; 
    boolean isValid = false;
    for (ItemStack is : this.container.communityEventData.targetedItems) {
      if (itemStack.func_77969_a(is))
        isValid = true; 
    } 
    if (!isValid)
      return; 
    int amount = itemStack.field_77994_a;
    func_75215_d(null);
    if (this.player instanceof net.minecraft.entity.player.EntityPlayerMP)
      PalaCommunityEventManager.getInstance().progress(this.player, this.container.communityEventData.id, amount); 
  }
  
  public void func_82870_a(EntityPlayer p, ItemStack itemStack) {
    func_75208_c(itemStack);
    super.func_82870_a(p, itemStack);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palacommunityevent\client\gui\container\slot\CommunityEventSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */