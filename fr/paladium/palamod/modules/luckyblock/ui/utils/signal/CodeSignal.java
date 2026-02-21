package fr.paladium.palamod.modules.luckyblock.ui.utils.signal;

import fr.paladium.zephyrui.lib.utils.signal.impl.iterable.ListSignal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CodeSignal extends ListSignal<Integer> {
  private int maxLength;
  
  public int getMaxLength() {
    return this.maxLength;
  }
  
  public CodeSignal(int maxLength) {
    super(new ArrayList());
    this.maxLength = maxLength;
  }
  
  public boolean isFilled() {
    return (size() >= this.maxLength);
  }
  
  public boolean add(Integer e) {
    boolean success = !isFilled() ? ((List<Integer>)getOrDefault()).add(e) : false;
    publish();
    return success;
  }
  
  public void removeLast() {
    if (size() > 0)
      remove(size() - 1); 
  }
  
  public String getCode() {
    return ((List)getOrDefault()).stream().map(Object::toString).collect(Collectors.joining(""));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckybloc\\u\\utils\signal\CodeSignal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */