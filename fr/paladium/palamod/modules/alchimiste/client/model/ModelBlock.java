package fr.paladium.palamod.modules.alchimiste.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelBlock extends ModelBase {
  ModelRenderer Block;
  
  public ModelBlock() {
    this(0.0F);
  }
  
  public ModelBlock(float par1) {
    this.Block = new ModelRenderer(this, -16, -16);
    this.Block.func_78787_b(16, 16);
    this.Block.func_78789_a(-8.0F, -8.0F, -8.0F, 16, 16, 16);
    this.Block.func_78793_a(0.0F, 24.0F, 0.0F);
  }
  
  public void renderAll() {
    this.Block.field_78795_f = 0.0F;
    this.Block.field_78796_g = 0.0F;
    this.Block.field_78808_h = 0.0F;
    this.Block.func_78791_b(0.0625F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\client\model\ModelBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */