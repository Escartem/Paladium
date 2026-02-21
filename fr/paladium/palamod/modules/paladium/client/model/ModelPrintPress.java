package fr.paladium.palamod.modules.paladium.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelPrintPress extends ModelBase {
  ModelRenderer LLeg;
  
  ModelRenderer RLeg;
  
  ModelRenderer frontbrace;
  
  ModelRenderer bottombrace;
  
  ModelRenderer backbrace;
  
  ModelRenderer inkplateINK;
  
  ModelRenderer inkplate;
  
  ModelRenderer pback;
  
  ModelRenderer pibrace;
  
  ModelRenderer table;
  
  ModelRenderer plate;
  
  ModelRenderer irshaft;
  
  ModelRenderer Bbook1;
  
  ModelRenderer Bbook2;
  
  ModelRenderer Bbook3;
  
  ModelRenderer Bbook4;
  
  ModelRenderer Fbook;
  
  public ModelRenderer inkRollers;
  
  public ModelRenderer press;
  
  public ModelPrintPress() {
    this.field_78090_t = 128;
    this.field_78089_u = 64;
    func_78085_a("inkRollers.irLbar", 9, 22);
    func_78085_a("inkRollers.irRbar", 9, 22);
    func_78085_a("inkRollers.irRarm", 2, 22);
    func_78085_a("inkRollers.irLarm", 2, 22);
    func_78085_a("inkRollers.irArmWeight", 1, 35);
    func_78085_a("inkRollers.ir1", 3, 19);
    func_78085_a("inkRollers.ir2", 3, 19);
    func_78085_a("press.pressplate", 50, 0);
    func_78085_a("press.pressback", 50, 12);
    this.LLeg = new ModelRenderer(this, 31, 28);
    this.LLeg.func_78789_a(-9.0F, 4.0F, -8.0F, 3, 19, 16);
    this.LLeg.func_78793_a(0.0F, 0.0F, -3.0F);
    this.LLeg.func_78787_b(64, 32);
    this.LLeg.field_78809_i = true;
    setRotation(this.LLeg, 0.0F, 0.0F, 0.0F);
    this.RLeg = new ModelRenderer(this, 31, 28);
    this.RLeg.func_78789_a(6.0F, 4.0F, -8.0F, 3, 19, 16);
    this.RLeg.func_78793_a(0.0F, 0.0F, -3.0F);
    this.RLeg.func_78787_b(64, 32);
    this.RLeg.field_78809_i = true;
    setRotation(this.RLeg, 0.0F, 0.0F, 0.0F);
    this.frontbrace = new ModelRenderer(this, 95, 34);
    this.frontbrace.func_78789_a(-6.0F, 8.0F, -10.0F, 12, 11, 4);
    this.frontbrace.func_78793_a(0.0F, 2.0F, 0.0F);
    this.frontbrace.func_78787_b(64, 32);
    this.frontbrace.field_78809_i = true;
    setRotation(this.frontbrace, 0.0F, 0.0F, 0.0F);
    this.bottombrace = new ModelRenderer(this, 89, 50);
    this.bottombrace.func_78789_a(-6.0F, 12.0F, -2.0F, 12, 7, 7);
    this.bottombrace.func_78793_a(0.0F, 2.0F, 0.0F);
    this.bottombrace.func_78787_b(64, 32);
    this.bottombrace.field_78809_i = true;
    setRotation(this.bottombrace, 0.0F, 0.0F, 0.0F);
    this.backbrace = new ModelRenderer(this, 62, 34);
    this.backbrace.func_78789_a(-6.0F, 10.0F, 1.0F, 12, 2, 4);
    this.backbrace.func_78793_a(0.0F, 2.0F, 0.0F);
    this.backbrace.func_78787_b(64, 32);
    this.backbrace.field_78809_i = true;
    setRotation(this.backbrace, 0.0F, 0.0F, 0.0F);
    this.inkplateINK = new ModelRenderer(this, 0, 0);
    this.inkplateINK.func_78789_a(-3.5F, 0.0F, 0.5F, 7, 1, 7);
    this.inkplateINK.func_78793_a(0.0F, 0.0F, 0.0F);
    this.inkplateINK.func_78787_b(64, 32);
    this.inkplateINK.field_78809_i = true;
    setRotation(this.inkplateINK, 0.2792527F, 0.0F, 0.0F);
    this.inkplate = new ModelRenderer(this, 0, 9);
    this.inkplate.func_78789_a(-3.5F, 0.0F, 0.5F, 7, 1, 7);
    this.inkplate.func_78793_a(0.0F, 0.0F, 0.0F);
    this.inkplate.func_78787_b(64, 32);
    this.inkplate.field_78809_i = true;
    setRotation(this.inkplate, 0.2792527F, 0.0F, 0.0F);
    this.pback = new ModelRenderer(this, 73, 0);
    this.pback.func_78789_a(-5.0F, 2.0F, 0.0F, 10, 12, 1);
    this.pback.func_78793_a(0.0F, 0.0F, 0.0F);
    this.pback.func_78787_b(64, 32);
    this.pback.field_78809_i = true;
    setRotation(this.pback, 0.2792527F, 0.0F, 0.0F);
    this.pibrace = new ModelRenderer(this, 37, 0);
    this.pibrace.func_78789_a(-1.0F, 1.0F, 1.0F, 2, 6, 4);
    this.pibrace.func_78793_a(0.0F, 0.0F, 0.0F);
    this.pibrace.func_78787_b(64, 32);
    this.pibrace.field_78809_i = true;
    setRotation(this.pibrace, 0.2792527F, 0.0F, 0.0F);
    this.table = new ModelRenderer(this, 70, 24);
    this.table.func_78789_a(-10.0F, 0.0F, -13.0F, 20, 1, 8);
    this.table.func_78793_a(0.0F, 3.0F, 0.0F);
    this.table.func_78787_b(64, 32);
    this.table.field_78809_i = true;
    setRotation(this.table, 0.0F, 0.0F, 0.0F);
    this.plate = new ModelRenderer(this, 77, 14);
    this.plate.func_78789_a(-4.0F, 3.0F, -1.0F, 8, 8, 1);
    this.plate.func_78793_a(0.0F, 0.0F, 0.0F);
    this.plate.func_78787_b(64, 32);
    this.plate.field_78809_i = true;
    setRotation(this.plate, 0.2792527F, 0.0F, 0.0F);
    this.irshaft = new ModelRenderer(this, 2, 42);
    this.irshaft.func_78789_a(-5.0F, 0.0F, 0.0F, 10, 1, 1);
    this.irshaft.func_78793_a(0.0F, 4.0F, 5.0F);
    this.irshaft.func_78787_b(64, 32);
    this.irshaft.field_78809_i = true;
    setRotation(this.irshaft, 0.0F, 0.0F, 0.0F);
    this.Bbook1 = new ModelRenderer(this, 96, 0);
    this.Bbook1.func_78789_a(-9.0F, -2.0F, -14.0F, 6, 2, 9);
    this.Bbook1.func_78793_a(0.0F, 3.0F, 0.0F);
    this.Bbook1.func_78787_b(64, 32);
    this.Bbook1.field_78809_i = true;
    setRotation(this.Bbook1, 0.0F, 0.0872665F, 0.0F);
    this.Bbook2 = new ModelRenderer(this, 96, 0);
    this.Bbook2.func_78789_a(-10.0F, -4.0F, -13.0F, 6, 2, 9);
    this.Bbook2.func_78793_a(0.0F, 3.0F, 0.0F);
    this.Bbook2.func_78787_b(64, 32);
    this.Bbook2.field_78809_i = true;
    setRotation(this.Bbook2, 0.0F, -0.0174533F, 0.0F);
    this.Bbook3 = new ModelRenderer(this, 96, 0);
    this.Bbook3.func_78789_a(-11.0F, -6.0F, -13.0F, 6, 2, 9);
    this.Bbook3.func_78793_a(0.0F, 3.0F, 0.0F);
    this.Bbook3.func_78787_b(64, 32);
    this.Bbook3.field_78809_i = true;
    setRotation(this.Bbook3, 0.0F, -0.122173F, 0.0F);
    this.Bbook4 = new ModelRenderer(this, 96, 0);
    this.Bbook4.func_78789_a(-9.0F, -8.0F, -14.0F, 6, 2, 9);
    this.Bbook4.func_78793_a(0.0F, 3.0F, 0.0F);
    this.Bbook4.func_78787_b(64, 32);
    this.Bbook4.field_78809_i = true;
    setRotation(this.Bbook4, 0.0F, 0.0872665F, 0.0F);
    this.Fbook = new ModelRenderer(this, 96, 12);
    this.Fbook.func_78789_a(3.0F, -2.0F, -14.0F, 6, 2, 9);
    this.Fbook.func_78793_a(0.0F, 3.0F, 0.0F);
    this.Fbook.func_78787_b(64, 32);
    this.Fbook.field_78809_i = true;
    setRotation(this.Fbook, 0.0F, -0.0349066F, 0.0F);
    this.inkRollers = new ModelRenderer(this, "inkRollers");
    this.inkRollers.func_78793_a(0.0F, 4.0F, 6.0F);
    setRotation(this.inkRollers, 0.0F, 0.0F, 0.0F);
    this.inkRollers.field_78809_i = true;
    this.inkRollers.func_78786_a("irLbar", -6.0F, -7.0F, -2.5F, 1, 1, 4);
    this.inkRollers.func_78786_a("irRbar", 5.0F, -7.0F, -2.5F, 1, 1, 4);
    this.inkRollers.func_78786_a("irRarm", 5.0F, -6.0F, -1.5F, 1, 10, 2);
    this.inkRollers.func_78786_a("irLarm", -6.0F, -6.0F, -1.5F, 1, 10, 2);
    this.inkRollers.func_78786_a("irArmWeight", -5.0F, 2.0F, -2.0F, 10, 3, 3);
    this.inkRollers.func_78786_a("ir1", -5.0F, -7.0F, 0.0F, 10, 1, 1);
    this.inkRollers.func_78786_a("ir2", -5.0F, -7.0F, -2.0F, 10, 1, 1);
    this.press = new ModelRenderer(this, "press");
    this.press.func_78793_a(0.0F, 15.5F, 1.0F);
    setRotation(this.press, 0.0F, 0.0F, 0.0F);
    this.press.field_78809_i = true;
    this.press.func_78786_a("pressplate", -5.0F, -14.0F, 0.5F, 10, 10, 1);
    this.press.func_78786_a("pressback", -4.0F, -12.0F, -2.5F, 8, 8, 3);
  }
  
  public static float scale = 0.05F;
  
  public void renderMain() {
    this.LLeg.func_78785_a(scale);
    this.RLeg.func_78785_a(scale);
    this.frontbrace.func_78785_a(scale);
    this.bottombrace.func_78785_a(scale);
    this.backbrace.func_78785_a(scale);
    this.pibrace.func_78785_a(scale);
    this.pback.func_78785_a(scale);
    this.table.func_78785_a(scale);
    this.irshaft.func_78785_a(scale);
  }
  
  public void renderInkPlateClean() {
    this.inkplate.func_78785_a(scale);
  }
  
  public void renderInkPlateInk() {
    this.inkplateINK.func_78785_a(scale);
  }
  
  public void renderEmptyBooks(int books) {
    if (books >= 1)
      this.Bbook1.func_78785_a(scale); 
    if (books >= 2)
      this.Bbook2.func_78785_a(scale); 
    if (books >= 3)
      this.Bbook3.func_78785_a(scale); 
    if (books >= 4)
      this.Bbook4.func_78785_a(scale); 
  }
  
  public void renderBook() {
    this.Fbook.func_78785_a(scale);
  }
  
  public void renderPlate() {
    this.plate.func_78785_a(scale);
  }
  
  public void renderPress() {
    this.press.func_78785_a(scale);
  }
  
  public void renderRollers() {
    this.inkRollers.func_78785_a(scale);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.field_78795_f = x;
    model.field_78796_g = y;
    model.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\model\ModelPrintPress.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */