package fr.paladium.palamod.modules.luckyblock.renders;

import fr.paladium.palamod.modules.spellsv2.models.ModelGhost;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderEntityGhostProjectile extends Render {
  public final ResourceLocation texture = new ResourceLocation("palamod", "textures/entity/ghost.png");
  
  public final ModelBase mainModel = (ModelBase)new ModelGhost();
  
  protected ResourceLocation func_110775_a(Entity p_110775_1_) {
    return this.texture;
  }
  
  public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
    GL11.glPushMatrix();
    float scale = 2.0F;
    func_110776_a(func_110775_a(p_76986_1_));
    GL11.glTranslated(p_76986_2_, p_76986_4_ + 1.0D, p_76986_6_);
    GL11.glScalef(scale, scale, scale);
    GL11.glRotated(180.0D, 1.0D, 0.0D, 0.0D);
    GL11.glRotated(90.0D, 0.0D, 1.0D, 0.0D);
    this.mainModel.func_78088_a(p_76986_1_, 5.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0475F);
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\RenderEntityGhostProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */