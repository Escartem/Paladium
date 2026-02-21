package fr.paladium.palamod.modules.back2future.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class StrayOverlayRenderer extends StrayRenderer {
  public ResourceLocation texture = new ResourceLocation("textures/entity/skeleton/stray_overlay.png");
  
  public ModelBiped tutModel;
  
  public StrayOverlayRenderer() {
    this.field_77045_g = (ModelBase)new ModelSkeleton(0.0F);
    this.field_77071_a = (ModelBiped)this.field_77045_g;
    this.field_82437_k = new ModelBiped(1.0F);
    this.field_82435_l = new ModelBiped(0.5F);
    this.tutModel = new ModelBiped(0.5F);
  }
  
  public void func_77036_a(EntityLivingBase entity, float par2, float par3, float par4, float par5, float par6, float par7) {
    super.func_77036_a(entity, par2, par3, par4, par5, par6, par7);
    for (int i = 0; i < 4; i++) {
      ModelBiped modelBiped = this.tutModel;
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      func_110776_a(this.texture);
      modelBiped.field_78116_c.field_78806_j = (i == 0);
      modelBiped.field_78114_d.field_78806_j = (i == 0);
      modelBiped.field_78115_e.field_78806_j = (i == 1 || i == 2);
      modelBiped.field_78112_f.field_78806_j = (i == 1);
      modelBiped.field_78113_g.field_78806_j = (i == 1);
      modelBiped.field_78123_h.field_78806_j = (i == 2 || i == 3);
      modelBiped.field_78124_i.field_78806_j = (i == 2 || i == 3);
      modelBiped.field_78095_p = this.field_77045_g.field_78095_p;
      modelBiped.field_78093_q = this.field_77045_g.field_78093_q;
      modelBiped.field_78091_s = this.field_77045_g.field_78091_s;
      if (this.field_77045_g instanceof ModelBiped) {
        modelBiped.field_78119_l = ((ModelBiped)this.field_77045_g).field_78119_l;
        modelBiped.field_78120_m = ((ModelBiped)this.field_77045_g).field_78120_m;
        modelBiped.field_78117_n = ((ModelBiped)this.field_77045_g).field_78117_n;
        modelBiped.field_78118_o = ((ModelBiped)this.field_77045_g).field_78118_o;
      } 
      modelBiped.func_78086_a(entity, par2, par3, 0.0F);
      modelBiped.func_78088_a((Entity)entity, par2, par3, par4, par5, par6, par7);
      GL11.glDisable(2896);
      func_110776_a(this.texture);
      GL11.glEnable(3008);
      GL11.glEnable(3042);
      GL11.glAlphaFunc(516, 0.0F);
      GL11.glBlendFunc(770, 771);
      modelBiped.func_78088_a((Entity)entity, par2, par3, par4, par5, par6, par7);
      GL11.glDisable(3042);
      GL11.glEnable(3008);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable(2896);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\renderer\entity\StrayOverlayRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */