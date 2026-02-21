package fr.paladium.palamod.modules.luckyblock.renders.models.june;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSwordGuitarOfTheApocalypse extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer cube_r1;
  
  public ModelSwordGuitarOfTheApocalypse() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, 1.0F, -12.0F, -6.0F, 5, 12, 12, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 6, 24, -1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 22, 0, -1.0F, -10.0F, -5.0F, 2, 8, 2, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 40, 33, -1.0F, -33.7F, -0.3F, 2, 13, 2, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 30, 19, -1.0F, -42.7F, -3.3F, 2, 9, 5, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 30, 28, 0.0F, -53.7F, -3.3F, 0, 11, 5, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 16, 30, 0.0F, -43.7F, 1.7F, 0, 11, 5, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 6, 30, 0.0F, -43.7F, -8.3F, 0, 11, 5, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 39, 12, -1.0F, -12.0F, 3.0F, 2, 10, 2, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 22, 0, -1.0F, -2.0F, -5.0F, 2, 2, 10, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 6, 24, -2.0F, -6.0F, -5.0F, 1, 1, 10, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 6, 0, -2.0F, -36.0F, -0.25F, 1, 1, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 6, 0, -2.0F, -38.0F, -1.25F, 1, 1, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 6, 0, -2.0F, -40.0F, -2.25F, 1, 1, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 6, 0, -2.0F, -42.0F, -3.25F, 1, 1, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 24, -2.0F, -35.0F, -0.25F, 1, 29, 0, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 24, -2.0F, -37.0F, -1.25F, 1, 31, 0, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 24, -2.0F, -39.0F, -2.25F, 1, 33, 0, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 24, -2.0F, -41.0F, -3.25F, 1, 35, 0, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(0.0F, -12.0F, 5.0F);
    this.bone.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 0.3491F, 0.0F, 0.0F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 0, 0, -1.0F, -10.0F, -2.0F, 2, 10, 2, 0.0F));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\models\june\ModelSwordGuitarOfTheApocalypse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */