package fr.paladium.palamod.modules.spellsv2.init;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.spellsv2.items.ItemSpellCroll;
import net.minecraft.item.Item;

public class Items {
  public static void register() {
    ItemsRegister.SPELL_SCROLL = (Item)new ItemSpellCroll();
    GameRegistry.registerItem(ItemsRegister.SPELL_SCROLL, ItemsRegister.SPELL_SCROLL
        .func_77658_a());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\init\Items.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */