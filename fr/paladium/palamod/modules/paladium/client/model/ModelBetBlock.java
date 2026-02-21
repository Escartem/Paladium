package fr.paladium.palamod.modules.paladium.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBetBlock extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer bb_main;
  
  public ModelBetBlock() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(4.0F, 23.0F, 0.0F);
    setRotationAngle(this.bone, 0.0F, 0.0F, -0.5236F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 32, -7.0F, -12.0F, -10.0F, 14, 2, 2, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 28, 36, -6.0F, -10.0F, -10.0F, 12, 1, 2, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 36, -6.0F, -7.0F, -10.0F, 12, 1, 2, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 39, -5.0F, -9.0F, -10.0F, 10, 2, 2, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 32, 32, -7.0F, -6.0F, -10.0F, 14, 2, 2, 0.0F));
    this.bb_main = new ModelRenderer(this);
    this.bb_main.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -8.0F, -16.0F, -8.0F, 16, 16, 16, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.bone.func_78785_a(f5);
    this.bb_main.func_78785_a(f5);
  }
  
  public void renderAll() {
    this.bb_main.func_78785_a(0.0625F);
    this.bone.func_78785_a(0.0625F);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\model\ModelBetBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */