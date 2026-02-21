package fr.paladium.palashop.client.ui.impl.inventory.manager.dto;

import lombok.NonNull;

public interface IShopInventoryInteraction {
  String getName();
  
  boolean isValid(@NonNull IShopInventoryElement paramIShopInventoryElement);
  
  boolean isEnabled(@NonNull IShopInventoryElement paramIShopInventoryElement);
  
  void execute(@NonNull IShopInventoryElement paramIShopInventoryElement);
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\clien\\ui\impl\inventory\manager\dto\IShopInventoryInteraction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */