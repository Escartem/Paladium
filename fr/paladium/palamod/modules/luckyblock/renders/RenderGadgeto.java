package fr.paladium.palamod.modules.luckyblock.renders;

import fr.paladium.palamod.modules.luckyblock.renders.models.ModelGadgeto;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class RenderGadgeto implements IItemRenderer {
  private static ResourceLocation texture = new ResourceLocation("palamod", "textures/models/gadgeto.png");
  
  private static ModelGadgeto model = new ModelGadgeto();
  
  private TextureManager texturemanager = (Minecraft.func_71410_x()).field_71446_o;
  
  public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
    return true;
  }
  
  public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
    return true;
  }
  
  public void renderItem(IItemRenderer.ItemRenderType type, ItemStack stack, Object... data) {
    GL11.glPushMatrix();
    GL11.glPushMatrix();
    float scale = 0.1F;
    switch (type) {
      case INVENTORY:
        GL11.glRotated(225.0D, 0.0D, 1.0D, 0.0D);
        GL11.glScaled(0.75D, 0.75D, 0.75D);
        GL11.glTranslated(0.0D, -0.3D, 0.0D);
      case ENTITY:
        GL11.glScaled(0.75D, 0.75D, 0.75D);
      case EQUIPPED:
        GL11.glScaled(2.5D, 2.5D, 2.5D);
        GL11.glRotated(225.0D, 0.0D, 1.0D, 0.0D);
        GL11.glRotated(180.0D, 1.0D, 0.0D, 0.0D);
        GL11.glTranslated(0.1D, -2.2D, 0.0D);
        break;
      case EQUIPPED_FIRST_PERSON:
        GL11.glScaled(1.5D, 1.5D, 1.5D);
        GL11.glRotated(-45.0D, 0.0D, 1.0D, 0.0D);
        GL11.glRotated(180.0D, 0.0D, 0.0D, 1.0D);
        GL11.glTranslated(0.0D, -2.6D, 0.0D);
        break;
    } 
    (Minecraft.func_71410_x()).field_71446_o.func_110577_a(texture);
    model.func_78088_a(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
    GL11.glPopMatrix();
    if (stack != null && !(stack.func_77973_b() instanceof fr.paladium.palamod.modules.luckyblock.items.black.ItemGadgeto)) {
      GL11.glPushMatrix();
      IIcon iicon = stack.func_77954_c();
      this.texturemanager.func_110577_a(this.texturemanager.func_130087_a(stack.func_94608_d()));
      TextureUtil.func_152777_a(false, false, 1.0F);
      Tessellator tessellator = Tessellator.field_78398_a;
      float f = iicon.func_94209_e();
      float f1 = iicon.func_94212_f();
      float f2 = iicon.func_94206_g();
      float f3 = iicon.func_94210_h();
      float f4 = 0.0F;
      float f5 = 0.3F;
      GL11.glEnable(32826);
      GL11.glTranslatef(-f4, -f5, 0.0F);
      float f6 = 1.5F;
      GL11.glScalef(f6, f6, f6);
      switch (type) {
        case INVENTORY:
          GL11.glEnable(3042);
          GL11.glRotated(225.0D, 0.0D, 1.0D, 0.0D);
          GL11.glScaled(0.75D, 0.75D, 0.75D);
          GL11.glTranslated(0.0D, -0.3D, 0.0D);
        case ENTITY:
          GL11.glScaled(0.75D, 0.75D, 0.75D);
        case EQUIPPED:
          GL11.glScaled(1.5D, 1.5D, 1.5D);
          GL11.glRotated(-45.0D, 0.0D, 1.0D, 0.0D);
          GL11.glRotated(-45.0D, 0.0D, 0.0D, 1.0D);
          GL11.glTranslated(-1.2D, -0.3D, 0.1D);
          break;
        case EQUIPPED_FIRST_PERSON:
          GL11.glRotated(225.0D, 0.0D, 1.0D, 0.0D);
          GL11.glRotated(315.0D, 0.0D, 0.0D, 1.0D);
          GL11.glTranslatef(-1.56F, 0.0F, 0.0F);
          break;
      } 
      ItemRenderer.func_78439_a(tessellator, f1, f2, f, f3, iicon.func_94211_a(), iicon.func_94216_b(), 0.0625F);
      GL11.glDisable(3042);
      GL11.glDisable(32826);
      this.texturemanager.func_110577_a(this.texturemanager.func_130087_a(stack.func_94608_d()));
      TextureUtil.func_147945_b();
      GL11.glPopMatrix();
    } 
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\RenderGadgeto.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */