package fr.paladium.palamod.modules.luckyblock.renders;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.entity.EntityBoatFurnace;
import fr.paladium.palamod.modules.luckyblock.renders.models.ModelBoatFurnace;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderBoatFurnace extends Render {
  private static final ResourceLocation boatTextures = new ResourceLocation("palamod", "textures/entity/furnace_boat.png");
  
  protected ModelBase modelBoat = (ModelBase)new ModelBoatFurnace();
  
  public void doRender(EntityBoatFurnace p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
    GL11.glPushMatrix();
    GL11.glTranslatef((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
    GL11.glRotatef(0.0F - p_76986_8_, 0.0F, 1.0F, 0.0F);
    float f2 = p_76986_1_.getTimeSinceHit() - p_76986_9_;
    float f3 = p_76986_1_.getDamageTaken() - p_76986_9_;
    if (f3 < 0.0F)
      f3 = 0.0F; 
    float f4 = 0.75F;
    GL11.glScalef(f4, f4, f4);
    GL11.glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4);
    GL11.glTranslatef(0.5F, -0.3F, 0.0F);
    func_110777_b((Entity)p_76986_1_);
    GL11.glScalef(-1.0F, -1.0F, 1.0F);
    this.modelBoat.func_78088_a((Entity)p_76986_1_, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
    GL11.glPopMatrix();
  }
  
  protected ResourceLocation getEntityTexture(EntityBoatFurnace p_110775_1_) {
    return boatTextures;
  }
  
  protected ResourceLocation func_110775_a(Entity p_110775_1_) {
    return getEntityTexture((EntityBoatFurnace)p_110775_1_);
  }
  
  public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
    doRender((EntityBoatFurnace)p_76986_1_, p_76986_2_, p_76986_4_ + 1.4D, p_76986_6_, p_76986_8_, p_76986_9_);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\RenderBoatFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */