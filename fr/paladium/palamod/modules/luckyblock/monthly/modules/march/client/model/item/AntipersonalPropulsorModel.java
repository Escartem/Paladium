package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.model.item;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class AntipersonalPropulsorModel extends ModelBase {
  public final float animationSpeed = 10.0F;
  
  private final ModelRenderer bone;
  
  private final ModelRenderer handle;
  
  private final ModelRenderer laser_0;
  
  private final ModelRenderer laser_1;
  
  public AntipersonalPropulsorModel() {
    this.field_78090_t = 32;
    this.field_78089_u = 32;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 24.0F, -2.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -2.0F, -4.0F, -3.0F, 3, 3, 6, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 15, -2.0F, -1.0F, -2.0F, 3, 1, 4, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 12, 12, -1.5F, -3.5F, 7.0F, 2, 2, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 9, 9, -1.5F, -3.5F, 3.0F, 2, 2, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 9, -2.0F, -4.0F, 4.0F, 3, 3, 3, 0.0F));
    this.handle = new ModelRenderer(this);
    this.handle.func_78793_a(-0.5F, -1.0F, 5.0F);
    this.bone.func_78792_a(this.handle);
    setRotationAngle(this.handle, 0.3927F, 0.0F, 0.0F);
    this.handle.field_78804_l.add(new ModelBox(this.handle, 12, 0, -1.0F, 0.0F, 0.0F, 2, 4, 2, 0.0F));
    this.laser_0 = new ModelRenderer(this);
    this.laser_0.func_78793_a(-0.5F, -2.5F, -5.0F);
    this.bone.func_78792_a(this.laser_0);
    this.laser_0.field_78804_l.add(new ModelBox(this.laser_0, 0, 0, -1.5F, -1.5F, 0.0F, 3, 3, 0, 0.0F));
    this.laser_1 = new ModelRenderer(this);
    this.laser_1.func_78793_a(-0.5F, -2.5F, -4.0F);
    this.bone.func_78792_a(this.laser_1);
    this.laser_1.field_78804_l.add(new ModelBox(this.laser_1, 0, 3, -1.5F, -1.5F, 0.0F, 3, 3, 0, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.laser_0.field_78808_h = (float)(this.laser_0.field_78808_h + Math.toRadians(10.0D));
    this.laser_1.field_78808_h = (float)(this.laser_1.field_78808_h + Math.toRadians(-10.0D));
    this.bone.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\client\model\item\AntipersonalPropulsorModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */