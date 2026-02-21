package fr.paladium.palamod.modules.alchimiste.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelLiquid extends ModelBase {
  ModelRenderer liquid;
  
  public ModelLiquid() {
    this(0.0F);
  }
  
  public ModelLiquid(float par1) {
    this.liquid = new ModelRenderer(this, 0, 0);
    this.liquid.func_78789_a(0.0F, 0.0F, 0.0F, 1, 1, 1);
    this.liquid.func_78793_a(0.5F, 0.5F, 0.5F);
  }
  
  public void renderAll() {
    this.liquid.func_78785_a(0.0625F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\client\model\ModelLiquid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */