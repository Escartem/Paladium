package fr.paladium.palamod.modules.luckyblock.blocks.models.christmas;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSantaClausMockup extends ModelBase {
  private final ModelRenderer SANTA;
  
  private final ModelRenderer LEG_LEFT;
  
  private final ModelRenderer LEG_RIGHT;
  
  private final ModelRenderer BODY;
  
  private final ModelRenderer HEAD;
  
  private final ModelRenderer ARM_RIGHT;
  
  private final ModelRenderer ARM_LEFT;
  
  public ModelSantaClausMockup() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.SANTA = new ModelRenderer(this);
    this.SANTA.func_78793_a(0.0F, 24.0F, 0.0F);
    setRotationAngle(this.SANTA, 0.0F, -1.5708F, 0.0F);
    this.LEG_LEFT = new ModelRenderer(this);
    this.LEG_LEFT.func_78793_a(0.0F, -12.0F, -2.0F);
    this.SANTA.func_78792_a(this.LEG_LEFT);
    setRotationAngle(this.LEG_LEFT, -0.3491F, 0.0F, 1.2217F);
    this.LEG_LEFT.field_78804_l.add(new ModelBox(this.LEG_LEFT, 18, 48, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F));
    this.LEG_RIGHT = new ModelRenderer(this);
    this.LEG_RIGHT.func_78793_a(0.0F, -12.0F, 2.0F);
    this.SANTA.func_78792_a(this.LEG_RIGHT);
    setRotationAngle(this.LEG_RIGHT, 0.3491F, 0.0F, 1.2217F);
    this.LEG_RIGHT.field_78804_l.add(new ModelBox(this.LEG_RIGHT, 48, 0, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F));
    this.BODY = new ModelRenderer(this);
    this.BODY.func_78793_a(0.0F, -12.0F, 0.0F);
    this.SANTA.func_78792_a(this.BODY);
    setRotationAngle(this.BODY, 0.0F, -0.48F, 0.0F);
    this.BODY.field_78804_l.add(new ModelBox(this.BODY, 0, 36, -3.0F, -9.0F, -4.0F, 1, 9, 8, 0.0F));
    this.BODY.field_78804_l.add(new ModelBox(this.BODY, 20, 28, -2.0F, -12.0F, -4.0F, 4, 12, 8, 0.0F));
    this.BODY.field_78804_l.add(new ModelBox(this.BODY, 0, 16, 2.0F, -12.0F, -4.0F, 6, 12, 8, 0.0F));
    this.HEAD = new ModelRenderer(this);
    this.HEAD.func_78793_a(0.0F, -12.0F, 0.0F);
    this.BODY.func_78792_a(this.HEAD);
    setRotationAngle(this.HEAD, 0.0F, -0.2182F, 0.0F);
    this.HEAD.field_78804_l.add(new ModelBox(this.HEAD, 24, 8, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F));
    this.HEAD.field_78804_l.add(new ModelBox(this.HEAD, 0, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.25F));
    this.HEAD.field_78804_l.add(new ModelBox(this.HEAD, 0, 0, 4.0F, -4.0F, -1.0F, 1, 4, 3, 0.0F));
    this.ARM_RIGHT = new ModelRenderer(this);
    this.ARM_RIGHT.func_78793_a(2.0F, -10.0F, 4.0F);
    this.BODY.func_78792_a(this.ARM_RIGHT);
    setRotationAngle(this.ARM_RIGHT, 1.9218F, 0.7351F, -1.1862F);
    this.ARM_RIGHT.field_78804_l.add(new ModelBox(this.ARM_RIGHT, 40, 44, -2.0F, 0.0F, 0.0F, 4, 12, 4, 0.0F));
    this.ARM_LEFT = new ModelRenderer(this);
    this.ARM_LEFT.func_78793_a(0.0F, -12.0F, -4.0F);
    this.BODY.func_78792_a(this.ARM_LEFT);
    setRotationAngle(this.ARM_LEFT, -0.3198F, 0.422F, 0.8909F);
    this.ARM_LEFT.field_78804_l.add(new ModelBox(this.ARM_LEFT, 44, 24, -2.0F, 0.0F, -4.0F, 4, 12, 4, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.SANTA.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\models\christmas\ModelSantaClausMockup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */