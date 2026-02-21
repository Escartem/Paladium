package fr.paladium.palamod.modules.paladium.common.logics;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.zephyrui.lib.color.Color;
import fr.paladium.zephyrui.lib.resource.Resource;
import net.minecraft.util.ResourceLocation;

public class TitaneChestLogic extends ModdedChestLogic {
  public TitaneChestLogic() {
    super(1, 2);
    this.colors = new Color[] { new Color(165, 165, 165), new Color(88, 88, 88) };
  }
  
  public String func_145825_b() {
    return "Titane Chest";
  }
  
  public Resource getSlotTexture() {
    return Resource.of(new ResourceLocation("palamod", "textures/gui/container/moddedchest/slotTitane.png"));
  }
  
  @SideOnly(Side.CLIENT)
  public Color[] getFontColors() {
    return this.colors;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\logics\TitaneChestLogic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */