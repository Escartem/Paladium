package fr.paladium.palamod.modules.palaboss.common.attacks;

import fr.paladium.palamod.modules.palaboss.common.entity.boss.EntityBossBase;
import net.minecraft.entity.EntityLivingBase;

class null implements Runnable {
  public void run() {
    try {
      Thread.sleep(1000L);
      while (entityHost.field_70163_u < entityHost.field_70170_p.func_72976_f((int)entityHost.field_70165_t, (int)entityHost.field_70161_v))
        entityHost.field_70181_x = 0.5D; 
      entityHost.field_70181_x = 0.5D;
      entityHost.field_70145_X = false;
      DjinnAttack.this.earthquake((EntityLivingBase)entityHost, (int)range);
      DjinnAttack.access$002(DjinnAttack.this, false);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\palaboss\common\attacks\DjinnAttack$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */