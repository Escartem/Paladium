package fr.paladium.palamod.modules.alchimiste.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelExtractor extends ModelBase {
  ModelRenderer BaseContour;
  
  ModelRenderer Base;
  
  ModelRenderer Robinet;
  
  ModelRenderer LeverOne;
  
  ModelRenderer LeverTwo;
  
  ModelRenderer Exit;
  
  ModelRenderer ExitContour;
  
  public ModelExtractor() {
    this(0.0F);
  }
  
  public ModelExtractor(float par1) {
    this.BaseContour = new ModelRenderer(this, 51, 11);
    this.BaseContour.func_78787_b(64, 32);
    this.BaseContour.func_78789_a(-0.5F, -2.5F, -2.5F, 1, 5, 5);
    this.BaseContour.func_78793_a(-7.0F, 18.0F, 0.0F);
    this.Base = new ModelRenderer(this, 0, 12);
    this.Base.func_78787_b(64, 32);
    this.Base.func_78789_a(-4.5F, -2.0F, -2.0F, 9, 4, 4);
    this.Base.func_78793_a(-2.0F, 18.0F, 0.0F);
    this.Robinet = new ModelRenderer(this, 32, 8);
    this.Robinet.func_78787_b(64, 32);
    this.Robinet.func_78789_a(-0.5F, -1.0F, -0.5F, 1, 2, 1);
    this.Robinet.func_78793_a(0.0F, 15.0F, 0.0F);
    this.LeverOne = new ModelRenderer(this, 35, 16);
    this.LeverOne.func_78787_b(64, 32);
    this.LeverOne.func_78789_a(-2.0F, -0.5F, -0.5F, 4, 1, 1);
    this.LeverOne.func_78793_a(0.0F, 14.0F, 0.0F);
    this.LeverTwo = new ModelRenderer(this, 35, 11);
    this.LeverTwo.func_78787_b(64, 32);
    this.LeverTwo.func_78789_a(-0.5F, -0.5F, -2.0F, 1, 1, 4);
    this.LeverTwo.func_78793_a(0.0F, 14.0F, 0.0F);
    this.Exit = new ModelRenderer(this, 13, 13);
    this.Exit.func_78787_b(64, 32);
    this.Exit.func_78789_a(-1.5F, -2.0F, -1.5F, 3, 4, 3);
    this.Exit.func_78793_a(0.0F, 22.0F, 0.0F);
    this.ExitContour = new ModelRenderer(this, 12, 12);
    this.ExitContour.func_78787_b(64, 32);
    this.ExitContour.func_78789_a(-2.0F, -0.5F, -2.0F, 4, 1, 4);
    this.ExitContour.func_78793_a(0.0F, 24.0F, 0.0F);
  }
  
  public void renderAll() {
    this.BaseContour.field_78795_f = 0.0F;
    this.BaseContour.field_78796_g = 0.0F;
    this.BaseContour.field_78808_h = 0.0F;
    this.BaseContour.func_78791_b(0.0625F);
    this.Base.field_78795_f = 0.0F;
    this.Base.field_78796_g = 0.0F;
    this.Base.field_78808_h = 0.0F;
    this.Base.func_78791_b(0.0625F);
    this.Robinet.field_78795_f = 0.0F;
    this.Robinet.field_78796_g = 0.0F;
    this.Robinet.field_78808_h = 0.0F;
    this.Robinet.func_78791_b(0.0625F);
    this.LeverOne.field_78795_f = 0.0F;
    this.LeverOne.field_78796_g = 0.0F;
    this.LeverOne.field_78808_h = 0.0F;
    this.LeverOne.func_78791_b(0.0625F);
    this.LeverTwo.field_78795_f = 0.0F;
    this.LeverTwo.field_78796_g = 0.0F;
    this.LeverTwo.field_78808_h = 0.0F;
    this.LeverTwo.func_78791_b(0.0625F);
    this.Exit.field_78795_f = 0.0F;
    this.Exit.field_78796_g = 0.0F;
    this.Exit.field_78808_h = 0.0F;
    this.Exit.func_78791_b(0.0625F);
    this.ExitContour.field_78795_f = 0.0F;
    this.ExitContour.field_78796_g = 0.0F;
    this.ExitContour.field_78808_h = 0.0F;
    this.ExitContour.func_78791_b(0.0625F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\client\model\ModelExtractor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */