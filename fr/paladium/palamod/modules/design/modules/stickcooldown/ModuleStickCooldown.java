package fr.paladium.palamod.modules.design.modules.stickcooldown;

import cpw.mods.fml.relauncher.Side;
import fr.paladium.helios.module.BaseModule;
import fr.paladium.helios.module.utils.annotations.Module;
import fr.paladium.helios.module.utils.interfaces.IWidget;
import fr.paladium.helios.module.utils.settings.BooleanSetting;
import fr.paladium.helios.module.utils.settings.ISetting;

@Module(name = "stick_cooldown", version = 1)
public class ModuleStickCooldown extends BaseModule<WidgetStickCooldown> {
  private static ModuleStickCooldown instance;
  
  public ModuleStickCooldown() {
    super((IWidget)new WidgetStickCooldown());
    instance = this;
    addSetting("text", "textShadow", (ISetting)new BooleanSetting(true));
    addSetting("visual", "showAllSticks", (ISetting)new BooleanSetting(false));
  }
  
  public void init(Side side) {
    setEnable(true);
    super.init(side);
  }
  
  public static ModuleStickCooldown getInstance() {
    return instance;
  }
  
  public boolean isTextShadow() {
    return ((BooleanSetting)getSetting("textShadow")).getValue();
  }
  
  public boolean isShowAllSticks() {
    return ((BooleanSetting)getSetting("showAllSticks")).getValue();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\design\modules\stickcooldown\ModuleStickCooldown.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */