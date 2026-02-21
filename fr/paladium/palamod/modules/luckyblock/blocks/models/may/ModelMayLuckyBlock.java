package fr.paladium.palamod.modules.luckyblock.blocks.models.may;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMayLuckyBlock extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer bb_main;
  
  private final ModelRenderer cube_r1;
  
  private final ModelRenderer cube_r2;
  
  public ModelMayLuckyBlock() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 32, 45, 8.0F, -19.0F, -8.0F, 0, 3, 16, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 61, -8.0F, -19.0F, 8.0F, 16, 3, 0, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 32, 45, -8.0F, -19.0F, -8.0F, 0, 3, 16, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 61, -8.0F, -19.0F, -8.0F, 16, 3, 0, 0.0F));
    this.bb_main = new ModelRenderer(this);
    this.bb_main.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -8.0F, -16.0F, -8.0F, 16, 16, 16, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, 2.0F, -19.0F, -8.1F, 7, 7, 0, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 7, -10.0F, -15.0F, 8.1F, 6, 6, 0, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(2.5F, -10.5F, -8.1F);
    this.bb_main.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 0.0F, 3.1416F, 0.0F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 0, 37, -8.5F, -2.5F, 0.0F, 7, 7, 0, 0.0F));
    this.cube_r2 = new ModelRenderer(this);
    this.cube_r2.func_78793_a(-4.0F, -15.0F, 8.1F);
    this.bb_main.func_78792_a(this.cube_r2);
    setRotationAngle(this.cube_r2, 0.0F, 3.1416F, 0.0F);
    this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 0, 32, -4.0F, -3.0F, 0.0F, 6, 6, 0, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.bone.func_78785_a(f5);
    this.bb_main.func_78785_a(f5);
  }
  
  public void renderAll() {
    this.bone.func_78785_a(0.0625F);
    this.bb_main.func_78785_a(0.0625F);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\models\may\ModelMayLuckyBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */