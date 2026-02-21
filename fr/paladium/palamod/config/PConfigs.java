package fr.paladium.palamod.config;

import fr.paladium.palamod.Constants;
import java.io.File;
import net.minecraftforge.common.config.Configuration;

public class PConfigs {
  public static boolean server_minage_enabled;
  
  public static boolean server_minage_dimension;
  
  public static boolean threadLockAutoReboot;
  
  public static int threadLockRebootTime;
  
  public static String threadLockWebhook;
  
  public static String API_TOKEN;
  
  public static String Redis;
  
  public static int[] potionBanned = new int[0];
  
  public static boolean granite_enabled;
  
  public static boolean diorite_enabled;
  
  public static boolean andesite_enabled;
  
  public static boolean limestone_enabled;
  
  public static boolean marble_enabled;
  
  public static boolean amethyst_ore_enabled;
  
  public static int amethyst_ore_rarety;
  
  public static int amethyst_ore_layer_min;
  
  public static int amethyst_ore_layer_max;
  
  public static boolean titane_ore_enabled;
  
  public static int titane_ore_rarety;
  
  public static int titane_ore_layer_min;
  
  public static int titane_ore_layer_max;
  
  public static boolean paladium_ore_enabled;
  
  public static int paladium_ore_rarety;
  
  public static int paladium_ore_layer_min;
  
  public static int paladium_ore_layer_max;
  
  public static boolean doublepaladium_ore_enabled;
  
  public static boolean paladiumgreen_ore_enabled;
  
  public static int paladiumgreen_ore_rarety;
  
  public static int paladiumgreen_ore_layer_min;
  
  public static int paladiumgreen_ore_layer_max;
  
  public static boolean findium_ore_enabled;
  
  public static int findium_ore_rarety;
  
  public static int findium_ore_layer_min;
  
  public static int findium_ore_layer_max;
  
  public static boolean trixium_ore_enabled;
  
  public static int trixium_ore_rarety;
  
  public static int trixium_ore_layer_min;
  
  public static int trixium_ore_layer_max;
  
  public static boolean custom_tree_judeecercis_enabled;
  
  public static boolean custom_tree_jacaranda_enabled;
  
  public static boolean custom_tree_erable_enabled;
  
  public static boolean custom_tree_ostrya_enabled;
  
  public static String tps_ip;
  
  public static String tps_key;
  
  public static void initConfigs(File location) {
    File configFile = new File(location + "/" + "palamod" + ".cfg");
    Configuration config = new Configuration(configFile);
    server_minage_enabled = config.get("Server", "Server Minage", false).getBoolean();
    server_minage_dimension = config.get("Server", "Server Minage Dimension", false).getBoolean();
    threadLockAutoReboot = config.get("Server.threadlock", "AutoReboot", false).getBoolean();
    threadLockRebootTime = config.get("Server.threadlock", "RebootTime", 30000).getInt();
    threadLockWebhook = config.get("Server.threadlock", "Webhook", "").getString();
    API_TOKEN = config.get("Server", "Api Token", "noKey").getString();
    potionBanned = config.get("Server.balance", "Potion Launcher Banned potionId", new int[] { 7, 19 }).getIntList();
    granite_enabled = config.get("World", "Enabled Granite", true).getBoolean(true);
    diorite_enabled = config.get("World", "Enabled Diorite", true).getBoolean(true);
    andesite_enabled = config.get("World", "Enabled Andesite", true).getBoolean(true);
    limestone_enabled = config.get("World", "Enabled Limestone", true).getBoolean(true);
    marble_enabled = config.get("World", "Enabled Marble", true).getBoolean(true);
    amethyst_ore_enabled = config.get("World.Amethyst", "Enabled Amethyst Ore", true).getBoolean(true);
    amethyst_ore_rarety = config.get("World.Amethyst", "Amethyst Ore Rarity (max: 1000)", 2).getInt(1000);
    amethyst_ore_layer_min = config.get("World.Amethyst", "Amethyst Ore Layer Min (min: 0)", 0).getInt(150);
    amethyst_ore_layer_max = config.get("World.Amethyst", "Amethyst Ore Layer Max (max: 150)", 54).getInt(150);
    titane_ore_enabled = config.get("World.Titane", "Enabled Titane Ore", true).getBoolean(true);
    titane_ore_rarety = config.get("World.Titane", "Titane Ore Rarity (max: 1000)", 2).getInt(1000);
    titane_ore_layer_min = config.get("World.Titane", "Titane Ore Layer Min (min: 0)", 0).getInt(150);
    titane_ore_layer_max = config.get("World.Titane", "Titane Ore Layer Max (max: 150)", 24).getInt(150);
    paladium_ore_enabled = config.get("World.Paladium", "Enabled Paladium Ore", true).getBoolean(true);
    paladium_ore_rarety = config.get("World.Paladium", "Paladium Ore Rarity (max: 1000)", 2).getInt(1000);
    paladium_ore_layer_min = config.get("World.Paladium", "Paladium Ore Layer Min (min: 0)", 0).getInt(150);
    paladium_ore_layer_max = config.get("World.Paladium", "Paladium Ore Layer Max (max: 150)", 11).getInt(150);
    doublepaladium_ore_enabled = config.get("World.Paladium", "Enabled x2 Paladium Ore", false).getBoolean(false);
    paladiumgreen_ore_enabled = config.get("World.PaladiumGreen", "Enabled Paladium Green Ore", false).getBoolean(false);
    paladiumgreen_ore_rarety = config.get("World.PaladiumGreen", "Paladium Green Ore Rarity (max: 1000)", 300).getInt(1000);
    paladiumgreen_ore_layer_min = config.get("World.PaladiumGreen", "Paladium Green Ore Layer Min (min: 0)", 0).getInt(150);
    paladiumgreen_ore_layer_max = config.get("World.PaladiumGreen", "Paladium Green Ore Layer Max (max: 150)", 11).getInt(150);
    findium_ore_enabled = config.get("World.Findium", "Enabled Findium Ore", true).getBoolean(true);
    findium_ore_rarety = config.get("World.Findium", "Findium Green Ore Rarity (max: 1000)", 5).getInt(1000);
    findium_ore_layer_min = config.get("World.Findium", "Findium Ore Layer Min (min: 0)", 0).getInt(150);
    findium_ore_layer_max = config.get("World.Findium", "Findium Ore Layer Max (max: 150)", 25).getInt(150);
    trixium_ore_enabled = config.get("World.Trixium", "Enabled Trixium Ore", true).getBoolean(true);
    trixium_ore_rarety = config.get("World.Trixium", "Trixium Green Ore Rarity (max: 1000)", 5).getInt(1000);
    trixium_ore_layer_min = config.get("World.Trixium", "Trixium Ore Layer Min (min: 0)", 0).getInt(150);
    trixium_ore_layer_max = config.get("World.Trixium", "Trixium Ore Layer Max (max: 150)", 25).getInt(150);
    custom_tree_judeecercis_enabled = config.get("World.CustomTree", "Enabled Custom Tree JudeeCercis", true).getBoolean(true);
    custom_tree_jacaranda_enabled = config.get("World.CustomTree", "Enabled Custom Tree Jacaranda", true).getBoolean(true);
    custom_tree_erable_enabled = config.get("World.CustomTree", "Enabled Custom Tree Erable", true).getBoolean(true);
    custom_tree_ostrya_enabled = config.get("World.CustomTree", "Enabled Custom Tree Ostrya", true).getBoolean(true);
    trixium_ore_enabled = config.get("World.Trixium", "Enabled Trixium Ore", true).getBoolean(true);
    trixium_ore_rarety = config.get("World.Trixium", "Trixium Green Ore Rarity (max: 1000)", 5).getInt(1000);
    trixium_ore_layer_min = config.get("World.Trixium", "Trixium Ore Layer Min (min: 0)", 0).getInt(150);
    trixium_ore_layer_max = config.get("World.Trixium", "Trixium Ore Layer Max (max: 150)", 25).getInt(150);
    tps_ip = config.getString("ip", "tps", "127.0.0.1", "TPS's API url");
    tps_key = config.getString("key", "tps", "key", "TPS's API key");
    if (config.hasChanged())
      config.save(); 
    Constants.API_TOKEN = API_TOKEN;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\config\PConfigs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */