package fr.paladium.pet.common.container.slot;

import fr.paladium.pet.common.network.data.PetPlayer;
import fr.paladium.pet.common.registry.impl.PetItemRegistry;
import fr.paladium.pet.server.assignement.AssignmentManager;
import fr.paladium.pet.server.config.assignment.fields.AssignmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotFeedPet extends Slot {
  private final EntityPlayer player;
  
  public SlotFeedPet(IInventory inv, int slot, EntityPlayer player) {
    super(inv, slot, 0, 0);
    this.player = player;
  }
  
  public boolean func_75214_a(ItemStack itemStack) {
    if (itemStack == null)
      return false; 
    Item item = itemStack.func_77973_b();
    return (item.equals(PetItemRegistry.BAIT) || item instanceof net.minecraft.item.ItemFood);
  }
  
  public void func_75218_e() {
    ItemStack itemStack = func_75211_c();
    if (itemStack == null || this.player == null)
      return; 
    if (!func_75214_a(itemStack)) {
      super.func_75218_e();
      return;
    } 
    if (this.player.field_70170_p.field_72995_K) {
      func_75215_d(null);
      super.func_75218_e();
      return;
    } 
    AssignmentManager.getInstance().performAssignments((EntityPlayerMP)this.player, 
        PetPlayer.get(this.player), itemStack, new AssignmentType[] { AssignmentType.ITEM });
    func_75215_d(null);
    super.func_75218_e();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\container\slot\SlotFeedPet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */