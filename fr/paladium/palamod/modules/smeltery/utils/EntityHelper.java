package fr.paladium.palamod.modules.smeltery.utils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

public class EntityHelper {
  public static void customKnockBack(EntityPlayer player, EntityPlayer attacker, double par3, double par4) {
    double motionY = player.field_70181_x;
    double motionX = player.field_70181_x;
    double motionZ = player.field_70181_x;
    player.field_70160_al = true;
    float f1 = MathHelper.func_76133_a(par3 * par3 + par4 * par4);
    float f2 = 0.4F;
    motionX /= 2.0D;
    motionY /= 2.0D;
    motionZ /= 2.0D;
    motionX -= par3 / f1 * f2;
    motionY += f2;
    player.field_70179_y -= par4 / f1 * f2;
    if (motionY > 0.4000000059604645D)
      motionY = 0.4000000059604645D; 
    player.func_70091_d(motionX, motionY, motionZ);
  }
  
  public static void knockbackEntity(EntityLivingBase living, double boost) {
    living.field_70159_w *= boost;
    living.field_70179_y *= boost;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\smelter\\utils\EntityHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */