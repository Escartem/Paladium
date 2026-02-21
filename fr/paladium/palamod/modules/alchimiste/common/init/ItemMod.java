package fr.paladium.palamod.modules.alchimiste.common.init;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.alchimiste.PAlchimiste;
import fr.paladium.palamod.modules.alchimiste.common.items.ItemAlchemistBase;
import fr.paladium.palamod.modules.alchimiste.common.items.ItemAlchemistKey;
import fr.paladium.palamod.modules.alchimiste.common.items.ItemFlask;
import fr.paladium.palamod.modules.alchimiste.common.items.ItemGlueball;
import fr.paladium.palamod.modules.alchimiste.common.items.ItemLightningPotion;
import fr.paladium.palamod.modules.alchimiste.common.utils.impl.EnumGlueball;
import fr.paladium.palamod.modules.alchimiste.common.utils.impl.ItemAlchemist;
import net.minecraft.item.Item;

public class ItemMod {
  static {
    ItemsRegister.FLASK = (ItemAlchemist)new ItemFlask();
    ItemsRegister.GREEN_GLUEBALL = (ItemAlchemist)new ItemGlueball(EnumGlueball.GREEN);
    ItemsRegister.BLUE_GLUEBALL = (ItemAlchemist)new ItemGlueball(EnumGlueball.BLUE);
    ItemsRegister.YELLOW_GLUEBALL = (ItemAlchemist)new ItemGlueball(EnumGlueball.YELLOW);
    ItemsRegister.RED_GLUEBALL = (ItemAlchemist)new ItemGlueball(EnumGlueball.RED);
    ItemsRegister.ORANGE_GLUEBALL = (ItemAlchemist)new ItemGlueball(EnumGlueball.ORANGE);
    ItemsRegister.GRAY_GLUEBALL = (ItemAlchemist)new ItemGlueball(EnumGlueball.GRAY);
    ItemsRegister.PURPLE_GLUEBALL = (ItemAlchemist)new ItemGlueball(EnumGlueball.PURPLE);
    ItemsRegister.GREEN_FLASH_GLUEBALL = (ItemAlchemist)new ItemGlueball(EnumGlueball.GREEN_FLASH);
    ItemsRegister.GREEN_DARK_GLUEBALL = (ItemAlchemist)new ItemGlueball(EnumGlueball.GREEN_DARK);
    ItemsRegister.CYAN_GLUEBALL = (ItemAlchemist)new ItemGlueball(EnumGlueball.CYAN);
    ItemsRegister.LIGHTNING_POTION = (ItemAlchemist)new ItemLightningPotion();
    ItemsRegister.GLUEBALL_PATTERN = (ItemAlchemist)new ItemAlchemistBase("glueball_pattern");
    ItemsRegister.ENDIUM_HEART = (ItemAlchemist)new ItemAlchemistBase("endium_heart");
    ItemsRegister.AMETHYSTE_PORTAL_KEY = (ItemAlchemist)new ItemAlchemistKey("amethyste_portal_key");
    ItemsRegister.TITANE_PORTAL_KEY = (ItemAlchemist)new ItemAlchemistKey("titane_portal_key");
    ItemsRegister.PALADIUM_PORTAL_KEY = (ItemAlchemist)new ItemAlchemistKey("paladium_portal_key");
    ItemsRegister.ENDIUM_PORTAL_KEY = (ItemAlchemist)new ItemAlchemistKey("endium_portal_key");
    ItemsRegister.TITANE_POLLEN = (new Item()).func_77637_a(PAlchimiste.TAB).func_77655_b("titane_pollen").func_111206_d("palamod:titane_pollen");
    ItemsRegister.AMETHYST_POLLEN = (new Item()).func_77637_a(PAlchimiste.TAB).func_77655_b("amethyst_pollen").func_111206_d("palamod:amethyst_pollen");
    ItemsRegister.PALADIUM_POLLEN = (new Item()).func_77637_a(PAlchimiste.TAB).func_77655_b("paladium_pollen").func_111206_d("palamod:paladium_pollen");
    ItemsRegister.ENDIUM_POLLEN = (new Item()).func_77637_a(PAlchimiste.TAB).func_77655_b("endium_pollen").func_111206_d("palamod:endium_pollen");
  }
  
  private static final ItemAlchemist[] REGISTRY = new ItemAlchemist[] { 
      ItemsRegister.FLASK, ItemsRegister.LIGHTNING_POTION, ItemsRegister.GREEN_GLUEBALL, ItemsRegister.BLUE_GLUEBALL, ItemsRegister.YELLOW_GLUEBALL, ItemsRegister.RED_GLUEBALL, ItemsRegister.ORANGE_GLUEBALL, ItemsRegister.GRAY_GLUEBALL, ItemsRegister.PURPLE_GLUEBALL, ItemsRegister.GREEN_FLASH_GLUEBALL, 
      ItemsRegister.GREEN_DARK_GLUEBALL, ItemsRegister.CYAN_GLUEBALL, ItemsRegister.GLUEBALL_PATTERN, ItemsRegister.ENDIUM_HEART, ItemsRegister.AMETHYSTE_PORTAL_KEY, ItemsRegister.TITANE_PORTAL_KEY, ItemsRegister.PALADIUM_PORTAL_KEY, ItemsRegister.ENDIUM_PORTAL_KEY };
  
  public static void register() {
    for (ItemAlchemist i : REGISTRY)
      GameRegistry.registerItem((Item)i, i.getName()); 
    GameRegistry.registerItem(ItemsRegister.TITANE_POLLEN, ItemsRegister.TITANE_POLLEN
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.AMETHYST_POLLEN, ItemsRegister.AMETHYST_POLLEN
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.PALADIUM_POLLEN, ItemsRegister.PALADIUM_POLLEN
        .func_77658_a());
    GameRegistry.registerItem(ItemsRegister.ENDIUM_POLLEN, ItemsRegister.ENDIUM_POLLEN
        .func_77658_a());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\common\init\ItemMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */