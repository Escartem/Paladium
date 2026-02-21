package fr.paladium.palamod.modules.luckyblock.blocks.models.may;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMayTrophy extends ModelBase {
  private final ModelRenderer bone;
  
  public ModelMayTrophy() {
    this.field_78090_t = 32;
    this.field_78089_u = 32;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 22.0F, 0.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 27, -4.0F, 1.0F, -2.0F, 8, 1, 4, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 17, -3.0F, -1.0F, -1.1F, 6, 2, 0, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 20, 28, -2.0F, 0.0F, -1.0F, 4, 1, 2, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 12, 6, -1.0F, -1.0F, -1.0F, 2, 1, 2, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 8, 0, -0.5F, -20.0F, -0.5F, 1, 14, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 12, 9, -0.5F, -3.0F, -0.5F, 1, 2, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 12, 0, -1.0F, -6.0F, -1.0F, 2, 4, 2, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -1.0F, -21.0F, -1.0F, 2, 15, 2, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.bone.func_78785_a(f5);
  }
  
  public void renderAll() {
    this.bone.func_78785_a(0.0625F);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\models\may\ModelMayTrophy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */