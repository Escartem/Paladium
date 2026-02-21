package fr.paladium.palarpg.module.equipment.common;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.lang.CustomLanguageManager;
import fr.paladium.palaforgeutils.lib.proxy.AModProxy;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.equipment.EquipmentModule;
import fr.paladium.palarpg.module.equipment.common.item.RPGItemCreativeTab;
import fr.paladium.palarpg.module.equipment.common.item.RPGItemType;
import fr.paladium.palarpg.module.equipment.common.listener.EquipmentCraftEventListener;
import fr.paladium.palarpg.module.equipment.common.listener.EquipmentPlayerEventListener;
import fr.paladium.palarpg.module.equipment.common.listener.RPGEquipmentAnvilEventListener;
import fr.paladium.palarpg.module.equipment.common.loader.RPGItemLoader;
import fr.paladium.palarpg.module.equipment.common.playerdata.RPGCraftPlayerData;
import java.io.File;
import java.io.IOException;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class EquipmentCommonProxy extends AModProxy {
  private static File CONFIG_DIRECTORY;
  
  private static RPGItemCreativeTab RPG_BASIC_TAB;
  
  private static RPGItemCreativeTab RPG_ARMOR_TAB;
  
  private static RPGItemCreativeTab RPG_WEAPON_TAB;
  
  private static RPGItemCreativeTab RPG_PARCHMENT_TAB;
  
  private static RPGItemCreativeTab RPG_CONSUMABLE_TAB;
  
  public void onPreInit(FMLPreInitializationEvent event) {
    addListener(new Class[] { EquipmentPlayerEventListener.class, EquipmentCraftEventListener.class, RPGEquipmentAnvilEventListener.class });
    RPGPlayer.registerPlayerData(new Class[] { RPGCraftPlayerData.class });
    CONFIG_DIRECTORY = new File(event.getModConfigurationDirectory(), "rpgitems");
    RPG_BASIC_TAB = new RPGItemCreativeTab("rpg_basic_tab", RPGItemType.BASIC, Items.field_151045_i);
    RPG_ARMOR_TAB = new RPGItemCreativeTab("rpg_armor_tab", RPGItemType.ARMOR, (Item)Items.field_151027_R);
    RPG_WEAPON_TAB = new RPGItemCreativeTab("rpg_weapon_tab", RPGItemType.SWORD, Items.field_151041_m);
    RPG_PARCHMENT_TAB = new RPGItemCreativeTab("rpg_parchment_tab", RPGItemType.PARCHMENT, Items.field_151099_bA);
    RPG_CONSUMABLE_TAB = new RPGItemCreativeTab("rpg_consumable_tab", RPGItemType.CONSUMABLE, Items.field_151083_be);
    if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
      CustomLanguageManager.register("itemGroup." + RPG_BASIC_TAB.func_78013_b(), "RPG Basic items");
      CustomLanguageManager.register("itemGroup." + RPG_ARMOR_TAB.func_78013_b(), "RPG Armor items");
      CustomLanguageManager.register("itemGroup." + RPG_WEAPON_TAB.func_78013_b(), "RPG Weapon items");
      CustomLanguageManager.register("itemGroup." + RPG_PARCHMENT_TAB.func_78013_b(), "RPG Parchment items");
      CustomLanguageManager.register("itemGroup." + RPG_CONSUMABLE_TAB.func_78013_b(), "RPG Consumable items");
    } 
    if (!CONFIG_DIRECTORY.exists()) {
      if (!CONFIG_DIRECTORY.mkdirs())
        throw new RuntimeException("Failed to create the configuration directory for RPGItems. (" + CONFIG_DIRECTORY.getAbsolutePath() + ")"); 
      EquipmentModule.logger.info("", new Object[0]);
      EquipmentModule.logger.info("Unable to find the configuration directory for Propsy.", new Object[0]);
      EquipmentModule.logger.info("Creating directory at " + CONFIG_DIRECTORY.getAbsolutePath(), new Object[0]);
    } 
    try {
      RPGItemLoader.load(CONFIG_DIRECTORY);
      RPG_BASIC_TAB.init();
      RPG_ARMOR_TAB.init();
      RPG_WEAPON_TAB.init();
      RPG_PARCHMENT_TAB.init();
      RPG_CONSUMABLE_TAB.init();
    } catch (IOException e) {
      EquipmentModule.logger.error("Failed to load the configuration files for RPGItems.", new Object[0]);
      throw new RuntimeException(e);
    } 
  }
  
  public static File getConfigDirectory() {
    return CONFIG_DIRECTORY;
  }
  
  public static CreativeTabs getRPGBasicTab() {
    return (CreativeTabs)RPG_BASIC_TAB;
  }
  
  public static CreativeTabs getRPGArmorTab() {
    return (CreativeTabs)RPG_ARMOR_TAB;
  }
  
  public static CreativeTabs getRPGWeaponTab() {
    return (CreativeTabs)RPG_WEAPON_TAB;
  }
  
  public static CreativeTabs getRPGParchmentTab() {
    return (CreativeTabs)RPG_PARCHMENT_TAB;
  }
  
  public static CreativeTabs getRPGConsumableTab() {
    return (CreativeTabs)RPG_CONSUMABLE_TAB;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\EquipmentCommonProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */