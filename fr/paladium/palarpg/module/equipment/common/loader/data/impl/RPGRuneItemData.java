package fr.paladium.palarpg.module.equipment.common.loader.data.impl;

import fr.paladium.palarpg.module.equipment.common.item.RPGItemRarity;

public class RPGRuneItemData extends RPGBasicRuneItemData {
  public static RPGRuneItemData create(String id, int tier, RPGItemRarity rarity) {
    RPGRuneItemData data = new RPGRuneItemData();
    data.setTier(tier);
    data.setId(id + "_" + tier);
    data.setMaxStackSize(1);
    data.setRarity(rarity);
    return data;
  }
  
  public String getDescription() {
    return "Rune de §btier " + (getTier() + 1) + "§r pouvant être améliorée dans une enclume avec une autre rune du même tier partageant au moins une statistique.\n\nPeut être appliquée via le grinder sur un équipement disposant d'un emplacement de rune libre.";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\loader\data\impl\RPGRuneItemData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */