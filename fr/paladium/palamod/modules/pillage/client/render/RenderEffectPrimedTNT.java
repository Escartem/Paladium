package fr.paladium.palamod.modules.pillage.client.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.pillage.common.blocks.effects.TNTEffect;
import fr.paladium.palamod.modules.pillage.common.effects.EntityTNTEffectPrimed;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderEffectPrimedTNT extends Render {
  private final RenderBlocks blockRenderer = new RenderBlocks();
  
  public RenderEffectPrimedTNT() {
    this.field_76989_e = 0.5F;
  }
  
  public void doRender(EntityTNTEffectPrimed entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
    GL11.glPushMatrix();
    GL11.glTranslatef((float)x, (float)y, (float)z);
    if (entity.field_70516_a - partialTicks + 1.0F < 10.0F) {
      float f1 = 1.0F - (entity.field_70516_a - partialTicks + 1.0F) / 10.0F;
      if (f1 < 0.0F)
        f1 = 0.0F; 
      if (f1 > 1.0F)
        f1 = 1.0F; 
      f1 *= f1;
      f1 *= f1;
      float f3 = 1.0F + f1 * 0.3F;
      GL11.glScalef(f3, f3, f3);
    } 
    float f2 = (1.0F - (entity.field_70516_a - partialTicks + 1.0F) / 100.0F) * 0.8F;
    func_110777_b((Entity)entity);
    TNTEffect tNTEffect = entity.getBlock();
    this.blockRenderer.func_147800_a((tNTEffect != null) ? (Block)tNTEffect : Blocks.field_150335_W, 0, entity.func_70013_c(partialTicks));
    if (entity.field_70516_a / 5 % 2 == 0) {
      GL11.glDisable(3553);
      GL11.glDisable(2896);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 772);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, f2);
      this.blockRenderer.func_147800_a((tNTEffect != null) ? (Block)tNTEffect : Blocks.field_150335_W, 0, 1.0F);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDisable(3042);
      GL11.glEnable(2896);
      GL11.glEnable(3553);
    } 
    GL11.glPopMatrix();
  }
  
  protected ResourceLocation getEntityTexture(EntityTNTEffectPrimed p_110775_1_) {
    return TextureMap.field_110575_b;
  }
  
  protected ResourceLocation func_110775_a(Entity p_110775_1_) {
    return getEntityTexture((EntityTNTEffectPrimed)p_110775_1_);
  }
  
  public void func_76986_a(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
    doRender((EntityTNTEffectPrimed)entity, x, y, z, p_76986_8_, partialTicks);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\client\render\RenderEffectPrimedTNT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */