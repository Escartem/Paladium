package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.renders.entities;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.models.entities.ModelBodyGuard;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.entities.EntityBodyGuard;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderBodyGuard extends GeoEntityRenderer<EntityBodyGuard> {
  public RenderBodyGuard() {
    super(RenderManager.field_78727_a, (AnimatedGeoModel)new ModelBodyGuard());
  }
  
  public void func_76986_a(Entity entity, double posX, double posY, double posZ, float f1, float f2) {
    GL11.glPushMatrix();
    GL11.glTranslated(posX, posY, posZ);
    GL11.glRotatef(entity.field_70125_A, 0.0F, 1.0F, 0.0F);
    GL11.glTranslated(-posX, -posY, -posZ);
    try {
      super.func_76986_a(entity, posX, posY, posZ, f1, f2);
    } catch (Exception e) {
      e.printStackTrace();
    } 
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\client\renders\entities\RenderBodyGuard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */