package fr.paladium.palamod.modules.design.modules.palalag;

import cpw.mods.fml.relauncher.Side;
import fr.paladium.helios.module.BaseModule;
import fr.paladium.helios.module.utils.annotations.Module;
import fr.paladium.helios.module.utils.interfaces.IWidget;
import fr.paladium.helios.module.utils.settings.BooleanSetting;
import fr.paladium.helios.module.utils.settings.ISetting;
import fr.paladium.palamod.modules.design.modules.palalag.packet.SCPacketPalaLag;
import net.minecraft.util.ResourceLocation;

@Module(name = "palalag", version = 1)
public class ModulePalaLag extends BaseModule<WidgetPalaLag> {
  private static ModulePalaLag instance;
  
  private final ResourceLocation icon;
  
  private long end;
  
  private long cooldown;
  
  public long getEnd() {
    return this.end;
  }
  
  public void setEnd(long end) {
    this.end = end;
  }
  
  public long getCooldown() {
    return this.cooldown;
  }
  
  public void setCooldown(long cooldown) {
    this.cooldown = cooldown;
  }
  
  public ModulePalaLag() {
    super((IWidget)new WidgetPalaLag());
    instance = this;
    this.icon = new ResourceLocation("palamod", "textures/helios/modules/palalag.png");
    addSetting("text", "textShadow", (ISetting)new BooleanSetting(true));
  }
  
  public void init(Side side) {
    setEnable(true);
    setIcon(this.icon);
    registerPacket(SCPacketPalaLag.Handler.class, SCPacketPalaLag.class, Side.CLIENT);
    super.init(side);
  }
  
  public static ModulePalaLag getInstance() {
    return instance;
  }
  
  public boolean isTextShadow() {
    return ((BooleanSetting)getSetting("textShadow")).getValue();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\design\modules\palalag\ModulePalaLag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */