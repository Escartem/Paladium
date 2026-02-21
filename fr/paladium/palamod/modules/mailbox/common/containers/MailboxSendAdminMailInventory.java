package fr.paladium.palamod.modules.mailbox.common.containers;

import fr.paladium.lib.apollon.container.abstracts.PaladiumInventory;
import net.minecraft.item.ItemStack;

public class MailboxSendAdminMailInventory extends PaladiumInventory {
  public MailboxSendAdminMailInventory() {
    super("container.market_sell", 21);
  }
  
  public boolean containsItem() {
    for (ItemStack itemStack : getContent()) {
      if (itemStack != null)
        return true; 
    } 
    return false;
  }
  
  public void func_70296_d() {}
  
  public boolean shouldDropOnClose() {
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\common\containers\MailboxSendAdminMailInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */