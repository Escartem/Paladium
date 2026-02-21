package fr.paladium.palamod.modules.palaboss.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelGear extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer bone2;
  
  private final ModelRenderer bone3;
  
  public ModelGear() {
    this.field_78090_t = 16;
    this.field_78089_u = 16;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(-0.48F, 19.5F, 0.5F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -2.52F, 2.5F, -0.5F, 5, 2, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -2.52F, -4.5F, -0.5F, 5, 2, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 4, -1.52F, 4.5F, -0.5F, 3, 1, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 4, -1.52F, -5.5F, -0.5F, 3, 1, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 12, 0, -0.42F, -2.5F, -0.5F, 1, 5, 1, 0.0F));
    this.bone2 = new ModelRenderer(this);
    this.bone2.func_78793_a(0.0F, 0.0F, 0.0F);
    this.bone.func_78792_a(this.bone2);
    setRotationAngle(this.bone2, 0.0F, 0.0F, 1.0472F);
    this.bone2.field_78804_l.add(new ModelBox(this.bone2, 0, 0, -2.52F, 2.5F, -0.5F, 5, 2, 1, 0.0F));
    this.bone2.field_78804_l.add(new ModelBox(this.bone2, 0, 0, -2.52F, -4.5F, -0.5F, 5, 2, 1, 0.0F));
    this.bone2.field_78804_l.add(new ModelBox(this.bone2, 0, 4, -1.52F, 4.5F, -0.5F, 3, 1, 1, 0.0F));
    this.bone2.field_78804_l.add(new ModelBox(this.bone2, 0, 4, -1.52F, -5.5F, -0.5F, 3, 1, 1, 0.0F));
    this.bone2.field_78804_l.add(new ModelBox(this.bone2, 12, 0, -0.42F, -2.5F, -0.5F, 1, 5, 1, 0.0F));
    this.bone3 = new ModelRenderer(this);
    this.bone3.func_78793_a(0.0F, 0.0F, 0.0F);
    this.bone.func_78792_a(this.bone3);
    setRotationAngle(this.bone3, 0.0F, 0.0F, 2.0944F);
    this.bone3.field_78804_l.add(new ModelBox(this.bone3, 0, 0, -2.52F, 2.5F, -0.5F, 5, 2, 1, 0.0F));
    this.bone3.field_78804_l.add(new ModelBox(this.bone3, 0, 0, -2.52F, -4.5F, -0.5F, 5, 2, 1, 0.0F));
    this.bone3.field_78804_l.add(new ModelBox(this.bone3, 0, 4, -1.52F, 4.5F, -0.5F, 3, 1, 1, 0.0F));
    this.bone3.field_78804_l.add(new ModelBox(this.bone3, 0, 4, -1.52F, -5.5F, -0.5F, 3, 1, 1, 0.0F));
    this.bone3.field_78804_l.add(new ModelBox(this.bone3, 12, 0, -0.42F, -2.5F, -0.5F, 1, 5, 1, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    GL11.glPushMatrix();
    GL11.glScalef(1.9F, 1.9F, 1.9F);
    GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
    this.bone.func_78785_a(f5);
    GL11.glPopMatrix();
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
  
  public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
    this.bone.field_78808_h = f2 / 2.0F;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\client\models\ModelGear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */