package fr.paladium.palavanillagui.client.ui.util;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.EnchantmentNameParts;

public class EnchantmentOption {
  private final int level;
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof EnchantmentOption))
      return false; 
    EnchantmentOption other = (EnchantmentOption)o;
    if (!other.canEqual(this))
      return false; 
    if (getLevel() != other.getLevel())
      return false; 
    Object<String> this$lines = (Object<String>)getLines(), other$lines = (Object<String>)other.getLines();
    return !((this$lines == null) ? (other$lines != null) : !this$lines.equals(other$lines));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof EnchantmentOption;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    result = result * 59 + getLevel();
    Object<String> $lines = (Object<String>)getLines();
    return result * 59 + (($lines == null) ? 43 : $lines.hashCode());
  }
  
  public String toString() {
    return "EnchantmentOption(level=" + getLevel() + ", lines=" + getLines() + ")";
  }
  
  public int getLevel() {
    return this.level;
  }
  
  private final List<String> lines = new ArrayList<>();
  
  public List<String> getLines() {
    return this.lines;
  }
  
  public EnchantmentOption(int level) {
    this.level = level;
    this.lines.add(EnchantmentNameParts.field_148338_a.func_148334_a());
    this.lines.add(EnchantmentNameParts.field_148338_a.func_148334_a());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\clien\\u\\util\EnchantmentOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */