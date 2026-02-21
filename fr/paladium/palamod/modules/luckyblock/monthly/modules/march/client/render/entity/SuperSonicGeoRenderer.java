package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.render.entity;

import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.model.entity.SupersonicRocketModel;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.entity.EntitySupersonicRocket;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.utils.RelativeDirection;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SuperSonicGeoRenderer extends GeoEntityRenderer<EntitySupersonicRocket> {
  private RenderManager renderManager;
  
  public SuperSonicGeoRenderer(RenderManager renderManager) {
    super(renderManager, (AnimatedGeoModel)new SupersonicRocketModel());
    this.renderManager = renderManager;
  }
  
  public void func_76986_a(Entity arg0, double arg1, double arg2, double arg3, float yaw, float arg5) {
    GL11.glPushMatrix();
    GL11.glTranslated(arg1, arg2, arg3);
    int direction = RelativeDirection.roundYaw(yaw);
    GL11.glTranslated(0.0D, 0.3D, 0.0D);
    GL11.glScaled(0.5D, 0.5D, 0.5D);
    GL11.glRotated(90.0D, 1.0D, 0.0D, 0.0D);
    GL11.glRotated(yaw, 0.0D, 0.0D, 1.0D);
    GL11.glRotated((yaw % 180.0F), 0.0D, 1.0D, 0.0D);
    GL11.glTranslated(0.0D, -1.0D, 0.0D);
    GL11.glTranslated(-arg1, -arg2, -arg3);
    super.func_76986_a(arg0, arg1, arg2, arg3, yaw, arg5);
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\client\render\entity\SuperSonicGeoRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */