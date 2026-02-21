package fr.paladium.palamod.modules.paladium.common.items.boost.hud;

import cpw.mods.fml.relauncher.Side;
import fr.paladium.helios.module.BaseModule;
import fr.paladium.helios.module.utils.annotations.Module;
import fr.paladium.helios.module.utils.interfaces.IWidget;
import fr.paladium.palamod.modules.paladium.common.items.boost.packet.PlayerBoostEPropertiesSync;
import net.minecraft.util.ResourceLocation;

@Module(name = "boost", version = 1)
public class ModuleBoost extends BaseModule<WidgetBoost> {
  public static ModuleBoost instance;
  
  private long boostDoubleXp;
  
  private long boostMinerFou;
  
  public ModuleBoost() {
    super((IWidget)new WidgetBoost());
    instance = this;
  }
  
  public void init(Side side) {
    setEnable(false);
    setIcon(new ResourceLocation("palamod", "textures/helios/modules/boost.png"));
    registerPacket(PlayerBoostEPropertiesSync.Handler.class, PlayerBoostEPropertiesSync.class, Side.CLIENT);
    super.init(side);
  }
  
  public static ModuleBoost getInstance() {
    return instance;
  }
  
  public long getBoostDoubleXp() {
    return this.boostDoubleXp;
  }
  
  public void setBoostDoubleXp(long boostDoubleXp) {
    this.boostDoubleXp = boostDoubleXp;
  }
  
  public long getBoostMinerFou() {
    return this.boostMinerFou;
  }
  
  public void setBoostMinerFou(long boostMinerFou) {
    this.boostMinerFou = boostMinerFou;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\boost\hud\ModuleBoost.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */