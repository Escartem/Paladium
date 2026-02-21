package fr.paladium.palamod.modules.paladium.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class HalloweenHatModel extends ModelBiped {
  private final ModelRenderer BONE;
  
  private final ModelRenderer HEAD;
  
  private final ModelRenderer MOUTH;
  
  private final ModelRenderer MOUTH_PART_RIGHT;
  
  private final ModelRenderer MOUTH_PART_LEFT;
  
  private final ModelRenderer LEGS;
  
  private final ModelRenderer LEGS_RIGHT;
  
  private final ModelRenderer LEG_RIGHT_ONE;
  
  private final ModelRenderer LEG_RIGHT_ONE_PART;
  
  private final ModelRenderer LEG_RIGHT_TWO;
  
  private final ModelRenderer LEG_RIGHT_TWO_PART;
  
  private final ModelRenderer LEG_RIGHT_THREE;
  
  private final ModelRenderer LEG_RIGHT_THREE_PART;
  
  private final ModelRenderer LEG_RIGHT_FOUR;
  
  private final ModelRenderer LEG_RIGHT_FOUR_PART;
  
  private final ModelRenderer LEGS_LEFT;
  
  private final ModelRenderer LEG_LEFT_ONE;
  
  private final ModelRenderer LEG_LEFT_ONE_PART;
  
  private final ModelRenderer LEG_LEFT_TWO;
  
  private final ModelRenderer LEG_LEFT_TWO_PART;
  
  private final ModelRenderer LEG_LEFT_THREE;
  
  private final ModelRenderer LEG_LEFT_THREE_PART;
  
  private final ModelRenderer LEG_LEFT_FOUR;
  
  private final ModelRenderer LEG_LEFT_FOUR_PART;
  
  public HalloweenHatModel() {
    this.field_78090_t = 32;
    this.field_78089_u = 32;
    this.BONE = new ModelRenderer((ModelBase)this);
    this.BONE.func_78793_a(0.0F, 0.0F, 0.0F);
    this.HEAD = new ModelRenderer((ModelBase)this);
    this.HEAD.func_78793_a(8.25F, -5.5F, -12.0F);
    this.BONE.func_78792_a(this.HEAD);
    this.HEAD.field_78804_l.add(new ModelBox(this.HEAD, 0, 21, -13.25F, -2.5F, 9.0F, 1, 3, 8, 0.0F));
    this.HEAD.field_78804_l.add(new ModelBox(this.HEAD, 0, 21, -4.25F, -2.5F, 9.0F, 1, 3, 8, 0.0F));
    this.HEAD.field_78804_l.add(new ModelBox(this.HEAD, 0, 16, -12.25F, -2.5F, 17.0F, 8, 3, 1, 0.0F));
    this.HEAD.field_78804_l.add(new ModelBox(this.HEAD, 0, 0, -12.25F, -2.5F, 7.6F, 8, 5, 1, 0.0F));
    this.HEAD.field_78804_l.add(new ModelBox(this.HEAD, 18, 0, -11.25F, -3.5F, 8.0F, 6, 1, 1, 0.0F));
    this.HEAD.field_78804_l.add(new ModelBox(this.HEAD, 18, 2, -11.25F, 2.5F, 8.0F, 6, 1, 1, 0.0F));
    this.HEAD.field_78804_l.add(new ModelBox(this.HEAD, 0, 6, -11.5F, -2.0F, 7.0F, 3, 3, 1, 0.0F));
    this.HEAD.field_78804_l.add(new ModelBox(this.HEAD, 0, 6, -8.0F, -2.0F, 7.0F, 3, 3, 1, 0.0F));
    this.MOUTH = new ModelRenderer((ModelBase)this);
    this.MOUTH.func_78793_a(8.25F, -5.5F, -12.0F);
    this.BONE.func_78792_a(this.MOUTH);
    this.MOUTH_PART_RIGHT = new ModelRenderer((ModelBase)this);
    this.MOUTH_PART_RIGHT.func_78793_a(-7.0F, 2.5F, 7.75F);
    this.MOUTH.func_78792_a(this.MOUTH_PART_RIGHT);
    setRotationAngle(this.MOUTH_PART_RIGHT, 0.0F, 0.0F, -0.3054F);
    this.MOUTH_PART_RIGHT.field_78804_l.add(new ModelBox(this.MOUTH_PART_RIGHT, 0, 10, -1.0F, -0.5F, -1.0F, 2, 3, 2, 0.0F));
    this.MOUTH_PART_LEFT = new ModelRenderer((ModelBase)this);
    this.MOUTH_PART_LEFT.func_78793_a(-9.5F, 2.5F, 7.75F);
    this.MOUTH.func_78792_a(this.MOUTH_PART_LEFT);
    setRotationAngle(this.MOUTH_PART_LEFT, 0.0F, 0.0F, 0.3054F);
    this.MOUTH_PART_LEFT.field_78804_l.add(new ModelBox(this.MOUTH_PART_LEFT, 0, 10, -1.0F, -0.5F, -1.0F, 2, 3, 2, 0.0F));
    this.LEGS = new ModelRenderer((ModelBase)this);
    this.LEGS.func_78793_a(0.0F, 0.0F, 0.0F);
    this.BONE.func_78792_a(this.LEGS);
    this.LEGS_RIGHT = new ModelRenderer((ModelBase)this);
    this.LEGS_RIGHT.func_78793_a(0.0F, 0.0F, 0.0F);
    this.LEGS.func_78792_a(this.LEGS_RIGHT);
    this.LEG_RIGHT_ONE = new ModelRenderer((ModelBase)this);
    this.LEG_RIGHT_ONE.func_78793_a(-5.0F, -7.5F, -1.5F);
    this.LEGS_RIGHT.func_78792_a(this.LEG_RIGHT_ONE);
    setRotationAngle(this.LEG_RIGHT_ONE, -1.2217F, -0.5672F, 0.9599F);
    this.LEG_RIGHT_ONE.field_78804_l.add(new ModelBox(this.LEG_RIGHT_ONE, 22, 5, -4.0F, -0.5F, -0.5F, 4, 1, 1, 0.0F));
    this.LEG_RIGHT_ONE_PART = new ModelRenderer((ModelBase)this);
    this.LEG_RIGHT_ONE_PART.func_78793_a(-4.25F, 0.0F, -0.25F);
    this.LEG_RIGHT_ONE.func_78792_a(this.LEG_RIGHT_ONE_PART);
    setRotationAngle(this.LEG_RIGHT_ONE_PART, 0.0F, 0.0F, -0.5236F);
    this.LEG_RIGHT_ONE_PART.field_78804_l.add(new ModelBox(this.LEG_RIGHT_ONE_PART, 22, 5, -3.75F, -0.5F, -0.25F, 4, 1, 1, 0.0F));
    this.LEG_RIGHT_TWO = new ModelRenderer((ModelBase)this);
    this.LEG_RIGHT_TWO.func_78793_a(-5.0F, -7.5F, 0.5F);
    this.LEGS_RIGHT.func_78792_a(this.LEG_RIGHT_TWO);
    setRotationAngle(this.LEG_RIGHT_TWO, 0.0F, -0.1745F, 0.4363F);
    this.LEG_RIGHT_TWO.field_78804_l.add(new ModelBox(this.LEG_RIGHT_TWO, 22, 5, -4.0F, -0.5F, -0.5F, 4, 1, 1, 0.0F));
    this.LEG_RIGHT_TWO_PART = new ModelRenderer((ModelBase)this);
    this.LEG_RIGHT_TWO_PART.func_78793_a(-4.25F, 0.0F, -0.25F);
    this.LEG_RIGHT_TWO.func_78792_a(this.LEG_RIGHT_TWO_PART);
    setRotationAngle(this.LEG_RIGHT_TWO_PART, 0.0F, 0.0F, -0.5236F);
    this.LEG_RIGHT_TWO_PART.field_78804_l.add(new ModelBox(this.LEG_RIGHT_TWO_PART, 22, 5, -3.75F, -0.5F, -0.25F, 4, 1, 1, 0.0F));
    this.LEG_RIGHT_THREE = new ModelRenderer((ModelBase)this);
    this.LEG_RIGHT_THREE.func_78793_a(-5.0F, -7.5F, 2.5F);
    this.LEGS_RIGHT.func_78792_a(this.LEG_RIGHT_THREE);
    setRotationAngle(this.LEG_RIGHT_THREE, 0.0F, 0.2182F, 0.3927F);
    this.LEG_RIGHT_THREE.field_78804_l.add(new ModelBox(this.LEG_RIGHT_THREE, 22, 5, -4.0F, -0.5F, -0.5F, 4, 1, 1, 0.0F));
    this.LEG_RIGHT_THREE_PART = new ModelRenderer((ModelBase)this);
    this.LEG_RIGHT_THREE_PART.func_78793_a(-4.25F, 0.0F, -0.25F);
    this.LEG_RIGHT_THREE.func_78792_a(this.LEG_RIGHT_THREE_PART);
    setRotationAngle(this.LEG_RIGHT_THREE_PART, 0.0F, 0.0F, -0.5236F);
    this.LEG_RIGHT_THREE_PART.field_78804_l.add(new ModelBox(this.LEG_RIGHT_THREE_PART, 22, 5, -3.75F, -0.5F, -0.25F, 4, 1, 1, 0.0F));
    this.LEG_RIGHT_FOUR = new ModelRenderer((ModelBase)this);
    this.LEG_RIGHT_FOUR.func_78793_a(-5.0F, -7.5F, 4.5F);
    this.LEGS_RIGHT.func_78792_a(this.LEG_RIGHT_FOUR);
    setRotationAngle(this.LEG_RIGHT_FOUR, 0.0F, 0.5672F, 0.2182F);
    this.LEG_RIGHT_FOUR.field_78804_l.add(new ModelBox(this.LEG_RIGHT_FOUR, 22, 5, -4.0F, -0.5F, -0.5F, 4, 1, 1, 0.0F));
    this.LEG_RIGHT_FOUR_PART = new ModelRenderer((ModelBase)this);
    this.LEG_RIGHT_FOUR_PART.func_78793_a(-4.25F, 0.0F, -0.25F);
    this.LEG_RIGHT_FOUR.func_78792_a(this.LEG_RIGHT_FOUR_PART);
    setRotationAngle(this.LEG_RIGHT_FOUR_PART, 0.0F, 0.0F, -0.5236F);
    this.LEG_RIGHT_FOUR_PART.field_78804_l.add(new ModelBox(this.LEG_RIGHT_FOUR_PART, 22, 5, -3.75F, -0.5F, -0.25F, 4, 1, 1, 0.0F));
    this.LEGS_LEFT = new ModelRenderer((ModelBase)this);
    this.LEGS_LEFT.func_78793_a(0.0F, 0.0F, 0.0F);
    this.LEGS.func_78792_a(this.LEGS_LEFT);
    this.LEG_LEFT_ONE = new ModelRenderer((ModelBase)this);
    this.LEG_LEFT_ONE.func_78793_a(5.0F, -7.5F, -1.5F);
    this.LEGS_LEFT.func_78792_a(this.LEG_LEFT_ONE);
    setRotationAngle(this.LEG_LEFT_ONE, -1.2217F, 0.5672F, -0.9599F);
    this.LEG_LEFT_ONE.field_78804_l.add(new ModelBox(this.LEG_LEFT_ONE, 22, 5, 0.0F, -0.5F, -0.5F, 4, 1, 1, 0.0F));
    this.LEG_LEFT_ONE_PART = new ModelRenderer((ModelBase)this);
    this.LEG_LEFT_ONE_PART.func_78793_a(4.25F, 0.0F, -0.25F);
    this.LEG_LEFT_ONE.func_78792_a(this.LEG_LEFT_ONE_PART);
    setRotationAngle(this.LEG_LEFT_ONE_PART, 0.0F, 0.0F, 0.5236F);
    this.LEG_LEFT_ONE_PART.field_78804_l.add(new ModelBox(this.LEG_LEFT_ONE_PART, 22, 5, -0.25F, -0.5F, -0.25F, 4, 1, 1, 0.0F));
    this.LEG_LEFT_TWO = new ModelRenderer((ModelBase)this);
    this.LEG_LEFT_TWO.func_78793_a(5.0F, -7.5F, 0.5F);
    this.LEGS_LEFT.func_78792_a(this.LEG_LEFT_TWO);
    setRotationAngle(this.LEG_LEFT_TWO, 0.0F, 0.1745F, -0.4363F);
    this.LEG_LEFT_TWO.field_78804_l.add(new ModelBox(this.LEG_LEFT_TWO, 22, 5, 0.0F, -0.5F, -0.5F, 4, 1, 1, 0.0F));
    this.LEG_LEFT_TWO_PART = new ModelRenderer((ModelBase)this);
    this.LEG_LEFT_TWO_PART.func_78793_a(4.25F, 0.0F, -0.25F);
    this.LEG_LEFT_TWO.func_78792_a(this.LEG_LEFT_TWO_PART);
    setRotationAngle(this.LEG_LEFT_TWO_PART, 0.0F, 0.0F, 0.5236F);
    this.LEG_LEFT_TWO_PART.field_78804_l.add(new ModelBox(this.LEG_LEFT_TWO_PART, 22, 5, -0.25F, -0.5F, -0.25F, 4, 1, 1, 0.0F));
    this.LEG_LEFT_THREE = new ModelRenderer((ModelBase)this);
    this.LEG_LEFT_THREE.func_78793_a(5.0F, -7.5F, 2.5F);
    this.LEGS_LEFT.func_78792_a(this.LEG_LEFT_THREE);
    setRotationAngle(this.LEG_LEFT_THREE, 0.0F, -0.2182F, -0.3927F);
    this.LEG_LEFT_THREE.field_78804_l.add(new ModelBox(this.LEG_LEFT_THREE, 22, 5, 0.0F, -0.5F, -0.5F, 4, 1, 1, 0.0F));
    this.LEG_LEFT_THREE_PART = new ModelRenderer((ModelBase)this);
    this.LEG_LEFT_THREE_PART.func_78793_a(4.25F, 0.0F, -0.25F);
    this.LEG_LEFT_THREE.func_78792_a(this.LEG_LEFT_THREE_PART);
    setRotationAngle(this.LEG_LEFT_THREE_PART, 0.0F, 0.0F, 0.5236F);
    this.LEG_LEFT_THREE_PART.field_78804_l.add(new ModelBox(this.LEG_LEFT_THREE_PART, 22, 5, -0.25F, -0.5F, -0.25F, 4, 1, 1, 0.0F));
    this.LEG_LEFT_FOUR = new ModelRenderer((ModelBase)this);
    this.LEG_LEFT_FOUR.func_78793_a(5.0F, -7.5F, 4.5F);
    this.LEGS_LEFT.func_78792_a(this.LEG_LEFT_FOUR);
    setRotationAngle(this.LEG_LEFT_FOUR, 0.0F, -0.5672F, -0.2182F);
    this.LEG_LEFT_FOUR.field_78804_l.add(new ModelBox(this.LEG_LEFT_FOUR, 22, 5, 0.0F, -0.5F, -0.5F, 4, 1, 1, 0.0F));
    this.LEG_LEFT_FOUR_PART = new ModelRenderer((ModelBase)this);
    this.LEG_LEFT_FOUR_PART.func_78793_a(4.25F, 0.0F, -0.25F);
    this.LEG_LEFT_FOUR.func_78792_a(this.LEG_LEFT_FOUR_PART);
    setRotationAngle(this.LEG_LEFT_FOUR_PART, 0.0F, 0.0F, 0.5236F);
    this.LEG_LEFT_FOUR_PART.field_78804_l.add(new ModelBox(this.LEG_LEFT_FOUR_PART, 22, 5, -0.25F, -0.5F, -0.25F, 4, 1, 1, 0.0F));
    this.field_78116_c.func_78792_a(this.BONE);
  }
  
  public void func_78088_a(Entity entity, float limbSwing, float limbSwingAmount, float liveTick, float headYaw, float headPitch, float scaleFactor) {
    func_78087_a(limbSwing, limbSwingAmount, liveTick, headYaw, headPitch, scaleFactor, entity);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    GL11.glPushMatrix();
    GL11.glTranslatef(this.field_78116_c.field_78800_c * scaleFactor, this.field_78116_c.field_78797_d * scaleFactor, this.field_78116_c.field_78798_e * scaleFactor);
    if (this.field_78116_c.field_78808_h != 0.0F)
      GL11.glRotatef(this.field_78116_c.field_78808_h * 57.295776F, 0.0F, 0.0F, 1.0F); 
    if (this.field_78116_c.field_78796_g != 0.0F)
      GL11.glRotatef(this.field_78116_c.field_78796_g * 57.295776F, 0.0F, 1.0F, 0.0F); 
    if (this.field_78116_c.field_78795_f != 0.0F)
      GL11.glRotatef(this.field_78116_c.field_78795_f * 57.295776F, 1.0F, 0.0F, 0.0F); 
    this.BONE.func_78785_a(scaleFactor);
    GL11.glPopMatrix();
    GL11.glDisable(3042);
  }
  
  public void renderAll() {
    this.BONE.func_78785_a(0.0625F);
  }
  
  public void func_78087_a(float limbSwing, float limbSwingAmount, float liveTick, float headYaw, float headPitch, float scaleFactor, Entity e) {
    super.func_78087_a(limbSwing, limbSwingAmount, liveTick, headYaw, headPitch, scaleFactor, e);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\model\HalloweenHatModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */