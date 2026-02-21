package fr.paladium.coremods;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.SortingIndex;
import java.util.Map;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

@SortingIndex(1000)
@MCVersion("1.7.10")
public class LoadingPlugin implements IFMLLoadingPlugin {
  public String[] getASMTransformerClass() {
    return new String[] { ClassTransformer.class.getName() };
  }
  
  public String getModContainerClass() {
    MixinBootstrap.init();
    Mixins.addConfiguration("mixins.common.json");
    return null;
  }
  
  public String getSetupClass() {
    return null;
  }
  
  public void injectData(Map<String, Object> data) {}
  
  public String getAccessTransformerClass() {
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\coremods\LoadingPlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */