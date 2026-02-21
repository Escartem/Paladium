package fr.paladium.palashop.common.provider.event;

public enum Phase {
  PRE, POST;
  
  public boolean isCancelable() {
    return (this == PRE);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\common\provider\event\ProviderEvent$Phase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */