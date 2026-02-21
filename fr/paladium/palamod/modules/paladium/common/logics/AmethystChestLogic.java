package fr.paladium.palamod.modules.paladium.common.logics;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.resource.Resource;
import net.minecraft.util.ResourceLocation;

public class AmethystChestLogic extends ModdedChestLogic {
  public AmethystChestLogic() {
    super(1, 1);
    this.colors = new Color[] { new Color(183, 30, 255), new Color(125, 0, 184) };
  }
  
  public String func_145825_b() {
    return "Amethyst Chest";
  }
  
  public Resource getSlotTexture() {
    return Resource.of(new ResourceLocation("palamod", "textures/gui/container/moddedchest/slotAmethyst.png"));
  }
  
  @SideOnly(Side.CLIENT)
  public Color[] getFontColors() {
    return this.colors;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\logics\AmethystChestLogic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */