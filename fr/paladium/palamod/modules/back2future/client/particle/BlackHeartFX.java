package fr.paladium.palamod.modules.back2future.client.particle;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class BlackHeartFX extends EntityFX {
  public BlackHeartFX(World world, double x, double y, double z) {
    super(world, x, y, z);
    func_70536_a(67);
    this.field_70545_g = 0.1F;
    this.field_70547_e = 20;
    this.field_70145_X = true;
  }
  
  public float func_70013_c(float p_70013_1_) {
    return 1.0F;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\client\particle\BlackHeartFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */