package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.models.items;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPipe extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer pipe;
  
  public ModelPipe() {
    this.field_78090_t = 32;
    this.field_78089_u = 32;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 24.0F, 0.0F);
    this.pipe = new ModelRenderer(this);
    this.pipe.func_78793_a(-1.5F, -1.0F, -3.25F);
    this.bone.func_78792_a(this.pipe);
    setRotationAngle(this.pipe, 0.3698F, 0.5735F, -0.0397F);
    this.pipe.field_78804_l.add(new ModelBox(this.pipe, 0, 8, -0.5F, -1.0F, -4.75F, 1, 1, 5, 0.0F));
    this.pipe.field_78804_l.add(new ModelBox(this.pipe, 0, 0, -1.5F, -4.0F, -7.75F, 3, 5, 3, 0.0F));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\client\renders\models\items\ModelPipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */