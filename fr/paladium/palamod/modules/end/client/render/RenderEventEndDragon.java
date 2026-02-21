package fr.paladium.palamod.modules.end.client.render;

import fr.paladium.palamod.modules.end.client.model.ModelEventEndDragon;
import fr.paladium.palamod.modules.end.common.entity.EntityEventEndDragon;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.IBossDisplayData;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderEventEndDragon extends GeoEntityRenderer<EntityEventEndDragon> {
  public RenderEventEndDragon() {
    super(RenderManager.field_78727_a, (AnimatedGeoModel)new ModelEventEndDragon());
  }
  
  public void func_76986_a(Entity entity, double posX, double posY, double posZ, float f1, float f2) {
    GL11.glPushMatrix();
    GL11.glTranslated(posX, posY, posZ);
    GL11.glRotated(180.0D, 0.0D, 1.0D, 0.0D);
    GL11.glTranslated(-posX, -posY, -posZ);
    super.func_76986_a(entity, posX, posY, posZ, f1, f2);
    GL11.glPopMatrix();
    BossStatus.func_82824_a((IBossDisplayData)entity, false);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\end\client\render\RenderEventEndDragon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */