package fr.paladium.palamod.modules.design.modules.unclaimfinder;

import cpw.mods.fml.relauncher.Side;
import fr.paladium.helios.module.BaseModule;
import fr.paladium.helios.module.utils.annotations.Module;
import fr.paladium.helios.module.utils.interfaces.IWidget;
import fr.paladium.helios.module.utils.settings.BooleanSetting;
import fr.paladium.helios.module.utils.settings.ISetting;

@Module(name = "unclaim_finder", version = 1)
public class ModuleUnclaimFinder extends BaseModule<WidgetUnclaimFinder> {
  private static ModuleUnclaimFinder instance;
  
  public ModuleUnclaimFinder() {
    super((IWidget)new WidgetUnclaimFinder());
    instance = this;
    addSetting("text", "textShadow", (ISetting)new BooleanSetting(true));
  }
  
  public void init(Side side) {
    setEnable(true);
    super.init(side);
  }
  
  public static ModuleUnclaimFinder getInstance() {
    return instance;
  }
  
  public boolean isTextShadow() {
    return ((BooleanSetting)getSetting("textShadow")).getValue();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\design\module\\unclaimfinder\ModuleUnclaimFinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */