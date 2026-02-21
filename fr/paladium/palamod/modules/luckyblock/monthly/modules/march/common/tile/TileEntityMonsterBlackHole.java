package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.tile;

import fr.paladium.palawither.common.utils.WitherUtils;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityMonsterBlackHole extends TileEntity {
  public void func_145839_a(NBTTagCompound compound) {
    super.func_145839_a(compound);
  }
  
  public void func_145841_b(NBTTagCompound compound) {
    super.func_145841_b(compound);
  }
  
  public void func_145845_h() {
    super.func_145845_h();
    if (!this.field_145850_b.field_72995_K) {
      int radius = 15;
      int x = this.field_145851_c;
      int y = this.field_145848_d;
      int z = this.field_145849_e;
      List entities = this.field_145850_b.func_72839_b(null, AxisAlignedBB.func_72330_a((x - 15), (y - 15), (z - 15), (x + 15), (y + 15), (z + 15)));
      for (Object obj : entities) {
        if (obj instanceof EntityLiving && !(obj instanceof net.minecraft.entity.player.EntityPlayer) && !WitherUtils.isWither((Entity)obj)) {
          EntityLiving entity = (EntityLiving)obj;
          double dX = x + 0.5D - entity.field_70165_t;
          double dY = y + 0.5D - entity.field_70163_u;
          double dZ = z + 0.5D - entity.field_70161_v;
          double distance = Math.sqrt(dX * dX + dY * dY + dZ * dZ);
          double speed = 0.1D;
          if (distance < 15.0D && distance > 0.5D) {
            entity.field_70159_w += dX / distance * 0.1D;
            entity.field_70181_x += dY / distance * 0.1D;
            entity.field_70179_y += dZ / distance * 0.1D;
          } 
        } 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\tile\TileEntityMonsterBlackHole.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */