package fr.paladium.palamod.modules.luckyblock.entity.fx;

import fr.paladium.palamod.modules.luckyblock.utils.LuckyType;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityFXLuckyBlock extends EntityFX {
  private ResourceLocation defaultParticleTexture;
  
  public EntityFXLuckyBlock(World world, double x, double y, double z) {
    super(world, x, y, z);
  }
  
  public void func_70539_a(Tessellator tess, float par2, float par3, float par4, float par5, float par6, float par7) {
    Tessellator tessellator1 = new Tessellator();
    tessellator1.func_78382_b();
    tessellator1.func_78380_c(func_70070_b(par2));
    (Minecraft.func_71410_x()).field_71446_o.func_110577_a(new ResourceLocation("palamod", "textures/block/" + LuckyType.values()[(new Random()).nextInt((LuckyType.values()).length)].name() + "_lucky_block.png"));
    float f = (this.field_94054_b % 16) / 16.0F;
    float f1 = f + 0.0624375F;
    float f2 = (this.field_94055_c / 16) / 16.0F;
    float f3 = f2 + 0.0624375F;
    float f4 = 0.1F * this.field_70544_f;
    float f5 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * par2 - field_70556_an);
    float f6 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * par2 - field_70554_ao);
    float f7 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * par2 - field_70555_ap);
    float f8 = 1.0F;
    tessellator1.func_78386_a(this.field_70552_h * f8, this.field_70553_i * f8, this.field_70551_j * f8);
    tessellator1.func_78374_a((f5 - par3 * f4 - par6 * f4), (f6 - par4 * f4), (f7 - par5 * f4 - par7 * f4), f1, f3);
    tessellator1.func_78374_a((f5 - par3 * f4 + par6 * f4), (f6 + par4 * f4), (f7 - par5 * f4 + par7 * f4), f1, f2);
    tessellator1.func_78374_a((f5 + par3 * f4 + par6 * f4), (f6 + par4 * f4), (f7 + par5 * f4 + par7 * f4), f, f2);
    tessellator1.func_78374_a((f5 + par3 * f4 - par6 * f4), (f6 - par4 * f4), (f7 + par5 * f4 - par7 * f4), f, f3);
    tessellator1.func_78381_a();
    (Minecraft.func_71410_x()).field_71446_o.func_110577_a((this.defaultParticleTexture == null) ? (this.defaultParticleTexture = new ResourceLocation("textures/particle/particles.png")) : this.defaultParticleTexture);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\fx\EntityFXLuckyBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */