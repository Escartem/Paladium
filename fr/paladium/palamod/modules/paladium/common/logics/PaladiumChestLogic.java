package fr.paladium.palamod.modules.paladium.common.logics;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.resource.Resource;
import net.minecraft.util.ResourceLocation;

public class PaladiumChestLogic extends ModdedChestLogic {
  public PaladiumChestLogic() {
    super(2, 4);
    this.colors = new Color[] { new Color(239, 57, 38), new Color(185, 28, 12) };
  }
  
  public String func_145825_b() {
    return "Paladium chest";
  }
  
  public Resource getSlotTexture() {
    return Resource.of(new ResourceLocation("palamod", "textures/gui/container/moddedchest/slotPaladium.png"));
  }
  
  @SideOnly(Side.CLIENT)
  public Color[] getFontColors() {
    return this.colors;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\logics\PaladiumChestLogic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */