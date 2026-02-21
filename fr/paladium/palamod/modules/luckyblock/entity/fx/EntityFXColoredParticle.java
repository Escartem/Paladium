package fr.paladium.palamod.modules.luckyblock.entity.fx;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityReddustFX;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityFXColoredParticle extends EntityReddustFX {
  private float r;
  
  private float g;
  
  private float b;
  
  public EntityFXColoredParticle(World world, double x, double y, double z, float r, float g, float b, float scale) {
    super(world, x, y, z, scale, 1.0F, 0.0F, 0.0F);
    this.r = r;
    this.g = g;
    this.b = b;
  }
  
  public void func_70071_h_() {
    super.func_70071_h_();
    this.field_70552_h = this.r;
    this.field_70553_i = this.g;
    this.field_70551_j = this.b;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\fx\EntityFXColoredParticle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */