package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.models.entities.ModelDutchBoat;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.entities.EntityDutchBoat;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderDutchBoat extends Render {
  private static final ResourceLocation boatTextures = new ResourceLocation("palamod", "textures/entity/dutch_boat.png");
  
  protected ModelDutchBoat modelBoat = new ModelDutchBoat();
  
  public void doRender(EntityDutchBoat entity, double x, double y, double z, float yaw, float partialTicks) {
    GL11.glPushMatrix();
    GL11.glTranslatef((float)x, (float)y - 0.2F, (float)z);
    GL11.glRotatef(90.0F - yaw, 0.0F, 1.0F, 0.0F);
    float f2 = entity.func_70268_h() - partialTicks;
    float f3 = entity.func_70271_g() - partialTicks;
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
  
  protected ResourceLocation getEntityTexture(EntityDutchBoat entity) {
    return boatTextures;
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    return getEntityTexture((EntityDutchBoat)entity);
  }
  
  public void func_76986_a(Entity entity, double x, double y, double z, float yaw, float partialTicks) {
    doRender((EntityDutchBoat)entity, x, y + 1.4D, z, yaw, partialTicks);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\client\renders\entities\RenderDutchBoat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */