package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDecryptedComputer extends ModelBase {
  private final ModelRenderer group2;
  
  private final ModelRenderer group3;
  
  private final ModelRenderer cube_r1;
  
  private final ModelRenderer group;
  
  private final ModelRenderer cube_r2;
  
  public ModelDecryptedComputer() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.group2 = new ModelRenderer(this);
    this.group2.func_78793_a(0.0F, 11.5615F, -2.109F);
    this.group3 = new ModelRenderer(this);
    this.group3.func_78793_a(0.0F, 0.0F, 0.0F);
    this.group2.func_78792_a(this.group3);
    this.group3.field_78804_l.add(new ModelBox(this.group3, 0, 34, -7.0F, -3.6615F, 1.609F, 14, 11, 2, 0.0F));
    this.group3.field_78804_l.add(new ModelBox(this.group3, 6, 0, -7.0F, -3.6615F, 0.609F, 2, 11, 1, 0.0F));
    this.group3.field_78804_l.add(new ModelBox(this.group3, 0, 0, 5.0F, -3.6615F, 0.609F, 2, 11, 1, 0.0F));
    this.group3.field_78804_l.add(new ModelBox(this.group3, 32, 41, -5.0F, 5.3385F, 0.609F, 10, 2, 1, 0.0F));
    this.group3.field_78804_l.add(new ModelBox(this.group3, 32, 38, -5.0F, -3.6615F, 0.609F, 10, 2, 1, 0.0F));
    this.group3.field_78804_l.add(new ModelBox(this.group3, 0, 17, -4.95F, -0.6F, 3.5F, 9, 8, 7, 0.0F));
    this.group3.field_78804_l.add(new ModelBox(this.group3, 32, 34, -4.95F, -2.1F, 3.5F, 9, 1, 3, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(0.0F, -3.6615F, 3.609F);
    this.group3.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, -0.3927F, 0.0F, 0.0F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 24, 24, -5.0F, 0.0F, 0.0F, 10, 2, 8, 0.0F));
    this.group = new ModelRenderer(this);
    this.group.func_78793_a(8.0F, 13.4385F, -5.891F);
    this.group3.func_78792_a(this.group);
    this.group.field_78804_l.add(new ModelBox(this.group, 0, 0, -16.0F, -6.1F, 5.5F, 16, 5, 12, 0.0F));
    this.group.field_78804_l.add(new ModelBox(this.group, 25, 17, -16.0F, -1.6F, -1.0F, 11, 1, 6, 0.0F));
    this.cube_r2 = new ModelRenderer(this);
    this.cube_r2.func_78793_a(-1.0F, -1.6F, 2.0F);
    this.group.func_78792_a(this.cube_r2);
    setRotationAngle(this.cube_r2, 0.0F, -0.3927F, 0.0F);
    this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 44, 0, -1.5F, -1.0F, -2.0F, 3, 2, 4, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.group2.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
  
  public void renderAll() {
    this.group2.func_78785_a(0.0625F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\client\renders\models\blocks\ModelDecryptedComputer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */