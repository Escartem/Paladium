package fr.paladium.palamod.modules.shop.data;

public enum ItemCategory {
  ALL, BLOCK, ORE, VEGETABLE, OTHER;
  
  public static ItemCategory DEFAULT;
  
  private static ItemCategory[] VALUES;
  
  static {
    DEFAULT = OTHER;
    VALUES = values();
  }
  
  public static ItemCategory fromCategory(String rawCategory) {
    for (ItemCategory category : VALUES) {
      if (category.name().equalsIgnoreCase(rawCategory))
        return category; 
    } 
    return DEFAULT;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\shop\data\ItemCategory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */