package fr.paladium.palajobs.client.helios;

import cpw.mods.fml.relauncher.Side;
import fr.paladium.helios.module.BaseModule;
import fr.paladium.helios.module.utils.annotations.Module;
import fr.paladium.helios.module.utils.interfaces.IWidget;
import fr.paladium.helios.module.utils.settings.ISetting;
import fr.paladium.helios.module.utils.settings.ListSetting;

@Module(name = "jobs", version = 1)
public class ModuleJobs extends BaseModule<WidgetJobs> {
  private static ModuleJobs instance;
  
  public ModuleJobs() {
    super((IWidget)new WidgetJobs());
    instance = this;
    addSetting("visual", "displayType", (ISetting)new ListSetting("value", new String[] { "value", "percentage" }));
  }
  
  public void init(Side side) {
    setEnable(false);
    super.init(side);
  }
  
  public static ModuleJobs getInstance() {
    return instance;
  }
  
  public String getDisplayType() {
    return ((ListSetting)getSetting("displayType")).getValue();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\client\helios\ModuleJobs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */