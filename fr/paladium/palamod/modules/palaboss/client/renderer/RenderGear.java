package fr.paladium.palamod.modules.palaboss.client.renderer;

import fr.paladium.palamod.modules.palaboss.client.models.ModelGear;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderGear extends Render {
  private ModelGear model = new ModelGear();
  
  private static final ResourceLocation gearTexture = new ResourceLocation("palamod", "textures/entity/projectiles/gear.png");
  
  protected ResourceLocation func_110775_a(Entity p_110775_1_) {
    return gearTexture;
  }
  
  public void func_76986_a(Entity entity, double x, double y, double z, float yaw, float partialTick) {
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.05D, y + 0.1D, z - 2.3D);
    func_110776_a(func_110775_a(entity));
    this.model.func_78088_a(entity, 0.0F, 0.0F, handleRotationFloat(entity, partialTick), 0.0F, 0.0F, 0.0625F);
    GL11.glPopMatrix();
  }
  
  protected float handleRotationFloat(Entity p_77044_1_, float p_77044_2_) {
    return p_77044_1_.field_70173_aa + p_77044_2_;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\client\renderer\RenderGear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */