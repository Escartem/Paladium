package fr.paladium.pet.client.renderer;

import fr.paladium.pet.client.models.entities.PetModel;
import fr.paladium.pet.common.entity.EntityDummyPet;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class PetGeoRenderer extends GeoEntityRenderer<EntityDummyPet> {
  public PetGeoRenderer() {
    super(RenderManager.field_78727_a, (AnimatedGeoModel)new PetModel());
  }
  
  public void render(GeoModel geo, Entity entity, float ticks, float red, float green, float blue, float alpha) {
    super.render(geo, entity, ticks, red, green, blue, alpha);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\client\renderer\PetGeoRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */