package fr.paladium.palajobs.client.render.boss;

import fr.paladium.lib.apollon.utils.AnimatedResourceLocation;
import fr.paladium.palajobs.client.model.boss.ModelJobMinerDigBoss;
import fr.paladium.palajobs.core.entity.boss.EntityJobMinerDigBoss;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderJobMinerDigBoss extends GeoEntityRenderer<EntityJobMinerDigBoss> {
  public RenderJobMinerDigBoss(RenderManager renderManager) {
    super(renderManager, (AnimatedGeoModel)new ModelJobMinerDigBoss());
  }
  
  public Color getRenderColor(Entity animatable, float partialTicks) {
    AnimatedResourceLocation.from(func_110775_a(animatable)).bind(animatable.field_70173_aa);
    return super.getRenderColor(animatable, partialTicks);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\client\render\boss\RenderJobMinerDigBoss.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */