package fr.paladium.palamod.modules.luckyblock.monthly.modules.may.client.model.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGoldenCage extends ModelBase {
  private final ModelRenderer all;
  
  private final ModelRenderer cage;
  
  private final ModelRenderer wood;
  
  private final ModelRenderer Openingdoor;
  
  private final ModelRenderer cube_r1;
  
  private final ModelRenderer Doors;
  
  public ModelGoldenCage() {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.all = new ModelRenderer(this);
    this.all.func_78793_a(0.0F, 24.0F, 0.0F);
    this.cage = new ModelRenderer(this);
    this.cage.func_78793_a(0.0F, 0.0F, 0.0F);
    this.all.func_78792_a(this.cage);
    this.wood = new ModelRenderer(this);
    this.wood.func_78793_a(0.0F, 0.0F, 0.0F);
    this.cage.func_78792_a(this.wood);
    this.wood.field_78804_l.add(new ModelBox(this.wood, 0, 0, -8.0F, -18.0F, -8.0F, 16, 2, 16, 0.0F));
    this.wood.field_78804_l.add(new ModelBox(this.wood, 0, 0, -8.0F, -2.0F, -8.0F, 16, 2, 16, 0.0F));
    this.wood.field_78804_l.add(new ModelBox(this.wood, 0, 0, 5.0F, -16.0F, -7.0F, 2, 14, 2, 0.0F));
    this.wood.field_78804_l.add(new ModelBox(this.wood, 0, 0, 5.0F, -16.0F, 5.0F, 2, 14, 2, 0.0F));
    this.wood.field_78804_l.add(new ModelBox(this.wood, 0, 0, -7.0F, -16.0F, 5.0F, 2, 14, 2, 0.0F));
    this.wood.field_78804_l.add(new ModelBox(this.wood, 0, 0, -7.0F, -16.0F, -7.0F, 2, 14, 2, 0.0F));
    this.Openingdoor = new ModelRenderer(this);
    this.Openingdoor.func_78793_a(0.0F, 0.0F, 0.0F);
    this.cage.func_78792_a(this.Openingdoor);
    this.Openingdoor.field_78804_l.add(new ModelBox(this.Openingdoor, 24, 18, -5.0F, -16.0F, 6.0F, 10, 14, 0, 0.0F));
    this.cube_r1 = new ModelRenderer(this);
    this.cube_r1.func_78793_a(0.0F, 0.0F, 0.0F);
    this.Openingdoor.func_78792_a(this.cube_r1);
    setRotationAngle(this.cube_r1, 0.0F, -1.5708F, 0.0F);
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 24, 18, -5.0F, -16.0F, -6.0F, 10, 14, 0, 0.0F));
    this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 24, 18, -5.0F, -16.0F, 6.0F, 10, 14, 0, 0.0F));
    this.Doors = new ModelRenderer(this);
    this.Doors.func_78793_a(0.0F, -16.0F, -5.95F);
    this.cage.func_78792_a(this.Doors);
    this.Doors.field_78804_l.add(new ModelBox(this.Doors, 24, 18, -5.0F, 0.0F, -0.05F, 10, 14, 0, 0.0F));
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    this.all.func_78785_a(f5);
  }
  
  public void renderAll() {
    this.all.func_78785_a(0.0625F);
  }
  
  public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\may\client\model\block\ModelGoldenCage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */