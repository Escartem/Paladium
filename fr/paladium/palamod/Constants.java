package fr.paladium.palamod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Constants {
  public enum Environment {
    RELEASE, SERVER, DEV;
    
    public static Environment getEnum(String value) {
      for (Environment v : values()) {
        if (v.name().equals(value))
          return v; 
      } 
      return DEV;
    }
  }
  
  public static Environment MOD_ENV = Environment.getEnum("RELEASE");
  
  public static final String MOD_ID = "palamod";
  
  public static final String TEXTURE_PATH = "palamod:";
  
  public static final String MOD_NAME = "PalaMod";
  
  public static final String MOD_VERSION = "8.0.1.6";
  
  public static final String MOD_DEPENDENCIES = "required-after:Forge;required-after:guardiangolem;required-after:ariane;required-after:customnpcs;required-after:helios;required-after:palashop;required-after:apollon;required-after:palaforge-utils;required-after:palapass;required-after:palajobs;required-after:achievement;required-after:lindworm;required-after:mount;required-after:factions;required-after:palapet;required-after:palavanillagui;required-after:palaspawner;required-after:palaautomation";
  
  public static final String MINECRAFT_VERSION = "[1.7.10]";
  
  public static final String SERVER_NAME = "Paladium";
  
  public static final String SERVER_IP = "mc.paladium-pvp.fr";
  
  public static final int SERVER_PORT = 25565;
  
  public static final int SERVER_PROTOCOL = 871;
  
  public static final boolean SERVER_SRV_RECORD = false;
  
  public static final int SERVER_LOOP = 5000;
  
  public static final int SERVER_EMU_PLAYER_MAX = 8000;
  
  public static final String ELUCIAPI_HOST = "10.50.14.1";
  
  public static final int ELUCIAPI_PORT = 3039;
  
  public static final String DISCORD_RPC_ID = "475720078088732672";
  
  public static final String WEB_PALADIUM = "http://www.paladium-pvp.fr/";
  
  public static final String WEB_PALADIUM_NEWS = "https://paladium-pvp.fr/index.php";
  
  public static final String WEB_PALADIUM_STORE = "https://store.paladium-pvp.fr/";
  
  public static final String WEB_PALADIUM_FORUM = "https://paladium-pvp.fr/index.php?forums/";
  
  public static final String WEB_DISCORD_PALADIUM = "https://discord.gg/paladium/";
  
  public static final String WEB_YOUTUBE_PALADIUM = "https://www.youtube.com/channel/UC3uSViy-aWBAk6b36Xjnm0g";
  
  public static final String WEB_TWITTER_PALADIUM = "https://twitter.com/PaladiumPVP/";
  
  public static final Logger logger = LogManager.getLogger("PalaMod");
  
  public static String API_TOKEN = "";
  
  public static String CDN_VERSION = "<N/A>";
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\Constants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */