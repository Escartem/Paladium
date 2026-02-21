package fr.paladium.palamod.modules.palaboss.common.attacks;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public class AttacksRegistry {
  public static AttacksRegistry INSTANCE = new AttacksRegistry();
  
  private Map<String, Class<? extends Attack>> registeredAttacks = new HashMap<>();
  
  public void registerAttack(String name, Class<? extends Attack> attackClazz) {
    this.registeredAttacks.put(name, attackClazz);
  }
  
  @Nullable
  public Class<? extends Attack> getAttackByName(String name) {
    for (Map.Entry<String, Class<? extends Attack>> attack : this.registeredAttacks.entrySet()) {
      if (((String)attack.getKey()).equalsIgnoreCase(name))
        return attack.getValue(); 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\attacks\AttacksRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */