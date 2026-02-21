package fr.paladium.common.tooltip;

import cpw.mods.fml.relauncher.Side;
import fr.paladium.client.keybind.KeybindBridge;
import fr.paladium.common.tooltip.event.ClientItemTooltipEventHandler;
import fr.paladium.helios.module.utils.abstracts.AModule;
import fr.paladium.helios.module.utils.annotations.Module;
import fr.paladium.helios.module.utils.interfaces.IWidget;
import fr.paladium.helios.module.utils.settings.BooleanSetting;
import fr.paladium.helios.module.utils.settings.ISetting;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ResourceLocation;

@Module(name = "itemtooltip", version = 1)
public class ModuleItemTooltip extends AModule {
  private static ModuleItemTooltip instance;
  
  private final ResourceLocation icon;
  
  private KeyBinding keyWiki;
  
  public KeyBinding getKeyWiki() {
    return this.keyWiki;
  }
  
  public ModuleItemTooltip() {
    instance = this;
    this.icon = new ResourceLocation("helios", "itemtooltip.png");
    addSetting("visual", "showDescription", (ISetting)new BooleanSetting(true));
    addSetting("visual", "showWiki", (ISetting)new BooleanSetting(true));
    addSetting("visual", "showCraft", (ISetting)new BooleanSetting(true));
    addSetting("visual", "showUsage", (ISetting)new BooleanSetting(true));
  }
  
  public void init(Side side) {
    setEnable(true);
    setIcon(this.icon);
    if (side == Side.CLIENT) {
      addListener(ClientItemTooltipEventHandler.class);
      this.keyWiki = registerKeyBind(this.keyWiki, "helios.wiki", 38);
      KeybindBridge.addHeliosSubcategory(this.keyWiki);
    } 
    super.init(side);
  }
  
  public static ModuleItemTooltip getInstance() {
    return instance;
  }
  
  public boolean isShowDescription() {
    return ((BooleanSetting)getSetting("showDescription")).getValue();
  }
  
  public boolean isShowWiki() {
    return ((BooleanSetting)getSetting("showWiki")).getValue();
  }
  
  public boolean isShowCraft() {
    return ((BooleanSetting)getSetting("showCraft")).getValue();
  }
  
  public boolean isShowUsage() {
    return ((BooleanSetting)getSetting("showUsage")).getValue();
  }
  
  public IWidget getWidget() {
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\common\tooltip\ModuleItemTooltip.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */