package fr.paladium.palamod.modules.luckyblock.monthly.modules.may.client.model.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCreeperPlush extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer leg_0;
  
  private final ModelRenderer leg_3;
  
  private final ModelRenderer leg_1;
  
  private final ModelRenderer leg_2;
  
  public ModelCreeperPlush() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -5.0F, -10.0F, -6.0F, 10, 10, 10, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -4.0F, -8.0F, -7.0F, 3, 3, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, 1.0F, -8.0F, -7.0F, 3, 3, 1, 0.0F));
    this.leg_0 = new ModelRenderer(this);
    this.leg_0.func_78793_a(-5.0F, -1.5F, -6.0F);
    this.bone.func_78792_a(this.leg_0);
    setRotationAngle(this.leg_0, 0.0F, 0.7854F, 0.0F);
    this.leg_0.field_78804_l.add(new ModelBox(this.leg_0, 0, 20, -1.0F, -1.5F, -3.0F, 2, 3, 4, 0.0F));
    this.leg_3 = new ModelRenderer(this);
    this.leg_3.func_78793_a(-6.5F, -1.5F, 5.5F);
    this.bone.func_78792_a(this.leg_3);
    setRotationAngle(this.leg_3, 0.0F, 2.3562F, 0.0F);
    this.leg_3.field_78804_l.add(new ModelBox(this.leg_3, 0, 20, -1.0F, -1.5F, -1.0F, 2, 3, 4, 0.0F));
    this.leg_1 = new ModelRenderer(this);
    this.leg_1.func_78793_a(5.0F, -1.5F, -6.0F);
    this.bone.func_78792_a(this.leg_1);
    setRotationAngle(this.leg_1, 0.0F, -0.7854F, 0.0F);
    this.leg_1.field_78804_l.add(new ModelBox(this.leg_1, 0, 20, -1.0F, -1.5F, -3.0F, 2, 3, 4, 0.0F));
    this.leg_2 = new ModelRenderer(this);
    this.leg_2.func_78793_a(6.5F, -1.5F, 5.5F);
    this.bone.func_78792_a(this.leg_2);
    setRotationAngle(this.leg_2, 0.0F, -2.3562F, 0.0F);
    this.leg_2.field_78804_l.add(new ModelBox(this.leg_2, 0, 20, -1.0F, -1.5F, -1.0F, 2, 3, 4, 0.0F));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\may\client\model\block\ModelCreeperPlush.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */