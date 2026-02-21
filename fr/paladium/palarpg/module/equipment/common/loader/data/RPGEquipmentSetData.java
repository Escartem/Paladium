package fr.paladium.palarpg.module.equipment.common.loader.data;

import fr.paladium.palarpg.module.equipment.common.loader.util.ItemStatMutator;
import java.util.List;
import java.util.Map;

public class RPGEquipmentSetData {
  private transient String id;
  
  private String name;
  
  private Map<Integer, List<ItemStatMutator>> mutators;
  
  public void setId(String id) {
    this.id = id;
  }
  
  public String getId() {
    return this.id;
  }
  
  public String getName() {
    return this.name;
  }
  
  public Map<Integer, List<ItemStatMutator>> getMutators() {
    return this.mutators;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\equipment\common\loader\data\RPGEquipmentSetData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */