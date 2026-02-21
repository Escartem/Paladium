package fr.paladium.palamod.modules.luckyblock.renders.models.may;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRunicAxe extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer cube_r1;
  
  public ModelRunicAxe() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -1.0F, -25.0F, -1.0F, 2, 23, 2, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 8, 0, 1.0F, -23.0F, -1.0F, 7, 8, 2, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 18, 20, 5.0F, -15.0F, -1.0F, 3, 4, 2, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 25, 2.0F, -12.0F, -1.0F, 3, 2, 2, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 8, 22, -7.0F, -22.0F, -1.0F, 3, 2, 2, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 20, 10, -4.0F, -22.0F, -1.0F, 3, 6, 2, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 29, -1.5F, -11.0F, -1.5F, 3, 5, 3, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 9, 28, -1.5F, -24.4F, -1.5F, 3, 1, 3, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(0.0F, -1.0F, 0.0F);
    this.bone.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 0.0F, 0.0F, 0.7854F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 6, 0, -2.0F, -2.0F, 0.8F, 1, 1, 1, 0.0F));
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 17, 10, -2.0F, -2.0F, -1.8F, 1, 1, 1, 0.0F));
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 8, 10, -15.0F, -15.0F, -1.5F, 3, 3, 3, 0.0F));
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 8, 16, -3.0F, -3.0F, -1.5F, 3, 3, 3, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.bone.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\models\may\ModelRunicAxe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */