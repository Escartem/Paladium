package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.renders.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTreadmill extends ModelBase {
  private final ModelRenderer tapis_roulant;
  
  private final ModelRenderer cube_r1;
  
  public ModelTreadmill() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.tapis_roulant = new ModelRenderer(this);
    this.tapis_roulant.func_78793_a(0.0F, 6.5F, -7.25F);
    this.tapis_roulant.field_78804_l.add(new ModelBox(this.tapis_roulant, 0, 28, -6.25F, 0.75F, -1.0F, 2, 17, 2, 0.0F));
    this.tapis_roulant.field_78804_l.add(new ModelBox(this.tapis_roulant, 24, 19, 4.25F, 0.75F, -1.0F, 2, 17, 2, 0.0F));
    this.tapis_roulant.field_78804_l.add(new ModelBox(this.tapis_roulant, 0, 0, -6.0F, 14.5F, -0.75F, 12, 3, 16, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(0.0F, 0.25F, 0.0F);
    this.tapis_roulant.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 0.7854F, 0.0F, 0.0F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 0, 19, -5.0F, -2.5F, -1.5F, 10, 7, 2, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.tapis_roulant.func_78785_a(f5);
  }
  
  public void renderAll() {
    this.tapis_roulant.func_78785_a(0.0625F);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\client\renders\models\blocks\ModelTreadmill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */