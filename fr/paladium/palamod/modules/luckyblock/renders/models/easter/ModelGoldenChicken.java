package fr.paladium.palamod.modules.luckyblock.renders.models.easter;

import net.minecraft.client.model.ModelChicken;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelGoldenChicken extends ModelChicken {
  public void func_78088_a(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
    func_78087_a(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
    GL11.glPushMatrix();
    GL11.glTranslatef(0.0F, -1.5F, 0.0F);
    GL11.glScalef(2.0F, 2.0F, 2.0F);
    this.field_78142_a.func_78785_a(p_78088_7_);
    this.field_78137_g.func_78785_a(p_78088_7_);
    this.field_78143_h.func_78785_a(p_78088_7_);
    this.field_78140_b.func_78785_a(p_78088_7_);
    this.field_78141_c.func_78785_a(p_78088_7_);
    this.field_78138_d.func_78785_a(p_78088_7_);
    this.field_78139_e.func_78785_a(p_78088_7_);
    this.field_78136_f.func_78785_a(p_78088_7_);
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\renders\models\easter\ModelGoldenChicken.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */