package fr.paladium.palarpg.common.api;

import fr.paladium.palaforgeutils.lib.registry.RegistryUtils;
import fr.paladium.palarpg.module.dungeon.common.item.ItemKeyring;
import fr.paladium.palarpg.module.equipment.common.item.RPGItemRarity;
import fr.paladium.palarpg.module.equipment.common.item.impl.RPGItemRune;
import fr.paladium.palarpg.module.equipment.common.item.impl.RPGItemRuneFragment;
import fr.paladium.palarpg.module.equipment.common.loader.data.impl.RPGBasicRuneItemData;
import fr.paladium.palarpg.module.equipment.common.loader.data.impl.RPGRuneItemData;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import net.minecraft.item.Item;

public class ItemsRegister {
  public static List<Item> DUNGEON_ITEMS = new ArrayList<>();
  
  public static Item DUNGEON_KEYRING;
  
  public static Item DUNGEON_RUNE_TIER_0;
  
  public static Item DUNGEON_RUNE_TIER_1;
  
  public static Item DUNGEON_RUNE_TIER_2;
  
  public static Item DUNGEON_RUNE_TIER_3;
  
  public static Item DUNGEON_RUNE_FRAGMENT_TIER_0;
  
  public static Item DUNGEON_RUNE_FRAGMENT_TIER_1;
  
  public static void register() {
    DUNGEON_ITEMS.add(register(DUNGEON_KEYRING = (new ItemKeyring()).func_77625_d(16).func_77655_b("dungeon_keyring").func_111206_d("palarpg:dungeon/keyring")));
    register(DUNGEON_RUNE_TIER_0 = (Item)new RPGItemRune(RPGRuneItemData.create("rpg_rune_tier", 0, RPGItemRarity.COMMON)));
    register(DUNGEON_RUNE_TIER_1 = (Item)new RPGItemRune(RPGRuneItemData.create("rpg_rune_tier", 1, RPGItemRarity.RARE)));
    register(DUNGEON_RUNE_TIER_2 = (Item)new RPGItemRune(RPGRuneItemData.create("rpg_rune_tier", 2, RPGItemRarity.EPIC)));
    register(DUNGEON_RUNE_TIER_3 = (Item)new RPGItemRune(RPGRuneItemData.create("rpg_rune_tier", 3, RPGItemRarity.LEGENDARY)));
    register(DUNGEON_RUNE_FRAGMENT_TIER_0 = (Item)new RPGItemRuneFragment(RPGBasicRuneItemData.create("rpg_rune_fragment_tier", 0, RPGItemRarity.COMMON, 16)));
    register(DUNGEON_RUNE_FRAGMENT_TIER_1 = (Item)new RPGItemRuneFragment(RPGBasicRuneItemData.create("rpg_rune_fragment_tier", 1, RPGItemRarity.RARE, 16)));
  }
  
  @NonNull
  private static Item register(@NonNull Item item) {
    if (item == null)
      throw new NullPointerException("item is marked non-null but is null"); 
    RegistryUtils.item(new Item[] { item });
    return item;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\common\api\ItemsRegister.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */