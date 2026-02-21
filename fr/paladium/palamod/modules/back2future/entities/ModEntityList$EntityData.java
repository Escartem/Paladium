package fr.paladium.palamod.modules.back2future.entities;

public class EntityData {
  public final String name;
  
  public final int id;
  
  public final int eggColour1;
  
  public final int eggColour2;
  
  public final boolean hasEgg;
  
  private EntityData(String name, int id, int eggColour1, int eggColour2, boolean hasEgg) {
    this.name = name;
    this.id = id;
    this.eggColour1 = eggColour1;
    this.eggColour2 = eggColour2;
    this.hasEgg = hasEgg;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\ModEntityList$EntityData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */