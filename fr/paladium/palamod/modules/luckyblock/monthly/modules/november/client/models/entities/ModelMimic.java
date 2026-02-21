package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.client.models.entities;

import net.minecraft.client.model.ModelChest;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelMimic extends ModelChest {
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    GL11.glPushMatrix();
    float scale = 0.8F;
    GL11.glScalef(scale, scale, scale);
    GL11.glTranslatef(-0.5F, 0.9F, -0.5F);
    this.field_78233_c.field_78795_f = this.field_78234_a.field_78795_f;
    this.field_78234_a.func_78785_a(f5);
    this.field_78233_c.func_78785_a(f5);
    this.field_78232_b.func_78785_a(f5);
    GL11.glPopMatrix();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\client\models\entities\ModelMimic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */