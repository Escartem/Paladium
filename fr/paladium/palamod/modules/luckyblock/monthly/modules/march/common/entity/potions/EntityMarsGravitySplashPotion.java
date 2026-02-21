package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.entity.potions;

import fr.paladium.palamod.api.PotionsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.utils.Interval;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityMarsGravitySplashPotion extends EntityThrowable {
  public static final Interval MARS_INTERVAL = new Interval(10, 20);
  
  public static final String ENTITY_ID = "entityMarsGravitySplashPotion";
  
  public EntityMarsGravitySplashPotion(World world) {
    super(world);
  }
  
  public EntityMarsGravitySplashPotion(World world, EntityPlayer player) {
    super(world, (EntityLivingBase)player);
    func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, 0.4F, 0.3F);
  }
  
  protected void func_70088_a() {
    super.func_70088_a();
  }
  
  protected void func_70184_a(MovingObjectPosition obj) {
    if (this.field_70170_p.field_72995_K)
      return; 
    applyPotionEffect(obj);
    func_70106_y();
  }
  
  protected float func_70185_h() {
    return super.func_70185_h();
  }
  
  protected float func_70182_d() {
    return super.func_70182_d();
  }
  
  protected float func_70183_g() {
    return super.func_70183_g();
  }
  
  public void applyPotionEffect(MovingObjectPosition p_70184_1_) {
    AxisAlignedBB axisalignedbb = this.field_70121_D.func_72314_b(4.0D, 2.0D, 4.0D);
    List list1 = this.field_70170_p.func_72872_a(EntityPlayer.class, axisalignedbb);
    if (list1 != null && !list1.isEmpty()) {
      Iterator<EntityPlayer> iterator = list1.iterator();
      while (iterator.hasNext()) {
        EntityPlayer entitylivingbase = iterator.next();
        double d0 = func_70068_e((Entity)entitylivingbase);
        if (d0 < 16.0D)
          entitylivingbase.func_70690_d(new PotionEffect(PotionsRegister.MARS_GRAVITY
                .getPotionId(), 
                MonthlyUtils.translateSecondsToTicks(60), 1)); 
      } 
    } 
    this.field_70170_p.func_72926_e(2002, (int)Math.round(this.field_70165_t), (int)Math.round(this.field_70163_u), 
        (int)Math.round(this.field_70161_v), 4);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\entity\potions\EntityMarsGravitySplashPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */