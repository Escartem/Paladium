package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.model.block;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class MonsterBlackHoleModel extends ModelBase {
  ModelRenderer black_hole;
  
  ModelRenderer core;
  
  ModelRenderer ring0;
  
  ModelRenderer ring1;
  
  ModelRenderer ring2;
  
  ModelRenderer ring3;
  
  ModelRenderer ring4;
  
  ModelRenderer ring5;
  
  public MonsterBlackHoleModel() {
    this.field_78090_t = 128;
    this.field_78089_u = 128;
    this.black_hole = new ModelRenderer(this);
    this.black_hole.func_78793_a(0.0F, 23.0F, 0.0F);
    this.core = new ModelRenderer(this);
    this.core.func_78793_a(0.0F, 0.0F, 0.0F);
    this.core.func_78790_a(-3.0F, -10.0F, -3.0F, 6, 6, 6, 0.0F);
    this.black_hole.func_78792_a(this.core);
    this.ring0 = new ModelRenderer(this);
    this.ring0.func_78793_a(0.0F, -6.05F, 0.0F);
    setRotation(this.ring0, 1.0F, 0.0F, 0.8854F);
    this.ring0.func_78790_a(-12.0F, 0.0F, -12.0F, 24, 0, 24, 0.0F);
    this.black_hole.func_78792_a(this.ring0);
    this.ring1 = new ModelRenderer(this);
    this.ring1.func_78793_a(0.0F, -5.95F, 0.0F);
    setRotation(this.ring1, 0.0F, 0.0F, -2.3562F);
    this.ring1.func_78790_a(-12.0F, 0.0F, -12.0F, 24, 0, 24, 0.0F);
    this.black_hole.func_78792_a(this.ring1);
    this.ring2 = new ModelRenderer(this);
    this.ring2.func_78793_a(0.0F, -6.05F, 0.0F);
    setRotation(this.ring2, 1.0F, 0.0F, 0.8854F);
    this.ring2.func_78790_a(-12.0F, 0.0F, -12.0F, 24, 0, 24, 0.0F);
    this.black_hole.func_78792_a(this.ring2);
    this.ring3 = new ModelRenderer(this);
    this.ring3.func_78793_a(0.0F, -5.95F, 0.0F);
    setRotation(this.ring1, 0.0F, 0.0F, -2.3562F);
    this.ring3.func_78790_a(-12.0F, 0.0F, -12.0F, 24, 0, 24, 0.0F);
    this.black_hole.func_78792_a(this.ring3);
    this.ring4 = new ModelRenderer(this);
    this.ring4.func_78793_a(0.0F, -5.95F, 0.0F);
    setRotation(this.ring4, 10.0F, 0.0F, -2.3562F);
    this.ring4.func_78790_a(-12.0F, 0.0F, -15.0F, 24, 0, 24, 0.0F);
    this.black_hole.func_78792_a(this.ring4);
    this.ring5 = new ModelRenderer(this);
    this.ring5.func_78793_a(0.0F, -5.95F, 0.0F);
    setRotation(this.ring5, 90.0F, 0.0F, -2.3562F);
    this.ring5.func_78790_a(-12.0F, 0.0F, -15.0F, 24, 0, 24, 0.0F);
    this.black_hole.func_78792_a(this.ring5);
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    this.black_hole.func_78785_a(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.field_78795_f = x;
    model.field_78796_g = y;
    model.field_78808_h = z;
  }
  
  public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {}
  
  public void renderAll() {
    float rotationAngle = (Minecraft.func_71410_x()).field_71439_g.field_70173_aa * 0.1F;
    this.ring0.field_78796_g = rotationAngle;
    this.ring1.field_78796_g = -rotationAngle + 0.7853982F;
    this.ring2.field_78796_g = rotationAngle;
    this.ring3.field_78796_g = -rotationAngle + 0.7853982F;
    this.ring4.field_78796_g = rotationAngle;
    this.ring5.field_78796_g = -rotationAngle + 0.7853982F;
    this.black_hole.func_78785_a(0.0625F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\client\model\block\MonsterBlackHoleModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */