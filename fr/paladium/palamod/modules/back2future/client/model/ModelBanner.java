package fr.paladium.palamod.modules.back2future.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(Side.CLIENT)
public class ModelBanner extends ModelBase {
  public ModelRenderer bannerSlate;
  
  public ModelRenderer bannerStand;
  
  public ModelRenderer bannerTop;
  
  public ModelBanner() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.bannerSlate = new ModelRenderer(this, 0, 0);
    this.bannerSlate.func_78790_a(-10.0F, 0.0F, -2.0F, 20, 40, 1, 0.0F);
    this.bannerStand = new ModelRenderer(this, 44, 0);
    this.bannerStand.func_78790_a(-1.0F, -30.0F, -1.0F, 2, 42, 2, 0.0F);
    this.bannerTop = new ModelRenderer(this, 0, 42);
    this.bannerTop.func_78790_a(-10.0F, -32.0F, -1.0F, 20, 2, 2, 0.0F);
  }
  
  public void renderAll() {
    this.bannerSlate.field_78797_d = -32.0F;
    this.bannerSlate.func_78785_a(0.0625F);
    this.bannerStand.func_78785_a(0.0625F);
    this.bannerTop.func_78785_a(0.0625F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\model\ModelBanner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */