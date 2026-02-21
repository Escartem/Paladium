package fr.paladium.palarpg.module.equipment.common.loader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palarpg.module.equipment.EquipmentModule;
import fr.paladium.palarpg.module.equipment.client.renderer.RPGItemRenderer;
import fr.paladium.palarpg.module.equipment.common.item.IRPGItem;
import fr.paladium.palarpg.module.equipment.common.item.RPGArmorType;
import fr.paladium.palarpg.module.equipment.common.item.RPGItemType;
import fr.paladium.palarpg.module.equipment.common.item.impl.RPGItem;
import fr.paladium.palarpg.module.equipment.common.item.impl.RPGItemArmor;
import fr.paladium.palarpg.module.equipment.common.loader.cache.RPGCraftCache;
import fr.paladium.palarpg.module.equipment.common.loader.cache.RPGEquipmentSetCache;
import fr.paladium.palarpg.module.equipment.common.loader.cache.RPGItemsCache;
import fr.paladium.palarpg.module.equipment.common.loader.data.RPGCraftData;
import fr.paladium.palarpg.module.equipment.common.loader.data.RPGEquipmentSetData;
import fr.paladium.palarpg.module.equipment.common.loader.data.RPGItemData;
import fr.paladium.palarpg.module.equipment.common.loader.data.RPGRuneConfigData;
import fr.paladium.palarpg.module.equipment.common.loader.data.impl.RPGArmorItemData;
import fr.paladium.palarpg.module.equipment.common.manager.EquipmentRuneManager;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Map;
import lombok.NonNull;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

public class RPGItemLoader {
  public static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
  
  public static void load(File configFile) throws IOException {
    EquipmentModule.logger.info("Loading RPG Items...", new Object[0]);
    for (RPGItemType type : RPGItemType.values) {
      File typeFile = new File(configFile, type.getPath());
      if (!typeFile.exists()) {
        typeFile.mkdirs();
        EquipmentModule.logger.error("Unable to find " + type + " category.", new Object[0]);
      } else {
        for (File dir : typeFile.listFiles()) {
          if (dir.isDirectory()) {
            File dataFile = new File(dir, "data.json");
            RPGItemData data = null;
            if (dataFile.exists()) {
              String json = new String(Files.readAllBytes(dataFile.toPath()), StandardCharsets.UTF_8);
              data = (RPGItemData)GSON.fromJson(json, type.getDataClazz());
            } else {
              try {
                data = type.getDataClazz().newInstance();
                data.createDefault(dir);
              } catch (InstantiationException|IllegalAccessException e) {
                EquipmentModule.logger.error("Unable to create instance of " + type + " class.", new Object[0]);
                e.printStackTrace();
              } 
            } 
            data.setId(dir.getName());
            data.setType(type);
            if (data.getVersion() == null)
              data.setVersion("1.0.0"); 
            data.onLoad(dir);
            if (data.getType().getItemClazz() != null)
              try {
                if (type == RPGItemType.ARMOR) {
                  RPGArmorItemData armorData = (RPGArmorItemData)data;
                  if (armorData.getArmors() != null)
                    for (Map.Entry<RPGArmorType, RPGArmorItemData.RPGArmor> entry : (Iterable<Map.Entry<RPGArmorType, RPGArmorItemData.RPGArmor>>)armorData.getArmors().entrySet()) {
                      RPGItemArmor item = type.getItemClazz().getDeclaredConstructor(new Class[] { RPGArmorType.class, RPGItemData.class }).newInstance(new Object[] { entry.getKey(), data });
                      item.register();
                      RPGItemsCache.addItem((IRPGItem)item);
                    }  
                } else {
                  RPGItem item = type.getItemClazz().getDeclaredConstructor(new Class[] { RPGItemData.class }).newInstance(new Object[] { data });
                  item.register();
                  if (FMLCommonHandler.instance().getSide() == Side.CLIENT && item.getItemData().isModel())
                    MinecraftForgeClient.registerItemRenderer((Item)item, (IItemRenderer)new RPGItemRenderer(item.getItemData())); 
                  RPGItemsCache.addItem((IRPGItem)item);
                } 
              } catch (InstantiationException|IllegalAccessException|IllegalArgumentException|java.lang.reflect.InvocationTargetException|NoSuchMethodException|SecurityException e) {
                e.printStackTrace();
              }  
          } 
        } 
      } 
    } 
    EquipmentModule.logger.info("RPG Items loaded.", new Object[0]);
    loadCrafts(configFile);
    loadEquipmentSets(configFile);
    loadRuneConfig(configFile);
  }
  
  private static void loadCrafts(File configFile) throws IOException {
    EquipmentModule.logger.info("Loading RPG Crafts...", new Object[0]);
    File typeFile = new File(configFile, "craft");
    if (!typeFile.exists()) {
      typeFile.mkdirs();
      EquipmentModule.logger.error("Unable to find craft category.", new Object[0]);
      return;
    } 
    for (File f : typeFile.listFiles()) {
      if (!f.isDirectory())
        if (f.getName().endsWith(".json")) {
          String json = new String(Files.readAllBytes(f.toPath()), StandardCharsets.UTF_8);
          RPGCraftData craftData = (RPGCraftData)GSON.fromJson(json, RPGCraftData.class);
          loadCraft(craftData);
        }  
    } 
    EquipmentModule.logger.info("RPG Crafts loaded.", new Object[0]);
  }
  
  public static void loadCraft(@NonNull RPGCraftData craftData) {
    if (craftData == null)
      throw new NullPointerException("craftData is marked non-null but is null"); 
    registerCraft(craftData);
    EquipmentModule.logger.info("[+] Craft " + craftData.getResultItem().func_82833_r() + " loaded.", new Object[0]);
  }
  
  private static void loadEquipmentSets(File configFile) throws IOException {
    EquipmentModule.logger.info("Loading RPG Equipments Sets...", new Object[0]);
    File typeFile = new File(configFile, "set");
    if (!typeFile.exists()) {
      typeFile.mkdirs();
      EquipmentModule.logger.error("Unable to find set category.", new Object[0]);
      return;
    } 
    for (File f : typeFile.listFiles()) {
      if (!f.isDirectory())
        if (f.getName().endsWith(".json")) {
          String json = new String(Files.readAllBytes(f.toPath()), StandardCharsets.UTF_8);
          RPGEquipmentSetData setData = (RPGEquipmentSetData)GSON.fromJson(json, RPGEquipmentSetData.class);
          setData.setId(f.getName().replace(".json", "").trim());
          RPGEquipmentSetCache.addSet(setData);
        }  
    } 
    EquipmentModule.logger.info("RPG Equipments Sets loaded.", new Object[0]);
  }
  
  private static void loadRuneConfig(File configFile) throws IOException {
    EquipmentModule.logger.info("Loading RPG Runes Config...", new Object[0]);
    File typeFile = new File(configFile, "rune");
    if (!typeFile.exists()) {
      typeFile.mkdirs();
      EquipmentModule.logger.error("Unable to find rune category.", new Object[0]);
      return;
    } 
    for (File f : typeFile.listFiles()) {
      if (!f.isDirectory())
        if ("config.json".equalsIgnoreCase(f.getName())) {
          String json = new String(Files.readAllBytes(f.toPath()), StandardCharsets.UTF_8);
          RPGRuneConfigData runeConfig = (RPGRuneConfigData)GSON.fromJson(json, RPGRuneConfigData.class);
          EquipmentRuneManager.setConfig(runeConfig);
        }  
    } 
    EquipmentModule.logger.info("RPG Runes Config loaded.", new Object[0]);
  }
  
  private static void registerCraft(@NonNull RPGCraftData craftData) {
    if (craftData == null)
      throw new NullPointerException("craftData is marked non-null but is null"); 
    if (craftData.hasCraftMatrix()) {
      ItemStack result = craftData.getResultItem();
      Object[] matrix = craftData.getCraftMatrix();
      CraftingManager.func_77594_a().func_92103_a(result, matrix);
    } 
    RPGCraftCache.registerCraftData(craftData);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\loader\RPGItemLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */