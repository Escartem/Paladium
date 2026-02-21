package fr.paladium.palamod.modules.back2future.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.ModItems;
import fr.paladium.palamod.modules.back2future.client.OpenGLHelper;
import fr.paladium.palamod.modules.back2future.entities.EntityLingeringPotion;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

@SideOnly(Side.CLIENT)
public class LingeringPotionRenderer extends RenderSnowball {
  public LingeringPotionRenderer() {
    super(ModItems.lingering_potion);
  }
  
  public void func_76986_a(Entity entity, double x, double y, double z, float p_76986_8_, float p_76986_9_) {
    int passes;
    if (!(entity instanceof EntityLingeringPotion))
      return; 
    ItemStack stack = ((EntityLingeringPotion)entity).getStack();
    if (stack == null || stack.func_77973_b() == null)
      return; 
    if (stack.func_77973_b().func_77623_v()) {
      passes = stack.func_77973_b().getRenderPasses(0);
    } else {
      passes = 1;
    } 
    OpenGLHelper.pushMatrix();
    OpenGLHelper.translate(x, y, z);
    OpenGLHelper.enableRescaleNormal();
    OpenGLHelper.scale(0.5F, 0.5F, 0.5F);
    func_110777_b(entity);
    for (int pass = 0; pass < passes; pass++) {
      IIcon icon = stack.func_77973_b().getIcon(stack, pass);
      if (icon != null) {
        OpenGLHelper.pushMatrix();
        OpenGLHelper.colour(stack.func_77973_b().func_82790_a(stack, pass));
        renderIcon(icon);
        OpenGLHelper.popMatrix();
      } 
    } 
    OpenGLHelper.colour(1.0F, 1.0F, 1.0F);
    OpenGLHelper.disableRescaleNormal();
    OpenGLHelper.popMatrix();
  }
  
  private void renderIcon(IIcon icon) {
    Tessellator tessellator = Tessellator.field_78398_a;
    float minU = icon.func_94209_e();
    float maxU = icon.func_94212_f();
    float minV = icon.func_94206_g();
    float maxV = icon.func_94210_h();
    OpenGLHelper.rotate(180.0F - this.field_76990_c.field_78735_i, 0.0F, 1.0F, 0.0F);
    OpenGLHelper.rotate(-this.field_76990_c.field_78732_j, 1.0F, 0.0F, 0.0F);
    tessellator.func_78382_b();
    tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
    tessellator.func_78374_a(-0.5D, -0.25D, 0.0D, minU, maxV);
    tessellator.func_78374_a(0.5D, -0.25D, 0.0D, maxU, maxV);
    tessellator.func_78374_a(0.5D, 0.75D, 0.0D, maxU, minV);
    tessellator.func_78374_a(-0.5D, 0.75D, 0.0D, minU, minV);
    tessellator.func_78381_a();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\renderer\entity\LingeringPotionRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */