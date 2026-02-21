package fr.paladium.palamod.modules.alchimiste.client.renderer.tesr;

import fr.paladium.palamod.modules.alchimiste.client.model.ModelExtractor;
import fr.paladium.palamod.modules.alchimiste.common.tileentities.TileEntityExtractor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class TileEntityExtractorRenderer extends TileEntitySpecialRenderer {
  public static ModelExtractor model = new ModelExtractor();
  
  public static ResourceLocation on = new ResourceLocation("palamod", "textures/alchimiste/models/tape_on.png");
  
  public static ResourceLocation off = new ResourceLocation("palamod", "textures/alchimiste/models/tape_off.png");
  
  public void func_147500_a(TileEntity tile, double x, double y, double z, float p_147500_8_) {
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    func_147499_a(((TileEntityExtractor)tile).isEnabled() ? on : off);
    GL11.glRotated((90 * ((TileEntityExtractor)tile).getAngle()), 0.0D, 1.0D, 0.0D);
    model.renderAll();
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    double xCorrection = 0.0D;
    double zCorrection = 0.0D;
    switch (((TileEntityExtractor)tile).getAngle()) {
      case 0:
        xCorrection = 0.5D;
        zCorrection = 0.25D;
        break;
      case 1:
        xCorrection = 0.25D;
        zCorrection = 0.5D;
        break;
      case 2:
        xCorrection = 0.5D;
        zCorrection = 0.75D;
        break;
      case 3:
        xCorrection = 0.75D;
        zCorrection = 0.5D;
        break;
    } 
    GL11.glTranslated(x + xCorrection, y - 0.5D, z + zCorrection);
    GL11.glRotated((90 * ((TileEntityExtractor)tile).getAngle()), 0.0D, 1.0D, 0.0D);
    GL11.glScaled(0.45D, 0.45D, 0.45D);
    GL11.glRotated(1.0D, 0.0D, -0.001D, 0.0D);
    GL11.glRotated(45.0D, 0.0D, 1.0D, 0.0D);
    GL11.glRotated(30.0D, 1.0D, 0.0D, 1.0D);
    GL11.glRotated(5.0D, -12.0D, -7.0D, -7.0D);
    if (((TileEntityExtractor)tile).getStack() != null) {
      ItemStack stack = ((TileEntityExtractor)tile).getStack();
      ItemRenderer render = new ItemRenderer(Minecraft.func_71410_x());
      render.func_78443_a((EntityLivingBase)(Minecraft.func_71410_x()).field_71439_g, stack, (int)p_147500_8_);
    } 
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\client\renderer\tesr\TileEntityExtractorRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */