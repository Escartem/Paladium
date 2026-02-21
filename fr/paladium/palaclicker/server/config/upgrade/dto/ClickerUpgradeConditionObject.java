package fr.paladium.palaclicker.server.config.upgrade.dto;

public class ClickerUpgradeConditionObject {
  private final ClickerUpgradeCondition type;
  
  private final Object value;
  
  public ClickerUpgradeConditionObject(ClickerUpgradeCondition type, Object value) {
    this.type = type;
    this.value = value;
  }
  
  public ClickerUpgradeCondition getType() {
    return this.type;
  }
  
  public Object getValue() {
    return this.value;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\server\confi\\upgrade\dto\ClickerUpgradeConditionObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */