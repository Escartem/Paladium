package fr.paladium.palamod.modules.paladium.common.potion;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.lib.apollon.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class PotionGravity extends Potion {
  private final ResourceLocation icon = new ResourceLocation("palamod:textures/gui/effects/gravity.png");
  
  public PotionGravity(int p_i1573_1_, boolean p_i1573_2_, int p_i1573_3_) {
    super(p_i1573_1_, p_i1573_2_, p_i1573_3_);
  }
  
  public Potion func_76399_b(int par1, int par2) {
    super.func_76399_b(par1, par2);
    return this;
  }
  
  @SideOnly(Side.CLIENT)
  public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
    GuiUtils.drawImageTransparent((x + 5), (y + 5), this.icon, 20.0D, 20.0D);
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_76400_d() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\potion\PotionGravity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */