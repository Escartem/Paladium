package fr.paladium.palamod.modules.luckyblock.renders.models.may;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDarkKnightHelmet extends ModelBiped {
  private final ModelRenderer Head;
  
  private final ModelRenderer Body;
  
  private final ModelRenderer RightArm;
  
  private final ModelRenderer LeftArm;
  
  private final ModelRenderer RightLeg;
  
  private final ModelRenderer LeftLeg;
  
  private final ModelRenderer bb_main;
  
  public ModelDarkKnightHelmet() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.Head = new ModelRenderer((ModelBase)this);
    this.Head.func_78793_a(0.0F, 0.0F, 0.0F);
    this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F));
    this.Head.field_78804_l.add(new ModelBox(this.Head, 32, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F));
    this.Body = new ModelRenderer((ModelBase)this);
    this.Body.func_78793_a(0.0F, 0.0F, 0.0F);
    this.Body.field_78804_l.add(new ModelBox(this.Body, 16, 16, -4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F));
    this.Body.field_78804_l.add(new ModelBox(this.Body, 16, 32, -4.0F, 0.0F, -2.0F, 8, 12, 4, 0.25F));
    this.RightArm = new ModelRenderer((ModelBase)this);
    this.RightArm.func_78793_a(-5.0F, 2.0F, 0.0F);
    this.RightArm.field_78804_l.add(new ModelBox(this.RightArm, 40, 16, -3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F));
    this.RightArm.field_78804_l.add(new ModelBox(this.RightArm, 40, 32, -3.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F));
    this.LeftArm = new ModelRenderer((ModelBase)this);
    this.LeftArm.func_78793_a(5.0F, 2.0F, 0.0F);
    this.LeftArm.field_78804_l.add(new ModelBox(this.LeftArm, 32, 48, -1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F));
    this.LeftArm.field_78804_l.add(new ModelBox(this.LeftArm, 48, 48, -1.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F));
    this.RightLeg = new ModelRenderer((ModelBase)this);
    this.RightLeg.func_78793_a(-1.9F, 12.0F, 0.0F);
    this.RightLeg.field_78804_l.add(new ModelBox(this.RightLeg, 0, 16, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F));
    this.RightLeg.field_78804_l.add(new ModelBox(this.RightLeg, 0, 32, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F));
    this.LeftLeg = new ModelRenderer((ModelBase)this);
    this.LeftLeg.func_78793_a(1.9F, 12.0F, 0.0F);
    this.LeftLeg.field_78804_l.add(new ModelBox(this.LeftLeg, 16, 48, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F));
    this.LeftLeg.field_78804_l.add(new ModelBox(this.LeftLeg, 0, 48, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F));
    this.bb_main = new ModelRenderer((ModelBase)this);
    this.bb_main.func_78793_a(0.0F, 24.0F, 0.0F);
    float decal = 4.2F;
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -5.0F, -13.0F + decal, -5.0F, 10, 9, 10, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 19, -1.0F, -14.0F + decal, -6.0F, 2, 1, 12, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -1.0F, -13.0F + decal, -6.0F, 2, 5, 1, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 22, -6.0F, -10.0F + decal, -6.0F, 5, 2, 1, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 19, 1.0F, -10.0F + decal, -6.0F, 5, 2, 1, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 18, 31, 5.0F, -10.0F + decal, -5.0F, 1, 2, 10, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 26, 19, -6.0F, -10.0F + decal, -5.0F, 1, 2, 10, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 30, 0, -6.0F, -10.0F + decal, 5.0F, 12, 2, 1, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 5, -1.0F, -13.0F + decal, 5.0F, 2, 3, 1, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 30, 31, -5.0F, -8.0F + decal, -5.2F, 10, 5, 0, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 22, -5.1F, -10.0F + decal, -5.0F, 0, 7, 10, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 16, 9, 5.1F, -10.0F + decal, -5.0F, 0, 7, 10, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 30, 3, -5.0F, -10.0F + decal, 5.0F, 10, 7, 0, 0.0F));
  }
  
  public void func_78088_a(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
    this.field_78114_d = this.bb_main;
    func_78087_a(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
    this.field_78114_d.func_78785_a(p_78088_7_);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\models\may\ModelDarkKnightHelmet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */