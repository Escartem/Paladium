package fr.paladium.palamod.modules.luckyblock.config;

public enum OPTIONS {
  public String category;
  
  public String key;
  
  public String title;
  
  public int defaultValue;
  
  public int min;
  
  public int max;
  
  public int step;
  
  public int type;
  
  public String[] traduction;
  
  OPTIONS(String category, String key, String title, int defaultValue, int min, int max, String[] traduction) {
    this.category = category;
    this.key = key;
    this.title = title;
    this.defaultValue = defaultValue;
    this.min = min;
    this.max = max;
    this.traduction = traduction;
    this.type = 0;
  }
  
  public int getValue() {
    int value = ConfigManager.getInt(this.category, this.key);
    if (value > this.max)
      value = this.max; 
    if (value < this.min)
      value = this.min; 
    return value;
  }
  
  public void write(int value) {
    ConfigManager.writeConfig(this.category, this.key, value);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\config\ConfigManager$OPTIONS.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */