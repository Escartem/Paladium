package fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.tile;

import java.util.List;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityTrash extends TileEntity {
  public void func_145845_h() {
    if (!this.field_145850_b.field_72995_K) {
      double range = 0.5D;
      AxisAlignedBB bounds = AxisAlignedBB.func_72330_a(this.field_145851_c + 0.5D - range, this.field_145848_d + 0.5D - range, this.field_145849_e + 0.5D - range, this.field_145851_c + 0.5D + range, this.field_145848_d + 0.5D + range, this.field_145849_e + 0.5D + range);
      List<EntityItem> items = this.field_145850_b.func_72872_a(EntityItem.class, bounds);
      for (EntityItem item : items)
        item.func_70106_y(); 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\may\common\tile\TileEntityTrash.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */