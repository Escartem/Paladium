package fr.paladium.palamod.modules.back2future.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelElytra extends ModelBiped {
  private ModelRenderer wingRight;
  
  private ModelRenderer wingLeft;
  
  public ModelElytra() {
    this.wingLeft = new ModelRenderer((ModelBase)this, 22, 0);
    this.wingLeft.func_78790_a(-10.0F, 0.0F, 0.0F, 10, 20, 2, 1.0F);
    this.wingRight = new ModelRenderer((ModelBase)this, 22, 0);
    this.wingRight.field_78809_i = true;
    this.wingRight.func_78790_a(0.0F, 0.0F, 0.0F, 10, 20, 2, 1.0F);
  }
  
  public void func_78088_a(Entity entity, float f0, float f1, float f2, float f3, float f4, float f5) {
    this.wingLeft.func_78785_a(f5);
    this.wingRight.func_78785_a(f5);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\model\ModelElytra.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */