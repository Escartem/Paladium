package fr.paladium.palaspawner.client.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaspawner.common.block.IntLocation;
import fr.paladium.palaspawner.common.manager.SpawnerManager;
import fr.paladium.palaspawner.common.spawner.data.ASpawnerEntityData;
import fr.paladium.palaspawner.common.tile.TileEntityEmptyMobSpawner;
import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.culling.Frustrum;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;

public class SpawnerRenderListener {
  private static final HashMap<IntLocation, EntityLiving> ENTITY_CACHE = new HashMap<>();
  
  @SubscribeEvent
  public void onRenderWorldLast(RenderWorldLastEvent event) {
    Minecraft mc = Minecraft.func_71410_x();
    EntityLivingBase cameraEntity = mc.field_71451_h;
    Vec3 renderingVector = cameraEntity.func_70666_h(event.partialTicks);
    Frustrum frustrum = new Frustrum();
    double viewX = cameraEntity.field_70142_S + (cameraEntity.field_70165_t - cameraEntity.field_70142_S) * event.partialTicks;
    double viewY = cameraEntity.field_70137_T + (cameraEntity.field_70163_u - cameraEntity.field_70137_T) * event.partialTicks;
    double viewZ = cameraEntity.field_70136_U + (cameraEntity.field_70161_v - cameraEntity.field_70136_U) * event.partialTicks;
    frustrum.func_78547_a(viewX, viewY, viewZ);
    for (Object o : mc.field_71441_e.field_147482_g) {
      if (o instanceof TileEntityEmptyMobSpawner) {
        TileEntityEmptyMobSpawner tileEntity = (TileEntityEmptyMobSpawner)o;
        if (tileEntity.shouldRenderInPass(0) && frustrum.func_78546_a(tileEntity.getRenderBoundingBox()) && 
          tileEntity.func_145835_a(viewX, viewY, viewZ) < tileEntity.func_145833_n()) {
          double x = tileEntity.field_145851_c - renderingVector.field_72450_a;
          double y = tileEntity.field_145848_d - renderingVector.field_72448_b;
          double z = tileEntity.field_145849_e - renderingVector.field_72449_c;
          renderTileEntityAt(tileEntity, x, y, z, mc.field_71439_g.field_70173_aa);
        } 
      } 
    } 
  }
  
  private Entity getOrCreateEntity(TileEntityEmptyMobSpawner te) {
    IntLocation location = IntLocation.of(te.field_145851_c, te.field_145848_d, te.field_145849_e);
    EntityLiving entity = ENTITY_CACHE.get(location);
    if (entity == null) {
      ASpawnerEntityData data = SpawnerManager.getInstance().getSpawnerData(te.getEntityId());
      if (data == null)
        return null; 
      entity = data.initEntity(te.func_145831_w());
      ENTITY_CACHE.put(location, entity);
    } else {
      Class<? extends EntityLiving> entityClass = (Class)entity.getClass();
      ASpawnerEntityData data = SpawnerManager.getInstance().getSpawnerData(entityClass);
      if (data != null && entityClass != data.getEntityClass()) {
        entity = data.initEntity(te.func_145831_w());
        ENTITY_CACHE.put(location, entity);
      } 
    } 
    return (Entity)entity;
  }
  
  public static void renderEntity(TileEntityEmptyMobSpawner te, Entity entity, double x, double y, double z, float ticks) {
    if (entity == null)
      return; 
    entity.field_70173_aa = (Minecraft.func_71410_x()).field_71439_g.field_70173_aa;
    entity.func_70029_a(te.func_145831_w());
    double ySize = entity.field_70121_D.field_72337_e - entity.field_70121_D.field_72338_b;
    float f1 = (float)(0.30000001192092896D / Math.log(ySize + 1.0D));
    f1 = Math.max(f1, 0.35F);
    GL11.glTranslatef(0.0F, 0.35F, 0.0F);
    GL11.glRotatef(((Minecraft.func_71410_x()).field_71439_g.field_70173_aa * 8), 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
    GL11.glScalef(f1, f1, f1);
    RenderManager.field_78727_a.func_147940_a(entity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
  }
  
  public void renderTileEntityAt(TileEntityEmptyMobSpawner tileEntity, double x, double y, double z, float ticks) {
    String entityId = tileEntity.getEntityId();
    if (entityId == null)
      return; 
    ASpawnerEntityData data = SpawnerManager.getInstance().getSpawnerData(entityId);
    if (data == null)
      return; 
    GL11.glPushMatrix();
    GL11.glTranslatef((float)x + 0.5F, (float)y, (float)z + 0.5F);
    renderEntity(tileEntity, getOrCreateEntity(tileEntity), x, y, z, ticks);
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaspawner\client\listener\SpawnerRenderListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */