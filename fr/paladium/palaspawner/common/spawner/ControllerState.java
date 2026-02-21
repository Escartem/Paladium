package fr.paladium.palaspawner.common.spawner;

public enum ControllerState {
  AUTOMATIC, MANUAL;
  
  public ControllerState next() {
    return values()[(ordinal() + 1) % (values()).length];
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\common\spawner\ControllerState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */