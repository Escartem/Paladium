package fr.paladium.palamod.modules.luckyblock.renders.models.may;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelEnergeticBowSword extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer bone2;
  
  public ModelEnergeticBowSword() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 28.0F, 0.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 6, 5, 0.0F, -24.0F, 0.0F, 2, 8, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 12, 5, -2.0F, -26.0F, 0.0F, 2, 3, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 14, -4.0F, -27.0F, 0.0F, 2, 2, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 16, 17, 2.0F, -17.0F, 0.0F, 2, 1, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 17, 8, -2.0F, -18.0F, 0.0F, 2, 1, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 17, -6.0F, -26.0F, 0.0F, 2, 1, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 11, 16, -4.0F, -24.0F, 0.0F, 2, 1, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 11, 18, -1.0F, -23.0F, 0.0F, 1, 2, 1, 0.0F));
    this.bone2 = new ModelRenderer(this);
    this.bone2.func_78793_a(0.0F, 21.0F, 0.0F);
    this.bone2.field_78804_l.add(new ModelBox(this.bone2, 0, 5, 0.0F, -8.0F, 0.0F, 2, 8, 1, 0.0F));
    this.bone2.field_78804_l.add(new ModelBox(this.bone2, 12, 9, -2.0F, -1.0F, 0.0F, 2, 3, 1, 0.0F));
    this.bone2.field_78804_l.add(new ModelBox(this.bone2, 6, 17, -2.0F, -7.0F, 0.0F, 2, 1, 1, 0.0F));
    this.bone2.field_78804_l.add(new ModelBox(this.bone2, 18, 5, -1.0F, -3.0F, 0.0F, 1, 2, 1, 0.0F));
    this.bone2.field_78804_l.add(new ModelBox(this.bone2, 6, 15, -4.0F, -1.0F, 0.0F, 2, 1, 1, 0.0F));
    this.bone2.field_78804_l.add(new ModelBox(this.bone2, 16, 15, -6.0F, 1.0F, 0.0F, 2, 1, 1, 0.0F));
    this.bone2.field_78804_l.add(new ModelBox(this.bone2, 17, 12, 2.0F, -8.0F, 0.0F, 2, 1, 1, 0.0F));
    this.bone2.field_78804_l.add(new ModelBox(this.bone2, 11, 13, -4.0F, 1.0F, 0.0F, 2, 2, 1, 0.0F));
    this.bone2.field_78804_l.add(new ModelBox(this.bone2, 0, 21, -10.0F, -12.0F, 0.5F, 20, 7, 0, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.bone.func_78785_a(f5);
    this.bone2.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\models\may\ModelEnergeticBowSword.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */