package fr.paladium.palamod.modules.back2future.client.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.back2future.client.model.ModelEndermite;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class EndermiteRenderer extends RenderLiving {
  private static final ResourceLocation texture = new ResourceLocation("textures/entity/endermite.png");
  
  public EndermiteRenderer() {
    super((ModelBase)new ModelEndermite(), 0.3F);
  }
  
  protected float func_77037_a(EntityLivingBase entity) {
    return 180.0F;
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    return texture;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\renderer\entity\EndermiteRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */