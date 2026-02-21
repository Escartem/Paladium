package fr.paladium.palashop.provider.cosmetic.common.network;

import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import lombok.NonNull;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class HitResult {
  private final MovingObjectPosition.MovingObjectType type;
  
  private final int blockX;
  
  private final int blockY;
  
  private final int blockZ;
  
  private final int sideHit;
  
  private final Vec3 hitVec;
  
  private final String entityHit;
  
  private final int subHit;
  
  private final Object hitInfo;
  
  public MovingObjectPosition.MovingObjectType getType() {
    return this.type;
  }
  
  public int getBlockX() {
    return this.blockX;
  }
  
  public int getBlockY() {
    return this.blockY;
  }
  
  public int getBlockZ() {
    return this.blockZ;
  }
  
  public int getSideHit() {
    return this.sideHit;
  }
  
  public Vec3 getHitVec() {
    return this.hitVec;
  }
  
  public String getEntityHit() {
    return this.entityHit;
  }
  
  public int getSubHit() {
    return this.subHit;
  }
  
  public Object getHitInfo() {
    return this.hitInfo;
  }
  
  public HitResult(@NonNull MovingObjectPosition mop) {
    if (mop == null)
      throw new NullPointerException("mop is marked non-null but is null"); 
    this.type = mop.field_72313_a;
    this.blockX = mop.field_72311_b;
    this.blockY = mop.field_72312_c;
    this.blockZ = mop.field_72309_d;
    this.sideHit = mop.field_72310_e;
    this.hitVec = mop.field_72307_f;
    this.entityHit = (mop.field_72308_g == null) ? null : UUIDUtils.toString(mop.field_72308_g);
    this.subHit = mop.subHit;
    this.hitInfo = mop.hitInfo;
  }
  
  public String toString() {
    return "HitResult{type=" + this.type + ", x=" + this.blockX + ", y=" + this.blockY + ", z=" + this.blockZ + ", f=" + this.sideHit + ", pos=" + this.hitVec + ", entity=" + this.entityHit + '}';
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\cosmetic\common\network\CSPacketExecuteWheelCosmetic$HitResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */