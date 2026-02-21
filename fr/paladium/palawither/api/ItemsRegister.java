package fr.paladium.palawither.api;

import fr.paladium.palaforgeutils.lib.registry.RegistryUtils;
import fr.paladium.palawither.common.entity.EntityBabyWither;
import fr.paladium.palawither.common.entity.EntityPassiveWither;
import fr.paladium.palawither.common.entity.EntityThirstyWither;
import fr.paladium.palawither.common.entity.targetable.EntityPredatorWither;
import fr.paladium.palawither.common.entity.targetable.EntitySupremeWither;
import fr.paladium.palawither.common.item.ItemWitherGem;
import fr.paladium.palawither.common.item.ItemWitherHead;
import fr.paladium.palawither.common.item.ItemWitherUpgrade;
import fr.paladium.palawither.common.item.target.ItemWitherTargetLaserSystem;
import fr.paladium.palawither.common.item.target.ItemWitherTargetPoint;
import fr.paladium.palawither.common.item.upgrade.ItemFurtiveWitherUpgrade;
import fr.paladium.palawither.common.item.upgrade.ItemResistanceWitherUpgrade;
import fr.paladium.palawither.common.item.upgrade.ItemSlimyWitherUpgrade;
import fr.paladium.palawither.common.wither.impl.VanillaWither;
import net.minecraft.item.Item;

public class ItemsRegister {
  public static Item VANILLA_WITHER_GEM;
  
  public static Item BABY_WITHER_GEM;
  
  public static Item PASSIVE_WITHER_GEM;
  
  public static Item THIRSTY_WITHER_GEM;
  
  public static Item PREDATOR_WITHER_GEM;
  
  public static Item SUPREME_WITHER_GEM;
  
  public static Item PASSIVE_WITHER_HEAD;
  
  public static Item PREDATOR_WITHER_HEAD;
  
  public static Item THIRSTY_WITHER_HEAD;
  
  public static Item SUPREME_WITHER_HEAD;
  
  public static Item WITHER_TARGET_POINT;
  
  public static Item WITHER_TARGET_LASER_SYSTEM;
  
  public static ItemWitherUpgrade FURTIVE_WITHER_UPGRADE;
  
  public static ItemWitherUpgrade RESISTANCE_WITHER_UPGRADE;
  
  public static ItemWitherUpgrade SLIMY_WITHER_UPGRADE;
  
  public static void register() {
    RegistryUtils.item(new Item[] { VANILLA_WITHER_GEM = (Item)new ItemWitherGem("vanilla_wither_gem", "palawither:gem/vanilla", VanillaWither.class) });
    RegistryUtils.item(new Item[] { BABY_WITHER_GEM = (Item)new ItemWitherGem("baby_wither_gem", "palawither:gem/baby", EntityBabyWither.class) });
    RegistryUtils.item(new Item[] { PASSIVE_WITHER_GEM = (Item)new ItemWitherGem("passive_wither_gem", "palawither:gem/passive", EntityPassiveWither.class) });
    RegistryUtils.item(new Item[] { THIRSTY_WITHER_GEM = (Item)new ItemWitherGem("thirsty_wither_gem", "palawither:gem/thirsty", EntityThirstyWither.class) });
    RegistryUtils.item(new Item[] { PREDATOR_WITHER_GEM = (Item)new ItemWitherGem("predator_wither_gem", "palawither:gem/predator", EntityPredatorWither.class) });
    RegistryUtils.item(new Item[] { SUPREME_WITHER_GEM = (Item)new ItemWitherGem("supreme_wither_gem", "palawither:gem/supreme", EntitySupremeWither.class) });
    RegistryUtils.item(new Item[] { PASSIVE_WITHER_HEAD = (Item)new ItemWitherHead("passive_wither_head") });
    RegistryUtils.item(new Item[] { PREDATOR_WITHER_HEAD = (Item)new ItemWitherHead("predator_wither_head") });
    RegistryUtils.item(new Item[] { THIRSTY_WITHER_HEAD = (Item)new ItemWitherHead("thirsty_wither_head") });
    RegistryUtils.item(new Item[] { SUPREME_WITHER_HEAD = (Item)new ItemWitherHead("supreme_wither_head") });
    RegistryUtils.item(new Item[] { (Item)(FURTIVE_WITHER_UPGRADE = (ItemWitherUpgrade)new ItemFurtiveWitherUpgrade()) });
    RegistryUtils.item(new Item[] { (Item)(RESISTANCE_WITHER_UPGRADE = (ItemWitherUpgrade)new ItemResistanceWitherUpgrade()) });
    RegistryUtils.item(new Item[] { (Item)(SLIMY_WITHER_UPGRADE = (ItemWitherUpgrade)new ItemSlimyWitherUpgrade()) });
    RegistryUtils.item(new Item[] { WITHER_TARGET_POINT = (Item)new ItemWitherTargetPoint() });
    RegistryUtils.item(new Item[] { WITHER_TARGET_LASER_SYSTEM = (Item)new ItemWitherTargetLaserSystem() });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palawither\api\ItemsRegister.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */