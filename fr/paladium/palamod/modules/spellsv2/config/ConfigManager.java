package fr.paladium.palamod.modules.spellsv2.config;

import java.io.File;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;

public class ConfigManager {
  public static Configuration config;
  
  private static String file = "config/palaspells.cfg";
  
  public static void init() {
    config = new Configuration(new File(file));
    try {
      config.load();
    } catch (Exception e) {
      System.out.println("Cannot load configuration file!");
    } finally {
      config.save();
    } 
  }
  
  public static void removeConfig(String category) {
    config = new Configuration(new File(file));
    try {
      config.load();
      if (config.hasCategory(category))
        config.removeCategory(new ConfigCategory(category)); 
    } catch (Exception e) {
      System.out.println("Cannot load configuration file!");
    } finally {
      config.save();
    } 
  }
  
  public static void removeConfig(String category, String key) {
    config = new Configuration(new File(file));
    try {
      config.load();
      if (config.getCategory(category).containsKey(key))
        config.getCategory(category).remove(key); 
    } catch (Exception e) {
      System.out.println("Cannot load configuration file!");
    } finally {
      config.save();
    } 
  }
  
  public static int getInt(String category, String key) {
    config = new Configuration(new File(file));
    try {
      config.load();
      if (config.getCategory(category).containsKey(key))
        return config.get(category, key, 0).getInt(); 
      writeConfig(category, key, 0);
    } catch (Exception e) {
      System.out.println("Cannot load configuration file!");
    } finally {
      config.save();
    } 
    return 0;
  }
  
  public static double getDouble(String category, String key) {
    config = new Configuration(new File(file));
    try {
      config.load();
      if (config.getCategory(category).containsKey(key))
        return config.get(category, key, 0.0D).getDouble(); 
    } catch (Exception e) {
      System.out.println("Cannot load configuration file!");
    } finally {
      config.save();
    } 
    return 0.0D;
  }
  
  public static float getFloat(String category, String key) {
    config = new Configuration(new File(file));
    try {
      config.load();
      if (config.getCategory(category).containsKey(key))
        return (float)config.get(category, key, 0.0D).getDouble(); 
    } catch (Exception e) {
      System.out.println("Cannot load configuration file!");
    } finally {
      config.save();
    } 
    return 0.0F;
  }
  
  public static String getString(String category, String key) {
    config = new Configuration(new File(file));
    try {
      config.load();
      if (config.getCategory(category).containsKey(key))
        return config.get(category, key, "").getString(); 
    } catch (Exception e) {
      System.out.println("Cannot load configuration file!");
    } finally {
      config.save();
    } 
    return "";
  }
  
  public static short getShort(String category, String key) {
    config = new Configuration(new File(file));
    try {
      config.load();
      if (config.getCategory(category).containsKey(key))
        return (short)config.get(category, key, 0).getInt(); 
    } catch (Exception e) {
      System.out.println("Cannot load configuration file!");
    } finally {
      config.save();
    } 
    return 0;
  }
  
  public static byte getByte(String category, String key) {
    config = new Configuration(new File(file));
    try {
      config.load();
      if (config.getCategory(category).containsKey(key))
        return (byte)config.get(category, key, 0).getInt(); 
    } catch (Exception e) {
      System.out.println("Cannot load configuration file!");
    } finally {
      config.save();
    } 
    return 0;
  }
  
  public static boolean getBoolean(String category, String key) {
    config = new Configuration(new File(file));
    try {
      config.load();
      if (config.getCategory(category).containsKey(key))
        return config.get(category, key, false).getBoolean(); 
    } catch (Exception e) {
      System.out.println("Cannot load configuration file!");
    } finally {
      config.save();
    } 
    return false;
  }
  
  public static void writeConfig(String category, String key, String value) {
    config = new Configuration(new File(file));
    try {
      config.load();
      String set = config.get(category, key, value).getString();
      config.getCategory(category).get(key).set(value);
    } catch (Exception e) {
      System.out.println("Cannot load configuration file!");
    } finally {
      config.save();
    } 
  }
  
  public static void writeConfig(String category, String key, int value) {
    config = new Configuration(new File(file));
    try {
      config.load();
      int set = config.get(category, key, value).getInt();
      config.getCategory(category).get(key).set(value);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Cannot load configuration file!");
    } finally {
      config.save();
    } 
  }
  
  public static void writeConfig(String category, String key, boolean value) {
    config = new Configuration(new File(file));
    try {
      config.load();
      boolean set = config.get(category, key, value).getBoolean();
      config.getCategory(category).get(key).set(value);
    } catch (Exception e) {
      System.out.println("Cannot load configuration file!");
    } finally {
      config.save();
    } 
  }
  
  public static void writeConfig(String category, String key, double value) {
    config = new Configuration(new File(file));
    try {
      config.load();
      double set = config.get(category, key, value).getDouble();
      config.getCategory(category).get(key).set(value);
    } catch (Exception e) {
      System.out.println("Cannot load configuration file!");
    } finally {
      config.save();
    } 
  }
  
  public static void writeConfig(String category, String key, short value) {
    config = new Configuration(new File(file));
    try {
      config.load();
      int set = config.get(category, key, value).getInt();
      config.getCategory(category).get(key).set(Integer.valueOf(value).intValue());
    } catch (Exception e) {
      System.out.println("Cannot load configuration file!");
    } finally {
      config.save();
    } 
  }
  
  public static void writeConfig(String category, String key, byte value) {
    config = new Configuration(new File(file));
    try {
      config.load();
      int set = config.get(category, key, value).getInt();
      config.getCategory(category).get(key).set(Integer.valueOf(value).intValue());
    } catch (Exception e) {
      System.out.println("Cannot load configuration file!");
    } finally {
      config.save();
    } 
  }
  
  public static void writeConfig(String category, String key, float value) {
    config = new Configuration(new File(file));
    try {
      config.load();
      double set = config.get(category, key, value).getDouble();
      config.getCategory(category).get(key).set(Double.valueOf(value).doubleValue());
    } catch (Exception e) {
      System.out.println("Cannot load configuration file!");
    } finally {
      config.save();
    } 
  }
  
  public static boolean hasCategory(String category) {
    config = new Configuration(new File(file));
    try {
      config.load();
      return config.hasCategory(category);
    } catch (Exception e) {
      System.out.println("Cannot load configuration file!");
    } finally {
      config.save();
    } 
    return false;
  }
  
  public static boolean hasKey(String category, String key) {
    config = new Configuration(new File(file));
    try {
      config.load();
      if (!config.hasCategory(category))
        return false; 
      return config.getCategory(category).containsKey(key);
    } catch (Exception e) {
      System.out.println("Cannot load configuration file!");
    } finally {
      config.save();
    } 
    return false;
  }
  
  public static void setFile(String filename) {
    file = "config/" + filename;
  }
  
  public static String getFile() {
    return file;
  }
  
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
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\config\ConfigManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */