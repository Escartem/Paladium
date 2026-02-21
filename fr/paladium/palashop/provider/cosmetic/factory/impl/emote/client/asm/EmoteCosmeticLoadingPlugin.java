package fr.paladium.palashop.provider.cosmetic.factory.impl.emote.client.asm;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.SortingIndex;
import java.io.File;
import java.util.Map;

@SortingIndex(1000)
public class EmoteCosmeticLoadingPlugin implements IFMLLoadingPlugin {
  public static boolean runtimeDeobfEnabled = false;
  
  public static File location;
  
  public String[] getASMTransformerClass() {
    return new String[] { EmoteCosmeticClassTransformer.class.getName() };
  }
  
  public String getModContainerClass() {
    return null;
  }
  
  public String getSetupClass() {
    return null;
  }
  
  public void injectData(Map<String, Object> data) {
    runtimeDeobfEnabled = ((Boolean)data.get("runtimeDeobfuscationEnabled")).booleanValue();
    location = (File)data.get("coremodLocation");
  }
  
  public String getAccessTransformerClass() {
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\factory\impl\emote\client\asm\EmoteCosmeticLoadingPlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */