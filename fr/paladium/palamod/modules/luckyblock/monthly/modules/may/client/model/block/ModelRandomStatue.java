package fr.paladium.palamod.modules.luckyblock.monthly.modules.may.client.model.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRandomStatue extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer player;
  
  private final ModelRenderer head;
  
  private final ModelRenderer arm_left;
  
  private final ModelRenderer arm_right;
  
  private final ModelRenderer gift;
  
  private final ModelRenderer ribon_0;
  
  private final ModelRenderer ribon_1;
  
  public ModelRandomStatue() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 24.0F, 0.0F);
    setRotationAngle(this.bone, 0.0F, -1.5708F, 0.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -7.0F, -2.0F, -7.0F, 14, 2, 14, 0.0F));
    this.player = new ModelRenderer(this);
    this.player.func_78793_a(5.0F, -2.0F, 2.0F);
    this.bone.func_78792_a(this.player);
    setRotationAngle(this.player, 0.0F, 0.0F, -0.3054F);
    this.player.field_78804_l.add(new ModelBox(this.player, 0, 32, -4.0F, -3.0F, -4.0F, 4, 3, 8, 0.0F));
    this.head = new ModelRenderer(this);
    this.head.func_78793_a(-2.0F, -3.0F, 0.0F);
    this.player.func_78792_a(this.head);
    setRotationAngle(this.head, 0.2967F, -0.1582F, 0.6378F);
    this.head.field_78804_l.add(new ModelBox(this.head, 24, 24, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F));
    this.arm_left = new ModelRenderer(this);
    this.arm_left.func_78793_a(-2.0F, -2.5F, -4.0F);
    this.player.func_78792_a(this.arm_left);
    setRotationAngle(this.arm_left, -0.0172F, -0.1298F, 1.7028F);
    this.arm_right = new ModelRenderer(this);
    this.arm_right.func_78793_a(-2.0F, -3.0F, 4.0F);
    this.player.func_78792_a(this.arm_right);
    setRotationAngle(this.arm_right, -0.48F, 0.0F, 2.618F);
    this.arm_right.field_78804_l.add(new ModelBox(this.arm_right, 20, 40, -2.0F, 0.0F, 0.0F, 4, 12, 4, 0.0F));
    this.gift = new ModelRenderer(this);
    this.gift.func_78793_a(-3.0F, 11.0F, 5.0F);
    this.arm_right.func_78792_a(this.gift);
    setRotationAngle(this.gift, -1.2834F, 0.4703F, -0.6229F);
    this.gift.field_78804_l.add(new ModelBox(this.gift, 0, 16, -4.0F, -2.0F, -2.0F, 8, 8, 8, 0.0F));
    this.ribon_0 = new ModelRenderer(this);
    this.ribon_0.func_78793_a(-4.0F, 1.0F, 2.0F);
    this.gift.func_78792_a(this.ribon_0);
    setRotationAngle(this.ribon_0, 0.0F, 0.0F, -0.1309F);
    this.ribon_0.field_78804_l.add(new ModelBox(this.ribon_0, 0, 6, -2.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F));
    this.ribon_1 = new ModelRenderer(this);
    this.ribon_1.func_78793_a(-4.0F, 3.0F, 2.0F);
    this.gift.func_78792_a(this.ribon_1);
    setRotationAngle(this.ribon_1, 0.0F, 0.0F, 0.1309F);
    this.ribon_1.field_78804_l.add(new ModelBox(this.ribon_1, 0, 0, -2.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\may\client\model\block\ModelRandomStatue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */