package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.utils;

import java.util.List;

public class RandomObjectPicker<T> {
  private final double totalChance;
  
  private final List<ChanceObject<T>> objects;
  
  public RandomObjectPicker(List<ChanceObject<T>> objects) {
    this.objects = objects;
    this.totalChance = objects.stream().mapToDouble(ChanceObject::getChance).sum();
  }
  
  public T pickRandomObject() {
    double random = Math.random() * this.totalChance;
    double current = 0.0D;
    for (ChanceObject<T> object : this.objects) {
      current += object.getChance();
      if (random < current)
        return object.getObject(); 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\commo\\utils\RandomObjectPicker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */