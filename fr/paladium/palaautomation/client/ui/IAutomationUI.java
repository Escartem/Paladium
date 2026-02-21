package fr.paladium.palaautomation.client.ui;

import fr.paladium.palaautomation.common.tile.IPipeMachine;
import fr.paladium.palaautomation.common.tile.util.PipeItemData;
import fr.paladium.zephyrui.lib.utils.signal.impl.primitive.IntegerSignal;
import org.lwjgl.input.Keyboard;

public interface IAutomationUI {
  IPipeMachine getPipeMachine();
  
  IntegerSignal getQuantitySignal();
  
  default PipeItemData getItemData() {
    IPipeMachine pipeMachine = getPipeMachine();
    if (pipeMachine == null)
      return null; 
    return pipeMachine.getItemData();
  }
  
  default void setQuantity(int quantity) {
    IntegerSignal quantitySignal = getQuantitySignal();
    int dataCount = (getItemData() == null) ? 0 : getItemData().getCount();
    int max = Math.min(dataCount, 576);
    if (quantity > max) {
      quantitySignal.set(Integer.valueOf(max));
      return;
    } 
    quantitySignal.set(Integer.valueOf(quantity));
  }
  
  default void incrementQuantity() {
    incrementQuantity(0);
  }
  
  default void incrementQuantity(int max) {
    PipeItemData itemData = getItemData();
    if (max <= 0 && itemData == null)
      return; 
    IntegerSignal quantitySignal = getQuantitySignal();
    int increment = (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(29)) ? 10 : 1;
    if (max <= 0)
      max = Math.min(itemData.getCount(), 576); 
    if (((Integer)quantitySignal.getOrDefault()).intValue() + increment > max) {
      quantitySignal.set(Integer.valueOf(max));
      return;
    } 
    quantitySignal.add(increment);
  }
  
  default void decrementQuantity() {
    IntegerSignal quantitySignal = getQuantitySignal();
    int decrement = (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(29)) ? 10 : 1;
    if (((Integer)quantitySignal.getOrDefault()).intValue() - decrement < 1) {
      quantitySignal.set(Integer.valueOf(1));
      return;
    } 
    quantitySignal.add(-decrement);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\clien\\ui\IAutomationUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */