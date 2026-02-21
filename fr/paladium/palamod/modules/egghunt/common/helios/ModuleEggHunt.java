package fr.paladium.palamod.modules.egghunt.common.helios;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.helios.module.BaseModule;
import fr.paladium.helios.module.utils.annotations.Module;
import fr.paladium.helios.module.utils.interfaces.IWidget;
import fr.paladium.helios.module.utils.settings.BooleanSetting;
import fr.paladium.palamod.modules.egghunt.common.helios.network.SCPacketEggHunt;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;

@Module(name = "egghunt", version = 1)
public class ModuleEggHunt extends BaseModule<WidgetEggHunt> {
  private static ModuleEggHunt instance;
  
  private final ResourceLocation icon;
  
  private boolean active;
  
  private boolean canXp;
  
  public void setActive(boolean active) {
    this.active = active;
  }
  
  public void setCanXp(boolean canXp) {
    this.canXp = canXp;
  }
  
  public ResourceLocation getIcon() {
    return this.icon;
  }
  
  public boolean isActive() {
    return this.active;
  }
  
  public boolean isCanXp() {
    return this.canXp;
  }
  
  public ModuleEggHunt() {
    super((IWidget)new WidgetEggHunt());
    instance = this;
    this.icon = new ResourceLocation("palamod", "textures/helios/modules/egghunt.png");
  }
  
  public void init(Side side) {
    setEnable(true);
    setIcon(this.icon);
    registerPacket(SCPacketEggHunt.Handler.class, SCPacketEggHunt.class, Side.CLIENT);
    super.init(side);
  }
  
  public static ModuleEggHunt getInstance() {
    return instance;
  }
  
  public boolean isTextShadow() {
    return ((BooleanSetting)getSetting("textShadow")).getValue();
  }
  
  public void sendEggHunt(EntityPlayerMP player, boolean active, boolean canXp) {
    getNetwork().sendTo((IMessage)new SCPacketEggHunt(active, canXp), player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\egghunt\common\helios\ModuleEggHunt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */