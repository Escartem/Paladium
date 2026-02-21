package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSeptemberTrophy extends ModelBase {
  private final ModelRenderer september_trophy;
  
  private final ModelRenderer cube_r1;
  
  public ModelSeptemberTrophy() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.september_trophy = new ModelRenderer(this);
    this.september_trophy.func_78793_a(0.0F, 13.0F, 0.0F);
    this.september_trophy.field_78804_l.add(new ModelBox(this.september_trophy, 0, 0, -5.0F, 9.0F, -5.0F, 10, 2, 10, 0.0F));
    this.september_trophy.field_78804_l.add(new ModelBox(this.september_trophy, 0, 0, -1.75F, -5.0F, -11.25F, 0, 16, 23, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(0.0F, 0.0F, 0.0F);
    this.september_trophy.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 0.0F, 0.0F, -0.3927F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 32, 4, -1.0F, -4.0F, -4.0F, 1, 10, 8, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.september_trophy.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
  
  public void renderAll() {
    this.september_trophy.func_78785_a(0.0625F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\client\models\blocks\ModelSeptemberTrophy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */