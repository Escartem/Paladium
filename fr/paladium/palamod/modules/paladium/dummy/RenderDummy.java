package fr.paladium.palamod.modules.paladium.dummy;

import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderDummy extends RenderBiped {
  private static final ResourceLocation texture = new ResourceLocation("palamod", "textures/entity/dummy.png");
  
  public static final ModelDummy model = new ModelDummy(0.0F, 0.0F);
  
  private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
  
  public RenderDummy() {
    super(model, 0.125F);
  }
  
  protected ResourceLocation func_110775_a(EntityLiving p_110775_1_) {
    return texture;
  }
  
  protected void func_82421_b() {
    this.field_82423_g = new ModelDummy(1.0F, 0.0F, 64, 32);
    this.field_82425_h = new ModelDummy(0.5F, 0.0F, 64, 32);
    ((ModelDummy)this.field_82423_g).standPlate.field_78806_j = false;
    ((ModelDummy)this.field_82425_h).standPlate.field_78806_j = false;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\dummy\RenderDummy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */