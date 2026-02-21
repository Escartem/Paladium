package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.models.items;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelWoodenLeg extends ModelBiped {
  private final ModelRenderer bone;
  
  public ModelWoodenLeg() {
    this.field_78090_t = 32;
    this.field_78089_u = 32;
    this.bone = new ModelRenderer((ModelBase)this);
    this.bone.func_78793_a(0.0F, 12.0F, 0.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -2.0F, 0.0F, -2.0F, 4, 5, 4, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 17, 0, -1.0F, 5.0F, -1.0F, 2, 5, 2, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 9, -2.0F, 10.0F, -2.0F, 4, 2, 4, 0.0F));
  }
  
  public void renderAll(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {}
  
  public void func_78088_a(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
    this.field_78124_i = this.bone;
    func_78087_a(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
    float x = 0.12F;
    GL11.glTranslated(x, 0.0D, 0.0D);
    this.bone.func_78785_a(p_78088_7_);
    GL11.glTranslated(-x, 0.0D, 0.0D);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\client\renders\models\items\ModelWoodenLeg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */