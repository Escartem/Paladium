package fr.paladium.palamod.modules.paladium.common.potion;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.lwjgl.opengl.GL11;

public class PotionFrost extends Potion {
  public PotionFrost(int par1, boolean par2, int par3) {
    super(par1, par2, par3);
  }
  
  public Potion func_76399_b(int par1, int par2) {
    super.func_76399_b(par1, par2);
    return this;
  }
  
  @SideOnly(Side.CLIENT)
  public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
    RenderHelper.func_74519_b();
    GL11.glPushMatrix();
    GL11.glEnable(3042);
    GL11.glDisable(2896);
    GL11.glEnable(32826);
    GL11.glTranslated((x + 7), (y + 7), 0.0D);
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    (new RenderItem()).func_82406_b(
        (Minecraft.func_71410_x()).field_71466_p, 
        (Minecraft.func_71410_x()).field_71446_o, new ItemStack(Blocks.field_150403_cj), 0, 0);
    GL11.glDisable(3042);
    GL11.glEnable(2896);
    GL11.glDisable(32826);
    GL11.glPopMatrix();
    RenderHelper.func_74518_a();
  }
  
  @SideOnly(Side.CLIENT)
  public boolean func_76400_d() {
    return false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\potion\PotionFrost.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */