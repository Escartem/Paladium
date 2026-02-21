package fr.paladium.palakeybind.mixins;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.SortingIndex;
import java.io.File;
import java.util.Map;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

@SortingIndex(1000)
public class LoadingPlugin implements IFMLLoadingPlugin {
  public static boolean runtimeDeobfEnabled = false;
  
  public static File location;
  
  public String[] getASMTransformerClass() {
    return new String[0];
  }
  
  public String getModContainerClass() {
    MixinBootstrap.init();
    Mixins.addConfiguration("mixins.palakeybind.json");
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


/* Location:              E:\Paladium\!\fr\paladium\palakeybind\mixins\LoadingPlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */