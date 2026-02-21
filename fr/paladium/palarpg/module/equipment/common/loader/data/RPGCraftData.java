package fr.paladium.palarpg.module.equipment.common.loader.data;

import fr.paladium.palarpg.module.equipment.common.loader.util.ItemUtils;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import net.minecraft.item.ItemStack;

public class RPGCraftData {
  private String row1;
  
  private String row2;
  
  private String row3;
  
  private String result;
  
  private Map<Character, Object> dict;
  
  private int requiredLevel;
  
  private boolean requiredParchment;
  
  private transient ItemStack resultItem;
  
  public String toString() {
    return "RPGCraftData(row1=" + getRow1() + ", row2=" + getRow2() + ", row3=" + getRow3() + ", result=" + getResult() + ", dict=" + getDict() + ", requiredLevel=" + getRequiredLevel() + ", requiredParchment=" + isRequiredParchment() + ", resultItem=" + getResultItem() + ")";
  }
  
  private RPGCraftData() {
    this.requiredLevel = 0;
    this.requiredParchment = false;
  }
  
  public String getRow1() {
    return this.row1;
  }
  
  public String getRow2() {
    return this.row2;
  }
  
  public String getRow3() {
    return this.row3;
  }
  
  public String getResult() {
    return this.result;
  }
  
  public Map<Character, Object> getDict() {
    return this.dict;
  }
  
  public int getRequiredLevel() {
    return this.requiredLevel;
  }
  
  public boolean isRequiredParchment() {
    return this.requiredParchment;
  }
  
  public static RPGCraftData build(ItemStack resultItem, int requiredLevel, boolean requiredParchment) {
    RPGCraftData craftData = new RPGCraftData();
    craftData.resultItem = resultItem;
    craftData.requiredLevel = requiredLevel;
    craftData.requiredParchment = requiredParchment;
    return craftData;
  }
  
  public static RPGCraftData build(String row1, String row2, String row3, Map<Character, Object> dict, ItemStack resultItem, int requiredLevel, boolean requiredParchment) {
    RPGCraftData craftData = new RPGCraftData();
    craftData.row1 = row1;
    craftData.row2 = row2;
    craftData.row3 = row3;
    craftData.dict = dict;
    craftData.resultItem = resultItem;
    craftData.requiredLevel = requiredLevel;
    craftData.requiredParchment = requiredParchment;
    return craftData;
  }
  
  public ItemStack getResultItem() {
    if (this.result == null && this.resultItem == null)
      throw new RuntimeException("[RPGItems] Result item \"" + this.result + " " + this.resultItem + "\" not valid"); 
    if (this.resultItem != null)
      return this.resultItem; 
    ItemStack resultItem = ItemUtils.parse(this.result);
    if (resultItem == null)
      throw new RuntimeException("[RPGItems] Result item parsed not valid"); 
    return resultItem;
  }
  
  public boolean hasCraftMatrix() {
    return ((this.row1 != null && this.row1.trim().length() > 0) || (this.row2 != null && this.row2.trim().length() > 0) || (this.row3 != null && this.row3.trim().length() > 0));
  }
  
  public Object[] getCraftMatrix() {
    List<Object> matrix = new LinkedList();
    if (this.row1 != null && this.row1.trim().length() > 0 && this.row1.trim().length() <= 3)
      matrix.add(this.row1); 
    if (this.row2 != null && this.row2.trim().length() > 0 && this.row2.trim().length() <= 3)
      matrix.add(this.row2); 
    if (this.row3 != null && this.row3.trim().length() > 0 && this.row3.trim().length() <= 3)
      matrix.add(this.row3); 
    this.dict.forEach((identifier, itemObject) -> {
          ItemStack stack = (itemObject instanceof String) ? ItemUtils.parse((String)itemObject) : ((itemObject instanceof ItemStack) ? (ItemStack)itemObject : null);
          if (stack == null)
            throw new RuntimeException("[RPGItems] Item for identifier " + identifier + " not valid"); 
          if (identifier == null)
            throw new RuntimeException("[RPGItems] Identifier " + identifier + " not valid"); 
          matrix.add(identifier);
          matrix.add(stack);
        });
    return matrix.toArray();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\loader\data\RPGCraftData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */