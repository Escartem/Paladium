package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.client.renders.models.items;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGunSword extends ModelBase {
  private final ModelRenderer bb_main;
  
  public ModelGunSword() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bb_main = new ModelRenderer(this);
    this.bb_main.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 12, 15, -2.0F, -1.0F, -2.0F, 4, 1, 4, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 19, -1.0F, -15.0F, 0.0F, 2, 7, 2, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 14, -2.0F, -8.0F, -2.0F, 4, 1, 4, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 28, -3, 0.0F, -32.0F, -3.0F, 0, 24, 3, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 8, 20, -1.0F, -7.0F, -1.0F, 2, 6, 2, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -3.0F, -8.0F, -4.0F, 6, 7, 7, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 19, 0, -1.0F, -7.0F, -3.0F, 2, 3, 2, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 16, 20, -2.0F, -10.0F, 2.0F, 2, 2, 2, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.bb_main.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
  
  public void renderAll() {
    this.bb_main.func_78785_a(0.0625F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\client\renders\models\items\ModelGunSword.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */