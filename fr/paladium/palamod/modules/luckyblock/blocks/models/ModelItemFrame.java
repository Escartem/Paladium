package fr.paladium.palamod.modules.luckyblock.blocks.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelItemFrame extends ModelBase {
  private final ModelRenderer bone;
  
  public ModelItemFrame() {
    this.field_78090_t = 32;
    this.field_78089_u = 32;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 16.0F, 8.55F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -5.0F, -5.0F, -0.05F, 10, 10, 0, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 12, -6.0F, 5.0F, -0.55F, 12, 1, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 10, -6.0F, -6.0F, -0.55F, 12, 1, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 4, 14, 5.0F, -5.0F, -0.55F, 1, 10, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 14, -6.0F, -5.0F, -0.55F, 1, 10, 1, 0.0F));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\models\ModelItemFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */