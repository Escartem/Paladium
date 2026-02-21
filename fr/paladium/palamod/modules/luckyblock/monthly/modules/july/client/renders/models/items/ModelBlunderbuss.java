package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.models.items;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBlunderbuss extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer bb_main;
  
  private final ModelRenderer cube_r1;
  
  public ModelBlunderbuss() {
    this.field_78090_t = 32;
    this.field_78089_u = 32;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 19.0F, -3.0F);
    setRotationAngle(this.bone, -0.4363F, 0.0F, 0.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, 0.0F, -2.0F, -2.0F, 1, 2, 2, 0.0F));
    this.bb_main = new ModelRenderer(this);
    this.bb_main.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -1.0F, -5.0F, 0.0F, 2, 5, 8, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 12, 5, -1.0F, -5.0F, -11.0F, 2, 3, 8, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 21, -1.0F, -5.0F, -16.0F, 2, 2, 5, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 14, 16, -2.0F, -6.0F, -19.0F, 4, 4, 3, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 12, 0, -1.0F, -2.0F, -6.0F, 2, 2, 3, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(-0.5F, 0.0F, 0.0F);
    this.bb_main.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, -0.48F, 0.0F, 0.0F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 0, 13, -0.5F, -3.0F, -5.0F, 2, 3, 5, -1.0E-4F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.bone.func_78785_a(f5);
    this.bb_main.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\client\renders\models\items\ModelBlunderbuss.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */