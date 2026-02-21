package fr.paladium.palamod.modules.back2future.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.client.model.ModelBoulder;
import fr.paladium.palamod.modules.back2future.entities.projectile.EntityBoulder;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderBoulder extends Render {
  protected ModelBase model = (ModelBase)new ModelBoulder();
  
  public void func_76986_a(Entity entity, double x, double y, double z, float yaw, float partialTick) {
    renderEntityModel(entity, x, y, z, yaw, partialTick);
  }
  
  public void renderEntityModel(Entity entity, double x, double y, double z, float yaw, float partialTick) {
    EntityBoulder ent = (EntityBoulder)entity;
    GL11.glPushMatrix();
    float scale = 1.0F;
    func_110776_a(func_110775_a((Entity)ent));
    GL11.glTranslated(x, y, z);
    GL11.glScalef(scale, scale, scale);
    this.model.func_78088_a((Entity)ent, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0475F);
    GL11.glPopMatrix();
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    return getBoulderTexture((EntityBoulder)entity);
  }
  
  protected ResourceLocation getBoulderTexture(EntityBoulder entity) {
    return entity.textureLoc;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\renderer\entity\RenderBoulder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */