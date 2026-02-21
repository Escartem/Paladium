package fr.paladium.palamod.modules.paladium.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelChristmasHammer extends ModelBase {
  private final ModelRenderer bb_main;
  
  public ModelChristmasHammer() {
    this.field_78090_t = 128;
    this.field_78089_u = 128;
    this.bb_main = new ModelRenderer(this);
    this.bb_main.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 22, -8.0F, -16.0F, -3.0F, 16, 16, 4, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 44, 52, -2.0F, 0.0F, 5.0F, 4, 2, 4, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -6.0F, -14.0F, 1.0F, 12, 12, 10, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 32, 34, -5.0F, -13.0F, 11.0F, 10, 10, 8, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 42, -4.0F, -12.0F, 19.0F, 8, 8, 8, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -1.0F, -17.0F, 21.0F, 2, 5, 2, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 36, 12, -4.0F, -2.0F, 1.0F, 8, 2, 10, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 32, 52, -1.5F, 2.0F, 5.5F, 3, 14, 3, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 40, 24, -2.0F, 16.0F, 5.0F, 4, 2, 4, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 44, 0, -2.0F, 8.0F, 5.0F, 4, 8, 4, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float limbSwing, float limbSwingAmount, float liveTick, float headYaw, float headPitch, float scaleFactor) {
    func_78087_a(limbSwing, limbSwingAmount, liveTick, headYaw, headPitch, scaleFactor, entity);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    super.func_78088_a(entity, limbSwing, limbSwingAmount, liveTick, headYaw, headPitch, scaleFactor);
    this.bb_main.func_78785_a(scaleFactor);
    GL11.glDisable(3042);
  }
  
  public void func_78087_a(float limbSwing, float limbSwingAmount, float liveTick, float headYaw, float headPitch, float scaleFactor, Entity e) {
    super.func_78087_a(limbSwing, limbSwingAmount, liveTick, headYaw, headPitch, scaleFactor, e);
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\model\ModelChristmasHammer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */