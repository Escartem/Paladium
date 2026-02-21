package fr.paladium.palamod.modules.paladium.common.entities.projectiles;

import fr.paladium.palaforgeutils.lib.location.DoubleLocation;
import fr.paladium.palamod.modules.paladium.utils.BowHelper;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityCustomArrow extends EntityArrow {
  public static final int POISON = 0;
  
  public static final int WITHER = 1;
  
  public static final int SLOWNESS = 2;
  
  public static final int SWITCH = 3;
  
  public static final int FROST = 4;
  
  public static final int MIDLIFE = 6;
  
  private EntityPlayer player;
  
  private int type;
  
  private boolean inGround;
  
  private boolean canBePickedUp;
  
  private int arrowShake;
  
  private Entity shootingEntity;
  
  private int ticksInAir;
  
  private DoubleLocation origin;
  
  public EntityPlayer getPlayer() {
    return this.player;
  }
  
  public int getType() {
    return this.type;
  }
  
  public boolean isInGround() {
    return this.inGround;
  }
  
  public boolean isCanBePickedUp() {
    return this.canBePickedUp;
  }
  
  public int getArrowShake() {
    return this.arrowShake;
  }
  
  public Entity getShootingEntity() {
    return this.shootingEntity;
  }
  
  public int getTicksInAir() {
    return this.ticksInAir;
  }
  
  public DoubleLocation getOrigin() {
    return this.origin;
  }
  
  public EntityCustomArrow(World world) {
    super(world);
  }
  
  public EntityCustomArrow(World world, int type, EntityPlayer player) {
    super(world);
    this.type = type;
    this.player = player;
    this.origin = new DoubleLocation((Entity)player);
  }
  
  public EntityCustomArrow(World world, EntityLivingBase entity, float power, int type, boolean infiniteAmmo) {
    super(world, entity, power);
    this.type = type;
    this.player = (EntityPlayer)entity;
    this.origin = new DoubleLocation((Entity)entity);
    this.canBePickedUp = infiniteAmmo;
  }
  
  public void func_70037_a(NBTTagCompound nbt) {
    super.func_70037_a(nbt);
    if (nbt.func_74764_b("origin")) {
      NBTTagCompound originTag = nbt.func_74775_l("origin");
      this.origin = new DoubleLocation(originTag.func_74769_h("x"), originTag.func_74769_h("y"), originTag.func_74769_h("z"));
    } 
    if (nbt.func_74764_b("typearrow"))
      setType(nbt.func_74762_e("typearrow")); 
    this.canBePickedUp = nbt.func_74767_n("canBePickedUp");
  }
  
  public void func_70014_b(NBTTagCompound nbt) {
    super.func_70014_b(nbt);
    if (this.origin != null) {
      NBTTagCompound originTag = new NBTTagCompound();
      originTag.func_74780_a("x", this.origin.getX());
      originTag.func_74780_a("y", this.origin.getY());
      originTag.func_74780_a("z", this.origin.getZ());
      nbt.func_74782_a("origin", (NBTBase)originTag);
    } 
    nbt.func_74768_a("typearrow", getType());
    nbt.func_74757_a("canBePickedUp", this.canBePickedUp);
  }
  
  public void func_70030_z() {
    if (this.field_70173_aa >= 2000)
      func_70106_y(); 
    Vec3 vec3d = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
    Vec3 vec3d1 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
    MovingObjectPosition movingobjectposition = this.field_70170_p.func_147447_a(vec3d, vec3d1, false, true, false);
    List<Entity> list = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0D, 1.0D, 1.0D));
    double d = 0.0D;
    Entity entity = null;
    for (int l = 0; l < list.size(); l++) {
      Entity entity1 = list.get(l);
      if (entity1.func_70067_L() && (entity1 != this.shootingEntity || this.ticksInAir >= 5)) {
        float f4 = 0.3F;
        AxisAlignedBB axisalignedbb1 = entity1.field_70121_D.func_72314_b(0.30000001192092896D, 0.30000001192092896D, 0.30000001192092896D);
        MovingObjectPosition movingobjectposition1 = axisalignedbb1.func_72327_a(vec3d, vec3d1);
        if (movingobjectposition1 != null) {
          double d1 = vec3d.func_72438_d(movingobjectposition1.field_72307_f);
          if (d1 < d || d == 0.0D) {
            entity = entity1;
            d = d1;
          } 
        } 
      } 
    } 
    if (entity != null)
      movingobjectposition = new MovingObjectPosition(entity); 
    if (movingobjectposition != null && 
      movingobjectposition.field_72308_g == null)
      this.inGround = true; 
    super.func_70030_z();
  }
  
  public void setType(int type) {
    this.type = type;
  }
  
  public void func_70100_b_(EntityPlayer player) {
    if (!this.canBePickedUp)
      return; 
    if (this.inGround) {
      if (BowHelper.getItem(this.type) == null)
        return; 
      EntityItem arrow = new EntityItem(player.field_70170_p, player.field_70165_t, player.field_70163_u, player.field_70161_v, new ItemStack(BowHelper.getItem(this.type)));
      if (!player.field_70170_p.field_72995_K)
        player.field_70170_p.func_72838_d((Entity)arrow); 
      func_70106_y();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\entities\projectiles\EntityCustomArrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */