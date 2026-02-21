package fr.paladium.palamod.modules.back2future.client.renderer.entity;

import fr.paladium.palamod.modules.back2future.client.OpenGLHelper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelEnderCrystal;
import net.minecraft.client.renderer.tileentity.RenderEnderCrystal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class PlacedEndCrystalRenderer extends RenderEnderCrystal {
  private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/endercrystal/endercrystal.png");
  
  private static final ModelBase MODEL = (ModelBase)new ModelEnderCrystal(0.0F, false);
  
  public void func_76986_a(EntityEnderCrystal crystal, double x, double y, double z, float p_76986_8_, float partialTickTime) {
    float rotation = crystal.field_70261_a + partialTickTime;
    OpenGLHelper.pushMatrix();
    OpenGLHelper.translate(x, y, z);
    func_110776_a(TEXTURE);
    float f3 = MathHelper.func_76126_a(rotation * 0.2F) / 2.0F + 0.5F;
    f3 += f3 * f3;
    MODEL.func_78088_a((Entity)crystal, 0.0F, rotation * 3.0F, f3 * 0.2F, 0.0F, 0.0F, 0.0625F);
    OpenGLHelper.popMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\renderer\entity\PlacedEndCrystalRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */