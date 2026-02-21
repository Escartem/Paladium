package fr.paladium.palamod.modules.smeltery.items.tools;

import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.paladium.common.materials.PToolMaterial;
import net.minecraft.creativetab.CreativeTabs;

public class ItemTitaneHammer extends ItemHammer {
  public ItemTitaneHammer() {
    super(PToolMaterial.titane);
    func_77655_b("hammer.titane");
    func_111206_d("palamod:hammer/titane");
    func_77637_a((CreativeTabs)Registry.SMELTERY_TAB);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smeltery\items\tools\ItemTitaneHammer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */