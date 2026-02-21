package fr.paladium.palamod.modules.luckyblock.monthly.modules.december.client.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelWreath extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer bb_main;
  
  public ModelWreath() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 36, 0, -4.0F, -19.0F, 2.0F, 3, 3, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 34, 28, -1.0F, -18.0F, 3.0F, 2, 1, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 36, 4, 1.0F, -19.0F, 2.0F, 3, 3, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 31, -7.0F, -17.0F, 2.6F, 14, 7, 0, 0.0F));
    this.bb_main = new ModelRenderer(this);
    this.bb_main.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 34, 16, -7.0F, -15.0F, -1.0F, 3, 9, 3, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 25, -7.0F, -18.0F, -1.0F, 14, 3, 3, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 28, 31, 4.0F, -15.0F, -1.0F, 3, 9, 3, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 19, -7.0F, -6.0F, -1.0F, 14, 3, 3, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -9.0F, -20.0F, 2.1F, 18, 19, 0, 0.0F));
  }
  
  public void renderAll() {
    this.bone.func_78785_a(0.0625F);
    this.bb_main.func_78785_a(0.0625F);
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\december\client\models\blocks\ModelWreath.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */