package fr.paladium.palamod.modules.alchimiste.common.utils.impl;

import fr.paladium.palamod.modules.alchimiste.PAlchimiste;
import net.minecraft.item.Item;

public abstract class ItemAlchemist extends Item {
  public ItemAlchemist() {
    func_77637_a(PAlchimiste.TAB);
  }
  
  public abstract String getName();
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\commo\\utils\impl\ItemAlchemist.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */