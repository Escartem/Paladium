package fr.paladium.palamod.modules.design.modules.currentspell;

import cpw.mods.fml.relauncher.Side;
import fr.paladium.helios.module.BaseModule;
import fr.paladium.helios.module.utils.annotations.Module;
import fr.paladium.helios.module.utils.interfaces.IWidget;
import fr.paladium.helios.module.utils.settings.BooleanSetting;
import fr.paladium.helios.module.utils.settings.ISetting;
import net.minecraft.util.ResourceLocation;

@Module(name = "currentspell", version = 1)
public class ModuleCurrentSpell extends BaseModule<WidgetCurrentSpell> {
  private static ModuleCurrentSpell instance;
  
  public int xp;
  
  public int limit;
  
  private final ResourceLocation icon;
  
  public ModuleCurrentSpell() {
    super((IWidget)new WidgetCurrentSpell());
    instance = this;
    this.icon = new ResourceLocation("palamod", "textures/helios/modules/currentspell.png");
    addSetting("text", "textShadow", (ISetting)new BooleanSetting(true));
  }
  
  public void init(Side side) {
    setEnable(false);
    setIcon(this.icon);
    super.init(side);
  }
  
  public static ModuleCurrentSpell getInstance() {
    return instance;
  }
  
  public boolean isTextShadow() {
    return ((BooleanSetting)getSetting("textShadow")).getValue();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\design\modules\currentspell\ModuleCurrentSpell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */