package fr.paladium.palamod.modules.communityevents.init;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.communityevents.items.galette.ItemGalette;
import fr.paladium.palamod.modules.communityevents.items.galette.ItemLuckyBean;
import fr.paladium.palamod.modules.communityevents.items.galette.ItemRandomBean;
import fr.paladium.palamod.modules.communityevents.items.pirate.ItemPirateChest;
import net.minecraft.item.Item;

public class Items {
  public static void register() {
    ItemsRegister.PIRATE_CHEST = (Item)new ItemPirateChest();
    GameRegistry.registerItem(ItemsRegister.PIRATE_CHEST, ItemsRegister.PIRATE_CHEST.func_77658_a());
    ItemsRegister.GALETTE = (Item)new ItemGalette();
    GameRegistry.registerItem(ItemsRegister.GALETTE, ItemsRegister.GALETTE.func_77658_a());
    ItemsRegister.LUCKY_BEAN = (Item)new ItemLuckyBean();
    GameRegistry.registerItem(ItemsRegister.LUCKY_BEAN, ItemsRegister.LUCKY_BEAN.func_77658_a());
    ItemsRegister.RANDOM_BEAN = (Item)new ItemRandomBean();
    GameRegistry.registerItem(ItemsRegister.RANDOM_BEAN, ItemsRegister.RANDOM_BEAN.func_77658_a());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\communityevents\init\Items.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */