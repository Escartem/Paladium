package fr.paladium.palamod.modules.luckyblock.hud.vision;

import cpw.mods.fml.relauncher.Side;
import fr.paladium.helios.module.BaseModule;
import fr.paladium.helios.module.utils.annotations.Module;
import fr.paladium.helios.module.utils.interfaces.IWidget;
import fr.paladium.helios.module.utils.settings.BooleanSetting;
import fr.paladium.helios.module.utils.settings.ISetting;
import fr.paladium.palamod.modules.luckyblock.hud.vision.packets.ClientVisionPacket;
import fr.paladium.palamod.modules.luckyblock.hud.vision.packets.ServerVisionPacket;
import net.minecraft.util.ResourceLocation;

@Module(name = "lb.vision", version = 1)
public class ModuleVision extends BaseModule<WidgetVision> {
  public static ModuleVision instance;
  
  private long lastUpdate = System.currentTimeMillis();
  
  public ModuleVision() {
    super((IWidget)new WidgetVision());
    instance = this;
    addSetting("visual", "showInventory", (ISetting)new BooleanSetting(true));
  }
  
  public void init(Side side) {
    setEnable(false);
    setIcon(new ResourceLocation("palamod:textures/gui/LuckyBlock/hud/vision/module_icone.png"));
    registerPacket(ClientVisionPacket.Handler.class, ClientVisionPacket.class, Side.CLIENT);
    registerPacket(ServerVisionPacket.Handler.class, ServerVisionPacket.class, Side.SERVER);
    super.init(side);
  }
  
  public static ModuleVision getInstance() {
    if (instance == null)
      new WidgetVision(); 
    return instance;
  }
  
  public long getLastUpdate() {
    return this.lastUpdate;
  }
  
  public void setLastUpdate(long lastUpdate) {
    this.lastUpdate = lastUpdate;
  }
  
  public boolean showInventory() {
    return ((BooleanSetting)getSetting("showInventory")).getValue();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\hud\vision\ModuleVision.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */