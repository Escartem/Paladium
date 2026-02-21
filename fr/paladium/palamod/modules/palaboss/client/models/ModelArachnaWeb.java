package fr.paladium.palamod.modules.palaboss.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelArachnaWeb extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer top;
  
  public ModelArachnaWeb() {
    this.field_78090_t = 32;
    this.field_78089_u = 32;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -3.0F, -3.0F, -3.0F, 6, 6, 6, 0.0F));
    this.top = new ModelRenderer(this);
    this.top.func_78793_a(0.0F, -3.5F, 0.0F);
    this.bone.func_78792_a(this.top);
    setRotationAngle(this.top, 0.0F, 0.7854F, 0.0F);
    this.top.field_78804_l.add(new ModelBox(this.top, 16, 21, -4.0F, -10.5F, 0.0F, 8, 11, 0, 0.0F));
    this.top.field_78804_l.add(new ModelBox(this.top, 16, 13, 0.0F, -10.5F, -4.0F, 0, 11, 8, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    this.bone.func_78785_a(f5);
    GL11.glDisable(3042);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\client\models\ModelArachnaWeb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */