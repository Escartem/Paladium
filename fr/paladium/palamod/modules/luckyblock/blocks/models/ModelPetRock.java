package fr.paladium.palamod.modules.luckyblock.blocks.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelPetRock extends ModelBase {
  private final ModelRenderer BONE;
  
  public ModelPetRock() {
    this.field_78090_t = 32;
    this.field_78089_u = 32;
    this.BONE = new ModelRenderer(this);
    this.BONE.func_78793_a(-1.0F, 24.0F, 0.0F);
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 0, 0, -3.0F, -9.0F, -3.0F, 8, 9, 4, 0.0F));
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 15, 0, -5.0F, -7.0F, -1.0F, 5, 7, 4, 0.0F));
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 15, 0, 0.0F, -7.0F, 1.0F, 3, 7, 1, 0.0F));
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 15, 0, 4.0F, -7.0F, -2.0F, 3, 7, 4, 0.0F));
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 15, 0, 2.0F, -4.0F, 0.0F, 3, 4, 4, 0.0F));
  }
  
  public void renderAll() {
    this.BONE.func_78785_a(0.0625F);
  }
  
  public void func_78088_a(Entity entity, float limbSwing, float limbSwingAmount, float liveTick, float headYaw, float headPitch, float scaleFactor) {
    func_78087_a(limbSwing, limbSwingAmount, liveTick, headYaw, headPitch, scaleFactor, entity);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    super.func_78088_a(entity, limbSwing, limbSwingAmount, liveTick, headYaw, headPitch, scaleFactor);
    this.BONE.func_78785_a(scaleFactor);
    GL11.glDisable(3042);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\models\ModelPetRock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */