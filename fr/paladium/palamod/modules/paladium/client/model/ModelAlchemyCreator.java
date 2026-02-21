package fr.paladium.palamod.modules.paladium.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelAlchemyCreator extends ModelBase {
  public ModelRenderer petitpod;
  
  public ModelRenderer Pied1;
  
  public ModelRenderer Pied2;
  
  public ModelRenderer Plaque;
  
  public ModelRenderer Inscriptions;
  
  public ModelRenderer table;
  
  public ModelRenderer Bord;
  
  public ModelRenderer Pied1_1;
  
  public ModelRenderer cap;
  
  public ModelRenderer bouchon;
  
  public ModelRenderer main;
  
  public ModelRenderer cap_1;
  
  public ModelRenderer main_1;
  
  public ModelRenderer main_2;
  
  public ModelRenderer main_3;
  
  public ModelRenderer main_4;
  
  public ModelRenderer main_5;
  
  public ModelRenderer main_6;
  
  public ModelRenderer cap_2;
  
  public ModelRenderer bouchon_1;
  
  public ModelRenderer main_7;
  
  public ModelRenderer cap_3;
  
  public ModelRenderer main_8;
  
  public ModelRenderer main_9;
  
  public ModelRenderer main_10;
  
  public ModelRenderer main_11;
  
  public ModelRenderer main_12;
  
  public ModelRenderer main_13;
  
  public ModelRenderer cap_4;
  
  public ModelRenderer bouchon_2;
  
  public ModelRenderer main_14;
  
  public ModelRenderer cap_5;
  
  public ModelRenderer main_15;
  
  public ModelRenderer main_16;
  
  public ModelRenderer main_17;
  
  public ModelRenderer main_18;
  
  public ModelRenderer main_19;
  
  public ModelRenderer main_20;
  
  public ModelAlchemyCreator() {
    this.field_78090_t = 82;
    this.field_78089_u = 64;
    this.main_2 = new ModelRenderer(this, 20, 26);
    this.main_2.func_78793_a(-6.4F, 10.5F, 4.3F);
    this.main_2.func_78790_a(0.0F, 0.0F, 0.0F, 6, 4, 2, 0.0F);
    this.Pied2 = new ModelRenderer(this, 0, 52);
    this.Pied2.func_78793_a(-4.0F, 19.2F, -5.4F);
    this.Pied2.func_78790_a(-1.0F, -5.0F, -1.0F, 2, 10, 2, 0.0F);
    setRotateAngle(this.Pied2, -0.1134464F, 0.0F, 0.0F);
    this.main_1 = new ModelRenderer(this, 20, 22);
    this.main_1.func_78793_a(-6.0F, 10.5F, 3.9F);
    this.main_1.func_78790_a(0.0F, 0.0F, 0.0F, 2, 4, 6, 0.0F);
    this.main_5 = new ModelRenderer(this, 20, 26);
    this.main_5.func_78793_a(-6.4F, 10.7F, 4.1F);
    this.main_5.func_78790_a(0.0F, 0.0F, 0.0F, 6, 2, 4, 0.0F);
    this.cap = new ModelRenderer(this, 0, 29);
    this.cap.func_78793_a(-6.2F, 9.9F, 4.3F);
    this.cap.func_78790_a(0.0F, 0.0F, 0.0F, 4, 1, 2, 0.0F);
    this.main = new ModelRenderer(this, 20, 22);
    this.main.func_78793_a(-6.0F, 10.3F, 4.1F);
    this.main.func_78790_a(0.0F, 0.0F, 0.0F, 2, 6, 4, 0.0F);
    this.main_3 = new ModelRenderer(this, 20, 24);
    this.main_3.func_78793_a(-6.2F, 10.5F, 4.1F);
    this.main_3.func_78790_a(0.0F, 0.0F, 0.0F, 4, 4, 4, 0.0F);
    this.main_13 = new ModelRenderer(this, 20, 24);
    this.main_13.func_78793_a(-5.9F, 10.7F, 0.6F);
    this.main_13.func_78790_a(0.0F, 0.0F, 0.0F, 4, 2, 6, 0.0F);
    this.main_11 = new ModelRenderer(this, 20, 24);
    this.main_11.func_78793_a(-5.9F, 10.3F, 1.0F);
    this.main_11.func_78790_a(0.0F, 0.0F, 0.0F, 4, 6, 2, 0.0F);
    this.main_7 = new ModelRenderer(this, 20, 22);
    this.main_7.func_78793_a(-5.7F, 10.3F, 0.8F);
    this.main_7.func_78790_a(0.0F, 0.0F, 0.0F, 2, 6, 4, 0.0F);
    this.main_15 = new ModelRenderer(this, 20, 22);
    this.main_15.func_78793_a(-5.4F, 10.5F, 2.0F);
    this.main_15.func_78790_a(0.0F, 0.0F, 0.0F, 2, 4, 6, 0.0F);
    this.cap_2 = new ModelRenderer(this, 0, 29);
    this.cap_2.func_78793_a(-5.9F, 9.9F, 1.0F);
    this.cap_2.func_78790_a(0.0F, 0.0F, 0.0F, 4, 1, 2, 0.0F);
    this.table = new ModelRenderer(this, 37, 52);
    this.table.func_78793_a(0.5F, 12.9F, -0.3F);
    this.table.func_78790_a(-7.5F, -1.5F, -0.5F, 3, 1, 8, -0.1F);
    this.Pied1_1 = new ModelRenderer(this, 22, 34);
    this.Pied1_1.func_78793_a(0.0F, 15.0F, -0.7F);
    this.Pied1_1.func_78790_a(-7.5F, -1.0F, -6.0F, 16, 1, 14, 0.0F);
    this.Bord = new ModelRenderer(this, 0, 43);
    this.Bord.func_78793_a(0.0F, 12.5F, 6.8F);
    this.Bord.func_78790_a(-7.5F, -1.5F, -0.5F, 16, 3, 1, 0.0F);
    this.cap_1 = new ModelRenderer(this, 0, 27);
    this.cap_1.func_78793_a(-6.0F, 9.9F, 4.1F);
    this.cap_1.func_78790_a(0.0F, 0.0F, 0.0F, 2, 1, 4, 0.0F);
    this.main_19 = new ModelRenderer(this, 20, 26);
    this.main_19.func_78793_a(-5.8F, 10.7F, 2.2F);
    this.main_19.func_78790_a(0.0F, 0.0F, 0.0F, 6, 2, 4, 0.0F);
    this.petitpod = new ModelRenderer(this, 0, 48);
    this.petitpod.func_78793_a(1.5F, 13.5F, 0.5F);
    this.petitpod.func_78790_a(-7.5F, -1.5F, -0.5F, 1, 2, 1, -0.15F);
    this.Plaque = new ModelRenderer(this, 0, 52);
    this.Plaque.func_78793_a(0.0F, 19.0F, 3.5F);
    this.Plaque.func_78790_a(-7.0F, -5.0F, -1.0F, 14, 10, 2, 0.0F);
    this.bouchon = new ModelRenderer(this, 12, 27);
    this.bouchon.func_78793_a(-6.0F, 9.7F, 4.3F);
    this.bouchon.func_78790_a(0.0F, 0.0F, 0.0F, 2, 3, 2, 0.0F);
    this.main_10 = new ModelRenderer(this, 20, 24);
    this.main_10.func_78793_a(-5.9F, 10.5F, 0.8F);
    this.main_10.func_78790_a(0.0F, 0.0F, 0.0F, 4, 4, 4, 0.0F);
    this.bouchon_1 = new ModelRenderer(this, 12, 27);
    this.bouchon_1.func_78793_a(-5.7F, 9.7F, 1.0F);
    this.bouchon_1.func_78790_a(0.0F, 0.0F, 0.0F, 2, 3, 2, 0.0F);
    this.main_17 = new ModelRenderer(this, 20, 24);
    this.main_17.func_78793_a(-5.6F, 10.5F, 2.2F);
    this.main_17.func_78790_a(0.0F, 0.0F, 0.0F, 4, 4, 4, 0.0F);
    this.cap_3 = new ModelRenderer(this, 0, 27);
    this.cap_3.func_78793_a(-5.7F, 9.9F, 0.8F);
    this.cap_3.func_78790_a(0.0F, 0.0F, 0.0F, 2, 1, 4, 0.0F);
    this.cap_5 = new ModelRenderer(this, 0, 27);
    this.cap_5.func_78793_a(-5.4F, 9.9F, 2.2F);
    this.cap_5.func_78790_a(0.0F, 0.0F, 0.0F, 2, 1, 4, 0.0F);
    this.main_20 = new ModelRenderer(this, 20, 24);
    this.main_20.func_78793_a(-5.6F, 10.7F, 2.0F);
    this.main_20.func_78790_a(0.0F, 0.0F, 0.0F, 4, 2, 6, 0.0F);
    this.main_9 = new ModelRenderer(this, 20, 26);
    this.main_9.func_78793_a(-6.1F, 10.5F, 1.0F);
    this.main_9.func_78790_a(0.0F, 0.0F, 0.0F, 6, 4, 2, 0.0F);
    this.bouchon_2 = new ModelRenderer(this, 12, 27);
    this.bouchon_2.func_78793_a(-5.4F, 9.7F, 2.4F);
    this.bouchon_2.func_78790_a(0.0F, 0.0F, 0.0F, 2, 3, 2, 0.0F);
    this.main_6 = new ModelRenderer(this, 20, 24);
    this.main_6.func_78793_a(-6.2F, 10.7F, 3.9F);
    this.main_6.func_78790_a(0.0F, 0.0F, 0.0F, 4, 2, 6, 0.0F);
    this.main_8 = new ModelRenderer(this, 20, 22);
    this.main_8.func_78793_a(-5.7F, 10.5F, 0.6F);
    this.main_8.func_78790_a(0.0F, 0.0F, 0.0F, 2, 4, 6, 0.0F);
    this.main_16 = new ModelRenderer(this, 20, 26);
    this.main_16.func_78793_a(-5.8F, 10.5F, 2.4F);
    this.main_16.func_78790_a(0.0F, 0.0F, 0.0F, 6, 4, 2, 0.0F);
    this.main_18 = new ModelRenderer(this, 20, 24);
    this.main_18.func_78793_a(-5.6F, 10.3F, 2.4F);
    this.main_18.func_78790_a(0.0F, 0.0F, 0.0F, 4, 6, 2, 0.0F);
    this.main_12 = new ModelRenderer(this, 20, 26);
    this.main_12.func_78793_a(-6.1F, 10.7F, 0.8F);
    this.main_12.func_78790_a(0.0F, 0.0F, 0.0F, 6, 2, 4, 0.0F);
    this.Inscriptions = new ModelRenderer(this, 0, 0);
    this.Inscriptions.func_78793_a(1.0F, 14.4F, 0.0F);
    this.Inscriptions.func_78790_a(-9.5F, -0.5F, -9.5F, 19, 1, 19, 0.0F);
    this.main_4 = new ModelRenderer(this, 20, 24);
    this.main_4.func_78793_a(-6.2F, 10.3F, 4.3F);
    this.main_4.func_78790_a(0.0F, 0.0F, 0.0F, 4, 6, 2, 0.0F);
    this.Pied1 = new ModelRenderer(this, 0, 52);
    this.Pied1.func_78793_a(4.0F, 19.2F, -5.4F);
    this.Pied1.func_78790_a(-1.0F, -5.0F, -1.0F, 2, 10, 2, 0.0F);
    setRotateAngle(this.Pied1, -0.1134464F, 0.0F, 0.0F);
    this.main_14 = new ModelRenderer(this, 20, 22);
    this.main_14.func_78793_a(-5.4F, 10.3F, 2.2F);
    this.main_14.func_78790_a(0.0F, 0.0F, 0.0F, 2, 6, 4, 0.0F);
    this.cap_4 = new ModelRenderer(this, 0, 29);
    this.cap_4.func_78793_a(-5.6F, 9.9F, 2.4F);
    this.cap_4.func_78790_a(0.0F, 0.0F, 0.0F, 4, 1, 2, 0.0F);
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    GL11.glPushMatrix();
    GL11.glTranslatef(this.main_2.field_82906_o, this.main_2.field_82908_p, this.main_2.field_82907_q);
    GL11.glTranslatef(this.main_2.field_78800_c * f5, this.main_2.field_78797_d * f5, this.main_2.field_78798_e * f5);
    GL11.glScaled(0.2D, 0.2D, 0.2D);
    GL11.glTranslatef(-this.main_2.field_82906_o, -this.main_2.field_82908_p, -this.main_2.field_82907_q);
    GL11.glTranslatef(-this.main_2.field_78800_c * f5, -this.main_2.field_78797_d * f5, -this.main_2.field_78798_e * f5);
    this.main_2.func_78785_a(f5);
    GL11.glPopMatrix();
    this.Pied2.func_78785_a(f5);
    GL11.glPushMatrix();
    GL11.glTranslatef(this.main_1.field_82906_o, this.main_1.field_82908_p, this.main_1.field_82907_q);
    GL11.glTranslatef(this.main_1.field_78800_c * f5, this.main_1.field_78797_d * f5, this.main_1.field_78798_e * f5);
    GL11.glScaled(0.2D, 0.2D, 0.2D);
    GL11.glTranslatef(-this.main_1.field_82906_o, -this.main_1.field_82908_p, -this.main_1.field_82907_q);
    GL11.glTranslatef(-this.main_1.field_78800_c * f5, -this.main_1.field_78797_d * f5, -this.main_1.field_78798_e * f5);
    this.main_1.func_78785_a(f5);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslatef(this.main_5.field_82906_o, this.main_5.field_82908_p, this.main_5.field_82907_q);
    GL11.glTranslatef(this.main_5.field_78800_c * f5, this.main_5.field_78797_d * f5, this.main_5.field_78798_e * f5);
    GL11.glScaled(0.2D, 0.2D, 0.2D);
    GL11.glTranslatef(-this.main_5.field_82906_o, -this.main_5.field_82908_p, -this.main_5.field_82907_q);
    GL11.glTranslatef(-this.main_5.field_78800_c * f5, -this.main_5.field_78797_d * f5, -this.main_5.field_78798_e * f5);
    this.main_5.func_78785_a(f5);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslatef(this.cap.field_82906_o, this.cap.field_82908_p, this.cap.field_82907_q);
    GL11.glTranslatef(this.cap.field_78800_c * f5, this.cap.field_78797_d * f5, this.cap.field_78798_e * f5);
    GL11.glScaled(0.2D, 0.2D, 0.2D);
    GL11.glTranslatef(-this.cap.field_82906_o, -this.cap.field_82908_p, -this.cap.field_82907_q);
    GL11.glTranslatef(-this.cap.field_78800_c * f5, -this.cap.field_78797_d * f5, -this.cap.field_78798_e * f5);
    this.cap.func_78785_a(f5);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslatef(this.main.field_82906_o, this.main.field_82908_p, this.main.field_82907_q);
    GL11.glTranslatef(this.main.field_78800_c * f5, this.main.field_78797_d * f5, this.main.field_78798_e * f5);
    GL11.glScaled(0.2D, 0.2D, 0.2D);
    GL11.glTranslatef(-this.main.field_82906_o, -this.main.field_82908_p, -this.main.field_82907_q);
    GL11.glTranslatef(-this.main.field_78800_c * f5, -this.main.field_78797_d * f5, -this.main.field_78798_e * f5);
    this.main.func_78785_a(f5);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslatef(this.main_3.field_82906_o, this.main_3.field_82908_p, this.main_3.field_82907_q);
    GL11.glTranslatef(this.main_3.field_78800_c * f5, this.main_3.field_78797_d * f5, this.main_3.field_78798_e * f5);
    GL11.glScaled(0.2D, 0.2D, 0.2D);
    GL11.glTranslatef(-this.main_3.field_82906_o, -this.main_3.field_82908_p, -this.main_3.field_82907_q);
    GL11.glTranslatef(-this.main_3.field_78800_c * f5, -this.main_3.field_78797_d * f5, -this.main_3.field_78798_e * f5);
    this.main_3.func_78785_a(f5);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslatef(this.main_13.field_82906_o, this.main_13.field_82908_p, this.main_13.field_82907_q);
    GL11.glTranslatef(this.main_13.field_78800_c * f5, this.main_13.field_78797_d * f5, this.main_13.field_78798_e * f5);
    GL11.glScaled(0.2D, 0.2D, 0.2D);
    GL11.glTranslatef(-this.main_13.field_82906_o, -this.main_13.field_82908_p, -this.main_13.field_82907_q);
    GL11.glTranslatef(-this.main_13.field_78800_c * f5, -this.main_13.field_78797_d * f5, -this.main_13.field_78798_e * f5);
    this.main_13.func_78785_a(f5);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslatef(this.main_11.field_82906_o, this.main_11.field_82908_p, this.main_11.field_82907_q);
    GL11.glTranslatef(this.main_11.field_78800_c * f5, this.main_11.field_78797_d * f5, this.main_11.field_78798_e * f5);
    GL11.glScaled(0.2D, 0.2D, 0.2D);
    GL11.glTranslatef(-this.main_11.field_82906_o, -this.main_11.field_82908_p, -this.main_11.field_82907_q);
    GL11.glTranslatef(-this.main_11.field_78800_c * f5, -this.main_11.field_78797_d * f5, -this.main_11.field_78798_e * f5);
    this.main_11.func_78785_a(f5);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslatef(this.main_7.field_82906_o, this.main_7.field_82908_p, this.main_7.field_82907_q);
    GL11.glTranslatef(this.main_7.field_78800_c * f5, this.main_7.field_78797_d * f5, this.main_7.field_78798_e * f5);
    GL11.glScaled(0.2D, 0.2D, 0.2D);
    GL11.glTranslatef(-this.main_7.field_82906_o, -this.main_7.field_82908_p, -this.main_7.field_82907_q);
    GL11.glTranslatef(-this.main_7.field_78800_c * f5, -this.main_7.field_78797_d * f5, -this.main_7.field_78798_e * f5);
    this.main_7.func_78785_a(f5);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslatef(this.main_15.field_82906_o, this.main_15.field_82908_p, this.main_15.field_82907_q);
    GL11.glTranslatef(this.main_15.field_78800_c * f5, this.main_15.field_78797_d * f5, this.main_15.field_78798_e * f5);
    GL11.glScaled(0.2D, 0.2D, 0.2D);
    GL11.glTranslatef(-this.main_15.field_82906_o, -this.main_15.field_82908_p, -this.main_15.field_82907_q);
    GL11.glTranslatef(-this.main_15.field_78800_c * f5, -this.main_15.field_78797_d * f5, -this.main_15.field_78798_e * f5);
    this.main_15.func_78785_a(f5);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslatef(this.cap_2.field_82906_o, this.cap_2.field_82908_p, this.cap_2.field_82907_q);
    GL11.glTranslatef(this.cap_2.field_78800_c * f5, this.cap_2.field_78797_d * f5, this.cap_2.field_78798_e * f5);
    GL11.glScaled(0.2D, 0.2D, 0.2D);
    GL11.glTranslatef(-this.cap_2.field_82906_o, -this.cap_2.field_82908_p, -this.cap_2.field_82907_q);
    GL11.glTranslatef(-this.cap_2.field_78800_c * f5, -this.cap_2.field_78797_d * f5, -this.cap_2.field_78798_e * f5);
    this.cap_2.func_78785_a(f5);
    GL11.glPopMatrix();
    this.table.func_78785_a(f5);
    this.Pied1_1.func_78785_a(f5);
    this.Bord.func_78785_a(f5);
    GL11.glPushMatrix();
    GL11.glTranslatef(this.cap_1.field_82906_o, this.cap_1.field_82908_p, this.cap_1.field_82907_q);
    GL11.glTranslatef(this.cap_1.field_78800_c * f5, this.cap_1.field_78797_d * f5, this.cap_1.field_78798_e * f5);
    GL11.glScaled(0.2D, 0.2D, 0.2D);
    GL11.glTranslatef(-this.cap_1.field_82906_o, -this.cap_1.field_82908_p, -this.cap_1.field_82907_q);
    GL11.glTranslatef(-this.cap_1.field_78800_c * f5, -this.cap_1.field_78797_d * f5, -this.cap_1.field_78798_e * f5);
    this.cap_1.func_78785_a(f5);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslatef(this.main_19.field_82906_o, this.main_19.field_82908_p, this.main_19.field_82907_q);
    GL11.glTranslatef(this.main_19.field_78800_c * f5, this.main_19.field_78797_d * f5, this.main_19.field_78798_e * f5);
    GL11.glScaled(0.2D, 0.2D, 0.2D);
    GL11.glTranslatef(-this.main_19.field_82906_o, -this.main_19.field_82908_p, -this.main_19.field_82907_q);
    GL11.glTranslatef(-this.main_19.field_78800_c * f5, -this.main_19.field_78797_d * f5, -this.main_19.field_78798_e * f5);
    this.main_19.func_78785_a(f5);
    GL11.glPopMatrix();
    this.petitpod.func_78785_a(f5);
    this.Plaque.func_78785_a(f5);
    GL11.glPushMatrix();
    GL11.glTranslatef(this.bouchon.field_82906_o, this.bouchon.field_82908_p, this.bouchon.field_82907_q);
    GL11.glTranslatef(this.bouchon.field_78800_c * f5, this.bouchon.field_78797_d * f5, this.bouchon.field_78798_e * f5);
    GL11.glScaled(0.2D, 0.2D, 0.2D);
    GL11.glTranslatef(-this.bouchon.field_82906_o, -this.bouchon.field_82908_p, -this.bouchon.field_82907_q);
    GL11.glTranslatef(-this.bouchon.field_78800_c * f5, -this.bouchon.field_78797_d * f5, -this.bouchon.field_78798_e * f5);
    this.bouchon.func_78785_a(f5);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslatef(this.main_10.field_82906_o, this.main_10.field_82908_p, this.main_10.field_82907_q);
    GL11.glTranslatef(this.main_10.field_78800_c * f5, this.main_10.field_78797_d * f5, this.main_10.field_78798_e * f5);
    GL11.glScaled(0.2D, 0.2D, 0.2D);
    GL11.glTranslatef(-this.main_10.field_82906_o, -this.main_10.field_82908_p, -this.main_10.field_82907_q);
    GL11.glTranslatef(-this.main_10.field_78800_c * f5, -this.main_10.field_78797_d * f5, -this.main_10.field_78798_e * f5);
    this.main_10.func_78785_a(f5);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslatef(this.bouchon_1.field_82906_o, this.bouchon_1.field_82908_p, this.bouchon_1.field_82907_q);
    GL11.glTranslatef(this.bouchon_1.field_78800_c * f5, this.bouchon_1.field_78797_d * f5, this.bouchon_1.field_78798_e * f5);
    GL11.glScaled(0.2D, 0.2D, 0.2D);
    GL11.glTranslatef(-this.bouchon_1.field_82906_o, -this.bouchon_1.field_82908_p, -this.bouchon_1.field_82907_q);
    GL11.glTranslatef(-this.bouchon_1.field_78800_c * f5, -this.bouchon_1.field_78797_d * f5, -this.bouchon_1.field_78798_e * f5);
    this.bouchon_1.func_78785_a(f5);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslatef(this.main_17.field_82906_o, this.main_17.field_82908_p, this.main_17.field_82907_q);
    GL11.glTranslatef(this.main_17.field_78800_c * f5, this.main_17.field_78797_d * f5, this.main_17.field_78798_e * f5);
    GL11.glScaled(0.2D, 0.2D, 0.2D);
    GL11.glTranslatef(-this.main_17.field_82906_o, -this.main_17.field_82908_p, -this.main_17.field_82907_q);
    GL11.glTranslatef(-this.main_17.field_78800_c * f5, -this.main_17.field_78797_d * f5, -this.main_17.field_78798_e * f5);
    this.main_17.func_78785_a(f5);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslatef(this.cap_3.field_82906_o, this.cap_3.field_82908_p, this.cap_3.field_82907_q);
    GL11.glTranslatef(this.cap_3.field_78800_c * f5, this.cap_3.field_78797_d * f5, this.cap_3.field_78798_e * f5);
    GL11.glScaled(0.2D, 0.2D, 0.2D);
    GL11.glTranslatef(-this.cap_3.field_82906_o, -this.cap_3.field_82908_p, -this.cap_3.field_82907_q);
    GL11.glTranslatef(-this.cap_3.field_78800_c * f5, -this.cap_3.field_78797_d * f5, -this.cap_3.field_78798_e * f5);
    this.cap_3.func_78785_a(f5);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslatef(this.cap_5.field_82906_o, this.cap_5.field_82908_p, this.cap_5.field_82907_q);
    GL11.glTranslatef(this.cap_5.field_78800_c * f5, this.cap_5.field_78797_d * f5, this.cap_5.field_78798_e * f5);
    GL11.glScaled(0.2D, 0.2D, 0.2D);
    GL11.glTranslatef(-this.cap_5.field_82906_o, -this.cap_5.field_82908_p, -this.cap_5.field_82907_q);
    GL11.glTranslatef(-this.cap_5.field_78800_c * f5, -this.cap_5.field_78797_d * f5, -this.cap_5.field_78798_e * f5);
    this.cap_5.func_78785_a(f5);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslatef(this.main_20.field_82906_o, this.main_20.field_82908_p, this.main_20.field_82907_q);
    GL11.glTranslatef(this.main_20.field_78800_c * f5, this.main_20.field_78797_d * f5, this.main_20.field_78798_e * f5);
    GL11.glScaled(0.2D, 0.2D, 0.2D);
    GL11.glTranslatef(-this.main_20.field_82906_o, -this.main_20.field_82908_p, -this.main_20.field_82907_q);
    GL11.glTranslatef(-this.main_20.field_78800_c * f5, -this.main_20.field_78797_d * f5, -this.main_20.field_78798_e * f5);
    this.main_20.func_78785_a(f5);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslatef(this.main_9.field_82906_o, this.main_9.field_82908_p, this.main_9.field_82907_q);
    GL11.glTranslatef(this.main_9.field_78800_c * f5, this.main_9.field_78797_d * f5, this.main_9.field_78798_e * f5);
    GL11.glScaled(0.2D, 0.2D, 0.2D);
    GL11.glTranslatef(-this.main_9.field_82906_o, -this.main_9.field_82908_p, -this.main_9.field_82907_q);
    GL11.glTranslatef(-this.main_9.field_78800_c * f5, -this.main_9.field_78797_d * f5, -this.main_9.field_78798_e * f5);
    this.main_9.func_78785_a(f5);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslatef(this.bouchon_2.field_82906_o, this.bouchon_2.field_82908_p, this.bouchon_2.field_82907_q);
    GL11.glTranslatef(this.bouchon_2.field_78800_c * f5, this.bouchon_2.field_78797_d * f5, this.bouchon_2.field_78798_e * f5);
    GL11.glScaled(0.2D, 0.2D, 0.2D);
    GL11.glTranslatef(-this.bouchon_2.field_82906_o, -this.bouchon_2.field_82908_p, -this.bouchon_2.field_82907_q);
    GL11.glTranslatef(-this.bouchon_2.field_78800_c * f5, -this.bouchon_2.field_78797_d * f5, -this.bouchon_2.field_78798_e * f5);
    this.bouchon_2.func_78785_a(f5);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslatef(this.main_6.field_82906_o, this.main_6.field_82908_p, this.main_6.field_82907_q);
    GL11.glTranslatef(this.main_6.field_78800_c * f5, this.main_6.field_78797_d * f5, this.main_6.field_78798_e * f5);
    GL11.glScaled(0.2D, 0.2D, 0.2D);
    GL11.glTranslatef(-this.main_6.field_82906_o, -this.main_6.field_82908_p, -this.main_6.field_82907_q);
    GL11.glTranslatef(-this.main_6.field_78800_c * f5, -this.main_6.field_78797_d * f5, -this.main_6.field_78798_e * f5);
    this.main_6.func_78785_a(f5);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslatef(this.main_8.field_82906_o, this.main_8.field_82908_p, this.main_8.field_82907_q);
    GL11.glTranslatef(this.main_8.field_78800_c * f5, this.main_8.field_78797_d * f5, this.main_8.field_78798_e * f5);
    GL11.glScaled(0.2D, 0.2D, 0.2D);
    GL11.glTranslatef(-this.main_8.field_82906_o, -this.main_8.field_82908_p, -this.main_8.field_82907_q);
    GL11.glTranslatef(-this.main_8.field_78800_c * f5, -this.main_8.field_78797_d * f5, -this.main_8.field_78798_e * f5);
    this.main_8.func_78785_a(f5);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslatef(this.main_16.field_82906_o, this.main_16.field_82908_p, this.main_16.field_82907_q);
    GL11.glTranslatef(this.main_16.field_78800_c * f5, this.main_16.field_78797_d * f5, this.main_16.field_78798_e * f5);
    GL11.glScaled(0.2D, 0.2D, 0.2D);
    GL11.glTranslatef(-this.main_16.field_82906_o, -this.main_16.field_82908_p, -this.main_16.field_82907_q);
    GL11.glTranslatef(-this.main_16.field_78800_c * f5, -this.main_16.field_78797_d * f5, -this.main_16.field_78798_e * f5);
    this.main_16.func_78785_a(f5);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslatef(this.main_18.field_82906_o, this.main_18.field_82908_p, this.main_18.field_82907_q);
    GL11.glTranslatef(this.main_18.field_78800_c * f5, this.main_18.field_78797_d * f5, this.main_18.field_78798_e * f5);
    GL11.glScaled(0.2D, 0.2D, 0.2D);
    GL11.glTranslatef(-this.main_18.field_82906_o, -this.main_18.field_82908_p, -this.main_18.field_82907_q);
    GL11.glTranslatef(-this.main_18.field_78800_c * f5, -this.main_18.field_78797_d * f5, -this.main_18.field_78798_e * f5);
    this.main_18.func_78785_a(f5);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslatef(this.main_12.field_82906_o, this.main_12.field_82908_p, this.main_12.field_82907_q);
    GL11.glTranslatef(this.main_12.field_78800_c * f5, this.main_12.field_78797_d * f5, this.main_12.field_78798_e * f5);
    GL11.glScaled(0.2D, 0.2D, 0.2D);
    GL11.glTranslatef(-this.main_12.field_82906_o, -this.main_12.field_82908_p, -this.main_12.field_82907_q);
    GL11.glTranslatef(-this.main_12.field_78800_c * f5, -this.main_12.field_78797_d * f5, -this.main_12.field_78798_e * f5);
    this.main_12.func_78785_a(f5);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslatef(this.Inscriptions.field_82906_o, this.Inscriptions.field_82908_p, this.Inscriptions.field_82907_q);
    GL11.glTranslatef(this.Inscriptions.field_78800_c * f5, this.Inscriptions.field_78797_d * f5, this.Inscriptions.field_78798_e * f5);
    GL11.glScaled(0.5D, 1.0D, 0.5D);
    GL11.glTranslatef(-this.Inscriptions.field_82906_o, -this.Inscriptions.field_82908_p, -this.Inscriptions.field_82907_q);
    GL11.glTranslatef(-this.Inscriptions.field_78800_c * f5, -this.Inscriptions.field_78797_d * f5, -this.Inscriptions.field_78798_e * f5);
    this.Inscriptions.func_78785_a(f5);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslatef(this.main_4.field_82906_o, this.main_4.field_82908_p, this.main_4.field_82907_q);
    GL11.glTranslatef(this.main_4.field_78800_c * f5, this.main_4.field_78797_d * f5, this.main_4.field_78798_e * f5);
    GL11.glScaled(0.2D, 0.2D, 0.2D);
    GL11.glTranslatef(-this.main_4.field_82906_o, -this.main_4.field_82908_p, -this.main_4.field_82907_q);
    GL11.glTranslatef(-this.main_4.field_78800_c * f5, -this.main_4.field_78797_d * f5, -this.main_4.field_78798_e * f5);
    this.main_4.func_78785_a(f5);
    GL11.glPopMatrix();
    this.Pied1.func_78785_a(f5);
    GL11.glPushMatrix();
    GL11.glTranslatef(this.main_14.field_82906_o, this.main_14.field_82908_p, this.main_14.field_82907_q);
    GL11.glTranslatef(this.main_14.field_78800_c * f5, this.main_14.field_78797_d * f5, this.main_14.field_78798_e * f5);
    GL11.glScaled(0.2D, 0.2D, 0.2D);
    GL11.glTranslatef(-this.main_14.field_82906_o, -this.main_14.field_82908_p, -this.main_14.field_82907_q);
    GL11.glTranslatef(-this.main_14.field_78800_c * f5, -this.main_14.field_78797_d * f5, -this.main_14.field_78798_e * f5);
    this.main_14.func_78785_a(f5);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslatef(this.cap_4.field_82906_o, this.cap_4.field_82908_p, this.cap_4.field_82907_q);
    GL11.glTranslatef(this.cap_4.field_78800_c * f5, this.cap_4.field_78797_d * f5, this.cap_4.field_78798_e * f5);
    GL11.glScaled(0.2D, 0.2D, 0.2D);
    GL11.glTranslatef(-this.cap_4.field_82906_o, -this.cap_4.field_82908_p, -this.cap_4.field_82907_q);
    GL11.glTranslatef(-this.cap_4.field_78800_c * f5, -this.cap_4.field_78797_d * f5, -this.cap_4.field_78798_e * f5);
    this.cap_4.func_78785_a(f5);
    GL11.glPopMatrix();
  }
  
  public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
    modelRenderer.field_78795_f = x;
    modelRenderer.field_78796_g = y;
    modelRenderer.field_78808_h = z;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\model\ModelAlchemyCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */