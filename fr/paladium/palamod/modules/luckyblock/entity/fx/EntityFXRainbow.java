package fr.paladium.palamod.modules.luckyblock.entity.fx;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityReddustFX;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityFXRainbow extends EntityReddustFX {
  public EntityFXRainbow(World world, double x, double y, double z) {
    super(world, x, y, z, 3.0F, 1.0F, 0.0F, 0.0F);
  }
  
  public void func_70071_h_() {
    super.func_70071_h_();
    float ageInTicks = (this.field_70546_d + 1);
    float maxAge = this.field_70547_e;
    float ageRatio = ageInTicks / maxAge;
    this.field_70552_h = MathHelper.func_76126_a(ageRatio * 3.1415927F);
    this.field_70553_i = MathHelper.func_76126_a((ageRatio + 0.33333334F) * 3.1415927F);
    this.field_70551_j = MathHelper.func_76126_a((ageRatio + 0.6666667F) * 3.1415927F);
    this.field_70544_f = 2.0F * (1.0F - ageRatio);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\entity\fx\EntityFXRainbow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */