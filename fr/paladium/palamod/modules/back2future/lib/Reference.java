package fr.paladium.palamod.modules.back2future.lib;

import org.reflections.scanners.FieldAnnotationsScanner;

public class Reference {
  public static final String ITEM_BLOCK_TEXTURE_PATH = "palamod:";
  
  public static final String ARMOUR_TEXTURE_PATH = "palamod:textures/models/armor/";
  
  public static final String ENTITY_TEXTURE_PATH = "palamod:textures/entities/";
  
  public static FieldAnnotationsScanner scanner = new FieldAnnotationsScanner();
  
  public static final String SERVER_PROXY_CLASS = "fr.paladium.palamod.modules.back2future.core.proxy.CommonProxy";
  
  public static final String CLIENT_PROXY_CLASS = "fr.paladium.palamod.modules.back2future.core.proxy.ClientProxy";
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\lib\Reference.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */