package fr.paladium.palamod.modules.pillage.utils;

import fr.paladium.palamod.modules.pillage.common.CommonProxy;
import fr.paladium.palamod.modules.pillage.common.effects.ExplosionObsi;
import java.util.UUID;
import javax.vecmath.Tuple3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class PPillageUtils {
  static int id;
  
  public static Vector3d getRotatedPoint(Vector3d pos, float pitch, float yaw, float roll) {
    double a = Math.cos((pitch * 0.017453292F));
    double b = Math.sin((pitch * 0.017453292F));
    double c = Math.cos((yaw * 0.017453292F));
    double d = Math.sin((yaw * 0.017453292F));
    double e = Math.cos((roll * 0.017453292F));
    double f = Math.sin((roll * 0.017453292F));
    float x = (float)(pos.x * (c * e - b * d * f) + pos.y * (-b * d * e - c * f) + pos.z * -a * d);
    float y = (float)(pos.x * a * f + pos.y * a * e + pos.z * -b);
    float z = (float)(pos.x * (d * e + b * c * f) + pos.y * (b * c * e - d * f) + pos.z * a * c);
    return new Vector3d(x, y, z);
  }
  
  public static void getRotatedPointPool(Vector3f pos, float pitch, float yaw, float roll) {
    double a = Math.cos((pitch * 0.017453292F));
    double b = Math.sin((pitch * 0.017453292F));
    double c = Math.cos((yaw * 0.017453292F));
    double d = Math.sin((yaw * 0.017453292F));
    double e = Math.cos((roll * 0.017453292F));
    double f = Math.sin((roll * 0.017453292F));
    float x = (float)(pos.x * (c * e - b * d * f) + pos.y * (-b * d * e - c * f) + pos.z * -a * d);
    float y = (float)(pos.x * a * f + pos.y * a * e + pos.z * -b);
    float z = (float)(pos.x * (d * e + b * c * f) + pos.y * (b * c * e - d * f) + pos.z * a * c);
    pos.set(x, y, z);
  }
  
  public static Vector3d rotateVec(Vector3d pos, float angle) {
    float x = (float)(pos.x * Math.cos(angle) - pos.z * Math.sin(angle));
    float z = (float)(pos.x * Math.sin(angle) + pos.z * Math.cos(angle));
    return new Vector3d(x, pos.y, z);
  }
  
  public static Vector3d getDirectionVec(Vector3d vecFrom, Vector3d vecTo) {
    double distance = getDistanceAtoB(vecFrom, vecTo);
    if (distance == 0.0D)
      distance = 0.1D; 
    Vector3d offset = (Vector3d)vecTo.clone();
    offset.sub((Tuple3d)vecFrom);
    return new Vector3d(offset.x / distance, offset.y / distance, offset.z / distance);
  }
  
  public static double getDistanceAtoB(double x1, double y1, double z1, double x2, double y2, double z2) {
    double dx = x1 - x2;
    double dy = y1 - y2;
    double dz = z1 - z2;
    return Math.sqrt(dx * dx + dy * dy + dz * dz);
  }
  
  public static double getDistanceAtoB(Vector3d pos1, Vector3d pos2) {
    return getDistanceAtoB(pos1.x, pos1.y, pos1.z, pos2.x, pos2.y, pos2.z);
  }
  
  public static void transformSheep(UUID sheep, byte level) {
    CommonProxy.SPONGE_SHEEP.put(sheep, Byte.valueOf(level));
  }
  
  public static MovingObjectPosition checkBlockCollision(World world, AxisAlignedBB aabb) {
    int i = MathHelper.func_76128_c(aabb.field_72340_a);
    int j = MathHelper.func_76128_c(aabb.field_72336_d + 1.0D);
    int k = MathHelper.func_76128_c(aabb.field_72338_b);
    int l = MathHelper.func_76128_c(aabb.field_72337_e + 1.0D);
    int i1 = MathHelper.func_76128_c(aabb.field_72339_c);
    int j1 = MathHelper.func_76128_c(aabb.field_72334_f + 1.0D);
    if (aabb.field_72340_a < 0.0D)
      i--; 
    if (aabb.field_72338_b < 0.0D)
      k--; 
    if (aabb.field_72339_c < 0.0D)
      i1--; 
    for (int k1 = i; k1 < j; k1++) {
      for (int l1 = k; l1 < l; l1++) {
        for (int i2 = i1; i2 < j1; i2++) {
          Block block = world.func_147439_a(k1, l1, i2);
          if (block.func_149688_o() != Material.field_151579_a) {
            MovingObjectPosition result = new MovingObjectPosition(k1, l1, i2, 0, Vec3.func_72443_a(k1, l1, i2));
            result.hitInfo = block;
            return result;
          } 
        } 
      } 
    } 
    return null;
  }
  
  public static void knockBack(EntityLivingBase entityIn, float strength, double xRatio, double zRatio) {
    if (entityIn.field_70170_p.field_73012_v.nextDouble() >= entityIn
      .func_110148_a(SharedMonsterAttributes.field_111266_c).func_111126_e()) {
      entityIn.field_70160_al = true;
      float f = MathHelper.func_76133_a(xRatio * xRatio + zRatio * zRatio);
      entityIn.field_70159_w /= 2.0D;
      entityIn.field_70179_y /= 2.0D;
      entityIn.field_70159_w -= xRatio / f * strength;
      entityIn.field_70179_y -= zRatio / f * strength;
      if (entityIn.field_70122_E) {
        entityIn.field_70181_x /= 2.0D;
        entityIn.field_70181_x += strength;
        if (entityIn.field_70181_x > 0.4000000059604645D)
          entityIn.field_70181_x = 0.4000000059604645D; 
      } 
      if (entityIn instanceof EntityPlayerMP)
        ((EntityPlayerMP)entityIn).field_71135_a
          .func_147359_a((Packet)new S12PacketEntityVelocity((Entity)entityIn)); 
    } 
  }
  
  static int number = 0;
  
  public static byte arrangeValues(byte[] arrayFrom, byte ordinal, byte offset) {
    byte[] arrayTo = new byte[arrayFrom.length / 2];
    for (int i = 0; i < arrayFrom.length / 2; i++)
      arrayTo[i] = arrayFrom[i * 2 + offset]; 
    return arrayTo[ordinal];
  }
  
  public static Explosion createExplosion(World world, double x, double y, double z, float strength, boolean isFlaming) {
    return newExplosion(world, x, y, z, strength, false, isFlaming);
  }
  
  private static Explosion newExplosion(World world, double x, double y, double z, float strength, boolean isFlaming, boolean isSmoking) {
    ExplosionObsi explosion = new ExplosionObsi(world, x, y, z, strength);
    explosion.field_77286_a = isFlaming;
    explosion.field_82755_b = isSmoking;
    if (ForgeEventFactory.onExplosionStart(world, (Explosion)explosion))
      return (Explosion)explosion; 
    explosion.func_77278_a();
    explosion.func_77279_a(true);
    return (Explosion)explosion;
  }
  
  public static String formatSeconds(int seconds, boolean full) {
    return String.format("%02d:%02d:%02d", new Object[] { Integer.valueOf(seconds / 3600), Integer.valueOf(seconds % 3600 / 60), Integer.valueOf(seconds % 60) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillag\\utils\PPillageUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */