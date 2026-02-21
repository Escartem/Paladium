package fr.paladium.palamod.modules.luckyblock.blocks.models.christmas;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSleighMockup extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer SLEIGH;
  
  private final ModelRenderer PRESENTS;
  
  private final ModelRenderer cube_r1;
  
  private final ModelRenderer cube_r2;
  
  private final ModelRenderer HULL;
  
  private final ModelRenderer BACK;
  
  private final ModelRenderer SKATE_LEFT;
  
  private final ModelRenderer cube_r3;
  
  private final ModelRenderer SKATE_LEFT_TIP;
  
  private final ModelRenderer cube_r4;
  
  private final ModelRenderer SKATE_RIGHT;
  
  private final ModelRenderer cube_r5;
  
  private final ModelRenderer SKATE_RIGHT_TIP;
  
  private final ModelRenderer cube_r6;
  
  private final ModelRenderer COUPLING;
  
  private final ModelRenderer COUPLING_RIGHT;
  
  private final ModelRenderer SEGMENT_RIGHT_1;
  
  private final ModelRenderer segment_right_link_1_r1;
  
  private final ModelRenderer SEGMENT_RIGHT_2;
  
  private final ModelRenderer segment_right_link_2_r1;
  
  private final ModelRenderer COUPLING_LEFT;
  
  private final ModelRenderer SEGMENT_LEFT_1;
  
  private final ModelRenderer segment_left_link_1_r1;
  
  private final ModelRenderer SEGMENT_LEFT_2;
  
  private final ModelRenderer segment_left_link_2_r1;
  
  public ModelSleighMockup() {
    this.field_78090_t = 256;
    this.field_78089_u = 256;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 24.0F, 0.0F);
    setRotationAngle(this.bone, 0.0F, -1.5708F, 0.0F);
    this.SLEIGH = new ModelRenderer(this);
    this.SLEIGH.func_78793_a(16.0F, 0.0F, 2.0F);
    this.bone.func_78792_a(this.SLEIGH);
    this.PRESENTS = new ModelRenderer(this);
    this.PRESENTS.func_78793_a(0.0F, 0.0F, -2.0F);
    this.SLEIGH.func_78792_a(this.PRESENTS);
    this.PRESENTS.field_78804_l.add(new ModelBox(this.PRESENTS, 148, 236, 9.0F, -18.0F, -1.5F, 10, 10, 10, 0.0F));
    this.PRESENTS.field_78804_l.add(new ModelBox(this.PRESENTS, 190, 236, 8.0F, -22.0F, -9.5F, 10, 10, 10, 0.0F));
    this.PRESENTS.field_78804_l.add(new ModelBox(this.PRESENTS, 148, 217, 2.0F, -15.0F, -9.5F, 10, 7, 10, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(7.0F, -11.0F, 4.5F);
    this.PRESENTS.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 0.0F, -0.3491F, 0.0F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 92, 244, -24.0F, -3.0F, 4.0F, 6, 6, 6, 0.0F));
    this.cube_r2 = new ModelRenderer(this);
    this.cube_r2.func_78793_a(7.0F, -11.0F, 4.5F);
    this.PRESENTS.func_78792_a(this.cube_r2);
    setRotationAngle(this.cube_r2, 0.0F, 0.3491F, 0.0F);
    this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 92, 230, 7.0F, -17.0F, -8.0F, 6, 6, 6, 0.0F));
    this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 120, 244, -4.0F, -3.0F, -3.0F, 6, 6, 6, 0.0F));
    this.HULL = new ModelRenderer(this);
    this.HULL.func_78793_a(0.0F, 0.0F, -2.0F);
    this.SLEIGH.func_78792_a(this.HULL);
    this.HULL.field_78804_l.add(new ModelBox(this.HULL, 0, 0, -17.0F, -7.0F, -11.5F, 27, 2, 22, 0.0F));
    this.HULL.field_78804_l.add(new ModelBox(this.HULL, 77, 0, -20.0F, -8.0F, -9.5F, 7, 1, 18, 0.0F));
    this.HULL.field_78804_l.add(new ModelBox(this.HULL, 131, 1, -23.0F, -14.0F, -11.5F, 3, 7, 22, 0.0F));
    this.HULL.field_78804_l.add(new ModelBox(this.HULL, 0, 75, -22.0F, -21.0F, -10.5F, 0, 7, 20, 0.0F));
    this.HULL.field_78804_l.add(new ModelBox(this.HULL, 1, 66, -20.0F, -12.0F, -11.5F, 7, 5, 2, 0.0F));
    this.HULL.field_78804_l.add(new ModelBox(this.HULL, 1, 66, -20.0F, -12.0F, 8.5F, 7, 5, 2, 0.0F));
    this.HULL.field_78804_l.add(new ModelBox(this.HULL, 0, 79, -22.0F, -21.0F, -10.5F, 23, 14, 0, 0.0F));
    this.HULL.field_78804_l.add(new ModelBox(this.HULL, 0, 79, -22.0F, -21.0F, 9.5F, 23, 14, 0, 0.0F));
    this.BACK = new ModelRenderer(this);
    this.BACK.func_78793_a(0.0F, 0.0F, -2.0F);
    this.SLEIGH.func_78792_a(this.BACK);
    this.BACK.field_78804_l.add(new ModelBox(this.BACK, 1, 48, 1.0F, -21.0F, 8.5F, 17, 14, 2, 0.0F));
    this.BACK.field_78804_l.add(new ModelBox(this.BACK, 0, 26, 1.0F, -8.0F, -9.5F, 17, 1, 18, 0.0F));
    this.BACK.field_78804_l.add(new ModelBox(this.BACK, 41, 48, 1.0F, -21.0F, -11.5F, 17, 14, 2, 0.0F));
    this.BACK.field_78804_l.add(new ModelBox(this.BACK, 81, 27, 18.0F, -23.0F, -11.5F, 2, 16, 22, 0.0F));
    this.BACK.field_78804_l.add(new ModelBox(this.BACK, 112, 29, 18.0F, -26.0F, -7.5F, 2, 3, 14, 0.0F));
    this.BACK.field_78804_l.add(new ModelBox(this.BACK, 0, 93, 19.0F, -31.0F, -11.5F, 0, 8, 11, 0.0F));
    this.BACK.field_78804_l.add(new ModelBox(this.BACK, 0, 102, 19.0F, -31.0F, -0.5F, 0, 8, 11, 0.0F));
    this.SKATE_LEFT = new ModelRenderer(this);
    this.SKATE_LEFT.func_78793_a(10.0F, 0.0F, -13.0F);
    this.SLEIGH.func_78792_a(this.SKATE_LEFT);
    setRotationAngle(this.SKATE_LEFT, 0.0F, -0.0436F, 0.0F);
    this.SKATE_LEFT.field_78804_l.add(new ModelBox(this.SKATE_LEFT, 40, 202, -38.0F, -2.0F, -3.0F, 52, 2, 7, 0.0F));
    this.SKATE_LEFT.field_78804_l.add(new ModelBox(this.SKATE_LEFT, 56, 190, 0.0F, -7.0F, -0.75F, 4, 5, 3, 0.0F));
    this.cube_r3 = new ModelRenderer(this);
    this.cube_r3.func_78793_a(-25.0F, -1.8F, -1.5F);
    this.SKATE_LEFT.func_78792_a(this.cube_r3);
    setRotationAngle(this.cube_r3, -0.5633F, 0.0702F, 0.1106F);
    this.cube_r3.field_78804_l.add(new ModelBox(this.cube_r3, 71, 190, -2.25F, -5.2F, -0.5F, 4, 6, 2, 0.0F));
    this.SKATE_LEFT_TIP = new ModelRenderer(this);
    this.SKATE_LEFT_TIP.func_78793_a(-38.0F, 0.0F, 2.0F);
    this.SKATE_LEFT.func_78792_a(this.SKATE_LEFT_TIP);
    setRotationAngle(this.SKATE_LEFT_TIP, 0.0F, 0.0F, 1.0036F);
    this.SKATE_LEFT_TIP.field_78804_l.add(new ModelBox(this.SKATE_LEFT_TIP, 0, 202, -12.0F, -2.0F, -5.0F, 12, 2, 7, 0.0F));
    this.cube_r4 = new ModelRenderer(this);
    this.cube_r4.func_78793_a(-12.0F, 0.0F, -2.0F);
    this.SKATE_LEFT_TIP.func_78792_a(this.cube_r4);
    setRotationAngle(this.cube_r4, 0.0F, 0.0F, 1.309F);
    this.cube_r4.field_78804_l.add(new ModelBox(this.cube_r4, 0, 189, -9.0F, -2.0F, -3.0F, 9, 2, 7, 0.0F));
    this.SKATE_RIGHT = new ModelRenderer(this);
    this.SKATE_RIGHT.func_78793_a(10.0F, 0.0F, 9.0F);
    this.SLEIGH.func_78792_a(this.SKATE_RIGHT);
    setRotationAngle(this.SKATE_RIGHT, 0.0F, 0.0436F, 0.0F);
    this.SKATE_RIGHT.field_78804_l.add(new ModelBox(this.SKATE_RIGHT, 40, 202, -38.0F, -2.0F, -4.0F, 52, 2, 7, 0.0F));
    this.SKATE_RIGHT.field_78804_l.add(new ModelBox(this.SKATE_RIGHT, 56, 190, 0.0F, -7.0F, -2.25F, 4, 5, 3, 0.0F));
    this.cube_r5 = new ModelRenderer(this);
    this.cube_r5.func_78793_a(-25.0F, -1.8F, 1.5F);
    this.SKATE_RIGHT.func_78792_a(this.cube_r5);
    setRotationAngle(this.cube_r5, 0.5633F, -0.0702F, 0.1106F);
    this.cube_r5.field_78804_l.add(new ModelBox(this.cube_r5, 71, 190, -2.25F, -6.2F, -2.5F, 4, 6, 2, 0.0F));
    this.SKATE_RIGHT_TIP = new ModelRenderer(this);
    this.SKATE_RIGHT_TIP.func_78793_a(-38.0F, 0.0F, -2.0F);
    this.SKATE_RIGHT.func_78792_a(this.SKATE_RIGHT_TIP);
    setRotationAngle(this.SKATE_RIGHT_TIP, 0.0F, 0.0F, 1.0036F);
    this.SKATE_RIGHT_TIP.field_78804_l.add(new ModelBox(this.SKATE_RIGHT_TIP, 0, 202, -12.0F, -2.0F, -2.0F, 12, 2, 7, 0.0F));
    this.cube_r6 = new ModelRenderer(this);
    this.cube_r6.func_78793_a(-12.0F, 0.0F, 2.0F);
    this.SKATE_RIGHT_TIP.func_78792_a(this.cube_r6);
    setRotationAngle(this.cube_r6, 0.0F, 0.0F, 1.309F);
    this.cube_r6.field_78804_l.add(new ModelBox(this.cube_r6, 0, 189, -9.0F, -2.0F, -4.0F, 9, 2, 7, 0.0F));
    this.COUPLING = new ModelRenderer(this);
    this.COUPLING.func_78793_a(-10.0F, -14.0F, 2.0F);
    this.bone.func_78792_a(this.COUPLING);
    this.COUPLING_RIGHT = new ModelRenderer(this);
    this.COUPLING_RIGHT.func_78793_a(3.0F, 0.5F, 2.0F);
    this.COUPLING.func_78792_a(this.COUPLING_RIGHT);
    setRotationAngle(this.COUPLING_RIGHT, 0.0017F, 0.1745F, 0.1741F);
    this.COUPLING_RIGHT.field_78804_l.add(new ModelBox(this.COUPLING_RIGHT, 0, 245, -42.0F, -0.5F, 0.0F, 42, 11, 0, 0.0F));
    this.COUPLING_RIGHT.field_78804_l.add(new ModelBox(this.COUPLING_RIGHT, 0, 226, -44.0F, -0.5F, -1.0F, 3, 3, 3, 0.0F));
    this.SEGMENT_RIGHT_1 = new ModelRenderer(this);
    this.SEGMENT_RIGHT_1.func_78793_a(-42.5F, 0.0F, -1.0F);
    this.COUPLING_RIGHT.func_78792_a(this.SEGMENT_RIGHT_1);
    setRotationAngle(this.SEGMENT_RIGHT_1, 0.0306F, -0.1745F, -0.1772F);
    this.SEGMENT_RIGHT_1.field_78804_l.add(new ModelBox(this.SEGMENT_RIGHT_1, 0, 234, -33.5F, -0.5F, 0.0F, 33, 11, 0, 0.0F));
    this.segment_right_link_1_r1 = new ModelRenderer(this);
    this.segment_right_link_1_r1.func_78793_a(-33.5F, 2.0F, 0.5F);
    this.SEGMENT_RIGHT_1.func_78792_a(this.segment_right_link_1_r1);
    setRotationAngle(this.segment_right_link_1_r1, 0.0F, 0.0F, 0.3491F);
    this.segment_right_link_1_r1.field_78804_l.add(new ModelBox(this.segment_right_link_1_r1, 0, 226, -1.0F, -2.5F, -1.5F, 3, 3, 3, 0.0F));
    this.SEGMENT_RIGHT_2 = new ModelRenderer(this);
    this.SEGMENT_RIGHT_2.func_78793_a(-33.5F, -0.5F, 0.0F);
    this.SEGMENT_RIGHT_1.func_78792_a(this.SEGMENT_RIGHT_2);
    this.SEGMENT_RIGHT_2.field_78804_l.add(new ModelBox(this.SEGMENT_RIGHT_2, 0, 234, -33.0F, 0.0F, 0.0F, 33, 11, 0, 0.0F));
    this.segment_right_link_2_r1 = new ModelRenderer(this);
    this.segment_right_link_2_r1.func_78793_a(-32.0F, 2.0F, 0.5F);
    this.SEGMENT_RIGHT_2.func_78792_a(this.segment_right_link_2_r1);
    setRotationAngle(this.segment_right_link_2_r1, 0.0F, 0.0F, -0.4363F);
    this.segment_right_link_2_r1.field_78804_l.add(new ModelBox(this.segment_right_link_2_r1, 0, 226, -1.0F, -2.0F, -1.5F, 3, 3, 3, 0.0F));
    this.COUPLING_LEFT = new ModelRenderer(this);
    this.COUPLING_LEFT.func_78793_a(3.0F, 0.5F, -6.0F);
    this.COUPLING.func_78792_a(this.COUPLING_LEFT);
    setRotationAngle(this.COUPLING_LEFT, -0.0016F, -0.1745F, 0.174F);
    this.COUPLING_LEFT.field_78804_l.add(new ModelBox(this.COUPLING_LEFT, 0, 245, -42.0F, -0.5F, 0.0F, 42, 11, 0, 0.0F));
    this.COUPLING_LEFT.field_78804_l.add(new ModelBox(this.COUPLING_LEFT, 0, 226, -44.0F, -0.5F, -2.0F, 3, 3, 3, 0.0F));
    this.SEGMENT_LEFT_1 = new ModelRenderer(this);
    this.SEGMENT_LEFT_1.func_78793_a(-42.5F, 0.0F, 1.0F);
    this.COUPLING_LEFT.func_78792_a(this.SEGMENT_LEFT_1);
    setRotationAngle(this.SEGMENT_LEFT_1, -0.0304F, 0.1745F, -0.1758F);
    this.SEGMENT_LEFT_1.field_78804_l.add(new ModelBox(this.SEGMENT_LEFT_1, 0, 234, -33.5F, -0.5F, 0.0F, 33, 11, 0, 0.0F));
    this.segment_left_link_1_r1 = new ModelRenderer(this);
    this.segment_left_link_1_r1.func_78793_a(-33.5F, 2.0F, -0.5F);
    this.SEGMENT_LEFT_1.func_78792_a(this.segment_left_link_1_r1);
    setRotationAngle(this.segment_left_link_1_r1, 0.0F, 0.0F, 0.3491F);
    this.segment_left_link_1_r1.field_78804_l.add(new ModelBox(this.segment_left_link_1_r1, 0, 226, -1.0F, -2.5F, -1.5F, 3, 3, 3, 0.0F));
    this.SEGMENT_LEFT_2 = new ModelRenderer(this);
    this.SEGMENT_LEFT_2.func_78793_a(-33.5F, -0.5F, 0.0F);
    this.SEGMENT_LEFT_1.func_78792_a(this.SEGMENT_LEFT_2);
    this.SEGMENT_LEFT_2.field_78804_l.add(new ModelBox(this.SEGMENT_LEFT_2, 0, 234, -33.0F, 0.0F, 0.0F, 33, 11, 0, 0.0F));
    this.segment_left_link_2_r1 = new ModelRenderer(this);
    this.segment_left_link_2_r1.func_78793_a(-32.0F, 2.0F, -0.5F);
    this.SEGMENT_LEFT_2.func_78792_a(this.segment_left_link_2_r1);
    setRotationAngle(this.segment_left_link_2_r1, 0.0F, 0.0F, -0.4363F);
    this.segment_left_link_2_r1.field_78804_l.add(new ModelBox(this.segment_left_link_2_r1, 0, 226, -1.0F, -2.0F, -1.5F, 3, 3, 3, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.bone.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\models\christmas\ModelSleighMockup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */