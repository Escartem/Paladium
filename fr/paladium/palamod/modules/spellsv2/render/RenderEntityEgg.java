package fr.paladium.palamod.modules.spellsv2.render;

import fr.paladium.palamod.modules.spellsv2.entity.EntityEgg;
import fr.paladium.palamod.modules.spellsv2.models.ModelEgg;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderEntityEgg extends RenderLiving {
  public final ResourceLocation[] texture = new ResourceLocation[] { new ResourceLocation("palamod", "textures/entity/egg/egg_0.png"), new ResourceLocation("palamod", "textures/entity/egg/egg_1.png"), new ResourceLocation("palamod", "textures/entity/egg/egg_2.png"), new ResourceLocation("palamod", "textures/entity/egg/egg_3.png") };
  
  public RenderEntityEgg() {
    super((ModelBase)new ModelEgg(), 1.0F);
  }
  
  public void func_76986_a(Entity entity, double x, double y, double z, float f1, float f2) {
    super.func_76986_a(entity, x, y, z, f1, f2);
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    if (entity instanceof EntityEgg) {
      EntityEgg egg = (EntityEgg)entity;
      if (egg.func_110143_aJ() > 150.0F)
        return this.texture[0]; 
      if (egg.func_110143_aJ() > 100.0F)
        return this.texture[1]; 
      if (egg.func_110143_aJ() > 50.0F)
        return this.texture[2]; 
      return this.texture[3];
    } 
    return this.texture[0];
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\render\RenderEntityEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */