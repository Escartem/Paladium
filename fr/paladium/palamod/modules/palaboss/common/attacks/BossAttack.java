package fr.paladium.palamod.modules.palaboss.common.attacks;

import com.google.gson.JsonPrimitive;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BossAttack implements Attack {
  private String attackName;
  
  private Map<String, Object> params;
  
  private transient List<AttackParamEntry> attackParams;
  
  public AttackParamEntry minRange;
  
  public AttackParamEntry maxRange;
  
  public BossAttack(String attackName) {
    this.attackName = attackName;
    this.attackParams = new ArrayList<>();
    registerParam(this.minRange = new AttackParamEntry("minRange", Integer.valueOf(0)));
    registerParam(this.maxRange = new AttackParamEntry("maxRange", Integer.valueOf(5)));
  }
  
  public AttackParamEntry registerParam(AttackParamEntry entry) {
    if (!this.attackParams.contains(entry)) {
      this.attackParams.add(entry);
      return entry;
    } 
    return null;
  }
  
  public String getAttackName() {
    return this.attackName;
  }
  
  public List<AttackParamEntry> getAttackParams() {
    return this.attackParams;
  }
  
  public int getMaxRange() {
    return ((JsonPrimitive)this.maxRange.getValue()).getAsInt();
  }
  
  public int getMinRange() {
    return ((JsonPrimitive)this.minRange.getValue()).getAsInt();
  }
  
  public AttackParamEntry getAttackParam(String param) {
    for (AttackParamEntry entry : this.attackParams) {
      if (entry.getParamName().equalsIgnoreCase(param))
        return entry; 
    } 
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\attacks\BossAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */