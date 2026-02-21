package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.client.renders.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAugustTrophy extends ModelBase {
  private final ModelRenderer august_trophy;
  
  private final ModelRenderer cube_r1;
  
  private final ModelRenderer cube_r2;
  
  public ModelAugustTrophy() {
    this.field_78090_t = 128;
    this.field_78089_u = 128;
    this.august_trophy = new ModelRenderer(this);
    this.august_trophy.func_78793_a(0.0F, 13.0F, 0.0F);
    this.august_trophy.field_78804_l.add(new ModelBox(this.august_trophy, 32, 16, -5.0F, 9.0F, -5.0F, 10, 2, 10, 0.0F));
    this.august_trophy.field_78804_l.add(new ModelBox(this.august_trophy, 38, 40, -4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F));
    this.august_trophy.field_78804_l.add(new ModelBox(this.august_trophy, 46, 0, -8.0F, -5.0F, 0.0F, 16, 16, 0, 0.0F));
    this.august_trophy.field_78804_l.add(new ModelBox(this.august_trophy, 0, 0, 0.0F, -5.0F, -8.0F, 0, 16, 16, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(0.0F, 3.0F, 1.0F);
    this.august_trophy.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 0.0F, 0.7854F, 0.0F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 0, 0, -10.75F, -8.0F, -0.75F, 23, 16, 0, 0.0F));
    this.cube_r2 = new ModelRenderer(this);
    this.cube_r2.func_78793_a(0.0F, 3.0F, 1.0F);
    this.august_trophy.func_78792_a(this.cube_r2);
    setRotationAngle(this.cube_r2, 0.0F, -0.7854F, 0.0F);
    this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 0, 32, -12.25F, -8.0F, -0.75F, 23, 16, 0, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.august_trophy.func_78785_a(f5);
  }
  
  public void renderAll() {
    this.august_trophy.func_78785_a(0.0625F);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\client\renders\models\blocks\ModelAugustTrophy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */