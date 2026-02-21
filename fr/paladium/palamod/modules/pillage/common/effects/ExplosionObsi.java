package fr.paladium.palamod.modules.pillage.common.effects;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class ExplosionObsi extends Explosion {
  private final int range = 16;
  
  private final World worldObj;
  
  private final Map field_77288_k = new HashMap<>();
  
  public ExplosionObsi(World world, double x, double y, double z, float explosionSize) {
    super(world, null, x, y, z, explosionSize);
    this.worldObj = world;
  }
  
  public void func_77278_a() {
    float f = this.field_77280_f;
    HashSet<ChunkPosition> hashset = new HashSet();
    int i = 0;
    getClass();
    for (; i < 16; i++) {
      int m = 0;
      getClass();
      for (; m < 16; m++) {
        int n = 0;
        getClass();
        for (; n < 16; n++) {
          getClass();
          getClass();
          getClass();
          if (i == 0 || i == 16 - 1 || m == 0 || m == 16 - 1 || n == 0 || n == 16 - 1) {
            getClass();
            double d0 = (i / (16.0F - 1.0F) * 2.0F - 1.0F);
            getClass();
            double d1 = (m / (16.0F - 1.0F) * 2.0F - 1.0F);
            getClass();
            double d2 = (n / (16.0F - 1.0F) * 2.0F - 1.0F);
            double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
            d0 /= d3;
            d1 /= d3;
            d2 /= d3;
            float f1 = this.field_77280_f * (0.7F + this.worldObj.field_73012_v.nextFloat() * 0.6F);
            double d5 = this.field_77284_b;
            double d6 = this.field_77285_c;
            double d7 = this.field_77282_d;
            for (float f2 = 0.3F; f1 > 0.0F; f1 -= f2 * 0.75F) {
              int j1 = MathHelper.func_76128_c(d5);
              int k1 = MathHelper.func_76128_c(d6);
              int l1 = MathHelper.func_76128_c(d7);
              Block block = this.worldObj.func_147439_a(j1, k1, l1);
              if (block.func_149688_o() != Material.field_151579_a) {
                float f3 = (this.field_77283_e != null) ? this.field_77283_e.func_145772_a(this, this.worldObj, j1, k1, l1, block) : block.getExplosionResistance(this.field_77283_e, this.worldObj, j1, k1, l1, this.field_77284_b, this.field_77285_c, this.field_77282_d);
                f1 -= (f3 + 0.3F) * f2;
              } 
              if (f1 > 0.0F && (this.field_77283_e == null || this.field_77283_e
                .func_145774_a(this, this.worldObj, j1, k1, l1, block, f1)))
                hashset.add(new ChunkPosition(j1, k1, l1)); 
              d5 += d0 * f2;
              d6 += d1 * f2;
              d7 += d2 * f2;
            } 
          } 
        } 
      } 
    } 
    this.field_77281_g.addAll(hashset);
    this.field_77280_f *= 2.0F;
    i = MathHelper.func_76128_c(this.field_77284_b - this.field_77280_f - 1.0D);
    int j = MathHelper.func_76128_c(this.field_77284_b + this.field_77280_f + 1.0D);
    int k = MathHelper.func_76128_c(this.field_77285_c - this.field_77280_f - 1.0D);
    int i2 = MathHelper.func_76128_c(this.field_77285_c + this.field_77280_f + 1.0D);
    int l = MathHelper.func_76128_c(this.field_77282_d - this.field_77280_f - 1.0D);
    int j2 = MathHelper.func_76128_c(this.field_77282_d + this.field_77280_f + 1.0D);
    List<Entity> list = this.worldObj.func_72872_a(Entity.class, 
        AxisAlignedBB.func_72330_a(i, k, l, j, i2, j2));
    ForgeEventFactory.onExplosionDetonate(this.worldObj, this, list, this.field_77280_f);
    Vec3 vec3 = Vec3.func_72443_a(this.field_77284_b, this.field_77285_c, this.field_77282_d);
    for (int i1 = 0; i1 < list.size(); i1++) {
      Entity entity = list.get(i1);
      double d4 = entity.func_70011_f(this.field_77284_b, this.field_77285_c, this.field_77282_d) / this.field_77280_f;
      if (d4 <= 1.0D) {
        double d5 = entity.field_70165_t - this.field_77284_b;
        double d6 = entity.field_70163_u + entity.func_70047_e() - this.field_77285_c;
        double d7 = entity.field_70161_v - this.field_77282_d;
        double d9 = MathHelper.func_76133_a(d5 * d5 + d6 * d6 + d7 * d7);
        if (d9 != 0.0D) {
          d5 /= d9;
          d6 /= d9;
          d7 /= d9;
          double d10 = this.worldObj.func_72842_a(vec3, entity.field_70121_D);
          double d11 = (1.0D - d4) * d10;
          entity.func_70097_a(DamageSource.func_94539_a(this), (int)((d11 * d11 + d11) / 2.0D * 8.0D * this.field_77280_f + 1.0D));
          double d8 = EnchantmentProtection.func_92092_a(entity, d11);
          entity.field_70159_w += d5 * d8;
          entity.field_70181_x += d6 * d8;
          entity.field_70179_y += d7 * d8;
          if (entity instanceof net.minecraft.entity.player.EntityPlayer)
            this.field_77288_k.put(entity, Vec3.func_72443_a(d5 * d11, d6 * d11, d7 * d11)); 
        } 
      } 
    } 
    this.field_77280_f = f;
  }
  
  public Map func_77277_b() {
    return this.field_77288_k;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\pillage\common\effects\ExplosionObsi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */