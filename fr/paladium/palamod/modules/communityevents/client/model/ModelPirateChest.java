package fr.paladium.palamod.modules.communityevents.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPirateChest extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer lid;
  
  public ModelPirateChest() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 19.0F, 5.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -7.0F, 0.0F, -8.0F, 14, 5, 8, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -1.0F, 0.0F, -9.0F, 2, 1, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 16, 25, -8.0F, 4.001F, -9.0F, 3, 1, 3, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 16, 29, 5.0F, 4.001F, -9.0F, 3, 1, 3, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 36, 0, 5.0F, 4.001F, -2.0F, 3, 1, 3, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 36, 4, -8.0F, 4.001F, -2.0F, 3, 1, 3, 0.0F));
    this.lid = new ModelRenderer(this);
    this.lid.func_78793_a(0.0F, 0.0F, 0.0F);
    this.bone.func_78792_a(this.lid);
    this.lid.field_78804_l.add(new ModelBox(this.lid, 0, 13, -7.0F, -4.0F, -8.0F, 14, 4, 8, 0.0F));
    this.lid.field_78804_l.add(new ModelBox(this.lid, 26, 25, 5.0F, -5.0F, -9.0F, 3, 2, 10, 0.0F));
    this.lid.field_78804_l.add(new ModelBox(this.lid, 0, 25, -8.0F, -5.0F, -9.0F, 3, 2, 10, 0.0F));
    this.lid.field_78804_l.add(new ModelBox(this.lid, 36, 13, -8.0F, -3.001F, -9.0F, 3, 3, 2, 0.0F));
    this.lid.field_78804_l.add(new ModelBox(this.lid, 0, 30, 5.0F, -3.001F, -9.0F, 3, 3, 2, 0.0F));
    this.lid.field_78804_l.add(new ModelBox(this.lid, 26, 27, -8.0F, -3.001F, -1.0F, 3, 3, 2, 0.0F));
    this.lid.field_78804_l.add(new ModelBox(this.lid, 0, 25, 5.0F, -3.001F, -1.0F, 3, 3, 2, 0.0F));
    this.lid.field_78804_l.add(new ModelBox(this.lid, 0, 37, -2.0F, -3.0F, -9.0F, 4, 3, 1, 0.0F));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\communityevents\client\model\ModelPirateChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */