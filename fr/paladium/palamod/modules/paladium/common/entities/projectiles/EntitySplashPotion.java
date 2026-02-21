package fr.paladium.palamod.modules.paladium.common.entities.projectiles;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.Interval;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class EntitySplashPotion extends EntityThrowable {
  public int damageValue;
  
  public boolean isGun;
  
  public static final int NAUSEA = 0;
  
  public static final int WEB = 1;
  
  public static final int CORRUPTION = 2;
  
  public static final Interval CORRUPTION_INTERVAL = new Interval(1, 10);
  
  public EntitySplashPotion(World world) {
    super(world);
  }
  
  public EntitySplashPotion(World world, EntityPlayer player, int damageValue) {
    super(world, (EntityLivingBase)player);
    func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, 0.4F, 0.3F);
    setDamageValue(damageValue);
  }
  
  public EntitySplashPotion(World world, EntityPlayer player, int damageValue, boolean isGun) {
    super(world, (EntityLivingBase)player);
    func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, 0.4F, 0.3F);
    setDamageValue(damageValue);
    this.isGun = isGun;
  }
  
  protected void func_70088_a() {
    super.func_70088_a();
    this.field_70180_af.func_75682_a(2, Integer.valueOf(0));
  }
  
  public void setDamageValue(int damageValue) {
    this.damageValue = damageValue;
    if (!this.field_70170_p.field_72995_K)
      this.field_70180_af.func_75692_b(2, Integer.valueOf(damageValue)); 
  }
  
  public int getDamageValue() {
    return this.field_70180_af.func_75679_c(2);
  }
  
  protected void func_70184_a(MovingObjectPosition p_70184_1_) {
    if (!this.field_70170_p.field_72995_K)
      switch (this.damageValue) {
        case 0:
          nauseaEffect(p_70184_1_);
          break;
        case 1:
          spawnWeb(p_70184_1_);
          break;
      }  
    func_70106_y();
    func_70106_y();
  }
  
  private static boolean setBlockIfNotSolid(World world, int x, int y, int z, Block block) {
    return setBlockIfNotSolid(world, x, y, z, block, 0);
  }
  
  private static boolean setBlockIfNotSolid(World world, int x, int y, int z, Block block, int metadata) {
    if (world.func_147439_a(x, y, z).isAir((IBlockAccess)world, x, y, z) || world
      .func_147439_a(x, y, z) == BlocksRegister.WEB_CUSTOM) {
      world.func_147465_d(x, y, z, block, metadata, 3);
      return true;
    } 
    return false;
  }
  
  private void spawnWeb(MovingObjectPosition mop) {
    switch (mop.field_72313_a) {
      case ENTITY:
        this.field_70170_p.func_147449_b((int)mop.field_72308_g.field_70165_t, (int)mop.field_72308_g.field_70163_u, (int)mop.field_72308_g.field_70161_v, Blocks.field_150321_G);
        break;
      case BLOCK:
        if (this.field_70170_p.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d) == Blocks.field_150433_aE) {
          mop.field_72312_c--;
          mop.field_72310_e = 1;
        } 
        switch (mop.field_72310_e) {
          case 0:
            setBlockIfNotSolid(this.field_70170_p, mop.field_72311_b, mop.field_72312_c - 1, mop.field_72309_d, (Block)BlocksRegister.WEB_CUSTOM);
            break;
          case 1:
            setBlockIfNotSolid(this.field_70170_p, mop.field_72311_b, mop.field_72312_c + 1, mop.field_72309_d, (Block)BlocksRegister.WEB_CUSTOM);
            break;
          case 2:
            setBlockIfNotSolid(this.field_70170_p, mop.field_72311_b - 1, mop.field_72312_c, mop.field_72309_d, (Block)BlocksRegister.WEB_CUSTOM);
            break;
          case 3:
            setBlockIfNotSolid(this.field_70170_p, mop.field_72311_b + 1, mop.field_72312_c, mop.field_72309_d, (Block)BlocksRegister.WEB_CUSTOM);
            break;
          case 4:
            setBlockIfNotSolid(this.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d - 1, (Block)BlocksRegister.WEB_CUSTOM);
            break;
          case 5:
            setBlockIfNotSolid(this.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d + 1, (Block)BlocksRegister.WEB_CUSTOM);
            break;
        } 
        break;
    } 
  }
  
  protected float func_70185_h() {
    if (this.isGun)
      return 0.01F; 
    return super.func_70185_h();
  }
  
  protected float func_70182_d() {
    if (this.isGun)
      return 2.0F; 
    return super.func_70182_d();
  }
  
  protected float func_70183_g() {
    if (this.isGun)
      return 2.0F; 
    return super.func_70183_g();
  }
  
  public void nauseaEffect(MovingObjectPosition p_70184_1_) {
    AxisAlignedBB axisalignedbb = this.field_70121_D.func_72314_b(4.0D, 2.0D, 4.0D);
    List list1 = this.field_70170_p.func_72872_a(EntityPlayer.class, axisalignedbb);
    if (list1 != null && !list1.isEmpty()) {
      Iterator<EntityPlayer> iterator = list1.iterator();
      while (iterator.hasNext()) {
        EntityPlayer entitylivingbase = iterator.next();
        double d0 = func_70068_e((Entity)entitylivingbase);
        if (d0 < 16.0D) {
          double d1 = 1.0D - Math.sqrt(d0) / 4.0D;
          if (entitylivingbase == p_70184_1_.field_72308_g)
            d1 = 1.0D; 
          if (this.damageValue == 0) {
            entitylivingbase.func_70690_d(new PotionEffect(Potion.field_76431_k.field_76415_H, 400, 100));
            continue;
          } 
          if (this.damageValue == 2);
        } 
      } 
    } 
    this.field_70170_p.func_72926_e(2002, (int)Math.round(this.field_70165_t), (int)Math.round(this.field_70163_u), 
        (int)Math.round(this.field_70161_v), 4);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\entities\projectiles\EntitySplashPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */