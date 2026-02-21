package fr.paladium.palamod.modules.luckyblock.luckyevents;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

class null extends EntityFireball {
  null(World x0, double x1, double x2, double x3, double x4, double x5, double x6) {
    super(x0, x1, x2, x3, x4, x5, x6);
  }
  
  protected void func_70227_a(MovingObjectPosition obj) {
    player.field_70170_p.func_72876_a((Entity)this, obj.field_72311_b, obj.field_72312_c, obj.field_72309_d, 10.0F, true);
    func_70106_y();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Ghast$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */