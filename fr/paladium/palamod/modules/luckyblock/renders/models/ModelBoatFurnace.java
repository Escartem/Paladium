package fr.paladium.palamod.modules.luckyblock.renders.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBoatFurnace extends ModelBase {
  private final ModelRenderer All;
  
  private final ModelRenderer bottom;
  
  private final ModelRenderer front;
  
  private final ModelRenderer back;
  
  private final ModelRenderer right;
  
  private final ModelRenderer left;
  
  private final ModelRenderer paddle_left;
  
  private final ModelRenderer paddle_right;
  
  private final ModelRenderer pipe2;
  
  private final ModelRenderer pipe;
  
  public ModelBoatFurnace() {
    this.field_78090_t = 128;
    this.field_78089_u = 128;
    this.All = new ModelRenderer(this);
    this.All.func_78793_a(-14.0F, 13.0F, 0.0F);
    this.All.field_78804_l.add(new ModelBox(this.All, 0, 35, -8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F));
    this.bottom = new ModelRenderer(this);
    this.bottom.func_78793_a(22.0F, 11.0F, 0.0F);
    this.All.func_78792_a(this.bottom);
    setRotationAngle(this.bottom, 1.5708F, 0.0F, 0.0F);
    this.bottom.field_78804_l.add(new ModelBox(this.bottom, 0, 0, -30.0F, -8.0F, 0.0F, 44, 16, 3, 0.0F));
    this.front = new ModelRenderer(this);
    this.front.func_78793_a(37.0F, 5.0F, 0.0F);
    this.All.func_78792_a(this.front);
    setRotationAngle(this.front, 0.0F, 1.5708F, 0.0F);
    this.front.field_78804_l.add(new ModelBox(this.front, 68, 43, -8.0F, -3.0F, -1.0F, 16, 6, 2, 0.0F));
    this.back = new ModelRenderer(this);
    this.back.func_78793_a(7.0F, 5.0F, 0.0F);
    this.All.func_78792_a(this.back);
    setRotationAngle(this.back, 0.0F, -1.5708F, 0.0F);
    this.back.field_78804_l.add(new ModelBox(this.back, 48, 35, -9.0F, -3.0F, 15.0F, 18, 6, 2, 0.0F));
    this.right = new ModelRenderer(this);
    this.right.func_78793_a(22.0F, 5.0F, -9.0F);
    this.All.func_78792_a(this.right);
    setRotationAngle(this.right, 0.0F, -3.1416F, 0.0F);
    this.right.field_78804_l.add(new ModelBox(this.right, 0, 27, -14.0F, -3.0F, -1.0F, 44, 6, 2, 0.0F));
    this.left = new ModelRenderer(this);
    this.left.func_78793_a(22.0F, 5.0F, 9.0F);
    this.All.func_78792_a(this.left);
    this.left.field_78804_l.add(new ModelBox(this.left, 0, 19, -30.0F, -3.0F, -1.0F, 44, 6, 2, 0.0F));
    this.paddle_left = new ModelRenderer(this);
    this.paddle_left.func_78793_a(19.5F, 1.0F, 9.0F);
    this.All.func_78792_a(this.paddle_left);
    setRotationAngle(this.paddle_left, -0.5236F, 0.0F, 0.0F);
    this.paddle_left.field_78804_l.add(new ModelBox(this.paddle_left, 0, 67, -1.0F, -1.0F, -5.5F, 2, 2, 18, 0.0F));
    this.paddle_left.field_78804_l.add(new ModelBox(this.paddle_left, 0, 67, -0.01F, -4.0F, 8.5F, 1, 6, 7, 0.0F));
    this.paddle_right = new ModelRenderer(this);
    this.paddle_right.func_78793_a(19.5F, 1.0F, -9.0F);
    this.All.func_78792_a(this.paddle_right);
    setRotationAngle(this.paddle_right, -0.5236F, 3.1416F, 0.0F);
    this.paddle_right.field_78804_l
      .add(new ModelBox(this.paddle_right, 46, 49, -1.0F, -1.0F, -5.5F, 2, 2, 18, 0.0F));
    this.paddle_right.field_78804_l
      .add(new ModelBox(this.paddle_right, 0, 35, -0.99F, -4.0F, 8.5F, 1, 6, 7, 0.0F));
    this.pipe2 = new ModelRenderer(this);
    this.pipe2.func_78793_a(22.0F, 29.0F, -10.0F);
    this.All.func_78792_a(this.pipe2);
    this.pipe2.field_78804_l.add(new ModelBox(this.pipe2, 58, 43, -34.0F, -25.0F, 3.0F, 2, 3, 3, 0.0F));
    this.pipe2.field_78804_l.add(new ModelBox(this.pipe2, 22, 67, -37.0F, -34.0F, 3.0F, 3, 12, 3, 0.0F));
    this.pipe2.field_78804_l.add(new ModelBox(this.pipe2, 48, 43, -39.0F, -34.0F, 3.0F, 2, 3, 3, 0.0F));
    this.pipe = new ModelRenderer(this);
    this.pipe.func_78793_a(22.0F, 29.0F, 1.0F);
    this.All.func_78792_a(this.pipe);
    this.pipe.field_78804_l.add(new ModelBox(this.pipe, 68, 57, -34.0F, -25.0F, 3.0F, 2, 3, 3, 0.0F));
    this.pipe.field_78804_l.add(new ModelBox(this.pipe, 34, 67, -37.0F, -34.0F, 3.0F, 3, 12, 3, 0.0F));
    this.pipe.field_78804_l.add(new ModelBox(this.pipe, 68, 51, -39.0F, -34.0F, 3.0F, 2, 3, 3, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.All.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\models\ModelBoatFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */