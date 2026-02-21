package fr.paladium.palamod.modules.luckyblock.renders;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderGhostHalloween extends RenderLiving {
  public RenderGhostHalloween(ModelBiped modelbiped, float f) {
    super((ModelBase)modelbiped, f);
  }
  
  public void func_76986_a(EntityLiving entityliving, double d, double d1, double d2, float f, float f1) {
    boolean flag = false;
    GL11.glPushMatrix();
    GL11.glEnable(3042);
    float transparency = 0.6F;
    GL11.glBlendFunc(770, 771);
    GL11.glColor4f(0.8F, 0.8F, 0.8F, 0.6F);
    super.func_76986_a(entityliving, d, d1, d2, f, f1);
    GL11.glDisable(3042);
    GL11.glPopMatrix();
  }
  
  protected ResourceLocation func_110775_a(Entity par1Entity) {
    return new ResourceLocation("palamod", "textures/entity/ghosthalloween.png");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\RenderGhostHalloween.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */