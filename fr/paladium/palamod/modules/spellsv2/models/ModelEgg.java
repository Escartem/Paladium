package fr.paladium.palamod.modules.spellsv2.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelEgg extends ModelBase {
  private final ModelRenderer bone;
  
  public ModelEgg() {
    this.field_78090_t = 128;
    this.field_78089_u = 128;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 56, 106, -9.0F, -3.0F, -9.0F, 18, 4, 18, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 48, 66, -10.0F, -20.0F, -10.0F, 20, 17, 20, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 56, 39, -9.0F, -25.0F, -9.0F, 18, 5, 18, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 64, 17, -8.0F, -28.0F, -8.0F, 16, 3, 16, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 80, 0, -6.0F, -31.0F, -6.0F, 12, 3, 12, 0.0F));
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
  
  public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
    super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\models\ModelEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */