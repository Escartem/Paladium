package fr.paladium.palamod.modules.paladium.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelTypeset extends ModelBase {
  ModelRenderer blPlate;
  
  ModelRenderer base;
  
  ModelRenderer tlc1;
  
  ModelRenderer trc1;
  
  ModelRenderer tbc1;
  
  ModelRenderer ttc1;
  
  ModelRenderer ttc2;
  
  ModelRenderer tlc2;
  
  ModelRenderer tbc2;
  
  ModelRenderer trc2;
  
  ModelRenderer ttc3;
  
  ModelRenderer trc3;
  
  ModelRenderer tbc3;
  
  ModelRenderer tlc3;
  
  ModelRenderer ttc4;
  
  ModelRenderer trc4;
  
  ModelRenderer tbc4;
  
  ModelRenderer tlc4;
  
  ModelRenderer topedge;
  
  ModelRenderer buttomedge;
  
  ModelRenderer mid1edge;
  
  ModelRenderer mid2edge;
  
  ModelRenderer leftedge;
  
  ModelRenderer rightedge;
  
  ModelRenderer bcedge;
  
  ModelRenderer m2edge;
  
  ModelRenderer m3edge;
  
  ModelRenderer m4edge;
  
  ModelRenderer m1edge;
  
  ModelRenderer trcedge;
  
  ModelRenderer tlcedge;
  
  ModelRenderer mcb1edge;
  
  ModelRenderer mcb2edge;
  
  ModelRenderer mcb3edge;
  
  ModelRenderer mcb4edge;
  
  ModelRenderer tl1edge;
  
  ModelRenderer tl2edge;
  
  ModelRenderer tr1edge;
  
  ModelRenderer tr2edge;
  
  ModelRenderer tlmc2edge;
  
  ModelRenderer tlmc1edge;
  
  ModelRenderer trmc2edge;
  
  ModelRenderer trmc1edge;
  
  ModelRenderer book;
  
  ModelRenderer brPlate;
  
  ModelRenderer blcRight;
  
  ModelRenderer blcLeft;
  
  ModelRenderer blcTop;
  
  ModelRenderer blcBottom;
  
  private float scale = 0.020833334F;
  
  public ModelTypeset() {
    this.field_78090_t = 128;
    this.field_78089_u = 64;
    this.blPlate = new ModelRenderer(this, 50, 0);
    this.blPlate.func_78789_a(-19.0F, 0.0F, -18.0F, 14, 2, 10);
    this.blPlate.func_78793_a(0.0F, 0.0F, 0.0F);
    this.blPlate.func_78787_b(128, 64);
    this.blPlate.field_78809_i = true;
    setRotation(this.blPlate, 0.0F, 0.0F, 0.0F);
    this.base = new ModelRenderer(this, 0, 0);
    this.base.func_78789_a(-24.0F, 2.0F, -23.0F, 48, 42, 48);
    this.base.func_78793_a(0.0F, 0.0F, 0.0F);
    this.base.func_78787_b(256, 128);
    this.base.field_78809_i = true;
    setRotation(this.base, 0.0F, 0.0F, 0.0F);
    this.tlc1 = new ModelRenderer(this, 51, 20);
    this.tlc1.func_78789_a(-7.0F, 0.0F, 10.0F, 2, 2, 10);
    this.tlc1.func_78793_a(0.0F, 0.0F, 0.0F);
    this.tlc1.func_78787_b(128, 64);
    this.tlc1.field_78809_i = true;
    setRotation(this.tlc1, 0.0F, 0.0F, 0.0F);
    this.trc1 = new ModelRenderer(this, 51, 20);
    this.trc1.func_78789_a(5.0F, 0.0F, 10.0F, 2, 2, 10);
    this.trc1.func_78793_a(0.0F, 0.0F, 0.0F);
    this.trc1.func_78787_b(128, 64);
    this.trc1.field_78809_i = true;
    setRotation(this.trc1, 0.0F, 0.0F, 0.0F);
    this.tbc1 = new ModelRenderer(this, 74, 26);
    this.tbc1.func_78789_a(-5.0F, 0.0F, 10.0F, 10, 2, 2);
    this.tbc1.func_78793_a(0.0F, 0.0F, 0.0F);
    this.tbc1.func_78787_b(128, 64);
    this.tbc1.field_78809_i = true;
    setRotation(this.tbc1, 0.0F, 0.0F, 0.0F);
    this.ttc1 = new ModelRenderer(this, 74, 26);
    this.ttc1.func_78789_a(-5.0F, 0.0F, 18.0F, 10, 2, 2);
    this.ttc1.func_78793_a(0.0F, 0.0F, 0.0F);
    this.ttc1.func_78787_b(128, 64);
    this.ttc1.field_78809_i = true;
    setRotation(this.ttc1, 0.0F, 0.0F, 0.0F);
    this.ttc2 = new ModelRenderer(this, 74, 26);
    this.ttc2.func_78789_a(-6.0F, -2.0F, 19.0F, 10, 2, 2);
    this.ttc2.func_78793_a(0.0F, 0.0F, 0.0F);
    this.ttc2.func_78787_b(128, 64);
    this.ttc2.field_78809_i = true;
    setRotation(this.ttc2, 0.0F, 0.0698132F, 0.0F);
    this.tlc2 = new ModelRenderer(this, 51, 20);
    this.tlc2.func_78789_a(-8.0F, -2.0F, 11.0F, 2, 2, 10);
    this.tlc2.func_78793_a(0.0F, 0.0F, 0.0F);
    this.tlc2.func_78787_b(128, 64);
    this.tlc2.field_78809_i = true;
    setRotation(this.tlc2, 0.0F, 0.0698132F, 0.0F);
    this.tbc2 = new ModelRenderer(this, 74, 26);
    this.tbc2.func_78789_a(-6.0F, -2.0F, 11.0F, 10, 2, 2);
    this.tbc2.func_78793_a(0.0F, 0.0F, 0.0F);
    this.tbc2.func_78787_b(128, 64);
    this.tbc2.field_78809_i = true;
    setRotation(this.tbc2, 0.0F, 0.0698132F, 0.0F);
    this.trc2 = new ModelRenderer(this, 51, 20);
    this.trc2.func_78789_a(4.0F, -2.0F, 11.0F, 2, 2, 10);
    this.trc2.func_78793_a(0.0F, 0.0F, 0.0F);
    this.trc2.func_78787_b(128, 64);
    this.trc2.field_78809_i = true;
    setRotation(this.trc2, 0.0F, 0.0698132F, 0.0F);
    this.ttc3 = new ModelRenderer(this, 74, 26);
    this.ttc3.func_78789_a(-4.0F, -4.0F, 19.0F, 10, 2, 2);
    this.ttc3.func_78793_a(0.0F, 0.0F, 0.0F);
    this.ttc3.func_78787_b(128, 64);
    this.ttc3.field_78809_i = true;
    setRotation(this.ttc3, 0.0F, -0.0349066F, 0.0F);
    this.trc3 = new ModelRenderer(this, 51, 20);
    this.trc3.func_78789_a(6.0F, -4.0F, 11.0F, 2, 2, 10);
    this.trc3.func_78793_a(0.0F, 0.0F, 0.0F);
    this.trc3.func_78787_b(128, 64);
    this.trc3.field_78809_i = true;
    setRotation(this.trc3, 0.0F, -0.0349066F, 0.0F);
    this.tbc3 = new ModelRenderer(this, 74, 26);
    this.tbc3.func_78789_a(-4.0F, -4.0F, 11.0F, 10, 2, 2);
    this.tbc3.func_78793_a(0.0F, 0.0F, 0.0F);
    this.tbc3.func_78787_b(128, 64);
    this.tbc3.field_78809_i = true;
    setRotation(this.tbc3, 0.0F, -0.0349066F, 0.0F);
    this.tlc3 = new ModelRenderer(this, 51, 20);
    this.tlc3.func_78789_a(-6.0F, -4.0F, 11.0F, 2, 2, 10);
    this.tlc3.func_78793_a(0.0F, 0.0F, 0.0F);
    this.tlc3.func_78787_b(128, 64);
    this.tlc3.field_78809_i = true;
    setRotation(this.tlc3, 0.0F, -0.0349066F, 0.0F);
    this.ttc4 = new ModelRenderer(this, 74, 26);
    this.ttc4.func_78789_a(-5.0F, -6.0F, 18.0F, 10, 2, 2);
    this.ttc4.func_78793_a(0.0F, 0.0F, 0.0F);
    this.ttc4.func_78787_b(128, 64);
    this.ttc4.field_78809_i = true;
    setRotation(this.ttc4, 0.0F, 0.0174533F, 0.0F);
    this.trc4 = new ModelRenderer(this, 51, 20);
    this.trc4.func_78789_a(5.0F, -6.0F, 10.0F, 2, 2, 10);
    this.trc4.func_78793_a(0.0F, 0.0F, 0.0F);
    this.trc4.func_78787_b(128, 64);
    this.trc4.field_78809_i = true;
    setRotation(this.trc4, 0.0F, 0.0174533F, 0.0F);
    this.tbc4 = new ModelRenderer(this, 74, 26);
    this.tbc4.func_78789_a(-5.0F, -6.0F, 10.0F, 10, 2, 2);
    this.tbc4.func_78793_a(0.0F, 0.0F, 0.0F);
    this.tbc4.func_78787_b(128, 64);
    this.tbc4.field_78809_i = true;
    setRotation(this.tbc4, 0.0F, 0.0174533F, 0.0F);
    this.tlc4 = new ModelRenderer(this, 51, 20);
    this.tlc4.func_78789_a(-7.0F, -6.0F, 10.0F, 2, 2, 10);
    this.tlc4.func_78793_a(0.0F, 0.0F, 0.0F);
    this.tlc4.func_78787_b(128, 64);
    this.tlc4.field_78809_i = true;
    setRotation(this.tlc4, 0.0F, 0.0174533F, 0.0F);
    this.topedge = new ModelRenderer(this, 0, 57);
    this.topedge.func_78789_a(-24.0F, 0.0F, 24.0F, 48, 2, 1);
    this.topedge.func_78793_a(0.0F, 0.0F, 0.0F);
    this.topedge.func_78787_b(128, 64);
    this.topedge.field_78809_i = true;
    setRotation(this.topedge, 0.0F, 0.0F, 0.0F);
    this.buttomedge = new ModelRenderer(this, 0, 57);
    this.buttomedge.func_78789_a(-24.0F, 0.0F, -23.0F, 48, 2, 1);
    this.buttomedge.func_78793_a(0.0F, 0.0F, 0.0F);
    this.buttomedge.func_78787_b(128, 64);
    this.buttomedge.field_78809_i = true;
    setRotation(this.buttomedge, 0.0F, 0.0F, 0.0F);
    this.mid1edge = new ModelRenderer(this, 0, 57);
    this.mid1edge.func_78789_a(-23.0F, 0.0F, -4.0F, 46, 2, 1);
    this.mid1edge.func_78793_a(0.0F, 0.0F, 0.0F);
    this.mid1edge.func_78787_b(128, 64);
    this.mid1edge.field_78809_i = true;
    setRotation(this.mid1edge, 0.0F, 0.0F, 0.0F);
    this.mid2edge = new ModelRenderer(this, 0, 57);
    this.mid2edge.func_78789_a(-23.0F, 0.0F, 5.0F, 46, 2, 1);
    this.mid2edge.func_78793_a(0.0F, 0.0F, 0.0F);
    this.mid2edge.func_78787_b(128, 64);
    this.mid2edge.field_78809_i = true;
    setRotation(this.mid2edge, 0.0F, 0.0F, 0.0F);
    this.leftedge = new ModelRenderer(this, 0, 8);
    this.leftedge.func_78789_a(-24.0F, 0.0F, -22.0F, 1, 2, 46);
    this.leftedge.func_78793_a(0.0F, 0.0F, 0.0F);
    this.leftedge.func_78787_b(128, 64);
    this.leftedge.field_78809_i = true;
    setRotation(this.leftedge, 0.0F, 0.0F, 0.0F);
    this.rightedge = new ModelRenderer(this, 0, 8);
    this.rightedge.func_78789_a(23.0F, 0.0F, -22.0F, 1, 2, 46);
    this.rightedge.func_78793_a(0.0F, 0.0F, 0.0F);
    this.rightedge.func_78787_b(128, 64);
    this.rightedge.field_78809_i = true;
    setRotation(this.rightedge, 0.0F, 0.0F, 0.0F);
    this.bcedge = new ModelRenderer(this, 4, 22);
    this.bcedge.func_78789_a(-0.5F, 0.0F, -22.0F, 1, 2, 18);
    this.bcedge.func_78793_a(0.0F, 0.0F, 0.0F);
    this.bcedge.func_78787_b(128, 64);
    this.bcedge.field_78809_i = true;
    setRotation(this.bcedge, 0.0F, 0.0F, 0.0F);
    this.m2edge = new ModelRenderer(this, 74, 42);
    this.m2edge.func_78789_a(-3.0F, 0.0F, -3.0F, 1, 2, 8);
    this.m2edge.func_78793_a(0.0F, 0.0F, 0.0F);
    this.m2edge.func_78787_b(128, 64);
    this.m2edge.field_78809_i = true;
    setRotation(this.m2edge, 0.0F, 0.0F, 0.0F);
    this.m3edge = new ModelRenderer(this, 74, 42);
    this.m3edge.func_78789_a(2.0F, 0.0F, -3.0F, 1, 2, 8);
    this.m3edge.func_78793_a(0.0F, 0.0F, 0.0F);
    this.m3edge.func_78787_b(128, 64);
    this.m3edge.field_78809_i = true;
    setRotation(this.m3edge, 0.0F, 0.0F, 0.0F);
    this.m4edge = new ModelRenderer(this, 74, 42);
    this.m4edge.func_78789_a(7.0F, 0.0F, -3.0F, 1, 2, 8);
    this.m4edge.func_78793_a(0.0F, 0.0F, 0.0F);
    this.m4edge.func_78787_b(128, 64);
    this.m4edge.field_78809_i = true;
    setRotation(this.m4edge, 0.0F, 0.0F, 0.0F);
    this.m1edge = new ModelRenderer(this, 74, 42);
    this.m1edge.func_78789_a(-8.0F, 0.0F, -3.0F, 1, 2, 8);
    this.m1edge.func_78793_a(0.0F, 0.0F, 0.0F);
    this.m1edge.func_78787_b(128, 64);
    this.m1edge.field_78809_i = true;
    setRotation(this.m1edge, 0.0F, 0.0F, 0.0F);
    this.trcedge = new ModelRenderer(this, 4, 22);
    this.trcedge.func_78789_a(9.0F, 0.0F, 6.0F, 1, 2, 18);
    this.trcedge.func_78793_a(0.0F, 0.0F, 0.0F);
    this.trcedge.func_78787_b(128, 64);
    this.trcedge.field_78809_i = true;
    setRotation(this.trcedge, 0.0F, 0.0F, 0.0F);
    this.tlcedge = new ModelRenderer(this, 4, 22);
    this.tlcedge.func_78789_a(-10.0F, 0.0F, 6.0F, 1, 2, 18);
    this.tlcedge.func_78793_a(0.0F, 0.0F, 0.0F);
    this.tlcedge.func_78787_b(128, 64);
    this.tlcedge.field_78809_i = true;
    setRotation(this.tlcedge, 0.0F, 0.0F, 0.0F);
    this.mcb1edge = new ModelRenderer(this, 7, 47);
    this.mcb1edge.func_78789_a(-23.0F, 0.0F, -1.0F, 15, 2, 1);
    this.mcb1edge.func_78793_a(0.0F, 0.0F, 0.0F);
    this.mcb1edge.func_78787_b(128, 64);
    this.mcb1edge.field_78809_i = true;
    setRotation(this.mcb1edge, 0.0F, 0.0F, 0.0F);
    this.mcb2edge = new ModelRenderer(this, 7, 47);
    this.mcb2edge.func_78789_a(-23.0F, 0.0F, 2.0F, 15, 2, 1);
    this.mcb2edge.func_78793_a(0.0F, 0.0F, 0.0F);
    this.mcb2edge.func_78787_b(128, 64);
    this.mcb2edge.field_78809_i = true;
    setRotation(this.mcb2edge, 0.0F, 0.0F, 0.0F);
    this.mcb3edge = new ModelRenderer(this, 7, 47);
    this.mcb3edge.func_78789_a(8.0F, 0.0F, -1.0F, 15, 2, 1);
    this.mcb3edge.func_78793_a(0.0F, 0.0F, 0.0F);
    this.mcb3edge.func_78787_b(128, 64);
    this.mcb3edge.field_78809_i = true;
    setRotation(this.mcb3edge, 0.0F, 0.0F, 0.0F);
    this.mcb4edge = new ModelRenderer(this, 7, 47);
    this.mcb4edge.func_78789_a(8.0F, 0.0F, 2.0F, 15, 2, 1);
    this.mcb4edge.func_78793_a(0.0F, 0.0F, 0.0F);
    this.mcb4edge.func_78787_b(128, 64);
    this.mcb4edge.field_78809_i = true;
    setRotation(this.mcb4edge, 0.0F, 0.0F, 0.0F);
    this.tl1edge = new ModelRenderer(this, 9, 43);
    this.tl1edge.func_78789_a(-23.0F, 0.0F, 20.0F, 13, 2, 1);
    this.tl1edge.func_78793_a(0.0F, 0.0F, 0.0F);
    this.tl1edge.func_78787_b(128, 64);
    this.tl1edge.field_78809_i = true;
    setRotation(this.tl1edge, 0.0F, 0.0F, 0.0F);
    this.tl2edge = new ModelRenderer(this, 9, 43);
    this.tl2edge.func_78789_a(-23.0F, 0.0F, 16.0F, 13, 2, 1);
    this.tl2edge.func_78793_a(0.0F, 0.0F, 0.0F);
    this.tl2edge.func_78787_b(128, 64);
    this.tl2edge.field_78809_i = true;
    setRotation(this.tl2edge, 0.0F, 0.0F, 0.0F);
    this.tr1edge = new ModelRenderer(this, 9, 43);
    this.tr1edge.func_78789_a(10.0F, 0.0F, 20.0F, 13, 2, 1);
    this.tr1edge.func_78793_a(0.0F, 0.0F, 0.0F);
    this.tr1edge.func_78787_b(128, 64);
    this.tr1edge.field_78809_i = true;
    setRotation(this.tr1edge, 0.0F, 0.0F, 0.0F);
    this.tr2edge = new ModelRenderer(this, 9, 43);
    this.tr2edge.func_78789_a(10.0F, 0.0F, 16.0F, 13, 2, 1);
    this.tr2edge.func_78793_a(0.0F, 0.0F, 0.0F);
    this.tr2edge.func_78787_b(128, 64);
    this.tr2edge.field_78809_i = true;
    setRotation(this.tr2edge, 0.0F, 0.0F, 0.0F);
    this.tlmc2edge = new ModelRenderer(this, 50, 40);
    this.tlmc2edge.func_78789_a(-15.0F, 0.0F, 6.0F, 1, 2, 10);
    this.tlmc2edge.func_78793_a(0.0F, 0.0F, 0.0F);
    this.tlmc2edge.func_78787_b(128, 64);
    this.tlmc2edge.field_78809_i = true;
    setRotation(this.tlmc2edge, 0.0F, 0.0F, 0.0F);
    this.tlmc1edge = new ModelRenderer(this, 50, 40);
    this.tlmc1edge.func_78789_a(-19.0F, 0.0F, 6.0F, 1, 2, 10);
    this.tlmc1edge.func_78793_a(0.0F, 0.0F, 0.0F);
    this.tlmc1edge.func_78787_b(128, 64);
    this.tlmc1edge.field_78809_i = true;
    setRotation(this.tlmc1edge, 0.0F, 0.0F, 0.0F);
    this.trmc2edge = new ModelRenderer(this, 50, 40);
    this.trmc2edge.func_78789_a(18.0F, 0.0F, 6.0F, 1, 2, 10);
    this.trmc2edge.func_78793_a(0.0F, 0.0F, 0.0F);
    this.trmc2edge.func_78787_b(128, 64);
    this.trmc2edge.field_78809_i = true;
    setRotation(this.trmc2edge, 0.0F, 0.0F, 0.0F);
    this.trmc1edge = new ModelRenderer(this, 50, 40);
    this.trmc1edge.func_78789_a(14.0F, 0.0F, 6.0F, 1, 2, 10);
    this.trmc1edge.func_78793_a(0.0F, 0.0F, 0.0F);
    this.trmc1edge.func_78787_b(128, 64);
    this.trmc1edge.field_78809_i = true;
    setRotation(this.trmc1edge, 0.0F, 0.0F, 0.0F);
    this.brPlate = new ModelRenderer(this, 50, 0);
    this.brPlate.func_78789_a(5.0F, 0.0F, -18.0F, 14, 2, 10);
    this.brPlate.func_78793_a(0.0F, 0.0F, 0.0F);
    this.brPlate.func_78787_b(128, 64);
    this.brPlate.field_78809_i = true;
    setRotation(this.brPlate, 0.0F, 0.0F, 0.0F);
    this.blcRight = new ModelRenderer(this, 51, 20);
    this.blcRight.func_78789_a(-7.0F, 0.0F, -18.0F, 2, 2, 10);
    this.blcRight.func_78793_a(0.0F, 0.0F, 0.0F);
    this.blcRight.func_78787_b(128, 64);
    this.blcRight.field_78809_i = true;
    setRotation(this.blcRight, 0.0F, 0.0F, 0.0F);
    this.blcLeft = new ModelRenderer(this, 51, 20);
    this.blcLeft.func_78789_a(-19.0F, 0.0F, -18.0F, 2, 2, 10);
    this.blcLeft.func_78793_a(0.0F, 0.0F, 0.0F);
    this.blcLeft.func_78787_b(128, 64);
    this.blcLeft.field_78809_i = true;
    setRotation(this.blcLeft, 0.0F, 0.0F, 0.0F);
    this.blcTop = new ModelRenderer(this, 74, 26);
    this.blcTop.func_78789_a(-17.0F, 0.0F, -10.0F, 10, 2, 2);
    this.blcTop.func_78793_a(0.0F, 0.0F, 0.0F);
    this.blcTop.func_78787_b(128, 64);
    this.blcTop.field_78809_i = true;
    setRotation(this.blcTop, 0.0F, 0.0F, 0.0F);
    this.blcBottom = new ModelRenderer(this, 74, 26);
    this.blcBottom.func_78789_a(-17.0F, 0.0F, -18.0F, 10, 2, 2);
    this.blcBottom.func_78793_a(0.0F, 0.0F, 0.0F);
    this.blcBottom.func_78787_b(128, 64);
    this.blcBottom.field_78809_i = true;
    setRotation(this.blcBottom, 0.0F, 0.0F, 0.0F);
    this.book = new ModelRenderer(this, 0, 0);
    this.book.func_78789_a(-21.0F, -2.0F, -19.0F, 18, 4, 12);
    this.book.func_78793_a(0.0F, 0.0F, 0.0F);
    this.book.func_78787_b(64, 32);
    this.book.field_78809_i = true;
    setRotation(this.book, 0.0F, 0.0F, 0.0F);
  }
  
  public void renderLeftChase() {
    this.blcRight.func_78785_a(this.scale);
    this.blcLeft.func_78785_a(this.scale);
    this.blcTop.func_78785_a(this.scale);
    this.blcBottom.func_78785_a(this.scale);
  }
  
  public void renderBase() {
    this.base.func_78785_a(this.scale);
  }
  
  public void renderTop() {
    this.topedge.func_78785_a(this.scale);
    this.buttomedge.func_78785_a(this.scale);
    this.mid1edge.func_78785_a(this.scale);
    this.mid2edge.func_78785_a(this.scale);
    this.leftedge.func_78785_a(this.scale);
    this.rightedge.func_78785_a(this.scale);
    this.bcedge.func_78785_a(this.scale);
    this.m2edge.func_78785_a(this.scale);
    this.m3edge.func_78785_a(this.scale);
    this.m4edge.func_78785_a(this.scale);
    this.m1edge.func_78785_a(this.scale);
    this.trcedge.func_78785_a(this.scale);
    this.tlcedge.func_78785_a(this.scale);
    this.mcb1edge.func_78785_a(this.scale);
    this.mcb2edge.func_78785_a(this.scale);
    this.mcb3edge.func_78785_a(this.scale);
    this.mcb4edge.func_78785_a(this.scale);
    this.tl1edge.func_78785_a(this.scale);
    this.tl2edge.func_78785_a(this.scale);
    this.tr1edge.func_78785_a(this.scale);
    this.tr2edge.func_78785_a(this.scale);
    this.tlmc2edge.func_78785_a(this.scale);
    this.tlmc1edge.func_78785_a(this.scale);
    this.trmc2edge.func_78785_a(this.scale);
    this.trmc1edge.func_78785_a(this.scale);
  }
  
  public void renderBook() {
    this.book.func_78785_a(this.scale);
  }
  
  public void renderlowerLeftPlate() {
    this.blPlate.func_78785_a(this.scale);
  }
  
  public void renderlowerRightPlate() {
    this.brPlate.func_78785_a(this.scale);
  }
  
  public void renderChaseStack(int num) {
    if (num >= 1) {
      this.tlc1.func_78785_a(this.scale);
      this.trc1.func_78785_a(this.scale);
      this.tbc1.func_78785_a(this.scale);
      this.ttc1.func_78785_a(this.scale);
    } 
    if (num >= 2) {
      this.tlc2.func_78785_a(this.scale);
      this.trc2.func_78785_a(this.scale);
      this.tbc2.func_78785_a(this.scale);
      this.ttc2.func_78785_a(this.scale);
    } 
    if (num >= 3) {
      this.tlc3.func_78785_a(this.scale);
      this.trc3.func_78785_a(this.scale);
      this.tbc3.func_78785_a(this.scale);
      this.ttc3.func_78785_a(this.scale);
    } 
    if (num >= 4) {
      this.tlc4.func_78785_a(this.scale);
      this.trc4.func_78785_a(this.scale);
      this.tbc4.func_78785_a(this.scale);
      this.ttc4.func_78785_a(this.scale);
    } 
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.field_78795_f = x;
    model.field_78796_g = y;
    model.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\model\ModelTypeset.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */