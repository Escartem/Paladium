package fr.paladium.palajobs.client.render.boss;

import fr.paladium.palajobs.client.model.boss.ModelJobFarmerPlantBoss;
import fr.paladium.palajobs.core.entity.boss.EntityJobFarmerPlantBoss;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderJobFarmerPlantBoss extends GeoEntityRenderer<EntityJobFarmerPlantBoss> {
  public RenderJobFarmerPlantBoss(RenderManager renderManager) {
    super(renderManager, (AnimatedGeoModel)new ModelJobFarmerPlantBoss());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\client\render\boss\RenderJobFarmerPlantBoss.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */