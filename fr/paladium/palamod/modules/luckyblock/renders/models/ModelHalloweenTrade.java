package fr.paladium.palamod.modules.luckyblock.renders.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHalloweenTrade extends ModelBase {
  private final ModelRenderer bone;
  
  public ModelHalloweenTrade() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(8.0F, 24.0F, -8.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -16.0F, -16.0F, 0.0F, 16, 14, 16, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 37, -5.0F, -2.0F, 0.0F, 5, 2, 5, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 35, 32, -16.0F, -2.0F, 0.0F, 5, 2, 5, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 20, 30, -5.0F, -2.0F, 11.0F, 5, 2, 5, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 30, -16.0F, -2.0F, 11.0F, 5, 2, 5, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.bone.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
  
  public void renderAll() {
    this.bone.func_78785_a(0.0625F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\models\ModelHalloweenTrade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */