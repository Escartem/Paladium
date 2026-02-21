package fr.paladium.achievement.core.helios;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.achievement.core.helios.packets.SCPacketAchievement;
import fr.paladium.helios.module.BaseModule;
import fr.paladium.helios.module.utils.annotations.Module;
import fr.paladium.helios.module.utils.interfaces.IWidget;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;

@Module(name = "achievement", version = 1)
public class ModuleAchievement extends BaseModule<WidgetAchievement> {
  private static ModuleAchievement instance;
  
  private final ResourceLocation icon;
  
  private String text;
  
  private long end;
  
  public String getText() {
    return this.text;
  }
  
  public void setText(String text) {
    this.text = text;
  }
  
  public long getEnd() {
    return this.end;
  }
  
  public void setEnd(long end) {
    this.end = end;
  }
  
  public ModuleAchievement() {
    super((IWidget)new WidgetAchievement());
    instance = this;
    this.icon = new ResourceLocation("achievement", "textures/gui/home/icon_helios.png");
  }
  
  public void init(Side side) {
    setEnable(true);
    setIcon(this.icon);
    registerPacket(SCPacketAchievement.Handler.class, SCPacketAchievement.class, Side.CLIENT);
    super.init(side);
  }
  
  public static ModuleAchievement getInstance() {
    return instance;
  }
  
  public void setAchievementTitle(String text, long duration) {
    this.text = text;
    this.end = System.currentTimeMillis() + duration;
  }
  
  public void sendAchievementTitle(EntityPlayerMP player, String text, long duration) {
    getNetwork().sendTo((IMessage)new SCPacketAchievement(text, duration), player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\core\helios\ModuleAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */