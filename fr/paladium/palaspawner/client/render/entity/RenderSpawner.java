package fr.paladium.palaspawner.client.render.entity;

import fr.paladium.palaspawner.common.entity.EntitySpawner;
import fr.paladium.palaspawner.common.manager.SpawnerManager;
import fr.paladium.palaspawner.common.spawner.data.ASpawnerEntityData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderSpawner extends RenderLiving {
  private static final ResourceLocation STEVE_TEXTURE = new ResourceLocation("textures/entity/steve.png");
  
  private final SpawnerManager manager;
  
  public RenderSpawner() {
    super((ModelBase)new ModelBiped(0.0F), 0.5F);
    this.manager = SpawnerManager.getInstance();
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    return STEVE_TEXTURE;
  }
  
  public void func_76986_a(Entity entity, double x, double y, double z, float f1, float f2) {
    EntitySpawner entitySpawner = (EntitySpawner)entity;
    ASpawnerEntityData data = this.manager.getSpawnerData(entitySpawner.getEntityType());
    if (data == null)
      return; 
    EntityLiving substituteEntity = data.getEntity();
    if (substituteEntity == null)
      return; 
    substituteEntity.func_70012_b(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, entitySpawner.field_70177_z, entity.field_70125_A);
    substituteEntity.field_70761_aq = entitySpawner.field_70761_aq;
    substituteEntity.field_70126_B = entitySpawner.field_70126_B;
    substituteEntity.field_70759_as = entitySpawner.field_70759_as;
    substituteEntity.field_70758_at = entitySpawner.field_70758_at;
    substituteEntity.field_70125_A = entity.field_70125_A;
    substituteEntity.field_70127_C = entity.field_70127_C;
    RenderLiving render = (RenderLiving)RenderManager.field_78727_a.func_78713_a((Entity)substituteEntity);
    if (render != null) {
      GL11.glDisable(2896);
      RenderManager.field_78727_a.func_147940_a((Entity)substituteEntity, x, y, z, 0.0F, 0.0F);
      GL11.glEnable(2896);
    } 
    renderHologram(entitySpawner, x, y, z);
  }
  
  public void renderHologram(EntitySpawner spawner, double x, double y, double z) {
    GL11.glPushMatrix();
    GL11.glTranslated(x, y + 2.299999952316284D, z);
    GL11.glRotated(180.0D, 0.0D, 0.0D, 1.0D);
    GL11.glRotated((Minecraft.func_71410_x()).field_71439_g.field_70177_z, 0.0D, 1.0D, 0.0D);
    float scale = 0.02F;
    GL11.glScaled(scale, scale, scale);
    GL11.glDisable(2896);
    String name = spawner.getHologramText();
    int nameSize = (Minecraft.func_71410_x()).field_71466_p.func_78256_a(name);
    (Minecraft.func_71410_x()).field_71466_p.func_78276_b(name, -nameSize / 2, 0, 16777215);
    GL11.glEnable(2896);
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\client\render\entity\RenderSpawner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */