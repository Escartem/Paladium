package fr.paladium.palamod.modules.paladium.dummy;

import java.text.DecimalFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderFloatingNumber extends Render {
  private static FontRenderer fontRenderer = (Minecraft.func_71410_x()).field_71466_p;
  
  private static DecimalFormat df = new DecimalFormat("#.##");
  
  public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
    doRender((EntityFloatingNumber)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_);
  }
  
  public void doRender(EntityFloatingNumber entity, double x, double y, double z) {
    boolean dps = entity instanceof EntityDpsFloatingNumber;
    GL11.glPushMatrix();
    GL11.glTranslated(x, y, z);
    fontRenderer = (Minecraft.func_71410_x()).field_71466_p;
    EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
    double xd = ((EntityPlayer)entityClientPlayerMP).field_70165_t - entity.field_70165_t;
    double yd = ((EntityPlayer)entityClientPlayerMP).field_70163_u - entity.field_70163_u;
    double zd = ((EntityPlayer)entityClientPlayerMP).field_70161_v - entity.field_70161_v;
    double l = Math.sqrt(xd * xd + yd * yd + zd * zd);
    double scale = 0.01D * l;
    if (dps)
      scale += 0.029999999329447746D; 
    GL11.glScaled(-scale, -scale, scale);
    GL11.glTranslated(0.0D, -l / 10.0D, 0.0D);
    GL11.glDisable(2896);
    GL11.glDepthMask(false);
    GL11.glNormal3f(0.0F, 1.0F, 0.0F);
    GL11.glRotatef(this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(-this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
    String s = df.format(entity.damage);
    if (dps)
      s = "DPS: " + s; 
    GL11.glTranslated((-fontRenderer.func_78256_a(s) / 2), 0.0D, 0.0D);
    fontRenderer.func_85187_a(s, 0, 0, -1, true);
    GL11.glTranslated((fontRenderer.func_78256_a(s) / 2), 0.0D, 0.0D);
    GL11.glDepthMask(true);
    GL11.glEnable(2896);
    GL11.glPopMatrix();
  }
  
  protected ResourceLocation func_110775_a(Entity p_110775_1_) {
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\dummy\RenderFloatingNumber.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */