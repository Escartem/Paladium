package fr.paladium.palarpg.module.equipment.common.item;

import fr.paladium.palarpg.module.equipment.common.item.impl.RPGItem;
import fr.paladium.palarpg.module.equipment.common.item.impl.RPGItemArmor;
import fr.paladium.palarpg.module.equipment.common.item.impl.RPGItemConsumable;
import fr.paladium.palarpg.module.equipment.common.item.impl.RPGItemParchment;
import fr.paladium.palarpg.module.equipment.common.item.impl.RPGItemSword;
import fr.paladium.palarpg.module.equipment.common.loader.data.RPGItemData;
import fr.paladium.palarpg.module.equipment.common.loader.data.impl.RPGArmorItemData;
import fr.paladium.palarpg.module.equipment.common.loader.data.impl.RPGBasicItemData;
import fr.paladium.palarpg.module.equipment.common.loader.data.impl.RPGConsumableItemData;
import fr.paladium.palarpg.module.equipment.common.loader.data.impl.RPGParchmentItemData;
import fr.paladium.palarpg.module.equipment.common.loader.data.impl.RPGSwordItemData;

public enum RPGItemType {
  BASIC("basic", (Class)RPGBasicItemData.class, (Class)RPGItem.class),
  ARMOR("armor", (Class)RPGArmorItemData.class, (Class)RPGItemArmor.class),
  SWORD("sword", (Class)RPGSwordItemData.class, (Class)RPGItemSword.class),
  PARCHMENT("parchment", (Class)RPGParchmentItemData.class, (Class)RPGItemParchment.class),
  CONSUMABLE("consumable", (Class)RPGConsumableItemData.class, (Class)RPGItemConsumable.class);
  
  RPGItemType(String path, Class<? extends RPGItemData> dataClazz, Class<? extends IRPGItem> itemClazz) {
    this.path = path;
    this.dataClazz = dataClazz;
    this.itemClazz = itemClazz;
  }
  
  public static final RPGItemType[] values;
  
  private final String path;
  
  private final Class<? extends RPGItemData> dataClazz;
  
  private final Class<? extends IRPGItem> itemClazz;
  
  static {
    values = values();
  }
  
  public String getPath() {
    return this.path;
  }
  
  public Class<? extends RPGItemData> getDataClazz() {
    return this.dataClazz;
  }
  
  public Class<? extends IRPGItem> getItemClazz() {
    return this.itemClazz;
  }
  
  public static RPGItemType fromOrdinal(int ordinal) {
    return values[ordinal];
  }
  
  public static RPGItemType fromString(String name) {
    for (RPGItemType type : values) {
      if (type.name().equalsIgnoreCase(name))
        return type; 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\item\RPGItemType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */