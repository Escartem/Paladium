package fr.paladium.palamod.modules.back2future.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(Side.CLIENT)
public class ModelHead extends ModelBase {
  private final ModelRenderer head;
  
  private final ModelRenderer overlay;
  
  public ModelHead() {
    this(32);
  }
  
  public ModelHead(int height) {
    this.field_78090_t = 64;
    this.field_78089_u = height;
    this.head = new ModelRenderer(this, 0, 0);
    this.overlay = new ModelRenderer(this, 32, 0);
    this.head.func_78790_a(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
    this.head.func_78793_a(0.0F, 0.0F, 0.0F);
    this.overlay.func_78790_a(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
    this.overlay.func_78793_a(0.0F, 0.0F, 0.0F);
  }
  
  public void render(float rotationY) {
    render(0.0F, rotationY);
  }
  
  public void render(float rotationX, float rotationY) {
    this.head.field_78795_f = rotationX / 57.295776F;
    this.head.field_78796_g = rotationY / 57.295776F;
    this.overlay.field_78796_g = this.head.field_78796_g;
    this.overlay.field_78795_f = this.head.field_78795_f;
    this.head.func_78785_a(0.0625F);
    this.overlay.func_78785_a(0.0625F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\model\ModelHead.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */