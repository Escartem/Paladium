package fr.paladium.palamod.modules.hunter.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public class ModelBamboo extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer leaves;
  
  private final ModelRenderer leaves2;
  
  private final ModelRenderer base;
  
  public ModelBamboo() {
    this.field_78090_t = 48;
    this.field_78089_u = 32;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 24.0F, 0.0F);
    this.leaves = new ModelRenderer(this);
    this.leaves2 = new ModelRenderer(this);
    this.leaves2.func_78793_a(0.0F, -8.0F, 0.0F);
    this.leaves.func_78793_a(0.0F, -8.0F, 0.0F);
    this.bone.func_78792_a(this.leaves);
    this.bone.func_78792_a(this.leaves2);
    setRotationAngle(this.leaves, 0.0F, 0.7418F, 0.0F);
    this.leaves.field_78804_l.add(new ModelBox(this.leaves, 0, 16, -8.0F, -8.0F, 0.0F, 16, 16, 0, 0.0F));
    this.leaves.field_78804_l.add(new ModelBox(this.leaves, 0, 0, 0.0F, -8.0F, -8.0F, 0, 16, 16, 0.0F));
    setRotationAngle(this.leaves2, 0.0F, 3.7418F, 0.0F);
    this.leaves2.field_78804_l.add(new ModelBox(this.leaves, 0, 16, -8.0F, -8.0F, 0.0F, 16, 16, 0, 0.0F));
    this.leaves2.field_78804_l.add(new ModelBox(this.leaves, 0, 0, 0.0F, -8.0F, -8.0F, 0, 16, 16, 0.0F));
    this.base = new ModelRenderer(this);
    this.base.func_78793_a(0.0F, -1.5F, 0.0F);
    this.bone.func_78792_a(this.base);
    setRotationAngle(this.base, 0.0F, 0.0F, -1.5708F);
    this.base.field_78804_l.add(new ModelBox(this.base, 0, 0, -1.5F, -1.5F, -1.5F, 16, 3, 3, 0.0F));
    this.base.field_78804_l.add(new ModelBox(this.base, 0, 6, -1.5F, -1.0F, -1.0F, 16, 2, 2, 0.0F));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\blocks\ModelBamboo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */