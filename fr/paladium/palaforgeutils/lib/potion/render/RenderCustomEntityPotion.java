package fr.paladium.palaforgeutils.lib.potion.render;

import fr.paladium.palaforgeutils.lib.potion.entity.CustomEntityPotion;
import fr.paladium.palaforgeutils.lib.potion.item.CustomSplashItemPotion;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderCustomEntityPotion extends Render {
  public void func_76986_a(Entity entity, double x, double y, double z, float fp1, float fp2) {
    if (!(entity instanceof CustomEntityPotion))
      return; 
    CustomEntityPotion entityPotion = (CustomEntityPotion)entity;
    ItemStack stack = entityPotion.getItem();
    if (stack == null)
      return; 
    CustomSplashItemPotion item = (CustomSplashItemPotion)stack.func_77973_b();
    IIcon iicon = item.func_77617_a(stack.func_77960_j());
    if (iicon == null)
      return; 
    GL11.glPushMatrix();
    GL11.glTranslated(x, y, z);
    GL11.glEnable(32826);
    GL11.glScalef(0.5F, 0.5F, 0.5F);
    func_110777_b(entity);
    Tessellator tessellator = Tessellator.field_78398_a;
    int color = item.getPotion().getColor().getRGB();
    float red = (color >> 16 & 0xFF) / 255.0F;
    float green = (color >> 8 & 0xFF) / 255.0F;
    float blue = (color & 0xFF) / 255.0F;
    GL11.glColor3f(red, green, blue);
    GL11.glPushMatrix();
    drawIcon(tessellator, ItemPotion.func_94589_d("overlay"));
    GL11.glPopMatrix();
    GL11.glColor3f(1.0F, 1.0F, 1.0F);
    drawIcon(tessellator, iicon);
    GL11.glDisable(32826);
    GL11.glPopMatrix();
  }
  
  private void drawIcon(Tessellator tessellator, IIcon icon) {
    float f = icon.func_94209_e();
    float f1 = icon.func_94212_f();
    float f2 = icon.func_94206_g();
    float f3 = icon.func_94210_h();
    float f4 = 1.0F;
    float f5 = 0.5F;
    float f6 = 0.25F;
    GL11.glRotatef(180.0F - this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(-this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
    tessellator.func_78382_b();
    tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
    tessellator.func_78374_a((0.0F - f5), (0.0F - f6), 0.0D, f, f3);
    tessellator.func_78374_a((f4 - f5), (0.0F - f6), 0.0D, f1, f3);
    tessellator.func_78374_a((f4 - f5), (f4 - f6), 0.0D, f1, f2);
    tessellator.func_78374_a((0.0F - f5), (f4 - f6), 0.0D, f, f2);
    tessellator.func_78381_a();
  }
  
  public ResourceLocation func_110775_a(Entity p_110775_1_) {
    return TextureMap.field_110576_c;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\potion\render\RenderCustomEntityPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */