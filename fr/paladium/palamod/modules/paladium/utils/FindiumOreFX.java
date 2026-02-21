package fr.paladium.palamod.modules.paladium.utils;

import net.minecraft.client.particle.EntityReddustFX;
import net.minecraft.world.World;

public class FindiumOreFX extends EntityReddustFX {
  public FindiumOreFX(World w, double x, double y, double z, float r, float g, float b) {
    super(w, x, y, z, 1.0F, 0.88F, 0.03F);
  }
  
  public int func_70070_b(float par1) {
    int j1 = super.func_70070_b(par1);
    j1 = Math.max(j1 >> 20, j1 >> 4);
    j1 += 3;
    if (j1 > 15)
      j1 = 15; 
    return j1 << 20 | j1 << 4;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladiu\\utils\FindiumOreFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */