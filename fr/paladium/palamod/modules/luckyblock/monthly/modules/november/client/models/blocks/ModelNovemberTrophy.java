package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelNovemberTrophy extends ModelBase {
  private final ModelRenderer bb_main;
  
  private final ModelRenderer cube_r1;
  
  private final ModelRenderer cube_r2;
  
  private final ModelRenderer cube_r3;
  
  public ModelNovemberTrophy() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bb_main = new ModelRenderer(this);
    this.bb_main.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 37, -4.0F, -2.0F, -3.0F, 8, 2, 7, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 24, 26, -3.0F, -3.0F, -4.0F, 6, 3, 1, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(0.8F, -1.3F, 0.0F);
    this.bb_main.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, -0.1439F, 0.7998F, -0.5738F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 12, 26, -5.0F, -6.0F, -2.0F, 4, 7, 4, 0.0F));
    this.cube_r2 = new ModelRenderer(this);
    this.cube_r2.func_78793_a(0.8F, -1.3F, 0.0F);
    this.bb_main.func_78792_a(this.cube_r2);
    setRotationAngle(this.cube_r2, 0.0F, 0.3054F, 0.7854F);
    this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 16, 14, -1.0F, -7.0F, -2.0F, 4, 8, 4, 0.0F));
    this.cube_r3 = new ModelRenderer(this);
    this.cube_r3.func_78793_a(0.8F, -1.3F, 0.0F);
    this.bb_main.func_78792_a(this.cube_r3);
    setRotationAngle(this.cube_r3, 0.0F, -0.3491F, 0.0F);
    this.cube_r3.field_78804_l.add(new ModelBox(this.cube_r3, 0, 14, -2.0F, -8.0F, -2.0F, 4, 10, 4, 0.0F));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\client\models\blocks\ModelNovemberTrophy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */