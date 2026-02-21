package fr.paladium.palamod.modules.mailbox.common.containers;

import fr.paladium.lib.apollon.container.abstracts.PaladiumContainer;
import fr.paladium.lib.apollon.container.interfaces.ApollonContainer;
import fr.paladium.palamod.common.guihandler.SimpleGHandler;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class MailboxSendAdminMailContainer extends PaladiumContainer implements ApollonContainer {
  public MailboxSendAdminMailContainer(SimpleGHandler.GuiHandlerData data) {
    this((IInventory)data.getInventory(), (IInventory)new MailboxSendAdminMailInventory());
  }
  
  public MailboxSendAdminMailContainer(IInventory playerInv, IInventory inventory) {
    int xPos = 194;
    int yPos = 145;
    for (int i = 0; i < 21; i++)
      func_75146_a(new Slot(inventory, i, 154, 46)); 
    for (int y = 0; y < 3; y++) {
      for (int j = 0; j < 9; j++)
        func_75146_a(new Slot(playerInv, j + y * 9 + 9, 194 + j * 16, 145 + y * 16)); 
    } 
    for (int x = 0; x < 9; x++)
      func_75146_a(new Slot(playerInv, x, 194 + x * 16, 197)); 
  }
  
  public int getPlayerInventoryPosition() {
    return 14;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\common\containers\MailboxSendAdminMailContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */