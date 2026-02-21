package fr.paladium.common.combat;

import cpw.mods.fml.relauncher.Side;
import fr.paladium.helios.module.BaseModule;
import fr.paladium.helios.module.utils.annotations.Module;
import fr.paladium.helios.module.utils.interfaces.IWidget;
import net.minecraft.util.ResourceLocation;

@Module(name = "combat", version = 1)
public class ModuleCombatTag extends BaseModule<WidgetCombatTag> {
  private static ModuleCombatTag instance;
  
  private final ResourceLocation icon;
  
  public ModuleCombatTag() {
    super((IWidget)new WidgetCombatTag());
    instance = this;
    this.icon = new ResourceLocation("helios", "combat.png");
  }
  
  public void init(Side side) {
    setEnable(true);
    setIcon(this.icon);
    super.init(side);
  }
  
  public static ModuleCombatTag getInstance() {
    return instance;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\common\combat\ModuleCombatTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */