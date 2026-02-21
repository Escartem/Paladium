package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.models.blocks.plant;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSecondCorruptedPlant extends ModelBase implements IModelPlant {
  private final ModelRenderer bone;
  
  private final ModelRenderer cube_r1;
  
  private final ModelRenderer cube_r2;
  
  private final ModelRenderer stage0;
  
  private final ModelRenderer stage1;
  
  private final ModelRenderer stage2;
  
  private final ModelRenderer stage3;
  
  private final ModelRenderer cube_r3;
  
  public ModelSecondCorruptedPlant() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 32, 6, 6.0F, -7.0F, -8.0F, 0, 7, 16, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 32, 6, -6.0F, -7.0F, -8.0F, 0, 7, 16, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(0.0F, 0.0F, 0.0F);
    this.bone.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 0.0F, 1.5708F, 0.0F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 32, 6, -6.0F, -7.0F, -8.0F, 0, 7, 16, 0.0F));
    this.cube_r2 = new ModelRenderer(this);
    this.cube_r2.func_78793_a(0.0F, 0.0F, 0.0F);
    this.bone.func_78792_a(this.cube_r2);
    setRotationAngle(this.cube_r2, 0.0F, -1.5708F, 0.0F);
    this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 32, 6, -6.0F, -7.0F, -8.0F, 0, 7, 16, 0.0F));
    this.stage0 = new ModelRenderer(this);
    this.stage0.func_78793_a(0.0F, 24.0F, 0.0F);
    this.stage0.field_78804_l.add(new ModelBox(this.stage0, 40, 12, -2.0F, -4.0F, -2.0F, 4, 4, 4, 0.0F));
    this.stage1 = new ModelRenderer(this);
    this.stage1.func_78793_a(0.0F, 24.0F, 0.0F);
    this.stage1.field_78804_l.add(new ModelBox(this.stage1, 40, 0, -3.0F, -6.0F, -3.0F, 6, 6, 6, 0.0F));
    this.stage2 = new ModelRenderer(this);
    this.stage2.func_78793_a(0.0F, 24.0F, 0.0F);
    this.stage2.field_78804_l.add(new ModelBox(this.stage2, 0, 20, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F));
    this.stage3 = new ModelRenderer(this);
    this.stage3.func_78793_a(0.0F, 24.0F, 0.0F);
    this.stage3.field_78804_l.add(new ModelBox(this.stage3, 0, 0, -5.0F, -10.0F, -5.0F, 10, 10, 10, 0.0F));
    this.stage3.field_78804_l.add(new ModelBox(this.stage3, 56, 14, -2.0F, -13.0F, 0.0F, 4, 3, 0, 0.0F));
    this.cube_r3 = new ModelRenderer(this);
    this.cube_r3.func_78793_a(0.0F, -11.5F, 0.0F);
    this.stage3.func_78792_a(this.cube_r3);
    setRotationAngle(this.cube_r3, 0.0F, -1.5708F, 0.0F);
    this.cube_r3.field_78804_l.add(new ModelBox(this.cube_r3, 56, 14, -2.0F, -1.5F, 0.0F, 4, 3, 0, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.bone.func_78785_a(f5);
    this.stage0.func_78785_a(f5);
    this.stage1.func_78785_a(f5);
    this.stage2.func_78785_a(f5);
    this.stage3.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
  
  public void renderAll() {
    float f = 0.0625F;
    this.bone.func_78785_a(f);
    this.stage0.func_78785_a(f);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\client\models\blocks\plant\ModelSecondCorruptedPlant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */