package fr.paladium.palamod.modules.luckyblock.renders.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelInvisible extends ModelBase {
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {}
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.field_78795_f = x;
    model.field_78796_g = y;
    model.field_78808_h = z;
  }
  
  public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
    super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\models\ModelInvisible.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */