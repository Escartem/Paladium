package fr.paladium.palavoicechat.common.listener;

public enum VoiceState {
  DISABLED, ONLYSTAFF, ENABLED;
  
  private static final VoiceState[] values;
  
  static {
    values = values();
  }
  
  public static VoiceState fromOrdinal(int ordinal) {
    if (ordinal < 0 || ordinal >= values.length)
      return DISABLED; 
    return values[ordinal];
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavoicechat\common\listener\VoiceState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */