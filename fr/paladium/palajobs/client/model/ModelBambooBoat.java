package fr.paladium.palajobs.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelBambooBoat extends ModelBase {
  public ModelRenderer[] boatSides = new ModelRenderer[5];
  
  public ModelBambooBoat() {
    this.boatSides[0] = new ModelRenderer(this, 0, 8);
    this.boatSides[1] = new ModelRenderer(this, 0, 0);
    this.boatSides[2] = new ModelRenderer(this, 0, 0);
    this.boatSides[3] = new ModelRenderer(this, 0, 0);
    this.boatSides[4] = new ModelRenderer(this, 0, 0);
    byte b0 = 24;
    byte b1 = 6;
    byte b2 = 20;
    byte b3 = 4;
    this.boatSides[0].func_78790_a((-b0 / 2), (-b2 / 2 + 2), -3.0F, b0, b2 - 4, 4, 0.0F);
    this.boatSides[0].func_78793_a(0.0F, b3, 0.0F);
    this.boatSides[1].func_78790_a((-b0 / 2 + 2), (-b1 - 1), -1.0F, b0 - 4, b1, 2, 0.0F);
    this.boatSides[1].func_78793_a((-b0 / 2 + 1), b3, 0.0F);
    this.boatSides[2].func_78790_a((-b0 / 2 + 2), (-b1 - 1), -1.0F, b0 - 4, b1, 2, 0.0F);
    this.boatSides[2].func_78793_a((b0 / 2 - 1), b3, 0.0F);
    this.boatSides[3].func_78790_a((-b0 / 2 + 2), (-b1 - 1), -1.0F, b0 - 4, b1, 2, 0.0F);
    this.boatSides[3].func_78793_a(0.0F, b3, (-b2 / 2 + 1));
    this.boatSides[4].func_78790_a((-b0 / 2 + 2), (-b1 - 1), -1.0F, b0 - 4, b1, 2, 0.0F);
    this.boatSides[4].func_78793_a(0.0F, b3, (b2 / 2 - 1));
    (this.boatSides[0]).field_78795_f = 1.5707964F;
    (this.boatSides[1]).field_78796_g = 4.712389F;
    (this.boatSides[2]).field_78796_g = 1.5707964F;
    (this.boatSides[3]).field_78796_g = 3.1415927F;
  }
  
  public void func_78088_a(Entity entity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
    for (int i = 0; i < 5; i++)
      this.boatSides[i].func_78785_a(p_78088_7_); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\client\model\ModelBambooBoat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */