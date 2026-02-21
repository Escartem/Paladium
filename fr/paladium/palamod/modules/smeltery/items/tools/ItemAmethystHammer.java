package fr.paladium.palamod.modules.smeltery.items.tools;

import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.paladium.common.materials.PToolMaterial;
import net.minecraft.creativetab.CreativeTabs;

public class ItemAmethystHammer extends ItemHammer {
  public ItemAmethystHammer() {
    super(PToolMaterial.amethyst);
    func_77655_b("hammer.amethyst");
    func_111206_d("palamod:hammer/amethyst");
    func_77637_a((CreativeTabs)Registry.SMELTERY_TAB);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smeltery\items\tools\ItemAmethystHammer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */