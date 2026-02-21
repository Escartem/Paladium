package fr.paladium.palamod.modules.luckyblock.renders.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelBalloon extends ModelBase {
  private final ModelRenderer bb_main;
  
  public ModelBalloon() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bb_main = new ModelRenderer(this, 0, 0);
    this.bb_main.func_78789_a(0.0F, 0.0F, 0.0F, 8, 8, 8);
    this.bb_main.func_78793_a(-2.0F, -2.0F, -2.0F);
    this.bb_main.func_78787_b(64, 64);
    this.bb_main.field_78809_i = true;
    setRotation(this.bb_main, 0.0F, 0.0F, 0.0F);
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    this.bb_main.func_78785_a(f5);
    GL11.glDisable(3042);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.field_78795_f = x;
    model.field_78796_g = y;
    model.field_78808_h = z;
  }
  
  public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
    super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\models\ModelBalloon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */