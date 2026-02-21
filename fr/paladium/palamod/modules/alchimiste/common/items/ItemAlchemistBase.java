package fr.paladium.palamod.modules.alchimiste.common.items;

import fr.paladium.palamod.modules.alchimiste.common.utils.impl.ItemAlchemist;

public class ItemAlchemistBase extends ItemAlchemist {
  private String name;
  
  public ItemAlchemistBase(String name) {
    this.name = name;
    func_77655_b(name);
    func_111206_d("palamod:alchimiste/" + name);
    func_77625_d(64);
  }
  
  public String getName() {
    return this.name;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\items\ItemAlchemistBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */