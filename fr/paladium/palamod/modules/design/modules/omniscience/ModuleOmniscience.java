package fr.paladium.palamod.modules.design.modules.omniscience;

import cpw.mods.fml.relauncher.Side;
import fr.paladium.helios.module.BaseModule;
import fr.paladium.helios.module.utils.annotations.Module;
import fr.paladium.helios.module.utils.interfaces.IWidget;
import fr.paladium.helios.module.utils.settings.BooleanSetting;
import fr.paladium.helios.module.utils.settings.ISetting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

@Module(name = "omniscience", version = 1)
public class ModuleOmniscience extends BaseModule<WidgetOmniscience> {
  private static ModuleOmniscience instance;
  
  public String player = null;
  
  public ItemStack[] inv;
  
  private final ResourceLocation icon;
  
  public ModuleOmniscience() {
    super((IWidget)new WidgetOmniscience());
    instance = this;
    this.icon = new ResourceLocation("palamod", "textures/helios/modules/omniscience.png");
    addSetting("text", "textShadow", (ISetting)new BooleanSetting(true));
  }
  
  public void init(Side side) {
    setEnable(true);
    setIcon(this.icon);
    super.init(side);
  }
  
  public static ModuleOmniscience getInstance() {
    return instance;
  }
  
  public boolean isTextShadow() {
    return ((BooleanSetting)getSetting("textShadow")).getValue();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\design\modules\omniscience\ModuleOmniscience.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */