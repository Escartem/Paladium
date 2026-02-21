package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.renders.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.entity.june.dancer.enums.DanceDirection;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.models.entities.ModelPaperAirplane;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.entities.EntityPaperAirplane;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderPaperAirplane extends Render {
  private static final ResourceLocation boatTextures = new ResourceLocation("palamod", "textures/entity/paper_airplane.png");
  
  protected ModelPaperAirplane model = new ModelPaperAirplane();
  
  public void doRender(EntityPaperAirplane entity, double x, double y, double z, float yaw, float partialTicks) {
    GL11.glPushMatrix();
    GL11.glTranslatef((float)x, (float)y + 0.2F, (float)z);
    int direction = DanceDirection.roundYaw(yaw);
    if (direction == 2) {
      yaw = 180.0F;
    } else if (direction == 3) {
      yaw = 90.0F;
    } else if (direction == 0) {
      yaw = 0.0F;
    } else if (direction == 1) {
      yaw = 270.0F;
    } 
    GL11.glRotatef(yaw, 0.0F, 1.0F, 0.0F);
    float f4 = 0.75F;
    GL11.glScalef(f4, f4, f4);
    GL11.glScalef(1.0F / f4, 1.0F / f4, 1.0F / f4);
    func_110777_b((Entity)entity);
    GL11.glScalef(-1.0F, -1.0F, 1.0F);
    this.model.func_78088_a((Entity)entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
    GL11.glPopMatrix();
  }
  
  protected ResourceLocation getEntityTexture(EntityPaperAirplane entity) {
    return boatTextures;
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    return getEntityTexture((EntityPaperAirplane)entity);
  }
  
  public void func_76986_a(Entity entity, double x, double y, double z, float yaw, float partialTicks) {
    doRender((EntityPaperAirplane)entity, x, y + 1.4D, z, yaw, partialTicks);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\client\renders\entities\RenderPaperAirplane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */