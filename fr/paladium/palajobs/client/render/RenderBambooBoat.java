package fr.paladium.palajobs.client.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palajobs.core.entity.EntityBambooBoat;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBoat;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderBambooBoat extends Render {
  private static final ResourceLocation boatTextures = new ResourceLocation("palajobs:textures/entity/bamboo_boat.png");
  
  private ModelBase modelBoat = (ModelBase)new ModelBoat();
  
  private void doRender(EntityBambooBoat entity, double x, double y, double z, float yaw, float pitch) {
    GL11.glPushMatrix();
    GL11.glTranslatef((float)x, (float)y, (float)z);
    GL11.glRotatef(180.0F - yaw, 0.0F, 1.0F, 0.0F);
    float f2 = entity.func_70268_h() - pitch;
    float f3 = entity.func_70271_g() - pitch;
    if (f3 < 0.0F)
      f3 = 0.0F; 
    if (f2 > 0.0F)
      GL11.glRotatef(MathHelper.func_76126_a(f2) * f2 * f3 / 10.0F * entity.func_70267_i(), 1.0F, 0.0F, 0.0F); 
    float f4 = 0.75F;
    GL11.glScalef(f4, f4, f4);
    GL11.glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4);
    func_110777_b((Entity)entity);
    GL11.glScalef(-1.0F, -1.0F, 1.0F);
    this.modelBoat.func_78088_a((Entity)entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
    GL11.glPopMatrix();
  }
  
  private ResourceLocation getEntityTexture(EntityBambooBoat entity) {
    return boatTextures;
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    return getEntityTexture((EntityBambooBoat)entity);
  }
  
  public void func_76986_a(Entity entity, double x, double y, double z, float yaw, float pitch) {
    doRender((EntityBambooBoat)entity, x, y, z, yaw, pitch);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\client\render\RenderBambooBoat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */