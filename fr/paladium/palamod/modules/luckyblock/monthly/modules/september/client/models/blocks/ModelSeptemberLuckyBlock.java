package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.client.models.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSeptemberLuckyBlock extends ModelBase {
  private final ModelRenderer wallet;
  
  private final ModelRenderer bb_main;
  
  public ModelSeptemberLuckyBlock() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.wallet = new ModelRenderer(this);
    this.wallet.func_78793_a(10.5F, 12.6F, 0.5F);
    setRotationAngle(this.wallet, 0.2182F, 0.0F, 0.0F);
    this.bb_main = new ModelRenderer(this);
    this.bb_main.func_78793_a(0.0F, 24.0F, 0.0F);
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -8.0F, -16.0F, -8.0F, 16, 16, 16, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 4, -1.0F, -19.0F, -6.5F, 2, 3, 1, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 0, -1.0F, -19.0F, 5.5F, 2, 3, 1, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 32, -1.0F, -19.0F, -5.5F, 2, 2, 11, 0.0F));
    this.bb_main.field_78804_l.add(new ModelBox(this.bb_main, 0, 8, -9.0F, -9.0F, -1.0F, 1, 3, 2, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.wallet.func_78785_a(f5);
    this.bb_main.func_78785_a(f5);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
  
  public void renderAll() {
    this.bb_main.func_78785_a(0.0625F);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\client\models\blocks\ModelSeptemberLuckyBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */