package fr.paladium.pet.client.tab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class TabPet extends CreativeTabs {
  public static final String LABEL = "tab_pet";
  
  public static final TabPet INSTANCE = new TabPet();
  
  public TabPet() {
    super("tab_pet");
  }
  
  public Item func_78016_d() {
    return Item.func_150898_a(Blocks.field_150484_ah);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\client\tab\TabPet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */