package fr.paladium.palamod.modules.luckyblock.renders;

import fr.paladium.palamod.modules.luckyblock.renders.models.ModelMissileMeteo;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderMissileMeteo extends Render {
  ModelMissileMeteo model = new ModelMissileMeteo();
  
  ResourceLocation texture = new ResourceLocation("palamod", "textures/models/missile_meteo.png");
  
  protected ResourceLocation func_110775_a(Entity p_110775_1_) {
    return this.texture;
  }
  
  public void func_76986_a(Entity entity, double x, double y, double z, float p_76986_8_, float p_76986_9_) {
    GL11.glPushMatrix();
    func_110776_a(func_110775_a(entity));
    GL11.glTranslated(x, y, z);
    GL11.glRotated(180.0D, 0.0D, 0.0D, 1.0D);
    this.model.func_78088_a(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.04F);
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\RenderMissileMeteo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */