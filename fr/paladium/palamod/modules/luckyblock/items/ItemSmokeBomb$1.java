package fr.paladium.palamod.modules.luckyblock.items;

import fr.paladium.palamod.modules.luckyblock.utils.LuckyTask;
import fr.paladium.palamod.scheduler.PaladiumScheduler;
import java.awt.Color;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

class null implements Runnable {
  long times = 0L;
  
  double offx = 0.0D;
  
  double offy = 0.0D;
  
  Vec3 loc = null;
  
  public void run() {
    this.times++;
    if (this.times > 120L) {
      PaladiumScheduler.INSTANCE.cancelTask(task.id);
      return;
    } 
    int pix = (int)player.field_70165_t;
    int piy = (int)player.field_70163_u;
    int piz = (int)player.field_70161_v;
    if (this.times < 40L) {
      double dy;
      for (dy = piy; dy < piy + 0.3D; dy += 0.1D) {
        double dx;
        for (dx = pix - 0.3D; dx < pix + 0.3D; dx += 0.1D) {
          this.loc = Vec3.func_72443_a(dx, dy + this.offy, piz);
          world.func_72869_a("reddust", dx, dy + this.offy, piz, c.getRed() / 255.0D, c
              .getGreen() / 255.0D, c.getBlue() / 255.0D);
        } 
      } 
      this.offy += 0.04D;
    } else if (this.times < 65L) {
      if (this.times == 40L)
        this.offy = 0.0D; 
      double dx;
      for (dx = -0.5D; dx < 0.5D; dx += 0.1D) {
        double dz;
        for (dz = -0.5D; dz < 0.5D; dz += 0.1D)
          world.func_72869_a("reddust", this.loc.field_72450_a + this.offx + dx, this.offy + this.loc.field_72448_b, this.loc.field_72449_c + dz, c
              .getRed() / 255.0D, c.getGreen() / 255.0D, c
              .getBlue() / 255.0D); 
      } 
      this.offx += 0.6D;
      this.offy += 0.8D;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemSmokeBomb$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */