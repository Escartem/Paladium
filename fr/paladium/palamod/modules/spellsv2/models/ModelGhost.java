package fr.paladium.palamod.modules.spellsv2.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelGhost extends ModelBase {
  ModelRenderer Shape1;
  
  ModelRenderer Shape2;
  
  ModelRenderer Shape3;
  
  ModelRenderer Shape4;
  
  ModelRenderer Piece1;
  
  public ModelGhost() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.Shape1 = new ModelRenderer(this, 0, 0);
    this.Shape1.func_78789_a(0.0F, 0.0F, 0.0F, 4, 4, 4);
    this.Shape1.func_78793_a(-2.0F, -2.0F, -2.0F);
    this.Shape1.func_78787_b(64, 64);
    this.Shape1.field_78809_i = true;
    setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
    this.Shape2 = new ModelRenderer(this, 0, 0);
    this.Shape2.func_78789_a(0.0F, 0.0F, 0.0F, 6, 7, 6);
    this.Shape2.func_78793_a(-3.0F, 2.0F, -3.0F);
    this.Shape2.func_78787_b(64, 64);
    this.Shape2.field_78809_i = true;
    setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
    this.Shape3 = new ModelRenderer(this, 0, 0);
    this.Shape3.func_78789_a(0.0F, 0.0F, 0.0F, 2, 11, 2);
    this.Shape3.func_78793_a(3.0F, 2.0F, -1.0F);
    this.Shape3.func_78787_b(64, 64);
    this.Shape3.field_78809_i = true;
    setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
    this.Shape4 = new ModelRenderer(this, 0, 0);
    this.Shape4.func_78789_a(0.0F, 0.0F, 0.0F, 2, 11, 2);
    this.Shape4.func_78793_a(-5.0F, 2.0F, -1.0F);
    this.Shape4.func_78787_b(64, 64);
    this.Shape4.field_78809_i = true;
    setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
    this.Piece1 = new ModelRenderer(this, "Piece1");
    this.Piece1.func_78793_a(0.0F, 0.0F, 0.0F);
    setRotation(this.Piece1, 0.0F, 0.0F, 0.0F);
    this.Piece1.field_78809_i = true;
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    this.Shape1.func_78785_a(f5);
    this.Shape2.func_78785_a(f5);
    this.Shape3.func_78785_a(f5);
    this.Shape4.func_78785_a(f5);
    this.Piece1.func_78785_a(f5);
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv2\models\ModelGhost.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */