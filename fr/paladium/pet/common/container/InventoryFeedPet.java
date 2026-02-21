package fr.paladium.pet.common.container;

import fr.paladium.lib.apollon.container.abstracts.PaladiumInventory;

public class InventoryFeedPet extends PaladiumInventory {
  public static final String NAME = "feed_pet";
  
  public static final int SIZE = 1;
  
  public InventoryFeedPet() {
    super("feed_pet", 1);
  }
  
  public boolean shouldDropOnClose() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\container\InventoryFeedPet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */