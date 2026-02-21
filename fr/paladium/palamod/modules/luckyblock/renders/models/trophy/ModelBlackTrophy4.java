package fr.paladium.palamod.modules.luckyblock.renders.models.trophy;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBlackTrophy4 extends ModelBase {
  private final ModelRenderer trophy;
  
  private final ModelRenderer lb;
  
  public ModelBlackTrophy4() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.trophy = new ModelRenderer(this);
    this.trophy.func_78793_a(0.0F, 24.0F, 0.0F);
    this.trophy.field_78804_l.add(new ModelBox(this.trophy, 10, 32, -2.0F, -2.0F, -2.0F, 4, 2, 4, 0.0F));
    this.trophy.field_78804_l.add(new ModelBox(this.trophy, 0, 0, -1.0F, -4.0F, -1.0F, 2, 3, 2, 0.0F));
    this.trophy.field_78804_l.add(new ModelBox(this.trophy, 24, 12, -3.0F, -5.0F, -3.0F, 6, 2, 6, 0.0F));
    this.trophy.field_78804_l.add(new ModelBox(this.trophy, 0, 0, -5.0F, -7.0F, -5.0F, 10, 2, 10, 0.0F));
    this.trophy.field_78804_l.add(new ModelBox(this.trophy, 24, 28, -5.0F, -9.0F, 3.0F, 10, 2, 2, 0.0F));
    this.trophy.field_78804_l.add(new ModelBox(this.trophy, 0, 28, -5.0F, -9.0F, -5.0F, 10, 2, 2, 0.0F));
    this.trophy.field_78804_l.add(new ModelBox(this.trophy, 0, 32, -5.0F, -9.0F, -3.0F, 2, 2, 6, 0.0F));
    this.trophy.field_78804_l.add(new ModelBox(this.trophy, 30, 0, 3.0F, -9.0F, -3.0F, 2, 2, 6, 0.0F));
    this.lb = new ModelRenderer(this);
    this.lb.func_78793_a(0.0F, 10.0F, 0.0F);
    this.lb.field_78804_l.add(new ModelBox(this.lb, 0, 12, -4.0F, -3.0F, -4.0F, 8, 8, 8, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.trophy.func_78785_a(f5);
    this.lb.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\models\trophy\ModelBlackTrophy4.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */