package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.models.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPaperAirplane extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer cube_r1;
  
  private final ModelRenderer bone2;
  
  private final ModelRenderer cube_r2;
  
  public ModelPaperAirplane() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 14, 0, -8.0F, -2.8F, -8.0F, 7, 0, 16, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(0.0F, 0.0F, 0.0F);
    this.bone.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 0.0F, 0.0F, -0.3491F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 0, 3, 0.0F, -3.0F, -8.0F, 0, 3, 16, 0.0F));
    this.bone2 = new ModelRenderer(this);
    this.bone2.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bone2.field_78804_l.add(new ModelBox(this.bone2, 0, 0, 1.0F, -2.8F, -8.0F, 7, 0, 16, 0.0F));
    this.cube_r2 = new ModelRenderer(this);
    this.cube_r2.func_78793_a(0.0F, 0.0F, 0.0F);
    this.bone2.func_78792_a(this.cube_r2);
    setRotationAngle(this.cube_r2, 0.0F, 0.0F, 0.3491F);
    this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 0, 0, 0.0F, -3.0F, -8.0F, 0, 3, 16, 0.0F));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\client\models\entities\ModelPaperAirplane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */