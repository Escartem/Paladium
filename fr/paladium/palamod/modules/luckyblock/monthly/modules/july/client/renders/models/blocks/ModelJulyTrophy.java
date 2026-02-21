package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelJulyTrophy extends ModelBase {
  private final ModelRenderer bone3;
  
  private final ModelRenderer bone2;
  
  private final ModelRenderer bone;
  
  private final ModelRenderer bb_main;
  
  public ModelJulyTrophy() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bone3 = new ModelRenderer(this);
    this.bone3.func_78793_a(1.0F, -4.0F, 9.4F);
    this.bone3.field_78804_l.add(new ModelBox(this.bone3, 0, 24, -5.0F, 26.0F, -19.0F, 9, 2, 19, 0.0F));
    this.bone3.field_78804_l.add(new ModelBox(this.bone3, 16, 45, -4.0F, 24.0F, -18.0F, 7, 2, 17, 0.0F));
    this.bone2 = new ModelRenderer(this);
    this.bone2.func_78793_a(0.0F, 0.0F, 2.0F);
    this.bone3.func_78792_a(this.bone2);
    setRotationAngle(this.bone2, 0.6109F, 0.0F, -3.1416F);
    this.bone2.field_78804_l.add(new ModelBox(this.bone2, 12, 15, -2.0F, -1.8848F, -2.1312F, 4, 1, 4, 0.0F));
    this.bone2.field_78804_l.add(new ModelBox(this.bone2, 0, 19, -1.0F, -15.8848F, -0.1312F, 2, 7, 2, 0.0F));
    this.bone2.field_78804_l.add(new ModelBox(this.bone2, 16, 20, -2.0F, -10.8848F, 1.8688F, 2, 2, 2, 0.0F));
    this.bone2.field_78804_l.add(new ModelBox(this.bone2, 28, -3, 0.0F, -32.8848F, -3.1312F, 0, 24, 3, 0.0F));
    this.bone2.field_78804_l.add(new ModelBox(this.bone2, 0, 14, -2.0F, -8.8848F, -2.1312F, 4, 1, 4, 0.0F));
    this.bone2.field_78804_l.add(new ModelBox(this.bone2, 0, 0, -3.0F, -8.8848F, -4.1312F, 6, 7, 7, 0.0F));
    this.bone2.field_78804_l.add(new ModelBox(this.bone2, 8, 20, -1.0F, -7.8848F, -1.1312F, 2, 6, 2, 0.0F));
    this.bone2.field_78804_l.add(new ModelBox(this.bone2, 19, 0, -1.0F, -7.8848F, -3.1312F, 2, 3, 2, 0.0F));
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(-1.0F, 0.6F, -19.4F);
    this.bone3.func_78792_a(this.bone);
    setRotationAngle(this.bone, -2.5307F, 0.0F, 0.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 12, 15, -2.0F, -0.4259F, -1.2135F, 4, 1, 4, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 19, -1.0F, -14.4259F, 0.7865F, 2, 7, 2, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 16, 20, -2.0F, -9.4259F, 2.7865F, 2, 2, 2, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 28, -3, 0.0F, -31.4259F, -2.2135F, 0, 24, 3, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 14, -2.0F, -7.4259F, -1.2135F, 4, 1, 4, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -3.0F, -7.4259F, -3.2135F, 6, 7, 7, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 8, 20, -1.0F, -6.4259F, -0.2135F, 2, 6, 2, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 19, 0, -1.0F, -6.4259F, -2.2135F, 2, 3, 2, 0.0F));
    this.bb_main = new ModelRenderer(this);
    this.bb_main.func_78792_a(this.bone3);
    this.bb_main.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 37, 5.3F, -4.0F, -4.0F, 0, 4, 8, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.bb_main.func_78785_a(f5);
  }
  
  public void renderAll() {
    this.bb_main.func_78785_a(0.0625F);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\client\renders\models\blocks\ModelJulyTrophy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */