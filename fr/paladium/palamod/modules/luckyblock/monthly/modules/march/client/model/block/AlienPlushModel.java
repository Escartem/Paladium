package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.model.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class AlienPlushModel extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer leg_0;
  
  private final ModelRenderer leg_1;
  
  private final ModelRenderer antenna;
  
  private final ModelRenderer bb_main;
  
  public AlienPlushModel() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -5.0F, -10.0F, -6.0F, 10, 10, 10, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 7, -4.0F, -6.0F, -7.0F, 2, 2, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 7, 2.0F, -6.0F, -7.0F, 2, 2, 1, 0.0F));
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 0, -2.0F, -9.0F, -7.0F, 4, 3, 1, 0.0F));
    this.leg_0 = new ModelRenderer(this);
    this.leg_0.func_78793_a(-5.0F, -1.5F, -6.0F);
    this.bone.func_78792_a(this.leg_0);
    setRotationAngle(this.leg_0, 0.0F, 0.7854F, 0.0F);
    this.leg_0.field_78804_l.add(new ModelBox(this.leg_0, 30, 3, -1.0F, -1.5F, -3.0F, 2, 3, 4, 0.0F));
    this.leg_1 = new ModelRenderer(this);
    this.leg_1.func_78793_a(5.0F, -1.5F, -6.0F);
    this.bone.func_78792_a(this.leg_1);
    setRotationAngle(this.leg_1, 0.0F, -0.7854F, 0.0F);
    this.leg_1.field_78804_l.add(new ModelBox(this.leg_1, 30, 3, -1.0F, -1.5F, -3.0F, 2, 3, 4, 0.0F));
    this.antenna = new ModelRenderer(this);
    this.antenna.func_78793_a(0.0F, -10.0F, -1.0F);
    this.bone.func_78792_a(this.antenna);
    setRotationAngle(this.antenna, -0.6545F, 0.0F, 0.0F);
    this.antenna.field_78804_l.add(new ModelBox(this.antenna, 0, 20, -3.0F, -7.0F, 0.0F, 6, 7, 0, 0.0F));
    this.bb_main = new ModelRenderer(this);
    this.bb_main.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 16, 20, 5.0F, -5.0F, -4.0F, 2, 2, 2, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 16, 20, -7.0F, -5.0F, -4.0F, 2, 2, 2, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.bone.func_78785_a(f5);
    this.bb_main.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
  
  public void renderAll() {
    this.bone.func_78785_a(0.0625F);
    this.bb_main.func_78785_a(0.0625F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\client\model\block\AlienPlushModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */