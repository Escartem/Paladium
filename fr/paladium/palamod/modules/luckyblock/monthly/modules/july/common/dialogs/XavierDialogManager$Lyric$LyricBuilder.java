package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.dialogs;

public class LyricBuilder {
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\dialogs\XavierDialogManager$Lyric$LyricBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */