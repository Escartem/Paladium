package fr.paladium.palamod.modules.design.modules.legendarypower;

import cpw.mods.fml.relauncher.Side;
import fr.paladium.helios.module.BaseModule;
import fr.paladium.helios.module.utils.annotations.Module;
import fr.paladium.helios.module.utils.interfaces.IWidget;
import fr.paladium.helios.module.utils.settings.BooleanSetting;
import fr.paladium.helios.module.utils.settings.ISetting;
import net.minecraft.util.ResourceLocation;

@Module(name = "legendary_power", version = 1)
public class ModuleLegendaryPower extends BaseModule<WidgetLegendaryPower> {
  private static ModuleLegendaryPower instance;
  
  private final ResourceLocation icon;
  
  public ModuleLegendaryPower() {
    super((IWidget)new WidgetLegendaryPower());
    instance = this;
    this.icon = new ResourceLocation("common", "textures/helios/modules/legendary_power.png");
    addSetting("text", "textShadow", (ISetting)new BooleanSetting(true));
  }
  
  public void init(Side side) {
    setEnable(true);
    setIcon(this.icon);
    super.init(side);
  }
  
  public static ModuleLegendaryPower getInstance() {
    return instance;
  }
  
  public boolean isTextShadow() {
    return ((BooleanSetting)getSetting("textShadow")).getValue();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\design\modules\legendarypower\ModuleLegendaryPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */