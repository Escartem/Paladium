package fr.paladium.palamod.modules.luckyblock.renders.models.easter;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBunnyPlush extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer leg_0;
  
  private final ModelRenderer leg_1;
  
  private final ModelRenderer ears;
  
  private final ModelRenderer bb_main;
  
  public ModelBunnyPlush() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -5.0F, -10.0F, -6.0F, 10, 10, 10, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 30, 0, -4.0F, -8.0F, -7.0F, 3, 4, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 11, 20, -1.0F, -2.0F, -7.0F, 2, 1, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 22, 20, -2.0F, -4.0F, -7.0F, 4, 2, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 20, -2.0F, -4.0F, 4.0F, 4, 4, 3, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 8, 27, 1.0F, -8.0F, -7.0F, 3, 4, 1, 0.0F));
    this.leg_0 = new ModelRenderer(this);
    this.leg_0.func_78793_a(-5.0F, -1.5F, -6.0F);
    this.bone.func_78792_a(this.leg_0);
    setRotationAngle(this.leg_0, 0.0F, 0.7854F, 0.0F);
    this.leg_0.field_78804_l.add(new ModelBox(this.leg_0, 22, 23, -1.0F, -1.5F, -3.0F, 2, 3, 4, 0.0F));
    this.leg_1 = new ModelRenderer(this);
    this.leg_1.func_78793_a(5.0F, -1.5F, -6.0F);
    this.bone.func_78792_a(this.leg_1);
    setRotationAngle(this.leg_1, 0.0F, -0.7854F, 0.0F);
    this.leg_1.field_78804_l.add(new ModelBox(this.leg_1, 14, 20, -1.0F, -1.5F, -3.0F, 2, 3, 4, 0.0F));
    this.ears = new ModelRenderer(this);
    this.ears.func_78793_a(0.0F, -10.0F, -1.0F);
    this.bone.func_78792_a(this.ears);
    setRotationAngle(this.ears, -0.6545F, 0.0F, 0.0F);
    this.ears.field_78804_l.add(new ModelBox(this.ears, 0, 27, 1.0F, -7.0F, -1.0F, 3, 8, 1, 0.0F));
    this.ears.field_78804_l.add(new ModelBox(this.ears, 0, 0, -4.0F, -7.0F, -1.0F, 3, 8, 1, 0.0F));
    this.bb_main = new ModelRenderer(this);
    this.bb_main.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 30, 5, 5.0F, -5.0F, -4.0F, 2, 2, 2, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 16, 28, -7.0F, -5.0F, -4.0F, 2, 2, 2, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.bone.func_78785_a(f5);
    this.bb_main.func_78785_a(f5);
  }
  
  public void renderAll() {
    this.bone.func_78785_a(0.0625F);
    this.bb_main.func_78785_a(0.0625F);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\models\easter\ModelBunnyPlush.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */