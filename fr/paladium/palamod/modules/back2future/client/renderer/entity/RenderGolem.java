package fr.paladium.palamod.modules.back2future.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.entities.EntityGolem;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderGolem extends RenderLiving {
  public RenderGolem(ModelBase model, float shadowSize) {
    super(model, shadowSize);
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    return getGolemTexture((EntityGolem)entity);
  }
  
  protected ResourceLocation getGolemTexture(EntityGolem entity) {
    return entity.textureLoc;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\renderer\entity\RenderGolem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */