package fr.paladium.palamod.modules.pillage.client.render;

import fr.paladium.palamod.modules.back2future.ModBlocks;
import fr.paladium.palamod.modules.pillage.client.model.ModelEmpty;
import fr.paladium.palamod.modules.pillage.common.entities.EntitySpongeSheep;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSheep2;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSheep;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.opengl.GL11;

public class RenderSpongeSheep extends RenderSheep {
  private static final RenderBlocks renderBlocks = new RenderBlocks();
  
  public RenderSpongeSheep() {
    super((ModelBase)new ModelSheep2(), (ModelBase)new ModelEmpty(), 0.7F);
  }
  
  protected int shouldRenderPass(EntitySpongeSheep entitySheep, int p_77032_2_, float partialTicks) {
    if (p_77032_2_ == 0 && !entitySheep.func_70892_o()) {
      GL11.glPushMatrix();
      GL11.glTranslatef(0.0F, entitySheep.func_70047_e() - 0.5F, 0.03F);
      GL11.glScaled(1.0D + entitySheep.getLevel() / 10.0D, 0.7D + entitySheep.getLevel() / 10.0D, 1.0D + entitySheep.getLevel() / 10.0D);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      RenderManager.field_78727_a.field_78724_e.func_110577_a(TextureMap.field_110575_b);
      renderBlocks.func_147800_a(ModBlocks.sponge, (entitySheep.getLevel() < 3) ? 0 : 1, 1.0F);
      GL11.glPopMatrix();
      return 1;
    } 
    return -1;
  }
  
  protected int func_77032_a(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_) {
    return shouldRenderPass((EntitySpongeSheep)p_77032_1_, p_77032_2_, p_77032_3_);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\client\render\RenderSpongeSheep.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */