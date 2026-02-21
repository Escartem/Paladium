package fr.paladium.palamod.modules.luckyblock.renders.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public class ModelPiegeFicelleOn extends ModelBase {
  private final ModelRenderer ficelle;
  
  private final ModelRenderer cube_r1;
  
  private final ModelRenderer bb_main;
  
  public ModelPiegeFicelleOn() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.ficelle = new ModelRenderer(this);
    this.ficelle.func_78793_a(0.5F, 17.0F, -9.5F);
    setRotationAngle(this.ficelle, 0.0F, 0.0F, -3.1416F);
    this.ficelle.field_78804_l.add(new ModelBox(this.ficelle, 0, 7, -1.5F, -5.0F, -1.1F, 4, 4, 0, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(0.0F, 0.0F, 0.0F);
    this.ficelle.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 0.3054F, 0.0F, 0.0F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 8, 7, -0.5F, -2.0F, -0.5F, 2, 4, 1, 0.0F));
    this.bb_main = new ModelRenderer(this);
    this.bb_main.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -8.0F, -16.0F, -8.0F, 16, 16, 16, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -3.0F, -11.0F, -9.0F, 6, 6, 1, 0.0F));
  }
  
  public void renderAll() {
    this.ficelle.func_78785_a(0.0625F);
    this.bb_main.func_78785_a(0.0625F);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\models\ModelPiegeFicelleOn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */