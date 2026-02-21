package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.utils;

public class ChanceObject<T> {
  private final double chance;
  
  private final T object;
  
  public ChanceObject(double chance, T object) {
    this.chance = chance;
    this.object = object;
  }
  
  public double getChance() {
    return this.chance;
  }
  
  public T getObject() {
    return this.object;
  }
  
  public static <T> ChanceObject<T> of(double chance, T object) {
    return new ChanceObject<>(chance, object);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\commo\\utils\ChanceObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */