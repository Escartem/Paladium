package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.dialogs;

public class Lyric {
  private String key;
  
  private String value;
  
  public void setKey(String key) {
    this.key = key;
  }
  
  public void setValue(String value) {
    this.value = value;
  }
  
  public static LyricBuilder builder() {
    return new LyricBuilder();
  }
  
  public static class LyricBuilder {
    private String key;
    
    private String value;
    
    public LyricBuilder key(String key) {
      this.key = key;
      return this;
    }
    
    public LyricBuilder value(String value) {
      this.value = value;
      return this;
    }
    
    public XavierDialogManager.Lyric build() {
      return new XavierDialogManager.Lyric(this.key, this.value);
    }
    
    public String toString() {
      return "XavierDialogManager.Lyric.LyricBuilder(key=" + this.key + ", value=" + this.value + ")";
    }
  }
  
  public String getKey() {
    return this.key;
  }
  
  public String getValue() {
    return this.value;
  }
  
  public Lyric(String key, String value) {
    this.key = key;
    this.value = value;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\dialogs\XavierDialogManager$Lyric.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */