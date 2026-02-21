package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.client.model.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMarchLuckyBlock extends ModelBase {
  private final ModelRenderer bone;
  
  private final ModelRenderer ring;
  
  public ModelMarchLuckyBlock() {
    this.field_78090_t = 128;
    this.field_78089_u = 128;
    this.bone = new ModelRenderer(this);
    this.bone.func_78793_a(0.0F, 16.0F, 0.0F);
    this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 30, -8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F));
    this.ring = new ModelRenderer(this);
    this.ring.func_78793_a(0.0F, 0.0F, 0.0F);
    this.bone.func_78792_a(this.ring);
    setRotationAngle(this.ring, 0.2182F, 0.0F, -0.3927F);
    this.ring.field_78804_l.add(new ModelBox(this.ring, 0, 0, -15.0F, 0.0F, -15.0F, 30, 0, 30, 0.0F));
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\client\model\block\ModelMarchLuckyBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */