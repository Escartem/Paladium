package fr.paladium.palamod.modules.paladium.common.potion;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.lib.apollon.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class PotionFireImbue extends Potion implements ITooltipWiki {
  private final ResourceLocation icon = new ResourceLocation("palamod:textures/gui/effects/FireEffect.png");
  
  public PotionFireImbue(int par1, boolean par2, int par3) {
    super(par1, par2, par3);
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
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/items-et-machines/items-du-palamod/pour-le-pvp#2.-les-potions";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\potion\PotionFireImbue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */