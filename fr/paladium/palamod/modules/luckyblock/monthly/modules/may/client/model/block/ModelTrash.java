package fr.paladium.palamod.modules.luckyblock.monthly.modules.may.client.model.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTrash extends ModelBase {
  private final ModelRenderer bone;
  
  public ModelTrash() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 13, -4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -5.0F, -13.0F, -5.0F, 10, 3, 10, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 24, 13, -2.0F, -15.0F, -1.0F, 4, 2, 2, 0.0F));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\may\client\model\block\ModelTrash.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */