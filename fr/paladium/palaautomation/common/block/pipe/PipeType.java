package fr.paladium.palaautomation.common.block.pipe;

public enum PipeType {
  PALADIUM("paladium", 1000L),
  GREEN_PALADIUM("green_paladium", 750L),
  MIXED_ENDIUM("mixed_endium", 500L);
  
  public static final PipeType[] values;
  
  public final String name;
  
  public final long cooldownMs;
  
  static {
    values = values();
  }
  
  public String getName() {
    return this.name;
  }
  
  public long getCooldownMs() {
    return this.cooldownMs;
  }
  
  PipeType(String name, long cooldownMs) {
    this.name = name;
    this.cooldownMs = cooldownMs;
  }
  
  public static PipeType getMinSpeedFrom(PipeType speed1, PipeType speed2) {
    return values()[Math.min(speed1.ordinal(), speed2.ordinal())];
  }
  
  public boolean canTransfer(PipeType type, long lastTransactionMs, long currentMs) {
    return (currentMs - lastTransactionMs >= type.cooldownMs);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaautomation\common\block\pipe\PipeType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */