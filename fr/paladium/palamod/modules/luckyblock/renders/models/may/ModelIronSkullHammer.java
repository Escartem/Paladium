package fr.paladium.palamod.modules.luckyblock.renders.models.may;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelIronSkullHammer extends ModelBase {
  private final ModelRenderer bone;
  
  public ModelIronSkullHammer() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 21.0F, 0.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 26, 16, -1.0F, -18.0F, 0.0F, 1, 18, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 24, 0, -2.5F, -18.0F, -1.5F, 4, 1, 4, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -1.5F, -17.0F, -0.5F, 2, 5, 2, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 16, -1.5F, -3.0F, -0.5F, 2, 2, 2, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -5.0F, -26.0F, -4.0F, 8, 8, 8, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 24, 5, -4.0F, 0.0F, 0.0F, 6, 1, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 16, 16, -5.0F, -1.0F, 0.0F, 3, 1, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 16, 3.0F, -27.0F, -5.0F, 3, 10, 10, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.bone.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\models\may\ModelIronSkullHammer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */