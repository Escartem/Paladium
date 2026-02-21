package fr.paladium.palamod.modules.luckyblock.blocks.models;

import fr.paladium.palamod.modules.luckyblock.tileentity.TileEntityJawsTrap;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelJawsTrap extends ModelBase {
  private final ModelRenderer BONE;
  
  private final ModelRenderer JAW1;
  
  private final ModelRenderer JAW2;
  
  public ModelJawsTrap() {
    this.field_78090_t = 32;
    this.field_78089_u = 32;
    this.BONE = new ModelRenderer(this);
    this.BONE.func_78793_a(0.0F, 24.0F, 0.0F);
    this.BONE.field_78804_l.add(new ModelBox(this.BONE, 0, 12, -1.0F, -1.0F, -5.0F, 2, 1, 10, 0.0F));
    this.JAW1 = new ModelRenderer(this);
    this.JAW1.func_78793_a(1.0F, 0.0F, 0.0F);
    this.BONE.func_78792_a(this.JAW1);
    this.JAW1.field_78804_l.add(new ModelBox(this.JAW1, 0, 0, 0.0F, -2.0F, -5.0F, 5, 2, 10, 0.0F));
    this.JAW2 = new ModelRenderer(this);
    this.JAW2.func_78793_a(-1.0F, 0.0F, 0.0F);
    this.BONE.func_78792_a(this.JAW2);
    this.JAW2.field_78795_f = 0.0F;
    this.JAW2.field_78796_g = 3.1416F;
    this.JAW2.field_78808_h = 0.0F;
    this.JAW2.field_78804_l.add(new ModelBox(this.JAW2, 0, 0, 0.0F, -2.0F, -5.0F, 5, 2, 10, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float limbSwing, float limbSwingAmount, float liveTick, float headYaw, float headPitch, float scaleFactor) {
    func_78087_a(limbSwing, limbSwingAmount, liveTick, headYaw, headPitch, scaleFactor, entity);
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    super.func_78088_a(entity, limbSwing, limbSwingAmount, liveTick, headYaw, headPitch, scaleFactor);
    this.BONE.func_78785_a(scaleFactor);
    GL11.glDisable(3042);
  }
  
  public void func_78087_a(float limbSwing, float limbSwingAmount, float liveTick, float headYaw, float headPitch, float scaleFactor, Entity e) {
    super.func_78087_a(limbSwing, limbSwingAmount, liveTick, headYaw, headPitch, scaleFactor, e);
  }
  
  public void renderAll(TileEntityJawsTrap trap) {
    if (trap == null || trap.isClosed()) {
      this.JAW1.field_78808_h = -1.44F;
      this.JAW2.field_78808_h = 1.44F;
    } else {
      this.JAW1.field_78808_h = 0.0F;
      this.JAW2.field_78808_h = 0.0F;
    } 
    this.BONE.func_78785_a(0.0625F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\models\ModelJawsTrap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */