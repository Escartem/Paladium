package fr.paladium.palamod.modules.hunter.renders;

import fr.paladium.palamod.modules.hunter.entites.EntityGoat;
import fr.paladium.palamod.modules.hunter.models.ModelGoat;
import fr.paladium.palamod.modules.hunter.models.ModelGoat2;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderGoat extends RenderLiving {
  private static final ResourceLocation goatTextures = new ResourceLocation("palamod", "textures/entity/goat/goat.png");
  
  private static final ResourceLocation shearedGoatTextures = new ResourceLocation("palamod", "textures/entity/goat/goat_mowed.png");
  
  public RenderGoat() {
    super((ModelBase)new ModelGoat(), 1.0F);
    func_77042_a((ModelBase)new ModelGoat2());
  }
  
  protected int shouldRenderPass(EntityGoat goat, int p_77032_2_, float p_77032_3_) {
    if (p_77032_2_ == 0 && !goat.func_70892_o()) {
      func_110776_a(goatTextures);
      if (goat.func_94056_bM() && "jeb_".equals(goat.func_94057_bL())) {
        boolean flag = true;
        int k = goat.field_70173_aa / 25 + goat.func_145782_y();
        int l = k % EntitySheep.field_70898_d.length;
        int i1 = (k + 1) % EntitySheep.field_70898_d.length;
        float f1 = ((goat.field_70173_aa % 25) + p_77032_3_) / 25.0F;
        GL11.glColor3f(EntitySheep.field_70898_d[l][0] * (1.0F - f1) + EntitySheep.field_70898_d[i1][0] * f1, EntitySheep.field_70898_d[l][1] * (1.0F - f1) + EntitySheep.field_70898_d[i1][1] * f1, EntitySheep.field_70898_d[l][2] * (1.0F - f1) + EntitySheep.field_70898_d[i1][2] * f1);
      } else {
        int j = goat.func_70896_n();
        GL11.glColor3f(EntitySheep.field_70898_d[j][0], EntitySheep.field_70898_d[j][1], EntitySheep.field_70898_d[j][2]);
      } 
      return 1;
    } 
    return -1;
  }
  
  protected int func_77032_a(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_) {
    return shouldRenderPass((EntityGoat)p_77032_1_, p_77032_2_, p_77032_3_);
  }
  
  protected ResourceLocation func_110775_a(Entity entity) {
    return shearedGoatTextures;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\renders\RenderGoat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */