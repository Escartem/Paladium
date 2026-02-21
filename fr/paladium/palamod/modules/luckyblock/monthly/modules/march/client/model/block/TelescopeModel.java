package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.model.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class TelescopeModel extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer telescope;
  
  private final ModelRenderer support;
  
  private final ModelRenderer feet_0;
  
  private final ModelRenderer feet_1;
  
  private final ModelRenderer feet_2;
  
  public TelescopeModel() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 24.0F, 0.0F);
    this.telescope = new ModelRenderer(this);
    this.telescope.func_78793_a(0.0F, -15.0F, 2.0F);
    this.bone.func_78792_a(this.telescope);
    setRotationAngle(this.telescope, -0.3491F, 0.0F, 0.0F);
    this.telescope.field_78804_l.add(new ModelBox(this.telescope, 0, 0, -1.5F, -2.5F, -5.0F, 3, 3, 10, 0.0F));
    this.telescope.field_78804_l.add(new ModelBox(this.telescope, 0, 13, -2.0F, -3.0F, -13.0F, 4, 4, 8, 0.0F));
    this.telescope.field_78804_l.add(new ModelBox(this.telescope, 16, 13, -2.0F, -3.0F, 5.0F, 4, 4, 3, 0.0F));
    this.support = new ModelRenderer(this);
    this.support.func_78793_a(0.0F, 0.0F, 0.0F);
    this.bone.func_78792_a(this.support);
    this.support.field_78804_l.add(new ModelBox(this.support, 0, 0, -1.0F, -16.0F, -1.0F, 2, 4, 2, 0.0F));
    this.support.field_78804_l.add(new ModelBox(this.support, 16, 0, -3.0F, -12.0F, -3.0F, 6, 1, 6, 0.0F));
    this.feet_0 = new ModelRenderer(this);
    this.feet_0.func_78793_a(-2.5F, -11.0F, -2.5F);
    this.support.func_78792_a(this.feet_0);
    setRotationAngle(this.feet_0, -0.3491F, 0.0F, 0.3491F);
    this.feet_0.field_78804_l.add(new ModelBox(this.feet_0, 24, 20, -0.5F, 0.0F, -0.5F, 1, 13, 1, 0.0F));
    this.feet_1 = new ModelRenderer(this);
    this.feet_1.func_78793_a(2.5F, -11.0F, -2.5F);
    this.support.func_78792_a(this.feet_1);
    setRotationAngle(this.feet_1, -0.3491F, 0.0F, -0.3491F);
    this.feet_1.field_78804_l.add(new ModelBox(this.feet_1, 24, 20, -0.5F, 0.0F, -0.5F, 1, 13, 1, 0.0F));
    this.feet_2 = new ModelRenderer(this);
    this.feet_2.func_78793_a(0.0F, -11.0F, 2.5F);
    this.support.func_78792_a(this.feet_2);
    setRotationAngle(this.feet_2, 0.5236F, 0.0F, 0.0F);
    this.feet_2.field_78804_l.add(new ModelBox(this.feet_2, 24, 20, -0.5F, 0.0F, -0.5F, 1, 13, 1, 0.0F));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\client\model\block\TelescopeModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */