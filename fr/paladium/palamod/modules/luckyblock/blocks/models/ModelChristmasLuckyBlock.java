package fr.paladium.palamod.modules.luckyblock.blocks.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelChristmasLuckyBlock extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer cube_r1;
  
  private final ModelRenderer cube_r2;
  
  private final ModelRenderer cube_r3;
  
  private final ModelRenderer bone3;
  
  public ModelChristmasLuckyBlock() {
    this.field_78090_t = 128;
    this.field_78089_u = 128;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -8.0F, -16.0F, -8.0F, 16, 16, 16, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 44, 44, -2.0F, -17.0F, -8.0F, 4, 1, 6, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 48, 0, -2.0F, -17.0F, 2.0F, 4, 1, 6, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 20, 49, -2.0F, -17.0F, -9.0F, 4, 17, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 10, 49, -2.0F, -17.0F, 8.0F, 4, 17, 1, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(0.0F, 0.0F, 0.0F);
    this.bone.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, -3.1416F, -1.5708F, 3.1416F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 0, 49, -2.0F, -17.0F, 8.0F, 4, 17, 1, 0.0F));
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 40, 32, -2.0F, -17.0F, -9.0F, 4, 17, 1, 0.0F));
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 0, 32, -2.0F, -17.0F, -8.0F, 4, 1, 16, 0.0F));
    this.cube_r2 = new ModelRenderer(this);
    this.cube_r2.func_78793_a(0.0F, -20.0F, 0.0F);
    this.bone.func_78792_a(this.cube_r2);
    setRotationAngle(this.cube_r2, 0.48F, 0.0F, 0.0F);
    this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 0, 0, -2.0F, -3.0F, -3.0F, 4, 6, 2, 0.0F));
    this.cube_r3 = new ModelRenderer(this);
    this.cube_r3.func_78793_a(0.0F, -20.0F, 0.0F);
    this.bone.func_78792_a(this.cube_r3);
    setRotationAngle(this.cube_r3, -0.9163F, 0.0F, 0.0F);
    this.cube_r3.field_78804_l.add(new ModelBox(this.cube_r3, 0, 8, -2.0F, -3.0F, 1.0F, 4, 6, 2, 0.0F));
    this.bone3 = new ModelRenderer(this);
    this.bone3.func_78793_a(0.0F, -20.0F, 0.0F);
    this.bone.func_78792_a(this.bone3);
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.bone.func_78785_a(f5);
  }
  
  public void renderAll() {
    this.bone.func_78785_a(0.0625F);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\models\ModelChristmasLuckyBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */